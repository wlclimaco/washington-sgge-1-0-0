package com.sensus.dm.elec.device.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LCMRelay.
 */
@SuppressWarnings("serial")
public class LCMRelay extends SensusModel
{

	/** The relay. */
	private Integer relay;

	/** The amp. */
	private Integer amp;

	/** The intended use. */
	private String intendedUse;

	/** The used. */
	private Boolean used;

	/** The start minutes. */
	private Integer startMinutes;

	/** The end minutes. */
	private Integer endMinutes;

	/** The enrollment code. */
	private Integer enrollmentCode;

	/** The device class. */
	private DeviceClassEnum deviceClass;

	/** The tamper detect timer. */
	private String tamperDetectTimer;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new lCM relay.
	 */
	public LCMRelay()
	{

	}

	/**
	 * Instantiates a new lCM relay.
	 * 
	 * @param relayParam the relay param
	 */
	public LCMRelay(Integer relayParam)
	{
		setRelay(relayParam);
	}

	/**
	 * Instantiates a new lCM relay.
	 * 
	 * @param relayParam the relay param
	 * @param ampParam the amp param
	 * @param intendedUseParam the intended use param
	 * @param usedParam the used param
	 */
	public LCMRelay(Integer relayParam, Integer ampParam, String intendedUseParam, Boolean usedParam)
	{
		setRelay(relayParam);
		setAmp(ampParam);
		setIntendedUse(intendedUseParam);
		setUsed(usedParam);
	}

	/**
	 * Instantiates a new lCM relay.
	 * 
	 * @param relayParam the relay param
	 * @param ampParam the amp param
	 * @param deviceClassParam the device class param
	 */
	public LCMRelay(Integer relayParam, Integer ampParam, DeviceClassEnum deviceClassParam)
	{
		setRelay(relayParam);
		setAmp(ampParam);
		setDeviceClass(deviceClassParam);
	}

	/**
	 * Instantiates a new lCM relay.
	 * 
	 * @param relayParam the relay param
	 * @param tamperDetectTimerParam the tamper detect timer param
	 */
	public LCMRelay(Integer relayParam, String tamperDetectTimerParam)
	{
		setRelay(relayParam);
		setTamperDetectTimer(tamperDetectTimerParam);

	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the relay.
	 * 
	 * @return the relay
	 */
	public Integer getRelay()
	{
		return relay;
	}

	/**
	 * Sets the relay.
	 * 
	 * @param relay the new relay
	 */
	public void setRelay(Integer relay)
	{
		this.relay = relay;
	}

	/**
	 * Gets the amp.
	 * 
	 * @return the amp
	 */
	public Integer getAmp()
	{
		return amp;
	}

	/**
	 * Sets the amp.
	 * 
	 * @param amp the new amp
	 */
	public void setAmp(Integer amp)
	{
		this.amp = amp;
	}

	/**
	 * Gets the intended use.
	 * 
	 * @return the intended use
	 */
	public String getIntendedUse()
	{
		return intendedUse;
	}

	/**
	 * Sets the intended use.
	 * 
	 * @param intendedUse the new intended use
	 */
	public void setIntendedUse(String intendedUse)
	{
		this.intendedUse = intendedUse;
	}

	/**
	 * Gets the used.
	 * 
	 * @return the used
	 */
	public Boolean getUsed()
	{
		return used;
	}

	/**
	 * Sets the used.
	 * 
	 * @param used the new used
	 */
	public void setUsed(Boolean used)
	{
		this.used = used;
	}

	/**
	 * Gets the start minutes.
	 * 
	 * @return the start minutes
	 */
	public Integer getStartMinutes()
	{
		return startMinutes;
	}

	/**
	 * Sets the start minutes.
	 * 
	 * @param startMinutes the new start minutes
	 */
	public void setStartMinutes(Integer startMinutes)
	{
		this.startMinutes = startMinutes;
	}

	/**
	 * Gets the end minutes.
	 * 
	 * @return the end minutes
	 */
	public Integer getEndMinutes()
	{
		return endMinutes;
	}

	/**
	 * Sets the end minutes.
	 * 
	 * @param endMinutes the new end minutes
	 */
	public void setEndMinutes(Integer endMinutes)
	{
		this.endMinutes = endMinutes;
	}

	/**
	 * Gets the enrollment code.
	 * 
	 * @return the enrollment code
	 */
	public Integer getEnrollmentCode()
	{
		return enrollmentCode;
	}

	/**
	 * Sets the enrollment code.
	 * 
	 * @param enrollmentCode the new enrollment code
	 */
	public void setEnrollmentCode(Integer enrollmentCode)
	{
		this.enrollmentCode = enrollmentCode;
	}

	/**
	 * Gets the device class.
	 * 
	 * @return the device class
	 */
	public DeviceClassEnum getDeviceClass()
	{
		return deviceClass;
	}

	/**
	 * Sets the device class.
	 * 
	 * @param deviceClass the new device class
	 */
	public void setDeviceClass(DeviceClassEnum deviceClass)
	{
		this.deviceClass = deviceClass;
	}

	/**
	 * Gets the device class value.
	 * 
	 * @return the device class value
	 */
	public Integer getDeviceClassValue()
	{
		if (getDeviceClass() != null)
		{
			return getDeviceClass().getValue();
		}

		return null;
	}

	/**
	 * Sets the han device type enum value.
	 * 
	 * @param deviceClassValue the new device class value
	 */
	public void setDeviceClassValue(Integer deviceClassValue)
	{
		setDeviceClass(DeviceClassEnum.enumForValue(deviceClassValue));
	}

	/**
	 * Gets the tamper detect timer.
	 * 
	 * @return the tamper detect timer
	 */
	public String getTamperDetectTimer()
	{
		return tamperDetectTimer;
	}

	/**
	 * Sets the tamper detect timer.
	 * 
	 * @param tamperDetectTimer the new tamper detect timer
	 */
	public void setTamperDetectTimer(String tamperDetectTimer)
	{
		this.tamperDetectTimer = tamperDetectTimer;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LCMRelay [getRelay()=" + getRelay() + ", getAmp()=" + getAmp() + ", getIntendedUse()="
				+ getIntendedUse() + ", getUsed()=" + getUsed() + ", getStartMinutes()=" + getStartMinutes()
				+ ", getEndMinutes()=" + getEndMinutes() + ", getEnrollmentCode()=" + getEnrollmentCode()
				+ ", getDeviceClass()=" + getDeviceClass() + ", getTamperDetectTimer()=" + getTamperDetectTimer()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
