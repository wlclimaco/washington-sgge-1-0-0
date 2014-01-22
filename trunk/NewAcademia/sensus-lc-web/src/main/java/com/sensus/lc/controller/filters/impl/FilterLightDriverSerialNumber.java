package com.sensus.lc.controller.filters.impl;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterLightDriverSerialNumber.
 */
@Component
public class FilterLightDriverSerialNumber extends AbstractFilterBase
{
	/** The Constant LIGHT_DRIVER_SERIAL_NUMBER. */
	private static final String LIGHT_DRIVER_SERIAL_NUMBER = "LIGHT_DRIVER_SERIAL_NUMBER";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return LIGHT_DRIVER_SERIAL_NUMBER.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		filtersResponse.setLight_driver_serial_number(new FiltersModel(FILTER_TYPE_TEXT,
				getCommonTextInput("widgets.customize.filter.lightdriverserialnumber")));
	}

}
