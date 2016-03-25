package com.qat.samples.sysmgmt.nf.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.nf.bac.IOrcamentoBAC;
import com.qat.samples.sysmgmt.nf.dac.IOrcamentoDAC;
import com.qat.samples.sysmgmt.nf.model.Orcamento;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;

/**
 * The Class OrcamentoBACImpl.
 */
public class OrcamentoBACImpl implements IOrcamentoBAC
{

	/** The person dac. */
	private IOrcamentoDAC orcamentoDAC;

	/**
	 * @return the orcamentoDAC
	 */
	public IOrcamentoDAC getOrcamentoDAC()
	{
		return orcamentoDAC;
	}

	/**
	 * @param orcamentoDAC the orcamentoDAC to set
	 */
	public void setOrcamentoDAC(IOrcamentoDAC orcamentoDAC)
	{
		this.orcamentoDAC = orcamentoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrcamentoBAC#insertOrcamento(OrcamentoRequest)
	 */
	@Override
	public InternalResultsResponse<Orcamento> insertOrcamento(OrcamentoMaintenanceRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		Integer a = getOrcamentoDAC().insertOrcamento(request.getOrcamento(), null, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrcamentoBAC#updateOrcamento(OrcamentoRequest)
	 */
	@Override
	public InternalResultsResponse<Orcamento> updateOrcamento(OrcamentoMaintenanceRequest request)
	{
		InternalResultsResponse<Orcamento> response = new InternalResultsResponse<Orcamento>();

		Integer a = getOrcamentoDAC().updateOrcamento(request.getOrcamento(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrcamentoBAC#fetchOrcamentoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request)
	{
		return getOrcamentoDAC().fetchOrcamentoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrcamentoBAC#fetchOrcamentoByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .OrcamentoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(OrcamentoInquiryRequest request)
	{
		return getOrcamentoDAC().fetchOrcamentoByRequest(request);
	}

	@Override
	public InternalResponse deleteOrcamento(OrcamentoMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
