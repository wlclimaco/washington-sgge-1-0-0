package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * Class represents a frequency based event.<br>
 * <p>
 * It is an abstract class which must be extended to be used. Each extending class must implement the getFrequencyCode()
 * method so that the extending class can be correctly identified when saving to a data store.
 */
@SuppressWarnings("serial")
public class FrequencyBasedEvent extends QATModelOL
{
	/** The effective date that events start happening. */
	private Long effectiveStartDate;

	/** The list of dates for the events to occur. */
	private List<FrequencyBasedEventCalendar> frequencyBasedEventCalendarList;

	/** The id of the event. */
	private Integer id;

	/** The id of the location associated with this event. */
	private Integer locationId;

	/** The name of the location. Carried as a helper for the UI. */
	private transient String locationName;

	/** The type of the frequency. */
	private FrequencyBasedEventTypeEnum type;

	/**
	 * Default Constructor.
	 */
	public FrequencyBasedEvent()
	{
		super();
	}

	/**
	 * Get the effective date that events start happening. If this date has a time portion, it will be removed. This
	 * attribute is only a date.
	 *
	 * @return The date.
	 */
	public Long getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	/**
	 * Set the effective date that events start happening.
	 *
	 * @param effectiveStartDate The date to set
	 */
	public void setEffectiveStartDate(Long effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Get the list of dates for the events to occur.
	 *
	 * @return The list of dates.
	 */
	public List<FrequencyBasedEventCalendar> getFrequencyBasedEventCalendarList()
	{
		if (ValidationUtil.isNull(frequencyBasedEventCalendarList))
		{
			setFrequencyBasedEventCalendarList(new ArrayList<FrequencyBasedEventCalendar>());
		}

		return frequencyBasedEventCalendarList;
	}

	/**
	 * Set the list of dates for the events to occur.
	 *
	 * @param frequencyBasedEventCalendarList The list of dates to set
	 */
	public void setFrequencyBasedEventCalendarList(List<FrequencyBasedEventCalendar> frequencyBasedEventCalendarList)
	{
		this.frequencyBasedEventCalendarList = frequencyBasedEventCalendarList;
	}

	/**
	 * Get the frequency code identifying the frequency. This method must be implemented by any extending class. Also,
	 * it is a ready only value, thus no setter methods.
	 *
	 * @return The frequency code.
	 */
	public FrequencyCodeEnum getFrequencyCode()
	{
		return null;
	}

	/**
	 * Get the enumeration value of the frequency code identifying the frequency.
	 *
	 * @return The value.
	 */
	public Integer getFrequencyCodeValue()
	{
		if (getFrequencyCode() == null)
		{
			return null;
		}

		return getFrequencyCode().getValue();
	}

	/**
	 * Get the id of the event.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the event.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the id of the location associated with this event.
	 *
	 * @return The location id.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Set the id of the location associated with this event.
	 *
	 * @param locationId The location id to set.
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/**
	 * Get the name of the location.
	 *
	 * @return The location name.
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * Set the name of the location.
	 *
	 * @param locationName The location name to set.
	 */
	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	/**
	 * Get the type of the frequency.
	 *
	 * @return The type.
	 */
	public FrequencyBasedEventTypeEnum getType()
	{
		return type;
	}

	/**
	 * Get the enumeration value of the the type of the frequency.
	 *
	 * @return The value.
	 */
	public Integer getTypeValue()
	{
		if (getType() == null)
		{
			return null;
		}

		return getType().getValue();
	}

	/**
	 * Set the type of the frequency.
	 *
	 * @param type The type to set.
	 */
	public void setType(FrequencyBasedEventTypeEnum type)
	{
		this.type = type;
	}

	/**
	 * Set the value of the type of the frequency.
	 *
	 * @param typeValue The enumeration value for type of the frequency.
	 */
	public void setTypeValue(Integer typeValue)
	{
		setType(FrequencyBasedEventTypeEnum.enumForValue(typeValue));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FrequencyBasedEvent [effectiveStartDate=" + effectiveStartDate + ", frequencyBasedEventCalendarList="
				+ frequencyBasedEventCalendarList + ", id=" + id + ", locationId=" + locationId + ", type=" + type
				+ ", getEffectiveStartDate()=" + getEffectiveStartDate() + ", getFrequencyBasedEventCalendarList()="
				+ getFrequencyBasedEventCalendarList() + ", getFrequencyCode()=" + getFrequencyCode()
				+ ", getFrequencyCodeValue()=" + getFrequencyCodeValue() + ", getId()=" + getId()
				+ ", getLocationId()=" + getLocationId() + ", getLocationName()=" + getLocationName() + ", getType()="
				+ getType() + ", getTypeValue()=" + getTypeValue() + ", toString()=" + super.toString() + "]";
	}
}