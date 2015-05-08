package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class CurrencyPurchaseDACImpl.
 */
public class CurrencyPurchaseDACImpl extends SqlSessionDaoSupport implements ICurrencyPurchaseDAC
{

	/** The Constant CURRENCY_PURCHASE_NAMESPACE. */
	private static final String CURRENCY_PURCHASE_NAMESPACE = "CurrencyPurchaseMap.";

	/** The Constant CURRENY_PURCHASE_STMT_FETCH_CURRENY_PURCHASE_BY_ID. */
	private static final String CURRENCY_PURCHASE_STMT_FETCH_CURRENY_PURCHASE_BY_ID = CURRENCY_PURCHASE_NAMESPACE
			+ "fetchCurrencyPurchaseById";

	/** The Constant CURRENY_PURCHASE_STMT_INSERT. */
	private static final String CURRENCY_PURCHASE_STMT_INSERT = CURRENCY_PURCHASE_NAMESPACE + "insertCurrencyPurchase";

	/** The Constant CURRENY_PURCHASE_STMT_UPDATE. */
	private static final String CURRENCY_PURCHASE_STMT_UPDATE = CURRENCY_PURCHASE_NAMESPACE + "updateCurrencyPurchase";

	/** The Constant CURRENY_PURCHASE_STMT_DELETE. */
	private static final String CURRENCY_PURCHASE_STMT_DELETE = CURRENCY_PURCHASE_NAMESPACE + "deleteCurrencyPurchase";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC#deleteCurrencyPurchase(com.prosperitasglobal.sendsolv
	 * .model.CurrencyPurchase)
	 */
	@Override
	public InternalResponse deleteCurrencyPurchase(CurrencyPurchase currencyPurchase)
	{
		InternalResponse response = new InternalResponse();
		QATMyBatisDacHelper.doRemove(getSqlSession(), CURRENCY_PURCHASE_STMT_DELETE, currencyPurchase, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC#fetchCurrencyPurchaseById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<CurrencyPurchase> fetchCurrencyPurchaseById(Integer id)
	{
		InternalResultsResponse<CurrencyPurchase> response = new InternalResultsResponse<CurrencyPurchase>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CURRENCY_PURCHASE_STMT_FETCH_CURRENY_PURCHASE_BY_ID, id,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC#insertCurrencyPurchase(com.prosperitasglobal.sendsolv
	 * .model.CurrencyPurchase)
	 */
	@Override
	public InternalResponse insertCurrencyPurchase(CurrencyPurchase currencyPurchase)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper
				.doInsert(getSqlSession(), CURRENCY_PURCHASE_STMT_INSERT, currencyPurchase, response);

		if (response.isInError())
		{
			return response;
		}

		if (!response.isInError() && (insertCount > 0))
		{
			currencyPurchase.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.dac.ICurrencyPurchaseDAC#updateCurrencyPurchase(com.prosperitasglobal.sendsolv
	 * .model.CurrencyPurchase)
	 */
	@Override
	public InternalResponse updateCurrencyPurchase(CurrencyPurchase currencyPurchase)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// First update the root
		updateCount =
				QATMyBatisDacHelper
				.doUpdate(getSqlSession(), CURRENCY_PURCHASE_STMT_UPDATE, currencyPurchase, response);

		if (response.isInError())
		{
			return response;
		}

		if (!response.isInError() && (updateCount > 0))
		{
			currencyPurchase.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}
}
