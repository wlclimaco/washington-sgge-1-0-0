package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.NotaFiscal;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface INotaFiscalDAC.
 */
public interface INotaFiscalDAC
{

	/**
	 * Update notaFiscal.
	 *
	 * @param notaFiscal the notaFiscal
	 * @return the internal results response< notaFiscal>
	 */
	public InternalResultsResponse<NotaFiscal> updateNotaFiscal(NotaFiscal notaFiscal);

	/**
	 * Insert notaFiscal.
	 *
	 * @param notaFiscal the notaFiscal
	 * @return the internal results response< notaFiscal>
	 */
	public InternalResultsResponse<NotaFiscal> insertNotaFiscal(NotaFiscal notaFiscal);

	/**
	 * Delete notaFiscal.
	 *
	 * @param notaFiscal the notaFiscal
	 * @return the internal response
	 */
	public InternalResponse deleteNotaFiscal(NotaFiscal notaFiscal);

	/**
	 * Fetch notaFiscal by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalById(FetchByIdRequest request);

	/**
	 * Fetch all notaFiscals.
	 *
	 * @return the internal results response< notaFiscal>
	 */
	public InternalResultsResponse<NotaFiscal> fetchAllNotaFiscals();

	/**
	 * Fetch notaFiscal by request.
	 *
	 * @param request the request
	 * @return the internal results response< notaFiscal>
	 */
	public InternalResultsResponse<NotaFiscal> fetchNotaFiscalByRequest(PagedInquiryRequest request);

}
