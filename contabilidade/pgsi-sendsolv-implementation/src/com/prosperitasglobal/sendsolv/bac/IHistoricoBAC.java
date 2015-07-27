package com.prosperitasglobal.sendsolv.bac;


/**
 * The Interface IHistoricoBAC.
 */
public interface IHistoricoBAC
{

	public InternalResultsResponse<Historico> insertHistorico(HistoricoMaintenanceRequest request);

	public InternalResultsResponse<Historico> updateHistorico(HistoricoMaintenanceRequest request);

	public InternalResponse deleteHistorico(HistoricoMaintenanceRequest request);

	public InternalResultsResponse<Historico> fetchHistoricoById(FetchByIdRequest request);

	public InternalResultsResponse<Historico> fetchHistoricoByRequest(HistoricoInquiryRequest request);

	public InternalResultsResponse<Alertas> fetchAlertasByRequest(AlertasInquiryRequest request);

}
