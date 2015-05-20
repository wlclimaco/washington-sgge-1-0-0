package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;

/**
 * Class for the maintenance of currency purchase objects.
 */
public class CurrencyPurchaseMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private CurrencyPurchase currencyPurchase;

	/**
	 * The Constructor.
	 */
	public CurrencyPurchaseMaintenanceRequest()
	{

	}

	/**
	 * Gets the currencyPurchase.
	 *
	 * @return the location
	 */
	public CurrencyPurchase getCurrencyPurchase()
	{
		return currencyPurchase;
	}

	/**
	 * Sets the currencyPurchase.
	 *
	 * @param currencyPurchase the currencyPurchase
	 */
	public void setCurrencyPurchase(CurrencyPurchase currencyPurchase)
	{
		this.currencyPurchase = currencyPurchase;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CurrencyPurchaseMaintenanceRequest [getCurrencyPurchase()=" + getCurrencyPurchase()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}
