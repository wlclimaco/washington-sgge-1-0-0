package com.sensus.dm.elec.device.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class Meter.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class ElectricMeterConfiguration extends SensusModel
{
	/** The firmware zigbee. */
	private String firmwareZigbee;

	/** The firmware bootflasher. */
	private String firmwareBootflasher;

	/** The firmware flexnet. */
	private String firmwareFlexnet;

	/** The firmware meter. */
	private String firmwareMeter;

	/** The premise id. */
	private String premiseId;

	/** The premise id device count. */
	private Integer premiseIdDeviceCount;

	/** The tou enable. */
	private Boolean touEnable;

	/** The esm enable. */
	private Boolean esmEnable;

	/** The encrypted. */
	private String encrypted;

	/** The encryption supported. */
	private Boolean encryptionSupported;

	/** The install date. */
	private Date installDate;

	/** The meter serial number. */
	private String meterSerialNumber;

	/** The remote connect available enum. */
	private RemoteConnectAvailableEnum remoteConnectAvailableEnum;

	/**
	 * Instantiates a new electric meter configuration.
	 */
	public ElectricMeterConfiguration()
	{
	}

	/**
	 * Instantiates a new electric meter configuration.
	 * 
	 * @param firmwareMeterParam the firmware meter param
	 */
	public ElectricMeterConfiguration(String firmwareMeterParam)
	{
		setFirmwareMeter(firmwareMeterParam);
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
	 * @param firmwareMeter
	 *            the new firmware meter
	 */
	public void setFirmwareMeter(String firmwareMeter)
	{
		this.firmwareMeter = firmwareMeter;
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
	 * Gets the premise id.
	 * 
	 * @return the premise id
	 */
	public String getPremiseId()
	{
		return premiseId;
	}

	/**
	 * Sets the premise id.
	 * 
	 * @param premiseId the new premise id
	 */
	public void setPremiseId(String premiseId)
	{
		this.premiseId = premiseId;
	}

	/**
	 * Gets the premise id device count.
	 * 
	 * @return the premise id device count
	 */
	public Integer getPremiseIdDeviceCount()
	{
		return premiseIdDeviceCount;
	}

	/**
	 * Sets the premise id device count.
	 * 
	 * @param premiseIdDeviceCount the new premise id device count
	 */
	public void setPremiseIdDeviceCount(Integer premiseIdDeviceCount)
	{
		this.premiseIdDeviceCount = premiseIdDeviceCount;
	}

	/**
	 * Gets the tou enable.
	 * 
	 * @return the tou enable
	 */
	public Boolean getTouEnable()
	{
		return touEnable;
	}

	/**
	 * Sets the tou enable.
	 * 
	 * @param touEnable the new tou enable
	 */
	public void setTouEnable(Boolean touEnable)
	{
		this.touEnable = touEnable;
	}

	/**
	 * Gets the esm enable.
	 * 
	 * @return the esm enable
	 */
	public Boolean getEsmEnable()
	{
		return esmEnable;
	}

	/**
	 * Sets the esm enable.
	 * 
	 * @param esmEnable the new esm enable
	 */
	public void setEsmEnable(Boolean esmEnable)
	{
		this.esmEnable = esmEnable;
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

	/**
	 * Gets the encryption supported.
	 * 
	 * @return the encryption supported
	 */
	public Boolean getEncryptionSupported()
	{
		return encryptionSupported;
	}

	/**
	 * Sets the encryption supported.
	 * 
	 * @param encryptionSupported the new encryption supported
	 */
	public void setEncryptionSupported(Boolean encryptionSupported)
	{
		this.encryptionSupported = encryptionSupported;
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
	 * Gets the meter serial number.
	 * 
	 * @return the meter serial number
	 */
	public String getMeterSerialNumber()
	{
		return meterSerialNumber;
	}

	/**
	 * Sets the meter serial number.
	 * 
	 * @param meterSerialNumber the new meter serial number
	 */
	public void setMeterSerialNumber(String meterSerialNumber)
	{
		this.meterSerialNumber = meterSerialNumber;
	}

	/**
	 * Gets the remote connect available enum.
	 * 
	 * @return the remote connect available enum
	 */
	public RemoteConnectAvailableEnum getRemoteConnectAvailableEnum()
	{
		return remoteConnectAvailableEnum;
	}

	/**
	 * Sets the remote connect available enum.
	 * 
	 * @param remoteConnectAvailableEnum the new remote connect available enum
	 */
	public void setRemoteConnectAvailableEnum(RemoteConnectAvailableEnum remoteConnectAvailableEnum)
	{
		this.remoteConnectAvailableEnum = remoteConnectAvailableEnum;
	}

	/**
	 * Gets the remote connect available enum value.
	 * 
	 * @return the remote connect available enum value
	 */
	public Integer getRemoteConnectAvailableEnumValue()
	{
		if (getRemoteConnectAvailableEnum() != null)
		{
			return getRemoteConnectAvailableEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the remote connect available enum value.
	 * 
	 * @param remoteConnectAvailableEnumParam the new remote connect available enum value
	 */
	public void setRemoteConnectAvailableEnumValue(Integer remoteConnectAvailableEnumParam)
	{
		setRemoteConnectAvailableEnum(RemoteConnectAvailableEnum.enumForValue(remoteConnectAvailableEnumParam));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.electric.Device#toString()
	 */
	@Override
	public String toString()
	{
		return "ElectricMeterConfiguration [getFirmwareZigbee()=" + getFirmwareZigbee() + ", getFirmwareBootflasher()="
				+ getFirmwareBootflasher() + ", getFirmwareFlexnet()=" + getFirmwareFlexnet() + ", getPremiseId()="
				+ getPremiseId() + ", getPremiseIdDeviceCount()=" + getPremiseIdDeviceCount()
				+ ", getTouEnable()=" + getTouEnable() + ", getEsmEnable()=" + getEsmEnable()
				+ ", getEncrypted()=" + getEncrypted() + ", getEncryptionSupported()=" + getEncryptionSupported()
				+ ", getInstallDate()=" + getInstallDate() + ", getMeterSerialNumber()=" + getMeterSerialNumber()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + ", getFirmwareMeter()=" + getFirmwareMeter()
				+ ", getRemoteConnectAvailableEnum()=" + getRemoteConnectAvailableEnum() + "]";
	}
}
