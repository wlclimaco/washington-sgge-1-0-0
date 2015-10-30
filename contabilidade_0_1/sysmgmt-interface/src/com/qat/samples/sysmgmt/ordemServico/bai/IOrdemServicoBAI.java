package com.qat.samples.sysmgmt.ordemServico.bai;

import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IOrdemServicoBAI.
 */
public interface IOrdemServicoBAI
{

	/**
	 * Insert ordem servico.
	 * 
	 * @param request the request
	 * @return the ordem servico response
	 */
	public OrdemServicoResponse insertOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
	 * Update ordem servico.
	 * 
	 * @param request the request
	 * @return the ordem servico response
	 */
	public OrdemServicoResponse updateOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
	 * Delete ordem servico.
	 * 
	 * @param request the request
	 * @return the ordem servico response
	 */
	public OrdemServicoResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
	 * Fetch ordem servico by id.
	 * 
	 * @param request the request
	 * @return the ordem servico response
	 */
	public OrdemServicoResponse fetchOrdemServicoById(FetchByIdRequest request);

	/**
	 * Fetch ordem servico by request.
	 * 
	 * @param request the request
	 * @return the ordem servico response
	 */
	public OrdemServicoResponse fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request);

}