package com.qat.samples.sysmgmt.contato.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.qat.samples.sysmgmt.contato.model.request.ContatoInquiryRequest;
import com.qat.samples.sysmgmt.contato.model.request.ContatoMaintenanceRequest;
import com.qat.samples.sysmgmt.contato.model.response.ContatoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IContatoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ContatoService", targetNamespace = "http://qat.com/sysmgmt", portName = "ContatoServicePort")
public interface IContatoBAS
{
	@WebMethod(action = "insertContato")
	@WebResult(name = "insertContatoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ContatoResponse insertContato(ContatoMaintenanceRequest request);

	@WebMethod(action = "updateContato")
	@WebResult(name = "updateContatoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ContatoResponse updateContato(ContatoMaintenanceRequest request);

	@WebMethod(action = "deleteContato")
	@WebResult(name = "deleteContatoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ContatoResponse deleteContato(ContatoMaintenanceRequest request);

	@WebMethod(action = "fetchContatoById")
	@WebResult(name = "fetchContatoByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ContatoResponse fetchContatoById(FetchByIdRequest request);

	@WebMethod(action = "fetchContatoByRequest")
	@WebResult(name = "fetchContatoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ContatoResponse fetchContatoByRequest(ContatoInquiryRequest request);

}
