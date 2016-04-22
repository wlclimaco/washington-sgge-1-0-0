package com.qat.samples.sysmgmt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.util.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface ICountyWS.
 */
@WebService(serviceName = "CountyService", targetNamespace = "http://qat.com/sysmgmt", portName = "CountyServicePort")
public interface ICountyWS
{

	/**
	 * Insert county.
	 *
	 * @param request the request
	 *
	 * @return the county response
	 */
	@WebMethod(action = "insertCounty")
	@WebResult(name = "insertCountyReturn")
	@WSDLDocumentation(value = "Insert a county record and optionally returns a list of counties.")
	public CountyResponse insertCounty(@WebParam(name = "request") CountyMaintenanceRequest request);

	/**
	 * Update county.
	 *
	 * @param request the request
	 *
	 * @return the county response
	 */
	@WebMethod(action = "updateCounty")
	@WebResult(name = "updateCountyReturn")
	@WSDLDocumentation(value = "Updates a county record and optionally returns a list of counties.")
	public CountyResponse updateCounty(@WebParam(name = "request") CountyMaintenanceRequest request);

	/**
	 * Delete county.
	 *
	 * @param request the request
	 *
	 * @return the county response
	 */
	@WebMethod(action = "deleteCounty")
	@WebResult(name = "deleteCountyReturn")
	@WSDLDocumentation(value = "Deletes a county record and optionally returns a list of counties.")
	public CountyResponse deleteCounty(@WebParam(name = "request") CountyMaintenanceRequest request);

	/**
	 * Refresh counties.
	 *
	 * @param request the request
	 *
	 * @return the county response
	 */
	@WebMethod(action = "refreshCounties")
	@WebResult(name = "refreshCountiesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the county table.")
	public CountyResponse refreshCounties(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch all counties.
	 *
	 * @param request the request
	 *
	 * @return the county response
	 */
	@WebMethod(action = "fetchAllCounties")
	@WebResult(name = "fetchAllCountiesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all counties.")
	public CountyResponse fetchAllCounties(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch county by id.
	 *
	 * @param request the request
	 *
	 * @return the county response
	 */
	@WebMethod(action = "fetchCountyById")
	@WebResult(name = "fetchCountyByIdReturn")
	@WSDLDocumentation(value = "Returns the selected county record.")
	public CountyResponse fetchCountyById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Fetch counties by request.
	 *
	 * @param request the request
	 * @return the county paged response
	 */
	@WebMethod(action = "fetchCountiesByRequest")
	@WebResult(name = "fetchCountiesByRequestReturn")
	@WSDLDocumentation(value = "Returns the selected county records paged.")
	public CountyResponse fetchCountiesByRequest(@WebParam(name = "request") PagedInquiryRequest request);
}
