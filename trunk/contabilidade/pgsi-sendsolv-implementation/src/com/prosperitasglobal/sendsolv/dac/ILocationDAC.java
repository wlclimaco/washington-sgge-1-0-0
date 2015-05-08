package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ILocationDAC.
 */
public interface ILocationDAC
{

	/**
	 * Update location.
	 *
	 * @param location the location
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Location> updateLocation(Location location);

	/**
	 * Insert location.
	 *
	 * @param location the location
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Location> insertLocation(Location location);

	/**
	 * Delete location.
	 *
	 * @param location the location
	 * @return the internal response
	 */
	public InternalResponse deleteLocation(Location location);

	/**
	 * Fetch frequency based event by id.
	 *
	 * @param id the id
	 * @return The {@link InternalResultsResponse} containing the {@link java.util.List} of {@link FrequencyBasedEvent}
	 *         collection.
	 */
	public InternalResultsResponse<FrequencyBasedEvent> fetchFrequencyBasedEventById(Integer id);

	/**
	 * Fetch location by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Location> fetchLocationById(FetchByIdRequest request);

	/**
	 * Fetch all locations.
	 *
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Location> fetchAllLocations();

	/**
	 * Fetch location by request.
	 *
	 * @param request the request
	 * @return the internal results response< location>
	 */
	public InternalResultsResponse<Location> fetchLocationByRequest(PagedInquiryRequest request);

	/**
	 * Update risk.
	 *
	 * @param request the request
	 * @return the internal results response< risk>
	 */
	public InternalResultsResponse<Risk> updateRisk(RiskMaintenanceRequest request);

	/**
	 * Apply location status.
	 *
	 * @param location the location
	 * @return the internal response
	 */
	public InternalResponse applyLocationStatus(Location location);

}
