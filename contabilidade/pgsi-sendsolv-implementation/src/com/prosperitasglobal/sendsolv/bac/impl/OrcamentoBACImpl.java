package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IOrcamentoBAC;
import com.prosperitasglobal.sendsolv.dac.IOrcamentoDAC;
import com.prosperitasglobal.sendsolv.model.Orcamento;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrcamentoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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

		response = getOrcamentoDAC().insertOrcamento(request.getOrcamento());

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

		response = getOrcamentoDAC().updateOrcamento(request.getOrcamento());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrcamentoBAC#deleteOrcamento
	 * (com.prosperitasglobal.sendsolv.model.request.OrcamentoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteOrcamento(OrcamentoMaintenanceRequest request)
	{
		return getOrcamentoDAC().deleteOrcamento(request.getOrcamento());
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
}
