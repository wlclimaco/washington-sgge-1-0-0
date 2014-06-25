package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class ProcedureViewController.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteViewController extends ClienteBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/cliente/cliente_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch clientesby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchProceduresByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchProceduresbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return clienteMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

}
