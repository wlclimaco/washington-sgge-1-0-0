package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;

/**
 * The Class CidadeViewController.
 */
@Controller
@RequestMapping("/contato")
public class ContatoViewController extends SiteBaseController
{

	/** The Constant PROCEDURE_MVC_BAS_RETURN. */
	private static final String PROCEDURE_MVC_BAS_RETURN = "/contato/contato_main";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch cidadesby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchContatoByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchRegimebyRequestBAS()
	{
		ContatoInquiryRequest request = new ContatoInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return contatoMAV(request, PROCEDURE_MVC_BAS_RETURN);
	}

}
