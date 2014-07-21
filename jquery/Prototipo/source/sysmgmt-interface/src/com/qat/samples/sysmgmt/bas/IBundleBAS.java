package com.qat.samples.sysmgmt.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

/**
 * The Interface IBundleBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "BundleService", targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt", portName = "BundleServicePort")
public interface IBundleBAS
{

	/**
	 * Insert bundle.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "insertBundle")
	@WebResult(name = "insertBundleReturn")
	@WSDLDocumentation(value = "Insert a bundle record and optionally returns a list of bundles.")
	public BundleResponse insertBundle(@WebParam(name = "request") BundleMaintenanceRequest request);

	/**
	 * Update bundle.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "updateBundle")
	@WebResult(name = "updateBundleReturn")
	@WSDLDocumentation(value = "Updates the selected bundle record and optionally returns a list of bundles.")
	public BundleResponse updateBundle(@WebParam(name = "request") BundleMaintenanceRequest request);

	/**
	 * Delete bundle.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "deleteBundle")
	@WebResult(name = "deleteBundleReturn")
	@WSDLDocumentation(value = "Deletes a bundle record and optionally returns a list of bundles.")
	public BundleResponse deleteBundle(@WebParam(name = "request") BundleMaintenanceRequest request);

	/**
	 * Fetch all bundles.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "fetchAllBundles")
	@WebResult(name = "fetchAllBundlesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all bundles.")
	public BundleResponse fetchAllBundles(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch bundle by id.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "fetchBundleById")
	@WebResult(name = "fetchBundleByIdReturn")
	@WSDLDocumentation(value = "Returns the desired bundle.")
	public BundleResponse fetchBundleById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh bundles.
	 * 
	 * @param request the request
	 * 
	 * @return the bundle response
	 */
	@WebMethod(action = "refreshBundles")
	@WebResult(name = "refreshBundlesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the bundle tables.")
	public BundleResponse refreshBundles(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch bundles by request.
	 * 
	 * @param request the request
	 * @return the bundle paged response
	 */
	@WebMethod(action = "fetchBundlesByRequest")
	@WebResult(name = "fetchBundlesByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of bundles paged.")
	public BundleResponse fetchBundlesByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
