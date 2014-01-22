package com.sensus.lc.light.model;

import com.sensus.common.model.IStringEnum;

public enum LightColumnEnum implements IStringEnum
{
	LIGHT_ID("light.light_id"),
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
	LIFECYCLE_STATE("lifecycle_state"),
	POLE_ID("pole_id"),
	PROTECTED("protected"),
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
	MAP_IT("map_it"),
	MODEL_NUMBER("model_number"),
	ALERTS("alerts"),
	UPPER_ASSEMBLY_SERIAL_NUMBER("upper_assembly_serial_number"),
	INPUT_VOLTAGE_RANGE("input_voltage_range"),
	CONSUMPTION(""),
	CREATE_DATE("create_date");

	private String columnName;

	/**
	 * Instantiates a new light column enum.
	 *
	 * @param column the column
	 */
	private LightColumnEnum(String column)
	{
		columnName = column;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columnName;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the light column enum
	 */
	public static LightColumnEnum enumForValue(String value)
	{
		for (LightColumnEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}
}
