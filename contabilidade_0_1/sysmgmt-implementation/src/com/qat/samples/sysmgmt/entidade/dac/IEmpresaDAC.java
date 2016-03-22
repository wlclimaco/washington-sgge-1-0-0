package com.qat.samples.sysmgmt.entidade.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.entidade.Deposito;
import com.qat.samples.sysmgmt.entidade.Empresa;
import com.qat.samples.sysmgmt.entidade.Filial;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

/**
 * The Interface IEmpresaDAC.
 */
public interface IEmpresaDAC
{

	/**
	 * Update empresa.
	 * 
	 * @param empresa the empresa
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> updateEmpresa(Empresa empresa);

	/**
	 * Insert empresa.
	 * 
	 * @param empresa the empresa
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa);

	/**
	 * Delete empresa.
	 * 
	 * @param empresa the empresa
	 * @return the internal response
	 */
	public InternalResponse deleteEmpresa(Empresa empresa);

	/**
	 * Fetch empresa by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	/**
	 * Fetch empresa by request.
	 * 
	 * @param request the request
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(EmpresaInquiryRequest request);

	// Condominio
	public InternalResultsResponse<Condominio> insertCondominio(Condominio filial);

	public InternalResultsResponse<Condominio> updateCondominio(Condominio filial);

	public InternalResponse deleteCondominio(Condominio filial);

	public InternalResultsResponse<Condominio> fetchCondominioById(FetchByIdRequest request);

	public InternalResultsResponse<Condominio> fetchCondominioByRequest(CondominioInquiryRequest request);

	// filial
	public InternalResultsResponse<Filial> insertFilial(Filial filial);

	public InternalResultsResponse<Filial> updateFilial(Filial filial);

	public InternalResponse deleteFilial(Filial filial);

	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

	public InternalResultsResponse<Filial> fetchFilialByRequest(FilialInquiryRequest request);

	// deposito
	public InternalResultsResponse<Deposito> insertDeposito(Deposito deposito);

	public InternalResultsResponse<Deposito> updateDeposito(Deposito deposito);

	public InternalResponse deleteDeposito(Deposito deposito);

	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

	public InternalResultsResponse<Deposito> fetchDepositoByRequest(DepositoInquiryRequest request);

	// outros
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(CnaeInquiryRequest cnae);

	public InternalResultsResponse<Regime> fetchRegimeByRequest(RegimeInquiryRequest regime);

	public InternalResultsResponse<Cidade> fetchCidadeByRequest(CidadeInquiryRequest cidade);

	public InternalResultsResponse<Cidade> insertCidade(Cidade cidade);

	public InternalResultsResponse<Cidade> updateCidade(Cidade cidade);

	public InternalResponse deleteCidade(Cidade cidade);

	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest plano);

	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest classificacao);

}
