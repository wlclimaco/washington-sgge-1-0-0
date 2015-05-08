package com.prosperitasglobal.sendsolv.payer.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.payer.model.PayerStateProvinceRegion;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class PayerStateProvinceRegionResponse.
 */
public class PayerStateProvinceRegionResponse extends InquiryResponse
{
	/** The list of payers. */
	private List<PayerStateProvinceRegion> payerStateProvinceRegionList;

	/**
	 * Get the list of payerStateProvinceRegion.
	 *
	 * @return The payer list.
	 */
	public List<PayerStateProvinceRegion> getPayerStateProvinceRegionList()
	{
		return payerStateProvinceRegionList;
	}

	/**
	 * Set the list of payerStateProvinceRegion.
	 *
	 * @param payerList The payer list to set.
	 */
	public void setPayerStateProvinceRegionList(List<PayerStateProvinceRegion> payerStateProvinceRegionList)
	{
		this.payerStateProvinceRegionList = payerStateProvinceRegionList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPayerStateProvinceRegionList((List<PayerStateProvinceRegion>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerResponse [getPayerStateProvinceRegionList()=" + getPayerStateProvinceRegionList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
