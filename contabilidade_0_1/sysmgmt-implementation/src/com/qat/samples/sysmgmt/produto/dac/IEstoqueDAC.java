package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Estoque;

/**
 * The Interface IEstoqueDAC.
 */
public interface IEstoqueDAC
{

	/**
	 * Update estoque.
	 * 
	 * @param estoque the estoque
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateEstoque(Estoque estoque, InternalResultsResponse<?> response);

	/**
	 * Insert estoque.
	 * 
	 * @param estoque the estoque
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEstoque(Estoque estoque, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business estoque.
	 * 
	 * @param estoque the estoque
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEstoque(Estoque estoque, InternalResultsResponse<?> response);

	/**
	 * Fetch estoque by id.
	 * 
	 * @param id the id
	 * @return the internal results response< estoque>
	 */
	public InternalResultsResponse<Estoque> fetchEstoqueById(Integer id);

	public InternalResultsResponse<Estoque> fetchEstoqueByRequest(PagedInquiryRequest request);

}
