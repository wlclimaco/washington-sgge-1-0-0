package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;

/**
 * The Class CidadeViewController.
 */
@Controller
@RequestMapping("/plano")
public class PlanoViewController extends SiteBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/plano/plano_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch cidadesby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchPlanosByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchCidadesbyRequestBAS()
	{
		PlanoInquiryRequest request = new PlanoInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return planoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

}
