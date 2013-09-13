package com.sensus.dm.elec.action.listener;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.cancelloadcontrol.messages.CancelLoadControlEventtResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The listener interface for receiving cancelDemandResponse events.
 * The class that is interested in processing a cancelDemandResponse
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCancelDemandResponseListener</code> method. When
 * the cancelDemandResponse event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see CancelDemandResponseEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=CancelDemandResponseListener", description = "CancelDemandResponseListener JMX Bean")
public class CancelDemandResponseListener extends AbstractListener<CancelLoadControlEventtResponse>
{

	/** The Constant AND_END_POINT_CUSTOMER_ID. */
	private static final String AND_END_POINT_CUSTOMER_ID = " and EndPointCustomer ID: ";

	/** The Constant ERROR_ABORTING_PROCESS_FOR_DEVICE_WITH_ENDPOINT_ID. */
	private static final String ERROR_ABORTING_PROCESS_FOR_DEVICE_WITH_ENDPOINT_ID =
			" Error aborting process for device with Endpoint ID: ";

	/**
	 * Instantiates a new cancel demand response listener.
	 */
	public CancelDemandResponseListener()
	{
		setJmsContainer("cancelLoadControlEvent.notif.jmsContainer");
	}

	@Override
	public void onResponse(CancelLoadControlEventtResponse response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		ProcessRequest processRequest = null;

		DMProcess process = new DMProcess();

		if (!checkRniEventId(process, response.getRequestId()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						" Cancel Load Control Event Response did not receive required request ID for: ").append(
						response));
			}
			return;
		}

		processRequest = new ProcessRequest(process, new DMTenant(response.getCustomerId()));
		processRequest.getFirstProcess().setAction(
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT)));

		if (!checkProcessByRniEventId(processRequest, this.getClass().getName()))
		{
			return;
		}

		createUserContext(processRequest);

		verifyProcessStatus(response.getStatus(), response.getMessages(), processRequest, response,
				response.getSubstatus());

		// adding summarize so the cancel process will mark the original process as aborted if successful
		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}

	}

	@Override
	protected void setProcessStatusSuccess(
			String status, List<String> messages, ProcessRequest processRequest,
			CancelLoadControlEventtResponse response)
	{
		processRequest.getFirstProcess().setProcessStatusEnum(ProcessStatusEnum.ABORTED);

		if (!ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProcessItems()))
		{
			for (ProcessItem processItem : processRequest.getFirstProcess().getProcessItems())
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.ABORTED);
			}
		}
	}

	@Override
	protected void setProcessStatusError(
			String status, List<String> messages, ProcessRequest processRequest,
			CancelLoadControlEventtResponse response, String subStatus)
	{
		// nothing happens here because if the cancel process fails, the state of the original process stays the same
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
					ERROR_ABORTING_PROCESS_FOR_DEVICE_WITH_ENDPOINT_ID).append(response.getEndpointId())
					.append(AND_END_POINT_CUSTOMER_ID).append(response.getCustomerId()));
		}
	}
}
