package com.sensus.dm.elec.device.model;

import java.util.Date;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.dm.common.device.model.DeviceModel;

/**
 * The Class Meter.
 */
/**
 * @author QATEmployee
 * 
 */
@SuppressWarnings("serial")
public class ElectricMeter extends Device
{

	/** The remote connec status enum. */
	private RemoteConnectStatusEnum remoteConnectStatusEnum;

	/** The lifecycle state enum. */
	private ElectricMeterLifecycleStateEnum lifecycleStateEnum;

	/** The lifecycle status enum. */
	private ElectricMeterLifecycleStatusEnum lifecycleStatusEnum;

	/** The configuration. */
	private ElectricMeterConfiguration configuration;

	/** The device model. */
	private DeviceModel deviceModel;

	/** The electric meter type enum. */
	private ElectricMeterTypeEnum electricMeterTypeEnum;

	/** The quarantine. */
	private Boolean quarantine;

	/** The last heard. */
	private Date lastHeard;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new electric meter.
	 */
	public ElectricMeter()
	{
		super(DeviceTypeEnum.ELECTRIC_METER);
	}

	/**
	 * Instantiates a new electric meter.
	 * 
	 * @param configurationParam the configuration param
	 */
	public ElectricMeter(ElectricMeterConfiguration configurationParam)
	{
		super(DeviceTypeEnum.ELECTRIC_METER);
		setConfiguration(configurationParam);
	}

	/**
	 * Instantiates a new electric meter.
	 * 
	 * @param deviceIdParam the device id param
	 */
	public ElectricMeter(String deviceIdParam)
	{
		super(deviceIdParam, DeviceTypeEnum.ELECTRIC_METER);
	}

	/**
	 * Instantiates a new electric meter.
	 * 
	 * @param electricMeterParam the electric meter param
	 * @param configurationParam the configuration param
	 */
	public ElectricMeter(ElectricMeterTypeEnum electricMeterParam, ElectricMeterConfiguration configurationParam)
	{
		super(DeviceTypeEnum.ELECTRIC_METER);
		setElectricMeterTypeEnum(electricMeterParam);
		setConfiguration(configurationParam);
	}

	/**
	 * Instantiates a new electric meter.
	 * 
	 * @param radio the radio
	 */
	public ElectricMeter(Radio radio)
	{
		super(radio, DeviceTypeEnum.ELECTRIC_METER);
	}

	/**
	 * Instantiates a new electric meter.
	 * 
	 * @param radio the radio
	 * @param deviceId the device id
	 */
	public ElectricMeter(Radio radio, String deviceId)
	{
		super(radio, DeviceTypeEnum.ELECTRIC_METER);
		setDeviceId(deviceId);
	}

	/**
	 * Instantiates a new electric meter.
	 * 
	 * @param remoteConnectStatusEnumParam the remote connect status enum param
	 */
	public ElectricMeter(RemoteConnectStatusEnum remoteConnectStatusEnumParam)
	{
		super(DeviceTypeEnum.ELECTRIC_METER);
		setRemoteConnectStatusEnum(remoteConnectStatusEnumParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the remote connect status enum.
	 * 
	 * @return the remote connect status enum
	 */
	public RemoteConnectStatusEnum getRemoteConnectStatusEnum()
	{
		return remoteConnectStatusEnum;
	}

	/**
	 * Sets the remote connect status enum.
	 * 
	 * @param remoteConnectStatusEnum the new remote connect status enum
	 */
	public void setRemoteConnectStatusEnum(RemoteConnectStatusEnum remoteConnectStatusEnum)
	{
		this.remoteConnectStatusEnum = remoteConnectStatusEnum;
	}

	/**
	 * Gets the meter type enum value.
	 * 
	 * @return the meter type enum value
	 */
	public Integer getRemoteConnectStatusEnumValue()
	{
		if (getRemoteConnectStatusEnum() != null)
		{
			return getRemoteConnectStatusEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the remote connect status enum value.
	 * 
	 * @param remoteConnectStatusEnumParam the new remote connect status enum value
	 */
	public void setRemoteConnectStatusEnumValue(Integer remoteConnectStatusEnumParam)
	{
		setRemoteConnectStatusEnum(RemoteConnectStatusEnum.enumForValue(remoteConnectStatusEnumParam));
	}

	/**
	 * Gets the lifecycle state enum.
	 * 
	 * @return the lifecycle state enum
	 */
	public ElectricMeterLifecycleStateEnum getLifecycleStateEnum()
	{
		return lifecycleStateEnum;
	}

	/**
	 * Sets the lifecycle state enum.
	 * 
	 * @param lifecycleStateEnum the new lifecycle state enum
	 */
	public void setLifecycleStateEnum(ElectricMeterLifecycleStateEnum lifecycleStateEnum)
	{
		this.lifecycleStateEnum = lifecycleStateEnum;
	}

	/**
	 * Gets the lifecycle state enum value.
	 * 
	 * @return the lifecycle state enum value
	 */
	public String getLifecycleStateEnumValue()
	{
		if (getLifecycleStateEnum() != null)
		{
			return getLifecycleStateEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the lifecycle state enum value.
	 * 
	 * @param lifecycleStateEnumParam the new lifecycle state enum value
	 */
	public void setLifecycleStateEnumValue(String lifecycleStateEnumParam)
	{
		setLifecycleStateEnum(ElectricMeterLifecycleStateEnum.enumForValue(lifecycleStateEnumParam));
	}

	/**
	 * Gets the lifecycle status enum.
	 * 
	 * @return the lifecycle status enum
	 */
	public ElectricMeterLifecycleStatusEnum getLifecycleStatusEnum()
	{
		return lifecycleStatusEnum;
	}

	/**
	 * Sets the lifecycle status enum.
	 * 
	 * @param lifecycleStatusEnum the new lifecycle status enum
	 */
	public void setLifecycleStatusEnum(ElectricMeterLifecycleStatusEnum lifecycleStatusEnum)
	{
		this.lifecycleStatusEnum = lifecycleStatusEnum;
	}

	/**
	 * Gets the lifecycle status enum value.
	 * 
	 * @return the lifecycle status enum value
	 */
	public Integer getLifecycleStatusEnumValue()
	{
		if (getLifecycleStatusEnum() != null)
		{
			return getLifecycleStatusEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the lifecycle status enum value.
	 * 
	 * @param lifecycleStateEnumParam the new lifecycle status enum value
	 */
	public void setLifecycleStatusEnumValue(Integer lifecycleStateEnumParam)
	{
		setLifecycleStatusEnum(ElectricMeterLifecycleStatusEnum.enumForValue(lifecycleStateEnumParam));
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	public ElectricMeterConfiguration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param configuration the new configuration
	 */
	public void setConfiguration(ElectricMeterConfiguration configuration)
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

	/**
	 * Gets the electric meter type enum.
	 * 
	 * @return the electric meter type enum
	 */
	public ElectricMeterTypeEnum getElectricMeterTypeEnum()
	{
		return electricMeterTypeEnum;
	}

	/**
	 * Sets the electric meter type enum.
	 * 
	 * @param electricMeterTypeEnum the new electric meter type enum
	 */
	public void setElectricMeterTypeEnum(ElectricMeterTypeEnum electricMeterTypeEnum)
	{
		this.electricMeterTypeEnum = electricMeterTypeEnum;
	}

	/**
	 * Gets the electric meter type enum value.
	 * 
	 * @return the electric meter type enum value
	 */
	public Integer getElectricMeterTypeEnumValue()
	{
		if (getElectricMeterTypeEnum() != null)
		{
			return getElectricMeterTypeEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the electric meter type enum value.
	 * 
	 * @param electricMeterTypeEnumParam the new electric meter type enum value
	 */
	public void setElectricMeterTypeEnumValue(Integer electricMeterTypeEnumParam)
	{
		setElectricMeterTypeEnum(ElectricMeterTypeEnum.enumForValue(electricMeterTypeEnumParam));
	}

	/**
	 * Gets the quarantine.
	 * 
	 * @return the quarantine
	 */
	public Boolean getQuarantine()
	{
		return quarantine;
	}

	/**
	 * Sets the quarantine.
	 * 
	 * @param quarantine the new quarantine
	 */
	public void setQuarantine(Boolean quarantine)
	{
		this.quarantine = quarantine;
	}

	/**
	 * Gets the last heard.
	 * 
	 * @return the last heard
	 */
	public Date getLastHeard()
	{
		return lastHeard;
	}

	/**
	 * Sets the last heard.
	 * 
	 * @param lastHeard the new last heard
	 */
	public void setLastHeard(Date lastHeard)
	{
		this.lastHeard = lastHeard;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.cbof.model.Device#toString()
	 */
	@Override
	public String toString()
	{
		return "ElectricMeter [getRemoteConnectStatusEnum()=" + getRemoteConnectStatusEnum()
				+ "getDeviceId()=" + getDeviceId()
				+ ", getRemoteConnectStatusEnumValue()=" + getRemoteConnectStatusEnumValue()
				+ ", getLifecycleStateEnum()=" + getLifecycleStateEnum() + ", getLifecycleStateEnumValue()="
				+ getLifecycleStateEnumValue() + ", getLifecycleStatusEnum()=" + getLifecycleStatusEnum()
				+ ", getLifecycleStatusEnumValue()=" + getLifecycleStatusEnumValue() + ", getConfiguration()="
				+ getConfiguration() + ", getDeviceModel()=" + getDeviceModel() + ", getElectricMeterTypeEnum()="
				+ getElectricMeterTypeEnum() + ", getElectricMeterTypeEnumValue()=" + getElectricMeterTypeEnumValue()
				+ ", getQuarantine()=" + getQuarantine() + ", getLastHeard()=" + getLastHeard() + "]";
	}
}
