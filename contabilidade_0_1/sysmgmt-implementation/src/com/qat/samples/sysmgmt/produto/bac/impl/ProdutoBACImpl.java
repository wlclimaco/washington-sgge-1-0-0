package com.qat.samples.sysmgmt.produto.bac.impl;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.bac.IProdutoBAC;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
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

/**
 * The Class ProdutoBACImpl.
 */
public class ProdutoBACImpl implements IProdutoBAC
{

	/** The person dac. */
	private IProdutoDAC produtoDAC;

	/**
	 * @return the produtoDAC
	 */
	public IProdutoDAC getProdutoDAC()
	{
		return produtoDAC;
	}

	/**
	 * @param produtoDAC the produtoDAC to set
	 */
	public void setProdutoDAC(IProdutoDAC produtoDAC)
	{
		this.produtoDAC = produtoDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#insertProduto(ProdutoRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> insertProduto(ProdutoMaintenanceRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		response = getProdutoDAC().insertProduto(request.getProduto());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#updateProduto(ProdutoRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> updateProduto(ProdutoMaintenanceRequest request)
	{
		InternalResultsResponse<Produto> response = new InternalResultsResponse<Produto>();

		response = getProdutoDAC().updateProduto(request.getProduto());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IProdutoBAC#deleteProduto
	 * (com.prosperitasglobal.sendsolv.model.request.ProdutoRequest
	 * )
	 */
	@Override
	public InternalResponse deleteProduto(ProdutoMaintenanceRequest request)
	{
		return getProdutoDAC().deleteProduto(request.getProduto());
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.bac.IProdutoBAC#fetchProdutoById(FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request)
	{
		return getProdutoDAC().fetchProdutoById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.sendsolv.bac.IProdutoBAC#fetchProdutoByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .ProdutoInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Produto> fetchProdutoByRequest(ProdutoInquiryRequest request)
	{
		return getProdutoDAC().fetchProdutoByRequest(request);
	}

	@Override
	public InternalResultsResponse<UniMed> fetchUniMedByRequest(UniMedInquiryRequest request)
	{
		return getProdutoDAC().fetchUniMedByRequest(request);
	}

	@Override
	public InternalResultsResponse<Grupo> fetchGrupoByRequest(GrupoInquiryRequest request)
	{
		return getProdutoDAC().fetchGrupoByRequest(request);
	}

	@Override
	public InternalResultsResponse<SubGrupo> fetchSubGrupoByRequest(SubGrupoInquiryRequest request)
	{
		return getProdutoDAC().fetchSubGrupoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Marca> fetchMarcaByRequest(MarcaInquiryRequest request)
	{
		return getProdutoDAC().fetchMarcaByRequest(request);
	}

	@Override
	public InternalResultsResponse<Tributacao> fetchTributacaoByRequest(TributacaoInquiryRequest request)
	{
		return getProdutoDAC().fetchTributacaoByRequest(request);
	}

	@Override
	public InternalResultsResponse<Cfop> fetchCfopByRequest(CfopInquiryRequest request)
	{
		return getProdutoDAC().fetchCfopByRequest(request);
	}

	@Override
	public InternalResultsResponse<Classificacao> fetchClassificacaoByRequest(ClassificacaoInquiryRequest request)
	{
		return getProdutoDAC().fetchClassificacaoByRequest(request);
	}

}
