package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum LightOrderByEnum.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public enum LightOrderByEnum implements IStringEnum
{
	/** The Light ID. */
	LIGHT_ID("light.light_id"),

	/** The RNI ID. */
	RNI_ID("RNI_id"),

	/** STATUS. */
	STATUS("SS.LABEL_KEY"),

	/** DATE_ADDED. */
	DATE_ADDED("13"),

	/** LAMP_TYPE. */
	LAMP_TYPE("18"),

	/** CITY. */
	CITY_NAME("29"),

	/** The PROTECTED. */
	PROTECTED("protected"),

	/** POLE_ID. */
	POLE_ID("12"),

	/** The BUL b_ seria l_ number. */
	BULB_SERIAL_NUMBER("47"),

	/** The COLO r_ temperature. */
	COLOR_TEMPERATURE("9"),

	/** The FIRMWAR e_ version. */
	FIRMWARE_VERSION("15"),

	/** The HOUSIN g_ color. */
	HOUSING_COLOR("75"),

	/** The LIGH t_ drive r_ seria l_ number. */
	SERIAL_NUMBER("5"),

	/** The LOWE r_ assembl y_ seria l_ number. */
	LOWER_ASSEMBLY_SERIAL_NUMBER("45"),

	/** The MA p_ it. */
	MAP_IT("MAP_IT"),

	/** The MODE l_ number. */
	MODEL_NUMBER("44"),

	/** The UPPE r_ assembl y_ seria l_ number. */
	UPPER_ASSEMBLY_SERIAL_NUMBER("46"),

	/** The VOLTAG e_ range. */
	INPUT_VOLTAGE_RANGE("8"),

	/** The ecomode. */
	ECOMODE("ecomode");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new order by enum.
	 * 
	 * @param columNameInd the colum name ind
	 */
	private LightOrderByEnum(String columNameInd)
	{
		columName = columNameInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return columName;
	}

}
