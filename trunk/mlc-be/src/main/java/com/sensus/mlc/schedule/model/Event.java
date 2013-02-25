package com.sensus.mlc.schedule.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;

/**
 * * The Model Object Event.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class Event extends SensusModel
{
	/** The days. */
	private List<DaysOfWeekEnum> days;

	/** The id. */
	private Integer id;

	/** The time. */
	private Date time;

	/** The intensity. */
	private Integer intensity;

	/** The blink status. */
	private LightBlinkEnum lightBlinkEnum;

	/**
	 * Instantiates a new event.
	 */
	public Event()
	{
	}

	/**
	 * Instantiates a new event.
	 * 
	 * @param eventIdParam the event id param
	 * @param timeParam the time param
	 * @param daysParam the days param
	 * @param intensityParam the intensity param
	 */
	public Event(Integer eventIdParam, Date timeParam, List<DaysOfWeekEnum> daysParam,
			Integer intensityParam)
	{
		setId(eventIdParam);
		setTime(timeParam);
		setDays(daysParam);
		setIntensity(intensityParam);
	}

	/**
	 * Gets the days.
	 * 
	 * @return the days
	 */
	public List<DaysOfWeekEnum> getDays()
	{
		return days;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getTime()
	{
		return time;
	}

	/**
	 * Sets the days.
	 * 
	 * @param dayList the new days
	 */
	public void setDays(List<DaysOfWeekEnum> dayList)
	{
		days = dayList;
	}

	/**
	 * Sets the id.
	 * 
	 * @param eventId the new id
	 */
	public void setId(Integer eventId)
	{
		id = eventId;
	}

	/**
	 * Sets the time.
	 * 
	 * @param timeDate the new time
	 */
	public void setTime(Date timeDate)
	{
		time = timeDate;
	}

	/**
	 * Sets the time.
	 * *Pattern required: h:mma
	 * (regex: \d\:\d{2}[ap]m)
	 * 
	 * @param timeDate the new time
	 */
	public void setTimeJson(String timeDate)
	{
		if ((timeDate == null) || !timeDate.matches("\\d?\\d\\:\\d{2}[ap]m"))
		{
			return;
		}

		String[] timeSplited = timeDate.split(":");
		int hour = Integer.parseInt(timeSplited[0]);
		int minute = Integer.parseInt(timeSplited[1].substring(0, 2));
		int amPm = 0;

		if ("pm".equals(timeSplited[1].toLowerCase().substring(2)))
		{
			amPm = 1;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.AM_PM, amPm);
		calendar.set(Calendar.SECOND, 0);

		time = calendar.getTime();
	}

	/**
	 * Sets the days value.
	 * 
	 * @param daysValue the new days value
	 */
	public void setDaysValue(List<Integer> daysValue)
	{
		days = new ArrayList<DaysOfWeekEnum>();
		for (Integer dayOfWeek : daysValue)
		{
			days.add(DaysOfWeekEnum.enumForValue(dayOfWeek));
		}
	}

	/**
	 * Gets the intensity.
	 * 
	 * @return the intensity
	 */
	public Integer getIntensity()
	{
		return intensity;
	}

	/**
	 * Sets the intensity.
	 * 
	 * @param intensity the new intensity
	 */
	public void setIntensity(Integer intensity)
	{
		this.intensity = intensity;
	}

	/**
	 * Gets the light blink enum.
	 * 
	 * @return the light blink enum
	 */
	public LightBlinkEnum getLightBlinkEnum()
	{
		return lightBlinkEnum;
	}

	/**
	 * Sets the light blink enum.
	 * 
	 * @param lightBlinkEnum the new light blink enum
	 */
	public void setLightBlinkEnum(LightBlinkEnum lightBlinkEnum)
	{
		this.lightBlinkEnum = lightBlinkEnum;
	}

	/**
	 * Sets the light blink enum value.
	 * 
	 * @param blink the new light blink enum value
	 */
	public void setLightBlinkEnumValue(Integer blink)
	{
		lightBlinkEnum = LightBlinkEnum.enumForValue(blink);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Event [getDays()=" + getDays() + ", getId()=" + getId() + ", getTime()=" + getTime()
				+ ", getIntensity()=" + getIntensity() + ", getLightBlinkEnum()=" + getLightBlinkEnum()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
