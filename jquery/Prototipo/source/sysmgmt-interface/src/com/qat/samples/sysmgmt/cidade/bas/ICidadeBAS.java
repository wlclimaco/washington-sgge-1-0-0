package com.qat.samples.sysmgmt.cidade.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.documento.model.request.DocumentoMaintenanceRequest;
import com.qat.samples.sysmgmt.documento.model.response.DocumentoResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IDocumentoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "DocumentoService", targetNamespace = "http://qat.com/sysmgmt", portName = "DocumentoServicePort")
public interface ICidadeBAS
{

	/**
	 * Insert bundle.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "insertDocumento")
	@WebResult(name = "insertDocumentoReturn")
	@WSDLDocumentation(value = "Insert a bundle record and optionally returns a list of bundles.")
	public DocumentoResponse insertDocumento(@WebParam(name = "request") DocumentoMaintenanceRequest request);

	/**
	 * Update bundle.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "updateDocumento")
	@WebResult(name = "updateDocumentoReturn")
	@WSDLDocumentation(value = "Updates the selected bundle record and optionally returns a list of bundles.")
	public DocumentoResponse updateDocumento(@WebParam(name = "request") DocumentoMaintenanceRequest request);

	/**
	 * Delete bundle.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "deleteDocumento")
	@WebResult(name = "deleteDocumentoReturn")
	@WSDLDocumentation(value = "Deletes a bundle record and optionally returns a list of bundles.")
	public DocumentoResponse deleteDocumento(@WebParam(name = "request") DocumentoMaintenanceRequest request);

	/**
	 * Fetch all bundles.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "fetchAllDocumentos")
	@WebResult(name = "fetchAllDocumentosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all bundles.")
	public DocumentoResponse fetchAllDocumentos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch bundle by id.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "fetchDocumentoById")
	@WebResult(name = "fetchDocumentoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired bundle.")
	public DocumentoResponse fetchDocumentoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh bundles.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "refreshDocumentos")
	@WebResult(name = "refreshDocumentosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the bundle tables.")
	public DocumentoResponse refreshDocumentos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch bundles by request.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	@WebMethod(action = "fetchDocumentosByRequest")
	@WebResult(name = "fetchDocumentosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of bundles paged.")
	public DocumentoResponse fetchDocumentosByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
