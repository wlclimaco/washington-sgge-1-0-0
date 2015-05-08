package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;

public class MoneyTransferAutoApprovalResponse extends InquiryResponse
{
	/** The list of {@Link MoneyTransfer}'s IDs that will be used to do the auto approval. */
	private List<Integer> idsToApproveList;

	/**
	 * @return the idsToApproveList
	 */
	public List<Integer> getIdsToApproveList()
	{
		return idsToApproveList;
	}

	/**
	 * @param idsToApproveList the idsToApproveList to set
	 */
	public void setIdsToApproveList(List<Integer> idsToApproveList)
	{
		this.idsToApproveList = idsToApproveList;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setIdsToApproveList((List<Integer>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferAutoApprovalResponse [getIdsToApproveList()=" + getIdsToApproveList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
