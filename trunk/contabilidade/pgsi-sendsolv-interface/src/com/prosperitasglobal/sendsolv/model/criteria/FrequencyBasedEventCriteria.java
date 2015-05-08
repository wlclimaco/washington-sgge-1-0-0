package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventTypeEnum;

/**
 * Criteria member used for doing a lookup on a frequency based event in the SendSolv system.
 */
@SuppressWarnings("serial")
public class FrequencyBasedEventCriteria implements Serializable
{
	/** The id of a frequency based event. */
	private Integer id;

	/** The id of a location associated with an event. */
	private Integer locationId;

	/** The frequency based event type of a frequency. */
	private FrequencyBasedEventTypeEnum frequencyBasedEventtype;

	/**
	 * Default constructor.
	 */
	public FrequencyBasedEventCriteria()
	{
		super();
	}

	/**
	 * Get the frequency based event type associated with an event.
	 *
	 * @return The frequency based event type associated with an event.
	 */
	public FrequencyBasedEventTypeEnum getFrequencyBasedEventType()
	{
		return frequencyBasedEventtype;
	}

	/**
	 * Get the enumeration value of the the type of the frequency.
	 *
	 * @return The value.
	 */
	public Integer getFrequencyBasedEventTypeValue()
	{
		if (getFrequencyBasedEventType() == null)
		{
			return null;
		}

		return getFrequencyBasedEventType().getValue();
	}

	/**
	 * Set the frequency based event type associated with an event.
	 *
	 * @param frequencyBasedEventtype The frequency based event type associated with an event to set.
	 */
	public void setFrequencyBasedEventType(FrequencyBasedEventTypeEnum frequencyBasedEventtype)
	{
		this.frequencyBasedEventtype = frequencyBasedEventtype;
	}

	/**
	 * Set the value of the type of the frequency by value.
	 *
	 * @param typeValue The enumeration value for type of the frequency.
	 */
	public void setFrequencyBasedEventTypeValue(Integer typeValue)
	{
		setFrequencyBasedEventType(FrequencyBasedEventTypeEnum.enumForValue(typeValue));
	}

	/**
	 * Get the product plan id to use in the inquiry.
	 *
	 * @return The product plan id to use in the inquiry.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the product plan id to use in the inquiry.
	 *
	 * @param id The product plan id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the id of the location associated with an event.
	 *
	 * @return The id of a location associated with an event.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Set the id of the location associated with an event.
	 *
	 * @param locationId The id of the location associated with an event.
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FrequencyBasedEventCriteria [getFrequencyBasedEventType()=" + getFrequencyBasedEventType()
				+ ", getFrequencyBasedEventTypeValue()=" + getFrequencyBasedEventTypeValue() + ", getId()=" + getId()
				+ ", getLocationId()=" + getLocationId() + "]";
	}
}
