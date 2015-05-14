package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.IArquivoBAC;

/**
 * The Class ArquivoBACImpl.
 */
public class ArquivoBACImpl implements IArquivoBAC
{

	/** The person dac. */
	private IArquivoDAC arquivoDAC;

	/**
	 * @return the arquivoDAC
	 */
	public IArquivoDAC getArquivoDAC()
	{
		return arquivoDAC;
	}

	/**
	 * @param arquivoDAC the arquivoDAC to set
	 */
	public void setArquivoDAC(IArquivoDAC arquivoDAC)
	{
		this.arquivoDAC = arquivoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IArquivoBAC#insertArquivo(ArquivoRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> insertArquivo(ArquivoMaintenanceRequest request)
	{
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();

		response = getArquivoDAC().insertArquivo(request.getArquivo());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IArquivoBAC#updateArquivo(ArquivoRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> updateArquivo(ArquivoMaintenanceRequest request)
	{
		InternalResultsResponse<Arquivo> response = new InternalResultsResponse<Arquivo>();

		response = getArquivoDAC().updateArquivo(request.getArquivo());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IArquivoBAC#deleteArquivo
	 * (com.prosperitasglobal.sendsolv.model.request.ArquivoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteArquivo(ArquivoMaintenanceRequest request)
	{
		return getArquivoDAC().deleteArquivo(request.getArquivo());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IArquivoBAC#fetchArquivoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request)
	{
		return getArquivoDAC().fetchArquivoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IArquivoBAC#fetchArquivoByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .ArquivoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Arquivo> fetchArquivoByRequest(ArquivoInquiryRequest request)
	{
		return getArquivoDAC().fetchArquivoByRequest(request);
	}
}
