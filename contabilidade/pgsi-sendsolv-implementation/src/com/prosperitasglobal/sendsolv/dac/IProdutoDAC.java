package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Cfop;
import com.prosperitasglobal.sendsolv.model.Classificacao;
import com.prosperitasglobal.sendsolv.model.Grupo;
import com.prosperitasglobal.sendsolv.model.Marca;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.SubGrupo;
import com.prosperitasglobal.sendsolv.model.Tributacao;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.prosperitasglobal.sendsolv.model.request.CfopInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ClassificacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.GrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MarcaInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SubGrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TributacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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

}
