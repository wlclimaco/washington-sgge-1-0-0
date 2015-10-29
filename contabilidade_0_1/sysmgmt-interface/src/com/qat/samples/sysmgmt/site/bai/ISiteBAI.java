package com.qat.samples.sysmgmt.site.bai;

import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * The Interface ISiteBAI. (Business Area Interface - BAI)
 */
public interface ISiteBAI
{

	/**
	 * Insert supermercado.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SiteResponse insertSite(SiteMaintenanceRequest request);

	/**
	 * Update supermercado.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SiteResponse updateSite(SiteMaintenanceRequest request);

	/**
	 * Delete supermercado.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SiteResponse deleteSite(SiteMaintenanceRequest request);

	/**
	 * Fetch supermercado by id.
	 * 
	 * @param request the request
	 * @return the supermercado response
	 */
	public SiteResponse fetchSiteById(FetchByIdRequest request);

	/**
	 * Fetch supermercados by request.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	public SiteResponse fetchSitesByRequest(PagedInquiryRequest request);

}
