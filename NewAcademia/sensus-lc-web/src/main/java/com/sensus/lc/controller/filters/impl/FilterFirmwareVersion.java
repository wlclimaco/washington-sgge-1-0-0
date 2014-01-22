package com.sensus.lc.controller.filters.impl;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterFirmwareVersion.
 */
@Component
public class FilterFirmwareVersion extends AbstractFilterBase
{
	/** The Constant FIRMWARE_VERSION. */
	private static final String FIRMWARE_VERSION = "FIRMWARE_VERSION";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return FIRMWARE_VERSION.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		filtersResponse.setFirmware_version(new FiltersModel(FILTER_TYPE_TEXT, getCommonTextInputs()));
	}

}
