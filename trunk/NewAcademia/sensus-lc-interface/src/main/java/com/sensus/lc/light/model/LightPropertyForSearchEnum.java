package com.sensus.lc.light.model;

/**
 * The Enum LightPropertyForSearchEnum.
 *
 * @author QAT Brazil
 *
 */
public enum LightPropertyForSearchEnum
{
	/** The RNI ID. */
	RNI_ID("RNI_id"),

	/** POLE_ID. */
	POLE_ID("POLE_ID"),

	/** The EVEN t_ id. */
	EVENT_ID("EVENT_ID"),

	/** The ACTION ID. */
	ACTION_ID("ACTION_ID"),

	/** The ACTION NAME. */
	ACTION_NAME("ACTION_NAME");

	/** The colum name. */
	private String propertyName;

	/**
	 * Instantiates a new order by enum.
	 *
	 * @param columNameInd the colum name ind
	 */
	private LightPropertyForSearchEnum(String columNameInd)
	{
		propertyName = columNameInd;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return propertyName;
	}

	/**
	 * Enum for value.
	 *
	 * @param value the value
	 * @return the property enum
	 */
	public static LightPropertyForSearchEnum enumForValue(String value)
	{
		for (LightPropertyForSearchEnum e : values())
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
		LightPropertyForSearchEnum[] enums = LightPropertyForSearchEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LightPropertyForSearchEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}
}
