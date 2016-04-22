package com.qat.samples.sysmgmt.util.model.response;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.model.Procedure;

/**
 * The Model Object ProcedureResponse.
 */
public class ProcedureResponse extends InquiryResponse
{

	/** The procedures. */
	@XmlElement(nillable = true)
	private List<Procedure> procedures;

	/**
	 * Gets the procedures.
	 *
	 * @return the procedures
	 */
	public List<Procedure> getProcedures()
	{
		return procedures;
	}

	/**
	 * Sets the procedures.
	 *
	 * @param procedures the new procedures
	 */
	public void setProcedures(List<Procedure> procedures)
	{
		this.procedures = procedures;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	public void addResults(Collection coll)
	{
		setProcedures((List<Procedure>)coll);
	}

	@Override
	public String toString()
	{
		// return some interesting information for logging/debugging
		// avoid personally identifying information
		return "ProcedureResponse [getProcedures()=" + getProcedures() + ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageList()=" + getMessageList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
