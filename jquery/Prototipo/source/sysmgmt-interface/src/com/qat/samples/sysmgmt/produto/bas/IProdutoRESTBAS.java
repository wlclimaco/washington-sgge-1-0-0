package com.qat.samples.sysmgmt.produto.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoMaintenanceRequest;
import com.qat.samples.sysmgmt.produto.model.response.ProdutoResponse;

/**
 * The Interface IProdutoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IProdutoRESTBAS
{

	/**
	 * Insert county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/insertProduto/")
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request);

	/**
	 * Update county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/updateProduto/")
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request);

	/**
	 * Delete county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/deleteProduto/")
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request);

	/**
	 * Refresh counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/refreshProdutos/")
	public ProdutoResponse refreshProdutos(RefreshRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchAllProdutos/")
	public ProdutoResponse fetchAllProdutos(FetchAllRequest request);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchProdutoById/")
	public ProdutoResponse fetchProdutoById(FetchByIdRequest request);

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	@POST
	@Path("/fetchProdutosByRequest/")
	public ProdutoResponse fetchProdutosByRequest(PagedInquiryRequest request);
}
