package com.sensus.lc.light.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

/**
 * The Interface ILightColumnFilterDAC.
 */
public interface ILightColumnFilterDAC
{

	/**
	 * Insert columns to custom search.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert filters to custom search.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest);

	/**
	 * Fetch all column filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal results response
	 */
	InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Fetch light filters.
	 *
	 * @param lightFilterRequest the light filter request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightFilterValue> fetchLightFilters(LightFilterRequest lightFilterRequest);

}
