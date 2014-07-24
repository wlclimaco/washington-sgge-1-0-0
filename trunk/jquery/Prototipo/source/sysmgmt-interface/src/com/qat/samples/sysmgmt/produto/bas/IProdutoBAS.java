package com.qat.samples.sysmgmt.produto.bas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;

import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.CadastroMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.EmbalagemInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.EmbalagemMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.CadastroResponse;
import com.qat.samples.sysmgmt.produto.model.response.EmbalagemResponse;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * The Interface IProdutoBAS. (Business Area Service - BAS)
 */
@WebService(serviceName = "ProdutoService", targetNamespace = "http://www.supermercado.kinghost.net/sysmgmt", portName = "ProdutoServicePort")
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
	public ProdutoResponse fetchAllProdutos(@WebParam(name = "request") ProdutoInquiryRequest request);

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

	// ======================================================
	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "insertCadastro")
	@WebResult(name = "insertCadastroReturn")
	@WSDLDocumentation(value = "Insert a produto record and optionally returns a list of produtos.")
	public CadastroResponse insertCadastro(@WebParam(name = "request") CadastroMaintenanceRequest request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "updateCadastro")
	@WebResult(name = "updateCadastroReturn")
	@WSDLDocumentation(value = "Updates the selected produto record and optionally returns a list of produtos.")
	public CadastroResponse updateCadastro(@WebParam(name = "request") CadastroMaintenanceRequest request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "deleteCadastro")
	@WebResult(name = "deleteCadastroReturn")
	@WSDLDocumentation(value = "Deletes a produto record and optionally returns a list of produtos.")
	public CadastroResponse deleteCadastro(@WebParam(name = "request") CadastroMaintenanceRequest request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "fetchAllCadastros")
	@WebResult(name = "fetchAllCadastrosReturn")
	@WSDLDocumentation(value = "Returns a complete list of all produtos.")
	public CadastroResponse fetchAllCadastros(@WebParam(name = "request") CadastroInquiryRequest request);

	/**
	 * Fetch produto by id.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "fetchCadastroById")
	@WebResult(name = "fetchCadastroByIdReturn")
	@WSDLDocumentation(value = "Returns the desired produto.")
	public CadastroResponse fetchCadastroById(@WebParam(name = "request") FetchByIdRequest request);

	/**
	 * Refresh produtos.
	 * 
	 * @param request the request
	 * 
	 * @return the produto response
	 */
	@WebMethod(action = "refreshCadastros")
	@WebResult(name = "refreshCadastrosReturn")
	@WSDLDocumentation(value = "Used for demo purposes only, rebuilds the produto tables.")
	public CadastroResponse refreshCadastros(@WebParam(name = "request") RefreshRequest request);

	/**
	 * Fetch produtos by request.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "fetchCadastrosByRequest")
	@WebResult(name = "fetchCadastrosByRequestReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public CadastroResponse fetchCadastrosByRequest(@WebParam(name = "request") CadastroInquiryRequest request);

	// ===================================
	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "insertEmbalagem")
	@WebResult(name = "insertEmbalagemReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse insertEmbalagem(@WebParam(name = "request") EmbalagemMaintenanceRequest request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "updateEmbalagem")
	@WebResult(name = "updateEmbalagemReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse updateEmbalagem(@WebParam(name = "request") EmbalagemMaintenanceRequest request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "deleteEmbalagem")
	@WebResult(name = "deleteEmbalagemReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse deleteEmbalagem(@WebParam(name = "request") EmbalagemMaintenanceRequest request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	@WebMethod(action = "fetchAllEmbalagems")
	@WebResult(name = "fetchAllEmbalagemsReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse fetchAllEmbalagems(@WebParam(name = "request") EmbalagemInquiryRequest request);

	// ================
	/**
	 * Insert produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "insertUniMed")
	@WebResult(name = "insertUniMedReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse insertUniMed(@WebParam(name = "request") EmbalagemMaintenanceRequest request);

	/**
	 * Update produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "updateUniMed")
	@WebResult(name = "updateUniMedReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse updateUniMed(@WebParam(name = "request") EmbalagemMaintenanceRequest request);

	/**
	 * Delete produto.
	 * 
	 * @param request the request
	 * @return the produto paged response
	 */
	@WebMethod(action = "deleteUniMed")
	@WebResult(name = "deleteUniMedReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse deleteUniMed(@WebParam(name = "request") EmbalagemMaintenanceRequest request);

	/**
	 * Fetch all produtos.
	 * 
	 * @param request the request
	 * @return the produto response
	 */
	@WebMethod(action = "fetchAllUniMeds")
	@WebResult(name = "fetchAllUniMedsReturn")
	@WSDLDocumentation(value = "Returns a list of produtos paged.")
	public EmbalagemResponse fetchAllUniMeds(@WebParam(name = "request") EmbalagemInquiryRequest request);

}
