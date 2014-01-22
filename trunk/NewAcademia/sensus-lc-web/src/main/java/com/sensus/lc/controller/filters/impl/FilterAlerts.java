package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.OverrideEnum;

/**
 * The Class FilterStatus.
 */
@Component
public class FilterAlerts extends AbstractFilterBase
{
	/** The Constant STATUS. */
	private static final String ALERTS = "ALERTS";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return ALERTS.equals(filter);
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

		records.put(AlertTypeEnum.ALARM.getValue().toString(),
				getText("smartpoint.status.filter.error", locale));
		records.put(OverrideEnum.ALL.getValue().toString(),
				getText("smartpoint.status.filter.override", locale));
		records.put(AlertTypeEnum.WARNING.getValue().toString(),
				getText("smartpoint.status.filter.warning", locale));

		filtersResponse.setAlerts(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
