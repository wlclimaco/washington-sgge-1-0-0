package com.qat.samples.sysmgmt.dac;

import com.qat.framework.model.response.CachedResultsResponse;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface ICountyDAC. (Data Access Component - DAC)
 */
public interface ICountyDAC
{

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */
	public CachedResultsResponse<County> fetchCountyById(FetchByIdRequest request);

	/**
	 * Insert county.
	 * 
	 * @param county the county
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertCounty(County county);

	/**
	 * Update county.
	 * 
	 * @param county the county
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateCounty(County county);

	/**
	 * Delete county.
	 * 
	 * @param county the county
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteCountyById(County county);

	/**
	 * Delete all counties.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllCounties();

	/**
	 * Fetch all counties.
	 * 
	 * @return the list< county>
	 */
	public CachedResultsResponse<County> fetchAllCounties();

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request);

}
