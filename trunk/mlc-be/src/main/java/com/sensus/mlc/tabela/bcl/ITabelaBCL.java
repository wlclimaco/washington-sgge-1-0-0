package com.sensus.mlc.tabela.bcl;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tabela.model.Tabela;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionBCL.
 *
 * @author - QAT Brazil.
 *
 */
public interface ITabelaBCL
{

	/**
	 * Insert Tabela.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public InternalResultsResponse<Tabela> insertTabela(TabelaRequest TabelaRequest);

	/**
	 * Update Tabela.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public InternalResultsResponse<Tabela> updateTabela(TabelaRequest TabelaRequest);

	/**
	 * Delete Tabela.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public InternalResponse deleteTabela(TabelaRequest TabelaRequest);

	/**
	 * Fetch all Tabela.
	 *
	 * @param inquiryTabelaRequest the inquiryTabela request
	 * @return the inquiry Tabela response
	 */
	public InternalResultsResponse<Tabela> fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest);

	/**
	 * Fetch Tabela by id.
	 *
	 * @param inquiryTabelaRequest the inquiryTabela request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tabela> fetchTabelaById(TabelaRequest TabelaRequest);

	/**
	 * Fetch all Tabela types.
	 *
	 * @param request the request
	 * @return the Tabela response
	 */
	public InternalResultsResponse<Tabela> fetchAllTabelaTypes(InquiryTabelaRequest inquiryTabelaRequest);

	/**
	 * Fetch all Tabela filial.
	 *
	 * @param request the request
	 * @return the Tabela response
	 */
	public InternalResultsResponse<Tabela> fetchAllTabelaFilial(TabelaRequest TabelaRequest);

}
