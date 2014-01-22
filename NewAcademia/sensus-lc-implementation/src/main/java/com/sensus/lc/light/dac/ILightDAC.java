package com.sensus.lc.light.dac;

import java.math.BigInteger;

import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.CommunicationFailureRequest;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.OperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;

/**
 * Light DAC Interface.
 * 
 * Check with Thiago and Gustavo before changing.
 */
public interface ILightDAC
{

	/**
	 * Fetch all lights based on LightRequest parameter.
	 * 
	 * @param request
	 * @return InternalResultsResponse<Light>
	 */
	InternalResultsResponse<Light> fetchAllByRequest(LightRequest request);

	/**
	 * @param request
	 * @return CountResponse
	 */
	InternalResultsResponse<Integer> countAllByRequest(LightRequest request);

	/**
	 * Fetch lights to build a map, using a lightRequest as parameter.
	 * 
	 * @param request
	 * @return InternalResultsResponse<GeocodeLightInfo>
	 */
	CachedResultsResponse<GeocodeLightInfo> fetchAllToMapByRequest(LightRequest request);

	/**
	 * Fetch a light by it's id's.
	 * 
	 * @param request
	 * @return InternalResultsResponse<Light>
	 */
	InternalResultsResponse<Light> fetchById(FetchByIdRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResponse updateLightBase(LightMaintenanceRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResponse updateLight(LightMaintenanceRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResponse deleteLightReference(LightDeleteRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResultsResponse<Light> insertLight(LightMaintenanceRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResponse updateSchedule(ScheduleMaintenanceRequest request);

	/**
	 * @param request
	 * @return InternalResponse
	 */
	InternalResponse updateConfiguration(ConfigurationMaintenanceRequest request);

	/**
	 * @param request
	 * @return InternalResultsResponse<GeocodeLightInfo>
	 */
	InternalResultsResponse<GeocodeLightInfo> fetchMapBoundsByRequest(LightRequest request);

	/**
	 * Reset min max value.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse resetMinMaxValue(LastOperationalDataMaintenanceRequest request);

	/**
	 * Insert operational data.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse insertOperationalData(OperationalDataMaintenanceRequest request);

	/**
	 * Fetch lights to add communication failure.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchLightsToAddCommunicationFailure(CommunicationFailureRequest request);

	/**
	 * Fetch lights in communication failure.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchLightsInCommunicationFailure(CommunicationFailureRequest request);

	/**
	 * Calculate light consumption in communication failure.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse calculateLightConsumptionInCommunicationFailure(CommunicationFailureRequest request);

	/**
	 * Fetch attribute changes.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<BigInteger> fetchAttributeChanges(LightRequest request);

}
