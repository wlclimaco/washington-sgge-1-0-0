package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.OrdemServico;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
	 * Fetch frequency based event by id.
	 *
	 * @param id the id
	 * @return The {@link InternalResultsResponse} containing the {@link java.util.List} of {@link FrequencyBasedEvent}
	 *         collection.
	 */
	public InternalResultsResponse<FrequencyBasedEvent> fetchFrequencyBasedEventById(Integer id);

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
