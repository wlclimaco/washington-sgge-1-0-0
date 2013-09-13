package com.sensus.dm.controller.electricdevice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.process.bcf.IProcessSummaryBCF;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.controller.util.DMUtil;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMTypeEnum;

/**
 * The Class DeviceAPIController.
 */
@Controller
@RequestMapping("/api/electricdevice")
public class ElectricDeviceAPIController extends BaseController
{

	/** The Constant TYPE_ID. */
	private static final String TYPE_ID = "typeId";

	/** The Constant SEARCH. */
	private static final String SEARCH = "search";

	/** The logger for this class. */
	private static final Logger LOG = LoggerFactory.getLogger(ElectricDeviceAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ElectricDeviceAPIController";

	/** The Constant DEMAND_RESPONSE_PROGRAM_PARTICIPATION. */
	private static final String DEMAND_RESPONSE_PROGRAM_PARTICIPATION = "demandResponseProgramParticipation";

	/** The Constant DEMAND_RESPONSE_SETUP. */
	private static final String DEMAND_RESPONSE_SETUP = "demandResponseSetup";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH = "/fetch";

	/** The Constant FETCH_DEMAND_RESPONSE. */
	private static final String FETCH_DEMAND_RESPONSE = "/fetchDemandResponse";

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "deviceId";

	/** The Constant PREMISE_ID. */
	private static final String PREMISE_ID = "premiseId";

	/** The Constant GROUP_IDS. */
	private static final String GROUP_IDS = "groupIds";

	/** The Constant MAC_ADDRESS. */
	private static final String MAC_ADDRESS = "macAddress";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant DEVICE_TYPE. */
	private static final String DEVICE_TYPE = "deviceType";

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant MANUFACTURE. */
	private static final String MANUFACTURE = "manufacture";

	/** The Constant MODEL. */
	private static final String MODEL = "model";

	/** The group BCF. */
	private IGroupBCF groupBCF;

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/** The device bcf. */
	private IDeviceBCF deviceBCF;

	/** The process summary bcf. */
	private IProcessSummaryBCF processSummaryBCF;

	/**
	 * Gets the process summary bcf.
	 * 
	 * @return the processSummaryBCF
	 */
	public IProcessSummaryBCF getProcessSummaryBCF()
	{
		return processSummaryBCF;
	}

	/**
	 * Sets the process summary bcf.
	 * 
	 * @param processSummaryBCF the processSummaryBCF to set
	 */
	@Resource
	public void setProcessSummaryBCF(IProcessSummaryBCF processSummaryBCF)
	{
		this.processSummaryBCF = processSummaryBCF;
	}

	/**
	 * Gets the electric meter bcf.
	 * 
	 * @return the electric meter bcf
	 */
	public IElectricMeterBCF getElectricMeterBCF()
	{
		return electricMeterBCF;
	}

	/**
	 * Sets the electric meter bcf.
	 * 
	 * @param electricMeterBCF the new electric meter bcf
	 */
	@Resource
	public void setElectricMeterBCF(IElectricMeterBCF electricMeterBCF)
	{
		this.electricMeterBCF = electricMeterBCF;
	}

	/**
	 * Gets the device bcf.
	 * 
	 * @return the device bcf
	 */
	public IDeviceBCF getDeviceBCF()
	{
		return deviceBCF;
	}

	/**
	 * Sets the device bcf.
	 * 
	 * @param deviceBCF the new device bcf
	 */
	@Resource
	public void setDeviceBCF(IDeviceBCF deviceBCF)
	{
		this.deviceBCF = deviceBCF;
	}

	/**
	 * Gets the group BCF.
	 * 
	 * @return the groupBCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group BCF.
	 * 
	 * @param groupBCF the groupBCF to set
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Fetch by id.
	 * 
	 * @param request the device request
	 * @return the response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody HashMap<String, Object> request)
	{

		DeviceResponse deviceResponse = new DeviceResponse();

		try
		{
			/* Methods Using GroupRequest */

			// Fetch Devices By Group
			if (!ValidationUtil.isNull(request.get(GROUP_IDS)))
			{
				GroupRequest groupRequest = new GroupRequest();
				// ADD user context to request
				addUserContextToRequest(groupRequest);

				// Fill GroupRequest with properties came at "request" Map
				fillGroupRequest(request, groupRequest);

				return getGroupBCF().fetchDevicesByGroup(groupRequest);
			}

			/* Methods Using DeviceRequest */

			DeviceRequest deviceRequest = new DeviceRequest();
			// ADD user context to request
			addUserContextToRequest(deviceRequest);

			// Fetch All PremiseIds
			if (!ValidationUtil.isNull(request.get(PREMISE_ID)))
			{
				// Fill GroupRequest with properties came at "request" Map
				fillPremiseIdRequest(request, deviceRequest);

				deviceResponse = getDeviceBCF().fetchAllByPremiseId(deviceRequest);

				// Convert to MacAddress
				DMConvertUtil.convertMacAddress(deviceResponse);

				return deviceResponse;
			}

			// Fetch All Han Devices By Meter
			if (SEARCH.equals(request.get(TYPE)))
			{
				deviceRequest.addDevice(new Device(new Radio(new BigInteger(String.valueOf(request.get(ID))))));

				deviceResponse = getElectricMeterBCF().fetchAllHanDevicesByMeter(deviceRequest);

				// Convert to MacAddress
				DMConvertUtil.convertMacAddress(deviceResponse);

				return deviceResponse;
			}

			// Fetch Manufactures By HAN and LCM types
			if (MANUFACTURE.equals(request.get(TYPE)))
			{
				Integer typeId = Integer.parseInt(request.get(TYPE_ID).toString());

				Device device;

				if (typeId == 1)
				{
					device = new LCM(LCMTypeEnum.enumForValue(typeId));
				}
				else
				{
					device = new HanDevice(HanDeviceTypeEnum.enumForValue(typeId));
				}

				deviceRequest.addDevice(device);

				return getElectricMeterBCF().fetchAllManufactureByDeviceSubType(deviceRequest);
			}

			// Fetch Models By HAN and LCM types
			if (MODEL.equals(request.get(TYPE)))
			{
				DeviceModel deviceModel = new DeviceModel();

				deviceModel.setManufacture(request.get(MANUFACTURE).toString());

				Integer typeId = Integer.parseInt(request.get(TYPE_ID).toString());

				if (typeId == 1)
				{
					LCM lcm = new LCM(LCMTypeEnum.enumForValue(typeId));
					lcm.setDeviceModel(deviceModel);
					deviceRequest.addDevice(lcm);
				}
				else
				{
					HanDevice hanDevice = new HanDevice(HanDeviceTypeEnum.enumForValue(typeId));
					hanDevice.setDeviceModel(deviceModel);
					deviceRequest.addDevice(hanDevice);
				}

				return getElectricMeterBCF().fetchAllModelByDeviceSubType(deviceRequest);
			}

			fillDeviceIdRequest(request, deviceRequest);

			// Fetch All Han Devices By Meter
			if (Boolean.TRUE.equals(request.get("connectHanDevice")))
			{

				if (!ValidationUtil.isNull(deviceRequest.getDevices().get(0).getRadio().getFlexNetId()))
				{
					deviceResponse = getDeviceBCF().fetchDeviceByIdImport(deviceRequest);
				}

				// Convert to MacAddress
				DMConvertUtil.convertMacAddress(deviceResponse);

				return deviceResponse;
			}

			deviceResponse = getElectricMeterBCF().fetchDeviceById(deviceRequest);

			// Convert to MacAddress
			DMConvertUtil.convertMacAddress(deviceResponse);

			// Convert Time zone to minutes and set in the displayMinutes field
			DMConvertUtil.convertTimeZoneToMinutes(deviceResponse.getDevices());

			return deviceResponse;
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, deviceResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return deviceResponse;
	}

	/**
	 * Fetch demand response program participation.
	 * 
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = FETCH_DEMAND_RESPONSE, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchDemandResponse(@RequestBody HashMap<String, String> request)
	{
		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			ProcessRequest processRequest = new ProcessRequest();

			// Add User Context
			addUserContextToRequest(processRequest);

			// Fetch Demand Response Program Participation
			if (DEMAND_RESPONSE_PROGRAM_PARTICIPATION.equals(request.get(TYPE))
					&& !ValidationUtil.isNull(request.get(ID)))
			{
				// Set FlexNetId into macAddress of the HanDevice object
				HanDevice hanDevice = new HanDevice();
				hanDevice.setMacAddress(request.get(ID));

				ProcessItem processItem = new ProcessItem(hanDevice);

				List<DMProcess> processList = new ArrayList<DMProcess>();
				processList.add(new DMProcess(processItem));

				processRequest.setProcessList(processList);

				return getProcessSummaryBCF().fetchDemandResponseProgramParticipation(processRequest);
			}

			// Fetch Demand Response Setup
			if (DEMAND_RESPONSE_SETUP.equals(request.get(TYPE)) && !ValidationUtil.isNull(request.get(ID)))
			{
				// Set FlexNetId into radio of the Device object
				Device device = new Device(new Radio(new BigInteger(request.get(ID).toString())));

				ProcessItem processItem = new ProcessItem(device);

				List<DMProcess> processList = new ArrayList<DMProcess>();
				processList.add(new DMProcess(processItem));

				processRequest.setProcessList(processList);

				return getProcessSummaryBCF().fetchAllDemandResponseSetup(processRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;
	}

	/**
	 * Fill group request.
	 * 
	 * @param request the request
	 * @param groupRequest the group request
	 * @return the group request
	 */
	@SuppressWarnings("unchecked")
	private void fillGroupRequest(HashMap<String, Object> request, GroupRequest groupRequest)
	{

		List<Group> listGroups = new ArrayList<Group>();
		List<Integer> ids = (ArrayList<Integer>)request.get(GROUP_IDS);

		for (Integer id : ids)
		{
			listGroups.add(new Group(id));
		}

		groupRequest.setGroups(listGroups);

	}

	/**
	 * Fill premise id request.
	 * 
	 * @param request the request
	 * @param deviceRequest the request
	 * @return the device request
	 */
	private void fillPremiseIdRequest(HashMap<String, Object> request, DeviceRequest deviceRequest)
	{

		ElectricMeterConfiguration electricMeterConfiguration = new ElectricMeterConfiguration();

		electricMeterConfiguration.setPremiseId(request.get(PREMISE_ID).toString());

		ElectricMeter electricMeter = new ElectricMeter();

		electricMeter.setConfiguration(electricMeterConfiguration);

		deviceRequest.addDevice(electricMeter);

	}

	/**
	 * Fill device id request.
	 * 
	 * @param request the device request
	 * @param deviceRequest the request
	 * @return the device request
	 */
	private void fillDeviceIdRequest(HashMap<String, Object> request, DeviceRequest deviceRequest)
	{
		Device device = null;

		// Create Device with flexNetId and device type
		if (!ValidationUtil.isNull(request.get(ID)))
		{
			device = new Device(new BigInteger(request.get(ID).toString()), readDeviceType(request));
		}
		// Create Device with MAC Address and Device Type
		else if (!ValidationUtil.isNull(request.get(MAC_ADDRESS)))
		{
			device = new Device(DMUtil.convertMacAddress(request.get(MAC_ADDRESS).toString()), readDeviceType(request));
		}
		// Create Device with Device Id and Device Type
		else if (!ValidationUtil.isNull(request.get(DEVICE_ID)))
		{
			device = new Device(request.get(DEVICE_ID).toString(), readDeviceType(request));
		}

		deviceRequest.addDevice(device);

	}

	/**
	 * Read device type.
	 * 
	 * @param request the request
	 * @return the device type ENUM
	 */
	private DeviceTypeEnum readDeviceType(HashMap<String, Object> request)
	{
		return DeviceTypeEnum.valueOf((String)request.get(DEVICE_TYPE));
	}

}
