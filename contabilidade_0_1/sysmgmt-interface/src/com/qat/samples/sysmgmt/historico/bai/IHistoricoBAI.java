package com.qat.samples.sysmgmt.historico.bai;

import com.qat.samples.sysmgmt.alerta.model.request.AlertasInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.historico.model.response.AlertasResponse;
import com.qat.samples.sysmgmt.historico.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

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