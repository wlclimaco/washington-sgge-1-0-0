package com.sensus.mlc.smartpoint.bcf;

import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CountLightResponse;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryGeocodeSmartpointInfoResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.LightingConfigurationsResponse;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;

/**
 * The Interface ISmartPointAccessorBCF.
 */
public interface ISmartPointAccessorBCF
{
	/**
	 * Fetch all lights.
	 * 
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse fetchAllLights(InquiryLightRequest inquiryLightRequest);

	/**
	 * Count lights.
	 * 
	 * @param request the request
	 * @return the count light response
	 */
	CountLightResponse countLights(LightingControlRequest request);

	/**
	 * Fetch light by id.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse fetchLightById(LightRequest lightRequest);

	/**
	 * Fetch light id by rni id.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse fetchLightIdByRniId(LightRequest lightRequest);

	/**
	 * Fetch property valid values.
	 * 
	 * @param request the property valid values request
	 * @return the property valid values response
	 */
	PropertyValidValuesResponse fetchPropertyValidValues(PropertyValidValuesRequest request);

	/**
	 * Fetch update light status.
	 * 
	 * @param processRequest the process request
	 * @return the light response
	 */
	LightResponse fetchUpdateLightStatus(ProcessRequest processRequest);

	/**
	 * Fetch all search light.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the inquiry light response
	 */
	InquiryCustomSearchResponse fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest);

	/**
	 * Fetch status message by light id.
	 * 
	 * @param request the request
	 * @return the status message response
	 */
	StatusMessageResponse fetchStatusMessageByLightID(LightRequest request);

	/**
	 * Fetch status message by id.
	 * 
	 * @param lightRequest the light request
	 * @return the status message response
	 */
	StatusMessageResponse fetchStatusMessageById(LightRequest lightRequest);

	/**
	 * Fetch ligthing configurations by part number.
	 * 
	 * @param request the request
	 * @return the lighting configurations response
	 */
	LightingConfigurationsResponse fetchLigthingConfigurationsByPartNumber(LightingConfigurationRequest request);

	/**
	 * Fetch light history.
	 * 
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse fetchLightHistory(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch light history header.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	LightResponse fetchLightHistoryHeader(LightRequest lightRequest);

	/**
	 * Fetch all column filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Fetch current alarm status messages by light.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	CurrentAlarmWarningMessageResponse fetchCurrentAlarmStatusMessagesByLight(LightRequest lightRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Generate light history file csv.
	 * 
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch smartpoints to map.
	 * 
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry geocode smartpoint info response
	 */
	InquiryGeocodeSmartpointInfoResponse fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest);
}
