package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents a semi-monthly frequency based event (twice a month).<br>
 * <p>
 * It is important to note that a semi-monthly frequency based event will always have a
 * {@link FrequencyCodeEnum#SEMI_MONTHLY} associated with it.
 */
@SuppressWarnings("serial")
public class FrequencyEventSemiMonthly extends FrequencyBasedEvent
{
	/** The first day of the month the event occurs. */
	private Integer firstDayOfMonth;

	/** The second day of the month the event occurs. */
	private Integer secondDayOfMonth;

	/** The frequency code for a semi-monthly event. It is read only, thus no setter method. */
	private FrequencyCodeEnum frequencyCode;

	/**
	 * Default Constructor.
	 */
	public FrequencyEventSemiMonthly()
	{
		super();

		frequencyCode = FrequencyCodeEnum.SEMI_MONTHLY;
	}

	/**
	 * Get the first day of the month. This Integer value will correspond to the {@link java.util.Calendar#DAY_OF_MONTH}
	 * , and the SendSolv system interprets the same way.
	 *
	 * @return The first day of the month.
	 */
	public Integer getFirstDayOfMonth()
	{
		return firstDayOfMonth;
	}

	/**
	 * Set the first day of the month. This Integer value will correspond to the {@link java.util.Calendar#DAY_OF_MONTH}
	 * , and the SendSolv system interprets the same way.
	 *
	 * @param firstDayOfMonth The first day of the month to set.
	 */
	public void setFirstDayOfMonth(Integer firstDayOfMonth)
	{
		this.firstDayOfMonth = firstDayOfMonth;
	}

	/**
	 * Get the second day of the month. This Integer value will correspond to the {@link Calendar#DAY_OF_MONTH}, and the
	 * SendSolv system interprets the same way.
	 *
	 * @return The second day of the month.
	 */
	public Integer getSecondDayOfMonth()
	{
		return secondDayOfMonth;
	}

	/**
	 * Set the second day of the month. This Integer value will correspond to the {@link Calendar#DAY_OF_MONTH}, and the
	 * SendSolv system interprets the same way.
	 *
	 * @param secondDayOfMonth The second day of the month to set.
	 */
	public void setSecondDayOfMonth(Integer secondDayOfMonth)
	{
		this.secondDayOfMonth = secondDayOfMonth;
	}

	/**
	 * Get the frequency code identifying the frequency. This method will always return
	 * {@link FrequencyCodeEnum#SEMI_MONTHLY}. This guarantees us that the correct frequency code is assigned to this
	 * class.
	 *
	 * @return The frequency code for a semi-monthly event.
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
		return "FrequencyEventSemiMonthly [getFirstDayOfMonth()=" + getFirstDayOfMonth() + ", getSecondDayOfMonth()="
				+ getSecondDayOfMonth() + ", getFrequencyCode()=" + getFrequencyCode() + ", toString()="
				+ super.toString() + "]";
	}
}