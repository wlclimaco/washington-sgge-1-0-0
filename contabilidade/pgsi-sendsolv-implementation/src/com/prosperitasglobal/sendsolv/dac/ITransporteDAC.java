package com.prosperitasglobal.sendsolv.dac;

/**
 * The Interface ITransporteDAC.
 */
public interface ITransporteDAC
{

	/**
	 * Update transporte.
	 *
	 * @param transporte the transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTransporte(Transporte transporte, InternalResultsResponse<?> response);

	/**
	 * Insert transporte.
	 *
	 * @param transporte the transporte
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTransporte(Transporte transporte, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete transporte.
	 *
	 * @param transporte the transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteTransporte(Transporte transporte, InternalResultsResponse<?> response);

	/**
	 * Fetch transporte by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Transporte> fetchTransporteByRequest(PagedInquiryRequest request);

}
