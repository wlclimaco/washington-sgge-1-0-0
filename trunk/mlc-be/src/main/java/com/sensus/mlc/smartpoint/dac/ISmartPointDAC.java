package com.sensus.mlc.smartpoint.dac;

import java.util.Calendar;
import java.util.Date;
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
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.SmartpointMiddleMap;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Interface ISmartPointDAC.
 *
 * @author - Gustavo - QAT Brazil
 */
/**
 * @author QATEmployee
 *
 */
public interface ISmartPointDAC
{

	/**
	 * Insert light.
	 *
	 * @param lightRequest the light request
	 * @return the integer
	 */
	Integer insertLight(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Fetch all light.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAllLights(InquiryLightRequest inquiryLightRequest);

	/**
	 * Count devices.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> countLights(LightingControlRequest request);

	/**
	 * Fetch light by id.
	 *
	 * @param lightRequest the light
	 * @return the light response
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
	 * Property valid values.
	 *
	 * @param request the request
	 * @return the property valida values list
	 */
	List<PropertyValidValue> fetchPropertyValidValues(PropertyValidValuesRequest request);

	/**
	 * Update light protected.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> updateLightProtected(LightRequest lightRequest);

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
	 * Insert custom search.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request);

	/**
	 * Delete custom search.
	 *
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse deleteCustomSearch(CustomSearchRequest request);

	/**
	 * Fetch all custom search.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest);

	/**
	 * Checks if is light in tenant.
	 *
	 * @param rniId the rni id
	 * @param tenantRniCode the tenant rni code
	 * @param allowedGroupIdList the allowed group id list
	 * @return the integer
	 */
	Integer isLightInTenant(Integer rniId, String tenantRniCode, List<Integer> allowedGroupIdList);

	/**
	 * Location has changed.
	 *
	 * @param rniId the rni id
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param allowedGroupIdList the allowed group id list
	 * @return the boolean
	 */
	Boolean locationHasChanged(Integer rniId, Double latitude, Double longitude, List<Integer> allowedGroupIdList);

	/**
	 * Delete addr tags for light.
	 *
	 * @param rniId the rni id
	 * @return the internal response
	 */
	InternalResponse deleteAddrTagsForLight(Integer rniId);

	/**
	 * Fetch status message by light id.
	 *
	 * @param lightId the light id
	 * @param allowedGroupIdList the allowed group id list
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> fetchStatusMessageByLightID(Integer lightId,
			List<Integer> allowedGroupIdList);

	/**
	 * Fetch status message by id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> fetchStatusMessageById(LightRequest lightRequest);

	/**
	 * Fetch status message by light id.
	 *
	 * @param lightId the light id
	 *            categoryEnum the light type id
	 * @param categoryEnum the category enum
	 * @param allowedGroupIdList the allowed group id list
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> fetchStatusMessageByLightIDMessageType(Integer lightId,
			StatusMessageCategoryEnum categoryEnum, List<Integer> allowedGroupIdList);

	/**
	 * Update light state.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateLightState(LightRequest lightRequest);

	/**
	 * Insert operational data.
	 *
	 * @param userId the user id
	 * @param idStatusMessage the id status message
	 * @param operationalData the operational data
	 * @return the internal response
	 */
	InternalResponse insertOperationalData(String userId, Integer idStatusMessage,
			List<OperationalData> operationalData);

	/**
	 * Delete light from all tag schedule group.
	 *
	 * @param rniId the rni id
	 * @return the internal response
	 */
	InternalResponse deleteLightFromAllTagScheduleGroup(Integer rniId);

	/**
	 * Insert status message.
	 *
	 * @param statusMessage the status message
	 * @param userId the user id
	 * @param tenantId the tenant id
	 * @param lightId the light id
	 * @param simpleNotification the simple notification
	 * @return the integer
	 */
	Integer insertStatusMessage(StatusMessage statusMessage, String userId, Integer tenantId, Integer lightId,
			Boolean simpleNotification);

	/**
	 * Insert status message status subtype.
	 *
	 * @param idStatusMessage the id status message
	 * @param statusExceptionTypeEnumValue the status exception type enum value
	 * @param userId the user id
	 * @param updateAnalytics the update analytics
	 * @return the integer
	 *         the internal results response
	 */
	void insertStatusMessageStatusSubtype(Integer idStatusMessage,
			Integer statusExceptionTypeEnumValue,
			String userId, Boolean updateAnalytics);

	/**
	 * Update analytics alarm warning.
	 *
	 * @param statusExceptionTypeEnumValue the status exception type enum value
	 * @param idStatusMessage the id status message
	 * @param userId the user id
	 * @param operator the operator
	 * @return the internal results response
	 */
	InternalResponse updateAnalyticsAlarmWarning(Integer statusExceptionTypeEnumValue, Integer idStatusMessage,
			String userId,
			String operator);

	/**
	 * Update analytics alarm warning.
	 *
	 * @param tenantId the tenant id
	 * @param lightId the light id
	 * @param statusExceptionTypeEnumValue the status exception type enum value
	 * @param userId the user id
	 * @param operator the operator
	 * @return the internal response
	 */
	InternalResponse updateAnalyticsAlarmWarning(Integer tenantId, Integer lightId,
			Integer statusExceptionTypeEnumValue, String userId, String operator);

	/**
	 * Generate file csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the InquiryLightResponse
	 */
	InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch all lights by process.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAllLightsByProcess(ProcessRequest request);

	/**
	 * Fetch ligthing configurations by part number.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<SensusPartNumberConfiguration> fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest request);

	/**
	 * Fetch light intensity percentage by light.
	 *
	 * @param lightId the light id
	 * @return the internal results response
	 */
	InternalResultsResponse<SensusPartNumberConfiguration> fetchLightIntensityPercentageByLight(Integer lightId);

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
	 * Fetch all light to Export csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, String>> fetchAllLightsToCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Reset min max value.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse resetMinMaxValue(LightRequest lightRequest);

	/**
	 * Generate summary file csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InquiryLightResponse generateSummaryFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Generate light history file csv.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the inquiry light response
	 */
	InternalResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch smart points by tenant.
	 *
	 * @param tenantID The tenant ID.
	 * @return A list of smart points related to the given tenant.
	 */
	List<Light> fetchLightsByTenant(Integer tenantID);

	/**
	 * Fetch the last status message for a given light.
	 *
	 * @param lightID The light ID.
	 * @return The last status message for a given light.
	 */
	Date fetchLastStatusMessageDateFromLight(Integer lightID);

	/**
	 * Fetch the last status message for a given light. Internally, this method
	 * calls <code>fetchLastStatusMessageDateFromLight(Integer)</code> of this interface,
	 * returning a <code>java.util.Calendar</code> instead of a <code>java.util.Date</code>.
	 *
	 * @param lightID The light ID.
	 * @return The last status message for a given light.
	 */
	Calendar fetchLastStatusMessageCalendarFromLight(Integer lightID);

	/**
	 * Fetch the last status sub-type ID from light.
	 *
	 * @param lightID The light ID.
	 * @return The last status sub-type id from light.
	 */
	Integer fetchLastStatusSubtypeIDFromLight(Integer lightID);

	/**
	 * Insert communication failure warnings.
	 *
	 * @param lightRequest the light request
	 */
	void insertCommunicationFailureWarnings(LightRequest lightRequest);

	/**
	 * Insert columns filters to user.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */

	InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert filters to custom search.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest);

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
	 * @return the list
	 */
	List<CurrentAlarmWarningMessage> fetchCurrentAlarmStatusMessagesByLight(Integer lightID);

	/**
	 * Insert current alarm status message.
	 *
	 * @param currentAlarmWarningMessage the current alarm warning message
	 */
	void insertCurrentAlarmStatusMessage(CurrentAlarmWarningMessage currentAlarmWarningMessage);

	/**
	 * Fetch current light messages by tenant.
	 *
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchCurrentLightMessagesByTenant(TenantRequest tenantRequest);

	/**
	 * Delete current alarm warning message by light id.
	 *
	 * @param lightID the light id
	 */
	void deleteCurrentAlarmWarningMessageByLightID(Integer lightID);

	/**
	 * Fetch smartpoints to map.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<GeocodeSmartpointInfo> fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch count smartpoints to map.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchCountSmartpointsToMap(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch limited smartpoints to map.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLimitedSmartpointsToMap(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch middle map.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<SmartpointMiddleMap> fetchMiddleMap(InquiryLightRequest inquiryLightRequest);

	/**
	 * Fetch middle smartpoints to map.
	 *
	 * @param inquiryLightRequest the inquiry light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchMiddleSmartpointsToMap(InquiryLightRequest inquiryLightRequest);

	/**
	 * Update current status message.
	 *
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	InternalResponse updateCurrentStatusMessage(LightRequest lightRequest);

	/**
	 * Insert smart point.
	 *
	 * @param lightRequest the light request
	 * @return the integer
	 */
	Integer insertSmartPoint(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Insert light configuration.
	 *
	 * @param lightRequest the light request
	 */
	void insertLightConfiguration(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Insert light schedule.
	 *
	 * @param lightRequest the light request
	 */
	void insertLightSchedule(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Insert light last operational data.
	 *
	 * @param lightRequest the light request
	 */
	void insertLightLastOperationalData(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Insert light location.
	 *
	 * @param lightRequest the light request
	 */
	void insertLightLocation(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Fetch light to insert.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	Light fetchLightToInsert(GuaranteeLightExistenceRequest lightRequest);

	/**
	 * Fetch status message status subtype.
	 *
	 * @param idStatusMessage the id status message
	 * @param statusExceptionTypeEnumValue the status exception type enum value
	 * @param userId the user id
	 * @return the internal results response
	 */
	Integer fetchStatusMessageStatusSubtype(Integer idStatusMessage,
			Integer statusExceptionTypeEnumValue, String userId);


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

	/**
	 * Check if valid binding message.
	 *
	 * @param lightRequest the light request
	 * @return the boolean
	 */
	Boolean checkIfValidBindingMessage(LightRequest lightRequest);
}
