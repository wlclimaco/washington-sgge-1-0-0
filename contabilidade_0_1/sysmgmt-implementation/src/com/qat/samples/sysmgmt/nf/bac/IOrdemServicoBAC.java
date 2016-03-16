package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.OrdemServico;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.OrdemServicoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
