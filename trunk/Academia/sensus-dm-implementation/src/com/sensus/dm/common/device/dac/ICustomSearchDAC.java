package com.sensus.dm.common.device.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.property.model.Property;

/**
 * The Interface ICustomSearchDAC.
 * 
 * @author - QAT Brazil.
 * 
 */
public interface ICustomSearchDAC
{

	/**
	 * Can insert custom search.
	 * 
	 * @param customSearchRequest the custom search request
	 * @return the InternalResultsResponse
	 */
	InternalResultsResponse<Boolean> canCustomSearchBeInserted(CustomSearchRequest customSearchRequest);

	/**
	 * Insert custom search.
	 * 
	 * @param request the request
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
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryCustomSearchRequest request);

	/**
	 * Fetch all column filter.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchAllColumnFilter(ColumnFilterRequest request);
}
