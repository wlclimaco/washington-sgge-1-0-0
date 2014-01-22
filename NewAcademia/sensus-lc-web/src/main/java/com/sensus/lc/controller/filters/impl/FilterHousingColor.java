package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterHousingColor.
 */
@Component
public class FilterHousingColor extends AbstractFilterBase
{
	/** The Constant HOUSING_COLOR. */
	private static final String HOUSING_COLOR = "HOUSING_COLOR";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return HOUSING_COLOR.equals(filter);
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

		records.put("GRAY", getText("smartpoint.filter.gray", locale));
		records.put("BLACK", getText("smartpoint.filter.black", locale));
		records.put("SILVER", getText("smartpoint.filter.silver", locale));
		records.put("BRONZE", getText("smartpoint.filter.bronze", locale));
		records.put("WHITE", getText("smartpoint.filter.white", locale));
		records.put("OFF-WHITE_CREAM", getText("smartpoint.filter.cream", locale));
		records.put("GREEN", getText("smartpoint.filter.green", locale));
		records.put("ALUMINUM", getText("smartpoint.filter.aluminum", locale));

		filtersResponse.setHousing_color(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
