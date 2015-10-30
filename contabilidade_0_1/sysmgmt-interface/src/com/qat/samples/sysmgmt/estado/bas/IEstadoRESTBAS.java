package com.qat.samples.sysmgmt.estado.bas;

import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.response.EstadoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

/**
 * The Interface ISupermercadoRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IEstadoRESTBAS
{

	@POST
	@Path("/insertEstado/")
	public EstadoResponse insertEstado(EstadoMaintenanceRequest request);

	@POST
	@Path("/updateEstado/")
	public EstadoResponse updateEstado(EstadoMaintenanceRequest request);

	@POST
	@Path("/deleteEstado/")
	public EstadoResponse deleteEstado(EstadoMaintenanceRequest request);

	@POST
	@Path("/fetchEstadoById/")
	public EstadoResponse fetchEstadoById(FetchByIdRequest request);

	@POST
	@Path("/fetchEstadoByRequest/")
	public EstadoResponse fetchEstadoByRequest(EstadoInquiryRequest request);

}
