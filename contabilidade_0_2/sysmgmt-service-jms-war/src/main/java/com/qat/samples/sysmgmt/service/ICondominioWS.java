/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.condominio.model.request.AvisoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.AvisoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.response.AvisoResponse;
import com.qat.samples.sysmgmt.condominio.model.response.InquilinoResponse;
import com.qat.samples.sysmgmt.condominio.model.response.SindicoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICondominioBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "CondominioService", targetNamespace = "http://qat.com/jms", portName = "CondominioServicePort")
public interface ICondominioWS
{

//===================================### SINDICO ####======================================

/**
	 * Rebuild a list of Sindicos.
	 *
	 * @param request SindicoRequest object containing parameter for building the list of Sindico objects.
	 *
	 * @return the SindicoResponse containing the list of Sindicos built
	 */
	@WebMethod(action = "insertSindicos")
	@WebResult(name = "insertSindicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SindicoResponse insertSindico(@WebParam(name = "request") SindicoMaintenanceRequest request);

	/**
	 * Rebuild a list of Sindicos.
	 *
	 * @param request SindicoRequest object containing parameter for building the list of Sindico objects.
	 *
	 * @return the SindicoResponse containing the list of Sindicos built
	 */
	@WebMethod(action = "updateSindicos")
	@WebResult(name = "updateSindicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SindicoResponse updateSindico(@WebParam(name = "request") SindicoMaintenanceRequest request);

	/**
	 * Rebuild a list of Sindicos.
	 *
	 * @param request SindicoRequest object containing parameter for building the list of Sindico objects.
	 *
	 * @return the SindicoResponse containing the list of Sindicos built
	 */
	@WebMethod(action = "deleteSindicos")
	@WebResult(name = "deleteSindicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SindicoResponse deleteSindico(@WebParam(name = "request") SindicoMaintenanceRequest request);

	/**
	 * Rebuild a list of Sindicos.
	 *
	 * @param request SindicoRequest object containing parameter for building the list of Sindico objects.
	 *
	 * @return the SindicoResponse containing the list of Sindicos built
	 */
	@WebMethod(action = "fetchSindicoById")
	@WebResult(name = "fetchSindicoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SindicoResponse fetchSindicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Sindicos.
	 *
	 * @param request SindicoRequest object containing parameter for building the list of Sindico objects.
	 *
	 * @return the SindicoResponse containing the list of Sindicos built
	 */
	@WebMethod(action = "fetchSindicosByRequest")
	@WebResult(name = "fetchSindicosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SindicoResponse fetchSindicosByRequest(@WebParam(name = "request") SindicoInquiryRequest request);


	/**
	 * Rebuild a list of Sindicos.
	 *
	 * @param request SindicoRequest object containing parameter for building the list of Sindico objects.
	 *
	 * @return the SindicoResponse containing the list of Sindicos built
	 */
	@WebMethod(action = "refreshSindicos")
	@WebResult(name = "refreshSindicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	SindicoResponse refreshSindicos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Sindicos.
	 *
	 * @param request the request
	 *
	 * @return the SindicoResponse containing all Sindico objects
	 */
	@WebMethod(action = "fetchAllSindicos")
	@WebResult(name = "fetchAllSindicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	SindicoResponse fetchAllSindicos(@WebParam(name = "request") FetchAllRequest request);



//===================================### INQUILINO ####======================================

/**
	 * Rebuild a list of Inquilinos.
	 *
	 * @param request InquilinoRequest object containing parameter for building the list of Inquilino objects.
	 *
	 * @return the InquilinoResponse containing the list of Inquilinos built
	 */
	@WebMethod(action = "insertInquilinos")
	@WebResult(name = "insertInquilinosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InquilinoResponse insertInquilino(@WebParam(name = "request") InquilinoMaintenanceRequest request);

	/**
	 * Rebuild a list of Inquilinos.
	 *
	 * @param request InquilinoRequest object containing parameter for building the list of Inquilino objects.
	 *
	 * @return the InquilinoResponse containing the list of Inquilinos built
	 */
	@WebMethod(action = "updateInquilinos")
	@WebResult(name = "updateInquilinosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InquilinoResponse updateInquilino(@WebParam(name = "request") InquilinoMaintenanceRequest request);

	/**
	 * Rebuild a list of Inquilinos.
	 *
	 * @param request InquilinoRequest object containing parameter for building the list of Inquilino objects.
	 *
	 * @return the InquilinoResponse containing the list of Inquilinos built
	 */
	@WebMethod(action = "deleteInquilinos")
	@WebResult(name = "deleteInquilinosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InquilinoResponse deleteInquilino(@WebParam(name = "request") InquilinoMaintenanceRequest request);

	/**
	 * Rebuild a list of Inquilinos.
	 *
	 * @param request InquilinoRequest object containing parameter for building the list of Inquilino objects.
	 *
	 * @return the InquilinoResponse containing the list of Inquilinos built
	 */
	@WebMethod(action = "fetchInquilinoById")
	@WebResult(name = "fetchInquilinoByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InquilinoResponse fetchInquilinoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Inquilinos.
	 *
	 * @param request InquilinoRequest object containing parameter for building the list of Inquilino objects.
	 *
	 * @return the InquilinoResponse containing the list of Inquilinos built
	 */
	@WebMethod(action = "fetchInquilinosByRequest")
	@WebResult(name = "fetchInquilinosByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InquilinoResponse fetchInquilinosByRequest(@WebParam(name = "request") InquilinoInquiryRequest request);


	/**
	 * Rebuild a list of Inquilinos.
	 *
	 * @param request InquilinoRequest object containing parameter for building the list of Inquilino objects.
	 *
	 * @return the InquilinoResponse containing the list of Inquilinos built
	 */
	@WebMethod(action = "refreshInquilinos")
	@WebResult(name = "refreshInquilinosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InquilinoResponse refreshInquilinos(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Inquilinos.
	 *
	 * @param request the request
	 *
	 * @return the InquilinoResponse containing all Inquilino objects
	 */
	@WebMethod(action = "fetchAllInquilinos")
	@WebResult(name = "fetchAllInquilinosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	InquilinoResponse fetchAllInquilinos(@WebParam(name = "request") FetchAllRequest request);



//===================================### AVISOS ####======================================

/**
	 * Rebuild a list of Avisoss.
	 *
	 * @param request AvisosRequest object containing parameter for building the list of Avisos objects.
	 *
	 * @return the AvisosResponse containing the list of Avisoss built
	 */
	@WebMethod(action = "insertAvisoss")
	@WebResult(name = "insertAvisossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AvisoResponse insertAvisos(@WebParam(name = "request") AvisoMaintenanceRequest request);

	/**
	 * Rebuild a list of Avisoss.
	 *
	 * @param request AvisosRequest object containing parameter for building the list of Avisos objects.
	 *
	 * @return the AvisosResponse containing the list of Avisoss built
	 */
	@WebMethod(action = "updateAvisoss")
	@WebResult(name = "updateAvisossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AvisoResponse updateAvisos(@WebParam(name = "request") AvisoMaintenanceRequest request);

	/**
	 * Rebuild a list of Avisoss.
	 *
	 * @param request AvisosRequest object containing parameter for building the list of Avisos objects.
	 *
	 * @return the AvisosResponse containing the list of Avisoss built
	 */
	@WebMethod(action = "deleteAvisoss")
	@WebResult(name = "deleteAvisossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AvisoResponse deleteAvisos(@WebParam(name = "request") AvisoMaintenanceRequest request);

	/**
	 * Rebuild a list of Avisoss.
	 *
	 * @param request AvisosRequest object containing parameter for building the list of Avisos objects.
	 *
	 * @return the AvisosResponse containing the list of Avisoss built
	 */
	@WebMethod(action = "fetchAvisosById")
	@WebResult(name = "fetchAvisosByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AvisoResponse fetchAvisosById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Avisoss.
	 *
	 * @param request AvisosRequest object containing parameter for building the list of Avisos objects.
	 *
	 * @return the AvisosResponse containing the list of Avisoss built
	 */
	@WebMethod(action = "fetchAvisossByRequest")
	@WebResult(name = "fetchAvisossByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AvisoResponse fetchAvisossByRequest(@WebParam(name = "request") AvisoInquiryRequest request);


	/**
	 * Rebuild a list of Avisoss.
	 *
	 * @param request AvisosRequest object containing parameter for building the list of Avisos objects.
	 *
	 * @return the AvisosResponse containing the list of Avisoss built
	 */
	@WebMethod(action = "refreshAvisoss")
	@WebResult(name = "refreshAvisossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	AvisoResponse refreshAvisoss(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Avisoss.
	 *
	 * @param request the request
	 *
	 * @return the AvisosResponse containing all Avisos objects
	 */
	@WebMethod(action = "fetchAllAvisoss")
	@WebResult(name = "fetchAllAvisossReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	AvisoResponse fetchAllAvisoss(@WebParam(name = "request") FetchAllRequest request);


}
