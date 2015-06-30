package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface IConhecimentoTransporteDAC.
 */
public interface IConhecimentoTransporteDAC
{

	/**
	 * Update evento.
	 *
	 * @param conhecimentoTransporte the conhecimento transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateEvento(ConhecimentoTransporte conhecimentoTransporte, InternalResultsResponse<?> response);

	/**
	 * Insert Evento.
	 *
	 * @param conhecimentoTransporte the conhecimento transporte
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertEvento(ConhecimentoTransporte conhecimentoTransporte, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business Evento.
	 *
	 * @param conhecimentoTransporte the conhecimento transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteEvento(ConhecimentoTransporte conhecimentoTransporte, InternalResultsResponse<?> response);

	/**
	 * Fetch conhecimento transporte by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<ConhecimentoTransporte> fetchConhecimentoTransporteByRequest(
			PagedInquiryRequest request);

}
