package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * The Class FilterAddress.
 */
@Component
public class FilterAddress extends AbstractFilterBase
{
	/** The Constant ADDRESS. */
	private static final String ADDRESS = "ADDRESS";

	/** The Constant RECORD_2. */
	private static final String RECORD_2 = "2";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return ADDRESS.equals(filter);
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

		records.put(RECORD_0, getText("commons.pages.street", locale));
		records.put(RECORD_1, getText("commons.pages.city", locale));
		records.put(RECORD_2, getText("commons.pages.zip", locale));

		filtersResponse.setAddress(new FiltersModel(FILTER_TYPE_TEXT, records));

	}

}
