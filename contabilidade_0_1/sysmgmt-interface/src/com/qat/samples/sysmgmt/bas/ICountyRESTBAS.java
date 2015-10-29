package com.qat.samples.sysmgmt.bas;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;

/**
 * The Interface ICountyRESTBAS. (Business Area Service - BAS)
 */
@Consumes("application/json")
@Produces("application/json")
public interface ICountyRESTBAS
{

	/**
	 * Insert county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/insertCounty/")
	public CountyResponse insertCounty(CountyMaintenanceRequest request);

	/**
	 * Update county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/updateCounty/")
	public CountyResponse updateCounty(CountyMaintenanceRequest request);

	/**
	 * Delete county.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/deleteCounty/")
	public CountyResponse deleteCounty(CountyMaintenanceRequest request);

	/**
	 * Refresh counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/refreshCounties/")
	public CountyResponse refreshCounties(RefreshRequest request);

	/**
	 * Fetch all counties.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchAllCounties/")
	public CountyResponse fetchAllCounties(FetchAllRequest request);

	/**
	 * Fetch county by id.
	 * 
	 * @param request the request
	 * 
	 * @return the county response
	 */
	@POST
	@Path("/fetchCountyById/")
	public CountyResponse fetchCountyById(FetchByIdRequest request);

	/**
	 * Fetch counties by request.
	 * 
	 * @param request the request
	 * @return the county paged response
	 */
	@POST
	@Path("/fetchCountiesByRequest/")
	public CountyResponse fetchCountiesByRequest(PagedInquiryRequest request);
}
