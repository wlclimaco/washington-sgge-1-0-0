package com.sensus.mlc.schedule.model;

import java.util.List;

/**
 * The Class EventSchedule.
 */
@SuppressWarnings("serial")
public class EventSchedule extends Schedule
{

	/** The events. */
	private List<Event> events;

	/**
	 * Instantiates a new event schedule.
	 */
	public EventSchedule()
	{
		setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
	}

	/**
	 * Instantiates a new event schedule.
	 * 
	 * @param eventsParam the events param
	 */
	public EventSchedule(List<Event> eventsParam)
	{
		setEvents(eventsParam);
	}

	/**
	 * Gets the events.
	 * 
	 * @return the events
	 */
	public List<Event> getEvents()
	{
		return events;
	}

	/**
	 * Sets the events.
	 * 
	 * @param events the new events
	 */
	public void setEvents(List<Event> events)
	{
		this.events = events;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.model.Schedule#toString()
	 */
	@Override
	public String toString()
	{
		return "EventSchedule [getEvents()=" + getEvents() + ", getDescription()=" + getDescription() + ", getId()="
				+ getId() + ", getLights()=" + getLights() + ", getName()=" + getName() + ", getSmartPointCount()="
				+ getSmartPointCount() + ", getScheduleTypeEnum()=" + getScheduleTypeEnum()
				+ ", getScheduleTypeValue()=" + getScheduleTypeValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
