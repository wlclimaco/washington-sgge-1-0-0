package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IProcedureWS.
 */
@WebService(serviceName = "ProcedureService", targetNamespace = "http://qat.com/sysmgmt", portName = "ProcedureServicePort")
public interface IProcedureWS
{

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertProcedure")
	@WebResult(name = "insertProcedureReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ProcedureResponse insertProcedure(@WebParam(name = "request") ProcedureMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateProcedure")
	@WebResult(name = "updateProcedureReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ProcedureResponse updateProcedure(@WebParam(name = "request") ProcedureMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteProcedure")
	@WebResult(name = "deleteProcedureReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ProcedureResponse deleteProcedure(@WebParam(name = "request") ProcedureMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshProcedures")
	@WebResult(name = "refreshProceduresReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ProcedureResponse refreshProcedures(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllProcedures")
	@WebResult(name = "fetchAllProceduresReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ProcedureResponse fetchAllProcedures(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchProcedureById")
	@WebResult(name = "fetchProcedureByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ProcedureResponse fetchProcedureById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchProceduresByRequest")
	@WebResult(name = "fetchProceduresByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ProcedureResponse fetchProceduresByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}