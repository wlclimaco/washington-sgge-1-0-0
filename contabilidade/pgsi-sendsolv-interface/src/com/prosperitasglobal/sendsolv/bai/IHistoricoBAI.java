package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.AlertasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.AlertasResponse;
import com.prosperitasglobal.sendsolv.model.response.HistoricoResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface IHistoricoBAI.
 */
public interface IHistoricoBAI
{

	/**
	 * Insert historico.
	 *
	 * @param request the request
	 * @return the historico response
	 */
	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request);

	/**
	 * Update historico.
	 *
	 * @param request the request
	 * @return the historico response
	 */
	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request);

	/**
	 * Delete historico.
	 *
	 * @param request the request
	 * @return the historico response
	 */
	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request);

	/**
	 * Fetch historico by id.
	 *
	 * @param request the request
	 * @return the historico response
	 */
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request);

	/**
	 * Fetch historico by request.
	 *
	 * @param request the request
	 * @return the historico response
	 */
	public HistoricoResponse fetchHistoricoByRequest(HistoricoInquiryRequest request);

	/**
	 * Fetch alertas by request.
	 *
	 * @param request the request
	 * @return the alertas response
	 */
	public AlertasResponse fetchAlertasByRequest(AlertasInquiryRequest request);

}