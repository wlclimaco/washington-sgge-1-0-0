/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.response.HistoricoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IHistoricoBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "HistoricoService", targetNamespace = "http://qat.com/jms", portName = "HistoricoServicePort")
public interface IHistoricoWS
{

//===================================### HISTORICO ####======================================

/**
	 * Rebuild a list of Historicos.
	 *
	 * @param request HistoricoRequest object containing parameter for building the list of Historico objects.
	 *
	 * @return the HistoricoResponse containing the list of Historicos built
	 */
	@WebMethod(action = "insertHistoricos")
	@WebResult(name = "insertHistoricosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoResponse insertHistorico(@WebParam(name = "request") HistoricoMaintenanceRequest request);
	
	/**
	 * Rebuild a list of Historicos.
	 *
	 * @param request HistoricoRequest object containing parameter for building the list of Historico objects.
	 *
	 * @return the HistoricoResponse containing the list of Historicos built
	 */
	@WebMethod(action = "updateHistoricos")
	@WebResult(name = "updateHistoricosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoResponse updateHistorico(@WebParam(name = "request") HistoricoMaintenanceRequest request);
	
	/**
	 * Rebuild a list of Historicos.
	 *
	 * @param request HistoricoRequest object containing parameter for building the list of Historico objects.
	 *
	 * @return the HistoricoResponse containing the list of Historicos built
	 */
	@WebMethod(action = "deleteHistoricos")
	@WebResult(name = "deleteHistoricosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoResponse deleteHistorico(@WebParam(name = "request") HistoricoMaintenanceRequest request);
	
	/**
	 * Rebuild a list of Historicos.
	 *
	 * @param request HistoricoRequest object containing parameter for building the list of Historico objects.
	 *
	 * @return the HistoricoResponse containing the list of Historicos built
	 */
	@WebMethod(action = "fetchHistoricoById")
	@WebResult(name = "fetchHistoricoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoResponse fetchHistoricoById(@WebParam(name = "request") FetchByIdRequest request);
	
	/**
	 * Rebuild a list of Historicos.
	 *
	 * @param request HistoricoRequest object containing parameter for building the list of Historico objects.
	 *
	 * @return the HistoricoResponse containing the list of Historicos built
	 */
	@WebMethod(action = "fetchHistoricosByRequest")
	@WebResult(name = "fetchHistoricosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoResponse fetchHistoricosByRequest(@WebParam(name = "request") HistoricoInquiryRequest request);
	
	
	/**
	 * Rebuild a list of Historicos.
	 *
	 * @param request HistoricoRequest object containing parameter for building the list of Historico objects.
	 *
	 * @return the HistoricoResponse containing the list of Historicos built
	 */
	@WebMethod(action = "refreshHistoricos")
	@WebResult(name = "refreshHistoricosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoResponse refreshHistoricos(@WebParam(name = "request") RefreshRequest request);
	

	/**
	 * Fetch all Historicos.
	 *
	 * @param request the request
	 *
	 * @return the HistoricoResponse containing all Historico objects
	 */
	@WebMethod(action = "fetchAllHistoricos")
	@WebResult(name = "fetchAllHistoricosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	HistoricoResponse fetchAllHistoricos(@WebParam(name = "request") FetchAllRequest request);



//===================================### HISTORICOITENS ####======================================

/**
	 * Rebuild a list of HistoricoItenss.
	 *
	 * @param request HistoricoItensRequest object containing parameter for building the list of HistoricoItens objects.
	 *
	 * @return the HistoricoItensResponse containing the list of HistoricoItenss built
	 */
	@WebMethod(action = "insertHistoricoItenss")
	@WebResult(name = "insertHistoricoItenssReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoItensResponse insertHistoricoItens(@WebParam(name = "request") HistoricoItensMaintenanceRequest request);
	
	/**
	 * Rebuild a list of HistoricoItenss.
	 *
	 * @param request HistoricoItensRequest object containing parameter for building the list of HistoricoItens objects.
	 *
	 * @return the HistoricoItensResponse containing the list of HistoricoItenss built
	 */
	@WebMethod(action = "updateHistoricoItenss")
	@WebResult(name = "updateHistoricoItenssReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoItensResponse updateHistoricoItens(@WebParam(name = "request") HistoricoItensMaintenanceRequest request);
	
	/**
	 * Rebuild a list of HistoricoItenss.
	 *
	 * @param request HistoricoItensRequest object containing parameter for building the list of HistoricoItens objects.
	 *
	 * @return the HistoricoItensResponse containing the list of HistoricoItenss built
	 */
	@WebMethod(action = "deleteHistoricoItenss")
	@WebResult(name = "deleteHistoricoItenssReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoItensResponse deleteHistoricoItens(@WebParam(name = "request") HistoricoItensMaintenanceRequest request);
	
	/**
	 * Rebuild a list of HistoricoItenss.
	 *
	 * @param request HistoricoItensRequest object containing parameter for building the list of HistoricoItens objects.
	 *
	 * @return the HistoricoItensResponse containing the list of HistoricoItenss built
	 */
	@WebMethod(action = "fetchHistoricoItensById")
	@WebResult(name = "fetchHistoricoItensByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoItensResponse fetchHistoricoItensById(@WebParam(name = "request") FetchByIdRequest request);
	
	/**
	 * Rebuild a list of HistoricoItenss.
	 *
	 * @param request HistoricoItensRequest object containing parameter for building the list of HistoricoItens objects.
	 *
	 * @return the HistoricoItensResponse containing the list of HistoricoItenss built
	 */
	@WebMethod(action = "fetchHistoricoItenssByRequest")
	@WebResult(name = "fetchHistoricoItenssByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoItensResponse fetchHistoricoItenssByRequest(@WebParam(name = "request") HistoricoItensInquiryRequest request);
	
	
	/**
	 * Rebuild a list of HistoricoItenss.
	 *
	 * @param request HistoricoItensRequest object containing parameter for building the list of HistoricoItens objects.
	 *
	 * @return the HistoricoItensResponse containing the list of HistoricoItenss built
	 */
	@WebMethod(action = "refreshHistoricoItenss")
	@WebResult(name = "refreshHistoricoItenssReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	HistoricoItensResponse refreshHistoricoItenss(@WebParam(name = "request") RefreshRequest request);
	

	/**
	 * Fetch all HistoricoItenss.
	 *
	 * @param request the request
	 *
	 * @return the HistoricoItensResponse containing all HistoricoItens objects
	 */
	@WebMethod(action = "fetchAllHistoricoItenss")
	@WebResult(name = "fetchAllHistoricoItenssReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	HistoricoItensResponse fetchAllHistoricoItenss(@WebParam(name = "request") FetchAllRequest request);


}
