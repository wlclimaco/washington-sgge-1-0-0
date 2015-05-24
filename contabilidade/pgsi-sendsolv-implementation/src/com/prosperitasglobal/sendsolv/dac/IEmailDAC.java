package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Email;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IEmailDAC.
 */
public interface IEmailDAC
{

	public Integer updateEmail(Email email, InternalResultsResponse<?> response);

	/**
	 * Insert Email.
	 *
	 * @param Email the Email
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEmail(Email email, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business Email.
	 *
	 * @param Email the Email
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEmail(Email email, InternalResultsResponse<?> response);
}
