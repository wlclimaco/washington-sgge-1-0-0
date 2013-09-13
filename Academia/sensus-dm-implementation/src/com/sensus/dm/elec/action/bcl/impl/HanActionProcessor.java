/*
 *
 */
package com.sensus.dm.elec.action.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import com.sensus.bcf.drserver.util.ParameterIdentifier;
import com.sensus.bcf.hanserver.service.HanService;
import com.sensus.bcf.hanserver.util.HanServerConstants;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.messagetypes.dr.LoadControlParameter;
import com.sensus.common.messagetypes.han.HanIHDMessage;
import com.sensus.common.messagetypes.han.HanInterface;
import com.sensus.common.messagetypes.han.HanSecurityToken;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.elec.action.model.ConnectHanDeviceAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.action.model.GetDemandResponseSetupStatusAction;
import com.sensus.dm.elec.action.model.GetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMRelay;

// TODO: Auto-generated Javadoc
/**
 * The Class HanActionProcessor.
 * 
 * @author QATGlobal
 */
public class HanActionProcessor extends DMActionProcessor
{

	/** The Constant TIME_OUT. */
	private static final int TIME_OUT = 255;

	/** The default firmware version. */
	private String defaultFirmwareVersion;

	/** The default zigbee protocol. */
	private String defaultZigbeeProtocol;

	/** The han service. */
	private HanService hanService;

	/**
	 * Gets the han service.
	 * 
	 * @return the han service
	 */
	public HanService getHanService()
	{
		return hanService;
	}

	/**
	 * Sets the han service.
	 * 
	 * @param hanService the new han service
	 */
	public void setHanService(HanService hanService)
	{
		this.hanService = hanService;
	}

	/**
	 * Gets the default firmware version.
	 * 
	 * @return the default firmware version
	 */
	public String getDefaultFirmwareVersion()
	{
		return defaultFirmwareVersion;
	}

	/**
	 * Sets the default firmware version.
	 * 
	 * @param defaultFirmwareVersion the new default firmware version
	 */
	public void setDefaultFirmwareVersion(String defaultFirmwareVersion)
	{
		this.defaultFirmwareVersion = defaultFirmwareVersion;
	}

	/**
	 * Gets the default zigbee protocol.
	 * 
	 * @return the default zigbee protocol
	 */
	public String getDefaultZigbeeProtocol()
	{
		return defaultZigbeeProtocol;
	}

	/**
	 * Sets the default zigbee protocol.
	 * 
	 * @param defaultZigbeeProtocol the new default zigbee protocol
	 */
	public void setDefaultZigbeeProtocol(String defaultZigbeeProtocol)
	{
		this.defaultZigbeeProtocol = defaultZigbeeProtocol;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.impl.DMActionProcessor#getDMAction()
	 */
	@Override
	public DMAction getDMAction()
	{
		return (DMAction)getAction();
	}

	/**
	 * Execute get demand response setup status.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	public boolean executeGetDemandResponseSetupStatus(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updateItemList)
	{
		// get han connection status is allowed for meters
		// and HAN devices, but not Entek LCMs
		if (!getValidator().validateGetDemandResponseSetupStatus(processItem.getDevice()))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ "This device - "
					+ processItem.getDevice().getRadio().getFlexNetId()
					+ " - is invalid for get demand response participation status");
			return false;
		}

		DemandResponseSetupAction setupAction = (DemandResponseSetupAction)getDMAction();

		List<String> errors = null;

		// entek LCM call per Relay
		if (!ValidationUtil.isNullOrEmpty(setupAction.getLcmRelays()))
		{
			for (LCMRelay lcmRelay : setupAction.getLcmRelays())
			{
				printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
						+ CALLING_API
						+ " for Relay " + lcmRelay.getRelay() + processItem.getDevice().getRadio().getFlexNetId()
						+ RNI_CORRELATION_ID + rniEventId);

				errors = executeGetLoadControlParameterLCM(rniEventId, processItem,
						updateItemList, lcmRelay);

				if (!ValidationUtil.isNullOrEmpty(errors))
				{
					break;
				}
			}
		}

		// meter call
		else
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ CALLING_API
					+ " for Device " + processItem.getDevice().getRadio().getFlexNetId()
					+ RNI_CORRELATION_ID + rniEventId);

			errors = executeGetLoadControlParameter(rniEventId, processItem, updateItemList);
		}

		return checkActionResult(processItem, updateItemList, errors, false);

	}

	/**
	 * Execute get load control parameter lcm.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @param lcmRelay the lcm relay
	 * @return the list
	 */
	private List<String> executeGetLoadControlParameterLCM(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList, LCMRelay lcmRelay)
	{
		LoadControlParameter lcParam = new LoadControlParameter();
		lcParam.setRelayId(lcmRelay.getRelay());

		List<String> errors = null;

		if (!ValidationUtil.isNull(lcmRelay.getEnrollmentCode()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.UtilityEnrollmentGroup);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		if (!ValidationUtil.isNull(lcmRelay.getDeviceClass()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.DeviceClass);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		if (!ValidationUtil.isNull(lcmRelay.getStartMinutes()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.StartRandomizationDuration);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		if (!ValidationUtil.isNull(lcmRelay.getEndMinutes()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.StopRandomizationDuration);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		return errors;
	}

	/**
	 * Execute load control parameter setup.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return the list
	 */
	private List<String> executeGetLoadControlParameter(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		GetDemandResponseSetupStatusAction setupAction = (GetDemandResponseSetupStatusAction)getAction();
		LoadControlParameter lcParam = new LoadControlParameter();

		List<String> errors = null;

		if (!ValidationUtil.isNull(setupAction.getEnrollmentCode()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.UtilityEnrollmentGroup);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		if (!ValidationUtil.isNull(setupAction.getStartMinutes()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.StartRandomizationDuration);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		if (!ValidationUtil.isNull(setupAction.getEndMinutes()))
		{
			lcParam.setParameterIdentifier(ParameterIdentifier.StopRandomizationDuration);
			errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);
			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				return errors;
			}
		}

		return errors;
	}

	/**
	 * Execute get load control parameter.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @param lcParam the lc param
	 * @return the list
	 */
	private List<String> executeGetLoadControlParameter(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList, LoadControlParameter lcParam)
	{
		List<String> errors;

		try
		{
			errors =
					getHanService().getLoadControlParameter(
							DMConvertUtil.convertToSensusHanDevice(processItem.getDevice()),
							lcParam.getParameterIdentifier(), rniEventId.toString(), lcParam.getRelayId());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		return errors;
	}

	/**
	 * Execute han network status.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @param lcParam the lc param
	 * @return true, if successful
	 */
	private boolean executeHanNetworkStatus(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList, LoadControlParameter lcParam)
	{

		List<String> errors;

		try
		{
			errors = getHanService().getHanNetworkStatus(DMConvertUtil.convertToHanInterface(processItem.getDevice()),
					rniEventId.toString());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		return checkActionResult(processItem, updatedItemList, errors, false);

	}

	/**
	 * Execute get han connection status.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeGetHanConnectionStatus(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// get han connection status is allowed for meters
		// and HAN devices, but not Entek LCMs
		if (getValidator().isEntekLCM(processItem.getDevice()))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_ACTION);
			updatedItemList.add(processItem);

			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ "This device - "
					+ processItem.getDevice().getRadio().getFlexNetId()
					+ " - is invalid for get han connection status");

			return false;
		}

		LoadControlParameter lcParam = new LoadControlParameter();
		lcParam.setParameterIdentifier(ParameterIdentifier.UtilityEnrollmentGroup);

		if (getValidator().isDeviceType(processItem.getDevice(), DeviceTypeEnum.ELECTRIC_METER))
		{
			return executeHanNetworkStatus(rniEventId, processItem, updatedItemList, lcParam);
		}

		List<String> errors = executeGetLoadControlParameter(rniEventId, processItem, updatedItemList, lcParam);

		return checkActionResult(processItem, updatedItemList, errors, false);

	}

	/**
	 * Execute get tamper detect timeout.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	public boolean executeGetTamperDetectTimeout(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updateItemList)
	{
		if (!getValidator().validateTamperDetect(processItem, updateItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName() + CALLING_API
					+ processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		GetTamperDetectTimerAction action = (GetTamperDetectTimerAction)getAction();

		LoadControlParameter lcParam = new LoadControlParameter();
		lcParam.setParameterIdentifier(ParameterIdentifier.TamperDetectTimeoutThreshold);

		List<String> errors = null;

		// call to RNI per relay
		for (LCMRelay relay : action.getLcmRelays())
		{
			lcParam.setRelayId(relay.getRelay());

			errors = executeGetLoadControlParameter(rniEventId, processItem, updateItemList, lcParam);

			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				break;
			}
		}

		return checkActionResult(processItem, updateItemList, errors, false);

	}

	/**
	 * Execute send han text message.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeSendHanTextMessage(ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// send han text message is allowed for meters
		// and connected IHDs and PCTs
		// but not LCMs or Entek LCMs

		if (!validateSendHanTextMessage(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		List<String> errors;

		try
		{
			HanInterface hanInterface = DMConvertUtil.convertToHanInterface(processItem.getDevice());

			if (!DeviceTypeEnum.ELECTRIC_METER.equals(processItem.getDevice().getDeviceType()))
			{
				hanInterface = DMConvertUtil.convertToHanInterface(processItem.getDevice().getRadio().getFirstDevice());
			}

			errors =
					getHanService().sendText(
							hanInterface,
							createHanIHDMessage(((SendHanTextMessageAction)getAction()).getTextMessage(),
									processItem.getId()),
							processItem.getId().toString());

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		return checkActionResult(processItem, updatedItemList, errors, false);

	}

	/**
	 * Execute connect han device.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeConnectHanDevice(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// connect HAN device is allowed for HAN devices only
		// meters and Entek LCMs cannot be connected
		Device device =
				fetchDevice(processItem.getDevice().getRadio().getFirstDevice().getRadio().getFlexNetId(), processItem
						.getDevice().getRadio().getCustomerId());

		if (ValidationUtil.isNull(device))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.UNREACHABLE);
			processItem.setMessage(INVALID_TARGET);
			updatedItemList.add(processItem);

			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ "Device not found, invalid target."
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ rniEventId);
			return false;
		}

		HanSecurityToken securityToken = new HanSecurityToken();
		securityToken.setTokenType(HanServerConstants.TOKEN_TYPE_ZIGBEE_INSTALL_CODE);

		if (!getValidator().validateConnectHan(securityToken, processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ rniEventId);
			return false;
		}

		List<String> errors;

		try
		{
			ConnectHanDeviceAction connectAction = (ConnectHanDeviceAction)getAction();
			connectAction.setTimeout(TIME_OUT); // This indicates 'wait forever for device to join'.

			errors = getHanService().joinHan(DMConvertUtil.convertToSensusHanDevice(processItem.getDevice())
					, DMConvertUtil.convertToHanInterface(device), securityToken, (float)connectAction.getTimeout(),
					rniEventId.toString());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);

		}

		return checkActionResult(processItem, updatedItemList, errors, false);

	}

	/**
	 * Execute disconnect han device.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDisconnectHanDevice(ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// disconnect HAN device is allowed for HAN devices only
		// meters and Entek LCMs cannot be disconnected

		if (!getValidator().validateDisconnectHan(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ processItem.getId());
			return false;
		}

		List<String> errors;
		try
		{
			// call business facade
			errors =
					getHanService().leaveHan(DMConvertUtil.convertToSensusHanDevice(processItem.getDevice()),
							processItem.getId().toString());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		return checkActionResult(processItem, updatedItemList, errors, false);

	}

	/**
	 * Execute import han device action.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeImportHanDeviceAction(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// import HAN device is allowed for HAN devices
		// but not for Entek LCMs or Meters

		if (!validateImportHanDevice(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ rniEventId);
			return false;
		}

		List<String> errors;
		try
		{
			errors =
					getHanService().createHanEndpoint(
							DMConvertUtil.convertToSensusHanDevice(processItem.getDevice()),
							DMConvertUtil.convertToHanDeviceDetail(processItem.getDevice(),
									getDefaultFirmwareVersion(), getDefaultZigbeeProtocol()),
							rniEventId.toString());

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());
			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		return checkActionResult(processItem, updatedItemList, errors, !connectAfterImport(processItem.getDevice()));

	}

	/**
	 * Connect after import.
	 * 
	 * @param device the device
	 * @return true, if successful
	 */
	private boolean connectAfterImport(Device device)
	{
		return !ValidationUtil.isNull(device.getRadio())
				&& !ValidationUtil.isNull(device.getRadio().getFirstDevice())
				&& !ValidationUtil.isNull(device.getRadio().getFirstDevice().getRadio())
				&& !ValidationUtil.isNull(device.getRadio().getFirstDevice().getRadio().getFlexNetId());
	}

	/**
	 * Execute delete han device action.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDeleteHanDeviceAction(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// delete HAN device is allowed for HAN devices
		// and Entek LCMs but not for Meters

		if (!getValidator().validateDeleteHanDevice(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ processItem.getId());
			return false;
		}

		List<String> errors;

		try
		{
			// call business facade
			errors = getHanService().deleteHanEndpoint(DMConvertUtil.convertToSensusHanDevice(processItem.getDevice()),
					processItem.getId().toString());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		return checkActionResult(processItem, updatedItemList, errors, true);

	}

	/**
	 * Execute cancel send han text message.
	 * 
	 * @param processItem the process item
	 * @return true, if successful
	 */
	public boolean executeCancelSendHanTextMessage(ProcessItem processItem)
	{

		try
		{
			// call business facade
			List<String> errors =
					getHanService().cancelText(DMConvertUtil.convertToHanInterface(processItem.getDevice()),
							processItem.getId(),
							processItem.getId().toString());

			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
						+ API_CALLED
						+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
						+ processItem.getId() + ". Errors: " + errors.toString());

				return false;
			}

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());
		}

		return true;
	}

	/**
	 * Validate send han text message.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	private boolean validateSendHanTextMessage(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// do not validate LCMs or Entek LCMs
		// only validate Meters or IHDs or PCTs
		if (getValidator().isDeviceType(processItem.getDevice(), DeviceTypeEnum.LCM))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(INVALID_ACTION);
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

		Device device = fetchElectricMeter(processItem.getDevice());

		if (ValidationUtil.isNull(device))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
			processItem.setMessage(INVALID_TARGET + processItem.getDevice().getRadio().getFlexNetId());
			updatedItemList.add(processItem);
			return false;
		}

		processItem.getDevice().getRadio().addDevice(device);

		return true;
	}

	/**
	 * Validate import han device.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	private boolean validateImportHanDevice(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// fetch the device type id according its model and manufacturer
		InternalResultsResponse<Integer> internalResponse =
				getElectricMeterBCL().fetchHanDeviceTypeId(new DeviceRequest(processItem.getDevice()));

		if (internalResponse.isInError())
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
			processItem.setMessage(internalResponse.getMessageInfoList().toString());
			updatedItemList.add(processItem);
			return false;
		}

		if (!internalResponse.hasResults())
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
			processItem.setMessage(INVALID_DEVICE_TYPE);
			updatedItemList.add(processItem);
			return false;
		}

		// it is not allowed to import entek lcm
		if (internalResponse.getFirstResult() == ENTEK_DEVICE_TYPE_ID)
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_TARGET);
			processItem.setMessage(FAILED);
			updatedItemList.add(processItem);
			return false;
		}

		return true;
	}

	/**
	 * Creates the han ihd message.
	 * 
	 * @param message the message
	 * @param id the id
	 * @return the han ihd message
	 */
	private HanIHDMessage createHanIHDMessage(String message, Integer id)
	{
		HanIHDMessage ihdMessage = new HanIHDMessage();
		ihdMessage.setMessageID(id);
		ihdMessage.setMessageText(message);

		if (ValidationUtil.isNull(ihdMessage.isIsComfirmationRequired())
				|| !ihdMessage.isIsComfirmationRequired())
		{
			ihdMessage.setIsComfirmationRequired(HanServerConstants.IHD_MESSAGE_CONFIRMATION_DEFAULT);
		}
		if (ValidationUtil.isNull(ihdMessage.getMessageControlTransmissionMode()))
		{
			ihdMessage.setMessageControlTransmissionMode(HanServerConstants.IHD_MESSAGE_CONTROL_XMIT_MODE_DEFAULT);
		}
		if (ValidationUtil.isNull(ihdMessage.getMessageControlPriority()))
		{
			ihdMessage.setMessageControlPriority(HanServerConstants.IHD_MESSAGE_CONTROL_PRIORITY_DEFAULT);
		}

		ihdMessage.setStartTime(DMConvertUtil.convertDateToInteger(((SendHanTextMessageAction)getAction())
				.getActionTime()));

		ihdMessage.setDuration(((SendHanTextMessageAction)getAction()).getDurationHANTextMessage());

		return ihdMessage;
	}

}
