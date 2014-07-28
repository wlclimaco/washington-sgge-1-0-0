package com.qat.samples.sysmgmt.controle.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;

/**
 * The Interface ICidadeBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ControleService", targetNamespace = "http://qat.com/sysmgmt", portName = "ControleServicePort")
public interface IControleBAS
{

	/**
	 * Fetch all cidades.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "fetchAllControles")
	@WebResult(name = "fetchAllControlesReturn")
	@WSDLDocumentation(value = "Returns a complete list of all controles.")
	public ControleResponse fetchAllControles(@WebParam(name = "request") ControleInquiryRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "fetchControleByPage")
	@WebResult(name = "fetchCControleByPageReturn")
	@WSDLDocumentation(value = "Returns the desired controle.")
	public ControleResponse fetchControleByPage(@WebParam(name = "request") ControleInquiryRequest request);

	/**
	 * Refresh cidades.
	 * 
	 * @param request the request
	 * 
	 * @return the cidade response
	 */
	@WebMethod(action = "fetchControlesByRequest")
	@WebResult(name = "fetchControlesByRequestReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the cidade tables.")
	public ControleResponse fetchControlesByRequest(@WebParam(name = "request") ControleInquiryRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	@WebMethod(action = "fetchControleByAction")
	@WebResult(name = "fetchControleByActionReturn")
	@WSDLDocumentation(value = "Returns a list of controles paged.")
	public ControleResponse fetchControleByAction(@WebParam(name = "request") ControleInquiryRequest request);

	@WebMethod(action = "fetchAllAcessos")
	@WebResult(name = "fetchAllAcessosReturn")
	@WSDLDocumentation(value = "Returns a list of controles paged.")
	public AcessosResponse fetchAllAcessos(@WebParam(name = "request") AcessosInquiryRequest request);
}
