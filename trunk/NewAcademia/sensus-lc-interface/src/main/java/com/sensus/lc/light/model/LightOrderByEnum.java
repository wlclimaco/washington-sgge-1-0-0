package com.sensus.lc.light.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum LightOrderByEnum.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum LightOrderByEnum implements IStringEnum
{
	LIGHT_ID("light_id"),
	LIGHT_TYPE("light_type"),
	LIGHT_STATE("light_state"),
	FLEXNET_ID("flexnet_id"),
	TENANT_ID("tenant_id"),
	ADDRESS("address"),
	CITY("city"),
	STATE("state"),
	ZIP_CODE("zip_code"),
	COUNTY("county"),
	TIMEZONE("timezone"),
	LATITUDE("latitude"),
	LONGITUDE("longitude"),
	LIFECYCLE_STATE("lifecycle_state_i18n"),
	PROTECTED("protected"),
	POLE_ID("pole_id"),
	INTENSITY("intensity"),
	ECOMODE("ecomode"),
	ECOMODE_REPLACED_TYPE("ecomode_replaced_type"),
	ECOMODE_REPLACED_WATTAGE("ecomode_replaced_wattage"),
	CALCULATE_RETROACTIVE_ECOMODE("calculate_retroactive_ecomode"),
	BLINK_LEVEL("blink_level"),
	OVERRIDE_PER_DATE("override_per_date"),
	OVERRIDE_CREATE_DATE("override_create_date"),
	BULB_SERIAL_NUMBER("bulb_serial_number"),
	COLOR_TEMPERATURE("color_temperature"),
	DATE_ADDED("date_added"),
	FIRMWARE_VERSION("firmware_version"),
	HOUSING_COLOR("housing_color"),
	SERIAL_NUMBER("ballast_serial_number"),
	LAMP_TYPE_WATTAGE_DIMMABLE("lamp_type_wattage_dimmable"),
	LOWER_ASSEMBLY_SERIAL_NUMBER("lower_assembly_serial_number"),
	MODEL_NUMBER("model_number"),
	ALERTS("label_key"),
	UPPER_ASSEMBLY_SERIAL_NUMBER("upper_assembly_serial_number"),
	INPUT_VOLTAGE_RANGE("input_voltage_range"),
	CONSUMPTION("consumption");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new order by enum.
	 * *
	 * 
	 * @param columNameInd the colum name ind
	 */
	private LightOrderByEnum(String columNameInd)
	{
		columName = columNameInd;
	}

	/**
	 * Gets the value. * *
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return columName;
	}

}
