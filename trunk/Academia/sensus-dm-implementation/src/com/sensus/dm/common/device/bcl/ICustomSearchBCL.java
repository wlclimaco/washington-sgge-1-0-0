package com.sensus.dm.common.device.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.property.model.Property;

/**
 * The Interface ICustomSearchBCL.
 * 
 * @author QAT Brazil.
 * 
 */
public interface ICustomSearchBCL
{

	/**
	 * Insert search meter.
	 * 
	 * @param request the request
	 * @return the meter response
	 */
	InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request);

	/**
	 * Delete search meter.
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
	 * Insert column and filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Fetch all column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the internal results response
	 */
	InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Fetch all column filter.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchAllColumnFilter(ColumnFilterRequest request);
}