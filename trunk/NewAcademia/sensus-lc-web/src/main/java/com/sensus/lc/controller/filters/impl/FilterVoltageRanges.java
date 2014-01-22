package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterVoltageRanges.
 */
@Component
public class FilterVoltageRanges extends AbstractFilterBase
{
	/** The Constant VOLTAGE_RANGE. */
	private static final String VOLTAGE_RANGE = "VOLTAGE_RANGE";

	/** The Constant VOLTAGE_RANGE_90_300V. */
	private static final String VOLTAGE_RANGE_90_300V = "90-300V";

	/** The Constant VOLTAGE_RANGE_150_300V. */
	private static final String VOLTAGE_RANGE_150_300V = "150-300V";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return VOLTAGE_RANGE.equals(filter);
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

		records = new HashMap<String, Object>();
		records.put(VOLTAGE_RANGE_150_300V, VOLTAGE_RANGE_150_300V);
		records.put(VOLTAGE_RANGE_90_300V, VOLTAGE_RANGE_90_300V);
		filtersResponse.setVoltage_range(new FiltersModel(FILTER_TYPE_CHECKBOX, records));

	}

}
