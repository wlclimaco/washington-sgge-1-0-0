package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface ITributacaoDAC.
 */
public interface ITributacaoDAC
{

	/**
	 * Update tributacao.
	 * 
	 * @param tributacao the tributacao
	 * @return the internal results response< tributacao>
	 */
	public Integer updateTributacao(Tributacao tributacao);

	/**
	 * Insert tributacao.
	 * 
	 * @param tributacao the tributacao
	 * @return the internal results response< tributacao>
	 */
	public Integer insertTributacao(Tributacao tributacao);

	/**
	 * Delete tributacao.
	 * 
	 * @param tributacao the tributacao
	 * @return the internal response
	 */
	public Integer deleteTributacao(Tributacao tributacao);

	/**
	 * Fetch tributacao by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Tributacao> fetchTributacaoById(FetchByIdRequest request);

	/**
	 * Fetch all tributacaos.
	 * 
	 * @return the internal results response< tributacao>
	 */
	public InternalResultsResponse<Tributacao> fetchAllTributacaos();

	/**
	 * Fetch tributacao by request.
	 * 
	 * @param request the request
	 * @return the internal results response< tributacao>
	 */
	public InternalResultsResponse<Tributacao> fetchTributacaoByRequest(PagedInquiryRequest request);

}
