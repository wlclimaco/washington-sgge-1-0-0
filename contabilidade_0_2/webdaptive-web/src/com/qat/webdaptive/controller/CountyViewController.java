package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;

@Controller
@RequestMapping("/county")
public class CountyViewController extends CountyBaseController
{
//	private static final String MOBILE = "mobile";
//	private static final String COUNTY_RESPONSE = "countyResponse";
//	private static final String COUNTY_MVC_RETURN = "/counties/counties_mvc";
//	private static final String COUNTY_MVC_M_RETURN = "/mobile/counties/counties_mvc.m";
//	private static final String COUNTY_MVC_BAI_RETURN = "/counties/counties_mvc_bai";
//	private static final String COUNTY_MVC_BAS_RETURN = "/counties/counties_mvc_bas";
//
//	/**
//	 * Handles and retrieves a JSP page containing the CountyResponse.
//	 *
//	 * @return the name of the JSP page & CountyResponse JSTL String
//	 */
//	@RequestMapping(value = "/fetchCounties", method = RequestMethod.GET)
//	public ModelAndView fetchAllCountiesMVC(@RequestParam String view)
//	{
//		// if empty send back full view
//		if (view == null)
//		{
//			return new ModelAndView(COUNTY_MVC_RETURN, COUNTY_RESPONSE, countyBEFetchAll(true, new FetchAllRequest()));
//		}
//
//		// if mobile send back mobile view
//		if (view.equalsIgnoreCase(MOBILE))
//		{
//			return new ModelAndView(COUNTY_MVC_M_RETURN, COUNTY_RESPONSE, countyBEFetchAll(true, new FetchAllRequest()));
//		}
//		// else anything else send back full view
//		else
//		{
//			return new ModelAndView(COUNTY_MVC_RETURN, COUNTY_RESPONSE, countyBEFetchAll(true, new FetchAllRequest()));
//		}
//	}
//
//	/**
//	 * Handles and retrieves a JSP page containing the CountyResponse.
//	 *
//	 * @return the name of the JSP page & CountyResponse JSON Object
//	 */
//	@RequestMapping(value = "/fetchAllCountiesBAI", method = RequestMethod.GET)
//	public ModelAndView fetchAllCountiesBAI()
//	{
//		return countyMAV(true, COUNTY_MVC_BAI_RETURN);
//	}
//
//	/**
//	 * Handles and retrieves a JSP page containing the CountyResponse.
//	 *
//	 * @return the name of the JSP page & CountyResponse JSON Object
//	 */
//	@RequestMapping(value = "/fetchAllCountiesBAS", method = RequestMethod.GET)
//	public ModelAndView fetchAllCountiesBAS()
//	{
//		return countyMAV(false, COUNTY_MVC_BAS_RETURN);
//	}
}
