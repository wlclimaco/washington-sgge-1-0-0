package com.sensus.dm.common.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class AlarmsTypesCount.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class AlarmsTypesCount extends SensusModel
{

	/** The alarm. */
	private AlarmEnum alarm;

	/** The amount. */
	private Integer amount;

	/**
	 * Instantiates a new alarms types count.
	 */
	public AlarmsTypesCount()
	{

	}

	/**
	 * Instantiates a new alarms types count.
	 * 
	 * @param alarmEnumParam the alarm enum param
	 * @param amountParam the amount param
	 */
	public AlarmsTypesCount(AlarmEnum alarmEnumParam, Integer amountParam)
	{
		setAlarm(alarmEnumParam);
		setAmount(amountParam);
	}

	/**
	 * Gets the alarm.
	 * 
	 * @return the alarm
	 */
	public AlarmEnum getAlarm()
	{
		return alarm;
	}

	/**
	 * Sets the alarm.
	 * 
	 * @param alarm the new alarm
	 */
	public void setAlarm(AlarmEnum alarm)
	{
		this.alarm = alarm;
	}

	/**
	 * Gets the alarm value.
	 * 
	 * @return the alarm value
	 */
	public String getAlarmValue()
	{
		if (getAlarm() != null)
		{
			return getAlarm().getValue();
		}

		return null;
	}

	/**
	 * Sets the alarm value.
	 * 
	 * @param alarmEnumParam the new alarm value
	 */
	public void setAlarmValue(String alarmEnumParam)
	{
		setAlarm(AlarmEnum.enumForValue(alarmEnumParam));
	}

	/**
	 * Gets the amount.
	 * 
	 * @return the amount
	 */
	public Integer getAmount()
	{
		return amount;
	}

	/**
	 * Sets the amount.
	 * 
	 * @param amount the new amount
	 */
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "AlarmsTypesCount [getAlarm()=" + getAlarm() + ", getAlarmValue()=" + getAlarmValue() + ", getAmount()="
				+ getAmount() + "]";
	}
}
