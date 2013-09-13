package com.sensus.dm.elec.action.listener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.getloadcontroleventreport.messages.GetLoadControlEventReportResponse;
import com.sensus.api.getloadcontroleventreport.messages.LoadControlEventStatus;
import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemHistory;
import com.sensus.dm.common.process.model.ProcessItemHistoryStatusEnum;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.action.model.DemandResponseParticipationEnum;

/**
 * The listener interface for receiving getDemandResponseStatus events.
 * The class that is interested in processing a getDemandResponseStatus
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addGetDemandResponseStatusListener method. When
 * the getDemandResponseStatus event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see GetDemandResponseStatusEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=GetDemandResponseStatusListener", description = "GetDemandResponseStatusListener JMX Bean")
public class GetDemandResponseStatusListener extends AbstractListener<GetLoadControlEventReportResponse>
{
	/** The Constant DEFAULT_DATE. */
	private static final Long DEFAULT_DATE = 946684800L;

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/**
	 * Instantiates a new gets the demand response status listener.
	 */
	public GetDemandResponseStatusListener()
	{
		setJmsContainer("getLoadControlEventReport.notif.jmsContainer");
	}

	/**
	 * Gets the process summary bcl.
	 * 
	 * @return the process summary bcl
	 */
	public IProcessSummaryBCL getProcessSummaryBCL()
	{
		return processSummaryBCL;
	}

	/**
	 * Sets the process summary bcl.
	 * 
	 * @param processSummaryBCL the new process summary bcl
	 */
	public void setProcessSummaryBCL(IProcessSummaryBCL processSummaryBCL)
	{
		this.processSummaryBCL = processSummaryBCL;
	}

	@Override
	public void onResponse(GetLoadControlEventReportResponse response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		if (ValidationUtil.isNullOrZero(response.getLoadEventId()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						" Get Load Control Event Report did not receive required Load Event Id for: ").append(response));
			}
			return;
		}

		if (ValidationUtil.isNullOrEmpty(response.getLoadControlEventStatus()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						" Get Load Control Event Report did not receive required Load Control Event Status for: ")
						.append(response));
			}
			return;
		}

		// October 4, 2012
		// [8:54:45 AM] SENSUS Jim McCrae: 2. LoadControlEventReports
		// [8:55:53 AM] SENSUS Jim McCrae: 2. These will always have the eventId in the message
		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(), new DMTenant(response.getCustomerId()));

		if (!validateProcessItem(processRequest, response.getLoadEventId(), response.getEndpointId()))
		{
			return;
		}

		createUserContext(processRequest);

		updateProcessItem(processRequest, response);

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	/**
	 * Validate process item.
	 * 
	 * @param processRequest the process request
	 * @param rniEventId the process id
	 * @param deviceId the client end point id
	 * @return true, if successful
	 */
	private boolean validateProcessItem(ProcessRequest processRequest, Integer rniEventId, String deviceId)
	{
		DMProcess process = new DMProcess(new ProcessItem(new Device(deviceId), ProcessItemStatusEnum.RUNNING));
		process.setRniEventId(rniEventId);
		process.setAction(new DMAction(new ActionType(ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS)));

		InternalResultsResponse<DMProcess> internalResponse =
				getProcessBCL().fetchProcessItemsByProcessId(new ProcessRequest(process, processRequest.getTenant()));

		if (internalResponse.isInError() || !internalResponse.hasResults()
				|| ValidationUtil.isNullOrEmpty(internalResponse.getFirstResult().getProcessItems()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						" Get Demand Response Status: Error fetchProcessById"));
			}
			return false;
		}

		processRequest.getFirstProcess().addProcessItem(internalResponse.getFirstResult().getFirstProcessItem());

		return true;
	}

	/**
	 * Update process item.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 */
	private void updateProcessItem(ProcessRequest processRequest, GetLoadControlEventReportResponse response)
	{

		if (!response.getStatus().equalsIgnoreCase(STR_SUCCESS))
		{
			if (!ValidationUtil.isNull(response.getMessages())
					&& !ValidationUtil.isNullOrEmpty(response.getSubstatus()) && response.getMessages().isEmpty())
			{
				response.getMessages().add(response.getSubstatus());
			}

			processRequest.getFirstProcess().getFirstProcessItem()
					.setMessage(DMUtil.generateMessageRni(response.getMessages().toString()));
			return;
		}

		// new method will set the DR participation
		// and set Process Item Histories
		updateDRProgramParticipationInfo(processRequest.getFirstProcess().getFirstProcessItem(), response);

		InternalResponse updateItemResponse = getProcessBCL().updateProcessItems(processRequest);

		if (updateItemResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
						.append(
								"Get Demand Response Status Received Message: Error updateItemResponse Message: ")
						.append(updateItemResponse.getMessageInfoList()));
			}
		}
	}

	/**
	 * Update dr program participation info.
	 * 
	 * @param item the item
	 * @param hanResponse the han response
	 */
	private void updateDRProgramParticipationInfo(ProcessItem item, GetLoadControlEventReportResponse hanResponse)
	{
		// method will set the DR participation
		// and set Process Item Histories
		List<LoadControlEventStatus> loadControlEventStatuses = hanResponse.getLoadControlEventStatus();

		// if there`s no event statuses...get out
		if (ValidationUtil.isNullOrEmpty(loadControlEventStatuses))
		{
			return;
		}

		List<ProcessItemHistory> processItemHistories = new ArrayList<ProcessItemHistory>();

		// go through all the load control event status messages
		for (LoadControlEventStatus loadControlEventStatus : loadControlEventStatuses)
		{
			String lcs = loadControlEventStatus.getLoadControlStatus();

			// for each possible resulting load control event status...we have to set 2 values...
			// 1) the DR program participation
			// which will be either Full Participation, Partial Participation, or Opt-out
			// and also 2) the Process Item History
			if (lcs.equals(ProcessItemHistoryStatusEnum.EVENTRECEIVED.getValue())
					|| lcs.equals(ProcessItemHistoryStatusEnum.EVENTSTARTED.getValue()))
			{
				item.setProcessItemStatusEnum(ProcessItemStatusEnum.RUNNING);

				processItemHistories.add(
						new ProcessItemHistory(
								ProcessItemHistoryStatusEnum.enumForValue(lcs),
								checkTimeDelta(loadControlEventStatus, hanResponse.getStartingTime())));

			}
			else
			{
				item.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);

				if (lcs.equals(DemandResponseParticipationEnum.EVENTSUPERSEDED.getValue())
						|| lcs.equals(DemandResponseParticipationEnum.EVENTPARTIALLYCOMPLETEDWITHUSEROPTOUT.getValue())
						|| lcs.equals(DemandResponseParticipationEnum.EVENTPARTIALLYCOMPLETEDDUETOUSEROPTIN.getValue())
						|| lcs.equalsIgnoreCase(DemandResponseParticipationEnum.USERCHOOSEOPTIN.getValue()))
				{
					item.setParticipation(DemandResponseParticipationEnum.PARTIAL_PARTICIPATION.getValue());
				}
				else if (lcs.equalsIgnoreCase(ProcessItemHistoryStatusEnum.EVENTCOMPLETED.getValue()))
				{

					item.setParticipation(DemandResponseParticipationEnum.FULL_PARTICIPATION.getValue());

					processItemHistories.add(
							new ProcessItemHistory(
									ProcessItemHistoryStatusEnum.enumForValue(lcs),
									checkTimeDelta(loadControlEventStatus, hanResponse.getStartingTime())));

				}
				else if (lcs.equalsIgnoreCase(DemandResponseParticipationEnum.USERCHOOSEOPTOUT.getValue()))
				{
					item.setParticipation(DemandResponseParticipationEnum.PARTIAL_PARTICIPATION_AND_OPT_OUT.getValue());
				}
				else if (lcs.equalsIgnoreCase(ProcessItemHistoryStatusEnum.EVENTCANCELLED.getValue()))
				{
					item.setParticipation(DemandResponseParticipationEnum.PARTIAL_PARTICIPATION.getValue());

					processItemHistories.add(
							new ProcessItemHistory(
									ProcessItemHistoryStatusEnum.enumForValue(lcs),
									checkTimeDelta(loadControlEventStatus, hanResponse.getStartingTime())));
				}
			}
		}

		item.setProcessItemHistories(processItemHistories);
	}

	/**
	 * Check time delta.
	 * 
	 * @param processItemHistory the process item history
	 * @param loadControlEventStatus the load control event status
	 * @param time the time
	 * @return the date
	 */
	private Date checkTimeDelta(LoadControlEventStatus loadControlEventStatus, Integer time)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(DMConvertUtil.convertDateToDefaultUTC(DMConvertUtil.convertIntegerToDate(time + DEFAULT_DATE)));
		cal.add(Calendar.MINUTE, loadControlEventStatus.getTimeDelta());

		return cal.getTime();
	}
}
