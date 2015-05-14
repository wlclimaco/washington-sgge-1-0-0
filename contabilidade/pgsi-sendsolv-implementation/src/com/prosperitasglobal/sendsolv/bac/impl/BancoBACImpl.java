package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IBancoBAC;
import com.prosperitasglobal.sendsolv.dac.IBancoDAC;
import com.prosperitasglobal.sendsolv.model.Banco;
import com.prosperitasglobal.sendsolv.model.request.BancoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class BancoBACImpl.
 */
public class BancoBACImpl implements IBancoBAC
{

	/** The person dac. */
	private IBancoDAC bancoDAC;

	/**
	 * @return the bancoDAC
	 */
	public IBancoDAC getBancoDAC()
	{
		return bancoDAC;
	}

	/**
	 * @param bancoDAC the bancoDAC to set
	 */
	public void setBancoDAC(IBancoDAC bancoDAC)
	{
		this.bancoDAC = bancoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IBancoBAC#insertBanco(BancoRequest)
	 */
	@Override
	public InternalResultsResponse<Banco> insertBanco(BancoMaintenanceRequest request)
	{
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		response = getBancoDAC().insertBanco(request.getBanco());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IBancoBAC#updateBanco(BancoRequest)
	 */
	@Override
	public InternalResultsResponse<Banco> updateBanco(BancoMaintenanceRequest request)
	{
		InternalResultsResponse<Banco> response = new InternalResultsResponse<Banco>();

		response = getBancoDAC().updateBanco(request.getBanco());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IBancoBAC#deleteBanco
	 * (com.prosperitasglobal.sendsolv.model.request.BancoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteBanco(BancoMaintenanceRequest request)
	{
		return getBancoDAC().deleteBanco(request.getBanco());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IBancoBAC#fetchBancoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request)
	{
		return getBancoDAC().fetchBancoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IBancoBAC#fetchBancoByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .BancoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Banco> fetchBancoByRequest(PagedInquiryRequest request)
	{
		return getBancoDAC().fetchBancoByRequest(request);
	}
}
