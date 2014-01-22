package com.sensus.lc.controller.search;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.light.bcf.ILightCustomSearchBCF;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CustomSearchResponse;

/**
 * The Class SavedSearchAPIController.
 */

@Controller
@RequestMapping("api/search")
public class SavedSearchAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant DELETE. */
	private static final String DELETE_SAVED_SEARCH = "/delete";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SavedSearchAPIController";

	/** The light custom search bcf. */
	private ILightCustomSearchBCF lightCustomSearchBCF;

	/**
	 * Gets the light custom search bcf.
	 * 
	 * @return the light custom search bcf
	 */
	public ILightCustomSearchBCF getLightCustomSearchBCF()
	{
		return lightCustomSearchBCF;
	}

	/**
	 * Sets the light custom search bcf.
	 * 
	 * @param lightCustomSearchBCF the new light custom search bcf
	 */
	@Resource
	public void setLightCustomSearchBCF(ILightCustomSearchBCF lightCustomSearchBCF)
	{
		this.lightCustomSearchBCF = lightCustomSearchBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public CustomSearchResponse fetch(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		CustomSearchResponse customSearchResponse = new CustomSearchResponse();

		try
		{
			setUserContext(lightRequest, request);

			customSearchResponse = getLightCustomSearchBCF().fetchAllCustomSearch(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, customSearchResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return customSearchResponse;

	}

	/**
	 * Delete.
	 * 
	 * @param customSearchRequest the custom search request
	 * @param request the request
	 * @return the custom search response
	 */
	@RequestMapping(value = DELETE_SAVED_SEARCH, method = RequestMethod.POST)
	@ResponseBody
	public CustomSearchResponse delete(
			@RequestBody CustomSearchRequest customSearchRequest,
			HttpServletRequest request)
	{

		CustomSearchResponse customSearchResponse = new CustomSearchResponse();

		try
		{
			// ADD user context to request
			setUserContext(customSearchRequest, request);

			customSearchResponse = getLightCustomSearchBCF().deleteCustomSearch(customSearchRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, customSearchResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return customSearchResponse;
	}

	/**
	 * Save search.
	 * 
	 * @param customSearchRequest the custom search request
	 * @param request the request
	 * @return the custom search response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public CustomSearchResponse saveSearch(@RequestBody CustomSearchRequest customSearchRequest,
			HttpServletRequest request)
	{
		CustomSearchResponse customSearchResponse = new CustomSearchResponse();
		try
		{
			// ADD user context to request
			setUserContext(customSearchRequest, request);

			customSearchResponse = getLightCustomSearchBCF().insertCustomSearch(customSearchRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, customSearchResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return customSearchResponse;
	}

}