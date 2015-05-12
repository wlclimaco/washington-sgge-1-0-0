package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Eventos;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IEventosDAC.
 */
public interface IEventoDAC
{

	public Integer updateEvento(Eventos Evento, InternalResultsResponse<?> response);

	/**
	 * Insert Evento.
	 *
	 * @param Evento the Evento
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEvento(Eventos Evento, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business Evento.
	 *
	 * @param Evento the Evento
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessEvento(Eventos Evento, InternalResultsResponse<?> response);

	/**
	 * Delete person Evento.
	 *
	 * @param Evento the Evento
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonEvento(Eventos Evento, InternalResultsResponse<?> response);

	/**
	 * Fetch Evento by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< Evento>
	 */
	public InternalResultsResponse<Eventos> fetchEventoByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch Evento by id.
	 *
	 * @param id the id
	 * @return the internal results response< Evento>
	 */
	public InternalResultsResponse<Eventos> fetchEventoById(Integer id);

	public InternalResultsResponse<Eventos> fetchEventoByRequest(PagedInquiryRequest request);

}
