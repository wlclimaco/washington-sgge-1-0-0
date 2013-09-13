package com.sensus.dm.elec.device.model;

import java.math.BigInteger;

import com.sensus.cbof.model.Device;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;

/**
 * The Class HanDevice.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class HanDevice extends Device
{
	// -------------------------------------------------------------------------
	// Fields:

	/** The han device id. */
	private String hanDeviceId;

	/** The han device type enum. */
	private HanDeviceTypeEnum hanDeviceTypeEnum;

	/** The life cycle state enum. */
	private HanLifecycleStateEnum lifecycleStateEnum;

	/** The electric meter flex net id. */
	private BigInteger electricMeterFlexNetId;

	/** The mac address. */
	private String macAddress;

	/** The configuration. */
	private HanDeviceConfiguration configuration;

	/** The device model. */
	private DeviceModel deviceModel;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new han device.
	 */
	public HanDevice()
	{
		super(DeviceTypeEnum.HAN_DEVICE);
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param baseFlexNetId the base flex net id
	 * @param installCode the install code
	 */
	public HanDevice(BigInteger baseFlexNetId, String installCode)
	{
		super(DeviceTypeEnum.HAN_DEVICE);
		setElectricMeterFlexNetId(baseFlexNetId);
		setConfiguration(new HanDeviceConfiguration(installCode));
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param hanDeviceTypeEnumParam the han device type enum param
	 */
	public HanDevice(HanDeviceTypeEnum hanDeviceTypeEnumParam)
	{
		this();
		setHanDeviceTypeEnum(hanDeviceTypeEnumParam);
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param radioParam the radio param
	 */
	public HanDevice(Radio radioParam)
	{
		super(radioParam, DeviceTypeEnum.HAN_DEVICE);
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param electricMeterFlexNetIdParam the electric meter flex net id
	 */
	public HanDevice(BigInteger electricMeterFlexNetIdParam)
	{
		this();
		setElectricMeterFlexNetId(electricMeterFlexNetIdParam);
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param paramHanDeviceId the param han device id
	 */
	public HanDevice(String paramHanDeviceId)
	{
		this();
		setHanDeviceId(paramHanDeviceId);
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param hanDeviceConfigurationParam the han device configuration param
	 */
	public HanDevice(HanDeviceConfiguration hanDeviceConfigurationParam)
	{
		this();
		setConfiguration(hanDeviceConfigurationParam);
	}

	/**
	 * Instantiates a new han device.
	 * 
	 * @param deviceModelParam the device model param
	 */
	public HanDevice(DeviceModel deviceModelParam)
	{
		this();
		setDeviceModel(deviceModelParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the han device id.
	 * 
	 * @return the han device id
	 */
	public String getHanDeviceId()
	{
		return hanDeviceId;
	}

	/**
	 * Sets the han device id.
	 * 
	 * @param hanDeviceId the new han device id
	 */
	public void setHanDeviceId(String hanDeviceId)
	{
		this.hanDeviceId = hanDeviceId;
	}

	/**
	 * Gets the han device type enum.
	 * 
	 * @return the han device type enum
	 */
	public HanDeviceTypeEnum getHanDeviceTypeEnum()
	{
		return hanDeviceTypeEnum;
	}

	/**
	 * Sets the han device type enum.
	 * 
	 * @param hanDeviceTypeEnum the new han device type enum
	 */
	public void setHanDeviceTypeEnum(HanDeviceTypeEnum hanDeviceTypeEnum)
	{
		this.hanDeviceTypeEnum = hanDeviceTypeEnum;
	}

	/**
	 * Gets the han device type enum value.
	 * 
	 * @return the han device type enum value
	 */
	public Integer getHanDeviceTypeEnumValue()
	{
		if (getHanDeviceTypeEnum() != null)
		{
			return getHanDeviceTypeEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the han device type enum value.
	 * 
	 * @param hanDeviceTypeEnumValue the new han device type enum value
	 */
	public void setHanDeviceTypeEnumValue(Integer hanDeviceTypeEnumValue)
	{
		setHanDeviceTypeEnum(HanDeviceTypeEnum.enumForValue(hanDeviceTypeEnumValue));
	}

	/**
	 * Gets the life cycle state enum.
	 * 
	 * @return the life cycle state enum
	 */
	public HanLifecycleStateEnum getLifecycleStateEnum()
	{
		return lifecycleStateEnum;
	}

	/**
	 * Sets the life cycle state enum.
	 * 
	 * @param lifecycleStateEnum the new life cycle state enum
	 */
	public void setLifecycleStateEnum(HanLifecycleStateEnum lifecycleStateEnum)
	{
		this.lifecycleStateEnum = lifecycleStateEnum;
	}

	/**
	 * Gets the life cycle state enum value.
	 * 
	 * @return the life cycle state enum value
	 */
	public Integer getLifecycleStateEnumValue()
	{
		if (getLifecycleStateEnum() != null)
		{
			return getLifecycleStateEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the life cycle state enum value.
	 * 
	 * @param lifecycleStateEnumParam the new life cycle state enum value
	 */
	public void setLifecycleStateEnumValue(Integer lifecycleStateEnumParam)
	{
		setLifecycleStateEnum(HanLifecycleStateEnum.enumForValue(lifecycleStateEnumParam));
	}

	/**
	 * Gets the electric meter flex net id.
	 * 
	 * @return the electric meter flex net id
	 */
	public BigInteger getElectricMeterFlexNetId()
	{
		return electricMeterFlexNetId;
	}

	/**
	 * Sets the electric meter flex net id.
	 * 
	 * @param electricMeterFlexNetId the new electric meter flex net id
	 */
	public void setElectricMeterFlexNetId(BigInteger electricMeterFlexNetId)
	{
		this.electricMeterFlexNetId = electricMeterFlexNetId;
	}

	/**
	 * Gets the mac address.
	 * 
	 * @return the mac address
	 */
	public String getMacAddress()
	{
		return macAddress;
	}

	/**
	 * Sets the mac address.
	 * 
	 * @param macAddress the new mac address
	 */
	public void setMacAddress(String macAddress)
	{
		this.macAddress = macAddress;
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	public HanDeviceConfiguration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param configuration the new configuration
	 */
	public void setConfiguration(HanDeviceConfiguration configuration)
	{
		this.configuration = configuration;
	}

	/**
	 * Gets the device model.
	 * 
	 * @return the device model
	 */
	public DeviceModel getDeviceModel()
	{
		return deviceModel;
	}

	/**
	 * Sets the device model.
	 * 
	 * @param deviceModel the new device model
	 */
	public void setDeviceModel(DeviceModel deviceModel)
	{
		this.deviceModel = deviceModel;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.model.Device#toString()
	 */
	@Override
	public String toString()
	{
		return "HanDevice [getHanDeviceId()=" + getHanDeviceId() + ", getHanDeviceTypeEnum()=" + getHanDeviceTypeEnum()
				+ ", getHanDeviceTypeEnumValue()=" + getHanDeviceTypeEnumValue() + ", getLifecycleStateEnum()="
				+ getLifecycleStateEnum() + ", getElectricMeterFlexNetId()=" + getElectricMeterFlexNetId()
				+ ", getMacAddress()=" + getMacAddress() + ", getConfiguration()=" + getConfiguration()
				+ ", getDeviceModel()=" + getDeviceModel() + ", toString()=" + super.toString() + "]";
	}
}
