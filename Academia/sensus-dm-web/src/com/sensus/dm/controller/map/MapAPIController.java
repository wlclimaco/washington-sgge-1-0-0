package com.sensus.dm.controller.map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.InquiryGeocodeDeviceInfoResponse;
import com.sensus.dm.controller.base.BaseController;

/**
 * The Class DashboardAPIController.
 */
@Controller
@RequestMapping("/api/maps")
public class MapAPIController extends BaseController
{

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MapAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "MapAPIController";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	private IDeviceBCF deviceBCF;

	/**
	 * @return the deviceBCF
	 */
	public IDeviceBCF getDeviceBCF()
	{
		return deviceBCF;
	}

	/**
	 * @param deviceBCF the deviceBCF to set
	 */
	@Resource
	public void setDeviceBCF(IDeviceBCF deviceBCF)
	{
		this.deviceBCF = deviceBCF;
	}

	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public InquiryGeocodeDeviceInfoResponse fetch(
			@RequestBody InquiryDeviceRequest inquiryLightRequest,
			HttpServletRequest request)
	{

		InquiryGeocodeDeviceInfoResponse response = new InquiryGeocodeDeviceInfoResponse();

		try
		{

			addUserContextToRequest(inquiryLightRequest);

			response = getDeviceBCF().fetchDevicesBoundsToMap(inquiryLightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	@RequestMapping(value = "/fetchDevice", method = RequestMethod.POST)
	@ResponseBody
	public InquiryGeocodeDeviceInfoResponse fetchDevice(
			@RequestBody InquiryDeviceRequest inquiryLightRequest,
			HttpServletRequest request)
	{

		InquiryGeocodeDeviceInfoResponse response = new InquiryGeocodeDeviceInfoResponse();

		try
		{

			addUserContextToRequest(inquiryLightRequest);

			response = getDeviceBCF().fetchDevicesToMap(inquiryLightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

}
