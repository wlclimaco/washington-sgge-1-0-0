package com.prosperitasglobal.sendsolv.organization.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.OrganizationResponse;

/**
 * The OrganizationAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */

@Controller
@RequestMapping("/api/organization")
public class OrganizationAPIController extends OrganizationBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";

	/** The URL mapping constants. */
	private static final String DELETE = "/delete";

	/** The Constant EDIT. */
	private static final String EDIT = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant INSERT. */
	private static final String INSERT = "/add";

	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";

	/**
	 * Fetch all Organizations.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */

	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public OrganizationResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{
		return fetchAllOrganization(pagedInquiryRequest);
	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the organization response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public OrganizationResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{
		return fetchOrganization(fetchByIdRequest);
	}

	/**
	 * Edit one Organization.
	 *
	 * @param organizationRequest the organization request
	 * @return the response
	 */
	@RequestMapping(value = EDIT, method = RequestMethod.POST)
	@ResponseBody
	public OrganizationResponse edit(@RequestBody OrganizationMaintenanceRequest organizationRequest)
	{
		return editOrganization(organizationRequest);

	}

	/**
	 * Delete one Organization.
	 *
	 * @param organizationRequest the organization request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public OrganizationResponse delete(@RequestBody OrganizationMaintenanceRequest organizationRequest)
	{

		return deleteOrganization(organizationRequest);

	}

	/**
	 * Insert One Organization.
	 *
	 * @param organizationRequest the organization request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public OrganizationResponse insert(@RequestBody OrganizationMaintenanceRequest organizationRequest)
	{
		return insertOrganization(organizationRequest);
	}

	/**
	 * Apply status.
	 *
	 * @param organizationRequest the organization request
	 * @return the organization response
	 */
	@RequestMapping(value = APPLY, method = RequestMethod.POST)
	@ResponseBody
	public OrganizationResponse applyStatus(@RequestBody OrganizationMaintenanceRequest organizationRequest)
	{
		return applyStatusOrganization(organizationRequest);
	}

	/**
	 * Fetch sic naics.
	 *
	 * @param codeValueEnum the code value enum
	 * @return the map< integer, string>
	 */
	@RequestMapping(value = FETCH_SIC_NAICS, method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> fetchSicNaics(@RequestBody Integer codeValueEnum, String userId)
	{
		return listSicNaics(codeValueEnum, userId);
	}

}
