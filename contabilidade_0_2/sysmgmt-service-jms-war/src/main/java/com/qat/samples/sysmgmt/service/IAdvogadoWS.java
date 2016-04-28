/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoMaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.response.AdvogadoResponse;
import com.qat.samples.sysmgmt.advocacia.response.AudienciaResponse;
import com.qat.samples.sysmgmt.advocacia.response.ProcessoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IAdvogadoBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "AdvogadoService", targetNamespace = "http://qat.com/jms", portName = "AdvogadoServicePort")
public interface IAdvogadoWS
{

//===================================### ADVOGADO ####======================================

/**
	 * Rebuild a list of Advogados.
	 *
	 * @param request AdvogadoRequest object containing parameter for building the list of Advogado objects.
	 *
	 * @return the AdvogadoResponse containing the list of Advogados built
	 */
	@WebMethod(action = "insertAdvogados")
	@WebResult(name = "insertAdvogadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AdvogadoResponse insertAdvogado(@WebParam(name = "request") AdvogadoMaintenanceRequest request);

	/**
	 * Rebuild a list of Advogados.
	 *
	 * @param request AdvogadoRequest object containing parameter for building the list of Advogado objects.
	 *
	 * @return the AdvogadoResponse containing the list of Advogados built
	 */
	@WebMethod(action = "updateAdvogados")
	@WebResult(name = "updateAdvogadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AdvogadoResponse updateAdvogado(@WebParam(name = "request") AdvogadoMaintenanceRequest request);

	/**
	 * Rebuild a list of Advogados.
	 *
	 * @param request AdvogadoRequest object containing parameter for building the list of Advogado objects.
	 *
	 * @return the AdvogadoResponse containing the list of Advogados built
	 */
	@WebMethod(action = "deleteAdvogados")
	@WebResult(name = "deleteAdvogadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AdvogadoResponse deleteAdvogado(@WebParam(name = "request") AdvogadoMaintenanceRequest request);

	/**
	 * Rebuild a list of Advogados.
	 *
	 * @param request AdvogadoRequest object containing parameter for building the list of Advogado objects.
	 *
	 * @return the AdvogadoResponse containing the list of Advogados built
	 */
	@WebMethod(action = "fetchAdvogadoById")
	@WebResult(name = "fetchAdvogadoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AdvogadoResponse fetchAdvogadoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Advogados.
	 *
	 * @param request AdvogadoRequest object containing parameter for building the list of Advogado objects.
	 *
	 * @return the AdvogadoResponse containing the list of Advogados built
	 */
	@WebMethod(action = "fetchAdvogadosByRequest")
	@WebResult(name = "fetchAdvogadosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AdvogadoResponse fetchAdvogadosByRequest(@WebParam(name = "request") AdvogadoInquiryRequest request);


	/**
	 * Rebuild a list of Advogados.
	 *
	 * @param request AdvogadoRequest object containing parameter for building the list of Advogado objects.
	 *
	 * @return the AdvogadoResponse containing the list of Advogados built
	 */
	@WebMethod(action = "refreshAdvogados")
	@WebResult(name = "refreshAdvogadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AdvogadoResponse refreshAdvogados(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Advogados.
	 *
	 * @param request the request
	 *
	 * @return the AdvogadoResponse containing all Advogado objects
	 */
	@WebMethod(action = "fetchAllAdvogados")
	@WebResult(name = "fetchAllAdvogadosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	AdvogadoResponse fetchAllAdvogados(@WebParam(name = "request") FetchAllRequest request);



//===================================### AUDIENCIA ####======================================

/**
	 * Rebuild a list of Audiencias.
	 *
	 * @param request AudienciaRequest object containing parameter for building the list of Audiencia objects.
	 *
	 * @return the AudienciaResponse containing the list of Audiencias built
	 */
	@WebMethod(action = "insertAudiencias")
	@WebResult(name = "insertAudienciasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AudienciaResponse insertAudiencia(@WebParam(name = "request") AudienciaMaintenanceRequest request);

	/**
	 * Rebuild a list of Audiencias.
	 *
	 * @param request AudienciaRequest object containing parameter for building the list of Audiencia objects.
	 *
	 * @return the AudienciaResponse containing the list of Audiencias built
	 */
	@WebMethod(action = "updateAudiencias")
	@WebResult(name = "updateAudienciasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AudienciaResponse updateAudiencia(@WebParam(name = "request") AudienciaMaintenanceRequest request);

	/**
	 * Rebuild a list of Audiencias.
	 *
	 * @param request AudienciaRequest object containing parameter for building the list of Audiencia objects.
	 *
	 * @return the AudienciaResponse containing the list of Audiencias built
	 */
	@WebMethod(action = "deleteAudiencias")
	@WebResult(name = "deleteAudienciasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AudienciaResponse deleteAudiencia(@WebParam(name = "request") AudienciaMaintenanceRequest request);

	/**
	 * Rebuild a list of Audiencias.
	 *
	 * @param request AudienciaRequest object containing parameter for building the list of Audiencia objects.
	 *
	 * @return the AudienciaResponse containing the list of Audiencias built
	 */
	@WebMethod(action = "fetchAudienciaById")
	@WebResult(name = "fetchAudienciaByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AudienciaResponse fetchAudienciaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Audiencias.
	 *
	 * @param request AudienciaRequest object containing parameter for building the list of Audiencia objects.
	 *
	 * @return the AudienciaResponse containing the list of Audiencias built
	 */
	@WebMethod(action = "fetchAudienciasByRequest")
	@WebResult(name = "fetchAudienciasByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AudienciaResponse fetchAudienciasByRequest(@WebParam(name = "request") AudienciaInquiryRequest request);


	/**
	 * Rebuild a list of Audiencias.
	 *
	 * @param request AudienciaRequest object containing parameter for building the list of Audiencia objects.
	 *
	 * @return the AudienciaResponse containing the list of Audiencias built
	 */
	@WebMethod(action = "refreshAudiencias")
	@WebResult(name = "refreshAudienciasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AudienciaResponse refreshAudiencias(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Audiencias.
	 *
	 * @param request the request
	 *
	 * @return the AudienciaResponse containing all Audiencia objects
	 */
	@WebMethod(action = "fetchAllAudiencias")
	@WebResult(name = "fetchAllAudienciasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	AudienciaResponse fetchAllAudiencias(@WebParam(name = "request") FetchAllRequest request);



//===================================### PROCESSO ####======================================

/**
	 * Rebuild a list of Processos.
	 *
	 * @param request ProcessoRequest object containing parameter for building the list of Processo objects.
	 *
	 * @return the ProcessoResponse containing the list of Processos built
	 */
	@WebMethod(action = "insertProcessos")
	@WebResult(name = "insertProcessosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProcessoResponse insertProcesso(@WebParam(name = "request") ProcessoMaintenanceRequest request);

	/**
	 * Rebuild a list of Processos.
	 *
	 * @param request ProcessoRequest object containing parameter for building the list of Processo objects.
	 *
	 * @return the ProcessoResponse containing the list of Processos built
	 */
	@WebMethod(action = "updateProcessos")
	@WebResult(name = "updateProcessosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProcessoResponse updateProcesso(@WebParam(name = "request") ProcessoMaintenanceRequest request);

	/**
	 * Rebuild a list of Processos.
	 *
	 * @param request ProcessoRequest object containing parameter for building the list of Processo objects.
	 *
	 * @return the ProcessoResponse containing the list of Processos built
	 */
	@WebMethod(action = "deleteProcessos")
	@WebResult(name = "deleteProcessosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProcessoResponse deleteProcesso(@WebParam(name = "request") ProcessoMaintenanceRequest request);

	/**
	 * Rebuild a list of Processos.
	 *
	 * @param request ProcessoRequest object containing parameter for building the list of Processo objects.
	 *
	 * @return the ProcessoResponse containing the list of Processos built
	 */
	@WebMethod(action = "fetchProcessoById")
	@WebResult(name = "fetchProcessoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProcessoResponse fetchProcessoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Processos.
	 *
	 * @param request ProcessoRequest object containing parameter for building the list of Processo objects.
	 *
	 * @return the ProcessoResponse containing the list of Processos built
	 */
	@WebMethod(action = "fetchProcessosByRequest")
	@WebResult(name = "fetchProcessosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProcessoResponse fetchProcessosByRequest(@WebParam(name = "request") ProcessoInquiryRequest request);


	/**
	 * Rebuild a list of Processos.
	 *
	 * @param request ProcessoRequest object containing parameter for building the list of Processo objects.
	 *
	 * @return the ProcessoResponse containing the list of Processos built
	 */
	@WebMethod(action = "refreshProcessos")
	@WebResult(name = "refreshProcessosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ProcessoResponse refreshProcessos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Processos.
	 *
	 * @param request the request
	 *
	 * @return the ProcessoResponse containing all Processo objects
	 */
	@WebMethod(action = "fetchAllProcessos")
	@WebResult(name = "fetchAllProcessosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ProcessoResponse fetchAllProcessos(@WebParam(name = "request") FetchAllRequest request);


}
