package com.prosperitasglobal.sendsolv.filter.controller.delegate;

import java.util.List;

import com.prosperitasglobal.sendsolv.filter.FilterFactory;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;

/**
 *
 * The Class BaseFilterController.
 */

public final class FilterControllerD
{
	/**
	 * Builds the filters response.
	 *
	 * @param userContext the user context
	 * @param filters the filters
	 * @param filterFactory the filter factory
	 * @return the filters response
	 */
	public static FiltersResponse buildFiltersResponse(UserContext userContext, List<String> filtersList,
			FilterFactory filterFactory)
	{
		FiltersResponse filtersResponse = new FiltersResponse();

		for (String filter : filtersList)
		{
			filterFactory.configureFilter(filter, userContext, filtersResponse);
		}

		filtersResponse.setFiltersList(filtersList);

		return filtersResponse;
	}
}