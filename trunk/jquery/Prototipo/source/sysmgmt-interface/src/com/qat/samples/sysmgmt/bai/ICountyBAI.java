package com.qat.samples.sysmgmt.bai;

import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

/**
 * The Interface ICountyBAI. (Business Area Interface - BAI)
 */
public interface ICountyBAI
{

	/**
	 * Insert county.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	public CountyResponse insertCounty(CountyMaintenanceRequest request);

	/**
	 * Update county.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	public CountyResponse updateCounty(CountyMaintenanceRequest request);

	/**
	 * Delete county.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	public CountyResponse deleteCounty(CountyMaintenanceRequest request);

	/**
	 * Refresh counties.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	public CountyResponse refreshCounties(RefreshRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	public CountyResponse fetchAllCounties(FetchAllRequest request);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the county response
	 */
	public CountyResponse fetchCountyById(FetchByIdRequest request);

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	public CountyResponse fetchCountiesByRequest(PagedInquiryRequest request);
}
