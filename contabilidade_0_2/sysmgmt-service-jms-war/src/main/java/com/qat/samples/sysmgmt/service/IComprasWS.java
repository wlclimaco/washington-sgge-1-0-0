/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.cotacao.request.CotacaoInquiryRequest;
import com.qat.samples.sysmgmt.cotacao.request.CotacaoMaintenanceRequest;
import com.qat.samples.sysmgmt.cotacao.response.CotacaoResponse;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalEntradaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.PedidoComprasMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalEntradaResponse;
import com.qat.samples.sysmgmt.nf.model.response.PedidoComprasResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IComprasBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "ComprasService", targetNamespace = "http://qat.com/jms", portName = "ComprasServicePort")
public interface IComprasWS
{

//===================================### NOTAFISCALENTRADA ####======================================

/**
	 * Rebuild a list of NotaFiscalEntradas.
	 *
	 * @param request NotaFiscalEntradaRequest object containing parameter for building the list of NotaFiscalEntrada objects.
	 *
	 * @return the NotaFiscalEntradaResponse containing the list of NotaFiscalEntradas built
	 */
	@WebMethod(action = "insertNotaFiscalEntradas")
	@WebResult(name = "insertNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalEntradaResponse insertNotaFiscalEntrada(@WebParam(name = "request") NotaFiscalEntradaMaintenanceRequest request);

	/**
	 * Rebuild a list of NotaFiscalEntradas.
	 *
	 * @param request NotaFiscalEntradaRequest object containing parameter for building the list of NotaFiscalEntrada objects.
	 *
	 * @return the NotaFiscalEntradaResponse containing the list of NotaFiscalEntradas built
	 */
	@WebMethod(action = "updateNotaFiscalEntradas")
	@WebResult(name = "updateNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalEntradaResponse updateNotaFiscalEntrada(@WebParam(name = "request") NotaFiscalEntradaMaintenanceRequest request);

	/**
	 * Rebuild a list of NotaFiscalEntradas.
	 *
	 * @param request NotaFiscalEntradaRequest object containing parameter for building the list of NotaFiscalEntrada objects.
	 *
	 * @return the NotaFiscalEntradaResponse containing the list of NotaFiscalEntradas built
	 */
	@WebMethod(action = "deleteNotaFiscalEntradas")
	@WebResult(name = "deleteNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalEntradaResponse deleteNotaFiscalEntrada(@WebParam(name = "request") NotaFiscalEntradaMaintenanceRequest request);

	/**
	 * Rebuild a list of NotaFiscalEntradas.
	 *
	 * @param request NotaFiscalEntradaRequest object containing parameter for building the list of NotaFiscalEntrada objects.
	 *
	 * @return the NotaFiscalEntradaResponse containing the list of NotaFiscalEntradas built
	 */
	@WebMethod(action = "fetchNotaFiscalEntradaById")
	@WebResult(name = "fetchNotaFiscalEntradaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalEntradaResponse fetchNotaFiscalEntradaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of NotaFiscalEntradas.
	 *
	 * @param request NotaFiscalEntradaRequest object containing parameter for building the list of NotaFiscalEntrada objects.
	 *
	 * @return the NotaFiscalEntradaResponse containing the list of NotaFiscalEntradas built
	 */
	@WebMethod(action = "fetchNotaFiscalEntradasByRequest")
	@WebResult(name = "fetchNotaFiscalEntradasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalEntradaResponse fetchNotaFiscalEntradasByRequest(@WebParam(name = "request") NotaFiscalInquiryRequest request);


	/**
	 * Rebuild a list of NotaFiscalEntradas.
	 *
	 * @param request NotaFiscalEntradaRequest object containing parameter for building the list of NotaFiscalEntrada objects.
	 *
	 * @return the NotaFiscalEntradaResponse containing the list of NotaFiscalEntradas built
	 */
	@WebMethod(action = "refreshNotaFiscalEntradas")
	@WebResult(name = "refreshNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalEntradaResponse refreshNotaFiscalEntradas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all NotaFiscalEntradas.
	 *
	 * @param request the request
	 *
	 * @return the NotaFiscalEntradaResponse containing all NotaFiscalEntrada objects
	 */
	@WebMethod(action = "fetchAllNotaFiscalEntradas")
	@WebResult(name = "fetchAllNotaFiscalEntradasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	NotaFiscalEntradaResponse fetchAllNotaFiscalEntradas(@WebParam(name = "request") FetchAllRequest request);



//===================================### PEDIDOCOMPRAS ####======================================

/**
	 * Rebuild a list of PedidoComprass.
	 *
	 * @param request PedidoComprasRequest object containing parameter for building the list of PedidoCompras objects.
	 *
	 * @return the PedidoComprasResponse containing the list of PedidoComprass built
	 */
	@WebMethod(action = "insertPedidoComprass")
	@WebResult(name = "insertPedidoComprassReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PedidoComprasResponse insertPedidoCompras(@WebParam(name = "request") PedidoComprasMaintenanceRequest request);

	/**
	 * Rebuild a list of PedidoComprass.
	 *
	 * @param request PedidoComprasRequest object containing parameter for building the list of PedidoCompras objects.
	 *
	 * @return the PedidoComprasResponse containing the list of PedidoComprass built
	 */
	@WebMethod(action = "updatePedidoComprass")
	@WebResult(name = "updatePedidoComprassReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PedidoComprasResponse updatePedidoCompras(@WebParam(name = "request") PedidoComprasMaintenanceRequest request);

	/**
	 * Rebuild a list of PedidoComprass.
	 *
	 * @param request PedidoComprasRequest object containing parameter for building the list of PedidoCompras objects.
	 *
	 * @return the PedidoComprasResponse containing the list of PedidoComprass built
	 */
	@WebMethod(action = "deletePedidoComprass")
	@WebResult(name = "deletePedidoComprassReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PedidoComprasResponse deletePedidoCompras(@WebParam(name = "request") PedidoComprasMaintenanceRequest request);

	/**
	 * Rebuild a list of PedidoComprass.
	 *
	 * @param request PedidoComprasRequest object containing parameter for building the list of PedidoCompras objects.
	 *
	 * @return the PedidoComprasResponse containing the list of PedidoComprass built
	 */
	@WebMethod(action = "fetchPedidoComprasById")
	@WebResult(name = "fetchPedidoComprasByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PedidoComprasResponse fetchPedidoComprasById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of PedidoComprass.
	 *
	 * @param request PedidoComprasRequest object containing parameter for building the list of PedidoCompras objects.
	 *
	 * @return the PedidoComprasResponse containing the list of PedidoComprass built
	 */
	@WebMethod(action = "fetchPedidoComprassByRequest")
	@WebResult(name = "fetchPedidoComprassByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PedidoComprasResponse fetchPedidoComprassByRequest(@WebParam(name = "request") PedidoComprasInquiryRequest request);


	/**
	 * Rebuild a list of PedidoComprass.
	 *
	 * @param request PedidoComprasRequest object containing parameter for building the list of PedidoCompras objects.
	 *
	 * @return the PedidoComprasResponse containing the list of PedidoComprass built
	 */
	@WebMethod(action = "refreshPedidoComprass")
	@WebResult(name = "refreshPedidoComprassReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	PedidoComprasResponse refreshPedidoComprass(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all PedidoComprass.
	 *
	 * @param request the request
	 *
	 * @return the PedidoComprasResponse containing all PedidoCompras objects
	 */
	@WebMethod(action = "fetchAllPedidoComprass")
	@WebResult(name = "fetchAllPedidoComprassReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	PedidoComprasResponse fetchAllPedidoComprass(@WebParam(name = "request") FetchAllRequest request);



//===================================### COTACAO ####======================================

/**
	 * Rebuild a list of Cotacaos.
	 *
	 * @param request CotacaoRequest object containing parameter for building the list of Cotacao objects.
	 *
	 * @return the CotacaoResponse containing the list of Cotacaos built
	 */
	@WebMethod(action = "insertCotacaos")
	@WebResult(name = "insertCotacaosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CotacaoResponse insertCotacao(@WebParam(name = "request") CotacaoMaintenanceRequest request);

	/**
	 * Rebuild a list of Cotacaos.
	 *
	 * @param request CotacaoRequest object containing parameter for building the list of Cotacao objects.
	 *
	 * @return the CotacaoResponse containing the list of Cotacaos built
	 */
	@WebMethod(action = "updateCotacaos")
	@WebResult(name = "updateCotacaosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CotacaoResponse updateCotacao(@WebParam(name = "request") CotacaoMaintenanceRequest request);

	/**
	 * Rebuild a list of Cotacaos.
	 *
	 * @param request CotacaoRequest object containing parameter for building the list of Cotacao objects.
	 *
	 * @return the CotacaoResponse containing the list of Cotacaos built
	 */
	@WebMethod(action = "deleteCotacaos")
	@WebResult(name = "deleteCotacaosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CotacaoResponse deleteCotacao(@WebParam(name = "request") CotacaoMaintenanceRequest request);

	/**
	 * Rebuild a list of Cotacaos.
	 *
	 * @param request CotacaoRequest object containing parameter for building the list of Cotacao objects.
	 *
	 * @return the CotacaoResponse containing the list of Cotacaos built
	 */
	@WebMethod(action = "fetchCotacaoById")
	@WebResult(name = "fetchCotacaoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CotacaoResponse fetchCotacaoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Cotacaos.
	 *
	 * @param request CotacaoRequest object containing parameter for building the list of Cotacao objects.
	 *
	 * @return the CotacaoResponse containing the list of Cotacaos built
	 */
	@WebMethod(action = "fetchCotacaosByRequest")
	@WebResult(name = "fetchCotacaosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CotacaoResponse fetchCotacaosByRequest(@WebParam(name = "request") CotacaoInquiryRequest request);


	/**
	 * Rebuild a list of Cotacaos.
	 *
	 * @param request CotacaoRequest object containing parameter for building the list of Cotacao objects.
	 *
	 * @return the CotacaoResponse containing the list of Cotacaos built
	 */
	@WebMethod(action = "refreshCotacaos")
	@WebResult(name = "refreshCotacaosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CotacaoResponse refreshCotacaos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Cotacaos.
	 *
	 * @param request the request
	 *
	 * @return the CotacaoResponse containing all Cotacao objects
	 */
	@WebMethod(action = "fetchAllCotacaos")
	@WebResult(name = "fetchAllCotacaosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CotacaoResponse fetchAllCotacaos(@WebParam(name = "request") FetchAllRequest request);


}
