package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.model.LifeCycleStateEnum;

/**
 * The Class FilterStatus.
 */
@Component
public class FilterLifeCycleState extends AbstractFilterBase
{
	/** The Constant STATUS. */
	private static final String LIFECYCLE_STATE = "LIFECYCLE_STATE";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return LIFECYCLE_STATE.equals(filter);
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

		records.put(LifeCycleStateEnum.ACTIVE.getValue().toString(),
				getText("smartpoint.status.filter.active", locale));
		records.put(LifeCycleStateEnum.DEACTIVATED.getValue().toString(),
				getText("smartpoint.status.filter.deactivated", locale));
		records.put(LifeCycleStateEnum.MAINTENANCE.getValue().toString(),
				getText("smartpoint.status.filter.maintenance", locale));

		filtersResponse.setLifecycle_state(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
