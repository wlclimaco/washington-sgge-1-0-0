package com.prosperitasglobal.sendsolv.payer.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.payer.model.PayerAddress;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerAddressResponse.
 */
public class PayerAddressResponse extends InquiryResponse
{
	/** The list of addresses. */
	private List<PayerAddress> payerAddressList;

	/**
	 * @return the payerAddressList
	 */
	public List<PayerAddress> getPayerAddressList()
	{
		return payerAddressList;
	}

	/**
	 * @param payerAddressList the payerAddressList to set
	 */
	public void setPayerAddressList(List<PayerAddress> payerAddressList)
	{
		this.payerAddressList = payerAddressList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerAddressList((List<PayerAddress>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerAddressResponse [getPayerAddressList()=" + getPayerAddressList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
