package com.prosperitasglobal.sendsolv.tela.controller;

import java.util.Calendar;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.TelaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TelaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.TelaResponse;

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

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TelaAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TelaAPIController";
	private static final String EDIT_TELA = "/edit";
	private static final String DELETE_TELA = "/delete";
	private static final String INSERT_TELA = "/add";

	/**
	 * Fetch all Telas.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse fetchAll(@RequestBody TelaInquiryRequest pagedInquiryRequest)
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
	 * Edit one historico.
	 *
	 * @param historicoRequest the historico request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_TELA, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse edit(@RequestBody TelaMaintenanceRequest historicoRequest)
	{
		TelaResponse historicoResponse = new TelaResponse();
		try
		{

			historicoResponse = getTelaBAI().updateTela(historicoRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			historicoResponse = null;
		}
		return historicoResponse;

	}

	/**
	 * Delete one historico.
	 *
	 * @param historicoRequest the historico request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_TELA, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse delete(@RequestBody TelaMaintenanceRequest historicoRequest)
	{
		TelaResponse historicoResponse = new TelaResponse();
		try
		{

			historicoResponse = getTelaBAI().deleteTela(historicoRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			historicoResponse = null;
		}

		return historicoResponse;

	}

	/**
	 * Insert one historico.
	 *
	 * @param historicoRequest the historico request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_TELA, method = RequestMethod.POST)
	@ResponseBody
	public TelaResponse insert(@RequestBody TelaMaintenanceRequest historicoRequest)
	{
		TelaResponse historicoResponse = new TelaResponse();

		try
		{

			historicoRequest.getTela().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			historicoResponse = getTelaBAI().insertTela(historicoRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			historicoResponse = null;
		}

		return historicoResponse;

	}

}
