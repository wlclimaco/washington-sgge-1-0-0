package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class SupermercadoViewController.
 */
@Controller
@RequestMapping("/cadastro")
public class CadastroViewController extends CadastroBaseController
{

	/** The Constant SUPERMERCADO_MVC_BAS_RETURN. */
	private static final String MENU_MVC_BAS_RETURN = "/cadastro/menu_mvc_bas";

	private static final String SUBMENU_MVC_BAS_RETURN = "/cadastro/submenu_mvc_bas";

	private static final String TRIMENU_MVC_BAS_RETURN = "/cadastro/trimenu_mvc_bas";

	private static final String UNIMED_MVC_BAS_RETURN = "/cadastro/unimed_mvc_bas";

	private static final String MARCA_MVC_BAS_RETURN = "/cadastro/marca_mvc_bas";

	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant TWENTY. */
	private static final int TWENTY = 20;

	/**
	 * Fetch proceduresby request bas.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/fetcMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetcMenusByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(MENU_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchSubMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchSubMenusByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(SUBMENU_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchTriMenusByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchTriMenusByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(TRIMENU_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchUniMEdByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchUniMEdByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(UNIMED_MVC_BAS_RETURN);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchMarcasByRequestBAS", method = RequestMethod.GET)
	public ModelAndView fetchMarcasByRequestBAS()
	{
		ModelAndView modelAndView = new ModelAndView(MARCA_MVC_BAS_RETURN);
		return modelAndView;
	}
}
