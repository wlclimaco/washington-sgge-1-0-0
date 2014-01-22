package com.sensus.lc.server.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightTypeEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SerialNumberElement;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.DataType;
import com.sensus.mlc.mlcserver.type.LightLevel;
import com.sensus.mlc.mlcserver.type.LightState;
import com.sensus.mlc.mlcserver.type.LightTopLevelState;

/**
 * Util class for MlcServer data.
 * 
 * @author Thiago
 * 
 */
public final class MlcServerDataUtil
{

	/** The Constant MODEL_NUMBER_SIZE_14. */
	private static final int MODEL_NUMBER_SIZE_14 = 14;

	/** The Constant MODEL_NUMBER_SIZE_15. */
	private static final int MODEL_NUMBER_SIZE_15 = 15;

	/**
	 * Instantiates a new mlc server data util.
	 */
	private MlcServerDataUtil()
	{

	}

	/**
	 * Translate light top level state.
	 * 
	 * @param lightTopLevelState the light top level state
	 * @return the light status enum
	 */
	public static LifeCycleStateEnum translateLightTopLevelState(LightTopLevelState lightTopLevelState)
	{
		// Decide, based on light state, if light will go on Maintenance
		if (LightTopLevelState.MAINTENANCE.equals(lightTopLevelState))
		{
			return LifeCycleStateEnum.MAINTENANCE;
		}
		else if (LightTopLevelState.IDLE.equals(lightTopLevelState))
		{
			return LifeCycleStateEnum.DEACTIVATED;
		}
		else if (LightTopLevelState.ACTIVE.equals(lightTopLevelState))
		{
			return LifeCycleStateEnum.ACTIVE;
		}
		else if (LightTopLevelState.UNKNOWN.equals(lightTopLevelState))
		{
			return LifeCycleStateEnum.UNKNOWN;
		}

		// Alarm/Warning will be decided when exceptions are evaluated (in the DAC).For now, flag it as UNKNOWN
		return LifeCycleStateEnum.UNKNOWN;
	}

	/**
	 * Translate light state.
	 * 
	 * @param lightState the light state
	 * @return the light status enum
	 */
	public static com.sensus.lc.light.model.LightStateEnum translateLightState(LightState lightState)
	{
		if (ValidationUtil.isNull(lightState))
		{
			return null;
		}

		switch (lightState)
		{
			case ON:
				return com.sensus.lc.light.model.LightStateEnum.ON;
			case OFF:
				return com.sensus.lc.light.model.LightStateEnum.OFF;
			case BLINK:
				return com.sensus.lc.light.model.LightStateEnum.BLINK;
			case MAINTENANCE:
				return com.sensus.lc.light.model.LightStateEnum.MAINTENANCE;
			case UNKNOWN:
				return com.sensus.lc.light.model.LightStateEnum.UNKNOWN;
			default:
				return null;
		}
	}

	/**
	 * Translate status exception.
	 * 
	 * @param alarmWarningSubType the alarm warning sub type
	 * @return the alert sub type enum
	 */
	public static AlertSubTypeEnum translateAlertSubtype(AlarmWarningSubType alarmWarningSubType)
	{
		if (ValidationUtil.isNull(alarmWarningSubType))
		{
			return null;
		}

		switch (alarmWarningSubType)
		{
			case BROWN_OUT_DETECTED:
				return AlertSubTypeEnum.BROWN_OUT_DETECTED;
			case LAMP_FAILURE:
				return AlertSubTypeEnum.LAMP_FAILURE;
			case POWER_FAILURE:
				return AlertSubTypeEnum.POWER_FAILURE;
			case POWER_SURGE_DETECTED:
				return AlertSubTypeEnum.POWER_SURGE_DETECTED;
			case METROLOGY_ERROR:
				return AlertSubTypeEnum.METROLOGY_ERROR;
			case METROLOGY_COM_FAILURE:
				return AlertSubTypeEnum.METROLOGY_COM_FAILURE;
			case HIGH_CURRENT:
				return AlertSubTypeEnum.HIGH_CURRENT;
			case LOW_CURRENT:
				return AlertSubTypeEnum.LOW_CURRENT;
			case REVERSE_ENERGY:
				return AlertSubTypeEnum.REVERSE_ENERGY;
			case METROLOGY_RESET:
				return AlertSubTypeEnum.METROLOGY_RESET;
			case BOARD_FAILURE:
				return AlertSubTypeEnum.BOARD_FAILURE;
			default:
				return null;
		}
	}

	/**
	 * Translate data type.
	 * 
	 * @param dataType the data type
	 * @return the light detail data type enum
	 */
	public static com.sensus.lc.light.model.LightDetailDataTypeEnum translateDataType(DataType dataType)
	{
		if (ValidationUtil.isNull(dataType))
		{
			return null;
		}
		switch (dataType)
		{
			case STATUS_DATA:
				return com.sensus.lc.light.model.LightDetailDataTypeEnum.STATUS;
			case CONFIGURATION_DATA:
				return com.sensus.lc.light.model.LightDetailDataTypeEnum.CONFIGURATION;
			default:
				return null;
		}
	}

	/**
	 * Translate light intensity.
	 * 
	 * @param lightIntensity the light intensity
	 * @return the light intensity enum
	 */
	public static IntensityEnum translateLightIntensity(LightLevel lightIntensity)
	{
		if (ValidationUtil.isNull(lightIntensity))
		{
			return null;
		}
		switch (lightIntensity)
		{
			case LEVEL_0:
				return IntensityEnum.LEVEL_0;
			case LEVEL_1:
				return IntensityEnum.LEVEL_1;
			case LEVEL_2:
				return IntensityEnum.LEVEL_2;
			case LEVEL_3:
				return IntensityEnum.LEVEL_3;
			case LEVEL_4:
				return IntensityEnum.LEVEL_4;
			case LEVEL_5:
				return IntensityEnum.LEVEL_5;
			case LEVEL_6:
				return IntensityEnum.LEVEL_6;
			default:
				return null;
		}
	}

	/**
	 * Prepare time.
	 * 
	 * @param hour the hour
	 * @param min the min
	 * @return the string
	 */
	public static String prepareTime(Integer hour, Integer min, Tenant tenant)
	{
		return LCDateUtil.convertSunriseSunsetToDefaultUTC(hour, min, tenant.getLightTimeZone());
	}

	/**
	 * Model Number is a compound code. This routine parses the model number and returns a list of properties and its
	 * values embedded in the Model Number.
	 * 
	 * @param productNumber the product number
	 * @return the list
	 */
	public static void parseModelNumber(String productNumber, List<SerialNumberElement> serialNumberLayout,
			Light light)
	{
		if (productNumber.length() == MODEL_NUMBER_SIZE_14 || productNumber.length() == MODEL_NUMBER_SIZE_15)
		{
			Map<String, String> modelNumberProperties = new HashMap<String, String>();

			// Cycle through all elements that are part of productNumber
			for (SerialNumberElement el : serialNumberLayout)
			{
				modelNumberProperties.put(el.getPropertyType().toString(), el.getValue(el.parseElement(productNumber)));
			}
			applyModelNumberPropertiesToLight(modelNumberProperties, light);
		}
	}

	/**
	 * Apply model number properties to light.
	 * 
	 * @param modelNumberProperties the model number properties
	 * @param light the light
	 */
	private static void applyModelNumberPropertiesToLight(Map<String, String> modelNumberProperties,
			Light light)
	{
		light.setLightType(LightTypeEnum.enumForValue(modelNumberProperties.get(PropertyEnum.LIGHT_SOURCE.name())));
		Configuration configuration = light.getConfiguration();
		configuration.setWattageRating(modelNumberProperties.get(PropertyEnum.WATTAGE_RATING.name()));
		configuration.setHousing(modelNumberProperties.get(PropertyEnum.HOUSING.name()));
		configuration.setFrequency(modelNumberProperties.get(PropertyEnum.FREQUENCY.name()));
		configuration.setHousingColor(modelNumberProperties.get(PropertyEnum.HOUSING_COLOR.name()));
		configuration.setManufacturer(modelNumberProperties.get(PropertyEnum.MANUFACTURER.name()));
		configuration.setDimmable(Boolean.valueOf(modelNumberProperties.get(PropertyEnum.DIMMABLE.name())));
		configuration.setInputVoltageRange(modelNumberProperties.get(PropertyEnum.INPUT_VOLTAGE_RANGE.name()));
		configuration.setColorTemperature(modelNumberProperties.get(PropertyEnum.COLOR_TEMPERATURE.name()));
		configuration.setLampTypeWattageDimmable(modelNumberProperties.get(PropertyEnum.LAMP_TYPE_WATTAGE_DIMMABLE
				.name()));

	}
}
