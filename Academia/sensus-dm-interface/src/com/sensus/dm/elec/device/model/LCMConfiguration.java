package com.sensus.dm.elec.device.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class LCMConfiguration.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class LCMConfiguration extends SensusModel
{

	/** The esi firmware. */
	private String esiFirmware;

	/** The han firmware. */
	private String hanFirmware;

	/** The install code. */
	private String installCode;

	/** The install date. */
	private Date installDate;

	/** The last date status. */
	private Date lastDateStatus;

	/** The firmware zigbee. */
	private String firmwareZigbee;

	/** The firmware bootflasher. */
	private String firmwareBootflasher;

	/** The firmware flexnet. */
	private String firmwareFlexnet;

	/** The firmware meter. */
	private String firmwareMeter;

	/** The encrypted. */
	private String encrypted;

	/**
	 * Instantiates a new lCM configuration.
	 */
	public LCMConfiguration()
	{

	}

	/**
	 * Instantiates a new lCM configuration.
	 * 
	 * @param firmwareMeterParam the firmware meter param
	 */
	public LCMConfiguration(String firmwareMeterParam)
	{
		setFirmwareMeter(firmwareMeterParam);
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

	/**
	 * Gets the firmware zigbee.
	 * 
	 * @return the firmware zigbee
	 */
	public String getFirmwareZigbee()
	{
		return firmwareZigbee;
	}

	/**
	 * Sets the firmware zigbee.
	 * 
	 * @param firmwareZigbee the new firmware zigbee
	 */
	public void setFirmwareZigbee(String firmwareZigbee)
	{
		this.firmwareZigbee = firmwareZigbee;
	}

	/**
	 * Gets the firmware bootflasher.
	 * 
	 * @return the firmware bootflasher
	 */
	public String getFirmwareBootflasher()
	{
		return firmwareBootflasher;
	}

	/**
	 * Sets the firmware bootflasher.
	 * 
	 * @param firmwareBootflasher the new firmware bootflasher
	 */
	public void setFirmwareBootflasher(String firmwareBootflasher)
	{
		this.firmwareBootflasher = firmwareBootflasher;
	}

	/**
	 * Gets the firmware flexnet.
	 * 
	 * @return the firmware flexnet
	 */
	public String getFirmwareFlexnet()
	{
		return firmwareFlexnet;
	}

	/**
	 * Sets the firmware flexnet.
	 * 
	 * @param firmwareFlexnet the new firmware flexnet
	 */
	public void setFirmwareFlexnet(String firmwareFlexnet)
	{
		this.firmwareFlexnet = firmwareFlexnet;
	}

	/**
	 * Gets the firmware meter.
	 * 
	 * @return the firmware meter
	 */
	public String getFirmwareMeter()
	{
		return firmwareMeter;
	}

	/**
	 * Sets the firmware meter.
	 * 
	 * @param firmwareMeter the new firmware meter
	 */
	public void setFirmwareMeter(String firmwareMeter)
	{
		this.firmwareMeter = firmwareMeter;
	}

	/**
	 * Gets the encrypted.
	 * 
	 * @return the encrypted
	 */
	public String getEncrypted()
	{
		return encrypted;
	}

	/**
	 * Sets the encrypted.
	 * 
	 * @param encrypted the new encrypted
	 */
	public void setEncrypted(String encrypted)
	{
		this.encrypted = encrypted;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LCMConfiguration [getFirmwareZigbee()=" + getFirmwareZigbee() + ", getFirmwareBootflasher()="
				+ getFirmwareBootflasher() + ", getFirmwareFlexnet()=" + getFirmwareFlexnet() + ", getFirmwareMeter()="
				+ getFirmwareMeter() + ", getEsiFirmware()=" + getEsiFirmware() + ", getHanFirmware()="
				+ getHanFirmware() + ", getInstallCode()=" + getInstallCode() + ", getLastDateStatus()="
				+ getLastDateStatus() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + ", getEncrypted()=" + getEncrypted() + "]";
	}
}
