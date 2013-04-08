package com.sensus.mlc.tabela.bcf;
import com.sensus.mlc.tabela.model.request.TabelaRequest;
import com.sensus.mlc.tabela.model.request.InquiryTabelaRequest;
import com.sensus.mlc.tabela.model.response.TabelaResponse;
import com.sensus.mlc.tabela.model.response.InquiryTabelaResponse;


/**
 * The Interface ITabelaBCF.
 *
 * @author Washington.Costa
 */
public interface ITabelaBCF
{

	/**
	 * Insert Tabela.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public TabelaResponse insertTabela(TabelaRequest TabelaRequest);

	/**
	 * Update Tabela.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public TabelaResponse updateTabela(TabelaRequest TabelaRequest);

	/**
	 * Delete Tabela.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public TabelaResponse deleteTabela(TabelaRequest TabelaRequest);

	/**
	 * Fetch all Tabela.
	 *
	 * @param inquiryTabelaRequest the inquiryTabela request
	 * @return the inquiry Tabela response
	 */
	public InquiryTabelaResponse fetchAllTabela(InquiryTabelaRequest inquiryTabelaRequest);

	/**
	 * Fetch Tabela by id.
	 *
	 * @param TabelaRequest the Tabela request
	 * @return the Tabela response
	 */
	public TabelaResponse fetchTabelaById(TabelaRequest TabelaRequest);

}
