package com.sensus.lc.controller.dashboard;

import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.UserContext;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.lc.analytics.bcf.IAnalyticsBCF;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.light.model.AlertSubTypeEnum;

/**
 * The Class DashBoardViewController.
 */
/**
 * @author Alex Tiveron
 * 
 */
@Controller
@RequestMapping("/dashboard")
public class DashBoardViewController extends BaseViewController
{

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant VIEW_DASHBOARD_MAIN. */
	private static final String VIEW_DASHBOARD_MAIN = "/dashboard/dashboard_main";

	/** The Constant DASHBOARD. */
	private static final String DASHBOARD = "dashboard";

	/** The Constant POWER_FAILURE. */
	private static final String POWER_FAILURE = "powerFailureResponse";

	/** The Constant LAMP_FAILURE. */
	private static final String LAMP_FAILURE = "lampFailureResponse";

	/** The Constant HEADER_RESPONSE. */
	private static final String HEADER_RESPONSE = "dashBoardHeaderResponse";

	/** The Constant HEADER_RESPONSE. */
	private static final String ALERTS_RESPONSE = "alertsResponse";

	/** The Constant RESUME_RESPONSE. */
	private static final String RESUME_RESPONSE = "resumeResponse";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DashBoardViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "DashBoardViewController";

	/** The group bcf. */
	private IAnalyticsBCF analyticsBCF;

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
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		UserContext userContext = getUserContext(request);
		ModelAndView modelAndView = new ModelAndView(VIEW_DASHBOARD_MAIN);
		AnalyticsRequest analyticsRequest = new AnalyticsRequest(userContext);

		AnalyticsResponse analyticsRequestPowerFailureResponse = new AnalyticsResponse();
		AnalyticsResponse analyticsRequestLampFailureResponse = new AnalyticsResponse();
		AnalyticsResponse analyticsRequestDashBoardHeaderResponse = new AnalyticsResponse();
		AnalyticsResponse analyticsRequestAlertsResponse = new AnalyticsResponse();
		AnalyticsResponse analyticsRequestDashboardResumeResponse = new AnalyticsResponse();

		analyticsRequest.setAlertSubtype(AlertSubTypeEnum.POWER_FAILURE);
		analyticsRequestPowerFailureResponse = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);

		analyticsRequest.setAlertSubtype(AlertSubTypeEnum.LAMP_FAILURE);
		analyticsRequestLampFailureResponse = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);

		analyticsRequestDashBoardHeaderResponse = getAnalyticsBCF().fetchDashboardHeader(analyticsRequest);
		analyticsRequestAlertsResponse = getAnalyticsBCF().fetchAnalyticsAlertsByType(analyticsRequest);

		int diffTimezoneHour = getHourTimezoneByTenant(userContext);
		Calendar initialCalendar = SensusDateUtil.truncateTime(Calendar.getInstance());
		initialCalendar.add(Calendar.HOUR, diffTimezoneHour);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(initialCalendar.getTime());
		endCalendar.add(Calendar.HOUR, 24);
		endCalendar.add(Calendar.MILLISECOND, -1);

		analyticsRequest.setInitialDate(initialCalendar.getTime());
		analyticsRequest.setEndDate(endCalendar.getTime());

		analyticsRequestDashboardResumeResponse = getAnalyticsBCF().fetchDashboardResume(analyticsRequest);

		try
		{
			modelAndView.addObject(RESUME_RESPONSE,
					getMapper().writeValueAsString(analyticsRequestDashboardResumeResponse));
			modelAndView.addObject(ALERTS_RESPONSE, getMapper().writeValueAsString(analyticsRequestAlertsResponse));
			modelAndView.addObject(HEADER_RESPONSE,
					getMapper().writeValueAsString(analyticsRequestDashBoardHeaderResponse));
			modelAndView.addObject(LAMP_FAILURE, getMapper().writeValueAsString(analyticsRequestLampFailureResponse));
			modelAndView.addObject(POWER_FAILURE, getMapper().writeValueAsString(analyticsRequestPowerFailureResponse));

		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(DASHBOARD, null);
		}
		return modelAndView;
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
}
