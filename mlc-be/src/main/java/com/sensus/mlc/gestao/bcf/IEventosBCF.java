package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.EventosRequest;
import com.sensus.mlc.gestao.model.request.InquiryEventosRequest;
import com.sensus.mlc.gestao.model.response.EventosResponse;
import com.sensus.mlc.gestao.model.response.InquiryEventosResponse;


/**
 * The Interface IEventosBCF.
 *
 * @author Washington.Costa
 */
public interface IEventosBCF
{

	/**
	 * Insert eventos.
	 *
	 * @param eventosRequest the eventos request
	 * @return the eventos response
	 */
	public EventosResponse insertEventos(EventosRequest eventosRequest);

	/**
	 * Update eventos.
	 *
	 * @param eventosRequest the eventos request
	 * @return the eventos response
	 */
	public EventosResponse updateEventos(EventosRequest eventosRequest);

	/**
	 * Delete eventos.
	 *
	 * @param eventosRequest the eventos request
	 * @return the eventos response
	 */
	public EventosResponse deleteEventos(EventosRequest eventosRequest);

	/**
	 * Fetch all eventos.
	 *
	 * @param inquiryEventosRequest the inquiryEventos request
	 * @return the inquiry eventos response
	 */
	public InquiryEventosResponse fetchAllEventos(InquiryEventosRequest inquiryEventosRequest);

	/**
	 * Fetch eventos by id.
	 *
	 * @param EventosRequest the eventos request
	 * @return the eventos response
	 */
	public EventosResponse fetchEventosById(EventosRequest eventosRequest);

}
