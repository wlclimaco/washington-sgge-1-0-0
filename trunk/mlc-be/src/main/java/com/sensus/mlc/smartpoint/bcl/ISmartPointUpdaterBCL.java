package com.sensus.mlc.smartpoint.bcl;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Interface ISmartPointUpdaterBCL.
 */
public interface ISmartPointUpdaterBCL
{

	/**
	 * Update light status.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> updateLightStatus(LightRequest lightRequest);

	/**
	 * Insert column filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert columns filters to custom search.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertColumnsFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest);

	/**
	 * Reset min max value.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> resetMinMaxValue(LightRequest lightRequest);

	/**
	 * Insert status message.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse insertStatusMessage(LightRequest request);

	/**
	 * Insert search light.
	 *
	 * @param request the request
	 * @return the light response
	 */
	InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request);

	/**
	 * Delete search light.
	 *
	 * @param request the request
	 * @return the light response
	 */
	InternalResponse deleteCustomSearch(CustomSearchRequest request);

	/**
	 * Update light protected.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> updateLightProtected(LightRequest lightRequest);

	/**
	 * Verify communication message.
	 *
	 * @param userContext the user context
	 * @return the internal response
	 */
	void verifyCommunicationMessage(UserContext userContext);

	/**
	 * Update light.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateLight(LightRequest lightRequest);

	/**
	 * Update light schedule.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateLightSchedule(LightRequest lightRequest);

	/**
	 * Update light last operation data.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateLightLastOperationData(LightRequest lightRequest);

	/**
	 * Update light location.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateLightLocation(LightRequest lightRequest);

	/**
	 * Update light configuration.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateLightConfiguration(LightRequest lightRequest);

	/**
	 * Insert light schedule.
	 *
	 * @param lightRequest the light request
	 */
	void insertLightSchedule(GuaranteeLightExistenceRequest lightRequest);
}
