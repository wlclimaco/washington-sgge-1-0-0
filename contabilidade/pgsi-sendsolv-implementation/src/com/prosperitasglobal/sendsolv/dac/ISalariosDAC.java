package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface ISalariosDAC
{
	/**
	 * Update salarios.
	 *
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateSalarios(Salarios salarios, InternalResultsResponse<?> response);

	/**
	 * Insert salarios.
	 *
	 * @param salarios the salarios
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSalarios(Salarios salarios, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business salarios.
	 *
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessSalarios(Salarios salarios, InternalResultsResponse<?> response);

	/**
	 * Delete person salarios.
	 *
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonSalarios(Salarios salarios, InternalResultsResponse<?> response);

	/**
	 * Fetch salarios by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< salarios>
	 */
	public InternalResultsResponse<Salarios> fetchSalariosByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch salarios by id.
	 *
	 * @param id the id
	 * @return the internal results response< salarios>
	 */
	public InternalResultsResponse<Salarios> fetchSalariosById(Integer id);

	/**
	 * Maintain salarios associations.
	 *
	 * @param salariosList the salarios list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainSalariosAssociations(List<Salarios> salariosList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}