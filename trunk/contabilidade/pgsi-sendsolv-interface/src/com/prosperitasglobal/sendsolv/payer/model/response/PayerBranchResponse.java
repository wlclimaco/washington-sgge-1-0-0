package com.prosperitasglobal.sendsolv.payer.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.payer.model.PayerBranch;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerBranchResponse.
 */
public class PayerBranchResponse extends InquiryResponse
{
	/** The list of payers. */
	private List<PayerBranch> payerBranchList;

	/**
	 * @return the payerBranchList
	 */
	public List<PayerBranch> getPayerBranchList()
	{
		return payerBranchList;
	}

	/**
	 * @param payerBranchList the payerBranchList to set
	 */
	public void setPayerBranchList(List<PayerBranch> payerBranchList)
	{
		this.payerBranchList = payerBranchList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerBranchList((List<PayerBranch>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerResponse [getPayerBranchList()=" + getPayerBranchList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
