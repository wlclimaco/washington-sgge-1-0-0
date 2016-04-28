/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.ClassesMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.response.ClassesResponse;
import com.qat.samples.sysmgmt.dicionario.response.FieldResponse;
import com.qat.samples.sysmgmt.dicionario.response.InterfaceResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDicionarioBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "DicionarioService", targetNamespace = "http://qat.com/jms", portName = "DicionarioServicePort")
public interface IDicionarioWS
{

//===================================### CLASSES ####======================================

/**
	 * Rebuild a list of Classess.
	 *
	 * @param request ClassesRequest object containing parameter for building the list of Classes objects.
	 *
	 * @return the ClassesResponse containing the list of Classess built
	 */
	@WebMethod(action = "insertClassess")
	@WebResult(name = "insertClassessReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClassesResponse insertClasses(@WebParam(name = "request") ClassesMaintenanceRequest request);

	/**
	 * Rebuild a list of Classess.
	 *
	 * @param request ClassesRequest object containing parameter for building the list of Classes objects.
	 *
	 * @return the ClassesResponse containing the list of Classess built
	 */
	@WebMethod(action = "updateClassess")
	@WebResult(name = "updateClassessReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClassesResponse updateClasses(@WebParam(name = "request") ClassesMaintenanceRequest request);

	/**
	 * Rebuild a list of Classess.
	 *
	 * @param request ClassesRequest object containing parameter for building the list of Classes objects.
	 *
	 * @return the ClassesResponse containing the list of Classess built
	 */
	@WebMethod(action = "deleteClassess")
	@WebResult(name = "deleteClassessReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClassesResponse deleteClasses(@WebParam(name = "request") ClassesMaintenanceRequest request);

	/**
	 * Rebuild a list of Classess.
	 *
	 * @param request ClassesRequest object containing parameter for building the list of Classes objects.
	 *
	 * @return the ClassesResponse containing the list of Classess built
	 */
	@WebMethod(action = "fetchClassesById")
	@WebResult(name = "fetchClassesByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClassesResponse fetchClassesById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Classess.
	 *
	 * @param request ClassesRequest object containing parameter for building the list of Classes objects.
	 *
	 * @return the ClassesResponse containing the list of Classess built
	 */
	@WebMethod(action = "fetchClassessByRequest")
	@WebResult(name = "fetchClassessByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClassesResponse fetchClassessByRequest(@WebParam(name = "request") ClassesInquiryRequest request);


	/**
	 * Rebuild a list of Classess.
	 *
	 * @param request ClassesRequest object containing parameter for building the list of Classes objects.
	 *
	 * @return the ClassesResponse containing the list of Classess built
	 */
	@WebMethod(action = "refreshClassess")
	@WebResult(name = "refreshClassessReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	ClassesResponse refreshClassess(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Classess.
	 *
	 * @param request the request
	 *
	 * @return the ClassesResponse containing all Classes objects
	 */
	@WebMethod(action = "fetchAllClassess")
	@WebResult(name = "fetchAllClassessReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	ClassesResponse fetchAllClassess(@WebParam(name = "request") FetchAllRequest request);



//===================================### INTERFACE ####======================================

/**
	 * Rebuild a list of Interfaces.
	 *
	 * @param request InterfaceRequest object containing parameter for building the list of Interface objects.
	 *
	 * @return the InterfaceResponse containing the list of Interfaces built
	 */
	@WebMethod(action = "insertInterfaces")
	@WebResult(name = "insertInterfacesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InterfaceResponse insertInterface(@WebParam(name = "request") InterfaceMaintenanceRequest request);

	/**
	 * Rebuild a list of Interfaces.
	 *
	 * @param request InterfaceRequest object containing parameter for building the list of Interface objects.
	 *
	 * @return the InterfaceResponse containing the list of Interfaces built
	 */
	@WebMethod(action = "updateInterfaces")
	@WebResult(name = "updateInterfacesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InterfaceResponse updateInterface(@WebParam(name = "request") InterfaceMaintenanceRequest request);

	/**
	 * Rebuild a list of Interfaces.
	 *
	 * @param request InterfaceRequest object containing parameter for building the list of Interface objects.
	 *
	 * @return the InterfaceResponse containing the list of Interfaces built
	 */
	@WebMethod(action = "deleteInterfaces")
	@WebResult(name = "deleteInterfacesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InterfaceResponse deleteInterface(@WebParam(name = "request") InterfaceMaintenanceRequest request);

	/**
	 * Rebuild a list of Interfaces.
	 *
	 * @param request InterfaceRequest object containing parameter for building the list of Interface objects.
	 *
	 * @return the InterfaceResponse containing the list of Interfaces built
	 */
	@WebMethod(action = "fetchInterfaceById")
	@WebResult(name = "fetchInterfaceByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InterfaceResponse fetchInterfaceById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Interfaces.
	 *
	 * @param request InterfaceRequest object containing parameter for building the list of Interface objects.
	 *
	 * @return the InterfaceResponse containing the list of Interfaces built
	 */
	@WebMethod(action = "fetchInterfacesByRequest")
	@WebResult(name = "fetchInterfacesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InterfaceResponse fetchInterfacesByRequest(@WebParam(name = "request") InterfaceInquiryRequest request);


	/**
	 * Rebuild a list of Interfaces.
	 *
	 * @param request InterfaceRequest object containing parameter for building the list of Interface objects.
	 *
	 * @return the InterfaceResponse containing the list of Interfaces built
	 */
	@WebMethod(action = "refreshInterfaces")
	@WebResult(name = "refreshInterfacesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	InterfaceResponse refreshInterfaces(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Interfaces.
	 *
	 * @param request the request
	 *
	 * @return the InterfaceResponse containing all Interface objects
	 */
	@WebMethod(action = "fetchAllInterfaces")
	@WebResult(name = "fetchAllInterfacesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	InterfaceResponse fetchAllInterfaces(@WebParam(name = "request") FetchAllRequest request);



//===================================### FIELD ####======================================

/**
	 * Rebuild a list of Fields.
	 *
	 * @param request FieldRequest object containing parameter for building the list of Field objects.
	 *
	 * @return the FieldResponse containing the list of Fields built
	 */
	@WebMethod(action = "insertFields")
	@WebResult(name = "insertFieldsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FieldResponse insertField(@WebParam(name = "request") FieldMaintenanceRequest request);

	/**
	 * Rebuild a list of Fields.
	 *
	 * @param request FieldRequest object containing parameter for building the list of Field objects.
	 *
	 * @return the FieldResponse containing the list of Fields built
	 */
	@WebMethod(action = "updateFields")
	@WebResult(name = "updateFieldsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FieldResponse updateField(@WebParam(name = "request") FieldMaintenanceRequest request);

	/**
	 * Rebuild a list of Fields.
	 *
	 * @param request FieldRequest object containing parameter for building the list of Field objects.
	 *
	 * @return the FieldResponse containing the list of Fields built
	 */
	@WebMethod(action = "deleteFields")
	@WebResult(name = "deleteFieldsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FieldResponse deleteField(@WebParam(name = "request") FieldMaintenanceRequest request);

	/**
	 * Rebuild a list of Fields.
	 *
	 * @param request FieldRequest object containing parameter for building the list of Field objects.
	 *
	 * @return the FieldResponse containing the list of Fields built
	 */
	@WebMethod(action = "fetchFieldById")
	@WebResult(name = "fetchFieldByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FieldResponse fetchFieldById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Fields.
	 *
	 * @param request FieldRequest object containing parameter for building the list of Field objects.
	 *
	 * @return the FieldResponse containing the list of Fields built
	 */
	@WebMethod(action = "fetchFieldsByRequest")
	@WebResult(name = "fetchFieldsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FieldResponse fetchFieldsByRequest(@WebParam(name = "request") FieldInquiryRequest request);


	/**
	 * Rebuild a list of Fields.
	 *
	 * @param request FieldRequest object containing parameter for building the list of Field objects.
	 *
	 * @return the FieldResponse containing the list of Fields built
	 */
	@WebMethod(action = "refreshFields")
	@WebResult(name = "refreshFieldsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	FieldResponse refreshFields(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Fields.
	 *
	 * @param request the request
	 *
	 * @return the FieldResponse containing all Field objects
	 */
	@WebMethod(action = "fetchAllFields")
	@WebResult(name = "fetchAllFieldsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	FieldResponse fetchAllFields(@WebParam(name = "request") FetchAllRequest request);


}
