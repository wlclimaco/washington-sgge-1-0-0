package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.CurrencyPurchaseMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.CurrencyPurchaseResponse;

/**
 * The Interface ICurrencyPurchaseBAI.
 */
public interface ICurrencyPurchaseBAI
{

	/**
	 * Delete currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public CurrencyPurchaseResponse deleteCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request);

	/**
	 * Insert currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public CurrencyPurchaseResponse insertCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request);

	/**
	 * Fetch currency purchase by id.
	 *
	 * @param id the id
	 * @return the internal results response< currency purchase>
	 */
	public CurrencyPurchaseResponse fetchCurrencyPurchaseById(FetchByIdRequest request);

	/**
	 * Update currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public CurrencyPurchaseResponse updateCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request);
}
