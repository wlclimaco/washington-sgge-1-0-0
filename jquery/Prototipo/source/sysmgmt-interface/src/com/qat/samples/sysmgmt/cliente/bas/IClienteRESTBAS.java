package com.qat.samples.sysmgmt.cliente.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.cliente.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.cliente.model.response.ClienteResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IClienteRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface IClienteRESTBAS
{

	/**
	 * Insert county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/insertCliente/")
	public ClienteResponse insertCliente(ClienteMaintenanceRequest request);

	/**
	 * Update county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/updateCliente/")
	public ClienteResponse updateCliente(ClienteMaintenanceRequest request);

	/**
	 * Delete county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/deleteCliente/")
	public ClienteResponse deleteCliente(ClienteMaintenanceRequest request);

	/**
	 * Refresh counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/refreshClientes/")
	public ClienteResponse refreshClientes(RefreshRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchAllClientes/")
	public ClienteResponse fetchAllClientes(FetchAllRequest request);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchClienteById/")
	public ClienteResponse fetchClienteById(FetchByIdRequest request);

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	@POST
	@Path("/fetchClientesByRequest/")
	public ClienteResponse fetchClientesByRequest(PagedInquiryRequest request);
}
