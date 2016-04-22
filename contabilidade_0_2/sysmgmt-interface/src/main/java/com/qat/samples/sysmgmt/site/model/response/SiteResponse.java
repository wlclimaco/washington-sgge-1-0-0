package com.qat.samples.sysmgmt.site.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.site.model.Site;

/**
 * The Model Object SiteResponse.
 */
public class SiteResponse extends InquiryResponse
{

	/** The sites. */
	@XmlElement(nillable = true)
	private List<Site> sites;

	/**
	 * Gets the sites.
	 * 
	 * @return the sites
	 */
	public List<Site> getSites()
	{
		return sites;
	}

	/**
	 * Sets the sites.
	 * 
	 * @param sites the new sites
	 */
	public void setSites(List<Site> sites)
	{
		this.sites = sites;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setSites((List<Site>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "SiteResponse [getSites()=" + getSites() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
