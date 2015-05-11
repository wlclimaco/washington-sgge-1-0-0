package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bac.IProdutoBAC;
import com.prosperitasglobal.sendsolv.dac.IProdutoDAC;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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

}
