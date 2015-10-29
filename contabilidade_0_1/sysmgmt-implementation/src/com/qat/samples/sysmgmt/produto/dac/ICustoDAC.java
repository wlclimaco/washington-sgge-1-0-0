package com.qat.samples.sysmgmt.produto.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.produto.model.Custo;

/**
 * The Interface ICustoDAC.
 */
public interface ICustoDAC
{

	/**
	 * Update custo.
	 * 
	 * @param custo the custo
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateCusto(Custo custo, InternalResultsResponse<?> response);

	/**
	 * Insert custo.
	 * 
	 * @param custo the custo
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCusto(Custo custo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business custo.
	 * 
	 * @param custo the custo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteCusto(Custo custo, InternalResultsResponse<?> response);

	/**
	 * Fetch custo by id.
	 * 
	 * @param id the id
	 * @return the internal results response< custo>
	 */
	public InternalResultsResponse<Custo> fetchCustoById(Integer id);

}
