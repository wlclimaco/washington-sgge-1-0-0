package com.sensus.mlc.wui.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;

/**
 * Utility that set searchLight of array coming from javascript.
 * 
 * @author Cristiane cobo
 * 
 */

public final class SearchParametersUtil
{

	/** The Constant TOKEN. */
	private static final String PIPE = "[|]";

	/** The Constant MAINTENANCE. */
	private static final int MAINTENANCE = 0;

	/** The Constant ALARM. */
	private static final int ALARM = 1;

	/** The Constant WARNING. */
	private static final int WARNING = 2;

	/** The Constant ACTIVE. */
	private static final int ACTIVE = 3;

	/** The Constant DEACTIVATED. */
	private static final int DEACTIVATED = 4;

	/** The Constant HASH_SEARCH_VALUE. */
	public static final String HASH_SEARCH_VALUE = "query";

	/* Request Parameters */
	/**
	 * The parameter name for the "status" search parameter. Part of request parameter stack.
	 */
	public static final String HASH_STATUS = "status";

	/**
	 * The parameter name for the "group" search parameter. Part of request parameter stack.
	 */
	public static final String HASH_GROUP = "groups";

	/**
	 * The parameter name for the "tab" search parameter. Part of request parameter stack.
	 */
	public static final String HASH_TAB = "tb";

	/**
	 * The parameter name for the "tag" search parameter. Part of request parameter stack.
	 */
	public static final String HASH_TAG = "tags";

	/** The Constant HASH_EVENT. */
	public static final String HASH_EVENT = "event_schedule";

	/** The Constant HASH_OFFSET. */
	public static final String HASH_OFFSET = "offset_schedule";

	/** The Constant HASH_CONFIGURATION. */
	public static final String HASH_CONFIGURATION = "configuration";

	/** The parameter name for the "tag" search parameter. Part of request parameter stack. */
	public static final String HASH_LAMP_TYPE = "lamptype";

	/** The Constant HASH_SEARCH. */
	public static final String HASH_SEARCH = "sch";

	/** The Constant HASH_STREET. */
	public static final String HASH_STREET = "street";

	/** The Constant HASH_CITY. */
	public static final String HASH_CITY = "city";

	/** The Constant HASH_ZIP. */
	public static final String HASH_ZIP = "zip";

	/** The Constant HASH_HOUSING. */
	public static final String HASH_HOUSING = "housing";

	/** The Constant HASH_DIMMABLE. */
	public static final String HASH_DIMMABLE = "dimmable";

	/** The Constant HASH_WATTAGE. */
	public static final String HASH_WATTAGE = "wattage";

	/** The Constant GROUP_KEY. */
	private static final String GROUP_KEY = "Group";

	/** The Constant TAG_KEY. */
	private static final String TAG_KEY = "Tag";

	/** The Constant OFFSET_SCHEDULE_KEY. */
	private static final String OFFSET_SCHEDULE_KEY = "Offset Schedule";

	/** The Constant EVENT_SCHEDULE_KEY. */
	private static final String EVENT_SCHEDULE_KEY = "Event Schedule";

	/** The Constant ALARM_TYPE_KEY. */
	private static final String ALARM_TYPE_KEY = "Alarm Type";

	/** The Constant ALARM_TYPE. */
	private static final String ALARM_TYPE = "alarm_type";

	/** The Constant WARNING_TYPES_KEY. */
	private static final String WARNING_TYPES_KEY = "Warning Types";

	/** The Constant WARNING_TYPES. */
	private static final String WARNING_TYPES = "warning_type";

	/** The Constant ALL. */
	private static final String ALL = "ALL";
	/**
	 * The logger for this class.
	 */
	private static transient Log LOG = LogFactory.getLog(SearchParametersUtil.class);

	/** The Constant FREQUENCY. */
	private static final String FREQUENCY = "frequency";

	/** The Constant VOLTAGE. */
	private static final String VOLTAGE = "voltage";

	/** The Constant CURRENT. */
	private static final String CURRENT = "current";

	/** The Constant CONSUMPTION. */
	private static final String CONSUMPTION = "consumption";

	/** The Constant GREATER_THAN. */
	private static final String GREATER_THAN = "gt";

	/** The Constant LESS_THAN. */
	private static final String LESS_THAN = "lt";

	/** The Constant SERIAL_NUMBER. */
	private static final String SERIAL_NUMBER = "serial";

	/** The Constant VOLTAGE_RANGE. */
	private static final String VOLTAGE_RANGE = "range";

	/** The Constant COLOR_TEMPERATURE. */
	private static final String COLOR_TEMPERATURE = "colorTemperature";

	/** The Constant PART_NUMBER. */
	private static final String PART_NUMBER = "partNumber";

	/** The Constant FIRMWARE. */
	private static final String FIRMWARE = "firmware";

	/** The Constant AFTER. */
	private static final String AFTER = "after";

	/** The Constant BEFORE. */
	private static final String BEFORE = "before";

	private static final String SORT = "sort";

	private static final String PAGE_SIZE = "length";

	private static final String COLON = ":";

	/**
	 * Instantiates a new search light util.
	 */
	private SearchParametersUtil()
	{
	}

	/**
	 * Gets the search light.
	 * 
	 * @param parameters the parameters
	 * @return the search light
	 */
	public static SearchLight getSearchLight(Map<String, String[]> parameters)
	{

		// Parse filter values from request parameters.
		// Default to most inclusive filter value if parsing error occurs.

		List<LightStatusEnum> alert = getEnumListStatus(parameters.get(HASH_STATUS));

		SearchLight searchLight = new SearchLight();
		searchLight.setStatusList(alert);

		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();

		searchParameters =
				fillListParameters(parameters.get(HASH_GROUP), GROUP_KEY, PropertyEnum.GROUP_ID,
						PropertyEnum.ALL_GROUPS, searchParameters);

		searchParameters =
				fillListParameters(parameters.get(HASH_TAG), TAG_KEY, PropertyEnum.TAG_ID, PropertyEnum.ALL_TAGS,
						searchParameters);

		searchParameters =
				fillListParameters(parameters.get(HASH_OFFSET), OFFSET_SCHEDULE_KEY,
						PropertyEnum.OFFSET_SCHEDULE, PropertyEnum.ALL_OFFSETS, searchParameters);

		searchParameters =
				fillListParameters(parameters.get(HASH_EVENT), EVENT_SCHEDULE_KEY,
						PropertyEnum.EVENT_SCHEDULE, PropertyEnum.ALL_EVENTS, searchParameters);

		searchParameters =
				fillListParameters(parameters.get(ALARM_TYPE), ALARM_TYPE_KEY,
						PropertyEnum.ALL_ALARMS, PropertyEnum.ALL_ALARMS, searchParameters);

		searchParameters =
				fillListParameters(parameters.get(WARNING_TYPES), WARNING_TYPES_KEY,
						PropertyEnum.ALL_WARNINGS, PropertyEnum.ALL_WARNINGS, searchParameters);

		searchParameters =
				getSearchParameters(parameters.get(HASH_SEARCH_VALUE),
						searchParameters);
		searchParameters = getSearchParameters(parameters.get(HASH_STREET), PropertyEnum.STREET_NAME, searchParameters);
		searchParameters = getSearchParameters(parameters.get(HASH_CITY), PropertyEnum.CITY_NAME, searchParameters);
		searchParameters = getSearchParameters(parameters.get(HASH_ZIP), PropertyEnum.ZIP_CODE, searchParameters);

		searchParameters =
				getSearchProtected(parameters.get(HASH_CONFIGURATION), PropertyEnum.PROTECTED, searchParameters);

		if (!ValidationUtil.isNull(parameters.get(HASH_LAMP_TYPE)) && !parameters.get(HASH_LAMP_TYPE)[0].equals(ALL))
		{
			searchParameters =
					getSearchParameters(parameters.get(HASH_LAMP_TYPE)[0], PropertyEnum.LAMP_TYPE, searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(HASH_WATTAGE)) && !parameters.get(HASH_WATTAGE)[0].equals(ALL))
		{
			searchParameters =
					getSearchParameters(parameters.get(HASH_WATTAGE)[0], PropertyEnum.WATTAGE_RATING,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(HASH_HOUSING)) && !parameters.get(HASH_HOUSING)[0].equals(ALL))
		{
			searchParameters =
					getSearchParameters(parameters.get(HASH_HOUSING)[0], PropertyEnum.HOUSING, searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(HASH_DIMMABLE)) && !parameters.get(HASH_DIMMABLE)[0].equals(ALL))
		{
			searchParameters =
					getSearchParameters(parameters.get(HASH_DIMMABLE)[0], PropertyEnum.DIMMABLE, searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(FREQUENCY))
				&& !ValidationUtil.isNull(parameters.get(FREQUENCY)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(FREQUENCY)[0], PropertyEnum.FREQUENCY, searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(VOLTAGE)) && !ValidationUtil.isNull(parameters.get(VOLTAGE)[0]))
		{
			String[] voltageValuesStrings = parameters.get(VOLTAGE)[0].split(COLON);
			if (voltageValuesStrings[0].equals(GREATER_THAN))
			{
				searchParameters =
						getSearchParameters(voltageValuesStrings[1], PropertyEnum.AC_VOLTAGE_MIN,
								searchParameters);
			}
			else if (voltageValuesStrings[0].equals(LESS_THAN))
			{
				searchParameters =
						getSearchParameters(voltageValuesStrings[1], PropertyEnum.AC_VOLTAGE_MAX,
								searchParameters);
			}
		}

		if (!ValidationUtil.isNull(parameters.get(CURRENT)) && !ValidationUtil.isNull(parameters.get(CURRENT)[0]))
		{
			String[] currentValuesStrings = parameters.get(CURRENT)[0].split(COLON);
			if (currentValuesStrings[0].equals(GREATER_THAN))
			{
				searchParameters =
						getSearchParameters(currentValuesStrings[1], PropertyEnum.AC_CURRENT_MIN,
								searchParameters);
			}
			else if (currentValuesStrings[0].equals(LESS_THAN))
			{
				searchParameters =
						getSearchParameters(currentValuesStrings[1], PropertyEnum.AC_CURRENT_MAX,
								searchParameters);
			}
		}

		if (!ValidationUtil.isNull(parameters.get(CONSUMPTION))
				&& !ValidationUtil.isNull(parameters.get(CONSUMPTION)[0]))
		{
			String[] consumptionValuesStrings = parameters.get(CONSUMPTION)[0].split(COLON);
			if (consumptionValuesStrings[0].equals(GREATER_THAN))
			{
				searchParameters =
						getSearchParameters(consumptionValuesStrings[1], PropertyEnum.CONSUMPTION_MIN,
								searchParameters);
			}
			else if (consumptionValuesStrings[0].equals(LESS_THAN))
			{
				searchParameters =
						getSearchParameters(consumptionValuesStrings[1], PropertyEnum.CONSUMPTION_MAX,
								searchParameters);
			}
		}

		if (!ValidationUtil.isNull(parameters.get(SERIAL_NUMBER))
				&& !ValidationUtil.isNull(parameters.get(SERIAL_NUMBER)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(SERIAL_NUMBER)[0], PropertyEnum.LIGHT_DRIVER_SERIAL_NUMBER,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(VOLTAGE_RANGE))
				&& !ValidationUtil.isNull(parameters.get(VOLTAGE_RANGE)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(VOLTAGE_RANGE)[0], PropertyEnum.INPUT_VOLTAGE_RANGE,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(COLOR_TEMPERATURE))
				&& !ValidationUtil.isNull(parameters.get(COLOR_TEMPERATURE)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(COLOR_TEMPERATURE)[0], PropertyEnum.COLOR_TEMPERATURE,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(PART_NUMBER))
				&& !ValidationUtil.isNull(parameters.get(PART_NUMBER)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(PART_NUMBER)[0], PropertyEnum.MODEL_NUMBER,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(FIRMWARE)) && !ValidationUtil.isNull(parameters.get(FIRMWARE)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(FIRMWARE)[0], PropertyEnum.FIRMWARE_VERSION,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(AFTER)) && !ValidationUtil.isNull(parameters.get(AFTER)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(AFTER)[0], PropertyEnum.DATE_ADDED_AFTER,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(BEFORE)) && !ValidationUtil.isNull(parameters.get(BEFORE)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(BEFORE)[0], PropertyEnum.DATE_ADDED_BEFORE,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(SORT)) && !ValidationUtil.isNull(parameters.get(SORT)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(SORT)[0], PropertyEnum.SORT,
							searchParameters);
		}

		if (!ValidationUtil.isNull(parameters.get(PAGE_SIZE)) && !ValidationUtil.isNull(parameters.get(PAGE_SIZE)[0]))
		{
			searchParameters =
					getSearchParameters(parameters.get(PAGE_SIZE)[0], PropertyEnum.PAGE_SIZE,
							searchParameters);
		}

		searchLight.setSearchParameters(searchParameters);

		return searchLight;
	}

	/**
	 * Gets the parameters with status.
	 * 
	 * @param parameters the parameters
	 * @return the parameters with status
	 */
	public static List<SearchParameter> getParametersWithStatus(Map<String, String[]> parameters)
	{
		SearchLight searchLight = getSearchLight(parameters);
		List<LightStatusEnum> lightStatusEnum = searchLight.getStatusList();
		List<SearchParameter> searchParameters = searchLight.getSearchParameters();

		for (LightStatusEnum status : lightStatusEnum)
		{
			getSearchParameters(LightStatusEnum.enumForValue(status.getValue()).toString(),
					PropertyEnum.STATUS_MESSAGE_ID, searchParameters);
		}

		return searchParameters;

	}

	/**
	 * Gets the search parameters.
	 * 
	 * @param value the value
	 * @param type the type
	 * @param searchParameters the search parameters
	 * @return the search parameters
	 */
	private static List<SearchParameter> getSearchParameters(Integer value, PropertyEnum type,
			List<SearchParameter> searchParameters)
	{
		SearchParameter param = new SearchParameter();
		param.setValue(value.toString());
		param.setPropertyEnum(type);
		searchParameters.add(param);
		return searchParameters;

	}

	/**
	 * Fill list parameters.
	 * 
	 * @param parameter the parameter
	 * @param type the type
	 * @param propertyEnum the property enum
	 * @param propertyEnumAll the property enum all
	 * @param searchParameters the search parameters
	 * @return the list
	 */
	private static List<SearchParameter> fillListParameters(String[] parameter, String type, PropertyEnum propertyEnum,
			PropertyEnum propertyEnumAll,
			List<SearchParameter> searchParameters)
	{
		try
		{
			if (!ValidationUtil.isNull(parameter))
			{
				for (String p : parameter[0].split(PIPE))
				{
					if (!ValidationUtil.isNullOrEmpty(p))
					{
						if (ALL.equals(p))
						{
							searchParameters =
									getSearchParameters(p, propertyEnumAll, searchParameters);
						}
						else
						{
							searchParameters = getSearchParameters(Integer.parseInt(p), propertyEnum, searchParameters);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Error parsing " + type + " from '" + parameter + "'", e);
			}
		}

		return searchParameters;
	}

	/**
	 * Gets the enum list status.
	 * 
	 * @param status the status
	 * @return the enum list status
	 */
	private static List<LightStatusEnum> getEnumListStatus(String[] status)
	{
		List<LightStatusEnum> statusEnumList = new ArrayList<LightStatusEnum>();
		if (!ValidationUtil.isNull(status))
		{
			for (String fs : status[0].split(PIPE))
			{
				if (!fs.equals(ALL))
				{
					switch (Integer.valueOf(fs))
					{
						case MAINTENANCE:
							statusEnumList.add(LightStatusEnum.MAINTENANCE);
							break;
						case ALARM:
							statusEnumList.add(LightStatusEnum.ALARM);
							break;
						case WARNING:
							statusEnumList.add(LightStatusEnum.WARNING);
							break;
						case ACTIVE:
							statusEnumList.add(LightStatusEnum.ACTIVE);
							break;
						case DEACTIVATED:
							statusEnumList.add(LightStatusEnum.DEACTIVATED);
							break;
						default:
							break;
					}
				}

			}
		}

		return statusEnumList;
	}

	/**
	 * Gets the search parameters.
	 * 
	 * @param value the value
	 * @param type the type
	 * @param searchParameters the search parameters
	 * @return the search parameters
	 */
	private static List<SearchParameter> getSearchParameters(String value, PropertyEnum type,
			List<SearchParameter> searchParameters)
	{
		if (PropertyEnum.SORT.equals(type))
		{
			value = value.replace("|", " ");
		}

		SearchParameter param = new SearchParameter();
		param.setValue(value);
		param.setPropertyEnum(type);

		searchParameters.add(param);

		return searchParameters;

	}

	/**
	 * Gets the search parameters.
	 * 
	 * @param value the value
	 * @param type the type
	 * @param searchParameters the search parameters
	 * @return the search parameters
	 */
	private static List<SearchParameter> getSearchParameters(String[] value, PropertyEnum type,
			List<SearchParameter> searchParameters)
	{

		if (!ValidationUtil.isNull(value) && !ValidationUtil.isNullOrEmpty(value[0]))
		{
			SearchParameter param = new SearchParameter();
			param.setValue(value[0]);
			param.setPropertyEnum(type);
			searchParameters.add(param);
			return searchParameters;
		}

		return searchParameters;

	}

	/**
	 * Gets the search protected.
	 * 
	 * @param strings the value
	 * @param type the type
	 * @param searchParameters the search parameters
	 * @return the search protected
	 */
	private static List<SearchParameter> getSearchProtected(String[] strings, PropertyEnum type,
			List<SearchParameter> searchParameters)
	{
		if (!ValidationUtil.isNull(strings))
		{

			for (String p : strings[0].split(PIPE))
			{
				if (!ValidationUtil.isNullOrEmpty(p))
				{

					SearchParameter param = new SearchParameter();
					param.setValue(p);
					param.setPropertyEnum(type);
					searchParameters.add(param);

				}
			}
		}

		return searchParameters;

	}

	/**
	 * Gets the search parameters.
	 * 
	 * @param searchValue the search value
	 * @param searchParameters the search parameters
	 * @return the search parameters
	 */
	private static List<SearchParameter> getSearchParameters(String[] searchValue,
			List<SearchParameter> searchParameters)
	{
		if (ValidationUtil.isNull(searchValue) || ValidationUtil.isNullOrEmpty(searchValue[0]))
		{
			return searchParameters;
		}

		String[] queryValues = searchValue[0].split(PIPE);

		SearchParameter param = new SearchParameter();
		param.setValue(queryValues[1]);

		if (StringUtils.isNumeric(queryValues[0]))
		{
			int enumValue = Integer.parseInt(queryValues[0]);
			param.setPropertyEnum(PropertyEnum.enumForValue(enumValue));
			searchParameters.add(param);

			return searchParameters;
		}

		param.setPropertyEnum(PropertyEnum.valueOf(queryValues[0]));
		searchParameters.add(param);

		return searchParameters;
	}
}