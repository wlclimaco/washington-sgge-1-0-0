package com.sensus.dm.controller.electricmeter;

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
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;

/**
 * The Class ElectricMeterDeviceAPIController.
 */
@Controller
@RequestMapping("/api/electricmeterdevice")
public class ElectricMeterDeviceAPIController extends BaseController
{

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ElectricMeterDeviceAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ElectricMeterDeviceAPIController";

	/** The Constant FETCH. */
	private static final String FETCH_ALL = "/fetchAll";

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

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

			response = getElectricMeterBCF().fetchAllElectricMeters(deviceRequest);

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
