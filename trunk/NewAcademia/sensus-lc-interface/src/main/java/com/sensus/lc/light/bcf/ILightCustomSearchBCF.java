package com.sensus.lc.light.bcf;

import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.light.model.response.CustomSearchResponse;
import com.sensus.lc.light.model.response.LightFilterResponse;

/**
 * The Interface ILightCustomSearchBCF.
 */
public interface ILightCustomSearchBCF
{

	/**
	 * Fetch all custom search.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the custom search response
	 */
	CustomSearchResponse fetchAllCustomSearch(LightRequest lightRequest);

	/**
	 * Fetch light filters.
	 * 
	 * @param lightFilterRequest the light filter request
	 * @return the light filter response
	 */
	LightFilterResponse fetchLightFilters(LightFilterRequest lightFilterRequest);

	/**
	 * Fetch all column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert custom search.
	 * 
	 * @param request the request
	 * @return the custom search response
	 */
	CustomSearchResponse insertCustomSearch(CustomSearchRequest request);

	/**
	 * Insert column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Delete custom search.
	 * 
	 * @param request the request
	 * @return the custom search response
	 */
	CustomSearchResponse deleteCustomSearch(CustomSearchRequest request);

}
