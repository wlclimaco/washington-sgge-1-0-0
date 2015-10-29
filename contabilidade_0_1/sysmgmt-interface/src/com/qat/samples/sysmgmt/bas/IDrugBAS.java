package com.qat.samples.sysmgmt.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByCodeRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;

/**
 * The Interface IDrugBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "DrugService", targetNamespace = "http://qat.com/sysmgmt", portName = "DrugServicePort")
public interface IDrugBAS
{

	/**
	 * Fetch drug by code.
	 * 
	 * @param request the request
	 * 
	 * @return the drug response
	 */
	@WebMethod(action = "fetchDrugByCode")
	@WebResult(name = "fetchDrugByCodeReturn")
	@WSDLDocumentation(value = "Returns the selected drug.")
	public DrugResponse fetchDrugByCode(@WebParam(name = "request") FetchByCodeRequest request);

	/**
	 * Insert drug.
	 * 
	 * @param request the request
	 * 
	 * @return the drug response
	 */
	@WebMethod(action = "insertDrug")
	@WebResult(name = "insertDrugReturn")
	@WSDLDocumentation(value = "Insert a drug record and optionally returns a list of drugs.")
	public DrugResponse insertDrug(@WebParam(name = "request") DrugMaintenanceRequest request);

	/**
	 * Update drug.
	 * 
	 * @param request the request
	 * 
	 * @return the drug response
	 */
	@WebMethod(action = "updateDrug")
	@WebResult(name = "updateDrugReturn")
	@WSDLDocumentation(value = "Updates the selected drug record and optionally returns a list of drugs.")
	public DrugResponse updateDrug(@WebParam(name = "request") DrugMaintenanceRequest request);

	/**
	 * Delete drug.
	 * 
	 * @param request the request
	 * 
	 * @return the drug response
	 */
	@WebMethod(action = "deleteDrug")
	@WebResult(name = "deleteDrugReturn")
	@WSDLDocumentation(value = "Deletes a drug record and optionally returns a list of drugs.")
	public DrugResponse deleteDrug(@WebParam(name = "request") DrugMaintenanceRequest request);

	/**
	 * Fetch all drugs.
	 * 
	 * @param request the request
	 * 
	 * @return the drug response
	 */
	@WebMethod(action = "fetchAllDrugs")
	@WebResult(name = "fetchAllDrugsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all drugs.")
	public DrugResponse fetchAllDrugs(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Refresh drugs.
	 * 
	 * @param request the request
	 * 
	 * @return the drug response
	 */
	@WebMethod(action = "refreshDrugs")
	@WebResult(name = "refreshDrugsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the drug tables.")
	public DrugResponse refreshDrugs(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch drugs by request.
	 * 
	 * @param request the request
	 * @return the drug paged response
	 */
	@WebMethod(action = "fetchDrugsByRequest")
	@WebResult(name = "fetchDrugsByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of drugs paged.")
	public DrugResponse fetchDrugsByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
