package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class CurrencyPurchaseResponse.
 *
 */
public class CurrencyPurchaseResponse extends InquiryResponse
{
	/** The list of currency purchases. */
	private List<CurrencyPurchase> currencyPurchaseList;

	/**
	 * Get the list of currencyPurchase.
	 *
	 * @return The currencyPurchase list.
	 */
	public List<CurrencyPurchase> getCurrencyPurchaseList()
	{
		return currencyPurchaseList;
	}

	/**
	 * Set the list of currencyPurchase.
	 *
	 * @param currencyPurchaseList The currencyPurchase list to set.
	 */
	public void setCurrencyPurchaseList(List<CurrencyPurchase> currencyPurchaseList)
	{
		this.currencyPurchaseList = currencyPurchaseList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCurrencyPurchaseList((List<CurrencyPurchase>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CurrencyPurchaseResponse [getCurrencyPurchaseList()=" + getCurrencyPurchaseList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
