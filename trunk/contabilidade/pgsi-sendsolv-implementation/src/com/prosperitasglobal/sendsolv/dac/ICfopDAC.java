package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Cfop;
import com.qat.framework.model.response.InternalResultsResponse;

public interface ICfopDAC
{
	/**
	 * Update cfop.
	 *
	 * @param cfop the cfop
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateCfop(Cfop cfop, InternalResultsResponse<?> response);

	/**
	 * Insert cfop.
	 *
	 * @param cfop the cfop
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCfop(Cfop cfop, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business cfop.
	 *
	 * @param cfop the cfop
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessCfop(Cfop cfop, InternalResultsResponse<?> response);

	/**
	 * Delete person cfop.
	 *
	 * @param cfop the cfop
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonCfop(Cfop cfop, InternalResultsResponse<?> response);

	/**
	 * Fetch cfop by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< cfop>
	 */
	public InternalResultsResponse<Cfop> fetchCfopByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch cfop by id.
	 *
	 * @param id the id
	 * @return the internal results response< cfop>
	 */
	public InternalResultsResponse<Cfop> fetchCfopById(Integer id);

	/**
	 * Maintain cfop associations.
	 *
	 * @param cfopList the cfop list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainCfopAssociations(List<Cfop> cfopList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}