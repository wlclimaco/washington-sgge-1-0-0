package com.sensus.mlc.wui.dashboard;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/**
 * The Class DashBoardSearchAjaxAction is called when table is rendering.
 * 
 * @author Lucas Oliveira
 */

@Controller
@RequestMapping("/api/dashboard")
public class DashBoardAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	private static final String MAP_FETCH = "/fetch";

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant TABLE. */
	private static final String TABLE = "table";

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant HEADER. */
	private static final String HEADER = "header";

	/** The Constant CONTROL. */
	private static final String CONTROL = "control";

	/** The Constant CHART. */
	private static final String CHART = "chart";

	/** The Constant END_DATE. */
	private static final String END_DATE = "endDate";

	/** The Constant INITIAL_DATE. */
	private static final String INITIAL_DATE = "initialDate";

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "yyyy-mm-dd";

	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "DashboardAPIController";

	/**
	 * Sets the analytics bcf.
	 * 
	 * @param analyticsBCF
	 *            the analyticsBCF to set
	 */
	@Resource
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
	}

	/**
	 * Gets the analytics bcf.
	 * 
	 * @return the analyticsBCF
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return this.analyticsBCF;
	}

	@Override
	public void setMessageSource(MessageSource arg0)
	{
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(
			@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{

		AnalyticsResponse analytcsResp = new AnalyticsResponse();
		try
		{

			AnalyticsRequest analyticsRequest = new AnalyticsRequest();

			setUserContext(analyticsRequest, request);

			Date endDate = (Date)SensusConvertUtil.toDate(String.valueOf(jsonRequest.get(END_DATE)), DATE_FORMAT);
			analyticsRequest.setEndDate(endDate);

			Date initialDate =
					(Date)SensusConvertUtil.toDate(String.valueOf(jsonRequest.get(INITIAL_DATE)), DATE_FORMAT);
			analyticsRequest.setInitialDate(initialDate);

			switch (jsonRequest.get(ACTION).toString())
			{

				case TABLE:

					analyticsRequest.setStatusExceptionTypeEnum(StatusExceptionTypeEnum.valueOf(String
							.valueOf(jsonRequest.get(TYPE))));

					analytcsResp = getAnalyticsBCF()
							.fetchAnalyticsAlarmsByStatusId(analyticsRequest);

					break;

				case HEADER:

					analytcsResp = getAnalyticsBCF().fetchDashboardHeader(
							analyticsRequest);

					break;

				case CONTROL:

					analytcsResp = getAnalyticsBCF().fetchDashboardResume(
							analyticsRequest);

					break;

				case CHART:

					analytcsResp = getAnalyticsBCF().fetchAnalyticsAlertsByType(
							analyticsRequest);

					break;
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, analytcsResp, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return analytcsResp;

	}

}
