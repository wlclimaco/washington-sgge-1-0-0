package com.sensus.lc.controller.light;

import java.io.IOException;

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
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.filters.BaseFilterController;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.bcf.ILightNotificationHistoryBCF;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.response.LightHistoryResponse;
import com.sensus.lc.process.model.ProcessFilter;

/**
 * The Class LightDetailHistoryViewController.
 */
@Controller
@RequestMapping("/history")
public class LightDetailHistoryViewController extends BaseFilterController
{

	/** The Constant FILL_HISTORY. */
	public static final String FILL_HISTORY = "";

	/** The Constant VIEW_LIGHT_DETAIL_HISTORY. */
	private static final String VIEW_LIGHT_DETAIL_HISTORY = "/light/light_detail_history";

	/** The Constant ROLE_ROLE_ADMIN. */
	private static final String ROLE_ROLE_ADMIN = "ROLE_Role.Admin";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightDetailHistoryViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightDetailHistoryViewController";

	/** The Constant LIGHT_ID. */
	public static final String LIGHT_ID = "id";

	/** The Constant PROCESS. */
	private static final String LIGHT_DETAIL_HISTORY = "lightDetail/history";

	/** The Constant INQUIRY_ACTION_TABLE. */

	/** The Constant INQUIRY_DATE_PATTERN. */
	private static final String INQUIRY_DATE_PATTERN = "mm/dd/yy";

	/** The Constant SORT_START_DATETIME. */
	private static final String SORT_START_DATETIME = "START_DATETIME";

	/** The Constant FILTER_SEARCH. */
	private static final String FILTER_SEARCH = "SEARCH";

	/** The Constant FILTER_USERS. */
	private static final String FILTER_USERS = "USERS";

	/** The Constant FILTER_EVENT_TYPE. */
	private static final String FILTER_ACTION_TYPE = "ACTION_TYPE";

	/** The Constant FILTERS_EVENT. */
	private static final String FILTERS_EVENT = "FILTERS_EVENT";

	/** The light notification history bcf. */
	private ILightNotificationHistoryBCF lightNotificationHistoryBCF;

	/**
	 * Gets the light notification history bcf.
	 * 
	 * @return the light notification history bcf
	 */
	public ILightNotificationHistoryBCF getLightNotificationHistoryBCF()
	{
		return lightNotificationHistoryBCF;
	}

	/**
	 * Sets the light notification history bcf.
	 * 
	 * @param lightNotificationHistoryBCF the new light notification history bcf
	 */
	@Resource
	public void setLightNotificationHistoryBCF(ILightNotificationHistoryBCF lightNotificationHistoryBCF)
	{
		this.lightNotificationHistoryBCF = lightNotificationHistoryBCF;
	}

	/**
	 * Fill detail history.
	 * 
	 * @param lightId the light id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FILL_HISTORY, method = RequestMethod.GET)
	public ModelAndView fillDetailHistory(@RequestParam(value = LIGHT_ID, required = true) Integer lightId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_LIGHT_DETAIL_HISTORY);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		setUserContext(notificationHistoryRequest, request);

		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			notificationHistoryRequest.setPageSize(pageSize);
		}

		notificationHistoryRequest.addSortExpressions(new SortExpression(SORT_START_DATETIME, Direction.Descending));

		NotificationHistoryCriteria historyCriteria = new NotificationHistoryCriteria();
		historyCriteria.setLightId(lightId);
		historyCriteria.setProcessFilter(new ProcessFilter());

		notificationHistoryRequest.setNotificationHistoryCriteria(historyCriteria);

		LightHistoryResponse historyResponse =
				getLightNotificationHistoryBCF().fetchLightNotificationHistory(notificationHistoryRequest);

		// Filters
		FiltersResponse filtersResponse = new FiltersResponse();
		getFilterFactory().configureFilter(FILTER_SEARCH, getUserContext(request), filtersResponse,
				LIGHT_DETAIL_HISTORY);
		getFilterFactory().configureFilter(FILTERS_EVENT, getUserContext(request), filtersResponse);
		getFilterFactory().configureFilter(FILTER_ACTION_TYPE, getUserContext(request), filtersResponse);

		if (getUserContext(request).getUserRole().equals(ROLE_ROLE_ADMIN))
		{
			getFilterFactory().configureFilter(FILTER_USERS, getUserContext(request), filtersResponse);
		}

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(historyResponse));
			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}
}
