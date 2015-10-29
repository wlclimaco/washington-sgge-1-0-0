package com.qat.samples.sysmgmt.site.bas.rest;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.site.bai.ISiteBAI;
import com.qat.samples.sysmgmt.site.bas.ISiteRESTBAS;
import com.qat.samples.sysmgmt.site.model.request.SiteMaintenanceRequest;
import com.qat.samples.sysmgmt.site.model.response.SiteResponse;

/**
 * Standard implementation of a BAS where the operations are delegated to a BAI.
 * Note the BAI is injected by Spring.
 */
public class SiteBAS implements ISiteRESTBAS
{

	/** The supermercado bai. */
	private ISiteBAI supermercadoBAI; // injected by Spring through setter

	/**
	 * Sets the supermercado bai.
	 * 
	 * @param supermercadoBAI the new supermercado bai
	 */
	public void setSiteBAI(ISiteBAI supermercadoBAI)
	{
		this.supermercadoBAI = supermercadoBAI;
	}

	/**
	 * Gets the supermercado bai.
	 * 
	 * @return the supermercado bai
	 */
	public ISiteBAI getSiteBAI()
	{
		return supermercadoBAI;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISiteRESTBAS#insertSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest)
	 */
	@Override
	public SiteResponse insertSite(SiteMaintenanceRequest request)
	{
		return getSiteBAI().insertSite(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISiteRESTBAS#updateSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest)
	 */
	@Override
	public SiteResponse updateSite(SiteMaintenanceRequest request)
	{
		return getSiteBAI().updateSite(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.bas.ISiteRESTBAS#deleteSite(com.qat.samples.sysmgmt.model.request.
	 * SiteMaintenanceRequest)
	 */
	@Override
	public SiteResponse deleteSite(SiteMaintenanceRequest request)
	{
		return getSiteBAI().deleteSite(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteRESTBAS#refreshSites(com.qat.samples.sysmgmt.model.request.
	 * RefreshRequest)
	 */
	// @Override
	// public SiteResponse refreshSites(RefreshRequest request)
	// {
	// // This method is demo code only. Do not view this as a QAT Global Standard.
	// return getSiteBAI().refreshSites(request);
	// }
	//
	// /*
	// * (non-Javadoc)
	// * @see
	// * com.qat.samples.sysmgmt.bas.ISiteRESTBAS#fetchAllSites(com.qat.samples.sysmgmt.model.request.
	// * FetchAllRequest)
	// */
	// @Override
	// public SiteResponse fetchAllSites(FetchAllRequest request)
	// {
	// return getSiteBAI().fetchAllSites(request);
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteRESTBAS#fetchSiteById(com.qat.samples.sysmgmt.model.request.
	 * FetchByIdRequest
	 * )
	 */
	@Override
	public SiteResponse fetchSiteById(FetchByIdRequest request)
	{
		return getSiteBAI().fetchSiteById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.sysmgmt.bas.ISiteRESTBAS#fetchSitesByRequest(com.qat.samples.sysmgmt.model.request
	 * .
	 * PagedInquiryRequest)
	 */
	@Override
	public SiteResponse fetchSitesByRequest(PagedInquiryRequest request)
	{
		return getSiteBAI().fetchSitesByRequest(request);
	}

	@Override
	public SiteResponse refreshSites(RefreshRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteResponse fetchAllSites(FetchAllRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
