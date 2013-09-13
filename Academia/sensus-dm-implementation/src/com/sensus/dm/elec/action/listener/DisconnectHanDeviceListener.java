package com.sensus.dm.elec.action.listener;

import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.leavehan.messages.LeaveHanResponse;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;

/**
 * The listener interface for receiving disconnectHanDevice events.
 * The class that is interested in processing a disconnectHanDevice
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addDisconnectHanDeviceListener method. When
 * the disconnectHanDevice event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see DisconnectHanDeviceEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=DisconnectHanDeviceListener", description = "DisconnectHanDeviceListener JMX Bean")
public class DisconnectHanDeviceListener extends AbstractListener<LeaveHanResponse>
{

	/**
	 * Instantiates a new disconnect han device listener.
	 */
	public DisconnectHanDeviceListener()
	{
		setJmsContainer("leaveHan.notif.jmsContainer");
	}

	@Override
	public void onResponse(LeaveHanResponse response)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		HanDevice hanDevice = createHanDevice(response.getEndpointId(), response.getCustomerId());

		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(new DMAction(new ActionType(ActionTypeEnum.DISCONNECT_HAN_DEVICE))),
						new DMTenant(response.getCustomerId()));

		if (!verifyProcessItems(processRequest, hanDevice, this.getClass().getName()))
		{
			return;
		}

		createUserContext(processRequest);

		verifyProcessStatus(response.getStatus(), response.getMessages(), processRequest, response,
				response.getSubstatus());

		summarizeResponse(processRequest);

		checkDeviceStatus(new DeviceRequest(hanDevice), this.getClass().getName());

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	@Override
	protected void setProcessStatusSuccess(
			String status, List<String> messages, ProcessRequest processRequest, LeaveHanResponse response)
	{
		((HanDevice)processRequest.getFirstProcess().getFirstProcessItem().getDevice())
				.setLifecycleStateEnum(HanLifecycleStateEnum.UNJOINED);
	}

}
