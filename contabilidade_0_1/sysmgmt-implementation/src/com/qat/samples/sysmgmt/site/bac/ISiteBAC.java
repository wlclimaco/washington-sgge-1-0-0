package com.qat.samples.sysmgmt.site.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.site.Site;

/**
 * The Interface ISiteBAC. (Business Area Component - BAC)
 */
public interface ISiteBAC
{

	/**
	 * Insert procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertSite(Site procedure);

	/**
	 * Update procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateSite(Site procedure);

	/**
	 * Delete procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteSite(Site procedure);

	/**
	 * Refresh procedures.
	 * 
	 * @param refreshNumber the value of the number of procedures you want refreshed
	 * 
	 */
	public void refreshSites(Integer refreshNumber);

	/**
	 * Fetch procedure by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Site> fetchSiteById(FetchByIdRequest request);

	/**
	 * Fetch all procedures.
	 * 
	 * @return the internal results response< procedure>
	 */
	public InternalResultsResponse<Site> fetchAllSites();

	/**
	 * Fetch procedures by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Site> fetchSitesByRequest(PagedInquiryRequest request);
}
