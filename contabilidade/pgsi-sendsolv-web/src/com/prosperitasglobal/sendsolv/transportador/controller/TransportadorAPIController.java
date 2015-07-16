package com.prosperitasglobal.sendsolv.transportador.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TransportadorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.TransportadorResponse;

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
	public TransportadorResponse fetchAll(@RequestBody TransportadorInquiryRequest pagedInquiryRequest)
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

			transportadorResponse = getPessoaBAI().updateTransportador(transportadorRequest);

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

			transportadorResponse = getPessoaBAI().deleteTransportador(transportadorRequest);

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
	public TransportadorResponse insertApi(@RequestBody TransportadorMaintenanceRequest transportadorRequest)
	{

		return insert(transportadorRequest);

	}

}
