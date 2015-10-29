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
@RequestMapping("/procedure")
public class ProcedureViewController extends ProcedureBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/procedures/procedures_mvc_bas";

	private static final String FOTO_MVC_BAS_RETURN = "/fotos/fotos_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch proceduresby request bas.
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
		return procedureMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

	@RequestMapping(value = "/fetchFotoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchFotobyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return procedureMAV(request, FOTO_MVC_BAS_RETURN);
	}
}
