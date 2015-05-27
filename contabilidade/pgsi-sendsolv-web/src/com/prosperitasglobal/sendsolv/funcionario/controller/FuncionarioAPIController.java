package com.prosperitasglobal.sendsolv.funcionario.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.FuncionarioResponse;

/**
 * The FuncionarioAPIController Class.
 *
 * @author Flavio Tosta, Washington Costa
 *
 */
@Controller
@RequestMapping("/api/funcionario")
public class FuncionarioAPIController extends FuncionarioBaseController
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
	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "FuncionarioAPIController";

	/**
	 * Fetch all Funcionarios.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse fetchAll(@RequestBody PagedInquiryRequest pagedInquiryRequest)
	{

		return fetchFuncionarioByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the location response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchFuncionarioById(fetchByIdRequest);

	}

	/**
	 * Edit one location.
	 *
	 * @param locationRequest the location request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_LOCATION, method = RequestMethod.POST)
	@ResponseBody
	public FuncionarioResponse edit(@RequestBody FuncionarioMaintenanceRequest locationRequest)
	{
		FuncionarioResponse locationResponse = new FuncionarioResponse();
		try
		{

			locationResponse = getFuncionarioBAI().updateFuncionario(locationRequest);

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
	public FuncionarioResponse delete(@RequestBody FuncionarioMaintenanceRequest locationRequest)
	{
		FuncionarioResponse locationResponse = new FuncionarioResponse();
		try
		{

			locationResponse = getFuncionarioBAI().deleteFuncionario(locationRequest);

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
	public FuncionarioResponse insert(@RequestBody FuncionarioMaintenanceRequest locationRequest)
	{
		FuncionarioResponse locationResponse = new FuncionarioResponse();

		try
		{

			locationRequest.getFuncionario().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			locationResponse = getFuncionarioBAI().insertFuncionario(locationRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			locationResponse = null;
		}

		return locationResponse;

	}
}
