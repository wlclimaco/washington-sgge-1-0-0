package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.Telefone;

/**
 * The Interface ITelefoneDAC.
 */
public interface ITelefoneDAC
{

	/**
	 * Update telefone.
	 * 
	 * @param email the email
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTelefone(Telefone email, InternalResultsResponse<?> response);

	/**
	 * Insert Telefone.
	 * 
	 * @param email the email
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTelefone(Telefone email, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business Telefone.
	 * 
	 * @param email the email
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessTelefone(Telefone email, InternalResultsResponse<?> response);

	/**
	 * Delete person Telefone.
	 * 
	 * @param email the email
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonTelefone(Telefone email, InternalResultsResponse<?> response);

	/**
	 * Fetch Telefone by id.
	 * 
	 * @param id the id
	 * @return the internal results response< Telefone>
	 */
	public InternalResultsResponse<Telefone> fetchTelefoneById(Integer id);

	/**
	 * Fetch telefone by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Telefone> fetchTelefoneByRequest(PagedInquiryRequest request);
}
