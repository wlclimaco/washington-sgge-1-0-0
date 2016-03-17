package com.qat.samples.sysmgmt.financeiro.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.financeiro.Financeiro;
import com.qat.samples.sysmgmt.financeiro.bac.IFinanceiroBAC;
import com.qat.samples.sysmgmt.financeiro.dac.IFinanceiroDAC;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroInquiryRequest;
import com.qat.samples.sysmgmt.financeiro.model.request.FinanceiroMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

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
