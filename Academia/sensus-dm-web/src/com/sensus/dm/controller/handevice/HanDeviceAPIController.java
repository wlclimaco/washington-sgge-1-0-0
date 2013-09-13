package com.sensus.dm.controller.handevice;

import java.math.BigInteger;

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
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.controller.util.DMUtil;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The Class HanDeviceAPIController.
 */
@Controller
@RequestMapping("/api/handevice")
public class HanDeviceAPIController extends BaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HanDeviceAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "HanDeviceAPIController";

	/** The Constant FETCH_ALL. */
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

			// Convert MacAddress to FlexNetId (BigInteger)
			if (!ValidationUtil.isNull(deviceRequest.getDeviceSearch())
					&& !ValidationUtil.isNull(deviceRequest.getDeviceSearch().getHanDeviceSearch())
					&& !ValidationUtil.isNull(deviceRequest.getDeviceSearch().getHanDeviceSearch().getHanDevice())
					&& !ValidationUtil.isNull(deviceRequest.getDeviceSearch().getHanDeviceSearch().getHanDevice()
							.getMacAddress()))
			{
				HanDevice hanDevice = deviceRequest.getDeviceSearch().getHanDeviceSearch().getHanDevice();

				String macAddress = hanDevice.getMacAddress();
				BigInteger flexNetID = DMUtil.convertMacAddress(macAddress);

				deviceRequest.getDeviceSearch().getHanDeviceSearch().getHanDevice().setRadio(new Radio(flexNetID));
			}

			response = getElectricMeterBCF().fetchAllHanDevices(deviceRequest);

			if (!ValidationUtil.isNull(response.getDevices()))
			{
				for (Device device : response.getDevices())
				{
					HanDevice hanDevice = (HanDevice)device;
					hanDevice.setMacAddress(DMUtil.convertBigInteger(hanDevice.getRadio().getFlexNetId()));
				}
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}
}
