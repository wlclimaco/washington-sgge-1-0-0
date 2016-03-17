package com.qat.samples.sysmgmt.nf.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;

public interface IOrdemServicoBAC
{

	/**
	 * Insert member.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<OrdemServico> insertOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
	 * Update member.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<OrdemServico> updateOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
	 * Delete member.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteOrdemServico(OrdemServicoMaintenanceRequest request);

	/**
	 * Fetch member by id.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoById(FetchByIdRequest request);

	/**
	 * Fetch member by request.
	 * 
	 * @param request the request
	 * @return the internal results response< member>
	 */
	public InternalResultsResponse<OrdemServico> fetchOrdemServicoByRequest(OrdemServicoInquiryRequest request);

}
