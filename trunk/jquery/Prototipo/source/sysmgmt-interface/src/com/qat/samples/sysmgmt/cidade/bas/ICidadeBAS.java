package com.qat.samples.sysmgmt.cidade.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface ICidadeBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "CidadeService", targetNamespace = "http://qat.com/sysmgmt", portName = "CidadeServicePort")
public interface ICidadeBAS
{

	/**
	 * Insert cidade.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "insertCidade")
	@WebResult(name = "insertCidadeReturn")
	@WSDLDocumentation(value = "Insert a cidade record and optionally returns a list of cidades.")
	public CidadeResponse insertCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Update cidade.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "updateCidade")
	@WebResult(name = "updateCidadeReturn")
	@WSDLDocumentation(value = "Updates the selected cidade record and optionally returns a list of cidades.")
	public CidadeResponse updateCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Delete cidade.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "deleteCidade")
	@WebResult(name = "deleteCidadeReturn")
	@WSDLDocumentation(value = "Deletes a cidade record and optionally returns a list of cidades.")
	public CidadeResponse deleteCidade(@WebParam(name = "request") CidadeMaintenanceRequest request);

	/**
	 * Fetch all cidades.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "fetchAllCidades")
	@WebResult(name = "fetchAllCidadesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all cidades.")
	public CidadeResponse fetchAllCidades(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "fetchCidadeById")
	@WebResult(name = "fetchCidadeByIdReturn")
	@WSDLDocumentation(value = "Returns the desired cidade.")
	public CidadeResponse fetchCidadeById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh cidades.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "refreshCidades")
	@WebResult(name = "refreshCidadesReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the cidade tables.")
	public CidadeResponse refreshCidades(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	@WebMethod(action = "fetchCidadesByRequest")
	@WebResult(name = "fetchCidadesByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of cidades paged.")
	public CidadeResponse fetchCidadesByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
