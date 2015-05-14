package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IContasReceberDAC
{
	/**
	 * Update contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateContasReceber(ContasReceber contasReceber, InternalResultsResponse<?> response);

	/**
	 * Insert contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertContasReceber(ContasReceber contasReceber, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessContasReceber(ContasReceber contasReceber, InternalResultsResponse<?> response);

	/**
	 * Delete person contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonContasReceber(ContasReceber contasReceber, InternalResultsResponse<?> response);

	/**
	 * Fetch contasReceber by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< contasReceber>
	 */
	public InternalResultsResponse<ContasReceber> fetchContasReceberByParent(Integer parentId,
			BusinessTypeEnum parentType);

	/**
	 * Fetch contasReceber by id.
	 *
	 * @param id the id
	 * @return the internal results response< contasReceber>
	 */
	public InternalResultsResponse<ContasReceber> fetchContasReceberById(Integer id);

	/**
	 * Maintain contasReceber associations.
	 *
	 * @param contasReceberList the contasReceber list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainContasReceberAssociations(List<ContasReceber> contasReceberList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}