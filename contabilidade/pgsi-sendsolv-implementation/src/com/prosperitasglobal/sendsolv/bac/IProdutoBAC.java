package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Produto;
import com.prosperitasglobal.sendsolv.model.request.ProdutoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.ProdutoMaintenanceRequest;
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

}
