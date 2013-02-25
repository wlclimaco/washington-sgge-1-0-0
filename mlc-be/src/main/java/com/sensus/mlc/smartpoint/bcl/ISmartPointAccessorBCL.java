package com.sensus.mlc.smartpoint.bcl;

import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Interface ISmartPointAccessorBCL.
 */
public interface ISmartPointAccessorBCL
{
	/**
	 * Fetch all lights.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAllLights(InquiryLightRequest inquiryLightRequest);

	/**
	 * Count lights.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> countLights(LightingControlRequest request);

	/**
	 * Fetch light by id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightById(LightRequest lightRequest);

	/**
	 * Fetch all lights to apply schedule.
	 *
	 * @param scheduleRequest the schedule request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAllLightsToApplySchedule(ScheduleRequest scheduleRequest);

	/**
	 * Fetch light by rni id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightByRniId(LightRequest lightRequest);

	/**
	 * Fetch property valid value by name.
	 *
	 * @param request the property valid values request
	 * @return the internal results response
	 */
	InternalResultsResponse<PropertyValidValue> fetchPropertyValidValues(PropertyValidValuesRequest request);

	/**
	 * Fetch update light status.
	 *
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchUpdateLightStatus(ProcessRequest processRequest);

	/**
	 * Fetch all search light.
	 *
	 * @param request the request
	 * @return the inquiry light response
	 */
	InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryPaginationRequest request);

	/**
	 * Checks if light belongs to tenant.
	 *
	 * @param request the request
	 * @param strictValidation if true, sends an error message when RniId is not found. Binding and Setup don't need to
	 *            have existing RniIds, so they set it to false.
	 * @return the internal response
	 */
	InternalResponse isLightInTenant(LightStatusRequest request, Boolean strictValidation);

	/**
	 * Verify custom search.
	 *
	 * @param search the search
	 * @return the search light
	 */
	SearchLight verifyCustomSearch(SearchLight search);

	/**
	 * Can insert custom search.
	 *
	 * @param tenant the tenant
	 * @param customSearch the custom search
	 * @param userId the user id
	 * @param list the list
	 * @param allowedGroupIdList the allowed group id list
	 * @return the boolean
	 */
	Boolean fetchCanInsertCustomSearch(Tenant tenant, CustomSearch customSearch, Integer userId,
			List<MessageInfo> list, List<Integer> allowedGroupIdList);

	/**
	 * Fetch status message by light id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> fetchStatusMessageByLightID(LightRequest request);

	/**
	 * Fetch status message by id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> fetchStatusMessageById(LightRequest lightRequest);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch all lights by process.
	 *
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAllLightsByProcess(ProcessRequest processRequest);

	/**
	 * Generate generic file csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse generateGenericFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch ligthing configurations by part number.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<SensusPartNumberConfiguration> fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest request);

	/**
	 * Fetch light intensity by light.
	 *
	 * @param lightId the light id
	 * @param percentage the percentage
	 * @return the light intensity enum
	 */
	LightIntensityEnum fetchLightIntensityByLight(Integer lightId, Integer percentage);

	/**
	 * Fetch light history.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightHistory> fetchLightHistory(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch light history.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(LightRequest lightRequest);

	/**
	 * Fetch all lights to Export CSV.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, String>> fetchAllLightsToCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Generate summary file csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse generateSummaryFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch all column filters.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal results response
	 */
	InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Fetch current alarm status messages by light.
	 *
	 * @param lightID the light id
	 * @return the internal results response
	 */
	InternalResultsResponse<CurrentAlarmWarningMessage> fetchCurrentAlarmStatusMessagesByLight(
			final Integer lightID);

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
	 * @return the internal results response
	 */
	InternalResultsResponse<GeocodeSmartpointInfo> fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest);

	/**
	 * Check if valid binding message.
	 *
	 * @param lightRequest the light request
	 * @return the boolean
	 */
	Boolean checkIfValidBindingMessage(LightRequest lightRequest);


	/**
	 * Fetch light schedule by id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightScheduleById(LightRequest lightRequest);

	/**
	 * Fetch light last operation data.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightLastOperationDataById(LightRequest lightRequest);

	/**
	 * Fetch light location.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightLocationById(LightRequest lightRequest);

	/**
	 * Fetch light configuration by id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightConfigurationById(LightRequest lightRequest);
}
