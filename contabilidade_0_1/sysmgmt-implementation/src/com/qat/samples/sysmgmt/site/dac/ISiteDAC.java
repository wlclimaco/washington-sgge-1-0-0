package com.qat.samples.sysmgmt.site.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.site.Site;

/**
 * The Interface ISiteDAC. (Data Access Component - DAC)
 */
public interface ISiteDAC
{

	/**
	 * Insert site.
	 * 
	 * @param site the site
	 * @return the internal response
	 */
	public InternalResponse insertSite(Site site);

	/**
	 * Update site.
	 * 
	 * @param site the site
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateSite(Site site);

	/**
	 * Delete site.
	 * 
	 * @param site the site
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteSite(Site site);

	/**
	 * Delete all sites.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllSites();

	/**
	 * Fetch all sites.
	 * 
	 * @return the list< site>
	 */
	public List<Site> fetchAllSites();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Site fetchSiteById(FetchByIdRequest request);

	/**
	 * Fetch sites by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request);

}
