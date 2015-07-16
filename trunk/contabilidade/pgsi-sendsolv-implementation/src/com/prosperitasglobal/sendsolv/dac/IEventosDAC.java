package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IEventosDAC.
 */
public interface IEventosDAC
{

	public Integer updateEvento(Eventos evento, InternalResultsResponse<?> response);

	public Integer insertEvento(Eventos evento, String statementName, InternalResultsResponse<?> response);

	public Integer deleteEvento(Eventos evento, InternalResultsResponse<?> response);

	public InternalResultsResponse<Eventos> fetchEventosByRequest(PagedInquiryRequest request);

	public Integer updateEventoPessoa(EventoPessoa evento);

	public Integer insertEventoPessoa(EventoPessoa evento);

	public Integer deleteEventoPessoa(EventoPessoa evento);

}
