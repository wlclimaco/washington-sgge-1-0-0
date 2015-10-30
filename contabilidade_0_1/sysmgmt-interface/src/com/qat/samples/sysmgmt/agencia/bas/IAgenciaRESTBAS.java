package com.qat.samples.sysmgmt.agencia.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaMaintenanceRequest;
import com.qat.samples.sysmgmt.agencia.model.response.AgenciaResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IAgenciaRESTBAS
{

	@POST
	@Path("/insertAgencia/")
	public AgenciaResponse insertAgencia(AgenciaMaintenanceRequest request);

	@POST
	@Path("/updateAgencia/")
	public AgenciaResponse updateAgencia(AgenciaMaintenanceRequest request);

	@POST
	@Path("/deleteAgencia/")
	public AgenciaResponse deleteAgencia(AgenciaMaintenanceRequest request);

	@POST
	@Path("/fetchAgenciaById/")
	public AgenciaResponse fetchAgenciaById(FetchByIdRequest request);

	@POST
	@Path("/fetchAgenciaByRequest/")
	public AgenciaResponse fetchAgenciaByRequest(AgenciaInquiryRequest request);

}
