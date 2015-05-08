package com.prosperitasglobal.sendsolv.sdn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class SdnViewController.
 *
 * @author Anke Doerfel-Parker
 * */
@Controller
@RequestMapping("/sdn")
public class SdnViewController extends SdnBaseController
{

	private static final String SDN_MAIN_LIST = "/sdn/sdn_tabs";

	/** The Constant VIEW_RECIPIENT_VIEW. */
	private static final String VIEW_REPORTS_VIEW = "/reports/reports_view";

	/** The URL mapping constants. */
	private static final String FETCH_LIST = "";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW = "/view";

	/** The Constant FETCH_VIEW. */
	private static final String FETCH_VIEW_REPORTS = "/viewReports";

	/** The Constant VIEW_RECIPIENT_VIEW. */
	private static final String VIEW_SDN_VIEW = "/sdn/sdn_view";

	/** The Constant RECIPIENT_ID. */
	private static final String SDN_ID = "id";

	/**
	 * Load view.
	 *
	 * @param sdnId the sdn id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/{type}" + FETCH_VIEW, method = RequestMethod.GET)
	public ModelAndView loadSdnView(
			@RequestParam(value = SDN_ID, required = true) Integer id, @PathVariable("type") String type,
			HttpServletRequest request)
	{
		return sdnMAV(id, VIEW_SDN_VIEW, type, false, request);
	}

	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(SDN_MAIN_LIST);
		return modelAndView;
	}

	@RequestMapping(value = FETCH_VIEW_REPORTS, method = RequestMethod.GET)
	public ModelAndView loadReportsView(
			@RequestParam(value = "id", required = true) Integer id, String type,
			HttpServletRequest request)
	{
		return new ModelAndView(VIEW_REPORTS_VIEW);
	}
}
