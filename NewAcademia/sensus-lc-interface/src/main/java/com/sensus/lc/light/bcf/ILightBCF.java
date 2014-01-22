package com.sensus.lc.light.bcf;

import com.sensus.common.model.response.MaintenanceResponse;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.light.model.response.ChangesResponse;
import com.sensus.lc.light.model.response.ConfigurationMaintenanceResponse;
import com.sensus.lc.light.model.response.CountResponse;
import com.sensus.lc.light.model.response.FetchAllResponse;
import com.sensus.lc.light.model.response.GeocodeLightInfoResponse;
import com.sensus.lc.light.model.response.LastOperationalDataMaintenanceResponse;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.light.model.response.LightingConfigurationsResponse;
import com.sensus.lc.light.model.response.ScheduleMaintenanceResponse;

/**
 * Contains methods only related to working with the Light.
 * Do not update this interface without checking with Gustavo Peres.
 */
public interface ILightBCF
{
	/**
	 * Fetch all by request.
	 * 
	 * @param request the request
	 * @return the fetch all response
	 */
	FetchAllResponse fetchAllByRequest(LightRequest request);

	/**
	 * Count all by request.
	 * 
	 * @param request the request
	 * @return the count response
	 */
	CountResponse countAllByRequest(LightRequest request);

	/**
	 * Fetch by id.
	 * 
	 * @param request the request
	 * @return the light response
	 */
	LightResponse fetchById(FetchByIdRequest request);

	/**
	 * Update light base.
	 * 
	 * @param request the request
	 * @return the maintenance response
	 */
	MaintenanceResponse updateLightBase(LightMaintenanceRequest request);

	/**
	 * Update light mass.<br>
	 * When is necessary to update several light with same value.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	MaintenanceResponse updateLightMass(LightMassUpdateRequest request);

	/**
	 * Reset min max value.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse resetMinMaxValue(LightRequest lightRequest);

	/**
	 * Fetch all to map by request.
	 * 
	 * @param request the request
	 * @return the geocode light info response
	 */
	GeocodeLightInfoResponse fetchAllToMapByRequest(LightRequest request);

	/**
	 * Fetch map bounds by request.
	 * 
	 * @param request the request
	 * @return the geocode light info response
	 */
	GeocodeLightInfoResponse fetchMapBoundsByRequest(LightRequest request);

	/**
	 * Fetch part number configurations by model number.
	 * 
	 * @param modelNumber the model number
	 * @return the lighting configurations response
	 */
	LightingConfigurationsResponse fetchPartNumberConfigurationsByModelNumber(String modelNumber);

	/**
	 * Update last operational data.
	 * 
	 * @param request the request
	 * @return the last operational data maintenance response
	 */
	LastOperationalDataMaintenanceResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request);

	/**
	 * Update schedule.
	 * 
	 * @param request the request
	 * @return the schedule maintenance response
	 */
	ScheduleMaintenanceResponse updateSchedule(ScheduleMaintenanceRequest request);

	/**
	 * Update configuration.
	 * 
	 * @param request the request
	 * @return the configuration maintenance response
	 */
	ConfigurationMaintenanceResponse updateConfiguration(ConfigurationMaintenanceRequest request);

	/**
	 * Fetch attribute changes.
	 * 
	 * @param request the request
	 * @return the changes response
	 */
	ChangesResponse fetchAttributeChanges(LightRequest request);
}
