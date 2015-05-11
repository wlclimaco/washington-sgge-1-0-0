package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC;
import com.prosperitasglobal.sendsolv.dac.IFuncionarioDAC;
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
	private IFuncionarioDAC funcionarioDAC;

	/**
	 * @return the funcionarioDAC
	 */
	public IFuncionarioDAC getFuncionarioDAC()
	{
		return funcionarioDAC;
	}

	/**
	 * @param funcionarioDAC the funcionarioDAC to set
	 */
	public void setFuncionarioDAC(IFuncionarioDAC funcionarioDAC)
	{
		this.funcionarioDAC = funcionarioDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#insertFuncionario(FuncionarioRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request)
	{
		InternalResultsResponse<Funcionario> response = new InternalResultsResponse<Funcionario>();

		response = getFuncionarioDAC().insertFuncionario(request.getFuncionario());

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

		response = getFuncionarioDAC().updateFuncionario(request.getFuncionario());

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
		return getFuncionarioDAC().deleteFuncionario(request.getFuncionario());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IFuncionarioBAC#fetchFuncionarioById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request)
	{
		return getFuncionarioDAC().fetchFuncionarioById(request);
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
		return getFuncionarioDAC().fetchFuncionarioByRequest(request);
	}

}
