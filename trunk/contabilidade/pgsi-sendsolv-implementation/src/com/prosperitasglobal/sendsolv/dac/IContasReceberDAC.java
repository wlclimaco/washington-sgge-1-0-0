package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.ContaReceber;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IContasReceberDAC
{
	/**
	 * Update contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateContasReceber(ContaReceber contasReceber, InternalResultsResponse<?> response);

	/**
	 * Insert contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertContasReceber(ContaReceber contasReceber, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessContasReceber(ContaReceber contasReceber, InternalResultsResponse<?> response);

	/**
	 * Delete person contasReceber.
	 *
	 * @param contasReceber the contasReceber
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonContasReceber(ContaReceber contasReceber, InternalResultsResponse<?> response);

	/**
	 * Fetch contasReceber by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< contasReceber>
	 */
	public InternalResultsResponse<ContaReceber> fetchContasReceberByParent(Integer parentId,
			BusinessTypeEnum parentType);

	/**
	 * Fetch contasReceber by id.
	 *
	 * @param id the id
	 * @return the internal results response< contasReceber>
	 */
	public InternalResultsResponse<ContaReceber> fetchContasReceberById(Integer id);

	/**
	 * Maintain contasReceber associations.
	 *
	 * @param contasReceberList the contasReceber list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainContasReceberAssociations(List<ContaReceber> contasReceberList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}