package com.sensus.lc.communication.model;

import com.sensus.common.model.SensusModel;

/**
 * Model object for alert email CSV.
 */
@SuppressWarnings("serial")
public class LightAlertEmail extends SensusModel
{
	/**
	 * Attributes.
	 */
	private String poleId;
	private String flexNetId;
	private String address;
	private String city;
	private String alertType;
	private String alertSubType;
	private String messageDate;
	private String voltage;
	private String current;
	private String lifeCycleState;

	/**
	 * @return the poleId
	 */
	public String getPoleId()
	{
		return poleId;
	}

	/**
	 * @param poleId the poleId to set
	 */
	public void setPoleId(String poleId)
	{
		this.poleId = poleId;
	}

	/**
	 * @return the flexNetId
	 */
	public String getFlexNetId()
	{
		return flexNetId;
	}

	/**
	 * @param flexNetId the flexNetId to set
	 */
	public void setFlexNetId(String flexNetId)
	{
		this.flexNetId = flexNetId;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the alertType
	 */
	public String getAlertType()
	{
		return alertType;
	}

	/**
	 * @param alertType the alertType to set
	 */
	public void setAlertType(String alertType)
	{
		this.alertType = alertType;
	}

	/**
	 * @return the alertSubType
	 */
	public String getAlertSubType()
	{
		return alertSubType;
	}

	/**
	 * @param alertSubType the alertSubType to set
	 */
	public void setAlertSubType(String alertSubType)
	{
		this.alertSubType = alertSubType;
	}

	/**
	 * @return the messageDate
	 */
	public String getMessageDate()
	{
		return messageDate;
	}

	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(String messageDate)
	{
		this.messageDate = messageDate;
	}

	/**
	 * @return the voltage
	 */
	public String getVoltage()
	{
		return voltage;
	}

	/**
	 * @param voltage the voltage to set
	 */
	public void setVoltage(String voltage)
	{
		this.voltage = voltage;
	}

	/**
	 * @return the current
	 */
	public String getCurrent()
	{
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(String current)
	{
		this.current = current;
	}

	/**
	 * @return the lifeCycleState
	 */
	public String getLifeCycleState()
	{
		return lifeCycleState;
	}

	/**
	 * @param lifeCycleState the lifeCycleState to set
	 */
	public void setLifeCycleState(String lifeCycleState)
	{
		this.lifeCycleState = lifeCycleState;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightAlertEmail [poleId=" + poleId + ", flexNetId=" + flexNetId + ", address=" + address + ", city="
				+ city + ", alertType=" + alertType + ", alertSubType=" + alertSubType + ", messageDate=" + messageDate
				+ ", voltage=" + voltage + ", current=" + current + ", lifeCycleState=" + lifeCycleState + "]";
	}

}
