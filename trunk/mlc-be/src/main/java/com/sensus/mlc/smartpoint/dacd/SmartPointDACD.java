/*
 *
 */
package com.sensus.mlc.smartpoint.dacd;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.util.LCConvertUtil.getIdsToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;

import com.sensus.mlc.ecomode.model.EcoModeFilterEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;

public final class SmartPointDACD
{
	/** The Constant GEO_CODE_TRUNC. */
	private static final String GEO_CODE_TRUNC = "geoCodeTrunc";

	/** The Constant MAP_CONDITION. */
	private static final String MAP_CONDITION = "map_condition";

	/** The Constant DELIMITER. */
	public static final String DELIMITER = ",";

	/** The Constant SELECTED_IDS. */
	public static final String SELECTED_IDS = "selectedIds";

	/** The Constant UNSELECTED_IDS. */
	public static final String UNSELECTED_IDS = "unSelectedIds";

	/** The Constant CURRENT_LIGHT_STATUS_SUBTYPE. */
	public static final String CURRENT_LIGHT_STATUS_SUBTYPE = "status_subType";

	/** The Constant LAST_MESSAGES. */
	public static final String LAST_MESSAGES = "last_messages";

	/** The Constant VOLTAGE_GREATER. */
	public static final String VOLTAGE_GREATER = "voltage_greater";

	/** The Constant VOLTAGE_LESS. */
	public static final String VOLTAGE_LESS = "voltage_less";

	/** The Constant CURRENT_GREATER. */
	public static final String CURRENT_GREATER = "current_greater";

	/** The Constant CURRENT_LESS. */
	public static final String CURRENT_LESS = "current_less";

	/** The Constant CONSUMPTION_GREATER. */
	public static final String CONSUMPTION_GREATER = "consumption_greater";

	/** The Constant CONSUMPTION_LESS. */
	public static final String CONSUMPTION_LESS = "consumption_less";

	/** The Constant SERIAL_NUMBER. */
	public static final String SERIAL_NUMBER = "serial_number";

	/** The Constant LIGHT_TYPE. */
	public static final String LIGHT_TYPE = "light_type";

	/** The Constant INPUT_VOLTAGE_RANGE. */
	public static final String INPUT_VOLTAGE_RANGE = "input_voltage";

	/** The Constant COLOR_TEMPERATURE. */
	public static final String COLOR_TEMPERATURE = "color_temperature";

	/** The Constant FIRMWARE_VERSION. */
	public static final String FIRMWARE_VERSION = "firmware_version";

	/** The Constant DATE_ADDED_BEFORE. */
	public static final String DATE_ADDED_BEFORE = "date_added_before";

	/** The Constant DATE_ADDED_AFTER. */
	public static final String DATE_ADDED_AFTER = "date_added_after";

	/** The Constant MODEL_NUMBER. */
	public static final String MODEL_NUMBER = "part_number";

	/** The Constant ALL_GROUPS. */
	public static final String ALL_GROUPS = "all_groups";

	/** The Constant ALL_TAGS. */
	public static final String ALL_TAGS = "all_tags";

	/** The Constant ALL_EVENTS. */
	public static final String ALL_EVENTS = "all_events";

	/** The Constant ALL_OFFSETS. */
	public static final String ALL_OFFSETS = "all_offsets";

	/** The Constant ALL_ALARMS_WARNINGS. */
	public static final String ALL_ALARMS_WARNINGS = "all_alarms_warnings";

	/** The Constant OPERATIONAL_DATA_TYPE_ID. */
	public static final String OPERATIONAL_DATA_TYPE_ID = "operational_data_type_id";

	/** The Constant VALUE. */
	public static final String VALUE = "value";

	/** The Constant RNI_ID. */
	public static final String RNI_ID = "rni_id";

	/** The Constant LIGHT_ID. */
	public static final String LIGHT_ID = "light_id";

	/** The Constant MESSAGE_TYPE_ID. */
	public static final String MESSAGE_TYPE_ID = "message_type_id";

	/** The Constant LAT. */
	public static final String LAT = "lat";

	/** The Constant LNG. */
	public static final String LNG = "lng";

	/** The Constant MESSAGE_DATE. */
	public static final String MESSAGE_DATE = "message_date";

	/** The Constant MESSAGE_TYPE. */
	public static final String MESSAGE_TYPE = "message_type";

	/** The Constant STATUS_ID. */
	public static final String STATUS_ID = "status_id";

	/** The Constant RNI_LIGHT_ID. */
	public static final String RNI_LIGHT_ID = "rni_light_id";

	/** The Constant PROPERTY_ID. */
	public static final String PROPERTY_ID = "property_id";

	/** The Constant PROPERTY_VALUE. */
	public static final String PROPERTY_VALUE = "property_value";

	/** The Constant RNI_TENANT_CODE. */
	public static final String RNI_TENANT_CODE = "rni_tenant_code";

	/** The Constant MODIFY_USER. */
	public static final String MODIFY_USER = "modify_user";

	/** The Constant STATUS_MESSAGE_ID. */
	public static final String STATUS_MESSAGE_ID = "status_message_id";

	/** The Constant STATUS_SUBTYPE_ID. */
	public static final String STATUS_SUBTYPE_ID = "status_subtype_id";

	/** The Constant LIGHT_STATE. */
	public static final String LIGHT_STATE = "light_state";

	/** The Constant ORDER_BY. */
	public static final String ORDER_BY = "orderBy";

	/** The Constant PROP_NAME. */
	public static final String PROP_NAME = "propName";

	/** The Constant PROTECTED. */
	public static final String PROTECTED = "protected";

	/** The Constant CUSTOM_SEARCH_ID. */
	public static final String CUSTOM_SEARCH_ID = "custom_search_id";

	/** The Constant CUSTOM_SEARCH_NAME. */
	public static final String CUSTOM_SEARCH_NAME = "custom_search_name";

	/** The Constant CUSTOM_SEARCH_DESCRIPTION. */
	public static final String CUSTOM_SEARCH_DESCRIPTION = "custom_search_description";

	/** The Constant OPERATOR_ID. */
	public static final String OPERATOR_ID = "operator_id";

	/** The Constant TENANT_ID. */
	public static final String TENANT_ID = "tenant_id";

	/** The Constant PROCESS_ID. */
	public static final String PROCESS_ID = "process_id";

	/** The Constant FAILURE_ID. */
	public static final String FAILURE_ID = "failure_id";

	/** The Constant USER_ID. */
	public static final String USER_ID = "user_id";

	/** The Constant TAG_IDS. */
	public static final String TAG_IDS = "tagIds";

	/** The Constant GROUP_IDS. */
	public static final String GROUP_IDS = "groupIds";

	/** The Constant SCHEDULE_IDS. */
	public static final String SCHEDULE_IDS = "scheduleIds";

	/** The Constant POLE_ID. */
	public static final String POLE_ID = "poleId";

	/** The Constant STREET_NAME. */
	public static final String STREET_NAME = "streetName";

	/** The Constant CITY_NAME. */
	public static final String CITY_NAME = "cityName";

	/** The Constant ZIP_CODE. */
	public static final String ZIP_CODE = "zipCode";

	/** The Constant PROTECT. */
	public static final String PROTECT = "protect";

	/** The Constant HOUSING. */
	public static final String HOUSING = "housing";

	/** The Constant DIMMABLE. */
	public static final String DIMMABLE = "dimmable";

	/** The Constant LAMP_TYPE. */
	public static final String LAMP_TYPE = "lampType";

	/** The Constant WATTAGE. */
	public static final String WATTAGE = "wattage";

	public static final String HOUSING_COLOR = "housing_color";

	/** The Constant STATUS_LIST. */
	public static final String STATUS_LIST = "statusList";

	/** The Constant OVERRIDE. */
	public static final String OVERRIDE = "override";

	/** The Constant PROPERTY_NAMES. */
	public static final String PROPERTY_NAMES = "propertyNames";

	/** The Constant PROPERTY_VALUES. */
	public static final String PROPERTY_VALUES = "propertyValues";

	/** The Constant PAGE_SIZE. */
	public static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	public static final String START_ROW = "startRow";

	/** The Constant SIMPLE_NOTIFICATION. */
	public static final String SIMPLE_NOTIFICATION = "simple_notification";

	/** The Constant SIMPLE_NOTIFICATION. */
	public static final String DEFAULT_LIGHT_SOURCE = "O";

	/** The Constant STATUS_TYPE. */
	public static final String STATUS_TYPE = "status_type";

	/** The Constant OPERATOR. */
	public static final String OPERATOR = "operator";

	/** The Constant TYPE_WARNING. */
	public static final String TYPE_WARNING = "W";

	/** The Constant TYPE_ALARM. */
	public static final String TYPE_ALARM = "A";

	/** The Constant OPERATOR_PLUS. */
	public static final String OPERATOR_PLUS = "+";

	/** The Constant INTENSITY. */
	public static final String INTENSITY = "intensity";

	/** The Constant EVENT_ID. */
	public static final String EVENT_ID = "eventId";

	/** The Constant SEARCH_TEXT. */
	public static final String SEARCH_TEXT = "searchText";

	/** The Constant USER_IDS. */
	public static final String USER_IDS = "userIds";

	/** The Constant START_DATE. */
	public static final String START_DATE = "startDate";

	/** The Constant END_DATE. */
	public static final String END_DATE = "endDate";

	/** The Constant CREATE_USER. */
	public static final String CREATE_USER = "create_user";

	/** The Constant ACTION_ID. */
	public static final String ACTION_ID = "actionId";

	/** The Constant ALLOWED_GROUP_ID_LIST. */
	public static final String ALLOWED_GROUP_ID_LIST = "allowedGroupIdList";

	/** The Constant DELIMITER WITH QUOTE. */
	public static final String DELIMITER_WITH_QUOTE = "','";

	/** The Constant SIMPLE_QUOTE. */
	public static final String SIMPLE_QUOTE = "'";

	/** The Constant CURRENT_LIGHT_STATUS. */
	public static final String CURRENT_LIGHT_STATUS = "currentLightStatusPropertyId";

	/** The Constant UPPER_ASSEMBLY. */
	private static final String UPPER_ASSEMBLY = "upper_assembly";

	/** The Constant LOWER_ASSEMBLY. */
	private static final String LOWER_ASSEMBLY = "lower_assembly";

	/** The Constant BULB_NUMBER. */
	public static final String BULB_NUMBER = "bulb_number";

	/** The Constant LIGHT_DRIVER_NUMBER. */
	private static final String LIGHT_DRIVER_NUMBER = "light_driver_number";

	/** The Constant BOTTOM_LEFT_LAT. */
	private static final String BOTTOM_LEFT_LAT = "bottom_left_lat";

	/** The Constant BOTTOM_LEFT_LON. */
	private static final String BOTTOM_LEFT_LON = "bottom_left_lon";

	/** The Constant TOP_RIGHT_LAT. */
	private static final String TOP_RIGHT_LAT = "top_right_lat";

	/** The Constant TOP_RIGHT_LON. */
	private static final String TOP_RIGHT_LON = "top_right_lon";

	/** The Constant MAX_DEVICE_COUNT. */
	private static final String MAX_DEVICE_COUNT = "max_device_count";

	/** The Constant MIDDLE_LON_MAX. */
	private static final String MIDDLE_LON_MAX = "middle_lon_max";

	/** The Constant MIDDLE_LON_MIN. */
	private static final String MIDDLE_LON_MIN = "middle_lon_min";

	/** The Constant MIDDLE_LAT_MAX. */
	private static final String MIDDLE_LAT_MAX = "middle_lat_max";

	/** The Constant MIDDLE_LAT_MIN. */
	private static final String MIDDLE_LAT_MIN = "middle_lat_min";

	/** The Constant ECOMODE. */
	private static final String ECOMODE = "ecomode";

	/**
	 * Instantiates a new smart point dacd.
	 */
	private SmartPointDACD()
	{

	}

	/**
	 * Sets the selected or unselected ids.
	 *
	 * @param request the request
	 * @param paramMap the param map
	 */
	public static void setSelectedOrUnselectedIds(InquiryLightRequest request, HashMap<String, Object> paramMap)
	{
		if (isNullOrEmpty(request.getSelectionPaginationIds())
				&& isNullOrEmpty(request.getUnselectionPaginationIds()))
		{
			return;
		}

		if (!isNull(request.getPaginationAllSelected()) && request.getPaginationAllSelected())
		{
			List<Integer> unselectedIds = new ArrayList<Integer>(request.getSelectionPaginationIds());
			unselectedIds.addAll(request.getUnselectionPaginationIds());
			paramMap.put(UNSELECTED_IDS, unselectedIds);
			return;
		}

		paramMap.put(SELECTED_IDS, request.getSelectionPaginationIds());
		paramMap.put(UNSELECTED_IDS, request.getUnselectionPaginationIds());
	}

	/**
	 * Sets the order by and prop name.
	 *
	 * @param request the request
	 * @param paramMap the param map
	 */
	public static void setOrderByAndPropName(InquiryLightRequest request, HashMap<String, Object> paramMap)
	{
		if (isNull(request.getSearch()) && isNull(request.getMlcSortExpression()))
		{
			return;
		}

		List<String> sorts = getStringIdsFromSearchLight(request.getSearch(), PropertyEnum.SORT);
		if (!isNullOrEmpty(sorts))
		{
			String sort = sorts.get(0);
			paramMap.put(ORDER_BY, sort);
			paramMap.put(PROP_NAME, null);

			String[] sortSplited = StringUtils.split(sort, " ");
			if (SmartPointColumnEnum.isValidValue(sort) && (sortSplited.length > 1))
			{
				paramMap.put(ORDER_BY, sortSplited[1]);
				paramMap.put(PROP_NAME, sortSplited[0]);
			}

			return;
		}

		paramMap.put(ORDER_BY, String.valueOf(request.getMlcSortExpression()));
		paramMap.put(PROP_NAME, null);

		if (!isNull(request.getMlcSortExpression()) && request.getMlcSortExpression().isProperty())
		{
			paramMap.put(ORDER_BY, request.getMlcSortExpression().getDirectionValue());
			paramMap.put(PROP_NAME, request.getMlcSortExpression().getField());
		}
	}

	/**
	 * Checks for filters.
	 *
	 * @param search the search
	 * @return true, if successful
	 */
	public static boolean hasFilter(SearchLight search)
	{
		return !(isNull(search) || (isNullOrEmpty(search.getSearchParameters()) && isNullOrEmpty(search.getStatusList())));
	}

	/**
	 * Gets the status list integer.
	 *
	 * @param search the search
	 * @return the status list as integer, comma separated
	 */
	public static List<Integer> getStatusListString(SearchLight search)
	{
		if (isNullOrEmpty(search.getStatusList()))
		{
			return null;
		}

		List<Integer> statusList = new ArrayList<Integer>();
		for (LightStatusEnum lse : search.getStatusList())
		{
			if (!lse.equals(LightStatusEnum.OVERRIDE))
			{
				statusList.add(lse.getValue());
			}
		}

		if (isNullOrEmpty(statusList))
		{
			return null;
		}

		return statusList;
	}

	public static List<String> getStatusOverrideListString(SearchLight search)
	{
		if (isNullOrEmpty(search.getStatusList()))
		{
			return null;
		}

		List<String> statusOverrideList = new ArrayList<String>();
		for (LightStatusEnum lse : search.getStatusList())
		{
			if (lse.equals(LightStatusEnum.OVERRIDE))
			{
				statusOverrideList.add(String.valueOf(lse.getValue()));
			}
		}

		if (isNullOrEmpty(statusOverrideList))
		{
			return null;
		}

		return statusOverrideList;
	}

	/**
	 * Gets the properties key string.
	 *
	 * @param search the search
	 * @return the properties key string
	 */
	public static String getPropertiesKeyString(SearchLight search)
	{
		HashMap<String, List<Integer>> hashPropertyValues = search.getPropertyValues();
		if (isNull(hashPropertyValues) || (hashPropertyValues.size() == 0))
		{
			return null;
		}

		Set<String> keys = hashPropertyValues.keySet();
		String text = getIdsToString(keys, DELIMITER_WITH_QUOTE);

		if (!isNull(text))
		{
			text = SIMPLE_QUOTE + text + SIMPLE_QUOTE;
		}

		return text;
	}

	/**
	 * Gets the properties values string.
	 *
	 * @param search the search
	 * @return the properties values string
	 */
	public static String getPropertiesValuesString(SearchLight search)
	{
		HashMap<String, List<Integer>> hashPropertyValues = search.getPropertyValues();
		if (isNull(hashPropertyValues) || (hashPropertyValues.size() == 0))
		{
			return null;
		}

		Collection<List<Integer>> values = hashPropertyValues.values();
		StringBuilder stringBuilder = new StringBuilder();

		for (List<Integer> idList : values)
		{
			stringBuilder.append(getIdsToString(idList, DELIMITER)).append(DELIMITER);
		}

		if (!isNullOrEmpty(stringBuilder.toString()))
		{
			return StringUtils.substringBeforeLast(stringBuilder.toString(), DELIMITER);
		}

		return null;
	}

	/**
	 * Gets the schedule id from even offset schedule.
	 *
	 * @param searchParam the search param
	 * @return the schedule id from even offset schedule
	 */
	public static SearchLight getScheduleIdFromEvenOffsetSchedule(SearchLight searchParam)
	{
		SearchLight searchLight = new SearchLight();
		if (isNullOrEmpty(searchParam.getSearchParameters()))
		{
			return searchLight;
		}

		for (SearchParameter param : searchParam.getSearchParameters())
		{
			if (param.getPropertyEnum().equals(PropertyEnum.EVENT_SCHEDULE)
					|| param.getPropertyEnum().equals(PropertyEnum.OFFSET_SCHEDULE))
			{
				searchLight.addSearchParameter(new SearchParameter(PropertyEnum.SCHEDULE_ID, param.getValue()));
			}
		}
		return searchLight;
	}

	/**
	 * Gets the integer ids from search light.
	 *
	 * @param search the search
	 * @param property the property
	 * @return the integer ids from search light
	 */
	public static List<Integer> getIdsByProperty(SearchLight search, PropertyEnum property)
	{
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return null;
		}

		List<Integer> ids = new ArrayList<Integer>();
		for (SearchParameter param : search.getSearchParameters())
		{
			if (param.getPropertyEnum().equals(property) && !isNull(param.getValue()))
			{
				ids.add(Integer.parseInt(param.getValue()));
			}
		}

		if (!isNullOrEmpty(ids))
		{
			return ids;
		}
		return null;
	}

	/**
	 * Gets the string ids from search light.
	 *
	 * @param search the search
	 * @param property the property
	 * @return the string ids from search light
	 */
	public static List<String> getStringIdsFromSearchLight(SearchLight search, PropertyEnum property)
	{
		if (isNull(search) || isNullOrEmpty(search.getSearchParameters()))
		{
			return null;
		}

		List<String> ids = new ArrayList<String>();
		for (SearchParameter param : search.getSearchParameters())
		{
			if (param.getPropertyEnum().equals(property) && !isNull(param.getValue()))
			{
				ids.add(StringUtils.upperCase(param.getValue()));
			}
		}
		if (!isNullOrEmpty(ids))
		{
			return ids;
		}
		return null;
	}

	/**
	 * Gets the parameters to fetch all lights.
	 *
	 * @param request the request
	 * @return the parameters to fetch all lights
	 */
	public static HashMap<String, Object> getParametersToFetchAllLights(InquiryLightRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ALLOWED_GROUP_ID_LIST, request.getAllowedGroupIdList());
		paramMap.put(TENANT_ID, request.getTenant().getId());
		paramMap.put(START_ROW, request.getStartRow());
		if (!isNull(request.getUserContext()))
		{
			paramMap.put(USER_ID, request.getUserContext().getUserId());
		}
		if (!isNull(request.getGeoCodeTrunc()))
		{
			paramMap.put(GEO_CODE_TRUNC, request.getGeoCodeTrunc());
		}

		setSmartpointMapProperties(request, paramMap);
		setSearch(request.getSearch(), paramMap);
		setSelectedOrUnselectedIds(request, paramMap);
		setOrderByAndPropName(request, paramMap);
		setPageSize(request, paramMap);

		return paramMap;
	}

	/**
	 * Sets the smartpoint map properties.
	 *
	 * @param request the request
	 * @param paramMap the param map
	 */
	private static void setSmartpointMapProperties(InquiryLightRequest request, HashMap<String, Object> paramMap)
	{

		if (!isNull(request.getBottomLeftLat()))
		{
			paramMap.put(BOTTOM_LEFT_LAT, request.getBottomLeftLat());
			paramMap.put(MAP_CONDITION, 1);
		}

		if (!isNull(request.getBottomLeftLon()))
		{
			paramMap.put(BOTTOM_LEFT_LON, request.getBottomLeftLon());
		}

		if (!isNull(request.getTopRightLat()))
		{
			paramMap.put(TOP_RIGHT_LAT, request.getTopRightLat());
		}

		if (!isNull(request.getTopRightLon()))
		{
			paramMap.put(TOP_RIGHT_LON, request.getTopRightLon());
		}

		if (!isNull(request.getMaxSmartpointCount()))
		{
			paramMap.put(MAX_DEVICE_COUNT, request.getMaxSmartpointCount());
		}

		if (!isNull(request.getSmartpointMiddleMap()))
		{
			if (!isNull(request.getSmartpointMiddleMap().getMiddleLatMin()))
			{
				paramMap.put(MIDDLE_LAT_MIN, request.getSmartpointMiddleMap().getMiddleLatMin());
				paramMap.put(MAP_CONDITION, 1);
			}

			if (!isNull(request.getSmartpointMiddleMap().getMiddleLatMax()))
			{
				paramMap.put(MIDDLE_LAT_MAX, request.getSmartpointMiddleMap().getMiddleLatMax());
			}

			if (!isNull(request.getSmartpointMiddleMap().getMiddleLonMin()))
			{
				paramMap.put(MIDDLE_LON_MIN, request.getSmartpointMiddleMap().getMiddleLonMin());
			}

			if (!isNull(request.getSmartpointMiddleMap().getMiddleLonMax()))
			{
				paramMap.put(MIDDLE_LON_MAX, request.getSmartpointMiddleMap().getMiddleLonMax());
			}

		}
	}

	/**
	 * Sets the search.
	 *
	 * @param searchLight the search light
	 * @param paramMap the param map
	 */
	private static void setSearch(SearchLight searchLight, HashMap<String, Object> paramMap)
	{
		if (isNull(searchLight))
		{
			return;
		}

		paramMap.put(TAG_IDS, getIdsByProperty(searchLight, PropertyEnum.TAG_ID));

		paramMap.put(GROUP_IDS, getIdsByProperty(searchLight, PropertyEnum.GROUP_ID));

		paramMap.put(SCHEDULE_IDS, getIdsByProperty(
				getScheduleIdFromEvenOffsetSchedule(searchLight), PropertyEnum.SCHEDULE_ID));

		paramMap.put(POLE_ID, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.POLE_ID), DELIMITER_WITH_QUOTE));

		paramMap.put(STREET_NAME, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.STREET_NAME), DELIMITER_WITH_QUOTE));

		paramMap.put(CITY_NAME, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.CITY_NAME), DELIMITER_WITH_QUOTE));

		paramMap.put(ZIP_CODE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.ZIP_CODE), DELIMITER_WITH_QUOTE));

		paramMap.put(RNI_ID, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.FLEXNET_ID), DELIMITER_WITH_QUOTE));

		paramMap.put(PROTECT, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.PROTECTED), DELIMITER_WITH_QUOTE));

		paramMap.put(HOUSING, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.HOUSING), DELIMITER_WITH_QUOTE));

		paramMap.put(HOUSING_COLOR, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.HOUSING_COLOR), DELIMITER_WITH_QUOTE));

		paramMap.put(DIMMABLE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.DIMMABLE), DELIMITER_WITH_QUOTE));

		paramMap.put(LAMP_TYPE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.LAMP_TYPE), DELIMITER_WITH_QUOTE));

		paramMap.put(WATTAGE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.WATTAGE_RATING), DELIMITER_WITH_QUOTE));

		paramMap.put(LAST_MESSAGES, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.FREQUENCY), DELIMITER_WITH_QUOTE));

		paramMap.put(VOLTAGE_GREATER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.AC_VOLTAGE_MIN), DELIMITER_WITH_QUOTE));

		paramMap.put(VOLTAGE_LESS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.AC_VOLTAGE_MAX), DELIMITER_WITH_QUOTE));

		paramMap.put(CURRENT_GREATER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.AC_CURRENT_MIN), DELIMITER_WITH_QUOTE));
		paramMap.put(CURRENT_LESS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.AC_CURRENT_MAX), DELIMITER_WITH_QUOTE));

		paramMap.put(CONSUMPTION_GREATER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.CONSUMPTION_MIN), DELIMITER_WITH_QUOTE));

		paramMap.put(CONSUMPTION_LESS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.CONSUMPTION_MAX), DELIMITER_WITH_QUOTE));

		paramMap.put(SERIAL_NUMBER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.LIGHT_DRIVER_SERIAL_NUMBER), DELIMITER_WITH_QUOTE));

		paramMap.put(LIGHT_TYPE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.LAMP_TYPE_WATTAGE_DIMMABLE), DELIMITER_WITH_QUOTE));

		paramMap.put(INPUT_VOLTAGE_RANGE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.INPUT_VOLTAGE_RANGE), DELIMITER_WITH_QUOTE));

		paramMap.put(COLOR_TEMPERATURE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.COLOR_TEMPERATURE), DELIMITER_WITH_QUOTE));

		paramMap.put(MODEL_NUMBER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.MODEL_NUMBER), DELIMITER_WITH_QUOTE));

		paramMap.put(FIRMWARE_VERSION, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.FIRMWARE_VERSION), DELIMITER_WITH_QUOTE));

		paramMap.put(UPPER_ASSEMBLY, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.UPPER_ASSEMBLY_SERIAL_NUMBER), DELIMITER_WITH_QUOTE));

		paramMap.put(LOWER_ASSEMBLY, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.LOWER_ASSEMBLY_SERIAL_NUMBER), DELIMITER_WITH_QUOTE));

		paramMap.put(BULB_NUMBER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.BULB_SERIAL_NUMBER), DELIMITER_WITH_QUOTE));

		paramMap.put(LIGHT_DRIVER_NUMBER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.LIGHT_DRIVER_SERIAL_NUMBER), DELIMITER_WITH_QUOTE));

		paramMap.put(DATE_ADDED_AFTER, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.DATE_ADDED_AFTER), DELIMITER_WITH_QUOTE));

		paramMap.put(DATE_ADDED_BEFORE, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.DATE_ADDED_BEFORE), DELIMITER_WITH_QUOTE));

		paramMap.put(CURRENT_LIGHT_STATUS_SUBTYPE,
				getIdsToString(getStringIdsFromSearchLight(searchLight,
						PropertyEnum.STATUS_SUBTYPE_ID), DELIMITER_WITH_QUOTE));

		paramMap.put(ALL_GROUPS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.ALL_GROUPS), DELIMITER_WITH_QUOTE));

		paramMap.put(ALL_TAGS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.ALL_TAGS), DELIMITER_WITH_QUOTE));

		paramMap.put(ALL_EVENTS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.ALL_EVENTS), DELIMITER_WITH_QUOTE));

		paramMap.put(ALL_OFFSETS, getIdsToString(getStringIdsFromSearchLight(searchLight,
				PropertyEnum.ALL_OFFSETS), DELIMITER_WITH_QUOTE));

		List<String> alarmWarning = new ArrayList<String>();
		if (!isNullOrEmpty(getStringIdsFromSearchLight(searchLight, PropertyEnum.ALL_WARNINGS)))
		{
			alarmWarning = getStringIdsFromSearchLight(searchLight, PropertyEnum.ALL_WARNINGS);
		}

		if (!isNullOrEmpty(getStringIdsFromSearchLight(searchLight, PropertyEnum.ALL_ALARMS)))
		{
			alarmWarning.addAll(getStringIdsFromSearchLight(searchLight, PropertyEnum.ALL_ALARMS));
		}

		paramMap.put(ALL_ALARMS_WARNINGS, getIdsToString(alarmWarning, DELIMITER_WITH_QUOTE));
		paramMap.put(STATUS_LIST, getStatusListString(searchLight));
		paramMap.put(OVERRIDE, getStatusOverrideListString(searchLight));
		setEcoMode(paramMap, searchLight);
	}

	/**
	 * Sets the page size.
	 *
	 * @param request the request
	 * @param paramMap the param map
	 */
	private static void setPageSize(InquiryLightRequest request, HashMap<String, Object> paramMap)
	{
		if (!isNull(request.getSearch())
				&& !isNull(getIdsByProperty(request.getSearch(), PropertyEnum.PAGE_SIZE)))
		{
			paramMap.put(PAGE_SIZE, getIdsByProperty(request.getSearch(), PropertyEnum.PAGE_SIZE).get(0));
			return;
		}
		paramMap.put(PAGE_SIZE, request.getPageSize());
	}

	/**
	 * Sets the eco mode.
	 *
	 * @param paramMap the param map
	 * @param searchLight the search light
	 */
	private static void setEcoMode(HashMap<String, Object> paramMap, SearchLight searchLight)
	{
		if (isNull(searchLight) || isNullOrEmpty(searchLight.getSearchParameters()))
		{
			return;
		}

		List<EcoModeFilterEnum> lstEcoModeFilter = new ArrayList<EcoModeFilterEnum>();
		for (SearchParameter searchParameter : searchLight.getSearchParameters())
		{
			if (searchParameter.getPropertyEnum() == PropertyEnum.ECOMODE)
			{
				String ecoModeValue = searchParameter.getValue();
				lstEcoModeFilter.add(EcoModeFilterEnum.enumForValue(ecoModeValue));
			}
		}

		if (isNullOrEmpty(lstEcoModeFilter))
		{
			return;
		}

		paramMap.put(ECOMODE, lstEcoModeFilter);
	}
}
