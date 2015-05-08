package com.prosperitasglobal.sendsolv.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

/**
 * The Class BaseViewController.
 *
 * @author Flavio Tosta
 */
public class BaseViewController extends BaseController
{

	/** The Constant REFRESH. */
	private static final String REFRESH = "refresh";

	/** The Constant INITIAL_LOAD. */
	public static final String INITIAL_LOAD = "initialLoad";

	/** The Constant FALSE. */
	private static final String FALSE = "false";

	/**
	 * Checks for initial load.
	 *
	 * @param request the request
	 * @param modelAndView the model and view
	 * @return the boolean
	 */
	public Boolean isInitialLoad(HttpServletRequest request, ModelAndView modelAndView)
	{
		if (FALSE.equals(request.getParameter(INITIAL_LOAD)))
		{
			modelAndView.addObject(REFRESH, REFRESH);

			return false;
		}

		return true;
	}
}