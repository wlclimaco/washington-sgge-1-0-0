package com.qat.webdaptive.controller.historico;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.AlertasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.AlertasResponse;
import com.prosperitasglobal.sendsolv.model.response.HistoricoResponse;

/**
 * The HistoricoAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/historico")
public class HistoricoAPIController extends HistoricoBaseController
{

	private static final String FETCH_ALERTAS = "fetch/alertas";

	/** The URL mapping constants. */
	private static final String DELETE_HISTORICO = "/delete";

	/** The Constant EDIT_HISTORICO. */
	private static final String EDIT_HISTORICO = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_HISTORICO. */
	private static final String INSERT_HISTORICO = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(HistoricoAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "HistoricoAPIController";

	/**
	 * Fetch all Historicos.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse fetchAll(@RequestBody HistoricoInquiryRequest pagedInquiryRequest)
	{

		return fetchHistoricoByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the historico response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchHistoricoById(fetchByIdRequest);

	}

	/**
	 * Edit one historico.
	 *
	 * @param historicoRequest the historico request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_HISTORICO, method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse edit(@RequestBody HistoricoMaintenanceRequest historicoRequest)
	{
		HistoricoResponse historicoResponse = new HistoricoResponse();
		try
		{

			historicoResponse = getHistoricoBAI().updateHistorico(historicoRequest);

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
	@RequestMapping(value = DELETE_HISTORICO, method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse delete(@RequestBody HistoricoMaintenanceRequest historicoRequest)
	{
		HistoricoResponse historicoResponse = new HistoricoResponse();
		try
		{

			historicoResponse = getHistoricoBAI().deleteHistorico(historicoRequest);

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
	@RequestMapping(value = INSERT_HISTORICO, method = RequestMethod.POST)
	@ResponseBody
	public HistoricoResponse insert(@RequestBody HistoricoMaintenanceRequest historicoRequest)
	{
		HistoricoResponse historicoResponse = new HistoricoResponse();

		try
		{

			historicoRequest.getHistorico().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			historicoResponse = getHistoricoBAI().insertHistorico(historicoRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			historicoResponse = null;
		}

		return historicoResponse;

	}

	@RequestMapping(value = FETCH_ALERTAS, method = RequestMethod.POST)
	@ResponseBody
	public AlertasResponse fetchAlerts(@RequestBody AlertasInquiryRequest historicoRequest)
	{
		return fetchAllAlertas(historicoRequest);

	}

}
