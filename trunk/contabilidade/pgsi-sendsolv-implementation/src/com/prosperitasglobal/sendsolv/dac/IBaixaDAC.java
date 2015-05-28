package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IProdutoDAC.
 */
public interface IBaixaDAC
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

}