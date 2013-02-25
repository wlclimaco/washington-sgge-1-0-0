package com.sensus.mlc.analytics.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum DashboardViewTypeEnum.
 */
public enum DashboardViewTypeEnum implements IStringEnum
{

	/** The TODAY. */
	TODAY("today"),

	/** The WEEK. */
	WEEK("week"),

	/** The MONTH. */
	MONTH("month");

	/** The view type id. */
	private String viewTypeId;

	/**
	 * Instantiates a new dashboard view type enum.
	 * 
	 * @param viewType the view type id
	 */
	private DashboardViewTypeEnum(String viewType)
	{
		this.viewTypeId = viewType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return viewTypeId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the dashboard view type enum
	 */
	public static DashboardViewTypeEnum enumForValue(String value)
	{
		for (DashboardViewTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

}
