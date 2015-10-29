package com.qat.webdaptive.controller.sendsolv;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.prosperitasglobal.sendsolv.filter.model.IFilter;
import com.prosperitasglobal.sendsolv.filter.model.response.FiltersResponse;
import com.qat.framework.model.UserContext;
import com.qat.framework.validation.ValidationUtil;

/**
 * A factory for creating Filter objects.
 */
@Component
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
