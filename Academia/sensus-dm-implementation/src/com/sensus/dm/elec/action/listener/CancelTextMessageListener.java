package com.sensus.dm.elec.action.listener;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.canceltext.messages.CancelTextResponse;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The listener interface for receiving cancelTextMessage events.
 * The class that is interested in processing a cancelTextMessage
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addCancelTextMessageListener method. When
 * the cancelTextMessage event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see CancelTextMessageEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=CancelTextMessageListener", description = "CancelTextMessageListener JMX Bean")
public class CancelTextMessageListener extends AbstractListener<CancelTextResponse>
{

	/**
	 * Instantiates a new cancel text message listener.
	 */
	public CancelTextMessageListener()
	{
		setJmsContainer("cancelText.notif.jmsContainer");
	}

	@Override
	public void onResponse(CancelTextResponse response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(new ProcessItem(Integer.parseInt(response.getRequestId()))),
						new DMTenant(response.getCustomerId()));

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
			String status, List<String> messages, ProcessRequest processRequest, CancelTextResponse response)
	{
		processRequest.getFirstProcess().setProcessStatusEnum(ProcessStatusEnum.ABORTED);
		processRequest.getFirstProcess().getFirstProcessItem().setProcessItemStatusEnum(ProcessItemStatusEnum.ABORTED);
	}

	@Override
	protected void setProcessStatusError(
			String status, List<String> messages, ProcessRequest processRequest, CancelTextResponse response,
			String subStatus)
	{
		// nothing happens here because if the cancel process fails, the state of the original process stays the same
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
					" Error aborting process for device with Endpoint ID:  ").append(response.getEndpointId())
					.append(" and EndPointCustomer ID: ").append(response.getCustomerId()));
		}
	}
}
