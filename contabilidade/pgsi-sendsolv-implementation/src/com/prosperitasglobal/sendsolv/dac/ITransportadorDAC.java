package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Transportador;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ITransportadorDAC.
 */
public interface ITransportadorDAC
{

	/**
	 * Update transporte.
	 *
	 * @param transporte the transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateTransportador(Transportador transporte, InternalResultsResponse<?> response);

	/**
	 * Insert transporte.
	 *
	 * @param transporte the transporte
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertTransportador(Transportador transporte, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete transporte.
	 *
	 * @param transporte the transporte
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteTransportador(Transportador transporte, InternalResultsResponse<?> response);

	/**
	 * Fetch transporte by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Transportador> fetchTransportadorByRequest(PagedInquiryRequest request);

}
