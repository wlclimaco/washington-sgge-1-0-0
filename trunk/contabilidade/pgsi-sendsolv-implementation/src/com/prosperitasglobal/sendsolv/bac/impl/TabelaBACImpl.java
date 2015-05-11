package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ITabelaBAC;
import com.prosperitasglobal.sendsolv.dac.ITabelaDAC;
import com.prosperitasglobal.sendsolv.model.Tabela;
import com.prosperitasglobal.sendsolv.model.request.TabelaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TabelaMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class TabelaBACImpl.
 */
public class TabelaBACImpl implements ITabelaBAC
{

	/** The person dac. */
	private ITabelaDAC tabelaDAC;

	/**
	 * @return the tabelaDAC
	 */
	public ITabelaDAC getTabelaDAC()
	{
		return tabelaDAC;
	}

	/**
	 * @param tabelaDAC the tabelaDAC to set
	 */
	public void setTabelaDAC(ITabelaDAC tabelaDAC)
	{
		this.tabelaDAC = tabelaDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ITabelaBAC#insertTabela(TabelaRequest)
	 */
	@Override
	public InternalResultsResponse<Tabela> insertTabela(TabelaMaintenanceRequest request)
	{
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();

		response = getTabelaDAC().insertTabela(request.getTabela());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ITabelaBAC#updateTabela(TabelaRequest)
	 */
	@Override
	public InternalResultsResponse<Tabela> updateTabela(TabelaMaintenanceRequest request)
	{
		InternalResultsResponse<Tabela> response = new InternalResultsResponse<Tabela>();

		response = getTabelaDAC().updateTabela(request.getTabela());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ITabelaBAC#deleteTabela
	 * (com.prosperitasglobal.sendsolv.model.request.TabelaRequest
	 * )
	 */
	@Override
	public InternalResponse deleteTabela(TabelaMaintenanceRequest request)
	{
		return getTabelaDAC().deleteTabela(request.getTabela());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ITabelaBAC#fetchTabelaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Tabela> fetchTabelaById(FetchByIdRequest request)
	{
		return getTabelaDAC().fetchTabelaById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ITabelaBAC#fetchTabelaByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .TabelaInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tabela> fetchTabelaByRequest(TabelaInquiryRequest request)
	{
		return getTabelaDAC().fetchTabelaByRequest(request);
	}

}
