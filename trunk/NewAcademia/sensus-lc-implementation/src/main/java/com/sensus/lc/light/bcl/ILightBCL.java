package com.sensus.lc.light.bcl;

import java.math.BigInteger;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PartNumberConfiguration;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;

/**
 * Contains ONLY methods related to working with a Light.
 * Do not update this interface without checking with Gustavo Peres
 */
public interface ILightBCL
{

	/**
	 * Fetch all by request.
	 * 
	 * @param request the request
	 * @return the fetch all response
	 */
	InternalResultsResponse<Light> fetchAllByRequest(LightRequest request);

	/**
	 * Fetch all to map by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<GeocodeLightInfo> fetchAllToMapByRequest(LightRequest request);

	/**
	 * Fetch map bounds by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<GeocodeLightInfo> fetchMapBoundsByRequest(LightRequest request);

	/**
	 * Count all by request.
	 * 
	 * @param request the request
	 * @return the fetch all response
	 */
	InternalResultsResponse<Integer> countAllByRequest(LightRequest request);

	/**
	 * Fetch by id.
	 * 
	 * @param request the request
	 * @return the light response
	 */
	InternalResultsResponse<Light> fetchById(FetchByIdRequest request);

	/**
	 * Fetch part number configurations by model number.
	 * 
	 * @param modelNumber the model number
	 * @return the internal results response
	 */
	InternalResultsResponse<PartNumberConfiguration> fetchPartNumberConfigurationsByModelNumber(String modelNumber);

	/**
	 * Fetch light intensity by light.
	 * 
	 * @param lightRequest the light request
	 * @return the light intensity enum
	 */
	IntensityEnum fetchLightIntensityByLight(LightRequest lightRequest);

	/**
	 * Update light.
	 * 
	 * @param request the request
	 * @return the maintenance request
	 */
	InternalResponse updateLightBase(LightMaintenanceRequest request);

	/**
	 * Update light mass.<br>
	 * When is necessary to update several light with same value.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateLightMass(LightMassUpdateRequest request);

	/**
	 * Insert light.
	 * 
	 * @param request the request
	 * @return the maintenance request
	 */
	InternalResponse insertLight(LightMaintenanceRequest request);

	/**
	 * Delete light references.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse deleteLightReferences(LightDeleteRequest request);

	/**
	 * Update last operational data.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request);

	/**
	 * Update schedule.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateSchedule(ScheduleMaintenanceRequest request);

	/**
	 * Update configuration.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateConfiguration(ConfigurationMaintenanceRequest request);

	/**
	 * Reset min max value.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> resetMinMaxValue(LightRequest request);

	/**
	 * Fetch attribute changes.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<BigInteger> fetchAttributeChanges(LightRequest request);
}
