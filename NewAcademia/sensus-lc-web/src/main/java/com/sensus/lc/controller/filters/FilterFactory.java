package com.sensus.lc.controller.filters;

import java.util.List;

import javax.annotation.Resource;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.model.FiltersResponse;

/**
 * A factory for creating Filter objects.
 */
public class FilterFactory
{

	/** The filters. */
	@Resource
	private List<IFilter> filters;

	/**
	 * Gets the filters.
	 * 
	 * @return the filters
	 */
	public List<IFilter> getFilters()
	{
		return filters;
	}

	/**
	 * Sets the filters.
	 * 
	 * @param filters the new filters
	 */
	public void setFilters(List<IFilter> filters)
	{
		this.filters = filters;
	}

	/**
	 * Configure filter.
	 * 
	 * @param filter the filter
	 * @param userContext the user context
	 * @param filtersResponse the filters response
	 * @param addInformations the add informations
	 */
	public void configureFilter(String filter,
			UserContext userContext,
			FiltersResponse filtersResponse,
			Object... addInformations)
	{
		if (ValidationUtil.isNullOrEmpty(getFilters()))
		{
			return;
		}

		for (IFilter f : getFilters())
		{
			if (f.isAssignableFrom(filter))
			{
				f.createFilter(userContext, filtersResponse, addInformations);
			}
		}
	}
}
