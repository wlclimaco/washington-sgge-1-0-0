package com.prosperitasglobal.sendsolv.filter.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;

/**
 * The Class FilterTags.
 */
@Component
public class FilterTransaction extends AbstractFilterBase
{
	/** The Constant TAGS. */
	private static final String TRANSACTION = "TRANSACTION";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return TRANSACTION.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{

		filtersResponse.addFilter(TRANSACTION.toLowerCase(), new ArrayList<String>());
	}

}
