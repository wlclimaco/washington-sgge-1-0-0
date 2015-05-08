package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents a one day frequency based event.<br>
 * <p>
 * It is important to note that a one day frequency based event will always have a {@link FrequencyCodeEnum#ONE_DAY}
 * associated with it.
 */
@SuppressWarnings("serial")
public class FrequencyEventOneDay extends FrequencyBasedEvent
{
	/** The frequency code for a one day event. It is read only, thus no setter method. */
	private FrequencyCodeEnum frequencyCode;

	/**
	 * Default Constructor.
	 */
	public FrequencyEventOneDay()
	{
		super();

		frequencyCode = FrequencyCodeEnum.ONE_DAY;
	}

	/**
	 * Get the frequency code identifying the frequency. This method will always return
	 * {@link FrequencyCodeEnum#ONE_DAY}. This guarantees us that the correct frequency code is assigned to this class.
	 *
	 * @return The frequency code for a 1 day event.
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
		return "FrequencyEventOneDay [getFrequencyCode()=" + getFrequencyCode() + ", toString()=" + super.toString()
				+ "]";
	}
}