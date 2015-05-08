package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ICurrencyPurchaseDAC.
 */
public interface ICurrencyPurchaseDAC
{

	/**
	 * Delete currency purchase.
	 *
	 * @param currencyPurchase the currency purchase to delete
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 */
	public InternalResponse deleteCurrencyPurchase(CurrencyPurchase currencyPurchase);

	/**
	 * Fetch currency purchase by id.
	 *
	 * @param id the id of the currency purchase
	 * @return the internal results response< currency purchase>
	 */
	public InternalResultsResponse<CurrencyPurchase> fetchCurrencyPurchaseById(Integer id);

	/**
	 * Insert currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public InternalResponse insertCurrencyPurchase(CurrencyPurchase currencyPurchase);

	/**
	 * Update currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public InternalResponse updateCurrencyPurchase(CurrencyPurchase currencyPurchase);
}
