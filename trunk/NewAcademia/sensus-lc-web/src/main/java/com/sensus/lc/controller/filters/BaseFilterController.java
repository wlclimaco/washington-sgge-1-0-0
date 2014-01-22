package com.sensus.lc.controller.filters;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sensus.lc.base.model.ListTypeEnum;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.light.bcf.ILightCustomSearchBCF;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

/**
 * The Class BaseFilterController.
 */
public class BaseFilterController extends BaseViewController
{
	/** The Constant FILTERS. */
	public static final String FILTERS = "filters";

	/** The filter factory. */
	private FilterFactory filterFactory;

	/**
	 * Gets the filter factory.
	 * 
	 * @return the filter factory
	 */
	public FilterFactory getFilterFactory()
	{
		return filterFactory;
	}

	/**
	 * Sets the filter factory.
	 * 
	 * @param filterFactory the new filter factory
	 */
	@Resource
	public void setFilterFactory(FilterFactory filterFactory)
	{
		this.filterFactory = filterFactory;
	}

	/** The light custom search bcf. */
	private ILightCustomSearchBCF lightCustomSearchBCF;

	/**
	 * Gets the light custom search bcf.
	 * 
	 * @return the light custom search bcf
	 */
	public ILightCustomSearchBCF getLightCustomSearchBCF()
	{
		return lightCustomSearchBCF;
	}

	/**
	 * Sets the light custom search bcf.
	 * 
	 * @param lightCustomSearchBCF the new light custom search bcf
	 */
	@Resource
	public void setLightCustomSearchBCF(ILightCustomSearchBCF lightCustomSearchBCF)
	{
		this.lightCustomSearchBCF = lightCustomSearchBCF;
	}

	/**
	 * Fetch customize filters.
	 * 
	 * @param filtersResponse the filters response
	 * @param request the request
	 * @param listTypeEnum the list type enum
	 */
	public void fetchCustomizeFilters(FiltersResponse filtersResponse, HttpServletRequest request,
			ListTypeEnum listTypeEnum)
	{
		ColumnFilterRequest columnFiltersRequest = new ColumnFilterRequest();
		setUserContext(columnFiltersRequest, request);

		columnFiltersRequest.setListTypeEnum(listTypeEnum);

		ColumnFilterResponse columnFilterResponse =
				getLightCustomSearchBCF().fetchAllColumnFilters(columnFiltersRequest);

		filtersResponse.setAdditionalColumns(columnFilterResponse.getAdditionalColumns());
		filtersResponse.setAdditionalFilters(columnFilterResponse.getAdditionalFilters());
		filtersResponse.setFilters(columnFilterResponse.getFilters());
		filtersResponse.setListColumn(columnFilterResponse.getListColumn());
	}

}