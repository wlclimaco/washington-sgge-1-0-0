package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.ecomode.model.EcoModeFilterEnum;

/**
 * The Class FilterEcomode.
 */
@Component
public class FilterEcomode extends AbstractFilterBase
{
	/** The Constant ECOMODE. */
	private static final String ECOMODE = "ECOMODE";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return ECOMODE.equals(filter);
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

		records.put(EcoModeFilterEnum.ECONOMY.getValue().toString(),
				getText("smartpoint.filter.ecomodeEconomy", locale));
		records.put(EcoModeFilterEnum.VALUE.getValue().toString(),
				getText("smartpoint.filter.ecomodeValue", locale));
		records.put(EcoModeFilterEnum.SECURITY.getValue().toString(),
				getText("smartpoint.filter.ecomodeSecurity", locale));

		filtersResponse.setEcomode(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
