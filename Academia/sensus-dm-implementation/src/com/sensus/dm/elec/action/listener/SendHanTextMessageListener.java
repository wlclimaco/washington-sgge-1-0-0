package com.sensus.dm.elec.action.listener;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.sendtext.messages.SendTextResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The listener interface for receiving sendHanTextMessage events.
 * The class that is interested in processing a sendHanTextMessage
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addSendHanTextMessageListener method. When
 * the sendHanTextMessage event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see SendHanTextMessageEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=SendHanTextMessageListener", description = "SendHanTextMessageListener JMX Bean")
public class SendHanTextMessageListener extends AbstractListener<SendTextResponse>
{
	/**
	 * Instantiates a new send han text message listener.
	 */
	public SendHanTextMessageListener()
	{
		setJmsContainer("sendText.notif.jmsContainer");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(SendTextResponse response)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		if (ValidationUtil.isNullOrZero(response.getMessageId()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						" Send Text Response did not receive required message ID for: ").append(response));
			}
			return;
		}
		// Oct. 4, 2012
		// [8:54:45 AM] SENSUS Jim McCrae: 3. ConfirmTextMessage.
		// [8:56:27 AM] SENSUS Jim McCrae: 3. These will always have the messageId of the display text request in the
		// message
		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(new ProcessItem(response.getMessageId())), new DMTenant(
						response.getCustomerId()));

		createUserContext(processRequest);

		verifyProcessStatus(response.getStatus(), response.getMessages(), processRequest, response,
				response.getSubstatus());

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#setProcessStatusSuccess
	 * (java.lang.String, java.util.List, com.sensus.dm.common.process.model.request.ProcessRequest, java.lang.Object)
	 */
	@Override
	protected void setProcessStatusSuccess(
			String status, List<String> messages, ProcessRequest processRequest, SendTextResponse response)
	{
		processRequest.getFirstProcess().getFirstProcessItem()
				.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#setProcessStatusError
	 * (java.lang.String, java.util.List, com.sensus.dm.common.process.model.request.ProcessRequest, java.lang.Object)
	 */
	@Override
	protected void setProcessStatusError(
			String status, List<String> messages, ProcessRequest processRequest, SendTextResponse response,
			String subStatus)
	{
		processRequest.getFirstProcess().getFirstProcessItem().setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);

		if (!ValidationUtil.isNullOrEmpty(messages))
		{
			processRequest.getFirstProcess().getFirstProcessItem()
					.setMessage(DMUtil.generateMessageRni(messages.toString()));
		}
		else
		{
			processRequest.getFirstProcess().getFirstProcessItem().setMessage(DMUtil.generateMessageRni(subStatus));
		}

	}
}
