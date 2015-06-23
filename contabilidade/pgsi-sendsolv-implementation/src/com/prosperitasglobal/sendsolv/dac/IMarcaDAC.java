package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Marca;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IMarcaDAC.
 */
public interface IMarcaDAC
{

	/**
	 * Update marca.
	 *
	 * @param marca the marca
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateMarca(Marca marca, InternalResultsResponse<?> response);

	/**
	 * Insert marca.
	 *
	 * @param marca the marca
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertMarca(Marca marca, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business marca.
	 *
	 * @param marca the marca
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteMarca(Marca marca, InternalResultsResponse<?> response);

	/**
	 * Fetch marca by id.
	 *
	 * @param id the id
	 * @return the internal results response< marca>
	 */
	public InternalResultsResponse<Marca> fetchMarcaById(Integer id);

}
