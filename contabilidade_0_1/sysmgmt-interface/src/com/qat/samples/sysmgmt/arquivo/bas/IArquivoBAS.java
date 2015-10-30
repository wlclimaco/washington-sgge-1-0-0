package com.qat.samples.sysmgmt.arquivo.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.response.ArquivoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IArquivoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ArquivoService", targetNamespace = "http://qat.com/sysmgmt", portName = "ArquivoServicePort")
public interface IArquivoBAS
{
	@WebMethod(action = "insertArquivo")
	@WebResult(name = "insertArquivoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ArquivoResponse insertArquivo(ArquivoMaintenanceRequest request);

	@WebMethod(action = "updateArquivo")
	@WebResult(name = "updateArquivoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ArquivoResponse updateArquivo(ArquivoMaintenanceRequest request);

	@WebMethod(action = "deleteArquivo")
	@WebResult(name = "deleteArquivoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ArquivoResponse deleteArquivo(ArquivoMaintenanceRequest request);

	@WebMethod(action = "fetchArquivoById")
	@WebResult(name = "fetchArquivoByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ArquivoResponse fetchArquivoById(FetchByIdRequest request);

	@WebMethod(action = "fetchArquivoByRequest")
	@WebResult(name = "fetchArquivoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ArquivoResponse fetchArquivoByRequest(ArquivoInquiryRequest request);

	// filial

}
