package com.prosperitasglobal.sendsolv.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;

/**
 * The Class ContactAPIController.
 */
@Controller
@RequestMapping("/api/contact")
public class ContactAPIController extends ContactBaseController
{
	/** The URL mapping constants. */
	private static final String DELETE = "/delete";

	/** The Constant EDIT. */
	private static final String EDIT = "/update";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_ALL. */
	private static final String FETCH = "/fetch";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/**
	 * Fetch all.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the liaison response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public LiaisonResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchAllLiaison(pagedInquiryRequest);
	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the liaison response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public LiaisonResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchLiaison(fetchByIdRequest);
	}

	/**
	 * Edits.
	 *
	 * @param liaisonMaintenanceRequest the liaison maintenance request
	 * @return the liaison response
	 */
	@RequestMapping(value = EDIT, method = RequestMethod.POST)
	@ResponseBody
	public LiaisonResponse edit(@RequestBody LiaisonMaintenanceRequest liaisonMaintenanceRequest)
	{

		return editLiaison(liaisonMaintenanceRequest);

	}

	/**
	 * Delete.
	 *
	 * @param liaisonMaintenanceRequest the liaison maintenance request
	 * @return the liaison response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public LiaisonResponse delete(@RequestBody LiaisonMaintenanceRequest liaisonMaintenanceRequest)
	{
		return deleteLiaison(liaisonMaintenanceRequest);

	}

	/**
	 * Insert.
	 *
	 * @param liaisonMaintenanceRequest the liaison maintenance request
	 * @return the liaison response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public LiaisonResponse insert(@RequestBody LiaisonMaintenanceRequest liaisonMaintenanceRequest)
	{

		return insertLiaison(liaisonMaintenanceRequest);
	}

}
