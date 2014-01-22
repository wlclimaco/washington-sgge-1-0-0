package com.sensus.lc.controller.analytics;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcf.IAnalyticsBCF;
import com.sensus.lc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsRangeDateEnum;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.lc.controller.BaseViewController;

/**
 * The Class AnalyticsViewController.
 */
@Controller
@RequestMapping("/analytics")
public class AnalyticsViewController extends BaseViewController
{

	/** The Constant FETCH. */
	private static final String FETCH_LIST = "";

	/** The Constant VIEW_ANALYTICS_MAIN. */
	private static final String VIEW_ANALYTICS_MAIN = "/analytics/analytics_main";

	/** The Constant DATE_RANGE. */
	private static final String DATE_RANGE = "dt";

	/** The Constant END_DATE. */
	private static final String ANALYTICS_FILTER = "ty";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant RESPONSE_CHART. */
	private static final String RESPONSE_CHART = "response_chart";

	/** The Constant RESPONSE_TABLE. */
	private static final String RESPONSE_TABLE = "response_table";

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AnalyticsViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "AnalyticsViewController";

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

	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(@RequestParam(value = DATE_RANGE) String requestDateInterval,
			@RequestParam(value = ANALYTICS_FILTER) Integer requestAnalyticsFilter,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_ANALYTICS_MAIN);
		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryAnalyticsRequest inquiryAnalyticsRequest = new InquiryAnalyticsRequest(getUserContext(request));
		AnalyticsRequest analyticsRequest = new AnalyticsRequest(getUserContext(request));

		AnalyticsResponse analyticsResponse = new AnalyticsResponse();

		AnalyticsResponse analyticsResponseGroup = getAnalyticsBCF().fetchAllAnalyticsGroup(analyticsRequest);

		// Apply date utc to analytics request
		int diffTimezoneHour = getHourTimezoneByTenant(getUserContext(request));

		switch (requestAnalyticsFilter)
		{

			case 2:
				inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);
				analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);
				break;
			case 3:
				inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);
				analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);
				break;
			case 4:
				inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CONSUMPTION);
				analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CONSUMPTION);
				break;
			case 5:
				inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ECOMODE);
				analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ECOMODE);
				diffTimezoneHour = 0;
				break;
			case 6:
				inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS);
				analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS);
				diffTimezoneHour = 0;
				break;
			default:
				inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
				analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
				break;
		}

		AnalyticsGroup analyticsGroup = new AnalyticsGroup();
		analyticsGroup.setId(0);

		Calendar calendar = SensusDateUtil.truncateTime(Calendar.getInstance());
		calendar.add(Calendar.SECOND, -1);
		calendar.add(Calendar.HOUR, diffTimezoneHour);
		Date endDate = calendar.getTime();

		// Verify request date interval
		switch (requestDateInterval)
		{

			case "1d":
				calendar.add(Calendar.DAY_OF_WEEK, 1);
				endDate = calendar.getTime();
				calendar.add(Calendar.DAY_OF_WEEK, -1);
				inquiryAnalyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
				analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
				break;
			case "1m":
				calendar.add(Calendar.MONTH, -1);
				inquiryAnalyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_MONTH);
				analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_MONTH);
				break;
			case "0":
				inquiryAnalyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
				analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				if (AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS.equals(inquiryAnalyticsRequest.getAnalyticsTypeEnum()))
				{
					calendar.add(Calendar.DAY_OF_WEEK, -1);
				}
				break;
			default:
				calendar.add(Calendar.DAY_OF_WEEK, -7);
				inquiryAnalyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);
				analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_WEEK);
				analyticsRequest.setAnalyticsRangeDateEnum(AnalyticsRangeDateEnum.WEEK);
				inquiryAnalyticsRequest.setAnalyticsRangeDateEnumEnum(AnalyticsRangeDateEnum.WEEK);
				break;

		}

		calendar.add(Calendar.SECOND, 1);
		Date initialDate = calendar.getTime();

		inquiryAnalyticsRequest.setInitialDate(initialDate);
		inquiryAnalyticsRequest.setEndDate(endDate);
		inquiryAnalyticsRequest.setGroup(analyticsGroup);
		InquiryAnalyticsResponse inquiryAnalyticsResponseTable = new InquiryAnalyticsResponse();
		inquiryAnalyticsRequest.setEndRow(0);
		inquiryAnalyticsRequest.setPageSize(0);

		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			inquiryAnalyticsRequest.setPageSize(pageSize);
		}

		inquiryAnalyticsRequest.setAction(INQUIRY_ACTION_TABLE);
		inquiryAnalyticsRequest.addSortExpressions(new SortExpression("TOTAL", Direction.Descending));
		inquiryAnalyticsResponseTable = getAnalyticsBCF().fetchAllAnalyticsByGroup(inquiryAnalyticsRequest);

		analyticsRequest.setInitialDate(initialDate);
		analyticsRequest.setEndDate(endDate);
		if (!ValidationUtil.isNullOrEmpty(analyticsResponseGroup.getAnalyticsGroups()))
		{
			analyticsRequest.setGroup(analyticsResponseGroup.getAnalyticsGroups().get(0));
		}
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(analyticsResponseGroup));
			modelAndView.addObject(RESPONSE_TABLE, getMapper().writeValueAsString(inquiryAnalyticsResponseTable));
			modelAndView.addObject(RESPONSE_CHART, getMapper().writeValueAsString(analyticsResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}
}
