package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.Orcamento;

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
	public Integer updateOrcamento(Orcamento orcamento, InternalResultsResponse<?> response);

	/**
	 * Insert orcamento.
	 * 
	 * @param orcamento the orcamento
	 * @return the internal results response< orcamento>
	 */
	public Integer insertOrcamento(Orcamento orcamento, String statementName,
			InternalResultsResponse<?> response);

	/**
	 * Delete orcamento.
	 * 
	 * @param orcamento the orcamento
	 * @return the internal response
	 */
	public Integer deleteOrcamento(Orcamento orcamento, InternalResultsResponse<?> response);

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
