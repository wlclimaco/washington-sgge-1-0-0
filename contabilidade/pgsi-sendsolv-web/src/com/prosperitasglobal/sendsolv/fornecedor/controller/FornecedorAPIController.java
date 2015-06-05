package com.prosperitasglobal.sendsolv.fornecedor.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FornecedorMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.FornecedorResponse;

@Controller
@RequestMapping("/api/fornecedor")
public class FornecedorAPIController extends FornecedorBaseController
{

	/** The URL mapping constants. */
	private static final String DELETE_FORNECEDOR = "/delete";

	/** The Constant EDIT_FORNECEDOR. */
	private static final String EDIT_FORNECEDOR = "/edit";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchall";

	/** The Constant INSERT_FORNECEDOR. */
	private static final String INSERT_FORNECEDOR = "/add";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FornecedorAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "FornecedorAPIController";

	/**
	 * Fetch all Fornecedors.
	 *
	 * @param pagedInquiryRequest the paged inquiry request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse fetchAll(@RequestBody FornecedorInquiryRequest pagedInquiryRequest)
	{

		return fetchFornecedorByRequest(pagedInquiryRequest);

	}

	/**
	 * Fetch.
	 *
	 * @param fetchByIdRequest the fetch by id request
	 * @return the fornecedor response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse fetch(@RequestBody FetchByIdRequest fetchByIdRequest)
	{

		return fetchFornecedorById(fetchByIdRequest);

	}

	/**
	 * Edit one fornecedor.
	 *
	 * @param fornecedorRequest the fornecedor request
	 * @return the response
	 */
	@RequestMapping(value = EDIT_FORNECEDOR, method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse edit(@RequestBody FornecedorMaintenanceRequest fornecedorRequest)
	{
		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try
		{

			fornecedorResponse = getPessoaBAI().updateFornecedor(fornecedorRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			fornecedorResponse = null;
		}
		return fornecedorResponse;

	}

	/**
	 * Delete one fornecedor.
	 *
	 * @param fornecedorRequest the fornecedor request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_FORNECEDOR, method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse delete(@RequestBody FornecedorMaintenanceRequest fornecedorRequest)
	{
		FornecedorResponse fornecedorResponse = new FornecedorResponse();
		try
		{

			fornecedorResponse = getPessoaBAI().deleteFornecedor(fornecedorRequest);

		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			fornecedorResponse = null;
		}

		return fornecedorResponse;

	}

	/**
	 * Insert one fornecedor.
	 *
	 * @param fornecedorRequest the fornecedor request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_FORNECEDOR, method = RequestMethod.POST)
	@ResponseBody
	public FornecedorResponse insert(@RequestBody FornecedorMaintenanceRequest fornecedorRequest)
	{
		FornecedorResponse fornecedorResponse = new FornecedorResponse();

		try
		{

			fornecedorRequest.getFornecedor().setCreateDateUTC(Calendar.getInstance().getTimeInMillis());
			fornecedorResponse = getPessoaBAI().insertFornecedor(fornecedorRequest);
		}
		catch (Exception e)
		{
			LOG.error(CONTROLLER_EXCEPTION_MSG, e);
			fornecedorResponse = null;
		}

		return fornecedorResponse;

	}

}
