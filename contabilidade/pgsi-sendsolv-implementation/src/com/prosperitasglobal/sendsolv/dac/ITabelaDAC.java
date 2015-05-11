package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Tabela;
import com.prosperitasglobal.sendsolv.model.request.TabelaInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ITabelaDAC.
 */
public interface ITabelaDAC
{

	/**
	 * Update tabela.
	 *
	 * @param tabela the tabela
	 * @return the internal results response< tabela>
	 */
	public InternalResultsResponse<Tabela> updateTabela(Tabela tabela);

	/**
	 * Insert tabela.
	 *
	 * @param tabela the tabela
	 * @return the internal results response< tabela>
	 */
	public InternalResultsResponse<Tabela> insertTabela(Tabela tabela);

	/**
	 * Delete tabela.
	 *
	 * @param tabela the tabela
	 * @return the internal response
	 */
	public InternalResponse deleteTabela(Tabela tabela);

	/**
	 * Fetch tabela by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tabela> fetchTabelaById(FetchByIdRequest request);

	/**
	 * Fetch all tabelas.
	 *
	 * @return the internal results response< tabela>
	 */
	public InternalResultsResponse<Tabela> fetchAllTabelas();

	/**
	 * Fetch tabela by request.
	 *
	 * @param request the request
	 * @return the internal results response< tabela>
	 */
	public InternalResultsResponse<Tabela> fetchTabelaByRequest(TabelaInquiryRequest request);

}
