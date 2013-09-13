package com.sensus.dm.controller.watermeter;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * The Class WaterMeterDeviceAPIController.
 */
@Controller
@RequestMapping("/api/watermeterdevice")
public class WaterMeterDeviceAPIController extends BaseController
{
	/** The logger for this class. */
	private static final Logger LOG = LoggerFactory.getLogger(WaterMeterDeviceAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "WaterMeterDeviceAPIController";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant FETCH. */
	private static final String FETCH_ALL = "/fetchAll";

	/** The Constant FETCH_LEAK_REPORT. */
	private static final String FETCH_LEAK_REPORT = "/fetchLeakReport";

	/** The water meter BCF. */
	private IWaterMeterBCF waterMeterBCF;

	/**
	 * Gets the water meter BCF.
	 * 
	 * @return the waterMeterBCF
	 */
	public IWaterMeterBCF getWaterMeterBCF()
	{
		return waterMeterBCF;
	}

	/**
	 * Sets the water meter BCF.
	 * 
	 * @param waterMeterBCF the waterMeterBCF to set
	 */
	@Resource
	public void setWaterMeterBCF(IWaterMeterBCF waterMeterBCF)
	{
		this.waterMeterBCF = waterMeterBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody InquiryDeviceRequest deviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();

		try
		{
			addUserContextToRequest(deviceRequest);

			deviceRequest.setPreQueryCount(true);

			response = getWaterMeterBCF().fetchAllWaterMeters(deviceRequest);

			// Convert Time zone to minutes and set in the displayMinutes field
			DMConvertUtil.convertTimeZoneToMinutes(response.getDevices());
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	/**
	 * Fetch.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{

			addUserContextToRequest(deviceRequest);

			// Fetch By Id
			response = getWaterMeterBCF().fetchWaterMeterById(deviceRequest);

			// Convert Time zone to minutes and set in the displayMinutes field
			DMConvertUtil.convertTimeZoneToMinutes(response.getDevices());
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch leak report.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_LEAK_REPORT, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchLeakReport(@RequestBody DeviceRequest request)
	{
		WaterMeterResponse response = new WaterMeterResponse();

		try
		{
			addUserContextToRequest(request);

			// Fetch Leak Report
			response = getWaterMeterBCF().fetchLeakReport(request);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

}
