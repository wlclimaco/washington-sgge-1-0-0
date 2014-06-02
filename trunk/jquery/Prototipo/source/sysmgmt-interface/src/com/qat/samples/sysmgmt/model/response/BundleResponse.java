package com.qat.samples.sysmgmt.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.model.Bundle;

/**
 * The Model Object ProcedureResponse.
 */
public class BundleResponse extends InquiryResponse
{

	/** The bundles. */
	@XmlElement(nillable = true)
	private List<Bundle> bundles;

	/**
	 * Gets the bundles.
	 * 
	 * @return the bundles
	 */
	public List<Bundle> getBundles()
	{
		return bundles;
	}

	/**
	 * Sets the bundles.
	 * 
	 * @param bundles the new bundles
	 */
	public void setBundles(List<Bundle> bundles)
	{
		this.bundles = bundles;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setBundles((List<Bundle>)coll);
	}

	@Override
	public String toString()
	{
		return "BundleResponse [getBundles()=" + getBundles() + ", toString()=" + super.toString() + "]";
	}

}
