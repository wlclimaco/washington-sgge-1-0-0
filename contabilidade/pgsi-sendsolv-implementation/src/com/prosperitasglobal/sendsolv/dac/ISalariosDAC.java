package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Salario;
import com.qat.framework.model.response.InternalResultsResponse;

public interface ISalariosDAC
{
	/**
	 * Update salarios.
	 *
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateSalario(Salario salarios, InternalResultsResponse<?> response);

	/**
	 * Insert salarios.
	 *
	 * @param salarios the salarios
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSalario(Salario salarios, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business salarios.
	 *
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessSalario(Salario salarios, InternalResultsResponse<?> response);

	/**
	 * Delete person salarios.
	 *
	 * @param salarios the salarios
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonSalario(Salario salarios, InternalResultsResponse<?> response);

	/**
	 * Fetch salarios by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< salarios>
	 */
	public InternalResultsResponse<Salario> fetchSalarioByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch salarios by id.
	 *
	 * @param id the id
	 * @return the internal results response< salarios>
	 */
	public InternalResultsResponse<Salario> fetchSalarioById(Integer id);

	/**
	 * Maintain salarios associations.
	 *
	 * @param salariosList the salarios list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainSalarioAssociations(List<Salario> salariosList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}