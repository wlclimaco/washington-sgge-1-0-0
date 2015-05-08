package com.prosperitasglobal.sendsolv.location.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;

/**
 * The LocationAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/location")
public class LocationAPIController extends LocationBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYLOCATION. */
	private static final String FETCH_ORGANIZATION_BYLOCATION = "fetchOrganizationBylocation";
	/** The URL mapping constants. */
	private static final String DELETE_LOCATION = "/delete";

	/** The Constant EDIT_LOCATION. */
	private static final String EDIT_LOCATION = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_LOCATION. */
	private static final String INSERT_LOCATION = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LocationAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "LocationAPIController";

	/**
	 * Fetch all Locations.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchLocationByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchLocationById(fetchByIdRequest);

	}

	/**
	 * Fetch all Locations.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ORGANIZATION_BYLOCATION, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse fetchOrganizationBylocation(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchLocationByOrganization(pagedInquiryRequest);

	}

	/**
	 * Edit one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse edit(@RequestBody LocationMaintenanceRequest locationRequest)
	{
		LocationResponse locationResponse = new LocationResponse();
		try
		{

			locationResponse = getLocationBAI().updateLocation(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}
		return locationResponse;

	}

	/**
	 * Delete one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse delete(@RequestBody LocationMaintenanceRequest locationRequest)
	{
		LocationResponse locationResponse = new LocationResponse();
		try
		{

			locationResponse = getLocationBAI().deleteLocation(locationRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	/**
	 * Insert one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse insert(@RequestBody LocationMaintenanceRequest locationRequest)
	{
		LocationResponse locationResponse = new LocationResponse();

		try
		{

			locationRequest.getLocation().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getLocationBAI().insertLocation(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}

	/**
	 * Apply status.
	 *
	 * @param locationRequest the location request
	 * @return the location response
	 */
	@RequestMapping(value = APPLY, method = RequestMethod.POST)
	@ResponseBody
	public LocationResponse applyStatus(@RequestBody LocationMaintenanceRequest locationRequest)
	{
		return applyStatusLocation(locationRequest);
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
