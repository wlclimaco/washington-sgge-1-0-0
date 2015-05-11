package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;

public interface IHistoricoBAI
{

	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request);

	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request);

	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request);

	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request);

	public HistoricoResponse fetchHistoricoByRequest(HistoricoInquiryRequest request);

}