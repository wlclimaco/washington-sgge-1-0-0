package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
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
 * The Interface IEmpresaBAC.
 */
public interface IEmpresaBAC
{

	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request);

	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request);

	public InternalResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(EmpresaInquiryRequest request);

	// filial
	public InternalResultsResponse<Filial> insertFilial(FilialMaintenanceRequest request);

	public InternalResultsResponse<Filial> updateFilial(FilialMaintenanceRequest request);

	public InternalResponse deleteFilial(FilialMaintenanceRequest request);

	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

	public InternalResultsResponse<Filial> fetchFilialByRequest(FilialInquiryRequest request);

	// deposito
	public InternalResultsResponse<Deposito> insertDeposito(DepositoMaintenanceRequest request);

	public InternalResultsResponse<Deposito> updateDeposito(DepositoMaintenanceRequest request);

	public InternalResponse deleteDeposito(DepositoMaintenanceRequest request);

	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

	public InternalResultsResponse<Deposito> fetchDepositoByRequest(DepositoInquiryRequest request);

	// outros
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest request);

	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest request);

	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest request);

	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request);

	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

}
