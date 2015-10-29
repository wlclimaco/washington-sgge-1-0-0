package com.qat.samples.sysmgmt.produto.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;

public interface IProdutoBAC
{

	/**
	 * Insert member.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Produto> insertProduto(ProdutoMaintenanceRequest request);

	/**
	 * Update member.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Produto> updateProduto(ProdutoMaintenanceRequest request);

	/**
	 * Delete member.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteProduto(ProdutoMaintenanceRequest request);

	/**
	 * Fetch member by id.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch member by request.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<Produto> fetchProdutoByRequest(ProdutoInquiryRequest request);

	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request);

	public InternalResultsResponse<Grupo> fetchGrupoByRequest(GrupoInquiryRequest request);

	public InternalResultsResponse<SubGrupo> fetchSubGrupoByRequest(SubGrupoInquiryRequest request);

	public InternalResultsResponse<Marca> fetchMarcaByRequest(MarcaInquiryRequest request);

	public InternalResultsResponse<Tributacao> fetchTributacaoByRequest(TributacaoInquiryRequest request);

	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request);

	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

}
