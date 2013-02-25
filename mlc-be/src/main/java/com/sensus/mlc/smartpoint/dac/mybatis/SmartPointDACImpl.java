package com.sensus.mlc.smartpoint.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCConvertUtil.getIdsToString;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.ACTION_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.ALLOWED_GROUP_ID_LIST;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.BULB_NUMBER;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.CITY_NAME;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.CREATE_USER;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.CURRENT_LIGHT_STATUS;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.CUSTOM_SEARCH_DESCRIPTION;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.CUSTOM_SEARCH_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.CUSTOM_SEARCH_NAME;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.DELIMITER_WITH_QUOTE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.DIMMABLE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.END_DATE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.EVENT_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.FAILURE_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.GROUP_IDS;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.HOUSING;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.INTENSITY;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.LAMP_TYPE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.LAT;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.LIGHT_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.LIGHT_STATE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.LNG;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.MESSAGE_DATE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.MESSAGE_TYPE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.MESSAGE_TYPE_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.MODIFY_USER;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.OPERATIONAL_DATA_TYPE_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.OPERATOR;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.OPERATOR_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.OPERATOR_PLUS;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.ORDER_BY;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PAGE_SIZE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.POLE_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROCESS_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROPERTY_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROPERTY_NAMES;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROPERTY_VALUE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROPERTY_VALUES;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROP_NAME;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROTECT;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.PROTECTED;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.RNI_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.RNI_LIGHT_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.RNI_TENANT_CODE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.SCHEDULE_IDS;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.SEARCH_TEXT;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.SIMPLE_NOTIFICATION;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.SIMPLE_QUOTE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.START_DATE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.START_ROW;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.STATUS_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.STATUS_LIST;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.STATUS_MESSAGE_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.STATUS_SUBTYPE_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.STATUS_TYPE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.STREET_NAME;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.TAG_IDS;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.TENANT_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.TYPE_ALARM;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.TYPE_WARNING;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.USER_ID;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.USER_IDS;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.VALUE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.WATTAGE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.ZIP_CODE;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getIdsByProperty;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getPropertiesKeyString;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getPropertiesValuesString;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getScheduleIdFromEvenOffsetSchedule;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getStatusListString;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getStatusOverrideListString;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getStringIdsFromSearchLight;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.hasFilter;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.setOrderByAndPropName;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.setSelectedOrUnselectedIds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.model.LCActionCategoryEnum;
import com.sensus.mlc.process.model.ProcessFilter;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessOrderByEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.csv.LightCSVDataSource;
import com.sensus.mlc.smartpoint.csv.LightHistoryCSVDataSource;
import com.sensus.mlc.smartpoint.csv.LightMapCSVDataSource;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.dacd.SmartPointDACD;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.CustomSearchOrderByEnum;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.SmartpointMiddleMap;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
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
 * The Class SmartPointDACImpl.
 */
public class SmartPointDACImpl extends SqlSessionDaoSupport implements ISmartPointDAC
{
	/** The Constant CHECK_IF_VALID_BINDING. */
	private static final String CHECK_IF_VALID_BINDING = "checkIfValidBinding";
	/** The Constant SMARTPOINT_NAMESPACE. */
	private static final String SMARTPOINT_NAMESPACE = "SmartPoint.";

	/** The Constant FETCH_ALL_LIGHTS_BY_PROCESS. */
	private static final String FETCH_ALL_LIGHTS_BY_PROCESS = SMARTPOINT_NAMESPACE + "fetchAllLightsByProcess";

	/** The Constant FETCH_LIGHT_BY_ID. */
	private static final String FETCH_LIGHT_BY_ID = SMARTPOINT_NAMESPACE + "fetchLightById";

	/** The Constant UPDATE_LIGHT_PROPERTIES_FOR_COMMUNICATION_FAILURE. */
	private static final String UPDATE_LIGHT_PROPERTIES_FOR_COMMUNICATION_FAILURE = SMARTPOINT_NAMESPACE
			+ "updateLightPropertiesForCommunicationFailure";

	/** The Constant FETCH_LAST_STATUS_SUBTYPE_ID_FROM_LIGHT. */
	private static final String FETCH_LAST_STATUS_SUBTYPE_ID_FROM_LIGHT = SMARTPOINT_NAMESPACE
			+ "fetchLastStatusSubtypeIDFromLight";

	/** The Constant FETCH_LAST_STATUS_MESSAGE_DATE_FROM_LIGHT. */
	private static final String FETCH_LAST_STATUS_MESSAGE_DATE_FROM_LIGHT = SMARTPOINT_NAMESPACE
			+ "fetchLastStatusMessageDateFromLight";

	/** The Constant FETCH_LIGHTS_BY_TENANT. */
	private static final String FETCH_LIGHTS_BY_TENANT = SMARTPOINT_NAMESPACE + "fetchLightsByTenant";

	/** The Constant RESET_MIN_MAX. */
	private static final String RESET_MIN_MAX = SMARTPOINT_NAMESPACE + "resetMinMax";

	/** The Constant FETCH_ALL_LIGHTS_TO_CSV. */
	private static final String FETCH_ALL_LIGHTS_TO_CSV = SMARTPOINT_NAMESPACE + "fetchAllLightsToCSV";

	/** The Constant FETCH_LIGHT_HISTORY_HEADER. */
	private static final String FETCH_LIGHT_HISTORY_HEADER = SMARTPOINT_NAMESPACE + "fetchLightHistoryHeader";

	/** The Constant FETCH_LIGHT_HISTORY_PAGINATION_TOTAL_ROWS. */
	private static final String FETCH_LIGHT_HISTORY_PAGINATION_TOTAL_ROWS = SMARTPOINT_NAMESPACE
			+ "fetchLightHistoryPaginationTotalRows";

	/** The Constant FETCH_LIGHT_HISTORY. */
	private static final String FETCH_LIGHT_HISTORY = SMARTPOINT_NAMESPACE + "fetchLightHistory";

	/** The Constant FETCH_LIGHT_INTENSITY_PERCENTAGE_BY_LIGHT. */
	private static final String FETCH_LIGHT_INTENSITY_PERCENTAGE_BY_LIGHT = SMARTPOINT_NAMESPACE
			+ "fetchLightIntensityPercentageByLight";

	/** The Constant FETCH_LIGTHING_CONFIGURATIONS_BY_PART_NUMBER. */
	private static final String FETCH_LIGTHING_CONFIGURATIONS_BY_PART_NUMBER = SMARTPOINT_NAMESPACE
			+ "fetchLigthingConfigurationsByPartNumber";

	/** The Constant COUNT_CUSTOM_SEARCH_BY_NAME. */
	private static final String COUNT_CUSTOM_SEARCH_BY_NAME = SMARTPOINT_NAMESPACE + "countCustomSearchByName";

	/** The Constant DELETE_LIGHT_FROM_ALL_TAG_SCHEDULE_GROUP. */
	private static final String DELETE_LIGHT_FROM_ALL_TAG_SCHEDULE_GROUP =
			SMARTPOINT_NAMESPACE + "deleteLightFromAllTagScheduleGroup";

	/** The Constant UPDATE_ANALYTICS_ALARMS_WARNINGS. */
	private static final String UPDATE_ANALYTICS_ALARMS_WARNINGS = SMARTPOINT_NAMESPACE
			+ "updateAnalyticsAlarmsWarnings";

	/** The Constant UPDATE_ANALYTICS_ALARMS_WARNINGS_BY_LIGHT. */
	private static final String UPDATE_ANALYTICS_ALARMS_WARNINGS_BY_LIGHT = SMARTPOINT_NAMESPACE
			+ "updateAnalyticsAlarmsWarningsByLight";

	/** The Constant INSERT_STATUS_MESSAGE_SUBTYPE. */
	private static final String INSERT_STATUS_MESSAGE_SUBTYPE = SMARTPOINT_NAMESPACE + "insertStatusMessageSubtype";

	/** The Constant INSERT_STATUS_MESSAGE. */
	private static final String INSERT_STATUS_MESSAGE = SMARTPOINT_NAMESPACE + "insertStatusMessage";

	/** The Constant UPDATE_LIGHT_STATE. */
	private static final String UPDATE_LIGHT_STATE = SMARTPOINT_NAMESPACE + "updateLightState";

	/** The Constant INSERT_OPERATIONAL_DATA. */
	private static final String INSERT_OPERATIONAL_DATA = SMARTPOINT_NAMESPACE + "insertOperationalData";

	/** The Constant CHECK_IF_LIGHT_IN_TENANT. */
	private static final String CHECK_IF_LIGHT_IN_TENANT = SMARTPOINT_NAMESPACE + "checkIfLightInTenant";

	/** The Constant LOCATION_HAS_CHANGED. */
	private static final String LOCATION_HAS_CHANGED = SMARTPOINT_NAMESPACE + "locationHasChanged";

	/** The Constant PAGINATION_TOTAL_ROWS_CUSTOM_SEARCH. */
	private static final String PAGINATION_TOTAL_ROWS_CUSTOM_SEARCH = SMARTPOINT_NAMESPACE
			+ "paginationTotalRowsCustomSearch";

	/** The Constant FETCH_ALL_CUSTOM_SEARCH. */
	private static final String FETCH_ALL_CUSTOM_SEARCH = SMARTPOINT_NAMESPACE + "fetchAllCustomSearch";

	/** The Constant DELETE_CUSTOM_SEARCH. */
	private static final String DELETE_CUSTOM_SEARCH = SMARTPOINT_NAMESPACE + "deleteCustomSearch";

	/** The Constant INSERT_CUSTOM_SEARCH_PROPERTY. */
	private static final String INSERT_CUSTOM_SEARCH_PROPERTY = SMARTPOINT_NAMESPACE + "insertCustomSearchProperty";

	/** The Constant INSERT_CUSTOM_SEARCH. */
	private static final String INSERT_CUSTOM_SEARCH = SMARTPOINT_NAMESPACE + "insertCustomSearch";

	/** The Constant FETCH_LIGHT_TO_INSERT. */
	private static final String FETCH_LIGHT_TO_INSERT = SMARTPOINT_NAMESPACE + "fetchLightToInsert";

	/** The Constant INSERT_LIGHT. */
	private static final String INSERT_LIGHT = SMARTPOINT_NAMESPACE + "insertLight";

	/** The Constant INSERT_LIGHT_CONFIGURATION. */
	private static final String INSERT_LIGHT_CONFIGURATION = SMARTPOINT_NAMESPACE + "insertLightConfiguration";

	/** The Constant INSERT_LIGHT_LOCATION. */
	private static final String INSERT_LIGHT_LOCATION = SMARTPOINT_NAMESPACE + "insertLightLocation";

	/** The Constant INSERT_LIGHT_SCHEDULE. */
	private static final String INSERT_LIGHT_SCHEDULE = SMARTPOINT_NAMESPACE + "insertLightSchedule";

	/** The Constant INSERT_SMARTPOINT. */
	private static final String INSERT_SMARTPOINT = SMARTPOINT_NAMESPACE + "insertSmartPoint";

	/** The Constant FETCH_LIGHT_ID_BY_RNI_ID. */
	private static final String FETCH_LIGHT_BY_RNI_ID = SMARTPOINT_NAMESPACE + "fetchLightByRniId";

	//	/** The Constant UPSERT_LIGHT_PROPERTY. */
	//	private static final String UPSERT_LIGHT_PROPERTY = SMARTPOINT_NAMESPACE + "upsertLightProperty";

	/** The Constant UPDATE_LIGHT_PROTECTED. */
	private static final String UPDATE_LIGHT_PROTECTED = SMARTPOINT_NAMESPACE + "updateLightProtected";

	/** The Constant UPDATE_LIGHT_LAT_LNG. */
	private static final String UPDATE_LIGHT_LAT_LNG = SMARTPOINT_NAMESPACE + "updateLightLatLng";

	/** The Constant DELETE_ADDR_TAGS_FOR_LIGHT. */
	private static final String DELETE_ADDR_TAGS_FOR_LIGHT = SMARTPOINT_NAMESPACE + "deleteAddrTagsForLight";

	/** The Constant FETCH_LIGHT_STATUS_MESSAGE_TYPE. */
	private static final String FETCH_LIGHT_STATUS_MESSAGE_TYPE = SMARTPOINT_NAMESPACE + "fetchLightStatusMessageType";

	/** The Constant FETCH_LIGHT_STATUS_MESSAGE. */
	private static final String FETCH_LIGHT_STATUS_MESSAGE = SMARTPOINT_NAMESPACE + "fetchLightStatusMessage";

	/** The Constant FETCH_LIGHT_STATUS_MESSAGE. */
	private static final String FETCH_STATUS_MESSAGE_BY_ID = SMARTPOINT_NAMESPACE + "fetchLightStatusMessageById";

	/** The Constant FETCH_PROPERTY_VALID_VALUE. */
	private static final String FETCH_PROPERTY_VALID_VALUE = SMARTPOINT_NAMESPACE + "fetchPropertyValidValue";

	/** The Constant FETCH_ALL_LIGHTS_TO_APPLY_SCHEDULE. */
	private static final String FETCH_ALL_LIGHTS_TO_APPLY_SCHEDULE = SMARTPOINT_NAMESPACE
			+ "fetchAllLightsToApplySchedule";

	/** The Constant FETCH_ALL_LIGHTS. */
	private static final String FETCH_ALL_LIGHTS = SMARTPOINT_NAMESPACE + "fetchAllLights";

	/** The Constant FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT. */
	private static final String FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT = SMARTPOINT_NAMESPACE
			+ "fetchCurrentAlarmStatusMessagesByLight";

	/** The Constant INSERT_CURRENT_ALARM_STATUS_MESSAGE. */
	private static final String INSERT_CURRENT_ALARM_STATUS_MESSAGE = SMARTPOINT_NAMESPACE
			+ "insertCurrentAlarmStatusMessage";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = SMARTPOINT_NAMESPACE + "paginationTotalRows";

	/** The Constant SMARTPOINT_FETCH_ALL_COLUMN_FILTERS. */
	private static final String FETCH_ALL_COLUMN_FILTERS = SMARTPOINT_NAMESPACE + "fetchAllColumnFilters";

	/** The Constant SMARTPOINT_INSERT_FILTER_TO_CUSTOM_SEARCH. */
	private static final String INSERT_FILTER_TO_CUSTOM_SEARCH = SMARTPOINT_NAMESPACE + "insertFilterToCustomSearch";

	/** The Constant SMARTPOINT_INSERT_COLUMNS_TO_CUSTOM_SEARCH. */
	private static final String INSERT_COLUMNS_TO_CUSTOM_SEARCH = SMARTPOINT_NAMESPACE + "insertColumnsToCustomSearch";

	/** The Constant UPDATE_LIGHT_CURRENTE_STATUS_MESSAGE. */
	private static final String UPDATE_LIGHT_CURRENTE_STATUS_MESSAGE = SMARTPOINT_NAMESPACE
			+ "updateCurrentStatusMessage";

	/** The Constant DELETE_CURRENT_ALARM_WARNING_MESSAGES_BY_LIGHT_ID. */
	private static final String DELETE_CURRENT_ALARM_WARNING_MESSAGES_BY_LIGHT_ID = SMARTPOINT_NAMESPACE
			+ "deleteCurrentAlarmWarningMessagesByLightID";

	/** The Constant COUNT_LIGHTS. */
	private static final String COUNT_LIGHTS = SMARTPOINT_NAMESPACE + "countLights";

	/** The Constant FETCH_CURRENT_LIGHT_MESSAGE_BY_TENANT. */
	private static final String FETCH_CURRENT_LIGHT_MESSAGE_BY_TENANT = SMARTPOINT_NAMESPACE
			+ "fetchCurrentLightMessageByTenant";

	/** The Constant fetchSmartpointsToMap. */
	private static final String FETCH_ALL_SMARTPOINTS_TO_MAP = SMARTPOINT_NAMESPACE
			+ "fetchSmartpointsToMap";

	/** The Constant SENSUS_MLC_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS =
			"sensus.mlc.customsearchvalidator.customsearch.already.exists";

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE7. */
	private static final Integer PARAMSIZE7 = 7;

	/** The Constant PARAMSIZE17. */
	private static final Integer PARAMSIZE17 = 17;

	/** The Constant BLINK_LEVE. */
	private static final String BLINK_LEVEL = "blink_level";

	/** The Constant OVERRIDE. */
	private static final String OVERRIDE = "override";

	/** The Constant OVERRIDE_PER_DATE. */
	private static final String OVERRIDE_PER_DATE = "override_per_date";

	/** The Constant OVERRIDE_CREATE_DATE. */
	private static final String OVERRIDE_CREATE_DATE = "override_create_date";

	/** The Constant CURRENT_STATUS_MESSAGE_ID. */
	private static final String CURRENT_STATUS_MESSAGE_ID = "current_status_message_id";

	/** The Constant FETCH_COUNT_SMARTPOINTS_TO_MAP. */
	private static final String FETCH_COUNT_SMARTPOINTS_TO_MAP = SMARTPOINT_NAMESPACE + "fetchCountSmartpointsToMap";

	/** The Constant FETCH_LIMITED_SMARTPOINTS_TO_MAP. */
	private static final String FETCH_LIMITED_SMARTPOINTS_TO_MAP = SMARTPOINT_NAMESPACE
			+ "fetchLimitedSmartpointsToMap";

	/** The Constant FETCH_MIDDLE_MAP. */
	private static final String FETCH_MIDDLE_MAP = SMARTPOINT_NAMESPACE + "fetchMiddleMap";

	/** The Constant FETCH_MIDDLE_SMARTPOINT_TO_MAP. */
	private static final String FETCH_MIDDLE_SMARTPOINT_TO_MAP = SMARTPOINT_NAMESPACE + "fetchMiddleSmartpointsToMap";

	/** The Constant ALPHA_COLUMN_DESC. */
	private static final Object ALPHA_COLUMN_DESC = "name DESC";

	/** The Constant ALPHA_COLUMN. */
	private static final Object ALPHA_COLUMN = "name";

	/** The Constant INSERT_LIGHT_LAST_OPERATION_DATA. */
	private static final String INSERT_LIGHT_LAST_OPERATION_DATA = SMARTPOINT_NAMESPACE + "insertLightLastOperationalData";

	/** The Constant FETCH_COUNT_STATUS_MESSAGE_SUBTYPE. */
	private static final String FETCH_COUNT_STATUS_MESSAGE_SUBTYPE = SMARTPOINT_NAMESPACE + "fetchCountStatusMessageSubtype";

	/** The Constant UPDATE_LIGHT_CONFIGURATION. */
	private static final String UPDATE_LIGHT_CONFIGURATION = SMARTPOINT_NAMESPACE + "updateLightConfiguration";

	/** The Constant UPDATE_LIGHT_LOCATION. */
	private static final String UPDATE_LIGHT_LOCATION = SMARTPOINT_NAMESPACE + "updateLightLocation";

	/** The Constant UPDATE_LIGHT_LAST_OPERATION_DATA. */
	private static final String UPDATE_LIGHT_LAST_OPERATION_DATA = SMARTPOINT_NAMESPACE + "updateLightLastOperationData";

	/** The Constant UPDATE_LIGHT_SCHEDULE. */
	private static final String UPDATE_LIGHT_SCHEDULE = SMARTPOINT_NAMESPACE + "updateLightSchedule";

	/** The Constant UPDATE_LIGHT. */
	private static final String UPDATE_LIGHT = SMARTPOINT_NAMESPACE + "updateLight";

	/** The Constant FETCH_LIGHT_SCHEDULE_BY_ID. */
	private static final String FETCH_LIGHT_SCHEDULE_BY_ID = SMARTPOINT_NAMESPACE + "fetchLightScheduleById";

	/** The Constant FETCH_LIGHT_LAST_OPERATION_BY_ID. */
	private static final String FETCH_LIGHT_LAST_OPERATION_BY_ID = SMARTPOINT_NAMESPACE + "fetchLightLastOperationDataById";

	/** The Constant FETCH_LIGHT_LOCATION_BY_ID. */
	private static final String FETCH_LIGHT_LOCATION_BY_ID = SMARTPOINT_NAMESPACE + "fetchLightLocationById";

	/** The Constant FETCH_LIGHT_CONFIGURATION_BY_ID. */
	private static final String FETCH_LIGHT_CONFIGURATION_BY_ID = SMARTPOINT_NAMESPACE + "fetchLightConfigurationById";

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(SmartPointDACImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchAllLights(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLights(InquiryLightRequest request)
	{
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(request);
		Integer totalRows = (Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, paramMap);
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#countLights(com.sensus.common.model.Request)
	 */
	@Override
	public InternalResultsResponse<Integer> countLights(LightingControlRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();

		if (request instanceof InquiryLightRequest)
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap = SmartPointDACD.getParametersToFetchAllLights((InquiryLightRequest)request);
			doQueryForList(getSqlSession(), PAGINATION_TOTAL_ROWS, paramMap, response);

			if (!response.hasResults())
			{
				response.addResult(0);
			}

			return response;
		}

		doQueryForList(getSqlSession(), COUNT_LIGHTS, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchLightById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_BY_ID, lightRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightsByIdList(java.util.List)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsToApplySchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS_TO_APPLY_SCHEDULE, scheduleRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchPropertyValidValueByName(java.lang.String)
	 * This method does not return an InternalResultsResponse because it is cached.
	 * Cached methods must implements Serializable.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PropertyValidValue> fetchPropertyValidValues(PropertyValidValuesRequest request)
	{
		return doQueryForList(getSqlSession(), FETCH_PROPERTY_VALID_VALUE, request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchStatusMessageByLightID(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageByLightID(Integer lightId,
			List<Integer> allowedGroupIdList)
	{
		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(LIGHT_ID, lightId);
		paramMap.put(ALLOWED_GROUP_ID_LIST, allowedGroupIdList);

		StatusMessage statusMessage = (StatusMessage)doQueryForObject(getSqlSession(),
				FETCH_LIGHT_STATUS_MESSAGE, paramMap);

		if (!ValidationUtil.isNull(statusMessage))
		{
			response.getResultsList().add(statusMessage);
		}
		else
		{
			response.setStatus(Status.NoRowsFoundError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchStatusMessageById(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageById(LightRequest lightRequest)
	{
		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
		paramMap.put(STATUS_MESSAGE_ID, lightRequest.getFirstMessage().getId());
		StatusMessage statusMessage =
				(StatusMessage)doQueryForObject(getSqlSession(), FETCH_STATUS_MESSAGE_BY_ID, paramMap);
		if (!ValidationUtil.isNull(statusMessage))
		{
			response.getResultsList().add(statusMessage);
		}
		else
		{
			response.setStatus(Status.NoRowsFoundError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchStatusMessageByLightID(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> fetchStatusMessageByLightIDMessageType(Integer lightId,
			StatusMessageCategoryEnum categoryEnum, List<Integer> allowedGroupIdList)
	{
		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(LIGHT_ID, lightId);
		paramMap.put(MESSAGE_TYPE_ID, categoryEnum.getValue());
		paramMap.put(ALLOWED_GROUP_ID_LIST, allowedGroupIdList);

		StatusMessage statusMessage = (StatusMessage)doQueryForObject(getSqlSession(),
				FETCH_LIGHT_STATUS_MESSAGE_TYPE, paramMap);

		if (!ValidationUtil.isNull(statusMessage))
		{
			response.getResultsList().add(statusMessage);
		}
		else
		{
			response.setStatus(Status.NoRowsFoundError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#deleteAddrTagsForLight(java.lang.Integer)
	 */
	@Override
	public InternalResponse deleteAddrTagsForLight(Integer rniId)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
		paramMap.put(RNI_LIGHT_ID, rniId);

		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_ADDR_TAGS_FOR_LIGHT, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#updateLightProtected(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> updateLightProtected(LightRequest lightRequest)
	{
		InquiryLightRequest inquityLghtRequest = LCHelp.createInquiryLightRequest(lightRequest);
		HashMap<String, Object> paramMap = getParametersToFetchAllLights(inquityLghtRequest);
		paramMap.put(MODIFY_USER, lightRequest.getUserContext().getUserId());
		paramMap.put(PROTECTED, lightRequest.getProtect());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doUpdate(getSqlSession(), UPDATE_LIGHT_PROTECTED, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchLightIdByRniId(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightByRniId(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_BY_RNI_ID, lightRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#insertLight(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public Integer insertLight(GuaranteeLightExistenceRequest lightRequest)
	{
		Integer result =
				(Integer)doQueryForObject(getSqlSession(), INSERT_LIGHT, lightRequest);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertSmartPoint(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public Integer insertSmartPoint(GuaranteeLightExistenceRequest lightRequest)
	{
		Integer result =
				(Integer)doQueryForObject(getSqlSession(), INSERT_SMARTPOINT, lightRequest);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightConfiguration(com.sensus.mlc.smartpoint.model.request
	 * .GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightConfiguration(GuaranteeLightExistenceRequest lightRequest)
	{
		doQueryForObject(getSqlSession(), INSERT_LIGHT_CONFIGURATION, lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightToInsert(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public Light fetchLightToInsert(GuaranteeLightExistenceRequest lightRequest)
	{
		Light light =
				(Light)doQueryForObject(getSqlSession(), FETCH_LIGHT_TO_INSERT, lightRequest);

		if (ValidationUtil.isNull(light))
		{
			return light;
		}

		light.setRniId(lightRequest.getFirstLight().getRniId());

		return light;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dao.ISmartPointDAO#insertSearchLight(com.sensus.mlc.smartpoint.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		CustomSearch customSearch = request.getCustomSearch();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(CUSTOM_SEARCH_NAME, customSearch.getName());
		paramMap.put(CUSTOM_SEARCH_DESCRIPTION, customSearch.getDescription());
		paramMap.put(CREATE_USER, request.getUserContext().getUserId());
		paramMap.put(USER_ID, request.getUserContext().getId());
		paramMap.put(TENANT_ID, request.getTenant().getId());

		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		Integer customSearchId = (Integer)doQueryForObject(getSqlSession(),
				INSERT_CUSTOM_SEARCH, paramMap);

		if (ValidationUtil.isNullOrZero(customSearchId))
		{
			LOG.error("Error inserting custom search.");
			response.setStatus(Status.PersistenceError);
			return response;
		}

		customSearch.setId(customSearchId);

		// all the others parameters are propertyEnum
		if (ValidationUtil.isNullOrEmpty(customSearch.getSearchParameters()))
		{
			response.addResult(customSearch);
			return response;
		}

		return insertCustomSearchParameters(request.getUserContext().getUserId(), customSearch);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#deleteCustomSearch(com.sensus.mlc.smartpoint.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_CUSTOM_SEARCH, request
				.getCustomSearch(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchAllCustomSearch(com.sensus.mlc.base.model.request.
	 * InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(TENANT_ID, inquiryPaginationRequest.getTenant().getId());
		paramMap.put(USER_ID, inquiryPaginationRequest.getUserContext().getId());
		paramMap.put(PAGE_SIZE, inquiryPaginationRequest.getPageSize());
		paramMap.put(START_ROW, inquiryPaginationRequest.getStartRow());
		paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryPaginationRequest.getAllowedGroupIdList());
		paramMap.put(ORDER_BY, CustomSearchOrderByEnum.CUSTOM_SEARCH_NAME.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryPaginationRequest.getSortExpressions().get(0));
		}

		doQueryForList(getSqlSession(), FETCH_ALL_CUSTOM_SEARCH, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS_CUSTOM_SEARCH, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#locationHasChanged(java.lang.Integer, java.lang.Double,
	 * java.lang.Double)
	 */
	@Override
	public Boolean locationHasChanged(Integer rniId, Double latitude, Double longitude, List<Integer> allowedGroupIdList)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(RNI_LIGHT_ID, rniId);
		paramMap.put(LAT, latitude);
		paramMap.put(LNG, longitude);
		paramMap.put(ALLOWED_GROUP_ID_LIST, allowedGroupIdList);

		return (Boolean)doQueryForObject(getSqlSession(), LOCATION_HAS_CHANGED, paramMap);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#isLightInTenant(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer isLightInTenant(Integer rniId, String tenantRniCode, List<Integer> allowedGroupIdList)
	{
		// -1 = smartpoint does not belong to this tenant.
		// -2 = RniId not found.
		// -3 = Tenant not found.
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(RNI_LIGHT_ID, rniId);
		paramMap.put(RNI_TENANT_CODE, tenantRniCode);
		paramMap.put(ALLOWED_GROUP_ID_LIST, allowedGroupIdList);

		Integer result = (Integer)doQueryForObject(getSqlSession(),
				CHECK_IF_LIGHT_IN_TENANT, paramMap);

		return result;
	}

	/**
	 * Process operational data.
	 *
	 * @param userName the user id
	 * @param idStatusMessage the id status message
	 * @param operationalData the operational data
	 * @return the internal response
	 */
	@Override
	public InternalResponse insertOperationalData(String userName, Integer idStatusMessage,
			List<OperationalData> operationalData)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(STATUS_MESSAGE_ID, idStatusMessage);
		paramMap.put(CREATE_USER, userName);

		for (OperationalData opData : operationalData)
		{
			paramMap.put(VALUE, opData.getValue());
			paramMap.put(OPERATIONAL_DATA_TYPE_ID, opData.getOperationalDataType().getValue());

			doInsert(getSqlSession(),
					INSERT_OPERATIONAL_DATA, paramMap, response);

			if (response.isInError())
			{
				break;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightState(com.sensus.mlc.smartpoint.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResponse updateLightState(LightRequest request)
	{
		Light light = request.getFirstLight();
		HashMap<String, Object> paramMapLightState = new HashMap<String, Object>(PARAMSIZE4);
		paramMapLightState.put(MODIFY_USER, request.getUserContext().getUserId());
		paramMapLightState.put(LIGHT_STATE, light.getLightStateEnumValue());
		paramMapLightState.put(BLINK_LEVEL, light.getLightBlinkEnumValue());
		paramMapLightState.put(OVERRIDE, light.getOverrideEnumValue());
		paramMapLightState.put(OVERRIDE_PER_DATE, light.getOverridePerDate());
		paramMapLightState.put(OVERRIDE_CREATE_DATE, light.getOverrideCreateDate());

		// This needs to be translated between RniId to LightId - See stored procedure
		paramMapLightState.put(RNI_LIGHT_ID, light.getRniId());
		paramMapLightState.put(INTENSITY, light.getLightIntensityEnum().getValue());

		InternalResponse result = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_STATE, paramMapLightState, result);
		return result;
	}

	/**
	 * Insert status message and returns the Id.
	 *
	 * @param statusMessage the status message
	 * @param userName the user id
	 * @param tenantId the tenant id
	 * @param lightId the light id
	 * @param simpleNotification the simple notification
	 * @return the integer
	 */
	@Override
	public Integer insertStatusMessage(StatusMessage statusMessage, String userName, Integer tenantId, Integer lightId,
			Boolean simpleNotification)
	{
		HashMap<String, Object> paramMapStatusMessage = new HashMap<String, Object>(PARAMSIZE7);

		paramMapStatusMessage.put(MESSAGE_DATE, statusMessage.getDate());
		paramMapStatusMessage.put(MESSAGE_TYPE, statusMessage.getStatusMessageCategoryEnumValue());
		paramMapStatusMessage.put(STATUS_ID, statusMessage.getLightStatusEnumValue());
		paramMapStatusMessage.put(LIGHT_ID, lightId);
		paramMapStatusMessage.put(TENANT_ID, tenantId);
		paramMapStatusMessage.put(CREATE_USER, userName);
		paramMapStatusMessage.put(SIMPLE_NOTIFICATION, simpleNotification);

		// Adds the message
		Integer idStatusMessage =
				(Integer)doQueryForObject(getSqlSession(),
						INSERT_STATUS_MESSAGE, paramMapStatusMessage);

		return idStatusMessage;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#insertStatusMessageStatusSubtype(java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public void insertStatusMessageStatusSubtype(Integer idStatusMessage,
			Integer statusExceptionTypeEnumValue,
			String userName, Boolean updateAnalytics)
	{

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);

		paramMap.put(STATUS_MESSAGE_ID, idStatusMessage);
		paramMap.put(STATUS_SUBTYPE_ID, statusExceptionTypeEnumValue);
		paramMap.put(CREATE_USER, userName);

		// Adds the StatusSubtype
		doQueryForObject(getSqlSession(), INSERT_STATUS_MESSAGE_SUBTYPE, paramMap);

		if (updateAnalytics)
		{
			updateAnalyticsAlarmWarning(statusExceptionTypeEnumValue, idStatusMessage, userName, OPERATOR_PLUS);
		}
	}

	/**
	 * Update analytics alarm warning.
	 *
	 * @param statusExceptionTypeEnumValue the status exception type enum value
	 * @param idStatusMessage the id status message
	 * @param userId the user id
	 * @param operator the operator
	 * @return the internal results response
	 */
	@Override
	public InternalResponse updateAnalyticsAlarmWarning(Integer statusExceptionTypeEnumValue, Integer idStatusMessage,
			String userId, String operator)
	{

		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(STATUS_MESSAGE_ID, idStatusMessage);
		paramMap.put(STATUS_SUBTYPE_ID, statusExceptionTypeEnumValue);
		paramMap.put(CREATE_USER, userId);

		if (StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.HIGH_CURRENT.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.LOW_CURRENT.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.REVERSE_ENERGY.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.METROLOGY_RESET.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue().equals(statusExceptionTypeEnumValue))
		{
			paramMap.put(STATUS_TYPE, TYPE_WARNING);
		}

		if (StatusExceptionTypeEnum.LAMP_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.POWER_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.BOARD_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.METROLOGY_ERROR.getValue().equals(statusExceptionTypeEnumValue))
		{
			paramMap.put(STATUS_TYPE, TYPE_ALARM);
		}

		paramMap.put(OPERATOR, operator);

		doUpdate(getSqlSession(),
				UPDATE_ANALYTICS_ALARMS_WARNINGS, paramMap, response);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateAnalyticsAlarmWarning(java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public InternalResponse updateAnalyticsAlarmWarning(Integer tenantId, Integer lightId,
			Integer statusExceptionTypeEnumValue, String userName, String operator)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(LIGHT_ID, lightId);
		paramMap.put(TENANT_ID, tenantId);
		paramMap.put(STATUS_SUBTYPE_ID, statusExceptionTypeEnumValue);
		paramMap.put(CREATE_USER, userName);
		paramMap.put(OPERATOR, operator);

		if (StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.HIGH_CURRENT.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.LOW_CURRENT.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.REVERSE_ENERGY.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.METROLOGY_RESET.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue().equals(statusExceptionTypeEnumValue))
		{
			paramMap.put(STATUS_TYPE, TYPE_WARNING);
		}

		if (StatusExceptionTypeEnum.LAMP_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.POWER_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.BOARD_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue().equals(statusExceptionTypeEnumValue)
				|| StatusExceptionTypeEnum.METROLOGY_ERROR.getValue().equals(statusExceptionTypeEnumValue))
		{
			paramMap.put(STATUS_TYPE, TYPE_ALARM);
		}

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(),
				UPDATE_ANALYTICS_ALARMS_WARNINGS_BY_LIGHT, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#deleteLightFromAllTagScheduleGroup(java.lang.Integer)
	 */
	@Override
	public InternalResponse deleteLightFromAllTagScheduleGroup(Integer rniId)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);

		paramMap.put(RNI_LIGHT_ID, rniId);

		doRemove(getSqlSession(), DELETE_LIGHT_FROM_ALL_TAG_SCHEDULE_GROUP,
				paramMap, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dao.ISmartPointDAO#canInsertCustomSearch(com.sensus.mlc.smartpoint.model.CustomSearch,
	 * java.util.List)
	 */
	@Override
	public Boolean fetchCanInsertCustomSearch(Tenant tenant, CustomSearch customSearch, Integer userId,
			List<MessageInfo> messageInfoList, List<Integer> allowedGroupIdList)
	{
		if (isCustomSearchNameUnique(tenant, customSearch, userId, allowedGroupIdList))
		{
			return true;
		}

		messageInfoList.add(new MessageInfo(SENSUS_MLC_CUSTOMSEARCHVALIDATOR_CUSTOMSEARCH_ALREADY_EXISTS,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation));

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#generateFileCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		// This method doesn't return an internal response, so I need to create one here and add
		// the messages to the InquiryLightResponse object.
		InternalResponse internalReponse = new InternalResponse();

		if (!CSVFileGenerator.create(inquiryLightRequest.getFileName(),
				new LightMapCSVDataSource(inquiryLightRequest), internalReponse))
		{
			response.setOperationSuccess(internalReponse.getStatus());
			response.addMessages(internalReponse.getMessageInfoList());
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#generateSummaryFileCSV(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InquiryLightResponse generateSummaryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		// This method doesn't return an internal response, so I need to create one here and add
		// the messages to the InquiryLightResponse object.
		InternalResponse internalReponse = new InternalResponse();

		if (!CSVFileGenerator.create(inquiryLightRequest.getFileName(),
				new LightCSVDataSource(inquiryLightRequest), internalReponse))
		{
			response.setOperationSuccess(false);
			response.addMessages(internalReponse.getMessageInfoList());
		}
		else
		{
			response.setFileName(inquiryLightRequest.getFileName());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#generateLightHistoryFileCSV(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		InternalResponse response = new InternalResponse();
		List<LightHistory> lightHistoryList = fetchLightHistory(inquiryLightRequest).getResultsList();
		CSVFileGenerator.create(inquiryLightRequest.getFileName(),
				new LightHistoryCSVDataSource(inquiryLightRequest, lightHistoryList), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchAllLightsByProcess(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllLightsByProcess(ProcessRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(TENANT_ID, request.getTenant().getId());
		paramMap.put(PROCESS_ID, request.getProcess().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, request.getAllowedGroupIdList());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		if (ValidationUtil.isNullOrEmpty(request.getProcess().getProcessItems()))
		{
			doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS_BY_PROCESS, paramMap, response);
			return response;
		}

		for (ProcessItem processItem : request.getProcess().getProcessItems())
		{
			if (!ValidationUtil.isNull(processItem.getProcessResult())
					&& (processItem.getProcessResult() != ProcessItemStatusEnum.MLCFAILURE))
			{
				continue;
			}

			paramMap.put(FAILURE_ID, 0);
			if (!ValidationUtil.isNull(processItem.getProcessReason()))
			{
				paramMap.put(FAILURE_ID, processItem.getProcessReason().getValue());
				break;
			}
		}

		doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS_BY_PROCESS, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchLigthingConfigurationsByPartNumber(com.sensus.mlc.smartpoint
	 * .model.request.LightingConfigurationRequest)
	 */
	@Override
	public InternalResultsResponse<SensusPartNumberConfiguration> fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest request)
	{
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				new InternalResultsResponse<SensusPartNumberConfiguration>();

		doQueryForList(getSqlSession(), FETCH_LIGTHING_CONFIGURATIONS_BY_PART_NUMBER, request
				.getLight().getId(),
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchLightIntensityPercentageByLight(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<SensusPartNumberConfiguration> fetchLightIntensityPercentageByLight(Integer lightId)
	{
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				new InternalResultsResponse<SensusPartNumberConfiguration>();

		doQueryForList(getSqlSession(), FETCH_LIGHT_INTENSITY_PERCENTAGE_BY_LIGHT, lightId, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightHistory(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<LightHistory> fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE17);
		paramMap.put(LIGHT_ID, inquiryLightRequest.getLights().get(0).getId());
		paramMap.put(TENANT_ID, inquiryLightRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryLightRequest.getPageSize());
		paramMap.put(START_ROW, inquiryLightRequest.getStartRow());
		paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryLightRequest.getAllowedGroupIdList());

		InternalResultsResponse<LightHistory> response =
				new InternalResultsResponse<LightHistory>();

		ProcessFilter processFilter = inquiryLightRequest.getProcessFilter();
		if (!ValidationUtil.isNull(processFilter))
		{
			paramMap.put(END_DATE, processFilter.getEndDate());
			paramMap.put(START_DATE, processFilter.getStartDate());
			paramMap.put(PROP_NAME, null);

			if (!ValidationUtil.isNullOrEmpty(processFilter.getActionCategoryList()))
			{
				List<Integer> actionCategoryList = new ArrayList<Integer>();
				for (LCActionCategoryEnum actionCategoryEnum : processFilter
						.getActionCategoryList())
				{
					actionCategoryList.add(actionCategoryEnum.getValue());
				}
				paramMap.put("ACTION_CATEGORY_LIST", actionCategoryList);
			}

			if (!ValidationUtil.isNull(processFilter.getLightTextSearch()))
			{
				if (processFilter.getLightTextSearch().getLightProperty() == LightPropertyForSearchEnum.EVENT_ID)
				{
					paramMap.put(EVENT_ID, processFilter.getLightTextSearch().getSearchText());
				}
				if (processFilter.getLightTextSearch().getLightProperty() == LightPropertyForSearchEnum.ACTION_ID)
				{
					paramMap.put(ACTION_ID, processFilter.getLightTextSearch()
							.getSearchText());
				}
				else
				{
					paramMap.put(SEARCH_TEXT, StringUtils.trim(processFilter.getLightTextSearch().getSearchText()));
				}
			}
			if (!ValidationUtil.isNullOrEmpty(processFilter.getUserIds()))
			{
				paramMap.put(USER_IDS,
						getIdsToString(processFilter.getUserIds(),
								DELIMITER_WITH_QUOTE,
								SIMPLE_QUOTE,
								SIMPLE_QUOTE));
			}
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryLightRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryLightRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryLightRequest.getMlcSortExpression()))
		{
			if (!ValidationUtil.isNullOrEmpty(inquiryLightRequest.getSortExpression()))
			{
				paramMap.put(ORDER_BY, inquiryLightRequest.getSortExpression());
				if (ValidationUtil.isNull(inquiryLightRequest.getMlcSortExpression().isProperty()))
				{
					paramMap.put(ORDER_BY, inquiryLightRequest.getMlcSortExpression().getDirectionValue());
					paramMap.put(PROP_NAME, inquiryLightRequest.getMlcSortExpression().getField());
				}
			}
		}

		/* Since Action Description is stored as a number, this will allow order by alphabetically by description */
		String orderBy = String.valueOf(paramMap.get(ORDER_BY));
		if ((ProcessOrderByEnum.ACTION_COLUMN.getValue() + " DESC").equals(orderBy))
		{
			paramMap.put(ORDER_BY, ALPHA_COLUMN_DESC);
		}
		else if (ProcessOrderByEnum.ACTION_COLUMN.getValue().equals(orderBy))
		{
			paramMap.put(ORDER_BY, ALPHA_COLUMN);
		}

		doQueryForList(getSqlSession(), FETCH_LIGHT_HISTORY, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				FETCH_LIGHT_HISTORY_PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightHistoryHeader(com.sensus.mlc.smartpoint.model.request.
	 * LightRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(
			LightRequest lightRequest)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		doQueryForList(getSqlSession(), FETCH_LIGHT_HISTORY_HEADER, lightRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dao.ISmartPointDAO#fetchAllLights(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, String>> fetchAllLightsToCSV(InquiryLightRequest request)
	{
		InternalResultsResponse<HashMap<String, String>> response =
				new InternalResultsResponse<HashMap<String, String>>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE17);
		paramMap.clear();
		paramMap.put(TENANT_ID, request.getTenant().getId());
		paramMap.put(CURRENT_LIGHT_STATUS, PropertyEnum.CURRENT_LIGHT_STATUS.getValue());
		paramMap.put(USER_ID, request.getUserContext().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, request.getAllowedGroupIdList());

		if (!ValidationUtil.isNull(request.getSearch()))
		{
			paramMap.put(TAG_IDS, getIdsByProperty(request.getSearch(),
					PropertyEnum.TAG_ID));

			paramMap.put(GROUP_IDS, getIdsByProperty(request.getSearch(),
					PropertyEnum.GROUP_ID));

			paramMap.put(SCHEDULE_IDS, getIdsByProperty(
					getScheduleIdFromEvenOffsetSchedule(request.getSearch()),
					PropertyEnum.SCHEDULE_ID));

			paramMap.put(POLE_ID, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.POLE_ID), DELIMITER_WITH_QUOTE));

			paramMap.put(STREET_NAME, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.STREET_NAME), DELIMITER_WITH_QUOTE));

			paramMap.put(CITY_NAME, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.CITY_NAME), DELIMITER_WITH_QUOTE));

			paramMap.put(ZIP_CODE, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.ZIP_CODE), DELIMITER_WITH_QUOTE));

			paramMap.put(RNI_ID, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.FLEXNET_ID), DELIMITER_WITH_QUOTE));

			paramMap.put(PROTECT, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.PROTECTED), DELIMITER_WITH_QUOTE));

			paramMap.put(HOUSING, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.HOUSING), DELIMITER_WITH_QUOTE));

			paramMap.put(DIMMABLE, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.DIMMABLE), DELIMITER_WITH_QUOTE));

			paramMap.put(LAMP_TYPE, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.LAMP_TYPE), DELIMITER_WITH_QUOTE));

			paramMap.put(WATTAGE, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.WATTAGE_RATING), DELIMITER_WITH_QUOTE));

			paramMap.put(BULB_NUMBER, getIdsToString(getStringIdsFromSearchLight(request.getSearch(),
					PropertyEnum.BULB_SERIAL_NUMBER), DELIMITER_WITH_QUOTE));

			paramMap.put(STATUS_LIST, getStatusListString(request.getSearch()));
			paramMap.put(OVERRIDE, getStatusOverrideListString(request.getSearch()));
			paramMap.put(PROPERTY_NAMES, getPropertiesKeyString(request.getSearch()));
			paramMap.put(PROPERTY_VALUES, getPropertiesValuesString(request.getSearch()));

		}

		setSelectedOrUnselectedIds(request, paramMap);
		setOrderByAndPropName(request, paramMap);
		// setPageSize(request, paramMap);

		Integer totalRows = null;
		if (hasFilter(request.getSearch()))
		{
			totalRows = (Integer)doQueryForObject(getSqlSession(),
					PAGINATION_TOTAL_ROWS, paramMap);
		}
		else
		{
			totalRows = (Integer)doQueryForObject(getSqlSession(),
					COUNT_LIGHTS, request);

		}
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		paramMap.put(START_ROW, request.getStartRow());

		doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS_TO_CSV, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#resetMinMaxValue(com.sensus.mlc.smartpoint.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResponse resetMinMaxValue(LightRequest lightRequest)
	{
		InquiryLightRequest inquiryLightRequest = LCHelp.createInquiryLightRequest(lightRequest);
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(inquiryLightRequest);
		paramMap.put(MODIFY_USER, lightRequest.getUserContext().getUserId());

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), RESET_MIN_MAX, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightsByTenant(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Light> fetchLightsByTenant(Integer tenantID)
	{
		return doQueryForList(getSqlSession(), FETCH_LIGHTS_BY_TENANT, tenantID);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLastStatusMessageDateFromLight(java.lang.Integer)
	 */
	@Override
	public Date fetchLastStatusMessageDateFromLight(Integer lightID)
	{
		return (Date)doQueryForObject(getSqlSession(), FETCH_LAST_STATUS_MESSAGE_DATE_FROM_LIGHT,
				lightID);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLastStatusMessageCalendarFromLight(java.lang.Integer)
	 */
	@Override
	public Calendar fetchLastStatusMessageCalendarFromLight(Integer lightID)
	{
		final Date lastStatusMessageDateFromLight = fetchLastStatusMessageDateFromLight(lightID);

		if (lastStatusMessageDateFromLight == null)
		{
			return null;
		}

		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastStatusMessageDateFromLight);

		return calendar;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLastStatusSubtypeIDFromLight(java.lang.Integer)
	 */
	@Override
	public Integer fetchLastStatusSubtypeIDFromLight(Integer lightID)
	{
		return (Integer)doQueryForObject(getSqlSession(), FETCH_LAST_STATUS_SUBTYPE_ID_FROM_LIGHT,
				lightID);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertCommunicationFailureWarnings(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public void insertCommunicationFailureWarnings(LightRequest lightRequest)
	{
		doQueryForObject(
				getSqlSession(), UPDATE_LIGHT_PROPERTIES_FOR_COMMUNICATION_FAILURE, lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertColumnsToCustomSearch(com.sensus.mlc.smartpoint.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(getSqlSession(), INSERT_COLUMNS_TO_CUSTOM_SEARCH, columnFilterRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertFiltersToCustomSearch(com.sensus.mlc.smartpoint.model.request
	 * .ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(getSqlSession(), INSERT_FILTER_TO_CUSTOM_SEARCH, columnFilterRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchAllColumnFilters(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResultsResponse<ColumnFilterResponse> response = new InternalResultsResponse<ColumnFilterResponse>();
		doQueryForList(getSqlSession(), FETCH_ALL_COLUMN_FILTERS, columnFilterRequest,
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCurrentAlarmStatusMessagesByLight(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CurrentAlarmWarningMessage> fetchCurrentAlarmStatusMessagesByLight(Integer lightID)
	{
		return doQueryForList(getSqlSession(), FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT, lightID);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertCurrentAlarmStatusMessage(com.sensus.mlc.smartpoint.model.
	 * CurrentAlarmWarningMessage)
	 */
	@Override
	public void insertCurrentAlarmStatusMessage(CurrentAlarmWarningMessage currentAlarmWarningMessage)
	{
		doInsert(getSqlSession(), INSERT_CURRENT_ALARM_STATUS_MESSAGE, currentAlarmWarningMessage, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#deleteCurrentAlarmWarningMessageByLightsIDs(java.lang.Integer)
	 */
	@Override
	public void deleteCurrentAlarmWarningMessageByLightID(Integer lightID)
	{
		doQueryForObject(getSqlSession(), DELETE_CURRENT_ALARM_WARNING_MESSAGES_BY_LIGHT_ID, lightID);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCurrentLightMessagesByTenant(com.sensus.mlc.tenant.model.request
	 * .TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchCurrentLightMessagesByTenant(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_CURRENT_LIGHT_MESSAGE_BY_TENANT, tenantRequest, response);
		return response;
	}

	// -------------------------------------------------------------------------
	// Private interface:
	// -------------------------------------------------------------------------

	/**
	 * Insert custom search parameters.
	 *
	 * @param userName the user name
	 * @param customSearch the custom search
	 * @return the list
	 */
	private InternalResultsResponse<CustomSearch> insertCustomSearchParameters(String userName,
			CustomSearch customSearch)
	{
		InternalResultsResponse<CustomSearch> response = new InternalResultsResponse<CustomSearch>();
		List<SearchParameter> searchParameters = customSearch.getSearchParameters();

		if (ValidationUtil.isNullOrEmpty(searchParameters))
		{
			response.addResult(customSearch);
			return response;
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(CUSTOM_SEARCH_ID, customSearch.getId());
		paramMap.put(CREATE_USER, userName);
		paramMap.put(OPERATOR_ID, null);

		for (SearchParameter param : customSearch.getSearchParameters())
		{
			if (!ValidationUtil.isNull(param.getValue()))
			{
				paramMap.put(PROPERTY_ID, param.getPropertyEnum().getValue());
				paramMap.put(PROPERTY_VALUE, param.getValue());

				Integer customSearchParameterId =
						(Integer)doInsert(getSqlSession(), INSERT_CUSTOM_SEARCH_PROPERTY, paramMap,
								response);
				if (ValidationUtil.isNullOrZero(customSearchParameterId))
				{
					return null;
				}

				param.setId(customSearchParameterId);
			}
		}

		if (ValidationUtil.isNullOrEmpty(searchParameters))
		{
			LOG.error("Error inserting custom search parameters.");
			response.setStatus(Status.PersistenceError);
			return response;
		}

		customSearch.setSearchParameters(searchParameters);
		response.addResult(customSearch);
		return response;
	}

	/**
	 * Checks if is custom search name unique.
	 *
	 * @param tenant the tenant
	 * @param customSearch the custom search
	 * @param userId the create user
	 * @param allowedGroupIdList the allowed group id list
	 * @return the boolean
	 */
	private Boolean isCustomSearchNameUnique(Tenant tenant, CustomSearch customSearch, Integer userId,
			List<Integer> allowedGroupIdList)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(TENANT_ID, tenant.getId());
		paramMap.put(CUSTOM_SEARCH_NAME, customSearch.getName());
		paramMap.put(USER_ID, userId);
		paramMap.put(ALLOWED_GROUP_ID_LIST, allowedGroupIdList);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				COUNT_CUSTOM_SEARCH_BY_NAME, paramMap);

		return ValidationUtil.isNullOrZero(totalRows);

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchSmartpointsToMap(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeSmartpointInfo> fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(inquiryLightRequest);

		InternalResultsResponse<GeocodeSmartpointInfo> response = new InternalResultsResponse<GeocodeSmartpointInfo>();
		doQueryForList(getSqlSession(), FETCH_ALL_SMARTPOINTS_TO_MAP, paramMap, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchCountSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchCountSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(inquiryLightRequest);

		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), FETCH_COUNT_SMARTPOINTS_TO_MAP, paramMap, response);

		if (!response.hasResults())
		{
			response.addResult(0);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLimitedSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public InternalResultsResponse<Light> fetchLimitedSmartpointsToMap(
			InquiryLightRequest inquiryLightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(inquiryLightRequest);

		response.getResultsList().addAll(
				doQueryForList(getSqlSession(), FETCH_LIMITED_SMARTPOINTS_TO_MAP, paramMap));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchMiddleMap(com.sensus.mlc.smartpoint.model.request.
	 * InquiryLightRequest)
	 */
	@Override
	public InternalResultsResponse<SmartpointMiddleMap> fetchMiddleMap(InquiryLightRequest inquiryLightRequest)
	{
		InternalResultsResponse<SmartpointMiddleMap> response = new InternalResultsResponse<SmartpointMiddleMap>();
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(inquiryLightRequest);

		response.getResultsList().add(
				(SmartpointMiddleMap)doQueryForObject(getSqlSession(), FETCH_MIDDLE_MAP, paramMap));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchMiddleSmartpointsToMap(com.sensus.mlc.smartpoint.model.request
	 * .InquiryLightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> fetchMiddleSmartpointsToMap(
			InquiryLightRequest inquiryLightRequest)
			{
		HashMap<String, Object> paramMap = SmartPointDACD.getParametersToFetchAllLights(inquiryLightRequest);
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		response.getResultsList().addAll(
				doQueryForList(getSqlSession(), FETCH_MIDDLE_SMARTPOINT_TO_MAP, paramMap));

		return response;
			}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateCurrentStatusMessage(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public InternalResponse updateCurrentStatusMessage(LightRequest lightRequest)
	{
		HashMap<String, Object> paramMapLightState = new HashMap<String, Object>(PARAMSIZE2);
		paramMapLightState.put(CURRENT_STATUS_MESSAGE_ID, lightRequest.getFirstLight().getCurrentStatusMessage()
				.getId());
		paramMapLightState.put(LIGHT_ID, lightRequest.getFirstLight().getId());

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_CURRENTE_STATUS_MESSAGE, paramMapLightState, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightSchedule(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightSchedule(GuaranteeLightExistenceRequest lightRequest)
	{
		doQueryForObject(getSqlSession(), INSERT_LIGHT_SCHEDULE, lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightLastOperationalData(com.sensus.mlc.smartpoint.model.request
	 * .GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightLastOperationalData(GuaranteeLightExistenceRequest lightRequest)
	{
		doQueryForObject(getSqlSession(), INSERT_LIGHT_LAST_OPERATION_DATA, lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#insertLightLocation(com.sensus.mlc.smartpoint.model.request.
	 * GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightLocation(GuaranteeLightExistenceRequest lightRequest)
	{
		doQueryForObject(getSqlSession(), INSERT_LIGHT_LOCATION, lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchStatusMessageStatusSubtype(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public Integer fetchStatusMessageStatusSubtype(Integer idStatusMessage,
			Integer statusExceptionTypeEnumValue, String userId)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);


		paramMap.put(STATUS_MESSAGE_ID, idStatusMessage);
		paramMap.put(STATUS_SUBTYPE_ID, statusExceptionTypeEnumValue);
		paramMap.put(CREATE_USER, userId);

		// Adds the StatusSubtype
		return (Integer)doQueryForObject(getSqlSession(), FETCH_COUNT_STATUS_MESSAGE_SUBTYPE, paramMap);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLight(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();

		doUpdate(getSqlSession(), UPDATE_LIGHT, lightRequest.getFirstLight(), response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightSchedule(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightSchedule(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_SCHEDULE, lightRequest.getFirstLight(), response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightLastOperationData(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLastOperationData(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_LAST_OPERATION_DATA, lightRequest.getFirstLight(), response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightLocation(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLocation(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_LOCATION, lightRequest.getFirstLight(), response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#updateLightConfiguration(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightConfiguration(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_LIGHT_CONFIGURATION, lightRequest.getFirstLight(), response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightScheduleById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightScheduleById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_SCHEDULE_BY_ID, lightRequest, response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightLastOperationDataById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLastOperationDataById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_LAST_OPERATION_BY_ID, lightRequest, response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightLocationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightLocationById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_LOCATION_BY_ID, lightRequest, response);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.dac.ISmartPointDAC#fetchLightConfigurationById(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightConfigurationById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_CONFIGURATION_BY_ID, lightRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.dac.ISmartPointDAC#checkIfValidBindingMessage(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public Boolean checkIfValidBindingMessage(LightRequest lightRequest)
	{
		Boolean response = (Boolean)doQueryForObject(getSqlSession(), CHECK_IF_VALID_BINDING, lightRequest);

		return response;
	}
}
