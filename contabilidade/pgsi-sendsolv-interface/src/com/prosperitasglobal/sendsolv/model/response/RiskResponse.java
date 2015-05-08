package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Risk;
import com.qat.framework.model.response.InquiryResponse;

/**
 * @author aporto
 * @version 1.0
 * @created 21-Aug-2014 04:11:00 AM
 */
public class RiskResponse extends InquiryResponse
{
	/** Attributes. */
	private List<Risk> riskList;

	/**
	 * The Constructor.
	 */
	public RiskResponse()
	{
		riskList = new ArrayList<Risk>();
	}

	/**
	 * Gets the document list.
	 *
	 * @return the document list
	 */
	public List<Risk> getRiskList()
	{
		return riskList;
	}

	/**
	 * Sets the document list.
	 *
	 * @param riskList the document list
	 */
	public void setRiskList(List<Risk> riskList)
	{
		this.riskList = riskList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setRiskList((List<Risk>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RiskResponse [getRiskList()=" + getRiskList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
