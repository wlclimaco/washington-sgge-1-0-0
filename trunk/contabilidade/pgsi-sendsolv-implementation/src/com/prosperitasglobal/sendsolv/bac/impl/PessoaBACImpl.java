package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IPessoaBAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.model.Pessoa;
import com.prosperitasglobal.sendsolv.model.request.PessoaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.PessoaMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class PessoaBACImpl.
 */
public class PessoaBACImpl implements IPessoaBAC
{

	/** The person dac. */
	private IPessoaDAC PessoaDAC;

	/**
	 * @return the PessoaDAC
	 */
	public IPessoaDAC getPessoaDAC()
	{
		return PessoaDAC;
	}

	/**
	 * @param PessoaDAC the PessoaDAC to set
	 */
	public void setPessoaDAC(IPessoaDAC PessoaDAC)
	{
		this.PessoaDAC = PessoaDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IPessoaBAC#insertPessoa(PessoaRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> insertPessoa(PessoaMaintenanceRequest request)
	{
		InternalResultsResponse<Pessoa> response = new InternalResultsResponse<Pessoa>();

		response = getPessoaDAC().insertPessoa(request.getPessoa());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IPessoaBAC#updatePessoa(PessoaRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> updatePessoa(PessoaMaintenanceRequest request)
	{
		InternalResultsResponse<Pessoa> response = new InternalResultsResponse<Pessoa>();

		response = getPessoaDAC().updatePessoa(request.getPessoa());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IPessoaBAC#deletePessoa
	 * (com.prosperitasglobal.sendsolv.model.request.PessoaRequest
	 * )
	 */
	@Override
	public InternalResponse deletePessoa(PessoaMaintenanceRequest request)
	{
		return getPessoaDAC().deletePessoa(request.getPessoa());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IPessoaBAC#fetchPessoaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> fetchPessoaById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchPessoaById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IPessoaBAC#fetchPessoaByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .PessoaInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Pessoa> fetchPessoaByRequest(PessoaInquiryRequest request)
	{
		return getPessoaDAC().fetchPessoaByRequest(request);
	}
}
