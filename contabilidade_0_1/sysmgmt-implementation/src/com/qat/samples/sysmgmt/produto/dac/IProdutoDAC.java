package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;

/**
 * The Interface IProdutoDAC.
 */
public interface IProdutoDAC
{

	/**
	 * Update produto.
	 * 
	 * @param produto the produto
	 * @return the internal results response< produto>
	 */
	public InternalResultsResponse<Produto> updateProduto(Produto produto);

	/**
	 * Insert produto.
	 * 
	 * @param produto the produto
	 * @return the internal results response< produto>
	 */
	public InternalResultsResponse<Produto> insertProduto(Produto produto);

	/**
	 * Delete produto.
	 * 
	 * @param produto the produto
	 * @return the internal response
	 */
	public InternalResponse deleteProduto(Produto produto);

	/**
	 * Fetch produto by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch all produtos.
	 * 
	 * @return the internal results response< produto>
	 */
	public InternalResultsResponse<Produto> fetchAllProdutos();

	/**
	 * Fetch produto by request.
	 * 
	 * @param request the request
	 * @return the internal results response< produto>
	 */
	public InternalResultsResponse<Produto> fetchProdutoByRequest(ProdutoInquiryRequest request);

	// ===================

	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request);

	public InternalResultsResponse<Grupo> fetchGrupoByRequest(GrupoInquiryRequest request);

	public InternalResultsResponse<SubGrupo> fetchSubGrupoByRequest(SubGrupoInquiryRequest request);

	public InternalResultsResponse<Marca> fetchMarcaByRequest(MarcaInquiryRequest request);

	public InternalResultsResponse<Tributacao> fetchTributacaoByRequest(TributacaoInquiryRequest request);

	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request);

	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request);

	public InternalResultsResponse<Servico> insertServico(Servico request);

	public InternalResultsResponse<Servico> updateServico(Servico request);

	public InternalResponse deleteServico(Servico request);

	public InternalResultsResponse<Servico> fetchServicoById(FetchByIdRequest request);

	public InternalResultsResponse<Servico> fetchServicoByRequest(ServicoInquiryRequest request);

	public InternalResultsResponse<Plano> insertPlano(Plano request);

	public InternalResultsResponse<Plano> updatePlano(Plano request);

	public InternalResponse deletePlano(Plano request);

	public InternalResultsResponse<Plano> fetchPlanoById(FetchByIdRequest request);

	public InternalResultsResponse<Plano> fetchPlanoByRequest(PlanoInquiryRequest request);

}
