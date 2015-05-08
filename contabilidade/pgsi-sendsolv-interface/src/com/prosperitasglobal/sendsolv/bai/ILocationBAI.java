package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;

/**
 * The Interface ILocationBAI.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface ILocationBAI
{

	/**
	 * Insert location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse insertLocation(LocationMaintenanceRequest request);

	/**
	 * Update location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse updateLocation(LocationMaintenanceRequest request);

	/**
	 * Delete location.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse deleteLocation(LocationMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse fetchLocationById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse fetchLocationByRequest(PagedInquiryRequest request);

	/**
	 * Fetch location by organization.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse fetchLocationByOrganization(PagedInquiryRequest request);

	/**
	 * Update risk.
	 *
	 * @param request the request
	 * @return the risk response
	 */
	public RiskResponse updateRisk(RiskMaintenanceRequest request);

	/**
	 * Apply status.
	 *
	 * @param request the request
	 * @return the location response
	 */
	public LocationResponse applyStatus(LocationMaintenanceRequest request);

}