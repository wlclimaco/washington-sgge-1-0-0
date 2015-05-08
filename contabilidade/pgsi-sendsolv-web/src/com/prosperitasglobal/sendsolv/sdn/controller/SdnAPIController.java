package com.prosperitasglobal.sendsolv.sdn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnHistoryResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnStatusHistoryResponse;

/**
 * The MemberAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/sdn")
public class SdnAPIController extends SdnBaseController
{

	private static final String FETCH = "fetch";
	/** The Constant EDIT_MEMBER. */
	private static final String EDIT_SDN = "/update";

	@RequestMapping(value = EDIT_SDN, method = RequestMethod.POST)
	@ResponseBody
	public SdnStatusHistoryResponse edit(@RequestBody SdnStatusHistoryRequest request)
	{
		return updateSdnStatusHistory(request);

	}

	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public SdnHistoryResponse fetch(@RequestBody SdnStatusHistoryInquiryRequest request)
	{
		return fetchFullSdnStatusHistory(request);

	}

}
