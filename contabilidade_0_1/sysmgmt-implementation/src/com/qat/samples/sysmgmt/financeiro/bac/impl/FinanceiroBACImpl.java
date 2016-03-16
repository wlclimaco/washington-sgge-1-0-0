package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IFinanceiroBAC;
import com.prosperitasglobal.sendsolv.dac.IFinanceiroDAC;
import com.prosperitasglobal.sendsolv.model.Financeiro;
import com.prosperitasglobal.sendsolv.model.request.FinanceiroInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FinanceiroMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class FinanceiroBACImpl.
 */
public class FinanceiroBACImpl implements IFinanceiroBAC
{

	/** The person dac. */
	private IFinanceiroDAC FinanceiroDAC;

	/**
	 * @return the FinanceiroDAC
	 */
	public IFinanceiroDAC getFinanceiroDAC()
	{
		return FinanceiroDAC;
	}

	/**
	 * @param FinanceiroDAC the FinanceiroDAC to set
	 */
	public void setFinanceiroDAC(IFinanceiroDAC FinanceiroDAC)
	{
		this.FinanceiroDAC = FinanceiroDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFinanceiroBAC#insertFinanceiro(FinanceiroRequest)
	 */
	@Override
	public InternalResultsResponse<Financeiro> insertFinanceiro(FinanceiroMaintenanceRequest request)
	{
		InternalResultsResponse<Financeiro> response = new InternalResultsResponse<Financeiro>();

		response = getFinanceiroDAC().insertFinanceiro(request.getFinanceiro());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFinanceiroBAC#updateFinanceiro(FinanceiroRequest)
	 */
	@Override
	public InternalResultsResponse<Financeiro> updateFinanceiro(FinanceiroMaintenanceRequest request)
	{
		InternalResultsResponse<Financeiro> response = new InternalResultsResponse<Financeiro>();

		response = getFinanceiroDAC().updateFinanceiro(request.getFinanceiro());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IFinanceiroBAC#deleteFinanceiro
	 * (com.prosperitasglobal.sendsolv.model.request.FinanceiroRequest
	 * )
	 */
	@Override
	public InternalResponse deleteFinanceiro(FinanceiroMaintenanceRequest request)
	{
		return getFinanceiroDAC().deleteFinanceiro(request.getFinanceiro());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFinanceiroBAC#fetchFinanceiroById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Financeiro> fetchFinanceiroById(FetchByIdRequest request)
	{
		return getFinanceiroDAC().fetchFinanceiroById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IFinanceiroBAC#fetchFinanceiroByRequest(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .FinanceiroInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Financeiro> fetchFinanceiroByRequest(FinanceiroInquiryRequest request)
	{
		return getFinanceiroDAC().fetchFinanceiroByRequest(request);
	}
}
