package com.qat.samples.sysmgmt.site.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.site.Site;

public class SiteMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Site site;

	/**
	 * The Constructor.
	 */
	public SiteMaintenanceRequest()
	{

	}

	/**
	 * @return the site
	 */
	public Site getSite()
	{
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(Site site)
	{
		this.site = site;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SiteMaintenanceRequest [getSite()=" + getSite() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}