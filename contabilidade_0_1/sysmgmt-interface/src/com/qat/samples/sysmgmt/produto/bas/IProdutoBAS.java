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
	 * Insert supermercado.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "insertProduto")
	@WebResult(name = "insertProdutoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ProdutoResponse insertProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Update supermercado.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "updateProduto")
	@WebResult(name = "updateProdutoReturn")
	@WSDLDocumentation(value = "Updates the selected supermercado record and optionally returns a list of supermercados.")
	public ProdutoResponse updateProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Delete supermercado.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "deleteProduto")
	@WebResult(name = "deleteProdutoReturn")
	@WSDLDocumentation(value = "Deletes a supermercado record and optionally returns a list of supermercados.")
	public ProdutoResponse deleteProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	/**
	 * Fetch all supermercados.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "fetchAllProdutos")
	@WebResult(name = "fetchAllProdutosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all supermercados.")
	public ProdutoResponse fetchAllProdutos(@WebParam(name = "request") FetchAllRequest request);

	/**
	 * Fetch supermercado by id.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "fetchProdutoById")
	@WebResult(name = "fetchProdutoByIdReturn")
	@WSDLDocumentation(value = "Returns the desired supermercado.")
	public ProdutoResponse fetchProdutoById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh supermercados.
	 * 
	 * @param request the request
	 * 
	 * @return the supermercado response
	 */
	@WebMethod(action = "refreshProdutos")
	@WebResult(name = "refreshProdutosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the supermercado tables.")
	public ProdutoResponse refreshProdutos(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch supermercados by request.
	 * 
	 * @param request the request
	 * @return the supermercado paged response
	 */
	@WebMethod(action = "fetchProdutosByRequest")
	@WebResult(name = "fetchProdutosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of supermercados paged.")
	public ProdutoResponse fetchProdutosByRequest(@WebParam(name = "request") PagedInquiryRequest request);

}
