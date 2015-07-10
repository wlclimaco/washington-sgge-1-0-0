package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.HistoricoItens;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IHistoricoDAC.
 */
public interface IHistoricoDAC
{

	/**
	 * Update historico.
	 *
	 * @param historico the historico
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateHistorico(Historico historico, InternalResultsResponse<?> response);

	/**
	 * Insert historico.
	 *
	 * @param historico the historico
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertHistorico(Historico historico, String statementName, InternalResultsResponse<?> response);

	public Integer insertHistoricoItens(HistoricoItens historico, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete business historico.
	 *
	 * @param historico the historico
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteHistorico(Historico historico, InternalResultsResponse<?> response);

	/**
	 * Fetch historico by id.
	 *
	 * @param id the id
	 * @return the internal results response< historico>
	 */
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request);

	/**
	 * Fetch empresa by request.
	 *
	 * @param request the request
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request);

}
