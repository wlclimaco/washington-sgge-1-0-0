package com.sensus.dm.common.base.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum SearchTypeEnum.
 */
public enum SearchTypeEnum implements IStringEnum
{

	/** The ACTIO n_ name. */
	ACTION_NAME("ACTION_NAME"),

	/** The ACTIO n_ id. */
	ACTION_ID("ACTION_ID"),

	/** The ID. */
	ID("ID"),

	/** The DEVIC e_ id. */
	DEVICE_ID("DEVICE_ID"),

	/** The EVEN t_ id. */
	EVENT_ID("EVENT_ID"),

	/** The EVEN t_ name. */
	EVENT_NAME("EVENT_NAME"),

	/** The METE r_ id. */
	METER_ID("METER_ID"),

	/** The NETWORK_ADDRESS. */
	NETWORK_ADDRESS("NETWORK_ADDRESS"),

	/** The FLEXNE t_ id. */
	FLEXNET_ID("FLEXNET_ID"),

	/** The PREMIS e_ id. */
	PREMISE_ID("PREMISE_ID"),

	/** The ADDRESS. */
	ADDRESS("ADDRESS");

	/** The action type. */
	private String value;

	/**
	 * Instantiates a new ActionType ind enum.
	 * 
	 * @param actionTypeInd the actionType ind
	 */
	private SearchTypeEnum(String actionTypeInd)
	{
		value = actionTypeInd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public String getValue()
	{
		return value;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the search type enum
	 */
	public static SearchTypeEnum enumForValue(String value)
	{
		for (SearchTypeEnum e : values())
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
		SearchTypeEnum[] enums = SearchTypeEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (SearchTypeEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return null;
	}
}
