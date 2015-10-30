package com.qat.samples.sysmgmt.contato.bas;

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
public interface IContatoRESTBAS
{

	@POST
	@Path("/insertEmpresa/")
	public EmpresaResponse insertEmpresa(EmpresaMaintenanceRequest request);

	@POST
	@Path("/updateEmpresa/")
	public EmpresaResponse updateEmpresa(EmpresaMaintenanceRequest request);

	@POST
	@Path("/deleteEmpresa/")
	public EmpresaResponse deleteEmpresa(EmpresaMaintenanceRequest request);

	@POST
	@Path("/fetchEmpresaById/")
	public EmpresaResponse fetchEmpresaById(FetchByIdRequest request);

	@POST
	@Path("/fetchEmpresaByRequest/")
	public EmpresaResponse fetchEmpresaByRequest(EmpresaInquiryRequest request);

}
