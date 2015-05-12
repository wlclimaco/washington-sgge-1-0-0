package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Telefone;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

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
	 * Fetch Telefone by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< Telefone>
	 */
	public InternalResultsResponse<Telefone> fetchTelefoneByParent(Integer parentId, BusinessTypeEnum parentType);

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
