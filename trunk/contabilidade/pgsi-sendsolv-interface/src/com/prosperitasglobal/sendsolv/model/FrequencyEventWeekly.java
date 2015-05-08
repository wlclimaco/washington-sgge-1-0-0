package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents a weekly frequency based event.<br>
 * <p>
 * It is important to note that a weekly frequency based event will always have a {@link FrequencyCodeEnum#WEEKLY}
 * associated with it.
 */
@SuppressWarnings("serial")
public class FrequencyEventWeekly extends FrequencyBasedEvent
{
	/** The day of the week. */
	private Integer dayOfWeek;

	/** The frequency code for a weekly event. It is read only, thus no setter method. */
	private FrequencyCodeEnum frequencyCode;

	/**
	 * Default Constructor.
	 */
	public FrequencyEventWeekly()
	{
		super();

		frequencyCode = FrequencyCodeEnum.WEEKLY;
	}

	/**
	 * Get the day of the week. This Integer value will correspond to the {@link java.util.Calendar#DAY_OF_WEEK}. The
	 * valid values will be {@link java.util.Calendar#SUNDAY}, {@link java.util.Calendar#MONDAY},
	 * {@link java.util.Calendar#TUESDAY}, {@link java.util.Calendar#WEDNESDAY}, {@link java.util.Calendar#THURSDAY},
	 * {@link java.util.Calendar#FRIDAY}, {@link java.util.Calendar#SATURDAY}, and the SendSolv system interprets the
	 * same way.
	 *
	 * @return The day of the week.
	 */
	public Integer getDayOfWeek()
	{
		return dayOfWeek;
	}

	/**
	 * Set the day of the week. This Integer value will correspond to the {@link java.util.Calendar#DAY_OF_WEEK}. The
	 * valid values will be {@link java.util.Calendar#SUNDAY}, {@link java.util.Calendar#MONDAY},
	 * {@link java.util.Calendar#TUESDAY}, {@link java.util.Calendar#WEDNESDAY}, {@link java.util.Calendar#THURSDAY},
	 * {@link java.util.Calendar#FRIDAY}, {@link java.util.Calendar#SATURDAY}, and the SendSolv system interprets the
	 * same way.
	 *
	 * @param dayOfWeek The day of the week to set.
	 */
	public void setDayOfWeek(Integer dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Get the frequency code identifying the frequency. This method will always return {@link FrequencyCodeEnum#WEEKLY}
	 * . This guarantees us that the correct frequency code is assigned to this class.
	 *
	 * @return The frequency code for a weekly event.
	 */
	@Override
	public FrequencyCodeEnum getFrequencyCode()
	{
		return frequencyCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FrequencyEventWeekly [getDayOfWeek()=" + getDayOfWeek() + ", getFrequencyCode()=" + getFrequencyCode()
				+ ", toString()=" + super.toString() + "]";
	}
}