package com.qat.samples.sysmgmt.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IBundleDAC. (Data Access Component - DAC)
 */
public interface IBundleDAC
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
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateBundle(Bundle bundle);

	/**
	 * Delete bundle.
	 * 
	 * @param bundle the bundle
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteBundle(Bundle bundle);

	/**
	 * Delete all bundles.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllBundles();

	/**
	 * Fetch all bundles.
	 * 
	 * @return the list< bundle>
	 */
	public List<Bundle> fetchAllBundles();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Bundle fetchBundleById(FetchByIdRequest request);

	/**
	 * Fetch bundles by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Bundle> fetchBundlesByRequest(PagedInquiryRequest request);

}
