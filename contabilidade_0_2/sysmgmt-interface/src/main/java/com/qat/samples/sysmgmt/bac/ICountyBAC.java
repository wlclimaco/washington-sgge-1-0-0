package com.qat.samples.sysmgmt.bac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICountyBAC. (Business Area Component - BAC)
 */
public interface ICountyBAC
{

	/**
	 * Insert county.
	 *
	 * @param request the county maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<County> insertCounty(CountyMaintenanceRequest request);

	/**
	 * Update county.
	 *
	 * @param request the county maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<County> updateCounty(CountyMaintenanceRequest request);

	/**
	 * Delete county.
	 *
	 * @param request the county maintenance request
	 *
	 * @return the internal results response
	 */
	public InternalResultsResponse<County> deleteCounty(CountyMaintenanceRequest request);

	/**
	 * Refresh counties.
	 *
	 * @param request containing the number to refresh with and whether to return the result
	 */
	public InternalResultsResponse<County> refreshCounties(RefreshRequest request);

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
