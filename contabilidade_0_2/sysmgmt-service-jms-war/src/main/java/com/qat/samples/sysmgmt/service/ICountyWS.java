package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICountyBAS delegate used by a JMS listener. (Business Area Service - BAS)
 */
@WebService(serviceName = "CountyService", targetNamespace = "http://qat.com/jms", portName = "CountyServicePort")
public interface ICountyWS
{

	/**
	 * Rebuild a list of Counties.
	 *
	 * @param request CountyRequest object containing parameter for building the list of County objects.
	 *
	 * @return the CountyResponse containing the list of Counties built
	 */
	@WebMethod(action = "refreshCounties")
	@WebResult(name = "refreshCountiesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	CountyResponse refreshCounties(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all Counties.
	 *
	 * @param request the request
	 *
	 * @return the CountyResponse containing all County objects
	 */
	@WebMethod(action = "fetchAllCounties")
	@WebResult(name = "fetchAllCountiesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	CountyResponse fetchAllCounties(@WebParam(name = "request") FetchAllRequest request);

}
