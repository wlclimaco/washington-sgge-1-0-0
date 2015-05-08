package com.prosperitasglobal.sendsolv.payer.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.payer.model.PayerZipcode;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerZipcodeResponse.
 */
public class PayerZipcodeResponse extends InquiryResponse
{
	/** The list of payers. */
	private List<PayerZipcode> payerZipcodeList;

	/**
	 * @return the payerZipcodeList
	 */
	public List<PayerZipcode> getPayerZipcodeList()
	{
		return payerZipcodeList;
	}

	/**
	 * @param payerZipcodeList the payerZipcodeList to set
	 */
	public void setPayerZipcodeList(List<PayerZipcode> payerZipcodeList)
	{
		this.payerZipcodeList = payerZipcodeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerZipcodeList((List<PayerZipcode>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerResponse [getPayerZipcodeList()=" + getPayerZipcodeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
