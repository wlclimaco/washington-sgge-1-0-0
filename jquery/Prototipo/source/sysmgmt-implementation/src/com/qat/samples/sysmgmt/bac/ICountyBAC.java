package com.qat.samples.sysmgmt.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface ICountyBAC. (Business Area Component - BAC)
 */
public interface ICountyBAC
{

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
	public InternalResponse deleteCounty(County county);

	/**
	 * Refresh counties.
	 * 
	 * @param refreshNumber the refresh number
	 */
	public void refreshCounties(Integer refreshNumber);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<County> fetchCountyById(FetchByIdRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @return the internal results response< county>
	 */
	public InternalResultsResponse<County> fetchAllCounties();

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request);
}
