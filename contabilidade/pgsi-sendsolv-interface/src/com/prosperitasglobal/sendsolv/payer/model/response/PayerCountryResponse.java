package com.prosperitasglobal.sendsolv.payer.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.payer.model.PayerCountry;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerCountryResponse.
 */
public class PayerCountryResponse extends InquiryResponse
{
	/** The list of payers. */
	private List<PayerCountry> payerCountryList;

	/**
	 * @return the payerCountryList
	 */
	public List<PayerCountry> getPayerCountryList()
	{
		return payerCountryList;
	}

	/**
	 * @param payerCountryList the payerCountryList to set
	 */
	public void setPayerCountryList(List<PayerCountry> payerCountryList)
	{
		this.payerCountryList = payerCountryList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerCountryList((List<PayerCountry>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerCountryResponse [getPayerCountryList()=" + getPayerCountryList() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
