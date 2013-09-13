package com.sensus.dm.water.device.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.dm.common.device.model.Alarm;
import com.sensus.cbof.model.Device;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;

/**
 * The Class WaterMeter.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class WaterMeter extends Device
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The water meter type enum. */
	private WaterMeterTypeEnum waterMeterTypeEnum;

	/** The mac address. */
	private String macAddress;

	/** The status. */
	private WaterGasMeterStatusEnum status;

	/** The device model. */
	private DeviceModel deviceModel;

	/** The configuration. */
	private WaterGasMeterConfiguration configuration;

	/** The transmit mode. */
	private String transmitMode;

	/** The transmit rate. */
	private String transmitRate;

	/** The interface type. */
	private WaterMeterInterfaceTypeEnum interfaceType;

	/** The supervisory transmit rate. */
	private Integer supervisoryTransmitRate;

	/** The last heard. */
	private Date lastHeard;

	/** The alarms. */
	private List<Alarm> alarms;

	/** The quarantine. */
	private Boolean quarantine;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new water meter.
	 */
	public WaterMeter()
	{
		super(DeviceTypeEnum.WATER_METER);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param deviceIdParam the device id param
	 */
	public WaterMeter(String deviceIdParam)
	{
		this();
		setDeviceId(deviceIdParam);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param radio the radio
	 */
	public WaterMeter(Radio radio)
	{
		super(radio, DeviceTypeEnum.WATER_METER);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param deviceIdParam the device id param
	 * @param radio the radio
	 */
	public WaterMeter(String deviceIdParam, Radio radio)
	{
		super(deviceIdParam, radio, DeviceTypeEnum.WATER_METER);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param waterGasMeterConfigurationParam the water gas meter configuration param
	 */
	public WaterMeter(WaterGasMeterConfiguration waterGasMeterConfigurationParam)
	{
		this();
		setConfiguration(waterGasMeterConfigurationParam);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param alarmParam the alarm param
	 */
	public WaterMeter(Alarm alarmParam)
	{
		this();
		addAlarm(alarmParam);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param alarmParam the alarm param
	 */
	public WaterMeter(List<Alarm> alarmParam)
	{
		this();
		setAlarms(alarmParam);
	}

	/**
	 * Instantiates a new water meter.
	 * 
	 * @param radio the radio
	 * @param alarmParam the alarm param
	 */
	public WaterMeter(Radio radio, Alarm alarmParam)
	{
		this();
		setRadio(radio);
		addAlarm(alarmParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the water meter type enum.
	 * 
	 * @return the water meter type enum
	 */
	public WaterMeterTypeEnum getWaterMeterTypeEnum()
	{
		return waterMeterTypeEnum;
	}

	/**
	 * Sets the water meter type enum.
	 * 
	 * @param waterMeterTypeEnum the new water meter type enum
	 */
	public void setWaterMeterTypeEnum(WaterMeterTypeEnum waterMeterTypeEnum)
	{
		this.waterMeterTypeEnum = waterMeterTypeEnum;
	}

	/**
	 * Sets the water meter type enum value.
	 * 
	 * @param waterMeterTypeEnumValue the new water meter type enum value
	 */
	public void setWaterMeterTypeEnumValue(Integer waterMeterTypeEnumValue)
	{
		waterMeterTypeEnum = WaterMeterTypeEnum.enumForValue(waterMeterTypeEnumValue);
	}

	/**
	 * Gets the water meter type enum value.
	 * 
	 * @return the water meter type enum value
	 */
	public Integer getWaterMeterTypeEnumValue()
	{
		if (getWaterMeterTypeEnum() != null)
		{
			return getWaterMeterTypeEnum().getValue();
		}

		return null;
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
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public WaterGasMeterStatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the new status
	 */
	public void setStatus(WaterGasMeterStatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Sets the status value.
	 * 
	 * @param statusValue the new status value
	 */
	public void setStatusValue(Integer statusValue)
	{
		status = WaterGasMeterStatusEnum.enumForValue(statusValue);
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
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	public WaterGasMeterConfiguration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param configuration the new configuration
	 */
	public void setConfiguration(WaterGasMeterConfiguration configuration)
	{
		this.configuration = configuration;
	}

	/**
	 * Gets the transmit mode.
	 * 
	 * @return the transmit mode
	 */
	public String getTransmitMode()
	{
		return transmitMode;
	}

	/**
	 * Sets the transmit mode.
	 * 
	 * @param transmitMode the new transmit mode
	 */
	public void setTransmitMode(String transmitMode)
	{
		this.transmitMode = transmitMode;
	}

	/**
	 * Gets the transmit rate.
	 * 
	 * @return the transmit rate
	 */
	public String getTransmitRate()
	{
		return transmitRate;
	}

	/**
	 * Sets the transmit rate.
	 * 
	 * @param transmitRate the new transmit rate
	 */
	public void setTransmitRate(String transmitRate)
	{
		this.transmitRate = transmitRate;
	}

	/**
	 * Gets the interface type.
	 * 
	 * @return the interface type
	 */
	public WaterMeterInterfaceTypeEnum getInterfaceType()
	{
		return interfaceType;
	}

	/**
	 * Sets the interface type.
	 * 
	 * @param interfaceType the new interface type
	 */
	public void setInterfaceType(WaterMeterInterfaceTypeEnum interfaceType)
	{
		this.interfaceType = interfaceType;
	}

	/**
	 * Gets the supervisory transmit rate.
	 * 
	 * @return the supervisory transmit rate
	 */
	public Integer getSupervisoryTransmitRate()
	{
		return supervisoryTransmitRate;
	}

	/**
	 * Sets the supervisory transmit rate.
	 * 
	 * @param supervisoryTransmitRate the new supervisory transmit rate
	 */
	public void setSupervisoryTransmitRate(Integer supervisoryTransmitRate)
	{
		this.supervisoryTransmitRate = supervisoryTransmitRate;
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

	/**
	 * Gets the alarms.
	 * 
	 * @return the alarms
	 */
	public List<Alarm> getAlarms()
	{
		return alarms;
	}

	/**
	 * Sets the alarms.
	 * 
	 * @param alarms the new alarms
	 */
	public void setAlarms(List<Alarm> alarms)
	{
		this.alarms = alarms;
	}

	/**
	 * Adds the alarm.
	 * 
	 * @param alarm the alarm
	 */
	public void addAlarm(Alarm alarm)
	{
		if (getAlarms() == null)
		{
			setAlarms(new ArrayList<Alarm>());
		}

		getAlarms().add(alarm);
	}

	/**
	 * Gets the first alarm.
	 * 
	 * @return the first alarm
	 */
	public Alarm getFirstAlarm()
	{
		if ((getAlarms() != null) && !getAlarms().isEmpty())
		{
			return getAlarms().get(FIRST);
		}

		return null;
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

	@Override
	public String toString()
	{
		return "WaterMeter [getWaterMeterTypeEnum()=" + getWaterMeterTypeEnum() + ", getWaterMeterTypeEnumValue()="
				+ getWaterMeterTypeEnumValue() + ", getMacAddress()=" + getMacAddress() + ", getStatus()="
				+ getStatus() + ", getDeviceModel()=" + getDeviceModel() + ", getConfiguration()=" + getConfiguration()
				+ ", getTransmitMode()=" + getTransmitMode() + ", getTransmitRate()=" + getTransmitRate()
				+ ", getInterfaceType()=" + getInterfaceType() + ", getSupervisoryTransmitRate()="
				+ getSupervisoryTransmitRate() + ", getLastHeard()=" + getLastHeard() + ", getAlarms()=" + getAlarms()
				+ ", getFirstAlarm()=" + getFirstAlarm() + ", getQuarantine()=" + getQuarantine() + ", toString()="
				+ super.toString() + "]";
	}

}
