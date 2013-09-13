package com.sensus.dm.elec.device.model;

import com.sensus.common.model.IStringEnum;

/**
 * The Enum PeakDemandOrderByEnum.
 */
public enum PeakDemandOrderByEnum implements IStringEnum
{

	/** The peak demand. */
	PEAK_DEMAND("peak_demand"),

	/** The reset time. */
	RESET_TIME("reset_time"),

	/** The peak time. */
	PEAK_TIME("peak_time"),

	/** The reading date. */
	READING_DATE("reading_date"),

	/** The demand reset count. */
	DEMAND_RESET_COUNT("demand_reset_count"),

	/** The tier. */
	TIER("tier");

	/** The colum name. */
	private String columName;

	/**
	 * Instantiates a new peak demand order by enum.
	 * 
	 * @param columNameParam the colum name param
	 */
	private PeakDemandOrderByEnum(String columNameParam)
	{
		columName = columNameParam;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IStringEnum#getValue()
	 */
	@Override
	public String getValue()
	{
		return columName;
	}

	/**
	 * Enum for value.
	 * 
	 * @param value the value
	 * @return the peak demand order by enum
	 */
	public static PeakDemandOrderByEnum enumForValue(String value)
	{
		for (PeakDemandOrderByEnum e : values())
		{
			if (e.getValue().equals(value))
			{
				return e;
			}
		}

		return null;
	}
}
