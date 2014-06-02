package com.qat.samples.sysmgmt.util;

import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

/**
 * The Interface IBundleBAI. (Business Area Interface - BAI)
 */
public interface IBundleBAI
{

	/**
	 * Insert bundle.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	public BundleResponse insertBundle(BundleMaintenanceRequest request);

	/**
	 * Update bundle.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	public BundleResponse updateBundle(BundleMaintenanceRequest request);

	/**
	 * Delete bundle.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	public BundleResponse deleteBundle(BundleMaintenanceRequest request);

	/**
	 * Fetch all bundles.
	 * 
	 * @param request the request
	 * @return the bundle response
	 */
	public BundleResponse fetchAllBundles(FetchAllRequest request);

	/**
	 * Refresh bundles.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	public BundleResponse refreshBundles(RefreshRequest request);

	/**
	 * Fetch bundle by id.
	 * 
	 * @param request the request
	 * @return the bundle response
	 */
	public BundleResponse fetchBundleById(FetchByIdRequest request);

	/**
	 * Fetch bundles by request.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	public BundleResponse fetchBundlesByRequest(PagedInquiryRequest request);

}
