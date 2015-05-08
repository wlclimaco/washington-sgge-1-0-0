package com.prosperitasglobal.sendsolv.tela.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The TelaAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/tela")
public class TelaAPIController extends TelaBaseController
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
	private static final Logger LOG = LoggerFactory.getLogger(TelaAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TelaAPIController";

	/**
	 * Fetch all Telas.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchTelaByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchTelaById(fetchByIdRequest);

	}

	/**
	 * Fetch all Telas.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ORGANIZATION_BYLOCATION, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse fetchOrganizationBylocation(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchTelaByOrganization(pagedInquiryRequest);

	}

	/**
	 * Edit one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse edit(@RequestBody TelaMaintenanceRequest locationRequest)
	{
		TelaResponse locationResponse = new TelaResponse();
		try
		{

			locationResponse = getTelaBAI().updateTela(locationRequest);

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
	public TelaResponse delete(@RequestBody TelaMaintenanceRequest locationRequest)
	{
		TelaResponse locationResponse = new TelaResponse();
		try
		{

			locationResponse = getTelaBAI().deleteTela(locationRequest);

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
	public TelaResponse insert(@RequestBody TelaMaintenanceRequest locationRequest)
	{
		TelaResponse locationResponse = new TelaResponse();

		try
		{

			locationRequest.getTela().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getTelaBAI().insertTela(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

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
