package com.sensus.dm.controller.device.detail;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

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
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.controller.base.BaseViewController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.device.bcf.IDeviceReadingBCF;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;

/**
 * The Class ProcessViewController.
 * 
 * @author QAT BRAZIL
 */
@Controller
@RequestMapping("/device")
public class DeviceReadsViewController extends BaseViewController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DeviceReadsViewController.class);

	/** The Constant FETCH_LIST_INTERVALREADS. */
	public static final String FETCH_LIST_INTERVALREADS = "/tab/intervalReads";

	/** The Constant FETCH_LIST_LOADPROFILE. */
	public static final String FETCH_LIST_LOADPROFILE = "/tab/loadProfiles";

	/** The Constant FETCH_LIST_SNAPSHOT. */
	public static final String FETCH_LIST_SNAPSHOT = "/tab/snapshot";

	/** The Constant FETCH_LIST_DATAREAD. */
	public static final String FETCH_LIST_DATAREAD = "/tab/readData";

	/** The Constant FETCH_LIST_TOUREAD. */
	public static final String FETCH_LIST_TOUREAD = "/tab/tou";

	/** The Constant LOAD_PROFILES. */
	private static final String LOAD_PROFILES = "loadProfiles";

	/** The Constant VIEW_DEVICES_MAIN. */
	private static final String VIEW_DEVICES_MAIN = "/device_detail/device_detail_readings";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "DeviceReadsViewController";

	/** The Constant ZERO. */
	private static final String ZERO = "0";

	/** The device reading bcf. */
	private IDeviceReadingBCF deviceReadingBCF;

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/** The gas meter bcf. */
	private IGasMeterBCF gasMeterBCF;

	/** The water meter bcf. */
	private IWaterMeterBCF waterMeterBCF;

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
	 * Load list.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST_INTERVALREADS, method = RequestMethod.GET)
	public ModelAndView loadListIntervalReads(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DEVICES_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		DeviceReadingRequest deviceReadingRequest = new DeviceReadingRequest();

		deviceReadingRequest.setDevice(new Device(new Radio(flexNetId)));
		deviceReadingRequest.setInitialDate(DMConvertUtil.getStartOfDay(new Date(), ZERO));
		deviceReadingRequest.setEndDate(DMConvertUtil.getEndOfDay(new Date(), ZERO));
		deviceReadingRequest.setDevice(fetchDevice(deviceType, flexNetId));

		// ADD user context to request
		addUserContextToRequest(deviceReadingRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
					getDeviceReadingBCF().fetchAllIntervalRead(deviceReadingRequest)));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Load list.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST_LOADPROFILE, method = RequestMethod.GET)
	public ModelAndView loadListLoadProfiles(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DEVICES_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		DeviceReadingRequest deviceReadingRequest = new DeviceReadingRequest();

		deviceReadingRequest.setDevice(new Device(new Radio(flexNetId)));
		deviceReadingRequest.setInitialDate(DMConvertUtil.getStartOfDay(new Date(), ZERO));
		deviceReadingRequest.setEndDate(DMConvertUtil.getEndOfDay(new Date(), ZERO));
		deviceReadingRequest.setDevice(fetchDevice(deviceType, flexNetId));

		// ADD user context to request
		addUserContextToRequest(deviceReadingRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
					getDeviceReadingBCF().fetchAllLoadProfileRead(deviceReadingRequest)));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Load list snapshot.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST_SNAPSHOT, method = RequestMethod.GET)
	public ModelAndView loadListSnapshot(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DEVICES_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		DeviceReadingRequest deviceReadingRequest = new DeviceReadingRequest();

		deviceReadingRequest.setDevice(new Device(new Radio(flexNetId)));
		deviceReadingRequest.setInitialDate(DMConvertUtil.getStartOfDay(new Date(), ZERO));
		deviceReadingRequest.setEndDate(DMConvertUtil.getEndOfDay(new Date(), ZERO));
		deviceReadingRequest.setDevice(fetchDevice(deviceType, flexNetId));

		// ADD user context to request
		addUserContextToRequest(deviceReadingRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
					getDeviceReadingBCF().fetchAllSnapshots(deviceReadingRequest)));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Load list data reads.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST_DATAREAD, method = RequestMethod.GET)
	public ModelAndView loadListDataReads(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DEVICES_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		DeviceReadingRequest deviceReadingRequest = new DeviceReadingRequest();

		Device device = fetchDevice(deviceType, flexNetId);

		deviceReadingRequest.setDevice(new Device(new Radio(flexNetId)));
		deviceReadingRequest.setInitialDate(DMConvertUtil.getStartOfDay(new Date(), ZERO));
		deviceReadingRequest.setEndDate(DMConvertUtil.getEndOfDay(new Date(), ZERO));
		deviceReadingRequest.setDevice(device);

		// ADD user context to request
		addUserContextToRequest(deviceReadingRequest);

		try
		{
			if (ServiceTypeEnum.ELECTRIC.equals(ServiceTypeEnum.valueOf(getServiceType())))
			{
				modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
						getDeviceReadingBCF().fetchAllIntervalRead(deviceReadingRequest)));
			}
			else if (ServiceTypeEnum.WATER.equals(ServiceTypeEnum.valueOf(getServiceType()))
					|| ServiceTypeEnum.GAS.equals(ServiceTypeEnum.valueOf(getServiceType())))
			{
				modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
						getDeviceReadingBCF().fetchAllWaterGasDataRead(deviceReadingRequest)));

				DeviceRequest deviceRequest = new DeviceRequest(device);

				// ADD user context to request
				addUserContextToRequest(deviceRequest);

				modelAndView.addObject(LOAD_PROFILES, getMapper().writeValueAsString(
						getDeviceReadingBCF().fetchUpdatedMeterLoadProfile(deviceRequest)));
			}
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Load list tou reads.
	 * 
	 * @param flexNetId the flex net id
	 * @param deviceType the device type
	 * @param deviceSubType the device sub type
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST_TOUREAD, method = RequestMethod.GET)
	public ModelAndView loadListTouReads(@RequestParam(value = "id", required = true) BigInteger flexNetId,
			@RequestParam(value = "deviceType", required = true) DeviceTypeEnum deviceType,
			@RequestParam(value = "typeEnum", required = false) String deviceSubType,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DEVICES_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		DeviceReadingRequest deviceReadingRequest = new DeviceReadingRequest();

		deviceReadingRequest.setDevice(new Device(new Radio(flexNetId)));
		deviceReadingRequest.setInitialDate(DMConvertUtil.getStartOfDay(new Date(), ZERO));
		deviceReadingRequest.setEndDate(DMConvertUtil.getEndOfDay(new Date(), ZERO));
		deviceReadingRequest.setDevice(fetchDevice(deviceType, flexNetId));

		// ADD user context to request
		addUserContextToRequest(deviceReadingRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(
					getDeviceReadingBCF().fetchAllTOURead(deviceReadingRequest)));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Fetch device.
	 * 
	 * @param deviceType the device type
	 * @param flexNetId the flex net id
	 * @return the device
	 */
	public Device fetchDevice(DeviceTypeEnum deviceType, BigInteger flexNetId)
	{
		// (Common Request) Device Request
		DeviceRequest deviceRequest = new DeviceRequest(new Device(new Radio(flexNetId), deviceType));

		// Add user context to request
		addUserContextToRequest(deviceRequest);

		DeviceResponse deviceResponse = new DeviceResponse();
		switch (deviceType)
		{
			case ELECTRIC_METER:
			case HAN_DEVICE:
			case LCM:

				deviceResponse = getElectricMeterBCF().fetchDeviceById(deviceRequest);
				DMConvertUtil.convertMacAddress(deviceResponse);
				break;

			case WATER_METER:

				deviceResponse = getWaterMeterBCF().fetchWaterMeterById(deviceRequest);
				break;

			case GAS_METER:

				deviceResponse = getGasMeterBCF().fetchGasMeterById(deviceRequest);
				break;
		}

		return deviceResponse.getDevices().get(0);

	}

}
