package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.ITelaBAC;
import com.prosperitasglobal.sendsolv.dac.ITelaDAC;
import com.prosperitasglobal.sendsolv.model.Tela;
import com.prosperitasglobal.sendsolv.model.request.TelaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TelaMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class TelaBACImpl.
 */
public class TelaBACImpl implements ITelaBAC
{

	/** The person dac. */
	private ITelaDAC telaDAC;

	/**
	 * @return the telaDAC
	 */
	public ITelaDAC getTelaDAC()
	{
		return telaDAC;
	}

	/**
	 * @param telaDAC the telaDAC to set
	 */
	public void setTelaDAC(ITelaDAC telaDAC)
	{
		this.telaDAC = telaDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ITelaBAC#insertTela(TelaRequest)
	 */
	@Override
	public InternalResultsResponse<Tela> insertTela(TelaMaintenanceRequest request)
	{
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();

		response = getTelaDAC().insertTela(request.getTela());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ITelaBAC#updateTela(TelaRequest)
	 */
	@Override
	public InternalResultsResponse<Tela> updateTela(TelaMaintenanceRequest request)
	{
		InternalResultsResponse<Tela> response = new InternalResultsResponse<Tela>();

		response = getTelaDAC().updateTela(request.getTela());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ITelaBAC#deleteTela
	 * (com.prosperitasglobal.sendsolv.model.request.TelaRequest
	 * )
	 */
	@Override
	public InternalResponse deleteTela(TelaMaintenanceRequest request)
	{
		return getTelaDAC().deleteTela(request.getTela());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ITelaBAC#fetchTelaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Tela> fetchTelaById(FetchByIdRequest request)
	{
		return getTelaDAC().fetchTelaById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ITelaBAC#fetchTelaByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .TelaInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Tela> fetchTelaByRequest(TelaInquiryRequest request)
	{
		return getTelaDAC().fetchTelaByRequest(request);
	}

}
