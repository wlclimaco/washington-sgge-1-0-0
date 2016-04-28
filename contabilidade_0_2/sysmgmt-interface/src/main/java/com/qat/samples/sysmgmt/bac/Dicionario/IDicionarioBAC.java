package com.qat.samples.sysmgmt.bac.Dicionario;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.dicionario.Field;
import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.dicionario.request.ClassesInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.ClassesMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.FieldMaintenanceRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceInquiryRequest;
import com.qat.samples.sysmgmt.dicionario.request.InterfaceMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IDicionarioBAC. (Business Area Component - BAC)
 */
public interface IDicionarioBAC
{



//===================================### CLASSES ####======================================
	/**

	/**
	 * Insert classes.
	 *
* @param request the classes maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Classes> insertClasses(ClassesMaintenanceRequest request);

	/**
* Update classes.
*
* @param request the classes maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Classes> updateClasses(ClassesMaintenanceRequest request);

	/**
* Delete classes.
*
* @param request the classes maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Classes> deleteClasses(ClassesMaintenanceRequest request);

	/**
* Refresh classess.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Classes> refreshClassess(RefreshRequest request);

	/**
* Fetch classes by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Classes> fetchClassesById(FetchByIdRequest request);

	/**
* Fetch all classess.
*
* @return the internal results response< classes>
*/
	public InternalResultsResponse<Classes> fetchAllClassess(Classes  classes);

	/**
* Fetch classess by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Classes> fetchClassessByRequest(ClassesInquiryRequest request);


//===================================### INTERFACE ####======================================
	/**

	/**
	 * Insert interface.
	 *
* @param request the interface maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Interface> insertInterface(InterfaceMaintenanceRequest request);

	/**
* Update interface.
*
* @param request the interface maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Interface> updateInterface(InterfaceMaintenanceRequest request);

	/**
* Delete interface.
*
* @param request the interface maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Interface> deleteInterface(InterfaceMaintenanceRequest request);

	/**
* Refresh interfaces.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Interface> refreshInterfaces(RefreshRequest request);

	/**
* Fetch interface by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Interface> fetchInterfaceById(FetchByIdRequest request);

	/**
* Fetch all interfaces.
*
* @return the internal results response< interface>
*/
	public InternalResultsResponse<Interface> fetchAllInterfaces(Interface  interfaces);

	/**
* Fetch interfaces by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Interface> fetchInterfacesByRequest(InterfaceInquiryRequest request);


//===================================### FIELD ####======================================
	/**

	/**
	 * Insert field.
	 *
* @param request the field maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Field> insertField(FieldMaintenanceRequest request);

	/**
* Update field.
*
* @param request the field maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Field> updateField(FieldMaintenanceRequest request);

	/**
* Delete field.
*
* @param request the field maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Field> deleteField(FieldMaintenanceRequest request);

	/**
* Refresh fields.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Field> refreshFields(RefreshRequest request);

	/**
* Fetch field by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Field> fetchFieldById(FetchByIdRequest request);

	/**
* Fetch all fields.
*
* @return the internal results response< field>
*/
	public InternalResultsResponse<Field> fetchAllFields(Field  field);

	/**
* Fetch fields by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Field> fetchFieldsByRequest(FieldInquiryRequest request);

}
