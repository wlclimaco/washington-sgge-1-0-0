package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC;
import com.prosperitasglobal.sendsolv.dac.IPessoaDAC;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FuncionarioMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class FuncionarioBACImpl.
 */
public class FuncionarioBACImpl implements IFuncionarioBAC
{

	/** The person dac. */
	private IPessoaDAC pessoaDAC;

	/**
	 * @return the pessoaDAC
	 */
	public IPessoaDAC getPessoaDAC()
	{
		return pessoaDAC;
	}

	/**
	 * @param pessoaDAC the pessoaDAC to set
	 */
	public void setPessoaDAC(IPessoaDAC pessoaDAC)
	{
		this.pessoaDAC = pessoaDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#insertFuncionario(FuncionarioRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		response = getPessoaDAC().insertFuncionario(request.getFuncionario());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#updateFuncionario(FuncionarioRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		response = getPessoaDAC().updateFuncionario(request.getFuncionario());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#deleteFuncionario
	 * (com.prosperitasglobal.sendsolv.model.request.FuncionarioRequest
	 * )
	 */
	@Override
	public InternalResponse deleteFuncionario(FuncionarioMaintenanceRequest request)
	{
		return getPessoaDAC().deleteFuncionario(request.getFuncionario());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#fetchFuncionarioById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
	{
		return getPessoaDAC().fetchFuncionarioById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#fetchFuncionarioByRequest(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .FuncionarioInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(FuncionarioInquiryRequest request)
	{
		return getPessoaDAC().fetchFuncionarioByRequest(request);
	}

}
