package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Grupo;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IGrupoDAC.
 */
public interface IGrupoDAC
{

	/**
	 * Update grupo.
	 *
	 * @param grupo the grupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateGrupo(Grupo grupo, InternalResultsResponse<?> response);

	/**
	 * Insert grupo.
	 *
	 * @param grupo the grupo
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertGrupo(Grupo grupo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business grupo.
	 *
	 * @param grupo the grupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteGrupo(Grupo grupo, InternalResultsResponse<?> response);

	/**
	 * Fetch grupo by id.
	 *
	 * @param id the id
	 * @return the internal results response< grupo>
	 */
	public InternalResultsResponse<Grupo> fetchGrupoById(Integer id);

}
