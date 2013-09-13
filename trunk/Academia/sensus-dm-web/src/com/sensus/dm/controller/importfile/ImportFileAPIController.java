package com.sensus.dm.controller.importfile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.sensus.bcf.hanserver.util.HanServerConstants;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.deviceoperation.model.ActionsModel;
import com.sensus.dm.controller.util.DMUtil;
import com.sensus.dm.elec.action.bcf.IActionBCF;
import com.sensus.dm.elec.action.model.ConnectHanDeviceAction;
import com.sensus.dm.elec.action.model.DisconnectHanDeviceAction;
import com.sensus.dm.elec.action.model.ImportHanDeviceAction;
import com.sensus.dm.elec.action.model.RetryImportHanDeviceAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class ScheduleAPIController.
 */
@Controller
@RequestMapping("api/importFile")
public class ImportFileAPIController extends BaseController
{

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "DeviceOperationAPIController";

	/** The Constant SENSUS_EPM_ACTION_CONNECT_DEVICE. */
	private static final String SENSUS_EPM_ACTION_CONNECT_DEVICE = "sensus.dm.action.connect.to.han";

	/** The Constant SENSUS_EPM_ACTION_CONNECT_HAN_DEVICE. */
	private static final String SENSUS_EPM_ACTION_CONNECT_HAN_DEVICE = "sensus.dm.action.connect.han.device";

	/** The Constant SPECIFIC_DEVICE_TYPE_ENUM_VALUE. */
	private static final String SPECIFIC_DEVICE_TYPE_ENUM_VALUE = "specificDeviceTypeEnumValue";

	/** The Constant ELECTRIC_METER_FLEXNET_ID. */
	private static final String ELECTRIC_METER_FLEXNET_ID = "electricMeterFlexNetId";

	/** The Constant LIFECYCLE_STATE. */
	private static final String LIFECYCLE_STATE = "lifecycleStateEnum";

	/** The Constant DEVICE_TYPE_ENUM. */
	private static final String DEVICE_TYPE_ENUM = "deviceType";

	/** The Constant INVENTORY. */
	private static final String INVENTORY = "INVENTORY";

	/** The Constant INSTALLED. */
	private static final String INSTALLED = "INSTALLED";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customerId";

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "deviceId";

	/** The Constant DEVICES. */
	private static final String DEVICES = "devices";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexNetId";

	/** The Constant ACTION_TYPE_NAME. */
	private static final String ACTION_TYPE_NAME = "actionTypeName";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "fileName";

	/** The Constant HYPHEN. */
	public static final String HYPHEN = "-";

	/** The Constant INSTALL_CODE. */
	private static final String INSTALL_CODE = "installCode";

	/** The Constant IS_MONITORED. */
	private static final String IS_MONITORED = "isMonitored";

	/** The Constant IS_RETRY. */
	private static final String IS_RETRY = "isRetry";

	/** The logger for this class. */
	private static final Logger LOG = LoggerFactory.getLogger(ImportFileAPIController.class);

	/** The Constant MAC_ADDRESS. */
	private static final String MAC_ADDRESS = "macAddress";

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "manufacture";

	/** The Constant MODEL. */
	private static final String MODEL = "model";

	/** The Constant ON_DEMAND. */
	private static final String ON_DEMAND = "onDemand";

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "processId";

	/** The Constant HAN_DEVICE_TYPE. */
	private static final String SUB_DEVICE_TYPE = "subDeviceType";

	/** The Constant UPLOAD_ACTION_ID. */
	private static final String UPLOAD_ACTION_ID = "uploadActionId";

	/** The Constant UPLOAD_FILE. */
	private static final String UPLOAD_FILE = "uploadFile";

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The action bcf. */
	private IActionBCF actionBCF;

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the action bcf.
	 * 
	 * @return the action bcf
	 */
	public IActionBCF getActionBCF()
	{
		return actionBCF;
	}

	/**
	 * Sets the action bcf.
	 * 
	 * @param actionBCF the new action bcf
	 */
	@Resource
	public void setActionBCF(IActionBCF actionBCF)
	{
		this.actionBCF = actionBCF;
	}

	/**
	 * Insert device file.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = "upload/group/insertDeviceFile", method = RequestMethod.POST)
	@ResponseBody
	public Response insertDeviceFile(@RequestBody GroupRequest request)
	{
		GroupResponse response = new GroupResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);

			response = getGroupBCF().insertDeviceFromFileToGroup(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Import han device from action.
	 * 
	 * @param actionsModel the actions model
	 * @return the response
	 */

	@RequestMapping(value = "upload/action/importFile", method = RequestMethod.POST)
	@ResponseBody
	public Response importHanDeviceFromAction(@RequestBody ActionsModel actionsModel)
	{
		ActionRequest actionRequest = new ActionRequest();
		ActionResponse response = new ActionResponse();
		Boolean isAllSelected = false;

		try
		{
			actionRequest.setAction(importHanDevice(actionsModel.getParameters()));

			if (!ValidationUtil.isNull(actionsModel.getParameters().get(IS_RETRY))
					&& !ValidationUtil.isNull(actionsModel.getParameters().get(PROCESS_ID)))
			{
				actionRequest.setProcessId((Integer)actionsModel.getParameters().get(PROCESS_ID));
			}
			if (SENSUS_EPM_ACTION_CONNECT_HAN_DEVICE.equals(actionsModel.getParameters().get(ACTION_TYPE_NAME))
					|| SENSUS_EPM_ACTION_CONNECT_DEVICE.equals(actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.setAction(connectHanDevice(actionsModel.getParameters(), isAllSelected));
			}
			else if (ActionTypeEnum.DISCONNECT_HAN_DEVICE.getActionTypeName().equals(
					actionsModel.getParameters().get(ACTION_TYPE_NAME)))
			{
				actionRequest.setAction(applyDisconnectHanDevice(actionsModel.getParameters(), isAllSelected));
			}

			// ADD user context to request
			addUserContextToRequest(actionRequest);

			actionRequest.setServiceTypeEnum(actionRequest.getServiceTypeEnum());
			response = getActionBCF().applyActionOnDemand(actionRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Import han device.
	 * 
	 * @param request the request
	 * @return the dM action
	 */
	public DMAction importHanDevice(Map<String, Object> request)
	{

		if (!ValidationUtil.isNull(request.get(IS_RETRY)) && !ValidationUtil.isNull(request.get(DEVICES)))
		{
			RetryImportHanDeviceAction action =
					new RetryImportHanDeviceAction((Boolean)request.get(IS_MONITORED), (Boolean)request.get(ON_DEMAND));
			action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
			action.setDevices(createListHanDevice((List<Map<String, Object>>)request.get(DEVICES)));
			return action;
		}

		ImportHanDeviceAction action =
				new ImportHanDeviceAction((Boolean)request.get(IS_MONITORED), (Boolean)request.get(ON_DEMAND));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.addDevice(fillHanDevice(request));
		return action;

	}

	/**
	 * Fetch.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = "fetch", method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody HashMap<String, String> request)
	{
		ScheduleResponse response = new ScheduleResponse();
		try
		{
			ScheduleRequest scheduleRequest = new ScheduleRequest();

			// ADD user context to request
			addUserContextToRequest(scheduleRequest);

			DMSchedule schedule = new DMSchedule();

			schedule.setName(request.get("name"));

			scheduleRequest.setSchedule(schedule);

			response = getScheduleBCF().fetchScheduleByName(scheduleRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Adds the device file to event.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = "upload/insertDeviceFileSchedule", method = RequestMethod.POST)
	@ResponseBody
	public Response addDeviceFileToEvent(@RequestBody Map<String, String> request)
	{
		ActionResponse actionResponse = new ActionResponse();
		try
		{
			ActionRequest actionRequest = new ActionRequest();

			// ADD user context to request
			addUserContextToRequest(actionRequest);

			DMAction action = new DMAction();
			action.setId(Integer.valueOf(request.get(UPLOAD_ACTION_ID)));
			actionRequest.setFileName(request.get(UPLOAD_FILE));

			if (!ValidationUtil.isNullOrEmpty(request.get(UPLOAD_FILE)))
			{
				String[] fileProperty = request.get(UPLOAD_FILE).split(HYPHEN);
				actionRequest.setIdFileType(PropertyEnum.enumForValue(fileProperty[0]));
			}

			if (!ValidationUtil.isNullOrZero(Integer.valueOf(request.get(PROCESS_ID))))
			{
				actionRequest.setProcessId(Integer.valueOf(request.get(PROCESS_ID)));
			}

			if (!ValidationUtil.isNull(request.get("bMonitored")))
			{
				actionRequest.setIsMonitored(true);
			}
			actionRequest.setAction(action);

			actionResponse = getActionBCF().insertDeviceFromFileToAction(actionRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, actionResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return actionResponse;
	}

	/**
	 * Adds the device file to group.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = "upload/insertDeviceFileGroup", method = RequestMethod.POST)
	@ResponseBody
	public Response addDeviceFileToGroup(@RequestBody Map<String, String> request)
	{
		GroupResponse groupResponse = new GroupResponse();

		try
		{
			GroupRequest groupRequest = new GroupRequest();

			// ADD user context to request
			addUserContextToRequest(groupRequest);

			Group group = new Group(Integer.parseInt(request.get(UPLOAD_ACTION_ID)));
			groupRequest.setFileName(request.get(UPLOAD_FILE));

			groupRequest.setProcessId(Integer.parseInt(request.get(PROCESS_ID)));

			if (!ValidationUtil.isNullOrEmpty(request.get(UPLOAD_FILE)))
			{
				String[] fileProperty = request.get(UPLOAD_FILE).split(HYPHEN);
				groupRequest.setIdFileType(PropertyEnum.enumForValue(fileProperty[0]));
			}

			String deviceType = request.get("deviceType");

			if (!ValidationUtil.isNullOrEmpty(deviceType))
			{
				if (HanDeviceTypeEnum.THERMOSTAT.toString().equals(deviceType)
						|| HanDeviceTypeEnum.IHD.toString().equals(deviceType))
				{
					group.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
					group.setHanDeviceType(HanDeviceTypeEnum.valueOf(deviceType));
				}
			}

			groupRequest.addGroup(group);

			groupResponse = getGroupBCF().insertDeviceFromFileToGroup(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return groupResponse;
	}

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
	 * Fill han device.
	 * 
	 * @param request the request
	 * @return the han device
	 */
	private HanDevice fillHanDevice(Map<String, Object> request)
	{
		HanDevice device = new HanDevice();
		Radio radio = new Radio();
		DeviceModel deviceModel = new DeviceModel();

		device.setConfiguration(new HanDeviceConfiguration((String)request.get(INSTALL_CODE)));

		if (!ValidationUtil.isNullOrEmpty((String)request.get(MAC_ADDRESS)))
		{
			radio.setFlexNetId(DMUtil.convertMacAddress((String)request.get(MAC_ADDRESS)));
		}

		if (!ValidationUtil.isNull(request.get(DEVICES)))
		{
			List<Map<String, Object>> mapDevice = (List<Map<String, Object>>)request.get(DEVICES);

			List<Device> devices = new ArrayList<Device>();
			devices.add(new Device(new BigInteger((String)mapDevice.get(0).get(FLEXNET_ID)), null));

			radio.setDevices(devices);
		}

		if (!ValidationUtil.isNull(request.get(FLEXNET_ID)))
		{
			radio.setFlexNetId(new BigInteger((String)request.get(FLEXNET_ID)));
		}

		if (!ValidationUtil.isNullOrEmpty((String)request.get(MANUFACTURE)))
		{
			deviceModel.setManufacture((String)request.get(MANUFACTURE));
		}

		if (!ValidationUtil.isNullOrEmpty((String)request.get(MODEL)))
		{
			deviceModel.setDescription((String)request.get(MODEL));
		}

		if (!ValidationUtil.isNullOrEmpty((String)request.get(DEVICE_ID)))
		{
			device.setHanDeviceId((String)request.get(DEVICE_ID));
			device.setDeviceId((String)request.get(DEVICE_ID));
		}

		if (!ValidationUtil.isNullOrEmpty((String)request.get(CUSTOMER_ID)))
		{
			radio.setCustomerId((String)request.get(CUSTOMER_ID));
		}

		if (!ValidationUtil.isNull(request.get(SUB_DEVICE_TYPE)))
		{
			if (String.valueOf(HanDeviceTypeEnum.IHD).equals(request.get(SUB_DEVICE_TYPE))
					|| String.valueOf(HanDeviceTypeEnum.THERMOSTAT).equals(request.get(SUB_DEVICE_TYPE)))
			{
				device.setHanDeviceTypeEnum(HanDeviceTypeEnum.valueOf((String)request.get(SUB_DEVICE_TYPE)));
			}
		}

		device.setDeviceModel(deviceModel);
		device.setRadio(radio);

		return device;
	}

	/**
	 * Connect han device.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 */
	public DMAction connectHanDevice(Map<String, Object> request, Boolean isAllSelected)
	{
		ConnectHanDeviceAction action =
				new ConnectHanDeviceAction((Boolean)request.get(IS_MONITORED), (Boolean)request.get(ON_DEMAND));
		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setSecurityTokenType(HanServerConstants.TOKEN_TYPE_ZIGBEE_INSTALL_CODE);
		action.addDevice(fillHanDevice(request));

		return action;
	}

	/**
	 * Apply disconnect han device.
	 * 
	 * @param request the request
	 * @param isAllSelected the is all selected
	 * @return the ePM action
	 * @throws Exception the exception
	 */
	public DMAction applyDisconnectHanDevice(Map<String, Object> request, Boolean isAllSelected) throws Exception
	{
		DisconnectHanDeviceAction action =
				new DisconnectHanDeviceAction((Boolean)request.get(ON_DEMAND), (Boolean)request.get(IS_MONITORED));

		action.setActionTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		action.setDevices(createListDevice((List<Map<String, Object>>)request.get(DEVICES), isAllSelected));

		return action;
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

}