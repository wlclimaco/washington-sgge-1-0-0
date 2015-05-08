package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Payer;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerResponse.
 */
public class PayerResponse extends InquiryResponse
{
	/** The list of payers. */
	private List<Payer> payerList;

	/**
	 * Get the list of payer.
	 *
	 * @return The payer list.
	 */
	public List<Payer> getPayerList()
	{
		return payerList;
	}

	/**
	 * Set the list of payer.
	 *
	 * @param payerList The payer list to set.
	 */
	public void setPayerList(List<Payer> payerList)
	{
		this.payerList = payerList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerList((List<Payer>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerResponse [getPayerList()=" + getPayerList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
