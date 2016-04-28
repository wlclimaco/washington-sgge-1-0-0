/** create by system gera-java version 1.0.0 27/04/2016*/


package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.cfop.model.request.CfopMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeMaintenanceRequest;
import com.qat.samples.sysmgmt.cnae.model.response.CnaeResponse;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeMaintenanceRequest;
import com.qat.samples.sysmgmt.fiscal.model.response.RegimeResponse;
import com.qat.samples.sysmgmt.produto.model.response.CfopResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IFiscalWS.
 */
@WebService(serviceName = "FiscalService", targetNamespace = "http://qat.com/sysmgmt", portName = "FiscalServicePort")
public interface IFiscalWS
{

//===================================### REGIME ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertRegime")
	@WebResult(name = "insertRegimeReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public RegimeResponse insertRegime(@WebParam(name = "request") RegimeMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateRegime")
	@WebResult(name = "updateRegimeReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public RegimeResponse updateRegime(@WebParam(name = "request") RegimeMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteRegime")
	@WebResult(name = "deleteRegimeReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public RegimeResponse deleteRegime(@WebParam(name = "request") RegimeMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshRegimes")
	@WebResult(name = "refreshRegimesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public RegimeResponse refreshRegimes(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllRegimes")
	@WebResult(name = "fetchAllRegimesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public RegimeResponse fetchAllRegimes(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchRegimeById")
	@WebResult(name = "fetchRegimeByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public RegimeResponse fetchRegimeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchRegimesByRequest")
	@WebResult(name = "fetchRegimesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public RegimeResponse fetchRegimesByRequest(@WebParam(name = "request") RegimeInquiryRequest request);


//===================================### CFOP ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCfop")
	@WebResult(name = "insertCfopReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public CfopResponse insertCfop(@WebParam(name = "request") CfopMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCfop")
	@WebResult(name = "updateCfopReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public CfopResponse updateCfop(@WebParam(name = "request") CfopMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCfop")
	@WebResult(name = "deleteCfopReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public CfopResponse deleteCfop(@WebParam(name = "request") CfopMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshCfops")
	@WebResult(name = "refreshCfopsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public CfopResponse refreshCfops(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllCfops")
	@WebResult(name = "fetchAllCfopsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public CfopResponse fetchAllCfops(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCfopById")
	@WebResult(name = "fetchCfopByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public CfopResponse fetchCfopById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCfopsByRequest")
	@WebResult(name = "fetchCfopsByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public CfopResponse fetchCfopsByRequest(@WebParam(name = "request") CfopInquiryRequest request);


//===================================### CNAE ####======================================

	/**
	 * Insert procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "insertCnae")
	@WebResult(name = "insertCnaeReturn")
	@WSDLDocumentation(value = "Insert a procedure record and optionally returns a list of procedures.")
	public CnaeResponse insertCnae(@WebParam(name = "request") CnaeMaintenanceRequest request);

	/**
	 * Update procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "updateCnae")
	@WebResult(name = "updateCnaeReturn")
	@WSDLDocumentation(value = "Updates the selected procedure record and optionally returns a list of procedures.")
	public CnaeResponse updateCnae(@WebParam(name = "request") CnaeMaintenanceRequest request);

	/**
	 * Delete procedure.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "deleteCnae")
	@WebResult(name = "deleteCnaeReturn")
	@WSDLDocumentation(value = "Deletes the selected procedure record and optionally returns a list of procedures.")
	public CnaeResponse deleteCnae(@WebParam(name = "request") CnaeMaintenanceRequest request);

	/**
	 * Refresh procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "refreshCnaes")
	@WebResult(name = "refreshCnaesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the procedure table.")
	public CnaeResponse refreshCnaes(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all procedures.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchAllCnaes")
	@WebResult(name = "fetchAllCnaesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all procedures.")
	public CnaeResponse fetchAllCnaes(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch procedure by id.
	 *
	 * @param request the request
	 *
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCnaeById")
	@WebResult(name = "fetchCnaeByIdReturn")
	@WSDLDocumentation(value = "Returns the desired procedure.")
	public CnaeResponse fetchCnaeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch procedures by request.
	 *
	 * @param request the request
	 * @return the procedure response
	 */
	@WebMethod(action = "fetchCnaesByRequest")
	@WebResult(name = "fetchCnaesByRequestReturn")
	@WSDLDocumentation(value = "Returns the list of procedures in a paged list (start and ending rows).")
	public CnaeResponse fetchCnaesByRequest(@WebParam(name = "request") CnaeInquiryRequest request);


}

