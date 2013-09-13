package com.sensus.dm.controller.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class FilterAPIController.
 */
@Controller
@RequestMapping("/api/filter")
public class FilterAPIController extends BaseFilterController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FilterAPIController.class);

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/**
	 * Filter.
	 * 
	 * @param request the request
	 * @param servletRequest the servlet request
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> filter(@RequestBody Map<String, Object> request, HttpServletRequest servletRequest)
	{

		HashMap<String, Object> response = new HashMap<String, Object>();

		try
		{
			List<String> filters = (List<String>)request.get("filter");
			List<String> deviceTypeEnums = (List<String>)request.get("deviceType");
			List<String> deviceSubTypes = (List<String>)request.get("deviceSubTypes");

			for (String filter : filters)
			{

				// Fill the filter group
				if (filter.equals(GROUP))
				{
					createFilterGroup(response, deviceTypeEnums, deviceSubTypes);
				}

				// Fill the tag filter
				else if (filter.equals(TAG))
				{
					createFilterTag(response);
				}

				// Fill the user filter
				else if (filter.equals(USERS))
				{
					createFilterUser(response);
				}

				// Fill the LifeCycle filter
				else if (filter.equals(LIFECYCLE_STATE))
				{
					createFilterLifecycleState(response, servletRequest, deviceTypeEnums);
				}

				// Fill the Quarantine filter
				else if (filter.equals(QUARANTINE))
				{
					createFilterQuarantine(response, servletRequest);
				}

				// Fill the Alarms filter
				else if (filter.equals(ALARM))
				{
					createFilterAlarms(response, servletRequest, deviceTypeEnums);
				}

				// Fill the Remote Disconnect filter
				else if (filter.equals(REMOTE_DISCONNECT))
				{
					createFilterRemoteDisconnect(response, servletRequest);
				}

				// Fill the Group Type filter
				else if (filter.equals(GROUP_TYPE))
				{
					createFilterGroupType(response, servletRequest);
				}

				// Fill the Status Schedule filter
				else if (filter.equals(STATUS_SCHEDULED))
				{
					createFilterStatusScheduled(response, servletRequest);
				}

				// Fill the All Action Categories filter
				else if (filter.equals(ALL_ACTION_CATEGORIES))
				{
					createFilterAllActionCategories(response, servletRequest);
				}

				// Fill the Schedule Action Categories Filter
				else if (filter.equals(SCHEDULE_ACTION_CATEGORIES))
				{
					createFilterScheduleActionCategories(response, servletRequest);
				}

				// Fill the Device Type filter
				else if (filter.equals(DEVICE_SUBTYPE))
				{
					createFilterDeviceType(response, servletRequest, deviceTypeEnums);
				}

				// Fill the Repeats filter
				else if (filter.equals(REPEATS))
				{
					createFilterRepeats(response, servletRequest);
				}

				// Fill the Search filter
				else if (filter.equals(SEARCH))
				{
					createFilterSearch(response, servletRequest);
				}

				// Fill the Status filter
				else if (filter.equals(STATUS_METER))
				{
					createFilterStatusMeter(response, servletRequest);
				}

				// Fill the Description filter
				else if (filter.equals(DESCRIPTION))
				{
					createFilterElectricDescription(response, deviceTypeEnums, deviceSubTypes);
				}

				// Fill the Group Device type filter
				else if (filter.equals(GROUP_DEVICE_TYPE))
				{
					createFilterGroupDeviceType(response, servletRequest);
				}
			}
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error fetching filters", e);
			}
		}

		return response;
	}
}
