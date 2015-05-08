package com.prosperitasglobal.sendsolv.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prosperitasglobal.sendsolv.common.controller.BaseViewController;

// TODO: Auto-generated Javadoc
/**
 * The DashboardViewController class.
 *
 * @author Flavio Tosta
 *
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardViewController extends BaseViewController
{
	/** The URL mapping constants. */
	private static final String FETCH = "";

	/** The view mapping constants . */
	private static final String VIEW_DASHBOARD_MAIN = "/dashboard/dashboard_main";

	/**
	 * Load dashboard.
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.GET)
	public ModelAndView loadDashBoard(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_DASHBOARD_MAIN);
		isInitialLoad(request, modelAndView);

		return modelAndView;
	}

}
