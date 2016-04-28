/** create by system gera-java version 1.0.0 28/04/2016 14:59 : 50*/
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
 * The Interface IFiscalBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "FiscalService", targetNamespace = "http://qat.com/jms", portName = "FiscalServicePort")
public interface IFiscalWS
{

//===================================### REGIME ####======================================

/**
	 * Rebuild a list of Regimes.
	 *
	 * @param request RegimeRequest object containing parameter for building the list of Regime objects.
	 *
	 * @return the RegimeResponse containing the list of Regimes built
	 */
	@WebMethod(action = "insertRegimes")
	@WebResult(name = "insertRegimesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	RegimeResponse insertRegime(@WebParam(name = "request") RegimeMaintenanceRequest request);

	/**
	 * Rebuild a list of Regimes.
	 *
	 * @param request RegimeRequest object containing parameter for building the list of Regime objects.
	 *
	 * @return the RegimeResponse containing the list of Regimes built
	 */
	@WebMethod(action = "updateRegimes")
	@WebResult(name = "updateRegimesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	RegimeResponse updateRegime(@WebParam(name = "request") RegimeMaintenanceRequest request);

	/**
	 * Rebuild a list of Regimes.
	 *
	 * @param request RegimeRequest object containing parameter for building the list of Regime objects.
	 *
	 * @return the RegimeResponse containing the list of Regimes built
	 */
	@WebMethod(action = "deleteRegimes")
	@WebResult(name = "deleteRegimesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	RegimeResponse deleteRegime(@WebParam(name = "request") RegimeMaintenanceRequest request);

	/**
	 * Rebuild a list of Regimes.
	 *
	 * @param request RegimeRequest object containing parameter for building the list of Regime objects.
	 *
	 * @return the RegimeResponse containing the list of Regimes built
	 */
	@WebMethod(action = "fetchRegimeById")
	@WebResult(name = "fetchRegimeByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	RegimeResponse fetchRegimeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Regimes.
	 *
	 * @param request RegimeRequest object containing parameter for building the list of Regime objects.
	 *
	 * @return the RegimeResponse containing the list of Regimes built
	 */
	@WebMethod(action = "fetchRegimesByRequest")
	@WebResult(name = "fetchRegimesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	RegimeResponse fetchRegimesByRequest(@WebParam(name = "request") RegimeInquiryRequest request);


	/**
	 * Rebuild a list of Regimes.
	 *
	 * @param request RegimeRequest object containing parameter for building the list of Regime objects.
	 *
	 * @return the RegimeResponse containing the list of Regimes built
	 */
	@WebMethod(action = "refreshRegimes")
	@WebResult(name = "refreshRegimesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	RegimeResponse refreshRegimes(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Regimes.
	 *
	 * @param request the request
	 *
	 * @return the RegimeResponse containing all Regime objects
	 */
	@WebMethod(action = "fetchAllRegimes")
	@WebResult(name = "fetchAllRegimesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	RegimeResponse fetchAllRegimes(@WebParam(name = "request") FetchAllRequest request);



//===================================### CFOP ####======================================

/**
	 * Rebuild a list of Cfops.
	 *
	 * @param request CfopRequest object containing parameter for building the list of Cfop objects.
	 *
	 * @return the CfopResponse containing the list of Cfops built
	 */
	@WebMethod(action = "insertCfops")
	@WebResult(name = "insertCfopsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CfopResponse insertCfop(@WebParam(name = "request") CfopMaintenanceRequest request);

	/**
	 * Rebuild a list of Cfops.
	 *
	 * @param request CfopRequest object containing parameter for building the list of Cfop objects.
	 *
	 * @return the CfopResponse containing the list of Cfops built
	 */
	@WebMethod(action = "updateCfops")
	@WebResult(name = "updateCfopsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CfopResponse updateCfop(@WebParam(name = "request") CfopMaintenanceRequest request);

	/**
	 * Rebuild a list of Cfops.
	 *
	 * @param request CfopRequest object containing parameter for building the list of Cfop objects.
	 *
	 * @return the CfopResponse containing the list of Cfops built
	 */
	@WebMethod(action = "deleteCfops")
	@WebResult(name = "deleteCfopsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CfopResponse deleteCfop(@WebParam(name = "request") CfopMaintenanceRequest request);

	/**
	 * Rebuild a list of Cfops.
	 *
	 * @param request CfopRequest object containing parameter for building the list of Cfop objects.
	 *
	 * @return the CfopResponse containing the list of Cfops built
	 */
	@WebMethod(action = "fetchCfopById")
	@WebResult(name = "fetchCfopByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CfopResponse fetchCfopById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Cfops.
	 *
	 * @param request CfopRequest object containing parameter for building the list of Cfop objects.
	 *
	 * @return the CfopResponse containing the list of Cfops built
	 */
	@WebMethod(action = "fetchCfopsByRequest")
	@WebResult(name = "fetchCfopsByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CfopResponse fetchCfopsByRequest(@WebParam(name = "request") CfopInquiryRequest request);


	/**
	 * Rebuild a list of Cfops.
	 *
	 * @param request CfopRequest object containing parameter for building the list of Cfop objects.
	 *
	 * @return the CfopResponse containing the list of Cfops built
	 */
	@WebMethod(action = "refreshCfops")
	@WebResult(name = "refreshCfopsReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CfopResponse refreshCfops(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Cfops.
	 *
	 * @param request the request
	 *
	 * @return the CfopResponse containing all Cfop objects
	 */
	@WebMethod(action = "fetchAllCfops")
	@WebResult(name = "fetchAllCfopsReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CfopResponse fetchAllCfops(@WebParam(name = "request") FetchAllRequest request);



//===================================### CNAE ####======================================

/**
	 * Rebuild a list of Cnaes.
	 *
	 * @param request CnaeRequest object containing parameter for building the list of Cnae objects.
	 *
	 * @return the CnaeResponse containing the list of Cnaes built
	 */
	@WebMethod(action = "insertCnaes")
	@WebResult(name = "insertCnaesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CnaeResponse insertCnae(@WebParam(name = "request") CnaeMaintenanceRequest request);

	/**
	 * Rebuild a list of Cnaes.
	 *
	 * @param request CnaeRequest object containing parameter for building the list of Cnae objects.
	 *
	 * @return the CnaeResponse containing the list of Cnaes built
	 */
	@WebMethod(action = "updateCnaes")
	@WebResult(name = "updateCnaesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CnaeResponse updateCnae(@WebParam(name = "request") CnaeMaintenanceRequest request);

	/**
	 * Rebuild a list of Cnaes.
	 *
	 * @param request CnaeRequest object containing parameter for building the list of Cnae objects.
	 *
	 * @return the CnaeResponse containing the list of Cnaes built
	 */
	@WebMethod(action = "deleteCnaes")
	@WebResult(name = "deleteCnaesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CnaeResponse deleteCnae(@WebParam(name = "request") CnaeMaintenanceRequest request);

	/**
	 * Rebuild a list of Cnaes.
	 *
	 * @param request CnaeRequest object containing parameter for building the list of Cnae objects.
	 *
	 * @return the CnaeResponse containing the list of Cnaes built
	 */
	@WebMethod(action = "fetchCnaeById")
	@WebResult(name = "fetchCnaeByIdReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CnaeResponse fetchCnaeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Rebuild a list of Cnaes.
	 *
	 * @param request CnaeRequest object containing parameter for building the list of Cnae objects.
	 *
	 * @return the CnaeResponse containing the list of Cnaes built
	 */
	@WebMethod(action = "fetchCnaesByRequest")
	@WebResult(name = "fetchCnaesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CnaeResponse fetchCnaesByRequest(@WebParam(name = "request") CnaeInquiryRequest request);


	/**
	 * Rebuild a list of Cnaes.
	 *
	 * @param request CnaeRequest object containing parameter for building the list of Cnae objects.
	 *
	 * @return the CnaeResponse containing the list of Cnaes built
	 */
	@WebMethod(action = "refreshCnaes")
	@WebResult(name = "refreshCnaesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CnaeResponse refreshCnaes(@WebParam(name = "request") RefreshRequest request);


	/**
	 * Fetch all Cnaes.
	 *
	 * @param request the request
	 *
	 * @return the CnaeResponse containing all Cnae objects
	 */
	@WebMethod(action = "fetchAllCnaes")
	@WebResult(name = "fetchAllCnaesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CnaeResponse fetchAllCnaes(@WebParam(name = "request") FetchAllRequest request);



}
