package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoInquiryRequest;

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

	// ================ MENU ========================
	private static final String MOBILE = "mobile";
	private static final String SUPERMERCADO_RESPONSE = "supermercadoResponse";
	private static final String SUPERMERCADO_MVC_RETURN = "/supermercado/supermercado_mvc";
	private static final String SUPERMERCADO_MVC_M_RETURN = "/mobile/supermercado/supermercado_mvc.m";
	// ==============================================

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
		ModelAndView modelAndView = new ModelAndView(SUPERMERCADO_MVC_BAS_RETURN);
		return modelAndView;
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
		ModelAndView modelAndView = new ModelAndView(SUPERMERCADO_MVC_BAS_UTIL);
		return modelAndView;

	}

	@RequestMapping(value = "/fetchSupermercados", method = RequestMethod.GET)
	public ModelAndView fetchAllSupermercadosMVC(@RequestParam String view)
	{
		// if mobile send back mobile view
		if (view.equalsIgnoreCase(MOBILE))
		{
			SupermercadoInquiryRequest request = new SupermercadoInquiryRequest();
			return new ModelAndView(SUPERMERCADO_MVC_M_RETURN, SUPERMERCADO_RESPONSE, supermercadoBEFetchAll(true,
					request));
		}
		return null;
	}
}
