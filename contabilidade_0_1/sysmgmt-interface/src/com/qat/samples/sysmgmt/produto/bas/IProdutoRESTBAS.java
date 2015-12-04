package com.qat.samples.sysmgmt.produto.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
 * The Interface IProdutoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IProdutoRESTBAS
{

	@POST
	@Path("/insertProduto/")
	public ProdutoResponse insertProduto(ProdutoMaintenanceRequest request);

	@POST
	@Path("/updateProduto/")
	public ProdutoResponse updateProduto(ProdutoMaintenanceRequest request);

	@POST
	@Path("/deleteProduto/")
	public ProdutoResponse deleteProduto(ProdutoMaintenanceRequest request);

	@POST
	@Path("/fetchProdutosByRequest/")
	public ProdutoResponse fetchProdutosByRequest(ProdutoInquiryRequest request);

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

	// serviço
	@POST
	@Path("/insertServico/")
	public ServicoResponse insertServico(ServicoMaintenanceRequest request);

	@POST
	@Path("/updateServico/")
	public ServicoResponse updateServico(ServicoMaintenanceRequest request);

	@POST
	@Path("/deleteServico/")
	public ServicoResponse deleteServico(ServicoMaintenanceRequest request);

	@POST
	@Path("/fetchServicosByRequest/")
	public ServicoResponse fetchServicosByRequest(ServicoInquiryRequest request);

	// Plano
	@POST
	@Path("/insertPlano/")
	public PlanoResponse insertPlano(PlanoMaintenanceRequest request);

	@POST
	@Path("/updatePlano/")
	public PlanoResponse updatePlano(PlanoMaintenanceRequest request);

	@POST
	@Path("/deletePlano/")
	public PlanoResponse deletePlano(PlanoMaintenanceRequest request);

	@POST
	@Path("/fetchPlanosByRequest/")
	public PlanoResponse fetchPlanosByRequest(PlanoInquiryRequest request);

}
