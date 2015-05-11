package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Tela;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ITelaDAC.
 */
public interface ITelaDAC
{

	/**
	 * Update tela.
	 *
	 * @param tela the tela
	 * @return the internal results response< tela>
	 */
	public InternalResultsResponse<Tela> updateTela(Tela tela);

	/**
	 * Insert tela.
	 *
	 * @param tela the tela
	 * @return the internal results response< tela>
	 */
	public InternalResultsResponse<Tela> insertTela(Tela tela);

	/**
	 * Delete tela.
	 *
	 * @param tela the tela
	 * @return the internal response
	 */
	public InternalResponse deleteTela(Tela tela);

	/**
	 * Fetch tela by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tela> fetchTelaById(FetchByIdRequest request);

	/**
	 * Fetch all telas.
	 *
	 * @return the internal results response< tela>
	 */
	public InternalResultsResponse<Tela> fetchAllTelas();

	/**
	 * Fetch tela by request.
	 *
	 * @param request the request
	 * @return the internal results response< tela>
	 */
	public InternalResultsResponse<Tela> fetchTelaByRequest(PagedInquiryRequest request);

}
