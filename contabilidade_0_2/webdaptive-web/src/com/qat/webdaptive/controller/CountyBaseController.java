package com.qat.webdaptive.controller;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.bas.ICountyBAS;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.webdaptive.bai.ICountyBAI;

/**
 * The Class CountyBaseController.
 */
public class CountyBaseController
{

//	/** The Constant LOG. */
//	private static final Logger LOG = LoggerFactory.getLogger(CountyBaseController.class);
//
//	/** The Constant DEFAULT_EXCEPTION_MSG. */
//	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.county.defaultexception";
//
//	/** The Constant COUNTY_RESPONSE. */
//	private static final String COUNTY_RESPONSE = "countyResponse";
//
//	/** The county bai. */
//	private ICountyBAI countyBAI; // injected by @Resource
//
//	/**
//	 * Gets the county bai.
//	 * 
//	 * @return the county bai
//	 */
//	public ICountyBAI getCountyBAI()
//	{
//		return countyBAI;
//	}
//
//	/**
//	 * Sets the county bai.
//	 * 
//	 * @param countyBAI the new county bai
//	 */
//	@Resource
//	public void setCountyBAI(ICountyBAI countyBAI)
//	{
//		this.countyBAI = countyBAI;
//	}
//
//	/**
//	 * County mav.
//	 * 
//	 * @param useBAI the use bai
//	 * @param returnViewName the return view name
//	 * @return the model and view
//	 */
//	protected ModelAndView countyMAV(boolean useBAI, String returnViewName)
//	{
//		ModelAndView modelAndView = new ModelAndView(returnViewName);
//		FetchAllRequest request = new FetchAllRequest();
//		ObjectMapper mapper = new ObjectMapper();
//		try
//		{
//			modelAndView.addObject(COUNTY_RESPONSE, mapper.writeValueAsString(countyBEFetchAll(useBAI, request)));
//		}
//		catch (Exception ex)
//		{
//			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
//			modelAndView.addObject(COUNTY_RESPONSE, null);
//		}
//		return modelAndView;
//	}
//
//	/**
//	 * County be fetch all.
//	 * 
//	 * @param useBAI the use bai
//	 * @param request the request
//	 * @return the county response
//	 */
//	protected CountyResponse countyBEFetchAll(boolean useBAI, FetchAllRequest request)
//	{
//		CountyResponse response = new CountyResponse();
//		try
//		{
//			if (useBAI)
//			{
//				response = getCountyBAI().fetchAllCounties(request);
//			}
//			else
//			{
//				ICountyBAS client = (ICountyBAS)QATAppContext.getBean("countyBASClientTarget");
//				response = client.fetchAllCounties(request);
//			}
//
//		}
//		catch (Exception ex)
//		{
//			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
//		}
//		return response;
//	}
//
//	/**
//	 * County be refresh.
//	 * 
//	 * @param useBAI the use bai
//	 * @param request the request
//	 * @return the county response
//	 */
//	protected CountyResponse countyBERefresh(boolean useBAI, RefreshRequest request)
//	{
//		CountyResponse response = new CountyResponse();
//		try
//		{
//			if (useBAI)
//			{
//				response = getCountyBAI().refreshCounties(request);
//			}
//			else
//			{
//				ICountyBAS client = (ICountyBAS)QATAppContext.getBean("countyBASClientTarget");
//				response = client.refreshCounties(request);
//			}
//		}
//		catch (Exception ex)
//		{
//			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
//		}
//		return response;
//	}

}
