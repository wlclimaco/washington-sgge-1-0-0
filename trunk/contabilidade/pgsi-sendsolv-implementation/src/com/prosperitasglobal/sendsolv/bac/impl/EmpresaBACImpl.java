package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IEmpresaBAC;
import com.prosperitasglobal.sendsolv.dac.IEmpresaDAC;
import com.prosperitasglobal.sendsolv.model.Cidade;
import com.prosperitasglobal.sendsolv.model.Classificacao;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.Deposito;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.Filial;
import com.prosperitasglobal.sendsolv.model.Plano;
import com.prosperitasglobal.sendsolv.model.Regime;
import com.prosperitasglobal.sendsolv.model.request.CidadeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.CnaeInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.DepositoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.EmpresaMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.FilialMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PlanoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RegimeInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class EmpresaBACImpl.
 */
public class EmpresaBACImpl implements IEmpresaBAC
{

	/** The person dac. */
	private IEmpresaDAC empresaDAC;

	/**
	 * @return the empresaDAC
	 */
	public IEmpresaDAC getEmpresaDAC()
	{
		return empresaDAC;
	}

	/**
	 * @param empresaDAC the empresaDAC to set
	 */
	public void setEmpresaDAC(IEmpresaDAC empresaDAC)
	{
		this.empresaDAC = empresaDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#insertEmpresa(EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		response = getEmpresaDAC().insertEmpresa(request.getEmpresa());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#updateEmpresa(EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request)
	{
		InternalResultsResponse<Empresa> response = new InternalResultsResponse<Empresa>();

		response = getEmpresaDAC().updateEmpresa(request.getEmpresa());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#deleteEmpresa
	 * (com.prosperitasglobal.sendsolv.model.request.EmpresaRequest
	 * )
	 */
	@Override
	public InternalResponse deleteEmpresa(EmpresaMaintenanceRequest request)
	{
		return getEmpresaDAC().deleteEmpresa(request.getEmpresa());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#fetchEmpresaById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request)
	{
		return getEmpresaDAC().fetchEmpresaById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#fetchEmpresaByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .EmpresaInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(EmpresaInquiryRequest request)
	{
		return getEmpresaDAC().fetchEmpresaByRequest(request);
	}

	@Override
	public InternalResultsResponse<Filial> insertFilial(FilialMaintenanceRequest request)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();

		response = getEmpresaDAC().insertFilial(request.getFilial());

		return response;
	}

	@Override
	public InternalResultsResponse<Filial> updateFilial(FilialMaintenanceRequest request)
	{
		InternalResultsResponse<Filial> response = new InternalResultsResponse<Filial>();

		response = getEmpresaDAC().updateFilial(request.getFilial());

		return response;
	}

	@Override
	public InternalResponse deleteFilial(FilialMaintenanceRequest request)
	{
		return getEmpresaDAC().deleteFilial(request.getFilial());
	}

	@Override
	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request)
	{
		return getEmpresaDAC().fetchFilialById(request);
	}

	@Override
	public InternalResultsResponse<Filial> fetchFilialByRequest(FilialInquiryRequest request)
	{
		return getEmpresaDAC().fetchFilialByRequest(request);
	}

	@Override
	public InternalResultsResponse<Deposito> insertDeposito(DepositoMaintenanceRequest request)
	{
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();

		response = getEmpresaDAC().insertDeposito(request.getDeposito());

		return response;
	}

	@Override
	public InternalResultsResponse<Deposito> updateDeposito(DepositoMaintenanceRequest request)
	{
		InternalResultsResponse<Deposito> response = new InternalResultsResponse<Deposito>();

		response = getEmpresaDAC().updateDeposito(request.getDeposito());

		return response;
	}

	@Override
	public InternalResponse deleteDeposito(DepositoMaintenanceRequest request)
	{
		return getEmpresaDAC().deleteDeposito(request.getDeposito());
	}

	@Override
	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request)
	{
		return getEmpresaDAC().fetchDepositoById(request);
	}

	@Override
	public InternalResultsResponse<Deposito> fetchDepositoByRequest(DepositoInquiryRequest request)
	{
		return getEmpresaDAC().fetchDepositoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request)
	{
		return getEmpresaDAC().fetchCnaeByRequest(request);
	}

	@Override
	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request)
	{
		return getEmpresaDAC().fetchRegimeByRequest(request);
	}

	@Override
	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request)
	{
		return getEmpresaDAC().fetchCidadeByRequest(request);
	}

	@Override
	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request)
	{
		return getEmpresaDAC().fetchPlanoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request)
	{
		return getEmpresaDAC().fetchClassificacaoByRequest(request);
	}
}
