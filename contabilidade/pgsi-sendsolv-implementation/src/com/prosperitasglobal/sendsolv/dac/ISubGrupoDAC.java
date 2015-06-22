package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface ISubGrupoDAC.
 */
public interface ISubGrupoDAC
{

	/**
	 * Update subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Insert subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSubGrupo(SubGrupo subGrupo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Fetch subGrupo by id.
	 *
	 * @param id the id
	 * @return the internal results response< subGrupo>
	 */
	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(Integer id);

}
