package com.prosperitasglobal.sendsolv.transportador.controller;

import java.util.Calendar;
import java.util.logging.Logger;

/**
 * The TransportadorAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/transportador")
public class TransportadorAPIController extends TransportadorBaseController
{

	/** The Constant FETCH_SIC_NAICS. */
	private static final String FETCH_SIC_NAICS = "fetchSicNaics";
	/** The Constant FETCH_ORGANIZATION_BYTRANSPORTADOR. */
	private static final String FETCH_ORGANIZATION_BYTRANSPORTADOR = "fetchOrganizationBytransportador";
	/** The URL mapping constants. */
	private static final String DELETE_TRANSPORTADOR = "/delete";

	/** The Constant EDIT_TRANSPORTADOR. */
	private static final String EDIT_TRANSPORTADOR = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_TRANSPORTADOR. */
	private static final String INSERT_TRANSPORTADOR = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TransportadorAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TransportadorAPIController";

	/**
	 * Fetch all Transportadors.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchTransportadorByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the transportador response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchTransportadorById(fetchByIdRequest);

	}

	/**
	 * Edit one transportador.
	 *
	 * @param transportadorRequest the transportador request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_TRANSPORTADOR, method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse edit(@RequestBody TransportadorMaintenanceRequest transportadorRequest)
	{
		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try
		{

			transportadorResponse = getTransportadorBAI().updateTransportador(transportadorRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			transportadorResponse = null;
		}
		return transportadorResponse;

	}

	/**
	 * Delete one transportador.
	 *
	 * @param transportadorRequest the transportador request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_TRANSPORTADOR, method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse delete(@RequestBody TransportadorMaintenanceRequest transportadorRequest)
	{
		TransportadorResponse transportadorResponse = new TransportadorResponse();
		try
		{

			transportadorResponse = getTransportadorBAI().deleteTransportador(transportadorRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			transportadorResponse = null;
		}

		return transportadorResponse;

	}

	/**
	 * Insert one transportador.
	 *
	 * @param transportadorRequest the transportador request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_TRANSPORTADOR, method = RequestMethod.POST)
	@ResponseBody
	public TransportadorResponse insert(@RequestBody TransportadorMaintenanceRequest transportadorRequest)
	{
		TransportadorResponse transportadorResponse = new TransportadorResponse();

		try
		{

			transportadorRequest.getTransportador().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			transportadorResponse = getTransportadorBAI().insertTransportador(transportadorRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			transportadorResponse = null;
		}

		return transportadorResponse;

	}

}
