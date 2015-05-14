package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.UniMed;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IUniMedDAC
{
	/**
	 * Update uniMed.
	 *
	 * @param uniMed the uniMed
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateUniMed(UniMed uniMed, InternalResultsResponse<?> response);

	/**
	 * Insert uniMed.
	 *
	 * @param uniMed the uniMed
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertUniMed(UniMed uniMed, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business uniMed.
	 *
	 * @param uniMed the uniMed
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessUniMed(UniMed uniMed, InternalResultsResponse<?> response);

	/**
	 * Delete person uniMed.
	 *
	 * @param uniMed the uniMed
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonUniMed(UniMed uniMed, InternalResultsResponse<?> response);

	/**
	 * Fetch uniMed by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< uniMed>
	 */
	public InternalResultsResponse<UniMed> fetchUniMedByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch uniMed by id.
	 *
	 * @param id the id
	 * @return the internal results response< uniMed>
	 */
	public InternalResultsResponse<UniMed> fetchUniMedById(Integer id);

	/**
	 * Maintain uniMed associations.
	 *
	 * @param uniMedList the uniMed list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainUniMedAssociations(List<UniMed> uniMedList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}