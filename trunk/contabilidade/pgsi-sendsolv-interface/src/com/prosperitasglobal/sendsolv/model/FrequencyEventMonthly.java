package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents a monthly frequency based event (once a month).<br>
 * <p>
 * It is important to note that a monthly frequency based event will always have a {@link FrequencyCodeEnum#MONTHLY}
 * associated with it.
 */
@SuppressWarnings("serial")
public class FrequencyEventMonthly extends FrequencyBasedEvent
{
	/** The day of the month the event occurs. */
	private Integer dayOfMonth;

	/** The frequency code for a monthly event. It is read only, thus no setter method. */
	private FrequencyCodeEnum frequencyCode;

	/**
	 * Default Constructor.
	 */
	public FrequencyEventMonthly()
	{
		super();

		frequencyCode = FrequencyCodeEnum.MONTHLY;
	}

	/**
	 * Get the day of the month. This Integer value will correspond to the {@link java.util.Calendar#DAY_OF_MONTH}, and
	 * the SendSolv system interprets the same way.
	 *
	 * @return The day of the month.
	 */
	public Integer getDayOfMonth()
	{
		return dayOfMonth;
	}

	/**
	 * Set the day of the month. This Integer value will correspond to the {@link java.util.Calendar#DAY_OF_MONTH}, and
	 * the SendSolv system interprets the same way.
	 *
	 * @param dayOfMonth The day of the month to set.
	 */
	public void setDayOfMonth(Integer dayOfMonth)
	{
		this.dayOfMonth = dayOfMonth;
	}

	/**
	 * Get the frequency code identifying the frequency. This method will always return
	 * {@link FrequencyCodeEnum#MONTHLY}. This guarantees us that the correct frequency code is assigned to this
	 * class.
	 *
	 * @return The frequency code for a monthly even.
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
		return "FrequencyEventMonthly [getDayOfMonth()=" + getDayOfMonth() + ", getFrequencyCode()="
				+ getFrequencyCode() + ", toString()=" + super.toString() + "]";
	}
}