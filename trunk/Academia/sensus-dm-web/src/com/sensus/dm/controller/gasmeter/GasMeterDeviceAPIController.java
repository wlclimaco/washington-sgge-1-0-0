package com.sensus.dm.controller.gasmeter;

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
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;

/**
 * The Class GasMeterDeviceAPIController.
 */
@Controller
@RequestMapping("/api/gasmeterdevice")
public class GasMeterDeviceAPIController extends BaseController
{
	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GasMeterDeviceAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "GasMeterDeviceAPIController";

	/** The Constant FETCH. */
	private static final String FETCH_ALL = "/fetchAll";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The gas meter BCF. */
	private IGasMeterBCF gasMeterBCF;

	/**
	 * Gets the gas meter BCF.
	 * 
	 * @return the gasMeterBCF
	 */
	public IGasMeterBCF getGasMeterBCF()
	{
		return gasMeterBCF;
	}

	/**
	 * Sets the gas meter BCF.
	 * 
	 * @param gasMeterBCF the gasMeterBCF to set
	 */
	@Resource
	public void setGasMeterBCF(IGasMeterBCF gasMeterBCF)
	{
		this.gasMeterBCF = gasMeterBCF;
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

			response = getGasMeterBCF().fetchAllGasMeters(deviceRequest);

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
			response = getGasMeterBCF().fetchGasMeterById(deviceRequest);

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

}
