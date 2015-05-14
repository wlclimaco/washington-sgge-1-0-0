package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IGrupoSubGrupoDAC
{
	/**
	 * Update grupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateGrupo(Grupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Insert subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertGrupo(Grupo subGrupo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessGrupo(Grupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Delete person subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonGrupo(Grupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Fetch subGrupo by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< subGrupo>
	 */
	public InternalResultsResponse<Grupo> fetchGrupoByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch subGrupo by id.
	 *
	 * @param id the id
	 * @return the internal results response< subGrupo>
	 */
	public InternalResultsResponse<Grupo> fetchGrupoById(Integer id);

	/**
	 * Maintain subGrupo associations.
	 *
	 * @param subGrupoList the subGrupo list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainGrupoAssociations(List<Grupo> subGrupoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);

	public Integer updateSubGrupo(SubGrupo subGrupo, InternalResultsResponse<?> response);

	/**
	 * Insert subGrupo.
	 *
	 * @param subGrupo the subGrupo
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSubGrupo(SubGrupo grupo, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business grupo.
	 *
	 * @param grupo the grupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessSubGrupo(SubGrupo grupo, InternalResultsResponse<?> response);

	/**
	 * Delete person grupo.
	 *
	 * @param grupo the grupo
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonSubGrupo(SubGrupo grupo, InternalResultsResponse<?> response);

	/**
	 * Fetch grupo by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< grupo>
	 */
	public InternalResultsResponse<SubGrupo> fetchSubGrupoByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch grupo by id.
	 *
	 * @param id the id
	 * @return the internal results response< grupo>
	 */
	public InternalResultsResponse<SubGrupo> fetchSubGrupoById(Integer id);

	/**
	 * Maintain grupo associations.
	 *
	 * @param grupoList the grupo list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainSubGrupoAssociations(List<SubGrupo> grupoList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}