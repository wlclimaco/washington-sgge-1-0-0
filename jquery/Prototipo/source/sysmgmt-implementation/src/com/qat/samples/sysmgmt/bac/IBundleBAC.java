package com.qat.samples.sysmgmt.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IBundleBAC.
 */
public interface IBundleBAC
{

	/**
	 * Insert bundle.
	 * 
	 * @param bundle the bundle
	 * @return the internal response
	 */
	public InternalResponse insertBundle(Bundle bundle);

	/**
	 * Update bundle.
	 * 
	 * @param bundle the bundle
	 * @return the internal response
	 */
	public InternalResponse updateBundle(Bundle bundle);

	/**
	 * Delete bundle.
	 * 
	 * @param bundle the bundle
	 * @return the internal response
	 */
	public InternalResponse deleteBundle(Bundle bundle);

	/**
	 * Refresh bundles.
	 * 
	 * @param refreshNumber the refresh number
	 */
	public void refreshBundles(Integer refreshNumber);

	/**
	 * Fetch all bundles.
	 * 
	 * @return the internal results response
	 */
	public InternalResultsResponse<Bundle> fetchAllBundles();

	/**
	 * Fetch bundle by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Bundle> fetchBundleById(FetchByIdRequest request);

	/**
	 * Fetch bundles by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Bundle> fetchBundlesByRequest(PagedInquiryRequest request);
}
