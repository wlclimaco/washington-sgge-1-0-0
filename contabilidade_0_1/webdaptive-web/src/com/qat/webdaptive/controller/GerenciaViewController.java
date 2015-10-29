package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Class SupermercadoViewController.
 */
@Controller
@RequestMapping("/gerencia")
public class GerenciaViewController extends GerenciaBaseController
{

	/** The Constant SUPERMERCADO_MVC_BAS_RETURN. */
	private static final String SUPERMERCADO_MVC_BAS_RETURN = "/supermercado/supermercado_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch proceduresby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetchSupermercadosByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchSupermercadosbyRequestBAS()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return supermercadoMAV(request, SUPERMERCADO_MVC_BAS_RETURN);
	}
}
