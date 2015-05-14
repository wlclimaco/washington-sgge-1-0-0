package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IContasPagarDAC
{
	/**
	 * Update contasPagar.
	 *
	 * @param contasPagar the contasPagar
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateContasPagar(ContasPagar contasPagar, InternalResultsResponse<?> response);

	/**
	 * Insert contasPagar.
	 *
	 * @param contasPagar the contasPagar
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertContasPagar(ContasPagar contasPagar, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business contasPagar.
	 *
	 * @param contasPagar the contasPagar
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessContasPagar(ContasPagar contasPagar, InternalResultsResponse<?> response);

	/**
	 * Delete person contasPagar.
	 *
	 * @param contasPagar the contasPagar
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonContasPagar(ContasPagar contasPagar, InternalResultsResponse<?> response);

	/**
	 * Fetch contasPagar by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< contasPagar>
	 */
	public InternalResultsResponse<ContasPagar> fetchContasPagarByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch contasPagar by id.
	 *
	 * @param id the id
	 * @return the internal results response< contasPagar>
	 */
	public InternalResultsResponse<ContasPagar> fetchContasPagarById(Integer id);

	/**
	 * Maintain contasPagar associations.
	 *
	 * @param contasPagarList the contasPagar list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainContasPagarAssociations(List<ContasPagar> contasPagarList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}