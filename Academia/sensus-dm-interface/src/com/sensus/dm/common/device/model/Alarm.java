package com.sensus.dm.common.device.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class Alarm.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class Alarm extends SensusModel
{

	/** The alarm enum. */
	private AlarmEnum alarmEnum;

	/** The alarm time. */
	private Date alarmTime;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new alarm.
	 */
	public Alarm()
	{

	}

	/**
	 * Instantiates a new alarm.
	 * 
	 * @param alarmEnumParam the alarm enum param
	 */
	public Alarm(AlarmEnum alarmEnumParam)
	{
		setAlarmEnum(alarmEnumParam);
	}

	/**
	 * Instantiates a new alarm.
	 * 
	 * @param alarmEnumParam the alarm enum param
	 * @param alarmTimeParam the alarm time param
	 */
	public Alarm(AlarmEnum alarmEnumParam, Date alarmTimeParam)
	{
		this(alarmEnumParam);
		setAlarmTime(alarmTimeParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the alarm enum.
	 * 
	 * @return the alarm enum
	 */
	public AlarmEnum getAlarmEnum()
	{
		return alarmEnum;
	}

	/**
	 * Sets the alarm enum.
	 * 
	 * @param alarmEnum the new alarm enum
	 */
	public void setAlarmEnum(AlarmEnum alarmEnum)
	{
		this.alarmEnum = alarmEnum;
	}

	/**
	 * Gets the alarm time.
	 * 
	 * @return the alarm time
	 */
	public Date getAlarmTime()
	{
		return alarmTime;
	}

	/**
	 * Sets the alarm time.
	 * 
	 * @param alarmTime the new alarm time
	 */
	public void setAlarmTime(Date alarmTime)
	{
		this.alarmTime = alarmTime;
	}

	/**
	 * Gets the alarm enum value.
	 * 
	 * @return the alarm enum value
	 */
	public String getAlarmEnumValue()
	{
		if (getAlarmEnum() != null)
		{
			return getAlarmEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the alarm enum value.
	 * 
	 * @param alarmEnumValue the new alarm enum value
	 */
	public void setAlarmEnumValue(String alarmEnumValue)
	{
		setAlarmEnum(AlarmEnum.enumForValue(alarmEnumValue));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Alarm [getAlarmEnum()=" + getAlarmEnum() + ", getAlarmTime()=" + getAlarmTime()
				+ ", getAlarmEnumValue()=" + getAlarmEnumValue() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
