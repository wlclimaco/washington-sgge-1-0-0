package com.qat.webdaptive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

/**
 * The Class CountyAPIController.
 */
@Controller
@RequestMapping("/county/api")
public class CountyAPIController extends CountyBaseController
{

	/**
	 * Refresh bai.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	@RequestMapping(value = "/refreshBAI", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse refreshBAI(@RequestBody RefreshRequest request)
	{
		return countyBERefresh(true, request);
	}

	/**
	 * Fetch all bai.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	@RequestMapping(value = "/fetchAllBAI", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse fetchAllBAI(@RequestBody FetchAllRequest request)
	{
		return countyBEFetchAll(true, request);
	}

	/**
	 * Refresh bas.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	@RequestMapping(value = "/refreshBAS", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse refreshBAS(@RequestBody RefreshRequest request)
	{
		return countyBERefresh(false, request);
	}

	/**
	 * Fetch all bas.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	@RequestMapping(value = "/fetchAllBAS", method = RequestMethod.POST)
	@ResponseBody
	public CountyResponse fetchAllBAS(@RequestBody FetchAllRequest request)
	{
		return countyBEFetchAll(false, request);
	}
}
