package com.sensus.lc.controller.filters.impl;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterInstallDate.
 */
@Component
public class FilterInstallDate extends AbstractFilterBase
{
	/** The Constant INSTALL_DATE. */
	private static final String INSTALL_DATE = "INSTALL_DATE";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return INSTALL_DATE.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		filtersResponse.setInstall_date(new FiltersModel(FILTER_TYPE_TEXT, getCommonTextInputs()));
	}

}
