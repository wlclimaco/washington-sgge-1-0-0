/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.ComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ComprasResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.ComprasInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IComprasWS.
 */
@WebService(serviceName = "ComprasService", targetNamespace = "http://qat.com/sysmgmt", portName = "ComprasServicePort")
public interface IComprasWS
{

//===================================### NOTAFISCALENTRADA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertNotaFiscalEntrada")
	@WebResult(name = "insertNotaFiscalEntradaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public NotaFiscalEntradaResponse insertNotaFiscalEntrada(@WebParam(name = "request") NotaFiscalEntradaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateNotaFiscalEntrada")
	@WebResult(name = "updateNotaFiscalEntradaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public NotaFiscalEntradaResponse updateNotaFiscalEntrada(@WebParam(name = "request") NotaFiscalEntradaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteNotaFiscalEntrada")
	@WebResult(name = "deleteNotaFiscalEntradaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public NotaFiscalEntradaResponse deleteNotaFiscalEntrada(@WebParam(name = "request") NotaFiscalEntradaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshNotaFiscalEntradas")
	@WebResult(name = "refreshNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public NotaFiscalEntradaResponse refreshNotaFiscalEntradas(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllNotaFiscalEntradas")
	@WebResult(name = "fetchAllNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public NotaFiscalEntradaResponse fetchAllNotaFiscalEntradas(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchNotaFiscalEntradaById")
	@WebResult(name = "fetchNotaFiscalEntradaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchNotaFiscalEntradasByRequest")
	@WebResult(name = "fetchNotaFiscalEntradasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public NotaFiscalEntradaResponse fetchNotaFiscalEntradasByRequest(@WebParam(name = "request") NotaFiscalEntradaInquiryRequest request);


//===================================### PEDIDOCOMPRAS ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertPedidoCompras")
	@WebResult(name = "insertPedidoComprasReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public PedidoComprasResponse insertPedidoCompras(@WebParam(name = "request") PedidoComprasMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updatePedidoCompras")
	@WebResult(name = "updatePedidoComprasReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public PedidoComprasResponse updatePedidoCompras(@WebParam(name = "request") PedidoComprasMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deletePedidoCompras")
	@WebResult(name = "deletePedidoComprasReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public PedidoComprasResponse deletePedidoCompras(@WebParam(name = "request") PedidoComprasMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshPedidoComprass")
	@WebResult(name = "refreshPedidoComprassReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public PedidoComprasResponse refreshPedidoComprass(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllPedidoComprass")
	@WebResult(name = "fetchAllPedidoComprassReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public PedidoComprasResponse fetchAllPedidoComprass(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchPedidoComprasById")
	@WebResult(name = "fetchPedidoComprasByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public PedidoComprasResponse fetchPedidoComprasById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchPedidoComprassByRequest")
	@WebResult(name = "fetchPedidoComprassByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public PedidoComprasResponse fetchPedidoComprassByRequest(@WebParam(name = "request") PedidoComprasInquiryRequest request);


//===================================### COTACAO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCotacao")
	@WebResult(name = "insertCotacaoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public CotacaoResponse insertCotacao(@WebParam(name = "request") CotacaoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCotacao")
	@WebResult(name = "updateCotacaoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public CotacaoResponse updateCotacao(@WebParam(name = "request") CotacaoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCotacao")
	@WebResult(name = "deleteCotacaoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public CotacaoResponse deleteCotacao(@WebParam(name = "request") CotacaoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshCotacaos")
	@WebResult(name = "refreshCotacaosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public CotacaoResponse refreshCotacaos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllCotacaos")
	@WebResult(name = "fetchAllCotacaosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public CotacaoResponse fetchAllCotacaos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCotacaoById")
	@WebResult(name = "fetchCotacaoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public CotacaoResponse fetchCotacaoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCotacaosByRequest")
	@WebResult(name = "fetchCotacaosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public CotacaoResponse fetchCotacaosByRequest(@WebParam(name = "request") CotacaoInquiryRequest request);

}

