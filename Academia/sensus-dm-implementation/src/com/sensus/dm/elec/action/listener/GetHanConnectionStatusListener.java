package com.sensus.dm.elec.action.listener;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbeesimplemessage.messages.EndpointConnectionStatus;
import com.sensus.api.zigbeesimplemessage.messages.GetHanNetworkStatusResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;

/**
 * The listener interface for receiving getHanConnectionStatus events.
 * The class that is interested in processing a getHanConnectionStatus
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addGetHanConnectionStatusListener method. When
 * the getHanConnectionStatus event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see GetHanConnectionStatusEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=GetHanConnectionStatusListener", description = "GetHanConnectionStatusListener JMX Bean")
public class GetHanConnectionStatusListener extends AbstractListener<GetHanNetworkStatusResponse>
{
	/** The Constant STR_LISTENER. */
	private static final String STR_LISTENER = "### GetHanConnectStatusListener: ";

	/**
	 * Instantiates a new gets the han connection status listener.
	 */
	public GetHanConnectionStatusListener()
	{
		setJmsContainer("getHanStatus.notif.jmsContainer");
	}

	@Override
	public void onResponse(GetHanNetworkStatusResponse response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		DMProcess process = new DMProcess(new DMAction(new ActionType(ActionTypeEnum.GET_HAN_CONNECTION_STATUS)));
		DeviceRequest deviceRequest = new DeviceRequest();

		// You must save the status of the device every time I get a status message.
		if (checkDeviceStatus(deviceRequest, response)) // Populates "deviceRequest".
		{
			checkDeviceStatus(deviceRequest, this.getClass().getName()); // save status of the device.
		}

		if (!checkRniEventId(process, response.getRequestId()))
		{
			return;
		}

		createProcessItem(process, deviceRequest.getDevices());

		ProcessRequest processRequest =
				new ProcessRequest(process, new DMTenant(response.getCustomerId()));

		if (!checkProcessItemByRniEventId(processRequest, STR_LISTENER))
		{
			return;
		}

		createUserContext(processRequest);

		verifyProcessItemStatus(process, response);

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	/**
	 * Check han device status.
	 * 
	 * @param deviceRequest the device request
	 * @param response the response
	 */
	private boolean checkDeviceStatus(DeviceRequest deviceRequest, GetHanNetworkStatusResponse response)
	{
		// no connection status means there are no connected HAN devices, so just process the meter
		if (!ValidationUtil.isNullOrEmpty(response.getConnectionStatus()))
		{
			for (EndpointConnectionStatus endPoint : response.getConnectionStatus())
			{
				HanDevice hanDevice = createHanDevice(endPoint.getEndpointId(), endPoint.getCustomerId());

				String consStatus = endPoint.getConStatus();

				if (consStatus.startsWith(STR_CONNECT))
				{
					hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.JOINED);
				}
				else if (consStatus.startsWith(STR_DISCONNECT))
				{
					hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.UNJOINED);
				}

				deviceRequest.addDevice(hanDevice);
			}
			return true;
		}
		else
		{
			deviceRequest.addDevice(new ElectricMeter(response.getMeterId()));
			return false;
		}
	}

	/**
	 * Verify process item status.
	 * 
	 * @param process the process
	 * @param response the response
	 */
	private void verifyProcessItemStatus(DMProcess process, GetHanNetworkStatusResponse response)
	{
		for (ProcessItem item : process.getProcessItems())
		{
			item.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);

			if (!STR_SUCCESS.startsWith(response.getStatus()))
			{
				item.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
				if (!ValidationUtil.isNullOrEmpty(response.getMessages()))
				{
					item.setMessage(DMUtil.generateMessageRni(response.getMessages().toString()));
				}
				else
				{
					item.setMessage(DMUtil.generateMessageRni(response.getSubstatus()));
				}
			}
		}
	}

}
