package com.qat.samples.sysmgmt.controle.bas;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;

/**
 * The Interface ICidadeRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IControleRESTBAS
{

	@POST
	@Path("/fetchAllControles/")
	public ControleResponse fetchAllControles(ControleInquiryRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	@POST
	@Path("/fetchControleByPage/")
	public ControleResponse fetchControleByPage(ControleInquiryRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	@POST
	@Path("/fetchControlesByRequest/")
	public ControleResponse fetchControlesByRequest(ControleInquiryRequest request);

	/**
	 * Fetch controle by action.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	@POST
	@Path("/fetchControleByAction/")
	public ControleResponse fetchControleByAction(ControleInquiryRequest request);

	@POST
	@Path("/fetchAllAcessos/")
	public AcessosResponse fetchAllAcessos(@WebParam(name = "request") AcessosInquiryRequest request);

}
