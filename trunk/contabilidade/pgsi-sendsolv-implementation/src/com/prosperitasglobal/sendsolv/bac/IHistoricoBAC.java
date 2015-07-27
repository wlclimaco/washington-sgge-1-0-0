package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Alertas;
import com.prosperitasglobal.sendsolv.model.Historico;
import com.prosperitasglobal.sendsolv.model.request.AlertasInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.HistoricoMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
