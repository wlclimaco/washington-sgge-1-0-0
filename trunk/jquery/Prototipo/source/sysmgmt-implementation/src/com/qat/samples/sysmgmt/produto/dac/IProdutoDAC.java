package com.qat.samples.sysmgmt.produto.dac;

import java.util.List;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Interface IProdutoDAC. (Data Access Component - DAC)
 */
public interface IProdutoDAC
{

	/**
	 * Insert produto.
	 * 
	 * @param produto the produto
	 * @return the internal response
	 */
	public InternalResponse insertProduto(Produto produto);

	/**
	 * Update produto.
	 * 
	 * @param produto the produto
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateProduto(Produto produto);

	/**
	 * Delete produto.
	 * 
	 * @param produto the produto
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteProduto(Produto produto);

	/**
	 * Delete all produtos.
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteAllProdutos();

	/**
	 * Fetch all produtos.
	 * 
	 * @return the list< produto>
	 */
	public List<Produto> fetchAllProdutos();

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * @return the cached results response
	 */

	public Produto fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch produtos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request);

}
