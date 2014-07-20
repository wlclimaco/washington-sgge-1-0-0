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
@RequestMapping("/supermercado")
public class SupermercadoViewController extends SupermercadoBaseController
{

	/** The Constant SUPERMERCADO_MVC_BAS_RETURN. */
	private static final String SUPERMERCADO_MVC_BAS_RETURN = "/supermercado/supermercado_mvc_bas";

	private static final String SUPERMERCADO_MVC_BAS_EDIT = "/supermercado/cadSup";

	private static final String SUPERMERCADO_MVC_BAS_UTIL = "/util/util_mvc_bas";

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

	@RequestMapping(value = "/fetchSupermercadosByEdit", method = RequestMethod.GET)
	public ModelAndView fetchSupermercadosbyEdit()
	{
		ModelAndView modelAndView = new ModelAndView(SUPERMERCADO_MVC_BAS_EDIT);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchSupermercadosByRequestBASUtil", method = RequestMethod.GET)
	public ModelAndView fetchSupermercadosbyRequestBASUtil()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		// we will be paging so set the default values
		request.setStartPage(ZERO);
		request.setPageSize(TWENTY);
		request.setPreQueryCount(true);
		return supermercadoMAV(request, SUPERMERCADO_MVC_BAS_UTIL);
	}
}
