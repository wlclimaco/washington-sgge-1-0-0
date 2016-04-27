/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.AdvogadoResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IAdvogadoWS.
 */
@WebService(serviceName = "AdvogadoService", targetNamespace = "http://qat.com/sysmgmt", portName = "AdvogadoServicePort")
public interface IAdvogadoWS
{

//===================================### ADVOGADO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertAdvogado")
	@WebResult(name = "insertAdvogadoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public AdvogadoResponse insertAdvogado(@WebParam(name = "request") AdvogadoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateAdvogado")
	@WebResult(name = "updateAdvogadoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public AdvogadoResponse updateAdvogado(@WebParam(name = "request") AdvogadoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteAdvogado")
	@WebResult(name = "deleteAdvogadoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public AdvogadoResponse deleteAdvogado(@WebParam(name = "request") AdvogadoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshAdvogados")
	@WebResult(name = "refreshAdvogadosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public AdvogadoResponse refreshAdvogados(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllAdvogados")
	@WebResult(name = "fetchAllAdvogadosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public AdvogadoResponse fetchAllAdvogados(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAdvogadoById")
	@WebResult(name = "fetchAdvogadoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public AdvogadoResponse fetchAdvogadoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAdvogadosByRequest")
	@WebResult(name = "fetchAdvogadosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public AdvogadoResponse fetchAdvogadosByRequest(@WebParam(name = "request") AdvogadoInquiryRequest request);


//===================================### AUDIENCIA ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertAudiencia")
	@WebResult(name = "insertAudienciaReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public AudienciaResponse insertAudiencia(@WebParam(name = "request") AudienciaMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateAudiencia")
	@WebResult(name = "updateAudienciaReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public AudienciaResponse updateAudiencia(@WebParam(name = "request") AudienciaMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteAudiencia")
	@WebResult(name = "deleteAudienciaReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public AudienciaResponse deleteAudiencia(@WebParam(name = "request") AudienciaMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshAudiencias")
	@WebResult(name = "refreshAudienciasReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public AudienciaResponse refreshAudiencias(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllAudiencias")
	@WebResult(name = "fetchAllAudienciasReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public AudienciaResponse fetchAllAudiencias(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAudienciaById")
	@WebResult(name = "fetchAudienciaByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public AudienciaResponse fetchAudienciaById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAudienciasByRequest")
	@WebResult(name = "fetchAudienciasByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public AudienciaResponse fetchAudienciasByRequest(@WebParam(name = "request") AudienciaInquiryRequest request);


//===================================### PROCESSO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertProcesso")
	@WebResult(name = "insertProcessoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ProcessoResponse insertProcesso(@WebParam(name = "request") ProcessoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateProcesso")
	@WebResult(name = "updateProcessoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ProcessoResponse updateProcesso(@WebParam(name = "request") ProcessoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteProcesso")
	@WebResult(name = "deleteProcessoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ProcessoResponse deleteProcesso(@WebParam(name = "request") ProcessoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshProcessos")
	@WebResult(name = "refreshProcessosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ProcessoResponse refreshProcessos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllProcessos")
	@WebResult(name = "fetchAllProcessosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ProcessoResponse fetchAllProcessos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchProcessoById")
	@WebResult(name = "fetchProcessoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ProcessoResponse fetchProcessoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchProcessosByRequest")
	@WebResult(name = "fetchProcessosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ProcessoResponse fetchProcessosByRequest(@WebParam(name = "request") ProcessoInquiryRequest request);

}

