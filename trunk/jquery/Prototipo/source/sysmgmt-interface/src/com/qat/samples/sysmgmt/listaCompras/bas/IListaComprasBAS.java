package com.qat.samples.sysmgmt.listaCompras.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.listaCompras.model.request.ListaComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.listaCompras.model.response.ListaComprasResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IListaComprasBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ListaComprasService", targetNamespace = "http://qat.com/sysmgmt", portName = "ListaComprasServicePort")
public interface IListaComprasBAS
{

	/**
	 * Insert listaCompras.
	 * 
	 * @param request the request
	 * 
	 * @return the listaCompras response
	 */
	@WebMethod(action = "insertListaCompras")
	@WebResult(name = "insertListaComprasReturn")
	@WSDLDocumentation(value = "Insert a listaCompras record and optionally returns a list of listaCompras.")
	public ListaComprasResponse insertListaCompras(@WebParam(name = "request") ListaComprasMaintenanceRequest request);

	/**
	 * Update listaCompras.
	 * 
	 * @param request the request
	 * 
	 * @return the listaCompras response
	 */
	@WebMethod(action = "updateListaCompras")
	@WebResult(name = "updateListaComprasReturn")
	@WSDLDocumentation(value = "Updates the selected listaCompras record and optionally returns a list of listaCompras.")
	public ListaComprasResponse updateListaCompras(@WebParam(name = "request") ListaComprasMaintenanceRequest request);

	/**
	 * Delete listaCompras.
	 * 
	 * @param request the request
	 * 
	 * @return the listaCompras response
	 */
	@WebMethod(action = "deleteListaCompras")
	@WebResult(name = "deleteListaComprasReturn")
	@WSDLDocumentation(value = "Deletes a listaCompras record and optionally returns a list of listaCompras.")
	public ListaComprasResponse deleteListaCompras(@WebParam(name = "request") ListaComprasMaintenanceRequest request);

	/**
	 * Fetch all listaCompras.
	 * 
	 * @param request the request
	 * 
	 * @return the listaCompras response
	 */
	@WebMethod(action = "fetchAllListaCompras")
	@WebResult(name = "fetchAllListaComprasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all listaCompras.")
	public ListaComprasResponse fetchAllListaCompras(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch listaCompras by id.
	 * 
	 * @param request the request
	 * 
	 * @return the listaCompras response
	 */
	@WebMethod(action = "fetchListaComprasById")
	@WebResult(name = "fetchListaComprasByIdReturn")
	@WSDLDocumentation(value = "Returns the desired listaCompras.")
	public ListaComprasResponse fetchListaComprasById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh listaCompras.
	 * 
	 * @param request the request
	 * 
	 * @return the listaCompras response
	 */
	@WebMethod(action = "refreshListaCompras")
	@WebResult(name = "refreshListaComprasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the listaCompras tables.")
	public ListaComprasResponse refreshListaCompras(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch listaCompras by request.
	 * 
	 * @param request the request
	 * @return the listaCompras paged response
	 */
	@WebMethod(action = "fetchListaComprasByRequest")
	@WebResult(name = "fetchListaComprasByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of listaCompras paged.")
	public ListaComprasResponse fetchListaComprasByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
