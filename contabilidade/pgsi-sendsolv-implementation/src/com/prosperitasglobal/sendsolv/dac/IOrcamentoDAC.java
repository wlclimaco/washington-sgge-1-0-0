package com.prosperitasglobal.sendsolv.dac;


/**
 * The Interface IOrcamentoDAC.
 */
public interface IOrcamentoDAC
{

	/**
	 * Update orcamento.
	 *
	 * @param orcamento the orcamento
	 * @return the internal results response< orcamento>
	 */
	public InternalResultsResponse<Orcamento> updateOrcamento(Orcamento orcamento);

	/**
	 * Insert orcamento.
	 *
	 * @param orcamento the orcamento
	 * @return the internal results response< orcamento>
	 */
	public InternalResultsResponse<Orcamento> insertOrcamento(Orcamento orcamento);

	/**
	 * Delete orcamento.
	 *
	 * @param orcamento the orcamento
	 * @return the internal response
	 */
	public InternalResponse deleteOrcamento(Orcamento orcamento);

	/**
	 * Fetch orcamento by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Orcamento> fetchOrcamentoById(FetchByIdRequest request);

	/**
	 * Fetch all orcamentos.
	 *
	 * @return the internal results response< orcamento>
	 */
	public InternalResultsResponse<Orcamento> fetchAllOrcamentos();

	/**
	 * Fetch orcamento by request.
	 *
	 * @param request the request
	 * @return the internal results response< orcamento>
	 */
	public InternalResultsResponse<Orcamento> fetchOrcamentoByRequest(PagedInquiryRequest request);

}
