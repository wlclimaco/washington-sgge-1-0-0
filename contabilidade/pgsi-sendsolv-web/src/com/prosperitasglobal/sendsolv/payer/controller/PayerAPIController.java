package com.prosperitasglobal.sendsolv.payer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerCountryInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.request.PayerStateProvinceRegionInquiryRequest;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerAddressResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerCountryResponse;
import com.prosperitasglobal.sendsolv.payer.model.response.PayerStateProvinceRegionResponse;

/**
 * The Class SarAPIController.
 */
@Controller
@RequestMapping("/api/payer")
public class PayerAPIController extends PayerBaseController
{

	/** The Constant FETCH. */

	@RequestMapping(value = "payerCoutry", method = RequestMethod.POST)
	@ResponseBody
	public PayerCountryResponse fetchPayerCoutry(@RequestBody PayerCountryInquiryRequest request)
	{

		return fetchPayerCountryByRequest(request);

	}

	@RequestMapping(value = "payerState", method = RequestMethod.POST)
	@ResponseBody
	public PayerStateProvinceRegionResponse fetchPayerState(@RequestBody PayerStateProvinceRegionInquiryRequest request)
	{

		return fetchPayerStateProvinceRegionByRequest(request);

	}

	@RequestMapping(value = "payerCity", method = RequestMethod.POST)
	@ResponseBody
	public PayerAddressResponse fetchPayerCity(@RequestBody FetchByIdRequest request)
	{

		return fetchPayerAddressesByCityId(request);

	}

}
