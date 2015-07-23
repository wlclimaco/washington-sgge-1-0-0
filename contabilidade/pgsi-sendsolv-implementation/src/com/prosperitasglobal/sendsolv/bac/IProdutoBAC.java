package com.prosperitasglobal.sendsolv.bac;

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
import com.prosperitasglobal.sendsolv.model.request.ProdutoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.SubGrupoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.TributacaoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.UniMedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
