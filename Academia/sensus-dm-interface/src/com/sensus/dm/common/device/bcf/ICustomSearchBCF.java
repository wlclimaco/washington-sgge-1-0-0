package com.sensus.dm.common.device.bcf;

import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.device.model.response.CustomSearchResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;

/**
 * The Interface ICustomSearchBCF.
 * 
 * @author - QAT Brazil.
 * 
 */
public interface ICustomSearchBCF
{

	/**
	 * Insert search meter.
	 * 
	 * @param request the request
	 * @return the meter response
	 */
	CustomSearchResponse insertCustomSearch(CustomSearchRequest request);

	/**
	 * Delete search meter.
	 * 
	 * @param request the request
	 * @return the meter response
	 */
	CustomSearchResponse deleteCustomSearch(CustomSearchRequest request);

	/**
	 * Fetch all custom search.
	 * 
	 * @param inquiryCustomSearchRequest the inquiry custom search request
	 * @return the inquiry custom search response
	 */
	InquiryCustomSearchResponse fetchAllCustomSearch(InquiryCustomSearchRequest inquiryCustomSearchRequest);

	/**
	 * Fetch all column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest);
}