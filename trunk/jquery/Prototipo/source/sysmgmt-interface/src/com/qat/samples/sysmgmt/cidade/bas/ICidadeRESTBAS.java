package com.qat.samples.sysmgmt.cidade.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.cidade.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.cidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.cidade.model.response.CidadeResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface ICidadeRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface ICidadeRESTBAS
{

	/**
	 * Insert county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/insertCidade/")
	public CidadeResponse insertCidade(CidadeMaintenanceRequest request);

	/**
	 * Update county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/updateCidade/")
	public CidadeResponse updateCidade(CidadeMaintenanceRequest request);

	/**
	 * Delete county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/deleteCidade/")
	public CidadeResponse deleteCidade(CidadeMaintenanceRequest request);

	/**
	 * Refresh counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/refreshCidades/")
	public CidadeResponse refreshCidades(RefreshRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchAllCidades/")
	public CidadeResponse fetchAllCidades(FetchAllRequest request);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchCidadeById/")
	public CidadeResponse fetchCidadeById(FetchByIdRequest request);

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	@POST
	@Path("/fetchCidadesByRequest/")
	public CidadeResponse fetchCidadesByRequest(CidadeInquiryRequest request);
}
