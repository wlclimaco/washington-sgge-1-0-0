package com.sensus.lc.analytics.model;

import com.sensus.common.model.IIntegerEnum;

public enum AnalyticsRangeDateEnum implements IIntegerEnum
{

	/** The one day. */
	ONE_DAY(0),

	/** The three days. */
	THREE_DAYS(1),

	/** The week. */
	WEEK(2),

	/** The one month. */
	ONE_MONTH(3),

	/** The three month. */
	THREE_MONTH(4),

	/** The ytd. */
	YTD(5),

	/** The one year. */
	ONE_YEAR(6);

	/** The range date id. */
	private Integer rangeDateId;

	/**
	 * Instantiates a new analytics range date enum.
	 * 
	 * @param id the id
	 */
	private AnalyticsRangeDateEnum(Integer id)
	{
		rangeDateId = id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Override
	public Integer getValue()
	{
		return rangeDateId;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the analytics range date enum
	 */
	public static AnalyticsRangeDateEnum enumForValue(Integer value)
	{
		for (AnalyticsRangeDateEnum type : values())
		{
			if (type.getValue().equals(value))
			{
				return type;
			}
		}

		return null;
	}

}
