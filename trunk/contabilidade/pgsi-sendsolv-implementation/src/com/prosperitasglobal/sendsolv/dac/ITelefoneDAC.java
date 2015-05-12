package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Telefone;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ITelefoneDAC.
 */
public interface ITelefoneDAC
{

	/**
	 * Update telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Telefone> updateTelefone(Telefone telefone);

	/**
	 * Insert telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Telefone> insertTelefone(Telefone telefone);

	/**
	 * Delete telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal response
	 */
	public InternalResponse deleteTelefone(Telefone telefone);

	/**
	 * Fetch telefone by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Telefone> fetchTelefoneById(FetchByIdRequest request);

	/**
	 * Fetch all telefones.
	 *
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Telefone> fetchAllTelefones();

	/**
	 * Fetch telefone by request.
	 *
	 * @param request the request
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Telefone> fetchTelefoneByRequest(PagedInquiryRequest request);

}
