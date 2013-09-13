package com.sensus.dm.elec.action.bcl.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.messagetypes.han.HanSecurityToken;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class DMActionProcessorValidator.
 * 
 * @author QAT Global
 * 
 */
public class DMActionProcessorValidator
{
	/** The log. */
	private static transient Log LOG = LogFactory.getLog(DMActionProcessorValidator.class);

	/** The Constant FAILED. */
	protected static final String FAILED = "sensus.dm.common.process.nc.process.failed*";

	/** The Constant INVALID_DEVICE_TYPE. */
	protected static final String INVALID_DEVICE_TYPE = "sensus.dm.common.process.nc.device.type.invalid*";

	/** The Constant TIMED_OUT. */
	protected static final String TIMED_OUT = "sensus.dm.common.process.nc.process.timed.out*";

	/** The Constant COMPLETED. */
	protected static final String COMPLETED = "sensus.dm.common.process.nc.process.completed*";

	/** The Constant PROCESSING_DEVICE. */
	protected static final String PROCESSING_DEVICE = "sensus.dm.common.process.processingDevice*";

	/** The Constant INVALID_ACTION. */
	protected static final String INVALID_ACTION = "sensus.dm.common.process.nc.process.invalid.action*";

	/** The Constant UNINSTALLED_ELECTRIC_METER. */
	protected static final String UNINSTALLED_ELECTRIC_METER =
			"sensus.dm.common.process.nc.uninstalled.electric.meter*";

	/** The Constant INVALID_TARGET. */
	protected static final String INVALID_TARGET = "sensus.dm.common.process.nc.process.invalid*";

	/** The Constant INVALID_ENROLLMENT_CODE. */
	protected static final String INVALID_ENROLLMENT_CODE = "sensus.dm.common.process.nc.enrollmentCode.invalid*";

	/** The Constant DISCONNECTED. */
	protected static final String DISCONNECTED = "sensus.dm.common.process.nc.process.disconnected*";

	/** The Constant EXCEPTION. */
	protected static final String EXCEPTION = "sensus.dm.common.process.nc.process.exception*";

	/** The Constant DECODE_FAILED. */
	protected static final String DECODE_FAILED = "sensus.dm.common.process.nc.process.decode.failed*";

	/** The Constant MISSING_METER_ID. */
	protected static final String MISSING_METER_ID = "sensus.dm.common.process.nc.process.missing.meter.id*";

	/** The Constant INVALID_FOR_ELECTRIC_METERS. */
	protected static final String INVALID_FOR_ELECTRIC_METERS =
			"sensus.dm.common.process.nc.process.invalid.for.electric.meters*";

	/** The Constant INVALID_FOR_DISCONNECTED_HAN_DEVICES. */
	protected static final String INVALID_FOR_DISCONNECTED_HAN_DEVICES =
			"sensus.dm.common.process.nc.process.invalid.for.disconnected.han.devices*";

	/** The Constant EXCEPTION_FOUND. */
	protected static final String EXCEPTION_FOUND = " Exception/Error for flenxetId: ";

	/** The Constant DOT_ERRORS. */
	protected static final String DOT_ERRORS = " Errors: ";

	/** The Constant STR_LOG. */
	protected static final String STR_LOG = "### ";

	/**
	 * Checks if is install code available.
	 * 
	 * @param device the device
	 * @return true, if is install code available
	 */
	public boolean isInstallCodeAvailable(Device device)
	{

		switch (device.getDeviceType())
		{
			case HAN_DEVICE:
				return !ValidationUtil.isNullOrEmpty(((HanDevice)device)
						.getConfiguration().getInstallCode());

			case LCM:
				return !ValidationUtil.isNullOrEmpty(((LCM)device)
						.getConfiguration().getInstallCode());

			default:
				return false;
		}

	}

	/**
	 * Checks if is remote action.
	 * 
	 * @param action the action
	 * @return true, if is remote action
	 */
	public boolean isRemoteAction(DMAction action)
	{
		return ActionTypeEnum.REMOTE_CONNECT.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.REMOTE_DISCONNECT.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.REMOTE_ARM_CONNECT.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.GET_REMOTE_CONNECT_STATUS.equals(action.getActionType().getActionTypeEnum());
	}

	/**
	 * Checks if is han action.
	 * 
	 * @param action the action
	 * @return true, if is han action
	 */
	public boolean isHanAction(DMAction action)
	{
		return ActionTypeEnum.DELETE_HAN_DEVICE.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.IMPORT_HAN_DEVICE.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.RETRY_IMPORT_HAN_DEVICE.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.GET_HAN_CONNECTION_STATUS.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.SEND_HAN_TEXT_MESSAGE.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.CONNECT_HAN_DEVICE.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.GET_TAMPER_DETECT_TIMER.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.DISCONNECT_HAN_DEVICE.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.GET_DEMAND_RESPONSE_SETUP_STATUS.equals(action.getActionType()
						.getActionTypeEnum());
	}

	/**
	 * Checks if is meter action.
	 * 
	 * @param action the action
	 * @return true, if is meter action
	 */
	public boolean isMeterAction(DMAction action)
	{
		return ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.equals(action.getActionType()
						.getActionTypeEnum())
				|| ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP.equals(action.getActionType()
						.getActionTypeEnum())
				|| ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS.equals(action.getActionType()
						.getActionTypeEnum())
				|| ActionTypeEnum.SET_TAMPER_DETECT_TIMER.equals(action.getActionType().getActionTypeEnum())
				|| ActionTypeEnum.DEMAND_READ.equals(action.getActionType().getActionTypeEnum());
	}

	/**
	 * Checks if is cancel action.
	 * 
	 * @param action the action
	 * @return true, if is cancel action
	 */
	public boolean isCancelAction(DMAction action)
	{
		return ActionTypeEnum.CANCEL_DEMAND_RESPONSE_EVENT.equals(action.getActionType().getActionTypeEnum()) ||
				ActionTypeEnum.CANCEL_SEND_HAN_TEXT_MESSAGE.equals(action.getActionType().getActionTypeEnum());
	}

	/**
	 * Checks if is entek lcm.
	 * 
	 * @param device the device
	 * @return true, if is entek lcm
	 */
	public boolean isEntekLCM(Device device)
	{
		return DeviceTypeEnum.LCM.equals(device.getDeviceType())
				&& LCMTypeEnum.FLEXNET_LCM.equals(((LCM)device).getLcmTypeEnum());
	}

	/**
	 * Checks if is device connected.
	 * 
	 * @param device the device
	 * @return true, if is device connected
	 */
	public boolean isDeviceConnected(Device device)
	{
		BigInteger value = null;

		switch (device.getDeviceType())
		{
			case LCM:
				value = ((LCM)device).getElectricMeterFlexNetId();
				break;

			case HAN_DEVICE:
				value = ((HanDevice)device).getElectricMeterFlexNetId();
				break;

			default:
				break;
		}

		return value != null && value.compareTo(BigInteger.ZERO) > 0;

	}

	/**
	 * Checks if is device type.
	 * 
	 * @param device the device
	 * @param deviceTypeEnum the device type enum
	 * @return true, if is device type
	 */
	public boolean isDeviceType(Device device, DeviceTypeEnum deviceTypeEnum)
	{
		return device.getDeviceType().equals(deviceTypeEnum);
	}

	/**
	 * Validate get han connection status.
	 * 
	 * @param process the process
	 * @return true, if successful
	 */
	public boolean validateRniEventId(DMProcess process)
	{
		if (ValidationUtil.isNull(process) || ValidationUtil.isNullOrZero(process.getRniEventId()))
		{
			return false;
		}

		return true;

	}

	/**
	 * Validate send han text message.
	 * 
	 * @param processItem the process item
	 * @return true, if successful
	 */
	public boolean validateDRGetStatus(ProcessItem processItem)
	{
		if (!isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER)
				&& !isEntekLCM(processItem.getDevice()))
		{
			return false;
		}

		if (ValidationUtil.isNullOrEmpty(processItem.getDevice().getDeviceId()))
		{
			return false;
		}

		// only electric meters which are installed can have actions sent to them
		if (DeviceTypeEnum.ELECTRIC_METER.equals(processItem.getDevice().getDeviceType())
				&& !ElectricMeterLifecycleStateEnum.INSTALLED.equals(((ElectricMeter)processItem.getDevice())
						.getLifecycleStateEnum()))
		{
			return false;
		}

		return true;
	}

	/**
	 * Validate delete meter.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateDeleteMeter(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		if (ValidationUtil.isNullOrEmpty(processItem.getDevice().getDeviceId()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(MISSING_METER_ID);
			updatedItemList.add(processItem);
			return false;
		}
		return true;
	}

	/**
	 * Validate electric meter.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateElectricMeter(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		if (!isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_TARGET);
			updatedItemList.add(processItem);
			return false;
		}

		// only electric meters which are installed can have actions sent to them
		if (DeviceTypeEnum.ELECTRIC_METER.equals(processItem.getDevice().getDeviceType())
				&& !ElectricMeterLifecycleStateEnum.INSTALLED.equals(((ElectricMeter)processItem.getDevice())
						.getLifecycleStateEnum()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(UNINSTALLED_ELECTRIC_METER);
			updatedItemList.add(processItem);
			return false;
		}
		return true;
	}

	/**
	 * Validate device demand response.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateDeviceDemandResponse(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// demand response is only allowed for meters or Entek LCMs
		if (!isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER)
				&& !isEntekLCM(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_TARGET);
			updatedItemList.add(processItem);
			return false;
		}

		if (ValidationUtil.isNullOrEmpty(processItem.getDevice().getDeviceId()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(MISSING_METER_ID);
			updatedItemList.add(processItem);
			return false;
		}

		// only electric meters which are installed can have actions sent to them
		if (DeviceTypeEnum.ELECTRIC_METER.equals(processItem.getDevice().getDeviceType())
				&& !ElectricMeterLifecycleStateEnum.INSTALLED.equals(((ElectricMeter)processItem.getDevice())
						.getLifecycleStateEnum()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(UNINSTALLED_ELECTRIC_METER);
			updatedItemList.add(processItem);
			return false;
		}

		return true;
	}

	/**
	 * Validate dr setup.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateDRSetup(ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// demand response setup is only allowed for Han Devices and Entek LCMs
		if (isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_FOR_ELECTRIC_METERS);
			updatedItemList.add(processItem);
			return false;
		}

		if (ValidationUtil.isNullOrEmpty(processItem.getDevice().getDeviceId()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(MISSING_METER_ID);
			updatedItemList.add(processItem);
			return false;
		}

		if (!isDeviceConnected(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_FOR_DISCONNECTED_HAN_DEVICES);
			updatedItemList.add(processItem);
			return false;
		}

		return true;
	}

	/**
	 * Validate remote action.
	 * 
	 * @param process the process
	 * @param updatedItemList the updated item list
	 * @return the list
	 */
	public List<ProcessItem> validateRemoteAction(DMProcess process, List<ProcessItem> updatedItemList)
	{
		List<ProcessItem> goodList = new ArrayList<ProcessItem>();

		for (ProcessItem processItem : process.getProcessItems())
		{
			if (ValidationUtil.isNullOrEmpty(processItem.getDevice().getDeviceId()))
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
				processItem.setMessage(MISSING_METER_ID);
				updatedItemList.add(processItem);
				continue;
			}

			if (!validateElectricMeter(processItem, updatedItemList))
			{
				continue;
			}

			goodList.add(processItem);
		}

		return goodList;
	}

	/**
	 * Validate demand read ping.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateDemandReadPing(ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		if (!isDeviceType(processItem.getDevice(), DeviceTypeEnum.WATER_METER)
				&& !isDeviceType(processItem.getDevice(), DeviceTypeEnum.GAS_METER))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_FOR_ELECTRIC_METERS);
			updatedItemList.add(processItem);
			return false;
		}

		if (ValidationUtil.isNullOrEmpty(processItem.getDevice().getDeviceId()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(MISSING_METER_ID);
			updatedItemList.add(processItem);
			return false;
		}

		return true;
	}

	/**
	 * Validate delete han device.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateDeleteHanDevice(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// do not validate if it is a meter
		if (isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_ACTION);
			updatedItemList.add(processItem);
			return false;

		}

		return true;
	}

	/**
	 * Validate get demand response setup status.
	 * 
	 * @param device the device
	 * @return true, if successful
	 */
	public boolean validateGetDemandResponseSetupStatus(Device device)
	{
		// demand response setup is only allowed for Han Devices and Entek LCMs
		if (isDeviceType(device, DeviceTypeEnum.ELECTRIC_METER)
				|| ValidationUtil.isNullOrEmpty(device.getDeviceId())
				|| !isDeviceConnected(device))
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if is lcm.
	 * 
	 * @param device the device
	 * @return true, if is lcm
	 */
	public boolean isLCM(Device device)
	{
		if (device.getDeviceType().equals(DeviceTypeEnum.LCM))
		{
			return true;
		}

		return false;
	}

	/**
	 * Validate connect han.
	 * 
	 * @param securityToken the security token
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateConnectHan(HanSecurityToken securityToken, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// do not validate if it is a meter or an entek
		if (isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER) || isEntekLCM(processItem.getDevice())
				|| !validateElectricMeterConnect(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_ACTION);
			updatedItemList.add(processItem);
			return false;
		}

		// if we pass a HAN device or LCM that doesn`t have a Meter ID for it to connect to, then quit
		if (isDeviceConnected(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(DISCONNECTED);
			return false;
		}

		// message applied inside validateInstallCode
		if (!validateInstallCode(securityToken, processItem, updatedItemList))
		{
			return false;
		}

		// only electric meters which are installed can have actions sent to them
		if (DeviceTypeEnum.ELECTRIC_METER.equals(processItem.getDevice().getDeviceType())
				&& !ElectricMeterLifecycleStateEnum.INSTALLED.equals(((ElectricMeter)processItem.getDevice())
						.getLifecycleStateEnum()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(UNINSTALLED_ELECTRIC_METER);
			updatedItemList.add(processItem);
			return false;
		}

		return true;
	}

	/**
	 * Validate electric meter connect.
	 * 
	 * @param device the device
	 * @return true, if successful
	 */
	public boolean validateElectricMeterConnect(Device device)
	{
		if (ValidationUtil.isNull(device.getRadio())
				|| ValidationUtil.isNull(device.getRadio().getFirstDevice())
				|| ValidationUtil.isNull(device.getRadio().getFirstDevice().getRadio())
				|| ValidationUtil.isNull(device.getRadio().getFirstDevice().getRadio().getFlexNetId()))
		{
			return false;
		}
		return true;
	}

	/**
	 * Validate disconnect/delete han.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateDisconnectHan(ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{

		// do not validate if it is a meter or an entek
		if (isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER) || isEntekLCM(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_ACTION);
			updatedItemList.add(processItem);
			return false;

		}

		BigInteger electMeterFlexNetId = null;

		if (isLCM(processItem.getDevice()))
		{
			electMeterFlexNetId = ((com.sensus.dm.elec.device.model.LCM)processItem
					.getDevice()).getElectricMeterFlexNetId();
		}
		else
		{
			electMeterFlexNetId = ((com.sensus.dm.elec.device.model.HanDevice)processItem
					.getDevice()).getElectricMeterFlexNetId();
		}

		// do not validate if it is not connected to the meter
		if (ValidationUtil.isNull(electMeterFlexNetId))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
			processItem.setMessage(DISCONNECTED);
			updatedItemList.add(processItem);
			return false;
		}

		return true;
	}

	/**
	 * Validate tamper detect.
	 * 
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	public boolean validateTamperDetect(ProcessItem processItem, List<ProcessItem> updateItemList)
	{
		// do not validate if it is a meter or an entek
		if (!isDeviceType(processItem.getDevice(), DeviceTypeEnum.LCM) || !isEntekLCM(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_ACTION);
			updateItemList.add(processItem);
			return false;
		}
		return true;
	}

	/**
	 * Validate tamper detect.
	 * 
	 * @param processItems the process items
	 * @param updateItemList the update item list
	 * @return the list
	 */
	public List<ProcessItem> validateTamperDetect(List<ProcessItem> processItems, List<ProcessItem> updateItemList)
	{
		List<ProcessItem> goodItems = new ArrayList<ProcessItem>();

		for (ProcessItem processItem : processItems)
		{
			if (!validateTamperDetect(processItem, updateItemList))
			{
				goodItems.add(processItem);
			}
		}

		return goodItems;
	}

	/**
	 * Validate install code.
	 * 
	 * @param securityToken the security token
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean validateInstallCode(HanSecurityToken securityToken, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		try
		{
			securityToken.setInstallCode(Hex.decodeHex(((com.sensus.dm.elec.device.model.HanDevice)processItem
					.getDevice())
					.getConfiguration().getInstallCode().toCharArray()));

		}
		catch (DecoderException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(STR_LOG + EXCEPTION_FOUND
						+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
						+ e.getMessage());
			}

			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(DECODE_FAILED
					+ ((com.sensus.dm.elec.device.model.HanDevice)processItem.getDevice()).getConfiguration()
							.getInstallCode());
			updatedItemList.add(processItem);

			return false;
		}

		return true;
	}

}
