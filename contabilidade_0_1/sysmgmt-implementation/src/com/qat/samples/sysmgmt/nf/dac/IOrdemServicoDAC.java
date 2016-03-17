package com.qat.samples.sysmgmt.nf.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;

/**
 * The Interface IOrdemServicoDAC.
 */
public interface IOrdemServicoDAC
{

	/**
	 * Update ordemServico.
	 * 
	 * @param ordemServico the ordemServico
	 * @return the internal results response< ordemServico>
	 */
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServico ordemServico);

	/**
	 * Insert ordemServico.
	 * 
	 * @param ordemServico the ordemServico
	 * @return the internal results response< ordemServico>
	 */
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServico ordemServico);

	/**
	 * Delete ordemServico.
	 * 
	 * @param ordemServico the ordemServico
	 * @return the internal response
	 */
	public InternalResponse deleteOrdemServico(OrdemServico ordemServico);

	/**
	 * Fetch ordemServico by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
	 * Fetch all ordemServicos.
	 * 
	 * @return the internal results response< ordemServico>
	 */
	public InternalResultsResponse<OrdemServico> fetchAllOrdemServicos();

	/**
	 * Fetch ordemServico by request.
	 * 
	 * @param request the request
	 * @return the internal results response< ordemServico>
	 */
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request);

}
