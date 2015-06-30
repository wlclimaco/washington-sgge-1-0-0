package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IEventosDAC.
 */
public interface IItensEspeciaisDAC
{

	/**
	 * Update evento.
	 *
	 * @param itensEspeciais the itens especiais
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateEvento(ItensEspeciais itensEspeciais, InternalResultsResponse<?> response);

	/**
	 * Insert Evento.
	 *
	 * @param itensEspeciais the itens especiais
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEvento(ItensEspeciais itensEspeciais, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business Evento.
	 *
	 * @param itensEspeciais the itens especiais
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEvento(ItensEspeciais itensEspeciais, InternalResultsResponse<?> response);

	/**
	 * Fetch eventos by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ItensEspeciais> fetchEventosByRequest(PagedInquiryRequest request);

}
