package com.sensus.dm.elec.action.listener;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.initloadcontrol.messages.InitLoadControlEventtResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The listener interface for receiving demandReponse events.
 * The class that is interested in processing a demandReponse
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addDemandReponseListener method. When
 * the demandReponse event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see DemandReponseEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=DemandReponseListener", description = "DemandReponseListener JMX Bean")
public class DemandReponseListener extends AbstractListener<InitLoadControlEventtResponse>
{

	/**
	 * Instantiates a new demand reponse listener.
	 */
	public DemandReponseListener()
	{
		setJmsContainer("initLoadControlEvent.notif.jmsContainer");
	}

	@Override
	public void onResponse(InitLoadControlEventtResponse response)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		// Oct. 4, 2012
		// InitLoadControlEventtResponse which just have a "RequestID" - string,
		// [9:12:45 AM] SENSUS Jim McCrae: That is available for you to set to whatever you want.
		// These are asynchronous to you but they are correlated and the context is preserved,
		// i.e. requestId is whatever you set on the request. If this is not the case let me
		// know asap cause that's a bug.
		ProcessRequest processRequest = null;

		try
		{
			if (ValidationUtil.isNullOrEmpty(response.getRequestId()))
			{
				return;
			}

			DMProcess process = new DMProcess();
			process.setRniEventId(Integer.parseInt(response.getRequestId()));

			processRequest =
					new ProcessRequest(process, new DMTenant(response.getCustomerId()));
			processRequest.getFirstProcess()
					.setAction(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT)));
			processRequest.getFirstProcess().setProcessStatusEnum(ProcessStatusEnum.IN_PROCESS);

			if (!checkProcessByRniEventId(processRequest, this.getClass().getName()))
			{
				return;
			}

		}
		catch (NumberFormatException e)
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Init Load Control Event Response did not receive required request ID for : ").append(response));
			}
			return;
		}

		createUserContext(processRequest);

		verifyProcessStatus(response.getStatus(), response.getMessages(), processRequest, response,
				response.getSubstatus());

		checkProcessItem(processRequest, this.getClass().getName());

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	@Override
	protected void setProcessStatusSuccess(
			String status, List<String> messages, ProcessRequest processRequest, InitLoadControlEventtResponse response)
	{
		ProcessItem processItem = processRequest.getFirstProcess().getFirstProcessItem();
		processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.RUNNING);
	}

	@Override
	protected void setProcessStatusError(
			String status, List<String> messages, ProcessRequest processRequest,
			InitLoadControlEventtResponse response, String subStatus)
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
