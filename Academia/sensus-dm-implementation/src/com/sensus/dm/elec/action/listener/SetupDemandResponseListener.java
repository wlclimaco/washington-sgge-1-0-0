package com.sensus.dm.elec.action.listener;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesetloadcontrolparameter.messages.SetLoadControlParameterResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The listener interface for receiving setupDemandResponse events.
 * The class that is interested in processing a setupDemandResponse
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's >addSetupDemandResponseListener method. When
 * the setupDemandResponse event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see SetupDemandResponseEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=SetupDemandResponseListener", description = "SetupDemandResponseListener JMX Bean")
public class SetupDemandResponseListener extends AbstractListener<SetLoadControlParameterResponse>
{

	/**
	 * Instantiates a new setup demand response listener.
	 */
	public SetupDemandResponseListener()
	{
		setJmsContainer("setLoadControlParameter.notif.jmsContainer");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(SetLoadControlParameterResponse response)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		// Oct. 4, 2012
		// SetLoadControlParameterResponse which just have a "RequestID" - string,
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
			processRequest.getFirstProcess().setAction(
					new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP)));
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
						" Set Load Control Parameter Response did not receive required request ID for: ").append(
						response));
			}
			return;
		}

		createUserContext(processRequest);

		verifyProcessStatus(response.getStatus(), response.getMessages(), processRequest, response,
				response.getSubstatus());

		processRequest.getFirstProcess().getFirstProcessItem()
				.setProcessItemStatusEnum(processRequest.getProcessItemStatusEnum());

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}
}
