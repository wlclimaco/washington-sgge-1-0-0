package com.sensus.dm.controller.deviceoperation;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.deviceoperation.model.ActionsModel;
import com.sensus.dm.controller.model.UserSettings;
import com.sensus.dm.elec.action.model.DeleteHanDeviceAction;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.action.model.GetDemandResponseEventStatusAction;
import com.sensus.dm.elec.action.model.GetDemandResponseSetupStatusAction;
import com.sensus.dm.elec.action.model.GetHanConnectionStatusAction;
import com.sensus.dm.elec.action.model.GetRemoteConnectStatusAction;
import com.sensus.dm.elec.action.model.GetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.RemoteArmConnectAction;
import com.sensus.dm.elec.action.model.RemoteConnectAction;
import com.sensus.dm.elec.action.model.RemoteDisconnectAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;
import com.sensus.dm.elec.action.model.SetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;
import com.sensus.dm.elec.device.bcf.IDeviceReadingBCF;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.DeviceClassEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryIntervalReadResponse;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;
import com.sensus.dm.elec.device.model.response.TOUReadResponse;
import com.sensus.dm.water.action.model.DemandReadAction;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class DeviceOperationAPIController.
 */
@Controller
@RequestMapping("/api/deviceop")
@SuppressWarnings("unchecked")
public class DeviceOperationAPIController extends BaseController
{

	/** The Constant LCM_FLEX_NET. */
	private static final String LCM_FLEX_NET = "lcmFlexNet";

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant ACTION_ID. */
	private static final String ACTION_ID = "actionId";

	/** The Constant ACTION_TYPE_NAME. */
	private static final String ACTION_TYPE_NAME = "actionTypeName";

	/** The Constant APPLY. */
	private static final String APPLY = "/apply";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "DeviceOperationAPIController";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customerId";

	/** The Constant DATE_FORMAT_EN. */
	private static final String DATE_FORMAT_EN = "MM/dd/yyyy";

	/** The Constant DATE_FORMAT_PT. */
	private static final String DATE_FORMAT_PT = "dd/MM/yyyy";

	/** The Constant DATE_TIME_STRING. */
	private static final String DATE_TIME_STRING = "dateTimeString";

	/** The Constant DEMAND_RESPONSE_DURATION. */
	private static final String DEMAND_RESPONSE_DURATION = "demandResponseDuration";

	/** The Constant DEVICE_CLASS. */
	private static final String DEVICE_CLASS = "deviceClass";

	/** The Constant DEVICE_CLASSES. */
	private static final String DEVICE_CLASSES = "deviceClasses";

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "deviceId";

	/** The Constant DEVICE_TYPE_ENUM. */
	private static final String DEVICE_TYPE_ENUM = "deviceType";

	/** The Constant DEVICES. */
	private static final String DEVICES = "devices";

	/** The Constant END_MINUTES. */
	private static final String END_MINUTES = "endMinutes";

	/** The Constant ELECTRIC_METER_FLEXNET_ID. */
	private static final String ELECTRIC_METER_FLEXNET_ID = "electricMeterFlexNetId";

	/** The Constant ENROLLMENT_CODE. */
	private static final String ENROLLMENT_CODE = "enrollmentCode";

	/** The Constant FETCH_DATA_READ. */
	private static final String FETCH_DATA_READ = "fetchDataRead";

	/** The Constant FETCH_INTERVAL_READ. */
	private static final String FETCH_INTERVAL_READ = "fetchIntervalRead";

	/** The Constant FETCH_METER_DEMAND_READS. */
	private static final String FETCH_METER_DEMAND_READS = "fetchMeterDemandReads";

	/** The Constant FETCH_METER_LOAD_PROFILE. */
	private static final String FETCH_METER_LOAD_PROFILE = "fetchMeterLoadProfile";

	/** The Constant FETCH_SNAPSHOT. */
	private static final String FETCH_SNAPSHOT = "fetchSnapshot";

	/** The Constant FETCH_TOU_READ. */
	private static final String FETCH_TOU_READ = "fetchTouRead";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexNetId";

	/** The Constant INVENTORY. */
	private static final String INVENTORY = "INVENTORY";

	/** The Constant INSTALLED. */
	private static final String INSTALLED = "INSTALLED";

	/** The Constant IS_MONITORED. */
	private static final String IS_MONITORED = "isMonitored";

	/** The Constant LCM_RELAY_LIST. */
	private static final String LCM_RELAY_LIST = "lcmRelaysList";

	/** The Constant LIFECYCLE_STATE. */
	private static final String LIFECYCLE_STATE = "lifecycleStateEnum";

	/** The Constant ON_DEMAND. */
	private static final String ON_DEMAND = "onDemand";

	/** The Constant RELAY. */
	private static final String RELAY = "relay";

	/** The Constant SPECIFIC_DEVICE_TYPE_ENUM_VALUE. */
	private static final String SPECIFIC_DEVICE_TYPE_ENUM_VALUE = "specificDeviceTypeEnumValue";

	/** The Constant SENSUS_EPM_GET_HAN_CONNECTION_STATUS. */
	private static final String SENSUS_EPM_GET_HAN_CONNECTION_STATUS = "sensus.dm.action.get.connection.status";

	/** The Constant START_MINUTES. */
	private static final String START_MINUTES = "startMinutes";

	/** The Constant TAMPER_DETECT_TIMEOUT. */
	private static final String TAMPER_DETECT_TIMEOUT = "tamperDetectTimeout";

	/** The Constant TIME_FORMAT_EN. */
	private static final String TIME_FORMAT_EN = " hh:mma";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DeviceOperationAPIController.class);

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The scheduele bcf. */
	private IScheduleBCF scheduleBCF;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/** The device reading bcf. */
	private IDeviceReadingBCF deviceReadingBCF;

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/**
	 * Gets the electric meter bcf.
	 * 
	 * @return the electricMeterBCF
	 */
	public IElectricMeterBCF getElectricMeterBCF()
	{
		return electricMeterBCF;
	}

	/**
	 * Sets the electric meter bcf.
	 * 
	 * @param electricMeterBCF the electricMeterBCF to set
	 */
	@Resource
	public void setElectricMeterBCF(IElectricMeterBCF electricMeterBCF)
	{
		this.electricMeterBCF = electricMeterBCF;
	}

	/**
	 * Gets the device reading bcf.
	 * 
	 * @return the device reading bcf
	 */
	public IDeviceReadingBCF getDeviceReadingBCF()
	{
		return deviceReadingBCF;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	@Resource
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Sets the device reading bcf.
	 * 
	 * @param deviceReadingBCF the new device reading bcf
	 */
	@Resource
	public void setDeviceReadingBCF(IDeviceReadingBCF deviceReadingBCF)
	{
		this.deviceReadingBCF = deviceReadingBCF;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the locale date format.
	 * 
	 * @param dateFormat the date format
	 * @return the locale date format
	 */
	private DateFormat getLocaleDateFormat(String dateFormat)
	{
		if (dateFormat.equalsIgnoreCase(DATE_FORMAT_EN))
		{
			return new SimpleDateFormat(DATE_FORMAT_EN + TIME_FORMAT_EN);
		}
		else if (dateFormat.equalsIgnoreCase(DATE_FORMAT_PT))
		{
			return new SimpleDateFormat(DATE_FORMAT_PT + TIME_FORMAT_EN);
		}

		return null;
	}

	/**
	 * apply the devices.
	 * 
	 * @param actionsModel the actions model
	 * @return the ActionResponse
	 */
	@RequestMapping(value = APPLY, method = RequestMethod.POST)
	@ResponseBody
	public Response apply(@RequestBody ActionsModel actionsModel)
	{
		InquiryActionRequest actionRequest = new InquiryActionRequest();
		ScheduleResponse response = new ScheduleResponse();
		Boolean isAllSelected = false;

		try
		{
			// Set Device Search;
			if (!ValidationUtil.isNull(actionsModel.getDeviceSearch()))
			{
				actionRequest.setDeviceSearch(actionsModel.getDeviceSearch());
			}

			// Set Pagination All Selected
			if (!ValidationUtil.isNull(actionsModel.getPaginationAllSelected()))
			{
				isAllSelected = true;
				actionRequest.setPaginationAllSelected(actionsModel.getPaginationAllSelected());
			}

			// Set pagination Ids
			if (!ValidationUtil.isNullOrEmpty(actionsModel.getSelectionPaginationIds()))
			{
				actionRequest.setSelectionPaginationIds(actionsModel.getSelectionPaginationIds());
			}

			// Set Action
			if (ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyDemandResponse(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applySetupDemandResponse(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.SEND_HAN_TEXT_MESSAGE.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyHanText(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.REMOTE_CONNECT.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(remoteConnect(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.REMOTE_DISCONNECT.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(remoteDisconnect(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.REMOTE_ARM_CONNECT.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(remoteArmConnect(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.DELETE_HAN_DEVICE.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(deleteHan(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.DEMAND_READ.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME))
					|| ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName().equals(
							actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyDemandResetAndRead(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyGetDemandResponseEvent(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.GET_DEMAND_RESPONSE_SETUP_STATUS.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyGetDemandResponseSetup(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.GET_REMOTE_CONNECT_STATUS.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME))
					|| ActionTypeEnum.GET_HAN_CONNECTION_STATUS.getActionTypeName().equals(
							actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyGetConnectStatus(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.GET_TAMPER_DETECT_TIMER.getActionTypeName()
					.equals(actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applyGetTamperDetectTimeout(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.SET_TAMPER_DETECT_TIMER.getActionTypeName()
					.equals(actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.addAction(applySetTamperDetectTimeout(actionsModel.getParameters(), isAllSelected));
			}

			// ADD user context to request
			addUserContextToRequest(actionRequest);
			response = getScheduleBCF().insertScheduleOnDemand(actionRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Remote connect.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 */
	private DMAction remoteConnect(Map<String, Object> request, Boolean isAllSelected)
	{
		DMAction action =
				new RemoteConnectAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED),
						DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Remote disconnect.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 */
	private DMAction remoteDisconnect(Map<String, Object> request, Boolean isAllSelected)
	{
		DMAction action =
				new RemoteDisconnectAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED),
						DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Remote arm connect.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 */
	private DMAction remoteArmConnect(Map<String, Object> request, Boolean isAllSelected)
	{
		DMAction action =
				new RemoteArmConnectAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED),
						DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Delete han.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 */
	private DMAction deleteHan(Map<String, Object> request, Boolean isAllSelected)
	{
		DMAction action = new DeleteHanDeviceAction((Boolean)request.get(IS_MONITORED), Boolean.TRUE);

		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Apply demand read.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 * @throws Exception the exception
	 */
	public DMAction applyDemandResetAndRead(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		DMAction action = null;

		// If invalid action return null
		if (!ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName().equals(request.get(ACTION_TYPE_NAME))
				&& !ActionTypeEnum.DEMAND_READ.getActionTypeName().equals(request.get(ACTION_TYPE_NAME)))
		{
			return action;
		}

		// Demand Reset
		if (ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName().equals(request.get(ACTION_TYPE_NAME)))
		{
			action = new DemandResetEventAction();
		}

		// Demand Response
		if (ActionTypeEnum.DEMAND_READ.getActionTypeName().equals(request.get(ACTION_TYPE_NAME)))
		{
			action = new DemandReadAction();
		}

		action.setOnDemand((Boolean)request.get(ON_DEMAND));
		action.setIsMonitored((Boolean)request.get(IS_MONITORED));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Apply get remote connect status.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 * @throws Exception the exception
	 */
	public DMAction applyGetConnectStatus(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{

		DMAction action = null;

		if (ActionTypeEnum.GET_REMOTE_CONNECT_STATUS.getActionTypeName().equals(request.get(ACTION_TYPE_NAME)))
		{
			action = new GetRemoteConnectStatusAction();
		}

		if (SENSUS_EPM_GET_HAN_CONNECTION_STATUS.equals(request.get(ACTION_TYPE_NAME)))
		{
			action = new GetHanConnectionStatusAction();
		}

		action.setOnDemand((Boolean)request.get(ON_DEMAND));
		action.setIsMonitored((Boolean)request.get(IS_MONITORED));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Fetch meter load profile.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_METER_LOAD_PROFILE, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchMeterLoadProfile(@RequestBody DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);
			response = getDeviceReadingBCF().fetchAllLoadProfileRead(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch meter demand reads.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_METER_DEMAND_READS, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchMeterDemandReads(@RequestBody DeviceReadingRequest request)
	{
		InquiryPeakDemandResponse response = new InquiryPeakDemandResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);
			response = getElectricMeterBCF().fetchAllPeakDemand(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch interval read.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_INTERVAL_READ, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchIntervalRead(@RequestBody DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);
			response = getDeviceReadingBCF().fetchAllIntervalRead(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch data read.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_DATA_READ, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchDataRead(@RequestBody DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);
			response = getDeviceReadingBCF().fetchAllWaterGasDataRead(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch snapshot.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_SNAPSHOT, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchSnapshot(@RequestBody DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);
			response = getDeviceReadingBCF().fetchAllSnapshots(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch tou.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_TOU_READ, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchTou(@RequestBody DeviceReadingRequest request)
	{
		TOUReadResponse response = new TOUReadResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);
			response = getDeviceReadingBCF().fetchAllTOURead(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Creates the device by type.
	 * 
	 * @param deviceTypeEnum the device type enum
	 * @param specificDeviceType the specific device type
	 * @param electricMeterFlexNetId the electric meter flex net id
	 * @return the device
	 */
	public Device createDeviceByType(DeviceTypeEnum deviceTypeEnum, Integer specificDeviceType,
			String electricMeterFlexNetId)
	{
		switch (deviceTypeEnum)
		{
			case ELECTRIC_METER:
				ElectricMeter electricMeter = new ElectricMeter();
				electricMeter.setElectricMeterTypeEnumValue(specificDeviceType);
				return electricMeter;

			case HAN_DEVICE:
				HanDevice hanDevice = new HanDevice();
				hanDevice.setHanDeviceTypeEnumValue(specificDeviceType);

				if (!ValidationUtil.isNullOrEmpty(electricMeterFlexNetId))
				{
					hanDevice.setElectricMeterFlexNetId(new BigInteger(electricMeterFlexNetId));
				}
				return hanDevice;

			case LCM:
				LCM lcm = new LCM();
				lcm.setLcmTypeEnumValue(specificDeviceType);

				if (!ValidationUtil.isNullOrEmpty(electricMeterFlexNetId))
				{
					lcm.setElectricMeterFlexNetId(new BigInteger(electricMeterFlexNetId));
				}
				return lcm;

			case WATER_METER:
				WaterMeter waterMeter = new WaterMeter();
				waterMeter.setWaterMeterTypeEnumValue(specificDeviceType);
				return waterMeter;

			default:
				return new Device();
		}
	}

	/**
	 * Creates the list device.
	 * 
	 * @param mapDevices the map devices
	 * @param allSelected the all selected
	 * @return the list
	 */
	public List<Device> createListDevice(List<Map<String, Object>> mapDevices, Boolean allSelected)
	{
		if (allSelected)
		{
			return null;
		}

		ArrayList<Device> devices = new ArrayList<Device>();
		Device device;
		DeviceTypeEnum deviceTypeEnum;
		String lifecycle_state;

		for (Integer i = ZERO; i < mapDevices.size(); i++)
		{
			deviceTypeEnum = DeviceTypeEnum.valueOf((String)mapDevices.get(i).get(DEVICE_TYPE_ENUM));

			device =
					createDeviceByType(deviceTypeEnum, (Integer)mapDevices.get(i).get(SPECIFIC_DEVICE_TYPE_ENUM_VALUE),
							(String)mapDevices.get(i).get(ELECTRIC_METER_FLEXNET_ID));

			device.setDeviceType(deviceTypeEnum);

			if (DeviceTypeEnum.ELECTRIC_METER.equals(device.getDeviceType()))
			{

				if (ValidationUtil.isNullOrEmpty((String)mapDevices.get(i).get(LIFECYCLE_STATE)))
				{
					lifecycle_state = "";
				}
				else
				{
					lifecycle_state = (String)mapDevices.get(i).get(LIFECYCLE_STATE);
				}
				if (lifecycle_state.toUpperCase().equals(INSTALLED))
				{
					((ElectricMeter)device).setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INSTALLED);
				}
				else if (lifecycle_state.toUpperCase().equals(INVENTORY))
				{
					((ElectricMeter)device).setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.INVENTORY);
				}
				else
				{
					((ElectricMeter)device).setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.NOT_ASSIGNED);
				}

			}

			device.setDeviceId((String)mapDevices.get(i).get(DEVICE_ID));
			device.setRadio(new Radio(new BigInteger((String)mapDevices.get(i).get(FLEXNET_ID)), (String)mapDevices
					.get(i).get(CUSTOMER_ID)));

			devices.add(device);
		}

		return devices;
	}

	/**
	 * Apply demand response.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the string
	 * @throws Exception the exception
	 */
	public DMAction applyDemandResponse(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		DemandResponseEventAction demandResponse = new DemandResponseEventAction();
		demandResponse.setOnDemand((Boolean)request.get(ON_DEMAND));
		demandResponse.setIsMonitored((Boolean)request.get(IS_MONITORED));

		UserSettings userSettings = getUserSettings();

		if (!ValidationUtil.isNullOrEmpty((String)request.get(DATE_TIME_STRING)))
		{
			DateFormat dateFormat = getLocaleDateFormat(userSettings.getDateFormatMask());
			String defineEventWhen = (String)request.get(DATE_TIME_STRING);
			Date dateJava = dateFormat.parse(defineEventWhen);
			demandResponse.setActionTime(dateJava);
		}

		List<String> classes = (ArrayList<String>)request.get(DEVICE_CLASSES);

		if (!ValidationUtil.isNullOrEmpty(classes))
		{
			demandResponse.setDeviceClasses(classes);
		}

		demandResponse.setDemandResponseDuration((Integer)request.get(DEMAND_RESPONSE_DURATION));
		demandResponse.setEnrollmentCode(Integer.valueOf((String)request.get(ENROLLMENT_CODE)));
		demandResponse.setHeating((Integer)request.get("heating"));
		demandResponse.setCooling((Integer)request.get("cooling"));
		demandResponse.setDutyCycleRate((Integer)request.get("dutyCycleRate"));
		demandResponse.setLoadAdjustment((Integer)request.get("loadAdjustment"));
		demandResponse.setCriticalityLevel((Integer)request.get("criticalityLevel"));
		demandResponse.setRandomizeStart((Boolean)request.get("randomizeStart"));
		demandResponse.setRandomizeEnd((Boolean)request.get("randomizeEnd"));
		demandResponse.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return demandResponse;
	}

	/**
	 * Apply han text.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the string
	 * @throws Exception the exception
	 */
	public DMAction applyHanText(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		SendHanTextMessageAction sendHan = new SendHanTextMessageAction();

		UserSettings userSettings = getUserSettings();

		if (!ValidationUtil.isNullOrEmpty((String)request.get(DATE_TIME_STRING)))
		{
			DateFormat dateFormat = getLocaleDateFormat(userSettings.getDateFormatMask());
			String defineEventWhen = (String)request.get(DATE_TIME_STRING);
			Date dateJava = dateFormat.parse(defineEventWhen);
			sendHan.setActionTime(dateJava);
		}

		sendHan.setTextMessage((String)request.get("textMessage"));
		sendHan.setDurationHANTextMessage((Integer)request.get("durationHANTextMessage"));
		sendHan.setOnDemand(Boolean.TRUE);
		sendHan.setIsMonitored((Boolean)request.get(IS_MONITORED));

		sendHan.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));
		DMAction action = sendHan;

		return action;
	}

	/**
	 * Apply setup demand response.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the string
	 * @throws Exception the exception
	 */
	public DMAction applySetupDemandResponse(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		DemandResponseSetupAction setupDemandResponseAction =
				new DemandResponseSetupAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED));

		setupDemandResponseAction.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES),
				isAllSelected));
		setupDemandResponseAction.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));

		if (!ValidationUtil.isNullOrEmpty((List<Map<String, Object>>)request.get(LCM_RELAY_LIST)))
		{
			setupDemandResponseAction.setLcmRelays(createListLCMRelay((List<Map<String, Object>>)request
					.get(LCM_RELAY_LIST)));
			return setupDemandResponseAction;
		}

		setupDemandResponseAction.setEnrollmentCode(Integer.valueOf((String)request.get(ENROLLMENT_CODE)));
		setupDemandResponseAction.setStartMinutes((Integer)request.get(START_MINUTES));
		setupDemandResponseAction.setEndMinutes((Integer)request.get(END_MINUTES));

		return setupDemandResponseAction;
	}

	/**
	 * Apply get demand response event.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 * @throws Exception the exception
	 */
	public DMAction applyGetDemandResponseEvent(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		GetDemandResponseEventStatusAction action =
				new GetDemandResponseEventStatusAction((Boolean)request.get(IS_MONITORED),
						(Boolean)request.get(ON_DEMAND));

		action.setProcessId((Integer)request.get(ACTION_ID));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	/**
	 * Apply get demand response setup.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the dM action
	 * @throws Exception the exception
	 */
	public DMAction applyGetDemandResponseSetup(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		GetDemandResponseSetupStatusAction action =
				new GetDemandResponseSetupStatusAction((Boolean)request.get(IS_MONITORED),
						(Boolean)request.get(ON_DEMAND));

		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));
		if ((Boolean)request.get(LCM_FLEX_NET))
		{
			List<Map<String, Object>> lcmRelays = (List<Map<String, Object>>)request.get(LCM_RELAY_LIST);

			if (!ValidationUtil.isNull(lcmRelays.get(0).get(ENROLLMENT_CODE)))
			{
				action.setEnrollmentCode(ONE);
			}

			if (!ValidationUtil.isNull(lcmRelays.get(0).get(START_MINUTES)))
			{
				action.setStartMinutes(ONE);
			}

			if (!ValidationUtil.isNull(lcmRelays.get(0).get(END_MINUTES)))
			{
				action.setEndMinutes(ONE);
			}
		}
		else
		{
			action.setLcmRelays(createListLCMRelay((List<Map<String, Object>>)request.get(LCM_RELAY_LIST)));
		}

		return action;
	}

	/**
	 * Apply set tamper detect timeout.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the dM action
	 * @throws Exception the exception
	 */
	public DMAction applySetTamperDetectTimeout(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		SetTamperDetectTimerAction action =
				new SetTamperDetectTimerAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED));

		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));
		action.setLcmRelays(createListLCMRelay((List<Map<String, Object>>)request.get(LCM_RELAY_LIST)));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));

		return action;
	}

	/**
	 * Apply get tamper detect timeout.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the dM action
	 * @throws Exception the exception
	 */
	public DMAction applyGetTamperDetectTimeout(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		GetTamperDetectTimerAction action =
				new GetTamperDetectTimerAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED));

		action.setLcmRelays(createListLCMRelay((List<Map<String, Object>>)request
				.get(LCM_RELAY_LIST)));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
	}

	// /**
	// * Fill han device.
	// *
	// * @param request the request
	// * @return the han device
	// */
	// private HanDevice fillHanDevice(Map<String, Object> request)
	// {
	// HanDevice device = new HanDevice();
	// Radio radio = new Radio();
	// DeviceModel deviceModel = new DeviceModel();
	//
	// device.setConfiguration(new HanDeviceConfiguration((String)request.get(INSTALL_CODE)));
	//
	// if (!ValidationUtil.isNullOrEmpty((String)request.get(MAC_ADDRESS)))
	// {
	// radio.setFlexNetId(DMUtil.convertMacAddress((String)request.get(MAC_ADDRESS)));
	// }
	//
	// if (!ValidationUtil.isNull(request.get(DEVICES)))
	// {
	// List<Map<String, Object>> mapDevice = (List<Map<String, Object>>)request.get(DEVICES);
	//
	// List<Device> devices = new ArrayList<Device>();
	// devices.add(new Device(new BigInteger((String)mapDevice.get(0).get(FLEXNET_ID)), null));
	//
	// radio.setDevices(devices);
	// }
	//
	// if (!ValidationUtil.isNull(request.get(FLEXNET_ID)))
	// {
	// radio.setFlexNetId(new BigInteger((String)request.get(FLEXNET_ID)));
	// }
	//
	// if (!ValidationUtil.isNullOrEmpty((String)request.get(MANUFACTURE)))
	// {
	// deviceModel.setManufacture((String)request.get(MANUFACTURE));
	// }
	//
	// if (!ValidationUtil.isNullOrEmpty((String)request.get(MODEL)))
	// {
	// deviceModel.setDescription((String)request.get(MODEL));
	// }
	//
	// if (!ValidationUtil.isNullOrEmpty((String)request.get(DEVICE_ID)))
	// {
	// device.setHanDeviceId((String)request.get(DEVICE_ID));
	// device.setDeviceId((String)request.get(DEVICE_ID));
	// }
	//
	// if (!ValidationUtil.isNullOrEmpty((String)request.get(CUSTOMER_ID)))
	// {
	// radio.setCustomerId((String)request.get(CUSTOMER_ID));
	// }
	//
	// if (!ValidationUtil.isNull(request.get(SUB_DEVICE_TYPE)))
	// {
	// if (String.valueOf(HanDeviceTypeEnum.IHD).equals(request.get(SUB_DEVICE_TYPE))
	// || String.valueOf(HanDeviceTypeEnum.THERMOSTAT).equals(request.get(SUB_DEVICE_TYPE)))
	// {
	// device.setHanDeviceTypeEnum(HanDeviceTypeEnum.valueOf((String)request.get(SUB_DEVICE_TYPE)));
	// }
	// }
	//
	// device.setDeviceModel(deviceModel);
	// device.setRadio(radio);
	//
	// return device;
	// }

	/**
	 * Creates the list han device.
	 * 
	 * @param mapDevices the map devices
	 * @return the list
	 */
	public List<Device> createListHanDevice(List<Map<String, Object>> mapDevices)
	{
		ArrayList<Device> devices = new ArrayList<Device>();
		HanDevice hanDevice;

		for (Integer i = ZERO; i < mapDevices.size(); i++)
		{
			hanDevice = new HanDevice(new Radio(new BigInteger((String)mapDevices.get(i).get(FLEXNET_ID))));
			hanDevice.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
			devices.add(hanDevice);
		}

		return devices;
	}

	/**
	 * Creates the list lcm relay.
	 * 
	 * @param lcmRelays the lcm relays
	 * @return the list
	 */
	private List<LCMRelay> createListLCMRelay(List<Map<String, Object>> lcmRelays)
	{
		List<LCMRelay> lcmRelayList = new ArrayList<LCMRelay>();

		for (Map<String, Object> relay : lcmRelays)
		{
			LCMRelay lcmRelay = new LCMRelay((Integer)relay.get(RELAY));

			if (!ValidationUtil.isNull(relay.get(ENROLLMENT_CODE)))
			{
				lcmRelay.setEnrollmentCode((Integer)relay.get(ENROLLMENT_CODE));
			}

			if (!ValidationUtil.isNull(relay.get(DEVICE_CLASS)))
			{
				lcmRelay.setDeviceClass(DeviceClassEnum.valueOf((String)relay.get(DEVICE_CLASS)));
			}

			if (!ValidationUtil.isNull(relay.get(TAMPER_DETECT_TIMEOUT)))
			{
				lcmRelay.setTamperDetectTimer(relay.get(TAMPER_DETECT_TIMEOUT).toString());
			}

			if (!ValidationUtil.isNull(relay.get(START_MINUTES)))
			{
				lcmRelay.setStartMinutes((Integer)relay.get(START_MINUTES));
			}

			if (!ValidationUtil.isNull(relay.get(END_MINUTES)))
			{
				lcmRelay.setEndMinutes((Integer)relay.get(END_MINUTES));
			}

			lcmRelayList.add(lcmRelay);
		}

		return lcmRelayList;
	}

	/**
	 * Update.
	 * 
	 * @param actionsModel the actions model
	 * @return the response
	 */
	@RequestMapping(value = "checkRunningProcess", method = RequestMethod.POST)
	@ResponseBody
	public Response update(@RequestBody ActionsModel actionsModel)
	{
		Map<String, Object> obj = actionsModel.getParameters();
		List<Map<String, Object>> mapDevice = (List<Map<String, Object>>)obj.get(DEVICES);

		return getProcessBCF().fetchAllProcessItems(
				new ProcessRequest(new DMProcess(new ProcessItem(new Device(new Radio(new BigInteger((String)mapDevice
						.get(0).get(FLEXNET_ID)))), ProcessItemStatusEnum.RUNNING))));
	}
}