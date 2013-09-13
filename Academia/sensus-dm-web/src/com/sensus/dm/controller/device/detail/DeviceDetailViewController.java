package com.sensus.dm.controller.device.detail;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.UiModulesPermissions;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.controller.base.BaseModuleController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.device.bcf.IDeviceReadingBCF;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.response.InquiryLoadProfileResponse;
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;

/**
 * The Class DeviceDetailViewController.
 */
@Controller
@RequestMapping({
		"/device/detail",
		"/device/about",
		"/device/intervalReads",
		"/device/loadProfiles",
		"/device/demandReads",
		"/device/readData",
		"/device/communications",
		"/device/snapshot",
		"/device/tou",
		"/device/history"})
public class DeviceDetailViewController extends BaseModuleController
{
	/** The Constant SUMMARY_DATAS. */
	private static final String SUMMARY_DATAS = "summaryDatas";

	/** The Constant TABS. */
	private static final String TABS = "tabs";

	/** The Constant VIEW_DETAIL_MAIN. */
	private static final String VIEW_DETAIL_MAIN = "/device_detail/device_detail_main";

	/** The Constant LOAD. */
	private static final String LOAD = "";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "DetailViewController";

	/** The Constant SERVICE_TYPE. */
	private static final String SERVICE_TYPE = "serviceType";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DeviceDetailViewController.class);

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/** The gas meter bcf. */
	private IGasMeterBCF gasMeterBCF;

	/** The water meter bcf. */
	private IWaterMeterBCF waterMeterBCF;

	/** The device reading bcf. */
	private IDeviceReadingBCF deviceReadingBCF;

	/**
	 * Gets the water meter bcf.
	 * 
	 * @return the water meter bcf
	 */
	public IWaterMeterBCF getWaterMeterBCF()
	{
		return waterMeterBCF;
	}

	/**
	 * Sets the water meter bcf.
	 * 
	 * @param waterMeterBCF the new water meter bcf
	 */
	@Resource
	public void setWaterMeterBCF(IWaterMeterBCF waterMeterBCF)
	{
		this.waterMeterBCF = waterMeterBCF;
	}

	/**
	 * Gets the gas meter bcf.
	 * 
	 * @return the gas meter bcf
	 */
	public IGasMeterBCF getGasMeterBCF()
	{
		return gasMeterBCF;
	}

	/**
	 * Sets the gas meter bcf.
	 * 
	 * @param gasMeterBCF the new gas meter bcf
	 */
	@Resource
	public void setGasMeterBCF(IGasMeterBCF gasMeterBCF)
	{
		this.gasMeterBCF = gasMeterBCF;
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
	 * Gets the device reading bcf.
	 * 
	 * @return the device reading bcf
	 */
	public IDeviceReadingBCF getDeviceReadingBCF()
	{
		return deviceReadingBCF;
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
	 * Load.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = LOAD, method = RequestMethod.GET)
	public ModelAndView load(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_DETAIL_MAIN);
		String serviceType = getServiceType();

		// Current Service type
		ServiceTypeEnum serviceTypeEnum = ServiceTypeEnum.valueOf(serviceType);

		// Detail Modules
		UiModulesPermissions detailUiModules = getDetailUiModules(serviceTypeEnum, deviceType, deviceSubType);

		// Module: Tabs and Summary Datas
		modelAndView.addObject(TABS, fillModulesResponse(detailUiModules.getTabs()));
		modelAndView.addObject(SUMMARY_DATAS, fillModulesResponse(detailUiModules.getSummaryDatas()));
		modelAndView.addObject(SERVICE_TYPE, serviceType);

		// (Common Request) Device Request
		DeviceRequest deviceRequest = new DeviceRequest(new Device(new Radio(flexNetId), deviceType));

		// Add user context to request
		addUserContextToRequest(deviceRequest);

		// Response Map
		Map<String, Response> responseMap = new HashMap<String, Response>();

		try
		{
			// Summaries array
			modelAndView.addObject("summaries", getMapper().writeValueAsString(detailUiModules.getSummaryDatas()));

			DeviceResponse deviceResponse = new DeviceResponse();
			switch (deviceType)
			{
				case ELECTRIC_METER:
				case HAN_DEVICE:
				case LCM:

					deviceResponse = getElectricMeterBCF().fetchDeviceById(deviceRequest);

					if (DeviceTypeEnum.HAN_DEVICE.equals(deviceType) || DeviceTypeEnum.LCM.equals(deviceType))
					{
						DMConvertUtil.convertMacAddress(deviceResponse);
					}

					break;

				case WATER_METER:

					deviceResponse = getWaterMeterBCF().fetchWaterMeterById(deviceRequest);
					break;

				case GAS_METER:

					deviceResponse = getGasMeterBCF().fetchGasMeterById(deviceRequest);
					break;
			}

			DMConvertUtil.convertTimeZoneToMinutes(deviceResponse.getDevices());

			// Response Map
			responseMap.put("device", deviceResponse);

			// Load Meter Profiles
			responseMap.put("loadProfiles", getLoadProfilesByDevice(deviceType, deviceResponse, deviceRequest));

			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(responseMap));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	private InquiryLoadProfileResponse getLoadProfilesByDevice(DeviceTypeEnum deviceType,
			DeviceResponse deviceResponse, DeviceRequest deviceRequest)
	{

		switch (deviceType)
		{
			case WATER_METER:
			case GAS_METER:

				if (!ValidationUtil.isNull(deviceResponse.getFirstDevice())
						&& !ValidationUtil.isNull(deviceResponse.getFirstDevice().getDeviceId()))
				{

					// Set Device Id
					deviceRequest.getFirstDevice().setDeviceId(deviceResponse.getFirstDevice().getDeviceId());

					// Set Customer Id
					deviceRequest.getFirstDevice().getRadio()
							.setCustomerId(deviceResponse.getFirstDevice().getRadio().getCustomerId());

					return getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(deviceRequest);
				}
				break;
		}

		return null;
	}
}
