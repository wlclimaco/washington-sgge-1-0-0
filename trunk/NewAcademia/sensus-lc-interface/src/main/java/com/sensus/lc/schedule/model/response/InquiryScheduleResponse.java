package com.sensus.lc.schedule.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;

/**
 * The Class InquiryScheduleResponse.
 */
public class InquiryScheduleResponse extends InquiryResponse
{

	/** The schedules. */
	@XmlElement(nillable = true)
	private List<? extends Schedule> schedules = new ArrayList<Schedule>();

	/**
	 * Gets the offset schedules.
	 * 
	 * @return the offset schedules
	 */
	@SuppressWarnings("unchecked")
	public List<OffsetSchedule> getOffsetSchedules()
	{
		return (List<OffsetSchedule>)CollectionUtils.select(getSchedules(), new Predicate()
		{
			@Override
			public boolean evaluate(Object schedule)
			{
				if (schedule == null)
				{
					return false;
				}

				return OffsetSchedule.class.isAssignableFrom(schedule.getClass());
			}
		});
	}

	/**
	 * Gets the event schedules.
	 * 
	 * @return the event schedules
	 */
	@SuppressWarnings("unchecked")
	public List<EventSchedule> getEventSchedules()
	{
		return (List<EventSchedule>)CollectionUtils.select(getSchedules(), new Predicate()
		{
			@Override
			public boolean evaluate(Object schedule)
			{
				if (schedule == null)
				{
					return false;
				}

				return EventSchedule.class.isAssignableFrom(schedule.getClass());
			}
		});
	}

	/**
	 * Gets the schedules.
	 * 
	 * @return the schedules
	 */
	@SuppressWarnings("unchecked")
	public List<Schedule> getSchedules()
	{
		return (List<Schedule>)schedules;
	}

	/**
	 * Sets the schedules.
	 * 
	 * @param schedules the new schedules
	 */
	public void setSchedules(List<Schedule> schedules)
	{
		this.schedules = schedules;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setSchedules(new ArrayList<Schedule>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryScheduleResponse [getOffsetSchedules()=" + getOffsetSchedules() + ", getEventSchedules()="
				+ getEventSchedules() + ", getSchedules()=" + getSchedules() + ", toString()=" + super.toString() + "]";
	}
}
