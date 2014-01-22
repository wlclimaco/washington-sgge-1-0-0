package com.sensus.lc.controller.filters.impl;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterMeterFirmware.
 */
@Component
public class FilterMeterFirmware extends AbstractFilterBase
{
	/** The Constant METER_FIRMWARE. */
	private static final String METER_FIRMWARE = "METER_FIRMWARE";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return METER_FIRMWARE.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		filtersResponse.setMeter_firmware(new FiltersModel(FILTER_TYPE_TEXT,
				getCommonTextInput("commons.pages.meterFirmware")));
	}

}
