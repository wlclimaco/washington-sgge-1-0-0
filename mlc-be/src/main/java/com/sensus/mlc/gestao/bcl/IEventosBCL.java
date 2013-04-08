package com.sensus.mlc.gestao.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.gestao.model.Eventos;
import com.sensus.mlc.gestao.model.request.EventosRequest;
import com.sensus.mlc.gestao.model.request.InquiryEventosRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - Washington
 *
 */
public interface IEventosBCL
{

	/**
	 * Insert eventos.
	 *
	 * @param eventosRequest the eventos request
	 * @return the eventos response
	 */
	public InternalResultsResponse<Eventos> insertEventos(EventosRequest eventosRequest);

	/**
	 * Update eventos.
	 *
	 * @param eventosRequest the eventos request
	 * @return the eventos response
	 */
	public InternalResultsResponse<Eventos> updateEventos(EventosRequest eventosRequest);

	/**
	 * Delete eventos.
	 *
	 * @param eventosRequest the eventos request
	 * @return the eventos response
	 */
	public InternalResponse deleteEventos(EventosRequest eventosRequest);

	/**
	 * Fetch all eventos.
	 *
	 * @param inquiryeventosRequest the inquiryeventos request
	 * @return the inquiry eventos response
	 */
	public InternalResultsResponse<Eventos> fetchAllEventos(InquiryEventosRequest inquiryeventosRequest);

	/**
	 * Fetch eventos by id.
	 *
	 * @param inquiryeventosRequest the inquiryeventos request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Eventos> fetchEventosById(EventosRequest eventosRequest);

	/**
	 * Fetch all eventos types.
	 *
	 * @param request the request
	 * @return the eventos response
	 */
	public InternalResultsResponse<Eventos> fetchAllEventosTypes(InquiryEventosRequest inquiryeventosRequest);

	/**
	 * Fetch all eventos filial.
	 *
	 * @param request the request
	 * @return the eventos response
	 */
	public InternalResultsResponse<Eventos> fetchAllEventosFilial(EventosRequest eventosRequest);

}
