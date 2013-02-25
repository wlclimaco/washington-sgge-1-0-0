package com.sensus.mlc.wui.analytics;

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
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/**
 * The Class GroupAPIController.
 */
@Controller
@RequestMapping("/api/analytics")
public class AnalyticsAPIController extends BaseController
{
	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "AnalyticsAPIController";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant FETCH_CHART. */
	private static final String FETCH_CHART = "/fetchChart";

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/**
	 * Gets the analytics bcf.
	 *
	 * @return the analytics bcf
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return analyticsBCF;
	}

	/**
	 * Sets the analytics bcf.
	 *
	 * @param analyticsBCF the new analytics bcf
	 */
	@Resource
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
	}

	/**
	 * Fetch.
	 *
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @param request the request
	 * @return the inquiry analytics response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public InquiryAnalyticsResponse fetch(
			@RequestBody InquiryAnalyticsRequest inquiryAnalyticsRequest,
			HttpServletRequest request)
	{

		InquiryAnalyticsResponse inquiryAnalyticsResponse = new InquiryAnalyticsResponse();

		try
		{
			// ADD user context to request
			setUserContext(inquiryAnalyticsRequest, request);

			inquiryAnalyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByGroup(inquiryAnalyticsRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryAnalyticsResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryAnalyticsResponse;
	}

	/**
	 * Fetch chart.
	 *
	 * @param analyticsRequest the analytics request
	 * @param request the request
	 * @return the analytics response
	 */
	@RequestMapping(value = FETCH_CHART, method = RequestMethod.POST)
	@ResponseBody
	public AnalyticsResponse fetchChart(
			@RequestBody AnalyticsRequest analyticsRequest,
			HttpServletRequest request)
	{

		AnalyticsResponse analyticsResponse = new AnalyticsResponse();

		try
		{
			// ADD user context to request
			setUserContext(analyticsRequest, request);

			analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, analyticsResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return analyticsResponse;
	}

}
