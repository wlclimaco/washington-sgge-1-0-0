package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterConfiguration.
 */
@Component
public class FilterConfiguration extends AbstractFilterBase
{
	/** The Constant CONFIGURATION. */
	private static final String CONFIGURATION = "CONFIGURATION";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return CONFIGURATION.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		Map<String, Object> records = new HashMap<String, Object>();
		String locale = getUserSettings().getLanguage();

		records.put(TRUE.toString(), getText("smartpoint.filter.protected", locale));
		records.put(FALSE.toString(), getText("smartpoint.filter.listening", locale));

		filtersResponse.setConfiguration(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
