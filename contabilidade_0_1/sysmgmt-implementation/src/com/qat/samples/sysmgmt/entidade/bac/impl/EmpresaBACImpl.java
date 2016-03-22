package com.qat.samples.sysmgmt.entidade.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.entidade.Deposito;
import com.qat.samples.sysmgmt.entidade.Empresa;
import com.qat.samples.sysmgmt.entidade.Filial;
import com.qat.samples.sysmgmt.entidade.bac.IEmpresaBAC;
import com.qat.samples.sysmgmt.entidade.dac.IEmpresaDAC;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

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

	// Condominio
	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IEmpresaBAC#insertEmpresa(EmpresaRequest)
	 */
	@Override
	public InternalResultsResponse<Condominio> insertCondominio(CondominioMaintenanceRequest request)
	{
		InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();

		response = getEmpresaDAC().insertCondominio(request.getCondominio());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ICondominioBAC#updateCondominio(CondominioRequest)
	 */
	@Override
	public InternalResultsResponse<Condominio> updateCondominio(CondominioMaintenanceRequest request)
	{
		InternalResultsResponse<Condominio> response = new InternalResultsResponse<Condominio>();

		response = getEmpresaDAC().updateCondominio(request.getCondominio());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICondominioBAC#deleteCondominio
	 * (com.prosperitasglobal.sendsolv.model.request.CondominioRequest
	 * )
	 */
	@Override
	public InternalResponse deleteCondominio(CondominioMaintenanceRequest request)
	{
		return getEmpresaDAC().deleteCondominio(request.getCondominio());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.ICondominioBAC#fetchCondominioById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Condominio> fetchCondominioById(FetchByIdRequest request)
	{
		return getEmpresaDAC().fetchCondominioById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.ICondominioBAC#fetchCondominioByRequest(com.prosperitasglobal.sendsolv.model.
	 * request
	 * .CondominioInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Condominio> fetchCondominioByRequest(CondominioInquiryRequest request)
	{
		return getEmpresaDAC().fetchCondominioByRequest(request);
	}

	// FIlial

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

	@Override
	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		response = getEmpresaDAC().insertCidade(request.getCidade());

		return response;
	}

	@Override
	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request)
	{
		InternalResultsResponse<Cidade> response = new InternalResultsResponse<Cidade>();

		response = getEmpresaDAC().updateCidade(request.getCidade());

		return response;
	}

	@Override
	public InternalResponse deleteCidade(CidadeMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		response = getEmpresaDAC().deleteCidade(request.getCidade());

		return response;
	}
}
