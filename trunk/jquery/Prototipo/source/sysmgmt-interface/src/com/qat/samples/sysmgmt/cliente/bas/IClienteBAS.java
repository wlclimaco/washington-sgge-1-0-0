package com.qat.samples.sysmgmt.cliente.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IClienteBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ClienteService", targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt", portName = "ClienteServicePort")
public interface IClienteBAS
{

	/**
	 * Insert cliente.
	 * 
	 * @param request the request
	 * 
	 * @return the cliente response
	 */
	@WebMethod(action = "insertCliente")
	@WebResult(name = "insertClienteReturn")
	@WSDLDocumentation(value = "Insert a cliente record and optionally returns a list of clientes.")
	public ClienteResponse insertCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Update cliente.
	 * 
	 * @param request the request
	 * 
	 * @return the cliente response
	 */
	@WebMethod(action = "updateCliente")
	@WebResult(name = "updateClienteReturn")
	@WSDLDocumentation(value = "Updates the selected cliente record and optionally returns a list of clientes.")
	public ClienteResponse updateCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Delete cliente.
	 * 
	 * @param request the request
	 * 
	 * @return the cliente response
	 */
	@WebMethod(action = "deleteCliente")
	@WebResult(name = "deleteClienteReturn")
	@WSDLDocumentation(value = "Deletes a cliente record and optionally returns a list of clientes.")
	public ClienteResponse deleteCliente(@WebParam(name = "request") ClienteMaintenanceRequest request);

	/**
	 * Fetch all clientes.
	 * 
	 * @param request the request
	 * 
	 * @return the cliente response
	 */
	@WebMethod(action = "fetchAllClientes")
	@WebResult(name = "fetchAllClientesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all clientes.")
	public ClienteResponse fetchAllClientes(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch cliente by id.
	 * 
	 * @param request the request
	 * 
	 * @return the cliente response
	 */
	@WebMethod(action = "fetchClienteById")
	@WebResult(name = "fetchClienteByIdReturn")
	@WSDLDocumentation(value = "Returns the desired cliente.")
	public ClienteResponse fetchClienteById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh clientes.
	 * 
	 * @param request the request
	 * 
	 * @return the cliente response
	 */
	@WebMethod(action = "refreshClientes")
	@WebResult(name = "refreshClientesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the cliente tables.")
	public ClienteResponse refreshClientes(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch clientes by request.
	 * 
	 * @param request the request
	 * @return the cliente paged response
	 */
	@WebMethod(action = "fetchClientesByRequest")
	@WebResult(name = "fetchClientesByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of clientes paged.")
	public ClienteResponse fetchClientesByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
