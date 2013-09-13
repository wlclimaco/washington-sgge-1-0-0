package com.sensus.dm.elec.action.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.zigbee.getloadcontrolparameter.messages.GetLoadControlParameterResponse;
import com.sensus.bcf.drserver.util.ParameterIdentifier;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;

/**
 * The listener interface for receiving getLoadControlParameter events.
 * The class that is interested in processing a getLoadControlParameter
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addGetLoadControlParameterListener method. When
 * the getLoadControlParameter event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see GetLoadControlParameterEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=GetLoadControlParameterListener", description = "GetLoadControlParameterListener JMX Bean")
public class GetLoadControlParameterListener extends AbstractListener<GetLoadControlParameterResponse>
{
	/** The Constant STR_LISTENER. */
	private static final String STR_LISTENER = "### GetLoadControlParameterListener: ";

	/** The Constant ENROLLMENT. */
	private static final String ENROLLMENT = "ENROLLMENT";

	/** The Constant RANDOMIZE_START. */
	private static final String RANDOMIZE_START = "RANDOMIZE_START";

	/** The Constant RANDOMIZE_END. */
	private static final String RANDOMIZE_END = "RANDOMIZE_END";

	/** The Constant DEVICE_CLASS. */
	private static final String DEVICE_CLASS = "DEVICE_CLASS";

	/** The Constant TAMPER_TIMEOUT. */
	private static final String TAMPER_TIMEOUT = "TAMPER_TIMEOUT";

	/** The Constant RELAY. */
	private static final String RELAY = "RELAY";

	/** The Constant CODE. */
	private static final String CODE = "CODE";

	/** The Constant UNDERLINE. */
	private static final String UNDERLINE = "_";

	/** The Constant SIXTY. */
	private static final int SIXTY = 60;

	/**
	 * Instantiates a new gets the load control parameter listener.
	 */
	public GetLoadControlParameterListener()
	{
		setJmsContainer("getLoadControlParameter.notif.jmsContainer");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(GetLoadControlParameterResponse hanResponse)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		DeviceRequest deviceRequest = new DeviceRequest();

		checkDeviceStatus(deviceRequest, hanResponse); // Populates "deviceRequest".
		checkDeviceStatus(deviceRequest, this.getClass().getName()); // save status of the device.

		DMProcess process = new DMProcess();
		createProcessItem(process, deviceRequest.getDevices());

		ProcessRequest processRequest =
				new ProcessRequest(process, new DMTenant(hanResponse.getCustomerId()));

		if (!checkRniEventId(process, hanResponse.getRequestId()))
		{
			return;
		}

		if (checkProcessByRniEventId(processRequest, STR_LISTENER))
		{
			insertProcessProperty(processRequest, hanResponse);
		}

		if (!checkProcessItemByRniEventId(processRequest, STR_LISTENER))
		{
			return;
		}

		createUserContext(processRequest);

		verifyProcessItemStatus(process, hanResponse);

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	/**
	 * Check device status.
	 * 
	 * @param deviceRequest the device request
	 * @param hanResponse the han response
	 */
	private void checkDeviceStatus(DeviceRequest deviceRequest, GetLoadControlParameterResponse hanResponse)
	{
		HanDevice hanDevice = createHanDevice(hanResponse.getEndpointId(), hanResponse.getCustomerId());

		if (hanResponse.getStatus().startsWith(STR_SUCCESS))
		{
			String connStatusName = hanResponse.getConnectionStatus().name();

			if (connStatusName.startsWith(STR_CONNECT))
			{
				hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.JOINED);
			}
			else if (connStatusName.startsWith(STR_DISCONNECT))
			{
				hanDevice.setLifecycleStateEnum(HanLifecycleStateEnum.UNJOINED);
			}
		}

		deviceRequest.addDevice(hanDevice);
	}

	/**
	 * Verify process item status.
	 * 
	 * @param process the process
	 * @param hanResponse the han response
	 */
	private void verifyProcessItemStatus(DMProcess process, GetLoadControlParameterResponse hanResponse)
	{
		for (ProcessItem item : process.getProcessItems())
		{
			item.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);

			if (!STR_SUCCESS.startsWith(hanResponse.getStatus()))
			{
				item.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
				if (!ValidationUtil.isNullOrEmpty(hanResponse.getMessages()))
				{
					item.setMessage(DMUtil.generateMessageRni(hanResponse.getMessages().toString()));
				}
				else
				{
					item.setMessage(DMUtil.generateMessageRni(hanResponse.getSubstatus()));
				}
			}
		}
	}

	/**
	 * Update process.
	 * 
	 * @param processRequest the process request
	 * @param hanResponse the han response
	 */
	private void insertProcessProperty(ProcessRequest processRequest, GetLoadControlParameterResponse hanResponse)
	{

		if (ValidationUtil.isNull(processRequest.getFirstProcess().getAction())
				|| !(ActionTypeEnum.GET_TAMPER_DETECT_TIMER.equals(processRequest.getFirstProcess().getAction()
						.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.GET_DEMAND_RESPONSE_SETUP_STATUS.equals(processRequest.getFirstProcess().getAction()
						.getActionType().getActionTypeEnum())))
		{
			return;
		}

		if (!verifyPropertyParam(processRequest.getFirstProcess().getProperties(), hanResponse))
		{
			return;
		}

		processRequest.getFirstProcess().setProperties(new ArrayList<Property>());
		createProperties(processRequest.getFirstProcess().getProperties(), hanResponse);

		InternalResponse updateProcessPropertyResponse = getProcessBCL().insertProcessProperty(processRequest);

		if (updateProcessPropertyResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
						.append(STR_LISTENER + "Received Message: Error insertProcessProperty Message: ")
						.append(updateProcessPropertyResponse.getMessageInfoList()));
			}
		}
	}

	/**
	 * Convert to minutes.
	 * 
	 * @param seconds the seconds
	 * @return the string
	 */
	private String convertToMinutes(Integer seconds)
	{
		try
		{
			return String.valueOf(seconds / SIXTY);
		}
		catch (NumberFormatException ex)
		{
			return String.valueOf(seconds);
		}
	}

	/**
	 * Creates the properties.
	 * 
	 * @param properties the properties
	 * @param hanResponse the han response
	 */
	private void createProperties(List<Property> properties, GetLoadControlParameterResponse hanResponse)
	{
		if (ParameterIdentifier.TamperDetectTimeoutThreshold.equals(hanResponse.getParameterIdentifier()))
		{
			createPropertyTamperTimeOut(properties, hanResponse);
			return;
		}

		if (!ValidationUtil.isNull(hanResponse.getRelayId()))
		{
			createPropertyDemandResponseSetupLcm(properties, hanResponse);
			return;
		}

		if (ParameterIdentifier.UtilityEnrollmentGroup.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_ENROLLMENT_CODE.getValue(), String
					.valueOf(hanResponse.getParameterValue())));
		}
		else if (ParameterIdentifier.StartRandomizationDuration.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_START.getValue(),
					convertToMinutes(hanResponse.getParameterValue())));
		}
		else if (ParameterIdentifier.StopRandomizationDuration.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(PropertyEnum.DEMAND_RESPONSE_RANDOMIZE_END.getValue(),
					convertToMinutes(hanResponse.getParameterValue())));
		}

	}

	/**
	 * Creates the property tamper time out.
	 * 
	 * @param properties the properties
	 * @param hanResponse the han response
	 */
	private void createPropertyTamperTimeOut(List<Property> properties, GetLoadControlParameterResponse hanResponse)
	{
		String relay = RELAY + hanResponse.getRelayId();

		properties.add(new Property(relay + UNDERLINE + TAMPER_TIMEOUT, convertToMinutes(hanResponse
				.getParameterValue())));

	}

	/**
	 * Creates the property demand response setup lcm.
	 * 
	 * @param properties the properties
	 * @param hanResponse the han response
	 */
	private void createPropertyDemandResponseSetupLcm(List<Property> properties,
			GetLoadControlParameterResponse hanResponse)
	{
		String relay = RELAY + hanResponse.getRelayId();

		if (ParameterIdentifier.UtilityEnrollmentGroup.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(relay + UNDERLINE + ENROLLMENT + UNDERLINE + CODE, String
					.valueOf(hanResponse.getParameterValue())));
		}
		else if (ParameterIdentifier.StartRandomizationDuration.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(relay + UNDERLINE + RANDOMIZE_START, convertToMinutes(hanResponse
					.getParameterValue())));
		}
		else if (ParameterIdentifier.StopRandomizationDuration.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(relay + UNDERLINE + RANDOMIZE_END, convertToMinutes(hanResponse
					.getParameterValue())));
		}
		else if (ParameterIdentifier.DeviceClass.equals(hanResponse.getParameterIdentifier()))
		{
			properties.add(new Property(relay + UNDERLINE + DEVICE_CLASS, String
					.valueOf(hanResponse.getParameterValue())));
		}

	}

	/**
	 * Verify property param.
	 * 
	 * @param properties the properties
	 * @param hanResponse the han response
	 * @return true, if successful
	 */
	private boolean verifyPropertyParam(List<Property> properties, GetLoadControlParameterResponse hanResponse)
	{
		String strParam = null;

		if (hanResponse.getParameterIdentifier().equals(ParameterIdentifier.UtilityEnrollmentGroup))
		{
			strParam = ENROLLMENT;
		}
		else if (hanResponse.getParameterIdentifier().equals(ParameterIdentifier.StartRandomizationDuration))
		{
			strParam = RANDOMIZE_START;
		}
		else if (hanResponse.getParameterIdentifier().equals(ParameterIdentifier.StopRandomizationDuration))
		{
			strParam = RANDOMIZE_END;
		}
		else if (hanResponse.getParameterIdentifier().equals(ParameterIdentifier.DeviceClass))
		{
			strParam = DEVICE_CLASS;
		}

		else if (hanResponse.getParameterIdentifier().equals(ParameterIdentifier.TamperDetectTimeoutThreshold))
		{
			strParam = TAMPER_TIMEOUT;
		}

		return verifyPropertyParam(properties, strParam, hanResponse.getRelayId());
	}

	/**
	 * Verify property param.
	 * 
	 * @param properties the properties
	 * @param param the param
	 * @param relay the relay
	 * @return true, if successful
	 */
	private boolean verifyPropertyParam(List<Property> properties, String param, Integer relay)
	{

		if (!ValidationUtil.isNullOrZero(relay))
		{
			param = RELAY + relay + UNDERLINE + param;
		}

		for (Property property : properties)
		{
			if (property.getPropertyName().indexOf(param) > 0)
			{
				return false;
			}
		}
		return true;
	}
}
