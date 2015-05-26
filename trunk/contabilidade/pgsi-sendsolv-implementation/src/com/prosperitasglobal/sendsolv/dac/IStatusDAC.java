package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

public interface IStatusDAC
{
	/**
	 * Update status.
	 *
	 * @param status the status
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateStatus(Status status, InternalResultsResponse<?> response);

	/**
	 * Insert status.
	 *
	 * @param status the status
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertStatus(Status status, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business status.
	 *
	 * @param status the status
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessStatus(Status status, InternalResultsResponse<?> response);

	/**
	 * Delete person status.
	 *
	 * @param status the status
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonStatus(Status status, InternalResultsResponse<?> response);

	/**
	 * Fetch status by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< status>
	 */
	public InternalResultsResponse<Status> fetchStatusByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch status by id.
	 *
	 * @param id the id
	 * @return the internal results response< status>
	 */
	public InternalResultsResponse<Status> fetchStatusById(Integer id);

	/**
	 * Maintain status associations.
	 *
	 * @param statusList the status list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainStatusAssociations(List<Status> statusList, Integer parentId,
			String associateStatement,
			InternalResultsResponse<?> response);
}