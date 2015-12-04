package com.qat.samples.sysmgmt.produto.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.PlanoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;
import com.qat.samples.sysmgmt.produto.model.response.ServicoResponse;

/**
 * The Interface IProdutoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ProdutoService", targetNamespace = "http://qat.com/sysmgmt", portName = "ProdutoServicePort")
public interface IProdutoBAS
{

	@WebMethod(action = "insertProduto")
	@WebResult(name = "insertProdutoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ProdutoResponse insertProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	@WebMethod(action = "updateProduto")
	@WebResult(name = "updateProdutoReturn")
	@WSDLDocumentation(value = "Updates the selected supermercado record and optionally returns a list of supermercados.")
	public ProdutoResponse updateProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	@WebMethod(action = "deleteProduto")
	@WebResult(name = "deleteProdutoReturn")
	@WSDLDocumentation(value = "Deletes a supermercado record and optionally returns a list of supermercados.")
	public ProdutoResponse deleteProduto(@WebParam(name = "request") ProdutoMaintenanceRequest request);

	@WebMethod(action = "fetchProdutosByRequest")
	@WebResult(name = "fetchProdutosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of supermercados paged.")
	public ProdutoResponse fetchProdutosByRequest(@WebParam(name = "request") ProdutoInquiryRequest request);

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

	// Serviço

	@WebMethod(action = "insertServico")
	@WebResult(name = "insertServicoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public ServicoResponse insertServico(@WebParam(name = "request") ServicoMaintenanceRequest request);

	@WebMethod(action = "updateServico")
	@WebResult(name = "updateServicoReturn")
	@WSDLDocumentation(value = "Updates the selected supermercado record and optionally returns a list of supermercados.")
	public ServicoResponse updateServico(@WebParam(name = "request") ServicoMaintenanceRequest request);

	@WebMethod(action = "deleteServico")
	@WebResult(name = "deleteServicoReturn")
	@WSDLDocumentation(value = "Deletes a supermercado record and optionally returns a list of supermercados.")
	public ServicoResponse deleteServico(@WebParam(name = "request") ServicoMaintenanceRequest request);

	@WebMethod(action = "fetchServicosByRequest")
	@WebResult(name = "fetchServicosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of supermercados paged.")
	public ServicoResponse fetchServicosByRequest(@WebParam(name = "request") ServicoInquiryRequest request);

	// Plano
	@WebMethod(action = "insertPlano")
	@WebResult(name = "insertPlanoReturn")
	@WSDLDocumentation(value = "Insert a supermercado record and optionally returns a list of supermercados.")
	public PlanoResponse insertPlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	@WebMethod(action = "updatePlano")
	@WebResult(name = "updatePlanoReturn")
	@WSDLDocumentation(value = "Updates the selected supermercado record and optionally returns a list of supermercados.")
	public PlanoResponse updatePlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	@WebMethod(action = "deletePlano")
	@WebResult(name = "deletePlanoReturn")
	@WSDLDocumentation(value = "Deletes a supermercado record and optionally returns a list of supermercados.")
	public PlanoResponse deletePlano(@WebParam(name = "request") PlanoMaintenanceRequest request);

	@WebMethod(action = "fetchPlanosByRequest")
	@WebResult(name = "fetchPlanosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of supermercados paged.")
	public PlanoResponse fetchPlanosByRequest(@WebParam(name = "request") PlanoInquiryRequest request);

}
