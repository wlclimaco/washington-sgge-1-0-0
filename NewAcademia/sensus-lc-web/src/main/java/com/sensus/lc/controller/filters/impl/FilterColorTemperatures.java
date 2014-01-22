package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterColorTemperatures.
 */
@Component
public class FilterColorTemperatures extends AbstractFilterBase
{
	/** The Constant COLOR_TEMPERATURE. */
	private static final String COLOR_TEMPERATURE = "COLOR_TEMPERATURE";

	/** The Constant COLOR_TEMPERATURE_3K. */
	private static final String COLOR_TEMPERATURE_3K = "3,000K";

	/** The Constant COLOR_TEMPERATURE_4K. */
	private static final String COLOR_TEMPERATURE_4K = "4,000K";

	/** The Constant COLOR_TEMPERATURE_5K. */
	private static final String COLOR_TEMPERATURE_5K = "5,000K";

	/** The Constant COLOR_TEMPERATURE_6K. */
	private static final String COLOR_TEMPERATURE_6K = "6,000K";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return COLOR_TEMPERATURE.equals(filter);
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

		records.put(COLOR_TEMPERATURE_3K, COLOR_TEMPERATURE_3K);
		records.put(COLOR_TEMPERATURE_4K, COLOR_TEMPERATURE_4K);
		records.put(COLOR_TEMPERATURE_5K, COLOR_TEMPERATURE_5K);
		records.put(COLOR_TEMPERATURE_6K, COLOR_TEMPERATURE_6K);

		filtersResponse.setColor_temperature(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
