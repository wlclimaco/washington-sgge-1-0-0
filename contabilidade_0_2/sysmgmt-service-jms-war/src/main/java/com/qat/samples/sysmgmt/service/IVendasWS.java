/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.NotaFiscalSaidaMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoInquiryRequest;
import com.qat.samples.sysmgmt.nf.model.request.OrcamentoMaintenanceRequest;
import com.qat.samples.sysmgmt.nf.model.response.NotaFiscalSaidaResponse;
import com.qat.samples.sysmgmt.nf.model.response.OrcamentoResponse;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoInquiryRequest;
import com.qat.samples.sysmgmt.ordemServico.model.request.OrdemServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.ordemServico.model.response.OrdemServicoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IVendasBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "VendasService", targetNamespace = "http://qat.com/jms", portName = "VendasServicePort")
public interface IVendasWS
{

//===================================### NOTAFISCALSAIDA ####======================================

/**
	 * Rebuild a list of NotaFiscalSaidas.
	 *
	 * @param request NotaFiscalSaidaRequest object containing parameter for building the list of NotaFiscalSaida objects.
	 *
	 * @return the NotaFiscalSaidaResponse containing the list of NotaFiscalSaidas built
	 */
	@WebMethod(action = "insertNotaFiscalSaidas")
	@WebResult(name = "insertNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalSaidaResponse insertNotaFiscalSaida(@WebParam(name = "request") NotaFiscalSaidaMaintenanceRequest request);

	/**
	 * Rebuild a list of NotaFiscalSaidas.
	 *
	 * @param request NotaFiscalSaidaRequest object containing parameter for building the list of NotaFiscalSaida objects.
	 *
	 * @return the NotaFiscalSaidaResponse containing the list of NotaFiscalSaidas built
	 */
	@WebMethod(action = "updateNotaFiscalSaidas")
	@WebResult(name = "updateNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalSaidaResponse updateNotaFiscalSaida(@WebParam(name = "request") NotaFiscalSaidaMaintenanceRequest request);

	/**
	 * Rebuild a list of NotaFiscalSaidas.
	 *
	 * @param request NotaFiscalSaidaRequest object containing parameter for building the list of NotaFiscalSaida objects.
	 *
	 * @return the NotaFiscalSaidaResponse containing the list of NotaFiscalSaidas built
	 */
	@WebMethod(action = "deleteNotaFiscalSaidas")
	@WebResult(name = "deleteNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalSaidaResponse deleteNotaFiscalSaida(@WebParam(name = "request") NotaFiscalSaidaMaintenanceRequest request);

	/**
	 * Rebuild a list of NotaFiscalSaidas.
	 *
	 * @param request NotaFiscalSaidaRequest object containing parameter for building the list of NotaFiscalSaida objects.
	 *
	 * @return the NotaFiscalSaidaResponse containing the list of NotaFiscalSaidas built
	 */
	@WebMethod(action = "fetchNotaFiscalSaidaById")
	@WebResult(name = "fetchNotaFiscalSaidaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalSaidaResponse fetchNotaFiscalSaidaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of NotaFiscalSaidas.
	 *
	 * @param request NotaFiscalSaidaRequest object containing parameter for building the list of NotaFiscalSaida objects.
	 *
	 * @return the NotaFiscalSaidaResponse containing the list of NotaFiscalSaidas built
	 */
	@WebMethod(action = "fetchNotaFiscalSaidasByRequest")
	@WebResult(name = "fetchNotaFiscalSaidasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalSaidaResponse fetchNotaFiscalSaidasByRequest(@WebParam(name = "request") NotaFiscalInquiryRequest request);


	/**
	 * Rebuild a list of NotaFiscalSaidas.
	 *
	 * @param request NotaFiscalSaidaRequest object containing parameter for building the list of NotaFiscalSaida objects.
	 *
	 * @return the NotaFiscalSaidaResponse containing the list of NotaFiscalSaidas built
	 */
	@WebMethod(action = "refreshNotaFiscalSaidas")
	@WebResult(name = "refreshNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	NotaFiscalSaidaResponse refreshNotaFiscalSaidas(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all NotaFiscalSaidas.
	 *
	 * @param request the request
	 *
	 * @return the NotaFiscalSaidaResponse containing all NotaFiscalSaida objects
	 */
	@WebMethod(action = "fetchAllNotaFiscalSaidas")
	@WebResult(name = "fetchAllNotaFiscalSaidasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	NotaFiscalSaidaResponse fetchAllNotaFiscalSaidas(@WebParam(name = "request") FetchAllRequest request);



//===================================### ORCAMENTO ####======================================

/**
	 * Rebuild a list of Orcamentos.
	 *
	 * @param request OrcamentoRequest object containing parameter for building the list of Orcamento objects.
	 *
	 * @return the OrcamentoResponse containing the list of Orcamentos built
	 */
	@WebMethod(action = "insertOrcamentos")
	@WebResult(name = "insertOrcamentosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrcamentoResponse insertOrcamento(@WebParam(name = "request") OrcamentoMaintenanceRequest request);

	/**
	 * Rebuild a list of Orcamentos.
	 *
	 * @param request OrcamentoRequest object containing parameter for building the list of Orcamento objects.
	 *
	 * @return the OrcamentoResponse containing the list of Orcamentos built
	 */
	@WebMethod(action = "updateOrcamentos")
	@WebResult(name = "updateOrcamentosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrcamentoResponse updateOrcamento(@WebParam(name = "request") OrcamentoMaintenanceRequest request);

	/**
	 * Rebuild a list of Orcamentos.
	 *
	 * @param request OrcamentoRequest object containing parameter for building the list of Orcamento objects.
	 *
	 * @return the OrcamentoResponse containing the list of Orcamentos built
	 */
	@WebMethod(action = "deleteOrcamentos")
	@WebResult(name = "deleteOrcamentosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrcamentoResponse deleteOrcamento(@WebParam(name = "request") OrcamentoMaintenanceRequest request);

	/**
	 * Rebuild a list of Orcamentos.
	 *
	 * @param request OrcamentoRequest object containing parameter for building the list of Orcamento objects.
	 *
	 * @return the OrcamentoResponse containing the list of Orcamentos built
	 */
	@WebMethod(action = "fetchOrcamentoById")
	@WebResult(name = "fetchOrcamentoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrcamentoResponse fetchOrcamentoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Orcamentos.
	 *
	 * @param request OrcamentoRequest object containing parameter for building the list of Orcamento objects.
	 *
	 * @return the OrcamentoResponse containing the list of Orcamentos built
	 */
	@WebMethod(action = "fetchOrcamentosByRequest")
	@WebResult(name = "fetchOrcamentosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrcamentoResponse fetchOrcamentosByRequest(@WebParam(name = "request") OrcamentoInquiryRequest request);


	/**
	 * Rebuild a list of Orcamentos.
	 *
	 * @param request OrcamentoRequest object containing parameter for building the list of Orcamento objects.
	 *
	 * @return the OrcamentoResponse containing the list of Orcamentos built
	 */
	@WebMethod(action = "refreshOrcamentos")
	@WebResult(name = "refreshOrcamentosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrcamentoResponse refreshOrcamentos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Orcamentos.
	 *
	 * @param request the request
	 *
	 * @return the OrcamentoResponse containing all Orcamento objects
	 */
	@WebMethod(action = "fetchAllOrcamentos")
	@WebResult(name = "fetchAllOrcamentosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	OrcamentoResponse fetchAllOrcamentos(@WebParam(name = "request") FetchAllRequest request);



//===================================### ORDEMSERVICO ####======================================

/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "insertOrdemServicos")
	@WebResult(name = "insertOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse insertOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "updateOrdemServicos")
	@WebResult(name = "updateOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse updateOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "deleteOrdemServicos")
	@WebResult(name = "deleteOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse deleteOrdemServico(@WebParam(name = "request") OrdemServicoMaintenanceRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "fetchOrdemServicoById")
	@WebResult(name = "fetchOrdemServicoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse fetchOrdemServicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "fetchOrdemServicosByRequest")
	@WebResult(name = "fetchOrdemServicosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse fetchOrdemServicosByRequest(@WebParam(name = "request") OrdemServicoInquiryRequest request);


	/**
	 * Rebuild a list of OrdemServicos.
	 *
	 * @param request OrdemServicoRequest object containing parameter for building the list of OrdemServico objects.
	 *
	 * @return the OrdemServicoResponse containing the list of OrdemServicos built
	 */
	@WebMethod(action = "refreshOrdemServicos")
	@WebResult(name = "refreshOrdemServicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	OrdemServicoResponse refreshOrdemServicos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all OrdemServicos.
	 *
	 * @param request the request
	 *
	 * @return the OrdemServicoResponse containing all OrdemServico objects
	 */
	@WebMethod(action = "fetchAllOrdemServicos")
	@WebResult(name = "fetchAllOrdemServicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	OrdemServicoResponse fetchAllOrdemServicos(@WebParam(name = "request") FetchAllRequest request);


}
