package com.qat.samples.sysmgmt.util.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.model.County;

/**
 * The Model Object CountyResponse.
 */
public class CountyResponse extends InquiryResponse
{

	/** The counties. */
	@XmlElement(nillable = true)
	private List<County> counties;

	/**
	 * Gets the counties.
	 *
	 * @return the counties
	 */
	public List<County> getCounties()
	{
		return counties;
	}

	/**
	 * Sets the counties.
	 *
	 * @param counties the new counties
	 */
	public void setCounties(List<County> counties)
	{
		this.counties = counties;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setCounties((List<County>)coll);
	}

	@Override
	public String toString()
	{
		return "CountyResponse [getCounties()=" + getCounties() + ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
