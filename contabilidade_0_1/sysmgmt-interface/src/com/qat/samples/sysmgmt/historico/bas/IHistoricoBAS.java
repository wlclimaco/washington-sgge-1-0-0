package com.qat.samples.sysmgmt.historico.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.qat.samples.sysmgmt.historico.model.request.HistoricoInquiryRequest;
import com.qat.samples.sysmgmt.historico.model.request.HistoricoMaintenanceRequest;
import com.qat.samples.sysmgmt.historico.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IHistoricoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "HistoricoService", targetNamespace = "http://qat.com/sysmgmt", portName = "HistoricoServicePort")
public interface IHistoricoBAS
{
	@WebMethod(action = "insertHistorico")
	@WebResult(name = "insertHistoricoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public HistoricoResponse insertHistorico(HistoricoMaintenanceRequest request);

	@WebMethod(action = "updateHistorico")
	@WebResult(name = "updateHistoricoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public HistoricoResponse updateHistorico(HistoricoMaintenanceRequest request);

	@WebMethod(action = "deleteHistorico")
	@WebResult(name = "deleteHistoricoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public HistoricoResponse deleteHistorico(HistoricoMaintenanceRequest request);

	@WebMethod(action = "fetchHistoricoById")
	@WebResult(name = "fetchHistoricoByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public HistoricoResponse fetchHistoricoById(FetchByIdRequest request);

	@WebMethod(action = "fetchHistoricoByRequest")
	@WebResult(name = "fetchHistoricoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public HistoricoResponse fetchHistoricoByRequest(HistoricoInquiryRequest request);

}
