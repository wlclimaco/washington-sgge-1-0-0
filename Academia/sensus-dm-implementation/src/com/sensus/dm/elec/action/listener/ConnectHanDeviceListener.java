package com.sensus.dm.elec.action.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.messages.JoinHanResponse;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;

/**
 * The listener interface for receiving connectHanDevice events.
 * The class that is interested in processing a connectHanDevice
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addConnectHanDeviceListener method. When
 * the connectHanDevice event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ConnectHanDeviceEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=ConnectHanDeviceListener", description = "ConnectHanDeviceListener JMX Bean")
public class ConnectHanDeviceListener extends AbstractListener<JoinHanResponse>
{

	/** The Constant STR_LISTENER. */
	private static final String STR_LISTENER = "### ConnectHanDeviceListener: ";

	/**
	 * Instantiates a new connect han device listener.
	 */
	public ConnectHanDeviceListener()
	{
		setJmsContainer("joinHan.notif.jmsContainer");
	}

	@Override
	public void onResponse(JoinHanResponse response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		DMProcess process = new DMProcess();

		if (!checkRniEventId(process, response.getRequestId()))
		{
			return;
		}

		HanDevice hanDevice = createHanDevice(response.getEndpointId(), response.getEndpointCustomerId());
		process.setProcessItems(new ArrayList<ProcessItem>());
		process.addProcessItem(new ProcessItem(hanDevice, ProcessItemStatusEnum.RUNNING));

		ProcessRequest processRequest =
				new ProcessRequest(process, new DMTenant(response.getEndpointCustomerId()));

		if (!checkProcessItemByRniEventId(processRequest, STR_LISTENER))
		{
			return;
		}

		createUserContext(processRequest);

		verifyProcessStatus(response.getStatus(), response.getMessages(), processRequest, response,
				response.getSubstatus());

		summarizeResponse(processRequest);

		checkDeviceStatus(new DeviceRequest(process.getFirstProcessItem().getDevice()), STR_LISTENER);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	@Override
	protected void setProcessStatusSuccess(
			String status, List<String> messages, ProcessRequest processRequest, JoinHanResponse response)
	{
		ProcessItem item = processRequest.getFirstProcess().getFirstProcessItem();
		item.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);

		Device device = item.getDevice();

		if (device.getDeviceType().equals(DeviceTypeEnum.LCM))
		{
			((LCM)device).setLifecycleStateEnum(LCMLifecycleStateEnum.PENDING_JOIN);
			return;
		}

		((HanDevice)device).setLifecycleStateEnum(HanLifecycleStateEnum.PENDING_JOIN);
	}
}
