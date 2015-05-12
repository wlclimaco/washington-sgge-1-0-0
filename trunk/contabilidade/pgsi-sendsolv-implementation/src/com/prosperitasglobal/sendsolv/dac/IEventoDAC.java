package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IEventosDAC.
 */
public interface IEventoDAC
{

	/**
	 * Update telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Eventos> updateEventos(Eventos telefone);

	/**
	 * Insert telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Eventos> insertEventos(Eventos telefone);

	/**
	 * Delete telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal response
	 */
	public InternalResponse deleteEventos(Eventos telefone);

	/**
	 * Fetch telefone by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request);

	/**
	 * Fetch all telefones.
	 *
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Eventos> fetchAllEventoss();

	/**
	 * Fetch telefone by request.
	 *
	 * @param request the request
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Eventos> fetchEventosByRequest(PagedInquiryRequest request);

}
