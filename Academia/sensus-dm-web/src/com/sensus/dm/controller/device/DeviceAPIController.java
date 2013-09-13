package com.sensus.dm.controller.device;

import java.math.BigInteger;
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
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMUtil;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.ElectricMeterSearch;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class DeviceAPIController.
 */
@Controller
@RequestMapping("/api/device")
public class DeviceAPIController extends BaseController
{

	/** The Constant ALARM_TIME. */
	private static final String ALARM_TIME = "alarmTime";

	/** The Constant ALARM_ENUM. */
	private static final String ALARM_ENUM = "alarmEnum";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "DeviceAPIController";

	/** The Constant DEVICE_ID. */
	private static final String DEVICE_ID = "DEVICE_ID";

	/** The Constant FETCH_ALARM_HISTORY. */
	private static final String FETCH_ALARM_HISTORY = "/fetchAlarmHistory";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_SEARCH = "/fetchBySearch";

	/** The Constant FLEXNETID. */
	private static final String FLEXNETID = "flexNetId";

	/** The Constant NETWORK_ADDRESS. */
	private static final String NETWORK_ADDRESS = "NETWORK_ADDRESS";

	/** The Constant PREMISE_ID. */
	private static final String PREMISE_ID = "PREMISE_ID";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DeviceAPIController.class);

	/** The device bcf. */
	private IDeviceBCF deviceBCF;

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
	 * Fetch by search.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_SEARCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchBySearch(@RequestBody Map<String, String> request)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{
			DeviceRequest deviceRequest = new DeviceRequest();
			ElectricMeter electricMeter = new ElectricMeter();
			ElectricMeterSearch electricMeterSearch = new ElectricMeterSearch(electricMeter);

			addUserContextToRequest(deviceRequest);

			if (!ValidationUtil.isNullOrEmpty(request.get(DEVICE_ID)))
			{
				electricMeter.setDeviceId(request.get(DEVICE_ID).toString());
			}

			if (!ValidationUtil.isNullOrEmpty(request.get(PREMISE_ID)))
			{
				ElectricMeterConfiguration electricMeterConfiguration = new ElectricMeterConfiguration();
				electricMeterConfiguration.setPremiseId(request.get(PREMISE_ID).toString());
				electricMeter.setConfiguration(electricMeterConfiguration);
			}

			if (!ValidationUtil.isNullOrEmpty(request.get(NETWORK_ADDRESS)))
			{
				// Convert MacAddress to FlexNetId (BigInteger)
				String macAddress = request.get(NETWORK_ADDRESS);

				electricMeter.setRadio(new Radio(DMUtil.convertMacAddress(macAddress)));
			}

			deviceRequest.setDeviceSearch(new DeviceSearch(electricMeterSearch));

			return getDeviceBCF().fetchAllDevicesTypeCount(deviceRequest);

		}
		catch (Exception e)
		{

			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return response;

	}

	/**
	 * Fetch alarm history.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALARM_HISTORY, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchAlarmHistory(@RequestBody Map<String, Object> request)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{

			DeviceRequest deviceRequest = new DeviceRequest();
			List<Device> deviceList = new ArrayList<Device>();
			List<Alarm> alarmList = new ArrayList<Alarm>();
			Date alarmTime = new Date();
			Alarm alarm = new Alarm();

			if (!ValidationUtil.isNull(request.get(ALARM_TIME)))
			{
				alarmTime.setTime(Long.parseLong(request.get(ALARM_TIME).toString()));
				alarm.setAlarmTime(alarmTime);
			}

			if (!ValidationUtil.isNull(request.get(ALARM_ENUM)))
			{
				alarm.setAlarmEnum(AlarmEnum.valueOf(request.get(ALARM_ENUM).toString()));
			}

			alarmList.add(alarm);

			if (!ValidationUtil.isNull(request.get(FLEXNETID)))
			{

				ServiceTypeEnum serviceTypeEnum = ServiceTypeEnum.valueOf(getServiceType());

				if (ServiceTypeEnum.ELECTRIC.equals(serviceTypeEnum))
				{

					LCM lcm = new LCM();
					lcm.setRadio(new Radio(new BigInteger(request.get(FLEXNETID).toString())));
					lcm.setAlarms(alarmList);

					deviceList.add(lcm);

				}
				else if (ServiceTypeEnum.WATER.equals(serviceTypeEnum))
				{

					WaterMeter water = new WaterMeter();
					water.setRadio(new Radio(new BigInteger(request.get(FLEXNETID).toString())));
					water.setAlarms(alarmList);

					deviceList.add(water);

				}
				else if (ServiceTypeEnum.GAS.equals(serviceTypeEnum))
				{

					GasMeter gas = new GasMeter();
					gas.setRadio(new Radio(new BigInteger(request.get(FLEXNETID).toString())));
					gas.setAlarms(alarmList);

					deviceList.add(gas);

				}
			}

			deviceRequest.setDevices(deviceList);
			addUserContextToRequest(deviceRequest);

			response = getDeviceBCF().fetchAlarmHistory(deviceRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}
}
