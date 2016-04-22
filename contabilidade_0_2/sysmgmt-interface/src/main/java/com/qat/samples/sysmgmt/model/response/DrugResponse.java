package com.qat.samples.sysmgmt.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.model.Drug;

/**
 * The Model Object DrugResponse.
 */
public class DrugResponse extends InquiryResponse
{

	/** The drugs. */
	@XmlElement(nillable = true)
	private List<Drug> drugs;

	/**
	 * Gets the drugs.
	 * 
	 * @return the drugs
	 */
	public List<Drug> getDrugs()
	{
		return drugs;
	}

	/**
	 * Sets the drugs.
	 * 
	 * @param drugs the new drugs
	 */
	public void setDrugs(List<Drug> drugs)
	{
		this.drugs = drugs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setDrugs((List<Drug>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "DrugResponse [getDrugs()=" + getDrugs() + ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
