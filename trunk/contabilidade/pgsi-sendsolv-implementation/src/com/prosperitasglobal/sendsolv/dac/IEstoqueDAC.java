package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Estoque;
import com.qat.framework.model.response.InternalResultsResponse;

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

}
