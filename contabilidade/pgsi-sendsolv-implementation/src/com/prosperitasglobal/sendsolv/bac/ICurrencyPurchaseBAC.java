package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.request.CurrencyPurchaseMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ICurrencyPurchaseBAC.
 */
public interface ICurrencyPurchaseBAC
{

	/**
	 * Fetch currency purchase by id.
	 *
	 * @param request the request
	 * @return the internal results response< currency purchase>
	 */
	public InternalResultsResponse<CurrencyPurchase> fetchCurrencyPurchaseById(FetchByIdRequest request);

	/**
	 * Delete currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public InternalResponse deleteCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request);

	/**
	 * Insert currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public InternalResponse insertCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request);

	/**
	 * Update currency purchase.
	 *
	 * @param currencyPurchase the currency purchase
	 * @return the internal response
	 */
	public InternalResponse updateCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request);
}
