package com.qat.samples.sysmgmt.estado.bas;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface IEstadoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "EstadoService", targetNamespace = "http://qat.com/sysmgmt", portName = "EstadoServicePort")
public interface IEstadoBAS
{
	@WebMethod(action = "insertEstado")
	@WebResult(name = "insertEstadoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EstadoResponse insertEstado(EstadoMaintenanceRequest request);

	@WebMethod(action = "updateEstado")
	@WebResult(name = "updateEstadoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EstadoResponse updateEstado(EstadoMaintenanceRequest request);

	@WebMethod(action = "deleteEstado")
	@WebResult(name = "deleteEstadoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request);

	@WebMethod(action = "fetchEstadoById")
	@WebResult(name = "fetchEstadoByIdReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EstadoResponse fetchEstadoById(FetchByIdRequest request);

	@WebMethod(action = "fetchEstadoByRequest")
	@WebResult(name = "fetchEstadoByRequestReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

}
