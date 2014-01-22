package com.sensus.lc.controller.light;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.sensus.lc.base.model.ListTypeEnum;
import com.sensus.lc.controller.filters.BaseFilterController;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.LightOrderByEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.criteria.ScheduleCriteria;
import com.sensus.lc.light.model.criteria.SearchTerm;
import com.sensus.lc.light.model.criteria.TagCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.FetchAllResponse;

/**
 * The Class LightViewController.
 */
@Controller
@RequestMapping("/light")
public class LightViewController extends BaseFilterController
{

	/** The Constant SPLIT_PATTERN. */
	private static final String SPLIT_PATTERN = "|";

	/** The Constant LIGHT. */
	private static final String LIGHT = "light";

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightViewController";

	/** The Constant VIEW_LIGHT_MAIN. */
	private static final String VIEW_LIGHT_MAIN = "/light/light_main";

	/** The Constant FILTER_SEARCH. */
	private static final String FILTER_SEARCH = "SEARCH";

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The Constant SEARCH. */
	private static final String SEARCH = "sd";

	/** The smart point accessor bcf. */
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
	 * Load list.
	 * 
	 * @param processId the process id
	 * @param groupIds the group id
	 * @param eventSchedule the event schedule
	 * @param offsetSchedule the offset schedule
	 * @param tagIds the tag id
	 * @param request the request
	 * @return the model and view
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(@RequestParam(value = "processId", required = false) Integer processId,
			@RequestParam(value = "failed", required = false) Boolean failedProcesses,
			@RequestParam(value = "groups", required = false) String groupIds,
			@RequestParam(value = "event_schedule", required = false) String eventSchedule,
			@RequestParam(value = "offset_schedule", required = false) String offsetSchedule,
			@RequestParam(value = "tags", required = false) String tagIds,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "alerts", required = false) String alerts,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_LIGHT_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		LightRequest lightRequest = new LightRequest(getUserContext(request));

		LightCriteria lightCriteria = new LightCriteria();

		if (request.getParameterMap().size() < 2)
		{

			lightCriteria.setLifeCycleStateList(Arrays.asList(LifeCycleStateEnum.ACTIVE));

		}

		lightRequest.addSortExpressions(new SortExpression(LightOrderByEnum.ALERTS.getValue(),
				Direction.Ascending));

		// Handle external filters
		if (!ValidationUtil.isNullOrEmpty(query))
		{
			query = URLDecoder.decode(query);
			String[] queryItems = StringUtils.splitByWholeSeparator(query, SPLIT_PATTERN);

			if (Integer.parseInt(queryItems[0]) == PropertyEnum.FLEXNET_ID.getValue())
			{
				lightCriteria.setFlexnetId(new SearchTerm(new BigInteger(queryItems[1])));
			}
			else
			{
				lightCriteria.setPoleId(new SearchTerm(queryItems[1]));
			}
		}

		lightRequest.setLightCriteria(lightCriteria);

		if (!ValidationUtil.isNullOrEmpty(alerts))
		{
			AlertCriteria alertCriteria = new AlertCriteria();
			List<AlertTypeEnum> alertTypeList = new ArrayList<AlertTypeEnum>();
			for (Integer alert : splitFilters(alerts))
			{
				alertTypeList.add(AlertTypeEnum.enumForValue(alert));
			}
			alertCriteria.setAlertTypeList(alertTypeList);
			lightRequest.setAlertCriteria(alertCriteria);
		}

		if (!ValidationUtil.isNullOrZero(processId))
		{
			ProcessCriteria processCriteria = new ProcessCriteria();
			processCriteria.setProcessId(processId);
			if (!ValidationUtil.isNull(failedProcesses) && failedProcesses)
			{
				processCriteria.setFailed(failedProcesses);
			}
			lightRequest.setProcessCriteria(processCriteria);
		}

		if (!ValidationUtil.isNullOrEmpty(groupIds))
		{
			GroupCriteria groupCriteria = new GroupCriteria();
			groupCriteria.setGroupIdList(splitFilters(groupIds));
			lightRequest.setGroupCriteria(groupCriteria);
		}

		if (!ValidationUtil.isNullOrEmpty(eventSchedule))
		{
			ScheduleCriteria scheduleCriteria = new ScheduleCriteria();
			scheduleCriteria.setLightSchedule(splitFilters(eventSchedule));
			lightRequest.setScheduleCriteria(scheduleCriteria);
		}

		if (!ValidationUtil.isNullOrEmpty(offsetSchedule))
		{
			ScheduleCriteria scheduleCriteria = new ScheduleCriteria();
			scheduleCriteria.setLightSchedule(splitFilters(offsetSchedule));
			lightRequest.setScheduleCriteria(scheduleCriteria);
		}

		if (!ValidationUtil.isNullOrEmpty(tagIds))
		{
			TagCriteria tagCriteria = new TagCriteria();
			tagCriteria.setTagIdList(splitFilters(tagIds));
			lightRequest.setTagCriteria(tagCriteria);
		}

		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			lightRequest.setPageSize(pageSize);
		}

		// Check for URL search parameters

		FetchAllResponse fetchAllResponse = getLightBCF().fetchAllByRequest(lightRequest);

		// Filters
		FiltersResponse filtersResponse = new FiltersResponse();
		fetchCustomizeFilters(filtersResponse, request, ListTypeEnum.SMARTPOINTLIST);

		if (TRUE.equals(request.getParameter(SEARCH)) || !ValidationUtil.isNullOrEmpty(query))
		{
			getFilterFactory().configureFilter(FILTER_SEARCH, getUserContext(request), filtersResponse, LIGHT);
		}

		for (Iterator<Filter> iterator = filtersResponse.getFilters().iterator(); iterator.hasNext();)
		{
			getFilterFactory().configureFilter(String.valueOf(iterator.next().getFilterEnumValue()),
					getUserContext(request), filtersResponse);
		}

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(fetchAllResponse));
			modelAndView.addObject(FILTERS, getMapper().writeValueAsString(filtersResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Split filters.
	 * 
	 * @param filters the filters
	 * @return the list
	 */
	@SuppressWarnings("deprecation")
	private List<Integer> splitFilters(String filters)
	{
		filters = URLDecoder.decode(filters);
		if (!filters.contains(SPLIT_PATTERN))
		{
			return null;
		}

		String[] filterIds = StringUtils.splitByWholeSeparator(filters, SPLIT_PATTERN);
		List<Integer> filterIdsList = new ArrayList<Integer>();
		for (int i = 0; i < filterIds.length; i++)
		{
			if (!ValidationUtil.isNullOrEmpty(filterIds[i]))
			{
				filterIdsList.add(Integer.parseInt(filterIds[i]));
			}
		}

		return filterIdsList;

	}
}