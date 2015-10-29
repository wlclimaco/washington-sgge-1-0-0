package com.qat.webdaptive.controller.ordemServico;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.OrdemServicoResponse;

@Controller
@RequestMapping("/api/ordemServico")
public class OrdemServicoAPIController extends OrdemServicoBaseController
{

	/** The URL mapping constants. */
	private static final String DELETE_ORDEMSERVICO = "/delete";

	/** The Constant EDIT_ORDEMSERVICO. */
	private static final String EDIT_ORDEMSERVICO = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_ORDEMSERVICO. */
	private static final String INSERT_ORDEMSERVICO = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";
	/** The Constant APPLY. */
	private static final String APPLY = "/applyStatus";
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(OrdemServicoAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "OrdemServicoAPIController";

	/**
	 * Fetch all OrdemServicos.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse fetchAll(@RequestBody OrdemServicoInquiryRequest pagedInquiryRequest)
	{

		return fetchOrdemServicoByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the ordemServico response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchOrdemServicoById(fetchByIdRequest);

	}

	/**
	 * Edit one ordemServico.
	 *
	 * @param ordemServicoRequest the ordemServico request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_ORDEMSERVICO, method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse edit(@RequestBody OrdemServicoMaintenanceRequest ordemServicoRequest)
	{
		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
		try
		{

			ordemServicoResponse = getOrdemServicoBAI().updateOrdemServico(ordemServicoRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			ordemServicoResponse = null;
		}
		return ordemServicoResponse;

	}

	/**
	 * Delete one ordemServico.
	 *
	 * @param ordemServicoRequest the ordemServico request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_ORDEMSERVICO, method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse delete(@RequestBody OrdemServicoMaintenanceRequest ordemServicoRequest)
	{
		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();
		try
		{

			ordemServicoResponse = getOrdemServicoBAI().deleteOrdemServico(ordemServicoRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			ordemServicoResponse = null;
		}

		return ordemServicoResponse;

	}

	/**
	 * Insert one ordemServico.
	 *
	 * @param ordemServicoRequest the ordemServico request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_ORDEMSERVICO, method = RequestMethod.POST)
	@ResponseBody
	public OrdemServicoResponse insert(@RequestBody OrdemServicoMaintenanceRequest ordemServicoRequest)
	{
		OrdemServicoResponse ordemServicoResponse = new OrdemServicoResponse();

		try
		{

			ordemServicoRequest.getOrdemServico().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			ordemServicoResponse = getOrdemServicoBAI().insertOrdemServico(ordemServicoRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			ordemServicoResponse = null;
		}

		return ordemServicoResponse;

	}

}
