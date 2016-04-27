/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.CondominioResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICondominioWS.
 */
@WebService(serviceName = "CondominioService", targetNamespace = "http://qat.com/sysmgmt", portName = "CondominioServicePort")
public interface ICondominioWS
{

//===================================### SINDICO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertSindico")
	@WebResult(name = "insertSindicoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public SindicoResponse insertSindico(@WebParam(name = "request") SindicoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateSindico")
	@WebResult(name = "updateSindicoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public SindicoResponse updateSindico(@WebParam(name = "request") SindicoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteSindico")
	@WebResult(name = "deleteSindicoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public SindicoResponse deleteSindico(@WebParam(name = "request") SindicoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshSindicos")
	@WebResult(name = "refreshSindicosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public SindicoResponse refreshSindicos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllSindicos")
	@WebResult(name = "fetchAllSindicosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public SindicoResponse fetchAllSindicos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchSindicoById")
	@WebResult(name = "fetchSindicoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public SindicoResponse fetchSindicoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchSindicosByRequest")
	@WebResult(name = "fetchSindicosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public SindicoResponse fetchSindicosByRequest(@WebParam(name = "request") SindicoInquiryRequest request);


//===================================### INQUILINO ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertInquilino")
	@WebResult(name = "insertInquilinoReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public InquilinoResponse insertInquilino(@WebParam(name = "request") InquilinoMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateInquilino")
	@WebResult(name = "updateInquilinoReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public InquilinoResponse updateInquilino(@WebParam(name = "request") InquilinoMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteInquilino")
	@WebResult(name = "deleteInquilinoReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public InquilinoResponse deleteInquilino(@WebParam(name = "request") InquilinoMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshInquilinos")
	@WebResult(name = "refreshInquilinosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public InquilinoResponse refreshInquilinos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllInquilinos")
	@WebResult(name = "fetchAllInquilinosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public InquilinoResponse fetchAllInquilinos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchInquilinoById")
	@WebResult(name = "fetchInquilinoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public InquilinoResponse fetchInquilinoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchInquilinosByRequest")
	@WebResult(name = "fetchInquilinosByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public InquilinoResponse fetchInquilinosByRequest(@WebParam(name = "request") InquilinoInquiryRequest request);


//===================================### AVISOS ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertAvisos")
	@WebResult(name = "insertAvisosReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public AvisosResponse insertAvisos(@WebParam(name = "request") AvisosMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateAvisos")
	@WebResult(name = "updateAvisosReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public AvisosResponse updateAvisos(@WebParam(name = "request") AvisosMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteAvisos")
	@WebResult(name = "deleteAvisosReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public AvisosResponse deleteAvisos(@WebParam(name = "request") AvisosMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshAvisoss")
	@WebResult(name = "refreshAvisossReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public AvisosResponse refreshAvisoss(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllAvisoss")
	@WebResult(name = "fetchAllAvisossReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public AvisosResponse fetchAllAvisoss(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAvisosById")
	@WebResult(name = "fetchAvisosByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public AvisosResponse fetchAvisosById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAvisossByRequest")
	@WebResult(name = "fetchAvisossByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public AvisosResponse fetchAvisossByRequest(@WebParam(name = "request") AvisosInquiryRequest request);

}

