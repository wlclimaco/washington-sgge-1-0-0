package com.sensus.lc.controller.filters.impl;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

@Component
public class FilterUpperAssemblySerialNumber extends AbstractFilterBase
{
	/** The Constant UPPER_ASSEMBLY_SERIAL_NUMBER. */
	private static final String UPPER_ASSEMBLY_SERIAL_NUMBER = "UPPER_ASSEMBLY_SERIAL_NUMBER";

	@Override
	public boolean isAssignableFrom(String filter)
	{
		return UPPER_ASSEMBLY_SERIAL_NUMBER.equals(filter);
	}

	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		filtersResponse.setUpper_assembly_serial_number(new FiltersModel(FILTER_TYPE_TEXT,
				getCommonTextInput("widgets.customize.filter.upperassemblyserialnumber")));
	}

}
