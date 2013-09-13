package com.sensus.dm.common.property.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum PropertyEnum.
 * 
 * @author - QAT Brazil
 */
public enum PropertyEnum implements IStringEnum
{
	/** The action date. */
	ACTION_DATE("ACTION_DATE"),

	/** The ACTIO n_ id. */
	ACTION_ID("ACTION_ID"),

	/** The ACTIO n_ name. */
	ACTION_NAME("ACTION_NAME"),

	/** The ADDRESS. */
	ADDRESS("ADDRESS"),

	/** The CITY_NAME. */
	CITY_NAME("CITY"),

	/** The CONNECTION_STATUS. */
	CONNECTION_STATUS("CONNECTION_STATUS"),

	/** The DA y_ o f_ month. */
	DAY_OF_MONTH("DAY_OF_MONTH"),

	/** The DA y_ o f_ week. */
	DAY_OF_WEEK("DAY_OF_WEEK"),

	/** The DAY s_ o f_ weeks. */
	DAYS_OF_WEEKS("DAYS_OF_WEEKS"),

	/** The DATE_ADDED. */
	DATE_ADDED("DATE_ADDED"),

	/** The DATE_FORMAT. */
	DATE_FORMAT("DATE_FORMAT"),

	/** The DESCRIPTION. */
	DESCRIPTION("DESCRIPTION"),

	/** The DEVICE_TYPE. */
	DEVICE_TYPE("DEVICE_TYPE"),

	/** The han device type. */
	HAN_DEVICE_TYPE("HAN_DEVICE_TYPE"),

	/** The device id. */
	DEVICE_ID("DEVICE_ID"),

	/** The device class. */
	DEVICE_CLASS("DEVICE_CLASS"),

	/** The tamper timeout. */
	TAMPER_TIMEOUT("TAMPER_TIMEOUT"),

	/** The DEMAND_RESPONSE_ENROLLMENT_CODE. */
	DEMAND_RESPONSE_ENROLLMENT_CODE("DEMAND_RESPONSE_ENROLLMENT_CODE"),

	/** The DEMAND_RESPONSE_CRITICALITY_LEVEL. */
	DEMAND_RESPONSE_CRITICALITY_LEVEL("DEMAND_RESPONSE_CRITICALITY_LEVEL"),

	/** The DEMAND_RESPONSE_DURATION. */
	DEMAND_RESPONSE_DURATION("DEMAND_RESPONSE_DURATION"),

	/** The DUTY_CYCLE_RATE. */
	DEMAND_RESPONSE_DUTY_CYCLE_RATE("DEMAND_RESPONSE_DUTY_CYCLE_RATE"),

	/** The COOLING. */
	DEMAND_RESPONSE_COOLING("DEMAND_RESPONSE_COOLING"),

	/** The HEATING. */
	DEMAND_RESPONSE_HEATING("DEMAND_RESPONSE_HEATING"),

	/** The LOA d_ adjustment. */
	DEMAND_RESPONSE_LOAD_ADJUSTMENT("DEMAND_RESPONSE_LOAD_ADJUSTMENT"),

	/** The DEMAND_RESPONSE_RANDOMIZE_START. */
	DEMAND_RESPONSE_RANDOMIZE_START("DEMAND_RESPONSE_RANDOMIZE_START"),

	/** The DEMAND_RESPONSE_RANDOMIZE_END. */
	DEMAND_RESPONSE_RANDOMIZE_END("DEMAND_RESPONSE_RANDOMIZE_END"),

	/** The DEMAND_RESPONSE_SEND_LATER. */
	DEMAND_RESPONSE_SEND_LATER("DEMAND_RESPONSE_SEND_LATER"),

	/** The DEMAND_RESPONSE_SEND_NOW. */
	DEMAND_RESPONSE_SEND_NOW("DEMAND_RESPONSE_SEND_NOW"),

	/** The electric vehicle. */
	ELECTRIC_VEHICLE("ELECTRIC_VEHICLE"),

	/** The END s_ afte r_ ocurrences. */
	ENDS_AFTER_OCURRENCES("ENDS_AFTER_OCURRENCES"),

	/** The EN d_ date. */
	END_DATE("END_DATE"),

	/** The EXECUTE d_ occurrences. */
	EXECUTED_OCCURRENCES("EXECUTED_OCCURRENCES"),

	/** The exterior lighting. */
	EXTERIOR_LIGHTING("EXTERIOR_LIGHTING"),

	/** The file downloaded. */
	FILE_DOWNLOADED("FILE_DOWNLOADED"),

	/** The FIL e_ id s_ type. */
	FILE_IDS_TYPE("FILE_IDS_TYPE"),

	/** The FILE_NAME. */
	FILE_NAME("FILE_NAME"),

	/** The FIL e_ path. */
	FILE_PATH("FILE_PATH"),

	/** The TYP e_ enum. */
	FREQUENCY_TYPE("FREQUENCY_TYPE"),

	/** The FLEXNE t_ id. */
	FLEXNET_ID("FLEXNET_ID"),

	/** The GENERATECSVFILEACTION. */
	GENERATECSVFILEACTION("GenerateCSVFileAction"),

	/** The generation systems. */
	GENERATION_SYSTEMS("GENERATION_SYSTEMS"),

	/** The GROUP_NAME. */
	GROUP_NAME("GROUP_NAME"),

	/** The GROUP_ID. */
	GROUP_ID("GROUP_ID"),

	/** The HAN text message duration. */
	HAN_TEXT_MESSAGE_DURATION("HAN_TEXT_MESSAGE_DURATION"),

	/** The HAN text message text. */
	HAN_TEXT_MESSAGE_TEXT("HAN_TEXT_MESSAGE_TEXT"),

	/** The hvac compressor. */
	HVAC_COMPRESSOR("HVAC_COMPRESSOR"),

	/** The INSTAL l_ code. */
	INSTALL_CODE("INSTALL_CODE"),

	/** The INSTALLED_DATE. */
	INSTALLED_DATE("INSTALLED_DATE"),

	/** The interior lighting. */
	INTERIOR_LIGHTING("INTERIOR_LIGHTING"),

	/** The irrigation pump. */
	IRRIGATION_PUMP("IRRIGATION_PUMP"),

	/** The Is monitored. */
	IS_MONITORED("IS_MONITORED"),

	/** The LAST_READ_VALUE. */
	LAST_READ_VALUE("LAST_READ_VALUE"),

	/** The LAST_READ_UNITS. */
	LAST_READ_UNITS("LAST_READ_UNITS"),

	/** The LAST_READ_TIME. */
	LAST_READ_TIME("LAST_READ_TIME"),

	/** The language. */
	LANGUAGE("LANGUAGE"),

	/** The LATITUDE. */
	LATITUDE("LATITUDE"),

	/** The LONGITUDE. */
	LONGITUDE("LONGITUDE"),

	/** The managed commercial. */
	MANAGED_COMMERCIAL("MANAGED_COMMERCIAL"),

	/** The MANUFACTURER. */
	MANUFACTURER("MANUFACTURER"),

	/** The MODEL. */
	MODEL("MODEL"),

	/** The METER_ID. */
	METER_ID("METER_ID"),

	/** The MONITO r_ dashboard. */
	MONITOR_DASHBOARD("MONITOR_DASHBOARD"),

	/** The MONITOR_REQUEST. */
	MONITOR_REQUEST("MONITOR_REQUEST"),

	/** The NETWORk ADDRESS. */
	NETWORK_ADDRESS("NETWORK_ADDRESS"),

	/** The NEVE r_ ends. */
	NEVER_ENDS("NEVER_ENDS"),

	/** The O n_ demand. */
	ON_DEMAND("ON_DEMAND"),

	/** The PAGE_SIZE. */
	PAGE_SIZE("PAGE_SIZE"),

	/** The Parent process. */
	PARENT_PROCESS("PARENT_PROCESS"),

	/** The pool pump. */
	POOL_PUMP("POOL_PUMP"),

	/** The PREMISE ID. */
	PREMISE_ID("PREMISE_ID"),

	/** The PROTECTED. */
	PROTECTED("PROTECTED"),

	/** The PROPERTY_ID. */
	PROPERTY_ID("PROPERTY_ID"),

	/** The schedule id. */
	SCHEDULE_ID("SCHEDULE_ID"),

	/** The schedule name. */
	SCHEDULE_NAME("SCHEDULE_NAME"),

	/** The schedule custom date. */
	SCHEDULE_CUSTOM_DATE("SCHEDULE_CUSTOM_DATE"),

	/** The show dialog polygon. */
	SHOW_DIALOG_POLYGON("SHOW_DIALOG_POLYGON"),

	/** The simple misc. */
	SIMPLE_MISC("SIMPLE_MISC"),

	/** The STAR t_ o n_ date. */
	START_ON_DATE("START_ON_DATE"),

	/** The TIME_ZONE. */
	TIME_ZONE("TIME_ZONE"),

	/** The smart appliances. */
	SMART_APPLIANCES("SMART_APPLIANCES"),

	/** The SMARTPOINT_COLUMN. */
	SMARTPOINT_COLUMN("SMARTPOINT_COLUMN"),

	/** The electric meter column. */
	ELECTRIC_METER_COLUMN("ELECTRIC_METER_COLUMN"),

	/** The han device column. */
	HAN_DEVICE_COLUMN("HAN_DEVICE_COLUMN"),

	/** The thermostat column. */
	THERMOSTAT_COLUMN("THERMOSTAT_COLUMN"),

	/** The ihd column. */
	IHD_COLUMN("IHD_COLUMN"),

	/** The lcm column. */
	LCM_COLUMN("LCM_COLUMN"),

	/** The water meter column. */
	WATER_METER_COLUMN("WATER_METER_COLUMN"),

	/** The gas meter column. */
	GAS_METER_COLUMN("GAS_METER_COLUMN"),

	/** The SMARTPOINT_FILTER. */
	SMARTPOINT_FILTER("SMARTPOINT_FILTER"),

	/** The electric meter filter. */
	ELECTRIC_METER_FILTER("ELECTRIC_METER_FILTER"),

	/** The han device filter. */
	HAN_DEVICE_FILTER("HAN_DEVICE_FILTER"),

	/** The thermostat filter. */
	THERMOSTAT_FILTER("THERMOSTAT_FILTER"),

	/** The ihd filter. */
	IHD_FILTER("IHD_FILTER"),

	/** The lcm filter. */
	LCM_FILTER("LCM_FILTER"),

	/** The water meter filter. */
	WATER_METER_FILTER("WATER_METER_FILTER"),

	/** The gas meter filter. */
	GAS_METER_FILTER("GAS_METER_FILTER"),

	/** The SMARTPOINT_ORDER. */
	SMARTPOINT_ORDER("SMARTPOINT_ORDER"),

	/** The strip heater. */
	STRIP_HEATER("STRIP_HEATER"),

	/** The TAG_ID. */
	TAG_ID("TAG_ID"),

	/** The TAG_NAME. */
	TAG_NAME("TAG_NAME"),

	/** The temperature. */
	TEMPERATURE("TEMPERATURE"),

	/** The TIM e_ t o_ repeat. */
	TIME_TO_REPEAT("TIME_TO_REPEAT"),

	/** The UNREACHABL e_ ids. */
	UNREACHABLE_IDS("UNREACHABLE_IDS"),

	/** The water heater. */
	WATER_HEATER("WATER_HEATER"),

	/** The ZIP_CODE. */
	ZIP_CODE("ZIP"),

	/** The alarm. */
	ALARM("ALARM"),

	/** The enrollment code for relay1. */
	RELAY1_ENROLLMENT_CODE("RELAY1_ENROLLMENT_CODE"),

	/** The randomize start for relay1. */
	RELAY1_RANDOMIZE_START("RELAY1_RANDOMIZE_START"),

	/** The randomize end for relay1. */
	RELAY1_RANDOMIZE_END("RELAY1_RANDOMIZE_END"),

	/** The device class for relay1. */
	RELAY1_DEVICE_CLASS("RELAY1_DEVICE_CLASS"),

	/** The tamper timeout for relay1. */
	RELAY1_TAMPER_TIMEOUT("RELAY1_TAMPER_TIMEOUT"),

	/** The enrollment code for relay2. */
	RELAY2_ENROLLMENT_CODE("RELAY2_ENROLLMENT_CODE"),

	/** The randomize start for relay2. */
	RELAY2_RANDOMIZE_START("RELAY2_RANDOMIZE_START"),

	/** The randomize end for relay2. */
	RELAY2_RANDOMIZE_END("RELAY2_RANDOMIZE_END"),

	/** The device class for relay2. */
	RELAY2_DEVICE_CLASS("RELAY2_DEVICE_CLASS"),

	/** The tamper timeout for relay1. */
	RELAY2_TAMPER_TIMEOUT("RELAY2_TAMPER_TIMEOUT"),

	/** The enrollment code for relay2. */
	RELAY3_ENROLLMENT_CODE("RELAY3_ENROLLMENT_CODE"),

	/** The randomize start for relay3. */
	RELAY3_RANDOMIZE_START("RELAY3_RANDOMIZE_START"),

	/** The randomize end for relay3. */
	RELAY3_RANDOMIZE_END("RELAY3_RANDOMIZE_END"),

	/** The device class for relay3. */
	RELAY3_DEVICE_CLASS("RELAY3_DEVICE_CLASS"),

	/** The tamper timeout for relay1. */
	RELAY3_TAMPER_TIMEOUT("RELAY3_TAMPER_TIMEOUT"),

	/** The alert. */
	ALERT("ALERT"),

	/** The quarantine. */
	QUARANTINE("QUARANTINE");

	/** The property Id. */
	private String propertyId;

	/**
	 * Instantiates a new property enum.
	 * 
	 * @param propertyIdParam the property id param
	 */
	private PropertyEnum(String propertyIdParam)
	{
		propertyId = propertyIdParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return propertyId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the property enum
	 */
	public static PropertyEnum enumForValue(String value)
	{
		for (PropertyEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

	/**
	 * Gets the valid values.
	 * 
	 * @return the valid values
	 */
	public static String getValidValues()
	{
		PropertyEnum[] enums = PropertyEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (PropertyEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}
