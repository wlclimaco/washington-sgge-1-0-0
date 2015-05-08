package com.prosperitasglobal.sendsolv.filter.model;

import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;

/**
 * The Interface IFilter.
 */
public interface IFilter
{

	/**
	 * Checks if is assignable from.
	 *
	 * @param filter the filter
	 * @return true, if is assignable from
	 */
	boolean isAssignableFrom(String filter);

	/**
	 * Creates the filter.
	 *
	 * @param userContext the user context
	 * @param filtersResponse the filters response
	 * @param addInformations the add informations
	 */
	void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations);

}
