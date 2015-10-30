package com.qat.samples.sysmgmt.agencia.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaMaintenanceRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IAgenciaBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "AgenciaService", targetNamespace = "http://qat.com/sysmgmt", portName = "AgenciaServicePort")
public interface IAgenciaBAS
{
	@WebMethod(action = "insertAgencia")
	@WebResult(name = "insertAgenciaReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public AgenciaResponse insertAgencia(AgenciaMaintenanceRequest request);

	@WebMethod(action = "updateAgencia")
	@WebResult(name = "updateAgenciaReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public AgenciaResponse updateAgencia(AgenciaMaintenanceRequest request);

	@WebMethod(action = "deleteAgencia")
	@WebResult(name = "deleteAgenciaReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public AgenciaResponse deleteAgencia(AgenciaMaintenanceRequest request);

	@WebMethod(action = "fetchAgenciaById")
	@WebResult(name = "fetchAgenciaByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public AgenciaResponse fetchAgenciaById(FetchByIdRequest request);

	@WebMethod(action = "fetchAgenciaByRequest")
	@WebResult(name = "fetchAgenciaByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request);

}
