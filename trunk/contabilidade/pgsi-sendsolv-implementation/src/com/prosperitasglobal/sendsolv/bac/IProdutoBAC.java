package com.prosperitasglobal.sendsolv.bac;

/**
 * The Interface IProdutoBAC.
 *
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 10:16:00 AM
 */
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

}
