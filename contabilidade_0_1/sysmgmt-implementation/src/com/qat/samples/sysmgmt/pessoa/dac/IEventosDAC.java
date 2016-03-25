package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dp.EventoPessoa;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IEventosDAC.
 */
public interface IEventosDAC
{

	/**
	 * Update evento.
	 * 
	 * @param evento the evento
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateEvento(Eventos evento, InternalResultsResponse<?> response);

	/**
	 * Insert evento.
	 * 
	 * @param evento the evento
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEvento(Eventos evento, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete evento.
	 * 
	 * @param evento the evento
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEvento(Eventos evento, InternalResultsResponse<?> response);

	/**
	 * Fetch eventos by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Eventos> fetchEventosByRequest(PagedInquiryRequest request);

	/**
	 * Update evento pessoa.
	 * 
	 * @param evento the evento
	 * @return the integer
	 */
	public Integer updateEventoPessoa(EventoPessoa evento, InternalResultsResponse<?> response);

	/**
	 * Insert evento pessoa.
	 * 
	 * @param evento the evento
	 * @return the integer
	 */
	public Integer insertEventoPessoa(EventoPessoa evento, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete evento pessoa.
	 * 
	 * @param evento the evento
	 * @return the integer
	 */
	public Integer deleteEventoPessoa(EventoPessoa evento, InternalResultsResponse<?> response);

	public InternalResultsResponse<Eventos> fetchEventosById(FetchByIdRequest request);

}
