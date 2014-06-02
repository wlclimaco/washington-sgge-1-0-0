package com.qat.samples.sysmgmt.produto.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * The Interface IProdutoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ProdutoService", targetNamespace = "http://qat.com/sysmgmt", portName = "ProdutoServicePort")
public interface IProdutoBAS
{

	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "insertProduto")
	@WebResult(name = "insertProdutoReturn")
	@WSDLDocumentation(value = "Insert a produto record and optionally returns a list of produtos.")
	public ProdutoResponse insertProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "updateProduto")
	@WebResult(name = "updateProdutoReturn")
	@WSDLDocumentation(value = "Updates the selected produto record and optionally returns a list of produtos.")
	public ProdutoResponse updateProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "deleteProduto")
	@WebResult(name = "deleteProdutoReturn")
	@WSDLDocumentation(value = "Deletes a produto record and optionally returns a list of produtos.")
	public ProdutoResponse deleteProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "fetchAllProdutos")
	@WebResult(name = "fetchAllProdutosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all produtos.")
	public ProdutoResponse fetchAllProdutos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch produto by id.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "fetchProdutoById")
	@WebResult(name = "fetchProdutoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired produto.")
	public ProdutoResponse fetchProdutoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh produtos.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "refreshProdutos")
	@WebResult(name = "refreshProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the produto tables.")
	public ProdutoResponse refreshProdutos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch produtos by request.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "fetchProdutosByRequest")
	@WebResult(name = "fetchProdutosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public ProdutoResponse fetchProdutosByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}