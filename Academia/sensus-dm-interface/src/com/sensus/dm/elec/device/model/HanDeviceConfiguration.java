package com.sensus.dm.elec.device.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class HanDeviceConfiguration.
 */
@SuppressWarnings("serial")
public class HanDeviceConfiguration extends SensusModel
{

	/** The esi firmware. */
	private String esiFirmware;

	/** The han firmware. */
	private String hanFirmware;

	/** The install code. */
	private String installCode;

	/** The last date status. */
	private Date lastDateStatus;

	/** The install date. */
	private Date installDate;

	/**
	 * Instantiates a new han device configuration.
	 */
	public HanDeviceConfiguration()
	{

	}

	/**
	 * Instantiates a new han device configuration.
	 * 
	 * @param lastDateStatusParam the last date status param
	 */
	public HanDeviceConfiguration(Date lastDateStatusParam)
	{
		setLastDateStatus(lastDateStatusParam);
	}

	/**
	 * Instantiates a new han device configuration.
	 * 
	 * @param installCodeParam the install code param
	 */
	public HanDeviceConfiguration(String installCodeParam)
	{
		setInstallCode(installCodeParam);
	}

	/**
	 * Gets the esi firmware.
	 * 
	 * @return the esi firmware
	 */
	public String getEsiFirmware()
	{
		return esiFirmware;
	}

	/**
	 * Sets the esi firmware.
	 * 
	 * @param esiFirmware the new esi firmware
	 */
	public void setEsiFirmware(String esiFirmware)
	{
		this.esiFirmware = esiFirmware;
	}

	/**
	 * Gets the han firmware.
	 * 
	 * @return the han firmware
	 */
	public String getHanFirmware()
	{
		return hanFirmware;
	}

	/**
	 * Sets the han firmware.
	 * 
	 * @param hanFirmware the new han firmware
	 */
	public void setHanFirmware(String hanFirmware)
	{
		this.hanFirmware = hanFirmware;
	}

	/**
	 * Gets the install code.
	 * 
	 * @return the install code
	 */
	public String getInstallCode()
	{
		return installCode;
	}

	/**
	 * Sets the install code.
	 * 
	 * @param installCode the new install code
	 */
	public void setInstallCode(String installCode)
	{
		this.installCode = installCode;
	}

	/**
	 * Gets the install date.
	 * 
	 * @return the install date
	 */
	public Date getInstallDate()
	{
		return installDate;
	}

	/**
	 * Sets the install date.
	 * 
	 * @param installDate the new install date
	 */
	public void setInstallDate(Date installDate)
	{
		this.installDate = installDate;
	}

	/**
	 * Gets the last date status.
	 * 
	 * @return the last date status
	 */
	public Date getLastDateStatus()
	{
		return lastDateStatus;
	}

	/**
	 * Sets the last date status.
	 * 
	 * @param lastDateStatus the new last date status
	 */
	public void setLastDateStatus(Date lastDateStatus)
	{
		this.lastDateStatus = lastDateStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "HanDeviceConfiguration [getEsiFirmware()=" + getEsiFirmware() + ", getHanFirmware()="
				+ getHanFirmware()
				+ ", getInstallCode()=" + getInstallCode() + ", getLastDateStatus()=" + getLastDateStatus()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()="
				+ getCreateDate() + ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate()
				+ "]";
	}

}
