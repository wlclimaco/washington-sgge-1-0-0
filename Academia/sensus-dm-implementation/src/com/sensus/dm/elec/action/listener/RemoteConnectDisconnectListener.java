package com.sensus.dm.elec.action.listener;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.cdstatechangenotification.messages.CdStateChangeNotificationRequest;
import com.sensus.api.cdstatenotification.messages.ConnectionStateNotification;
import com.sensus.common.messagetypes.loadshed.MeterRelays;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The listener interface for receiving CDNotificationReciver events.
 * The class that is interested in processing a CDNotificationReciver
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCDNotificationReciverListener</code> method. When
 * the CDNotificationReciver event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see CDNotificationReciverEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=RemoteConnectDisconnectListener", description = "RemoteConnectDisconnectListener JMX Bean")
public class RemoteConnectDisconnectListener extends AbstractListener<java.lang.Object>
{

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/**
	 * Instantiates a new connect han device listener.
	 */
	public RemoteConnectDisconnectListener()
	{
		setJmsContainer("cd.statechangenotif.jmsContainer");
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(java.lang.Object responseObject)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		// fill the meterRelays by reference in the validateResponseType method
		MeterRelays meterRelays = validateResponseType(responseObject);
		if (ValidationUtil.isNull(meterRelays))
		{
			return;
		}

		HanDevice hanDevice =
				createHanDevice(meterRelays.getMeterIdentity().getMeterNo(), meterRelays.getMeterIdentity()
						.getCustomerId());

		DMProcess process = new DMProcess(new ProcessItem(hanDevice));

		ProcessRequest processRequest = new ProcessRequest(process, meterRelays.getMeterIdentity()
				.getCustomerId());

		InternalResultsResponse<ProcessItem> getProcessesItemsResp =
				getProcessSummaryBCL().fetchProcessItemsByConnectDisconnect(processRequest);

		if (getProcessesItemsResp.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
						.append(" Not Found for Response Device with Endpoint ID: ")
						.append(hanDevice.getDeviceId()).append(" and EndPointCustomer ID: ")
						.append(hanDevice.getRadio().getCustomerId()));
			}
			return;
		}
		process.setProcessItems(getProcessesItemsResp.getResultsList());

		createUserContext(processRequest);

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}

	}

	/**
	 * Validate response type.
	 * 
	 * @param responseObject the response object
	 * @param meterRelays the meter relays
	 * @return true, if successful
	 */
	private MeterRelays validateResponseType(Object responseObject)
	{

		// if our original response is null, quit
		if (ValidationUtil.isNull(responseObject))
		{
			return null;
		}

		// continue if the response is of type ConnectionStateNotification
		// this listener receives 2 different types of objects, so we must check
		if (responseObject instanceof ConnectionStateNotification)
		{
			ConnectionStateNotification responseState = (ConnectionStateNotification)responseObject;

			// without connection state and meter relays, we cannot continue
			if (!ValidationUtil.isNull(responseState.getConnectionState())
					&& (!ValidationUtil.isNull(responseState.getMeterRelays())))
			{
				// get the meter relays from this object and finish the process
				return responseState.getMeterRelays();
			}
		}
		// if this is CdStateChangeNotificationRequest, continue working
		else if (responseObject instanceof CdStateChangeNotificationRequest)
		{
			CdStateChangeNotificationRequest responseNotify = (CdStateChangeNotificationRequest)responseObject;

			if (!ValidationUtil.isNull(responseNotify.getConnectionState())
					&& (!ValidationUtil.isNull(responseNotify.getMeterRelays())))
			{
				return responseNotify.getMeterRelays();
			}
		}

		// the object was neither ConnectionStateNotification nor CdStateChangeNotificationRequest
		// or the connection state or meter relays were null, so we can`t continue
		return null;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#setProcessRequestStatusesError(java.lang.String,
	 * java.util.List, com.sensus.dm.common.process.model.request.ProcessRequest)
	 */
	@Override
	protected void setProcessStatusError(
			String status, List<String> messages, ProcessRequest processRequest,
			java.lang.Object response, String subStatus)
	{
		ProcessItem processItem = processRequest.getFirstProcess().getFirstProcessItem();

		processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);

		if (!ValidationUtil.isNullOrEmpty(messages))
		{
			processItem.setMessage(DMUtil.generateMessageRni(messages.toString()));
		}
		else
		{
			processItem.setMessage(DMUtil.generateMessageRni(subStatus));
		}

		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
	}
}