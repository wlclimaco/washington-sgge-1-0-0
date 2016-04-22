package com.qat.samples.sysmgmt.site.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.site.model.Site;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class SiteMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "SiteMaintenanceRequest [getSite()=" + getSite() + ", toString()=" + super.toString() + "]";
	}


}