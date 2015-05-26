package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IHistoricoBAC;
import com.prosperitasglobal.sendsolv.dac.IHistoricoDAC;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class HistoricoBACImpl.
 */
public class HistoricoBACImpl implements IHistoricoBAC
{

	/** The person dac. */
	private IHistoricoDAC historicoDAC;

	/**
	 * @return the historicoDAC
	 */
	public IHistoricoDAC getHistoricoDAC()
	{
		return historicoDAC;
	}

	/**
	 * @param historicoDAC the historicoDAC to set
	 */
	public void setHistoricoDAC(IHistoricoDAC historicoDAC)
	{
		this.historicoDAC = historicoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IHistoricoBAC#insertHistorico(HistoricoRequest)
	 */
	@Override
	public InternalResultsResponse<Historico> insertHistorico(HistoricoMaintenanceRequest request)
	{
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();

		getHistoricoDAC().insertHistorico(request.getHistorico(), "", response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IHistoricoBAC#updateHistorico(HistoricoRequest)
	 */
	@Override
	public InternalResultsResponse<Historico> updateHistorico(HistoricoMaintenanceRequest request)
	{
		InternalResultsResponse<Historico> response = new InternalResultsResponse<Historico>();

		getHistoricoDAC().updateHistorico(request.getHistorico(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IHistoricoBAC#deleteHistorico
	 * (com.prosperitasglobal.sendsolv.model.request.HistoricoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteHistorico(HistoricoMaintenanceRequest request)
	{

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IHistoricoBAC#fetchHistoricoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request)
	{
		return getHistoricoDAC().fetchHistoricoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IHistoricoBAC#fetchHistoricoByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .HistoricoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request)
	{
		return getHistoricoDAC().fetchHistoricoByRequest(request);
	}
}
