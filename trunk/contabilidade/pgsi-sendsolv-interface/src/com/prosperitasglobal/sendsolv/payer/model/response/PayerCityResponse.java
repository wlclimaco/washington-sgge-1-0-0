package com.prosperitasglobal.sendsolv.payer.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.payer.model.PayerCity;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerCityResponse.
 */
public class PayerCityResponse extends InquiryResponse
{
	/** The list of payers. */
	private List<PayerCity> payerCityList;

	/**
	 * @return the payerCityList
	 */
	public List<PayerCity> getPayerCityList()
	{
		return payerCityList;
	}

	/**
	 * @param payerCityList the payerCityList to set
	 */
	public void setPayerCityList(List<PayerCity> payerCityList)
	{
		this.payerCityList = payerCityList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerCityList((List<PayerCity>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerResponse [getPayerCityList()=" + getPayerCityList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
