package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents a biweekly frequency based event (every other week).<br>
 * <p>
 * It is important to note that a biweekly frequency based event will always have a {@link FrequencyCodeEnum#BI_WEEKLY}
 * associated with it.
 */
@SuppressWarnings("serial")
public class FrequencyEventBiWeekly extends FrequencyBasedEvent
{
	/** The day of the week. */
	private Integer dayOfWeek;

	/** The frequency code for a biweekly event. It is read only, thus no setter method. */
	private FrequencyCodeEnum frequencyCode;

	/**
	 * Default Constructor.
	 */
	public FrequencyEventBiWeekly()
	{
		super();

		frequencyCode = FrequencyCodeEnum.BI_WEEKLY;
	}

	/**
	 * Get the day of the week. This Integer value will correspond to {@link java.util.Calendar#DAY_OF_WEEK}. The valid
	 * values will be {@link java.util.Calendar#SUNDAY}, {@link java.util.Calendar#MONDAY},
	 * {@link java.util.Calendar#TUESDAY}, {@link java.util.Calendar#WEDNESDAY}, {@link java.util.Calendar#THURSDAY},
	 * {@link java.util.Calendar#FRIDAY}, {@link java.util.Calendar#SATURDAY}, and the SendSolv system will
	 * interpret them the same way.
	 *
	 * @return The day of the week.
	 */
	public Integer getDayOfWeek()
	{
		return dayOfWeek;
	}

	/**
	 * Set the day of the week. This Integer value will correspond to {@link java.util.Calendar#DAY_OF_WEEK}. The valid
	 * values will be {@link java.util.Calendar#SUNDAY}, {@link java.util.Calendar#MONDAY},
	 * {@link java.util.Calendar#TUESDAY}, {@link java.util.Calendar#WEDNESDAY}, {@link java.util.Calendar#THURSDAY},
	 * {@link java.util.Calendar#FRIDAY}, {@link java.util.Calendar#SATURDAY}, and the SendSolv system will interpret
	 * them the same way.
	 *
	 * @param dayOfWeek The day of the week to set.
	 */
	public void setDayOfWeek(Integer dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Get the frequency code identifying the frequency. This method will always return
	 * {@link FrequencyCodeEnum#BI_WEEKLY}. This guarantees us that the correct frequency code is assigned to this
	 * class.
	 *
	 * @return The frequency code for a biweekly event.
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
		return "FrequencyEventBiWeekly [getDayOfWeek()=" + getDayOfWeek() + ", getFrequencyCode()="
				+ getFrequencyCode() + ", toString()=" + super.toString() + "]";
	}
}