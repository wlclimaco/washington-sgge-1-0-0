package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Status;
import com.qat.framework.model.response.InternalResultsResponse;

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
	public Integer deleteStatus(Status status, InternalResultsResponse<?> response);

	/**
	 * Fetch status by id.
	 *
	 * @param id the id
	 * @return the internal results response< status>
	 */
	public InternalResultsResponse<Status> fetchStatusById(Integer id);

}