package com.qat.samples.sysmgmt.produto.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Produto;

/**
 * The Interface IProdutoBAC. (Business Area Component - BAC)
 */
public interface IProdutoBAC
{

	/**
	 * Insert procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse insertProduto(Produto procedure);

	/**
	 * Update procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse updateProduto(Produto procedure);

	/**
	 * Delete procedure.
	 * 
	 * @param procedure the procedure
	 * 
	 * @return the internal response
	 */
	public InternalResponse deleteProduto(Produto procedure);

	/**
	 * Refresh procedures.
	 * 
	 * @param refreshNumber the value of the number of procedures you want refreshed
	 * 
	 */
	public void refreshProdutos(Integer refreshNumber);

	/**
	 * Fetch procedure by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Produto> fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch all procedures.
	 * 
	 * @return the internal results response< procedure>
	 */
	public InternalResultsResponse<Produto> fetchAllProdutos();

	/**
	 * Fetch procedures by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Produto> fetchProdutosByRequest(PagedInquiryRequest request);
}
