package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IHistoricoDAC.
 */
public interface IHistoricoDAC
{

	/**
	 * Update telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Historico> updateHistorico(Historico telefone);

	/**
	 * Insert telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Historico> insertHistorico(Historico telefone);

	/**
	 * Delete telefone.
	 *
	 * @param telefone the telefone
	 * @return the internal response
	 */
	public InternalResponse deleteHistorico(Historico telefone);

	/**
	 * Fetch telefone by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request);

	/**
	 * Fetch all telefones.
	 *
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Historico> fetchAllHistoricos();

	/**
	 * Fetch telefone by request.
	 *
	 * @param request the request
	 * @return the internal results response< telefone>
	 */
	public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request);

}
