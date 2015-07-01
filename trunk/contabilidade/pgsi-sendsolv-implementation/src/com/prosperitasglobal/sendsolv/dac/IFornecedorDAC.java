package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Fornecedor;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IFornecedorDAC.
 */
public interface IFornecedorDAC
{

	/**
	 * Update transporte.
	 *
	 * @param transporte the transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateFornecedor(Fornecedor transporte, InternalResultsResponse<?> response);

	/**
	 * Insert transporte.
	 *
	 * @param transporte the transporte
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertFornecedor(Fornecedor transporte, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete transporte.
	 *
	 * @param transporte the transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteFornecedor(Fornecedor transporte, InternalResultsResponse<?> response);

	/**
	 * Fetch transporte by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Fornecedor> fetchFornecedorByRequest(PagedInquiryRequest request);

}
