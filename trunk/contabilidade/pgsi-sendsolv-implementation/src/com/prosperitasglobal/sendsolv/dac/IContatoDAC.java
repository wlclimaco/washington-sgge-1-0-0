package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Contato;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IContatoDAC.
 */
public interface IContatoDAC
{

	/**
	 * Update contato.
	 *
	 * @param contato the contato
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateContato(Contato contato, InternalResultsResponse<?> response);

	/**
	 * Insert contato.
	 *
	 * @param contato the contato
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertContato(Contato contato, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business contato.
	 *
	 * @param contato the contato
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteContato(Contato contato, InternalResultsResponse<?> response);

	/**
	 * Fetch contato by id.
	 *
	 * @param id the id
	 * @return the internal results response< contato>
	 */
	public InternalResultsResponse<Contato> fetchContatoById(Integer id);

}
