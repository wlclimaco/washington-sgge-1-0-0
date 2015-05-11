package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;

public interface INotaFiscalBAI
{

	public NotaFiscalResponse insertNotaFiscal(NotaFiscalMaintenanceRequest request);

	public NotaFiscalResponse updateNotaFiscal(NotaFiscalMaintenanceRequest request);

	public NotaFiscalResponse deleteNotaFiscal(NotaFiscalMaintenanceRequest request);

	public NotaFiscalResponse fetchNotaFiscalById(FetchByIdRequest request);

	public NotaFiscalResponse fetchNotaFiscalByRequest(NotaFiscalInquiryRequest request);

}