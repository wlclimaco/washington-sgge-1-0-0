package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC;
import com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.request.CurrencyPurchaseMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CurrencyPurchaseBACImpl.
 */
public class CurrencyPurchaseBACImpl implements ICurrencyPurchaseBAC
{

	/** The currency purchase dac. */
	private ICurrencyPurchaseDAC currencyPurchaseDAC;

	/**
	 * Gets the currency purchase dac.
	 *
	 * @return the currency purchase dac
	 */
	public ICurrencyPurchaseDAC getCurrencyPurchaseDAC()
	{
		return currencyPurchaseDAC;
	}

	/**
	 * Sets the currency purchase dac.
	 *
	 * @param currencyPurchaseDAC the currency purchase dac
	 */
	public void setCurrencyPurchaseDAC(ICurrencyPurchaseDAC currencyPurchaseDAC)
	{
		this.currencyPurchaseDAC = currencyPurchaseDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC#fetchCurrencyPurchaseById(com.prosperitasglobal.cbof.
	 * model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<CurrencyPurchase> fetchCurrencyPurchaseById(FetchByIdRequest request)
	{
		return getCurrencyPurchaseDAC().fetchCurrencyPurchaseById(request.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC#deleteCurrencyPurchase(com.prosperitasglobal.sendsolv
	 * .model.request.CurrencyPurchaseMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request)
	{
		return getCurrencyPurchaseDAC().deleteCurrencyPurchase(request.getCurrencyPurchase());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC#insertCurrencyPurchase(com.prosperitasglobal.sendsolv
	 * .model.request.CurrencyPurchaseMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request)
	{
		return getCurrencyPurchaseDAC().insertCurrencyPurchase(request.getCurrencyPurchase());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICurrencyPurchaseBAC#updateCurrencyPurchase(com.prosperitasglobal.sendsolv
	 * .model.request.CurrencyPurchaseMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateCurrencyPurchase(CurrencyPurchaseMaintenanceRequest request)
	{
		return getCurrencyPurchaseDAC().updateCurrencyPurchase(request.getCurrencyPurchase());
	}

}
