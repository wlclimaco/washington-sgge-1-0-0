package com.prosperitasglobal.sendsolv.sar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Class SarAPIController.
 */
@Controller
@RequestMapping("/api/sar")
public class SarAPIController extends SarBaseController
{

	/** The Constant RAA. */
	private static final String RAA = "RAA";

	/** The Constant OAA. */
	private static final String OAA = "OAA";

	/** The Constant MAA. */
	private static final String MAA = "MAA";

	/** The Constant CAA. */
	private static final String CAA = "CAA";

	/** The Constant LAA. */
	private static final String LAA = "LAA";

	/** The Constant SEARCH. */
	private static final String SEARCH = "search";

	/** The Constant FETCH. */
	private static final String FETCH = "fetch";

	/** The Constant UPDATE. */
	private static final String UPDATE = "update";

	private static final String INSERT_SAR = "/insert";

	/**
	 * Insert one member.
	 *
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_SAR, method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse insert(@RequestBody SarMaintenanceRequest request)
	{

		return insertSuspiciousActivity(request);

	}

	/**
	 * Update.
	 *
	 * @param request the request
	 * @return the maintenance response
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse update(@RequestBody SarMaintenanceRequest request)
	{

		return updateSuspiciousActivity(request);

	}

	/**
	 * Fetch.
	 *
	 * @param request the request
	 * @return the sar response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public SarResponse fetch(@RequestBody SarInquiryRequest request)
	{

		return fetchSuspiciousActivityByRequest(request);

	}

	/**
	 * Search.
	 *
	 * @param request the request
	 * @return the object
	 */
	@RequestMapping(value = SEARCH, method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestBody FetchByIdRequest request)
	{

		if (request.getStringId().contains(LAA))
		{
			return fetchLocationById(request);
		}
		else if (request.getStringId().contains(CAA))
		{
			return fetchLiaisonByID(request);
		}
		else if (request.getStringId().contains(MAA))
		{
			return fetchMemberByID(request);
		}
		else if (request.getStringId().contains(OAA))
		{
			return fetchOrganizationById(request);
		}
		else if (request.getStringId().contains(RAA))
		{
			return fetchRecipientByID(request);
		}
		return null;

	}
}
