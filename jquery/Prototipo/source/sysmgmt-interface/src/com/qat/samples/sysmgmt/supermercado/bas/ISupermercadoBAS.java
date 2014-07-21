package com.qat.samples.sysmgmt.supermercado.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.supermercado.model.request.SupermercadoMaintenanceRequest;
import com.qat.samples.sysmgmt.supermercado.model.response.SupermercadoResponse;

/**
 * The Interface ISupermercadoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "SupermercadoService", targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt", portName = "SupermercadoServicePort")
public interface ISupermercadoBAS
{

	/**
	 * Insert supermercado.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "insertSupermercado")
	@WebResult(name = "insertSupermercadoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public SupermercadoResponse insertSupermercado(@WebParam(name = "request") SupermercadoMaintenanceRequest request);

	/**
	 * Update supermercado.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "updateSupermercado")
	@WebResult(name = "updateSupermercadoReturn")
	@WSDLDocumentation(value = "Updates the selected supermercado record and optionally returns a list of supermercados.")
	public SupermercadoResponse updateSupermercado(@WebParam(name = "request") SupermercadoMaintenanceRequest request);

	/**
	 * Delete supermercado.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "deleteSupermercado")
	@WebResult(name = "deleteSupermercadoReturn")
	@WSDLDocumentation(value = "Deletes a supermercado record and optionally returns a list of supermercados.")
	public SupermercadoResponse deleteSupermercado(@WebParam(name = "request") SupermercadoMaintenanceRequest request);

	/**
	 * Fetch all supermercados.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "fetchAllSupermercados")
	@WebResult(name = "fetchAllSupermercadosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all supermercados.")
	public SupermercadoResponse fetchAllSupermercados(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch supermercado by id.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "fetchSupermercadoById")
	@WebResult(name = "fetchSupermercadoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired supermercado.")
	public SupermercadoResponse fetchSupermercadoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh supermercados.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "refreshSupermercados")
	@WebResult(name = "refreshSupermercadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the supermercado tables.")
	public SupermercadoResponse refreshSupermercados(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch supermercados by request.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	@WebMethod(action = "fetchSupermercadosByRequest")
	@WebResult(name = "fetchSupermercadosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of supermercados paged.")
	public SupermercadoResponse fetchSupermercadosByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
