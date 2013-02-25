package com.sensus.mlc.smartpoint.bcf;

import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;

public interface ISmartPointUpdaterBCF
{
	/**
	 * Upsert light property.
	 * 
	 * @param lightStatusNotificationRequest the light status notification request
	 * @return the light response
	 */
	LightResponse upsertLightProperty(LightStatusNotificationRequest lightStatusNotificationRequest);

	/**
	 * Update light protected.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse updateLightProtected(LightRequest lightRequest);

	/**
	 * Insert search light.
	 * 
	 * @param request the request
	 * @return the light response
	 */
	CustomSearchResponse insertCustomSearch(CustomSearchRequest request);

	/**
	 * Delete search light.
	 * 
	 * @param request the request
	 * @return the light response
	 */
	CustomSearchResponse deleteCustomSearch(CustomSearchRequest request);

	/**
	 * Update light lat lng.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse updateLightLatLng(LightRequest lightRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest);

	/**
	 * Reset min max value.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse resetMinMaxValue(LightRequest lightRequest);

	/**
	 * Insert column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Update light status.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse updateLightStatus(LightRequest lightRequest);
}
