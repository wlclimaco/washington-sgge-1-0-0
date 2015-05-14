package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IHistoricoBAC.
 */
public interface IHistoricoBAC
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Historico> insertHistorico(HistoricoMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Historico> updateHistorico(HistoricoMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteHistorico(HistoricoMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request);

}
