package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.PropertyEnum;

/**
 * The Class FilterSearch.
 */
@Component
public class FilterSearch extends AbstractFilterBase
{
	/** The Constant SEARCH. */
	private static final String SEARCH = "SEARCH";

	/** The Constant PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_EVENT_ID. */
	private static final String PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_EVENT_ID =
			"process.page.filter.lightpropertysearch.eventID";

	/** The Constant PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_NAME. */
	private static final String PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_NAME =
			"process.page.filter.lightpropertysearch.actionName";

	/** The Constant PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_ID. */
	private static final String PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_ID =
			"process.page.filter.lightpropertysearch.actionID";

	/** The Constant SMARTPOINT_SEARCH_FLEX_NET_ID. */
	private static final String SMARTPOINT_SEARCH_FLEX_NET_ID = "smartpoint.search.flexNetID";

	/** The Constant SMARTPOINT_SEARCH_POLE_ID. */
	private static final String SMARTPOINT_SEARCH_POLE_ID = "smartpoint.search.poleID";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return SEARCH.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		String page = new String("");
		if (addInformations.length > 0)
		{
			page = (String)addInformations[0];
		}

		Map<String, Object> records = new HashMap<String, Object>();
		String locale = getUserSettings().getLanguage();

		switch (page)
		{
			case "light":
				records.put(PropertyEnum.POLE_ID.getValue().toString(),
						getText(SMARTPOINT_SEARCH_POLE_ID, locale));
				records.put(PropertyEnum.FLEXNET_ID.getValue().toString(),
						getText(SMARTPOINT_SEARCH_FLEX_NET_ID, locale));

				break;

			case "lightDetail/history":

				records.put(
						LightPropertyForSearchEnum.ACTION_ID.getValue().toString(),
						getText(PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_ID, locale));
				records.put(
						LightPropertyForSearchEnum.ACTION_NAME.getValue().toString(),
						getText(PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_NAME,
								locale));

				break;

			case "systemintelligence/process":
				records.put(LightPropertyForSearchEnum.POLE_ID.getValue().toString(),
						getText(SMARTPOINT_SEARCH_POLE_ID, locale));
				records.put(LightPropertyForSearchEnum.EVENT_ID.getValue().toString(),
						getText(PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_EVENT_ID, locale));
				records.put(LightPropertyForSearchEnum.RNI_ID.getValue().toString(),
						getText(SMARTPOINT_SEARCH_FLEX_NET_ID, locale));

				break;

			default:

				records.put(PropertyEnum.POLE_ID.getValue().toString(),
						getText(SMARTPOINT_SEARCH_POLE_ID, locale));
				records.put(PropertyEnum.FLEXNET_ID.getValue().toString(),
						getText(SMARTPOINT_SEARCH_FLEX_NET_ID, locale));
				records.put(LightPropertyForSearchEnum.EVENT_ID.getValue().toString(),
						getText(PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_EVENT_ID, locale));
				records.put(
						LightPropertyForSearchEnum.ACTION_ID.getValue().toString(),
						getText(PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_ID, locale));
				records.put(
						LightPropertyForSearchEnum.ACTION_NAME.getValue().toString(),
						getText(PROCESS_PAGE_FILTER_LIGHTPROPERTYSEARCH_ACTION_NAME,
								locale));
				break;
		}

		filtersResponse.setSearch(new FiltersModel("search", records));
	}

}
