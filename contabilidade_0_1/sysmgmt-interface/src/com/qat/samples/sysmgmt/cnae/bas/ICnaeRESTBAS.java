package com.qat.samples.sysmgmt.cnae.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.response.EmpresaResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface ICnaeRESTBAS
{

	@POST
	@Path("/insertCnae/")
	public EmpresaResponse insertCnae(EmpresaMaintenanceRequest request);

	@POST
	@Path("/updateCnae/")
	public EmpresaResponse updateCnae(EmpresaMaintenanceRequest request);

	@POST
	@Path("/deleteCnae/")
	public EmpresaResponse deleteCnae(EmpresaMaintenanceRequest request);

	@POST
	@Path("/fetchCnaeById/")
	public EmpresaResponse fetchCnaeById(FetchByIdRequest request);

	@POST
	@Path("/fetchCnaeByRequest/")
	public EmpresaResponse fetchCnaeByRequest(EmpresaInquiryRequest request);

}
