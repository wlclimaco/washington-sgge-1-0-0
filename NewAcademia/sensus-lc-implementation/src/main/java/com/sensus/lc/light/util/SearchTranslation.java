package com.sensus.lc.light.util;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.LightSelectionRequest;
import com.sensus.lc.ecomode.model.EcoModeFilterEnum;
import com.sensus.lc.light.model.LightTypeEnum;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.criteria.AddressCriteria;
import com.sensus.lc.light.model.criteria.ConfigurationCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.OperationalDataCriteria;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.criteria.ScheduleCriteria;
import com.sensus.lc.light.model.criteria.SearchTerm;
import com.sensus.lc.light.model.criteria.TagCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.process.model.request.ProcessRequest;

/**
 * The Class SearchTranslation.
 */
public final class SearchTranslation
{

	/**
	 * Instantiates a new search translation.
	 */
	private SearchTranslation()
	{

	}

	/**
	 * Gets the light criteria.
	 * 
	 * @param request the request
	 * @return the light criteria
	 */
	public static LightCriteria getLightCriteria(LightSelectionRequest request)
	{

		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		LightCriteria criteria = new LightCriteria();
		criteria.setLightIdList(request.getSelectionPaginationIds());
		criteria.setNotInlightIdList(request.getUnselectionPaginationIds());

		criteria.setPoleId(new SearchTerm(getStringValueByProperty(searchLight, PropertyEnum.POLE_ID)));
		criteria.setProtect(convertToBoolean(getStringValueByProperty(searchLight, PropertyEnum.PROTECTED)));
		criteria.setFlexnetId(new SearchTerm(getBigIntegerValueByProperty(searchLight, PropertyEnum.FLEXNET_ID)));
		criteria.setLightType(LightTypeEnum.enumForValue(getStringValueByProperty(searchLight, PropertyEnum.LAMP_TYPE)));
		criteria.setOverride(OverrideEnum.enumForValue(getIntegerValueByProperty(searchLight, PropertyEnum.OVERRIDE)));
		criteria.setEcomodeFilter(getEcoModeFilter(searchLight));

		return criteria;
	}

	/**
	 * Gets the configuration criteria.
	 * 
	 * @param request the request
	 * @return the configuration criteria
	 */
	public static ConfigurationCriteria getConfigurationCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		ConfigurationCriteria criteria = new ConfigurationCriteria();
		criteria.setHousing(getStringValueByProperty(searchLight, PropertyEnum.HOUSING));
		criteria.setHousingColor(convertToList(getStringValueByProperty(searchLight, PropertyEnum.HOUSING_COLOR)));
		criteria.setDimmable(convertToBoolean(getStringValueByProperty(searchLight, PropertyEnum.DIMMABLE)));
		criteria.setWattageRating(getStringValueByProperty(searchLight, PropertyEnum.WATTAGE_RATING));
		criteria.setLampTypeWattageDimmable(getStringValueByProperty(searchLight,
				PropertyEnum.LAMP_TYPE_WATTAGE_DIMMABLE));
		criteria.setInputVoltageRange(convertToList(getStringValueByProperty(searchLight,
				PropertyEnum.INPUT_VOLTAGE_RANGE)));
		criteria.setColorTemperature(getStringValueByProperty(searchLight, PropertyEnum.COLOR_TEMPERATURE));
		criteria.setUpperAssemblySerial(getStringValueByProperty(searchLight, PropertyEnum.UPPER_ASSEMBLY_SERIAL_NUMBER));
		criteria.setLowerAssemblySerial(getStringValueByProperty(searchLight, PropertyEnum.LOWER_ASSEMBLY_SERIAL_NUMBER));
		criteria.setBulbSerialNumber(getStringValueByProperty(searchLight, PropertyEnum.BULB_SERIAL_NUMBER));
		criteria.setFirmwareVersion(getStringValueByProperty(searchLight, PropertyEnum.FIRMWARE_VERSION));
		criteria.setModelNumber(getStringValueByProperty(searchLight, PropertyEnum.MODEL_NUMBER));
		criteria.setBallastSerialNumber(getStringValueByProperty(searchLight, PropertyEnum.BALLAST_SERIAL_NUMBER));
		if (!ValidationUtil.isNull(getStringValueByProperty(searchLight, PropertyEnum.DATE_ADDED_BEFORE))
				&& !ValidationUtil.isNull(getStringValueByProperty(searchLight, PropertyEnum.DATE_ADDED_AFTER)))
		{
			criteria.setDateAddedBefore(Date.valueOf(getStringValueByProperty(searchLight,
					PropertyEnum.DATE_ADDED_BEFORE)));
			criteria.setDateAddedAfter(Date
					.valueOf(getStringValueByProperty(searchLight, PropertyEnum.DATE_ADDED_AFTER)));
		}

		return criteria;
	}

	/**
	 * Address criteria.
	 * 
	 * @param request the request
	 * @return the address criteria
	 */
	public static AddressCriteria getAddressCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		AddressCriteria criteria = new AddressCriteria();
		criteria.setAddress(getStringValueByProperty(searchLight, PropertyEnum.STREET_NAME));
		criteria.setCity(getStringValueByProperty(searchLight, PropertyEnum.CITY_NAME));
		criteria.setZip(getStringValueByProperty(searchLight, PropertyEnum.ZIP_CODE));

		return criteria;
	}

	/**
	 * Operational data criteria.
	 * 
	 * @param request the request
	 * @return the operational data criteria
	 */
	public static OperationalDataCriteria getOperationalDataCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		OperationalDataCriteria criteria = new OperationalDataCriteria();
		criteria.setVoltageGreater(getIntegerValueByProperty(searchLight, PropertyEnum.AC_VOLTAGE_MIN));
		criteria.setVoltageLess(getIntegerValueByProperty(searchLight, PropertyEnum.AC_VOLTAGE_MAX));
		criteria.setCurrentGreater(getIntegerValueByProperty(searchLight, PropertyEnum.AC_CURRENT_MIN));
		criteria.setCurrentLess(getIntegerValueByProperty(searchLight, PropertyEnum.AC_CURRENT_MAX));
		criteria.setConsumptionGreater(getIntegerValueByProperty(searchLight, PropertyEnum.CONSUMPTION_MIN));
		criteria.setConsumptionLess(getIntegerValueByProperty(searchLight, PropertyEnum.CONSUMPTION_MAX));

		return criteria;
	}

	/**
	 * Gets the schedule criteria.
	 * 
	 * @param request the request
	 * @return the schedule criteria
	 */
	public static ScheduleCriteria getScheduleCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		ScheduleCriteria criteria = new ScheduleCriteria();
		criteria.setLightSchedule(getIntegerValuesByProperty(getScheduleIdFromEvenOffsetSchedule(searchLight),
				PropertyEnum.SCHEDULE_ID));
		criteria.setAllEvents(getStringValueByProperty(searchLight, PropertyEnum.ALL_EVENTS));
		criteria.setAllOffsets(getStringValueByProperty(searchLight, PropertyEnum.ALL_OFFSETS));

		return criteria;
	}

	/**
	 * Sets the light request criterias.
	 * 
	 * @param searchLight the search light
	 * @return the light request
	 */
	public static LightRequest setLightRequestCriterias(LightSelectionRequest lightSelectionRequest)
	{
		LightRequest lightRequest = new LightRequest();
		lightRequest.setLightCriteria(getLightCriteria(lightSelectionRequest));
		lightRequest.setConfigurationCriteria(getConfigurationCriteria(lightSelectionRequest));
		lightRequest.setAddressCriteria(getAddressCriteria(lightSelectionRequest));
		lightRequest.setOperationalDataCriteria(getOperationalDataCriteria(lightSelectionRequest));
		lightRequest.setScheduleCriteria(getScheduleCriteria(lightSelectionRequest));
		lightRequest.setTagCriteria(getTagCriteria(lightSelectionRequest));
		lightRequest.setGroupCriteria(getGroupCriteria(lightSelectionRequest));
		lightRequest.setProcessCriteria(getProcessCriteria(lightSelectionRequest));

		return lightRequest;
	}

	/**
	 * Sets the process request criterias.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @param processRequest the process request
	 * @return the process request
	 */
	public static ProcessRequest setProcessRequestCriterias(LightSelectionRequest lightSelectionRequest,
			ProcessRequest processRequest)
	{
		processRequest.setLightCriteria(getLightCriteria(lightSelectionRequest));
		processRequest.setConfigurationCriteria(getConfigurationCriteria(lightSelectionRequest));
		processRequest.setAddressCriteria(getAddressCriteria(lightSelectionRequest));
		processRequest.setOperationalDataCriteria(getOperationalDataCriteria(lightSelectionRequest));
		processRequest.setScheduleCriteria(getScheduleCriteria(lightSelectionRequest));
		processRequest.setTagCriteria(getTagCriteria(lightSelectionRequest));
		processRequest.setGroupCriteria(getGroupCriteria(lightSelectionRequest));
		processRequest.setProcessCriteria(getProcessCriteria(lightSelectionRequest));

		return processRequest;
	}

	/**
	 * Gets the tag criteria.
	 * 
	 * @param request the request
	 * @return the tag criteria
	 */
	public static TagCriteria getTagCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		TagCriteria criteria = new TagCriteria();
		criteria.setTagIdList(getIntegerValuesByProperty(searchLight, PropertyEnum.ALL_TAGS));

		return criteria;
	}

	/**
	 * Gets the group criteria.
	 * 
	 * @param request the request
	 * @return the group criteria
	 */
	public static GroupCriteria getGroupCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();
		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		GroupCriteria criteria = new GroupCriteria();
		criteria.setGroupIdList(getIntegerValuesByProperty(searchLight, PropertyEnum.ALL_GROUPS));

		return criteria;
	}

	/**
	 * Gets the process criteria.
	 * 
	 * @param request the request
	 * @return the process criteria
	 */
	public static ProcessCriteria getProcessCriteria(LightSelectionRequest request)
	{
		if (ValidationUtil.isNull(request))
		{
			return null;
		}

		SearchLight searchLight = request.getSearchLight();

		if (ValidationUtil.isNull(searchLight))
		{
			return null;
		}

		ProcessCriteria criteria = new ProcessCriteria();
		criteria.setProcessId(getIntegerValueByProperty(searchLight, PropertyEnum.PROCESS_ID));

		return criteria;
	}

	/**
	 * Gets the integer values by property.
	 * 
	 * @param search the search
	 * @param property the property
	 * @return the integer values by property
	 */
	private static List<Integer> getIntegerValuesByProperty(SearchLight search, PropertyEnum property)
	{
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return null;
		}

		List<Integer> values = new ArrayList<Integer>();
		for (SearchParameter param : search.getSearchParameters())
		{
			if (property.equals(param.getPropertyEnum()) && !isNull(param.getValue()) && isNumber(param.getValue()))
			{
				values.add(Integer.parseInt(param.getValue()));
			}
		}

		if (!isNullOrEmpty(values))
		{
			return values;
		}
		return null;
	}

	/**
	 * Gets the bigInteger value by property.
	 * 
	 * @param search the search
	 * @param property the property
	 * @return the bigInteger value by property
	 */
	private static BigInteger getBigIntegerValueByProperty(SearchLight search, PropertyEnum property)
	{
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return null;
		}

		for (SearchParameter param : search.getSearchParameters())
		{
			if (property.equals(param.getPropertyEnum()) && !isNull(param.getValue()) && isNumber(param.getValue()))
			{
				return new BigInteger(param.getValue());
			}
		}

		return null;
	}

	/**
	 * Gets the integer value by property.
	 * 
	 * @param search the search
	 * @param property the property
	 * @return the integer value by property
	 */
	private static Integer getIntegerValueByProperty(SearchLight search, PropertyEnum property)
	{
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return null;
		}

		for (SearchParameter param : search.getSearchParameters())
		{
			if (property.equals(param.getPropertyEnum()) && !isNull(param.getValue()) && isNumber(param.getValue()))
			{
				return Integer.parseInt(param.getValue());
			}
		}

		return null;
	}

	/**
	 * Gets the string value by property.
	 * 
	 * @param search the search
	 * @param property the property
	 * @return the string value by property
	 */
	private static String getStringValueByProperty(SearchLight search, PropertyEnum property)
	{
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return null;
		}

		for (SearchParameter param : search.getSearchParameters())
		{
			if (property.equals(param.getPropertyEnum()) && !isNull(param.getValue()))
			{
				return param.getValue();
			}
		}
		return null;
	}

	/**
	 * Convert to boolean.
	 * 
	 * @param value the value
	 * @return the boolean
	 */
	private static Boolean convertToBoolean(String value)
	{
		if (ValidationUtil.isNull(value)
				|| !Boolean.TRUE.toString().equals(value)
				|| !Boolean.FALSE.toString().equals(value))
		{
			return null;
		}
		return Boolean.valueOf(value);
	}

	/**
	 * Gets the schedule id from even offset schedule.
	 * 
	 * @param search the search
	 * @return the schedule id from even offset schedule
	 */
	private static SearchLight getScheduleIdFromEvenOffsetSchedule(SearchLight search)
	{
		SearchLight searchLight = new SearchLight();
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return searchLight;
		}

		for (SearchParameter param : search.getSearchParameters())
		{
			if (PropertyEnum.EVENT_SCHEDULE.equals(param.getPropertyEnum())
					|| PropertyEnum.OFFSET_SCHEDULE.equals(param.getPropertyEnum()))
			{
				searchLight.addSearchParameter(new SearchParameter(PropertyEnum.SCHEDULE_ID, param.getValue()));
			}
		}
		return searchLight;
	}

	/**
	 * Gets the eco mode filter.
	 * 
	 * @param searchLight the search light
	 * @return the eco mode filter
	 */
	private static List<EcoModeFilterEnum> getEcoModeFilter(SearchLight searchLight)
	{
		if (isNull(searchLight) || isNullOrEmpty(searchLight.getSearchParameters()))
		{
			return null;
		}

		List<EcoModeFilterEnum> lstEcoModeFilter = new ArrayList<EcoModeFilterEnum>();
		for (SearchParameter searchParameter : searchLight.getSearchParameters())
		{
			if (PropertyEnum.ECOMODE.equals(searchParameter.getPropertyEnum()))
			{
				String ecoModeValue = searchParameter.getValue();
				lstEcoModeFilter.add(EcoModeFilterEnum.enumForValue(ecoModeValue));
			}
		}

		if (isNullOrEmpty(lstEcoModeFilter))
		{
			return null;
		}

		return lstEcoModeFilter;
	}

	/**
	 * Convert to list.
	 * 
	 * @param <T> the generic type
	 * @param values the values
	 * @return the list
	 */
	@SafeVarargs
	private static <T> List<T> convertToList(T... values)
	{
		if (isNullOrZero(values.length))
		{
			return null;
		}

		List<T> list = new ArrayList<T>();
		for (T value : values)
		{
			if (!isNull(value))
			{
				list.add(value);
			}
		}
		return list;
	}
}
