package com.sensus.lc.process.model;

import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.IIntegerEnum;

/**
 * The Enum LCActionCategoryEnum.
 */
public enum LCActionCategoryEnum implements IIntegerEnum
{

	/** The control lights. */
	CONTROL_LIGHTS(1, "Dim", "Blink", "intensity", "Turn on", "Turn off"),

	/** The manage lights. */
	MANAGE_LIGHTS(2, "Locked", "Update Light Status", "update_light", "light_status", "Reset", "Update Pole",
			"Configuration", "Clear Override",
			"Update Light", "Dimming", "Update lat", "update_lat", "Protected", "Clear", "Alarm", "Warning", "Active"),

	/** The manage groups. */
	MANAGE_GROUPS(3, "Group", "grp"),

	/** The manage tags. */
	MANAGE_TAGS(4, "Tag"),

	/** The manage schedules. */
	MANAGE_SCHEDULES(5, "Schedule"),

	/** The unknown. */
	UNKNOWN(6);

	/** The lc action category. */
	private Integer lcActionCategory;

	/** The action descriptions. */
	private List<String> actionDescriptions;

	/**
	 * Instantiates a new lC action category enum.
	 * 
	 * @param lcActionCategoryInd the lc action category ind
	 */
	private LCActionCategoryEnum(Integer lcActionCategoryInd, String... actions)
	{
		lcActionCategory = lcActionCategoryInd;
		actionDescriptions = Arrays.asList(actions);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IIntegerEnum#getValue()
	 */
	@Override
	public Integer getValue()
	{
		return lcActionCategory;
	}

	/**
	 * Gets the action descriptions.
	 * 
	 * @return the action descriptions
	 */
	public List<String> getActionDescriptions()
	{
		return actionDescriptions;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the lC action category enum
	 */
	public static LCActionCategoryEnum enumForValue(Integer value)
	{
		for (LCActionCategoryEnum e : values())
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
		LCActionCategoryEnum[] enums = LCActionCategoryEnum.class.getEnumConstants();

		String comma = "";
		StringBuilder enumValue = new StringBuilder();
		for (LCActionCategoryEnum i : enums)
		{
			enumValue.append(comma + i.getValue());
			comma = ", ";
		}
		return enumValue.toString();
	}

}