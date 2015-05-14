package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Convenio;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IConvenioDAC
{
	/**
	 * Update convenio.
	 *
	 * @param convenio the convenio
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateConvenio(Convenio convenio, InternalResultsResponse<?> response);

	/**
	 * Insert convenio.
	 *
	 * @param convenio the convenio
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertConvenio(Convenio convenio, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business convenio.
	 *
	 * @param convenio the convenio
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessConvenio(Convenio convenio, InternalResultsResponse<?> response);

	/**
	 * Delete person convenio.
	 *
	 * @param convenio the convenio
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonConvenio(Convenio convenio, InternalResultsResponse<?> response);

	/**
	 * Fetch convenio by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< convenio>
	 */
	public InternalResultsResponse<Convenio> fetchConvenioByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch convenio by id.
	 *
	 * @param id the id
	 * @return the internal results response< convenio>
	 */
	public InternalResultsResponse<Convenio> fetchConvenioById(Integer id);

	/**
	 * Maintain convenio associations.
	 *
	 * @param convenioList the convenio list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainConvenioAssociations(List<Convenio> convenioList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}