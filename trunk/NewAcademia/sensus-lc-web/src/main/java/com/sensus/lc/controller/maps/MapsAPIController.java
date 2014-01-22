package com.sensus.lc.controller.maps;

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
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.GeocodeLightInfoResponse;

/**
 * The Class MapsAPIController.
 */
@Controller
@RequestMapping("/api/maps")
public class MapsAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_FETCH_BOUNDS. */
	private static final String MAP_FETCH_BOUNDS = "/fetchbounds";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MapsAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "MapsAPIController";

	/** The light bcf. */
	private ILightBCF lightBCF;

	/**
	 * Gets the light bcf.
	 * 
	 * @return the light bcf
	 */
	public ILightBCF getLightBCF()
	{
		return lightBCF;
	}

	/**
	 * Sets the light bcf.
	 * 
	 * @param lightBCF the new light bcf
	 */
	@Resource
	public void setLightBCF(ILightBCF lightBCF)
	{
		this.lightBCF = lightBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the geocode light info response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public GeocodeLightInfoResponse fetch(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		GeocodeLightInfoResponse geocodeLightInfoResponse = new GeocodeLightInfoResponse();

		try
		{

			setUserContext(lightRequest, request);
			geocodeLightInfoResponse = getLightBCF().fetchAllToMapByRequest(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, geocodeLightInfoResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return geocodeLightInfoResponse;

	}

	/**
	 * Fetch.
	 * 
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the geocode light info response
	 */
	@RequestMapping(value = MAP_FETCH_BOUNDS, method = RequestMethod.POST)
	@ResponseBody
	public GeocodeLightInfoResponse fetchBounds(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		GeocodeLightInfoResponse geocodeLightInfoResponse = new GeocodeLightInfoResponse();

		try
		{

			setUserContext(lightRequest, request);
			geocodeLightInfoResponse = getLightBCF().fetchMapBoundsByRequest(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, geocodeLightInfoResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return geocodeLightInfoResponse;

	}

}
