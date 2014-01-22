package com.sensus.lc.light.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;

/**
 * Contains methods ONLY related to working with Light Custom Search.
 * Do not update this interface without checking with Gustavo Peres
 */
public interface ILightCustomSearchBCL
{

	/**
	 * Fetch light filters.
	 *
	 * @param lightFilterRequest the light filter request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightFilterValue> fetchLightFilters(LightFilterRequest lightFilterRequest);

	/**
	 * Fetch all custom search.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> fetchAllCustomSearch(LightRequest lightRequest);

	/**
	 * Fetch all column filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal results response
	 */
	InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert custom search.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request);

	/**
	 * Insert column filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert columns filters to custom search.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertColumnsFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest);

	/**
	 * Delete custom search.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse deleteCustomSearch(CustomSearchRequest request);

	/**
	 * Verify custom search.
	 *
	 * @param search the search
	 * @return the search light
	 */
	SearchLight verifyCustomSearch(SearchLight search);

}
