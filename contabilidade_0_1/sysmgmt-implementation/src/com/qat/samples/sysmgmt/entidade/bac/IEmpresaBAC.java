package com.qat.samples.sysmgmt.entidade.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.entidade.Deposito;
import com.qat.samples.sysmgmt.entidade.Empresa;
import com.qat.samples.sysmgmt.entidade.Filial;
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
import com.qat.samples.sysmgmt.fiscal.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

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

	public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request);

	public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request);

	public InternalResponse deleteCidade(CidadeMaintenanceRequest request);

	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request);

	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

}
