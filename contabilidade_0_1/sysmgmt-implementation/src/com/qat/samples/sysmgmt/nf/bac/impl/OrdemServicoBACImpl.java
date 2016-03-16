package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC;
import com.prosperitasglobal.sendsolv.dac.IOrdemServicoDAC;
import com.prosperitasglobal.sendsolv.model.OrdemServico;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class OrdemServicoBACImpl.
 */
public class OrdemServicoBACImpl implements IOrdemServicoBAC
{

	/** The person dac. */
	private IOrdemServicoDAC ordemServicoDAC;

	/**
	 * @return the ordemServicoDAC
	 */
	public IOrdemServicoDAC getOrdemServicoDAC()
	{
		return ordemServicoDAC;
	}

	/**
	 * @param ordemServicoDAC the ordemServicoDAC to set
	 */
	public void setOrdemServicoDAC(IOrdemServicoDAC ordemServicoDAC)
	{
		this.ordemServicoDAC = ordemServicoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#insertOrdemServico(OrdemServicoRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		response = getOrdemServicoDAC().insertOrdemServico(request.getOrdemServico());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#updateOrdemServico(OrdemServicoRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		response = getOrdemServicoDAC().updateOrdemServico(request.getOrdemServico());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#deleteOrdemServico
	 * (com.prosperitasglobal.sendsolv.model.request.OrdemServicoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request)
	{
		return getOrdemServicoDAC().deleteOrdemServico(request.getOrdemServico());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#fetchOrdemServicoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request)
	{
		return getOrdemServicoDAC().fetchOrdemServicoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IOrdemServicoBAC#fetchOrdemServicoByRequest(com.prosperitasglobal.sendsolv.model
	 * .request
	 * .OrdemServicoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request)
	{
		return getOrdemServicoDAC().fetchOrdemServicoByRequest(request);
	}

}
