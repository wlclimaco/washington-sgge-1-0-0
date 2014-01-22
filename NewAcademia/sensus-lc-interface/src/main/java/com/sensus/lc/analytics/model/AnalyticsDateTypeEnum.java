package com.sensus.lc.analytics.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum AnalyticsDateTypeEnum.
 */
public enum AnalyticsDateTypeEnum implements IStringEnum
{

	/** The ONE_DAY. */
	ONE_DAY("1d"),

	/** The THREE_DAYS. */
	THREE_DAYS("3d"),

	/** The ONE_WEEK. */
	ONE_WEEK("1w"),

	/** The ONE_MONTH. */
	ONE_MONTH("1m"),

	/** The THREE_MONTH. */
	THREE_MONTHS("3m"),

	/** The YEAR_TO_DATE. */
	YEAR_TO_DATE("ytd"),

	/** The ONE_YEAR. */
	ONE_YEAR("1y");

	/** The analytic type id. */
	private String analyticsDateTypeId;

	/**
	 * Instantiates a new analytics date type enum.
	 * 
	 * @param typeId the analytics date type id
	 */
	private AnalyticsDateTypeEnum(String typeId)
	{
		this.analyticsDateTypeId = typeId;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return this.analyticsDateTypeId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the analytics date type enum
	 */
	public static AnalyticsDateTypeEnum enumForValue(String value)
	{
		for (AnalyticsDateTypeEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}

}
