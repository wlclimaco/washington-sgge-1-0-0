package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModelOL;

/**
 * This class represents a date in the calendar that is used in the frequency based event.
 */
@SuppressWarnings("serial")
public class FrequencyBasedEventCalendar extends QATModelOL
{
	/** The date of the event. */
	private Long eventDate;

	/** The id of the {@link FrequencyBasedEvent} that is associated. */
	private Integer frequencyBasedEventId;

	/** The id of the calendar. */
	private Integer id;

	/**
	 * Default constructor.
	 */
	public FrequencyBasedEventCalendar()
	{
		super();
	}

	/**
	 * Get the date of the event. If this date has a time portion, it will be removed. This attribute is only a date.
	 *
	 * @return The date of the event.
	 */
	public Long getEventDate()
	{
		return eventDate;
	}

	/**
	 * Set the date of the event.
	 *
	 * @param eventDate The date of the event to set.
	 */
	public void setEventDate(Long eventDate)
	{
		this.eventDate = eventDate;
	}

	/**
	 * Get the id of the {@link FrequencyBasedEvent} that is associated.
	 *
	 * @return The id of the {@link FrequencyBasedEvent} that is associated.
	 */
	public Integer getFrequencyBasedEventId()
	{
		return frequencyBasedEventId;
	}

	/**
	 * Set the id of the {@link FrequencyBasedEvent} that is associated.
	 *
	 * @param frequencyBasedEventId The id of the {@link FrequencyBasedEvent} that is associated to set.
	 */
	public void setFrequencyBasedEventId(Integer frequencyBasedEventId)
	{
		this.frequencyBasedEventId = frequencyBasedEventId;
	}

	/**
	 * Get the id of the calendar.
	 *
	 * @return The id of the calendar.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the calendar.
	 *
	 * @param id The id of the calendar to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FrequencyBasedEventCalendar [getEventDate()=" + getEventDate() + ", getFrequencyBasedEventId()="
				+ getFrequencyBasedEventId() + ", getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}
}
