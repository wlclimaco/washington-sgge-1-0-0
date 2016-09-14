/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.ClassesMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.response.ClassesResponse;
import com.qat.samples.sysmgmt.dicionario.response.FieldResponse;
import com.qat.samples.sysmgmt.dicionario.response.InterfaceResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDicionarioWS.
 */
@WebService(serviceName = "DicionarioService", targetNamespace = "http://qat.com/sysmgmt", portName = "DicionarioServicePort")
public interface IDicionarioWS
{

//===================================### CLASSES ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertClasses")
	@WebResult(name = "insertClassesReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public ClassesResponse insertClasses(@WebParam(name = "request") ClassesMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateClasses")
	@WebResult(name = "updateClassesReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public ClassesResponse updateClasses(@WebParam(name = "request") ClassesMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteClasses")
	@WebResult(name = "deleteClassesReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public ClassesResponse deleteClasses(@WebParam(name = "request") ClassesMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshClassess")
	@WebResult(name = "refreshClassessReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public ClassesResponse refreshClassess(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllClassess")
	@WebResult(name = "fetchAllClassessReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public ClassesResponse fetchAllClassess(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchClassesById")
	@WebResult(name = "fetchClassesByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public ClassesResponse fetchClassesById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchClassessByRequest")
	@WebResult(name = "fetchClassessByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public ClassesResponse fetchClassessByRequest(@WebParam(name = "request") ClassesInquiryRequest request);


//===================================### INTERFACE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertInterface")
	@WebResult(name = "insertInterfaceReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public InterfaceResponse insertInterface(@WebParam(name = "request") InterfaceMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateInterface")
	@WebResult(name = "updateInterfaceReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public InterfaceResponse updateInterface(@WebParam(name = "request") InterfaceMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteInterface")
	@WebResult(name = "deleteInterfaceReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public InterfaceResponse deleteInterface(@WebParam(name = "request") InterfaceMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshInterfaces")
	@WebResult(name = "refreshInterfacesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public InterfaceResponse refreshInterfaces(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllInterfaces")
	@WebResult(name = "fetchAllInterfacesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public InterfaceResponse fetchAllInterfaces(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchInterfaceById")
	@WebResult(name = "fetchInterfaceByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public InterfaceResponse fetchInterfaceById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchInterfacesByRequest")
	@WebResult(name = "fetchInterfacesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public InterfaceResponse fetchInterfacesByRequest(@WebParam(name = "request") InterfaceInquiryRequest request);


//===================================### FIELD ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertField")
	@WebResult(name = "insertFieldReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public FieldResponse insertField(@WebParam(name = "request") FieldMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateField")
	@WebResult(name = "updateFieldReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public FieldResponse updateField(@WebParam(name = "request") FieldMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteField")
	@WebResult(name = "deleteFieldReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public FieldResponse deleteField(@WebParam(name = "request") FieldMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshFields")
	@WebResult(name = "refreshFieldsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public FieldResponse refreshFields(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllFields")
	@WebResult(name = "fetchAllFieldsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public FieldResponse fetchAllFields(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFieldById")
	@WebResult(name = "fetchFieldByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public FieldResponse fetchFieldById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchFieldsByRequest")
	@WebResult(name = "fetchFieldsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public FieldResponse fetchFieldsByRequest(@WebParam(name = "request") FieldInquiryRequest request);

}

