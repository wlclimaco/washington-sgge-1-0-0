package com.sensus.lc.light.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Interface ILightCustomSearchDAC.
 */
public interface ILightCustomSearchDAC
{

	/**
	 * Insert custom search.
	 *
	 * @param customSearch the custom search
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request);

	/**
	 * Delete custom search.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse deleteCustomSearch(CustomSearchRequest request);

	/**
	 * Fetch all custom search.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> fetchAllCustomSearch(LightRequest lightRequest);

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

}
