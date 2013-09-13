package com.sensus.dm.elec.device.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.DeviceModel;

/**
 * The Class LCM.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class LCM extends Device
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The ife cycle state enum. */
	private LCMLifecycleStateEnum lifecycleStateEnum;

	/** The lcm relays. */
	private List<LCMRelay> lcmRelays;

	/** The device model. */
	private DeviceModel deviceModel;

	/** The electric meter flex net id. */
	private BigInteger electricMeterFlexNetId;

	/** The configuration. */
	private LCMConfiguration configuration;

	/** The lcm type enum. */
	private LCMTypeEnum lcmTypeEnum;

	/** The mac address. */
	private String macAddress;

	/** The alarms. */
	private List<Alarm> alarms;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new LCM.
	 */
	public LCM()
	{
		super(DeviceTypeEnum.LCM);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param deviceIdParam the device id param
	 */
	public LCM(String deviceIdParam)
	{
		super(deviceIdParam, DeviceTypeEnum.LCM);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param lcmTypeEnumParam the lcm type enum param
	 */
	public LCM(LCMTypeEnum lcmTypeEnumParam)
	{
		this();
		setLcmTypeEnum(lcmTypeEnumParam);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param radioParam the radio param
	 */
	public LCM(Radio radioParam)
	{
		super(radioParam, DeviceTypeEnum.LCM);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param configurationParam the configuration param
	 */
	public LCM(LCMConfiguration configurationParam)
	{
		this();
		setConfiguration(configurationParam);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param deviceModelParam the device model param
	 */
	public LCM(DeviceModel deviceModelParam)
	{
		this();
		setDeviceModel(deviceModelParam);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param alarmParam the alarm param
	 */
	public LCM(Alarm alarmParam)
	{
		this();
		addAlarm(alarmParam);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param alarmsParam the alarms param
	 */
	public LCM(List<Alarm> alarmsParam)
	{
		this();
		setAlarms(alarmsParam);
	}

	/**
	 * Instantiates a new lcm.
	 * 
	 * @param radio the radio
	 * @param alarmParam the alarm param
	 */
	public LCM(Radio radio, Alarm alarmParam)
	{
		this();
		setRadio(radio);
		addAlarm(alarmParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the lcm relays.
	 * 
	 * @return the lcm relays
	 */
	public List<LCMRelay> getLcmRelays()
	{
		return lcmRelays;
	}

	/**
	 * Sets the lcm relays.
	 * 
	 * @param lcmRelays the new lcm relays
	 */
	public void setLcmRelays(List<LCMRelay> lcmRelays)
	{
		this.lcmRelays = lcmRelays;
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
	 * Gets the device model.
	 * 
	 * @return the device model
	 */
	public DeviceModel getDeviceModel()
	{
		return deviceModel;
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
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	public LCMConfiguration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param configuration the new configuration
	 */
	public void setConfiguration(LCMConfiguration configuration)
	{
		this.configuration = configuration;
	}

	/**
	 * Gets the lcm type enum.
	 * 
	 * @return the lcm type enum
	 */
	public LCMTypeEnum getLcmTypeEnum()
	{
		return lcmTypeEnum;
	}

	/**
	 * Sets the lcm type enum.
	 * 
	 * @param lcmTypeEnum the new lcm type enum
	 */
	public void setLcmTypeEnum(LCMTypeEnum lcmTypeEnum)
	{
		this.lcmTypeEnum = lcmTypeEnum;
	}

	/**
	 * Gets the lcm type enum value.
	 * 
	 * @return the lcm type enum value
	 */
	public Integer getLcmTypeEnumValue()
	{
		if (getLcmTypeEnum() != null)
		{
			return getLcmTypeEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the lcm type enum value.
	 * 
	 * @param lcmTypeEnumValue the new lcm type enum value
	 */
	public void setLcmTypeEnumValue(Integer lcmTypeEnumValue)
	{
		setLcmTypeEnum(LCMTypeEnum.enumForValue(lcmTypeEnumValue));
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
	 * Gets the life cycle state enum.
	 * 
	 * @return the life cycle state enum
	 */
	public LCMLifecycleStateEnum getLifecycleStateEnum()
	{
		return lifecycleStateEnum;
	}

	/**
	 * Sets the life cycle state enum.
	 * 
	 * @param lifecycleStateEnum the new lifecycle state enum
	 */
	public void setLifecycleStateEnum(LCMLifecycleStateEnum lifecycleStateEnum)
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
	 * @param lifecycleStateEnumValue the new life cycle state enum value
	 */
	public void setLifecycleStateEnumValue(Integer lifecycleStateEnumValue)
	{
		setLifecycleStateEnum(LCMLifecycleStateEnum.enumForValue(lifecycleStateEnumValue));
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
		if (getAlarms() != null && !getAlarms().isEmpty())
		{
			return getAlarms().get(FIRST);
		}

		return null;
	}

	/**
	 * Gets the first lcm relay.
	 * 
	 * @return the first lcm relay
	 */
	public LCMRelay getFirstLCMRelay()
	{
		if (getLcmRelays() != null && !getLcmRelays().isEmpty())
		{
			return getLcmRelays().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the lcm relay.
	 * 
	 * @param lcmRelay the lcm relay
	 */
	public void addLCMRelay(LCMRelay lcmRelay)
	{
		if (getLcmRelays() == null)
		{
			setLcmRelays(new ArrayList<LCMRelay>());
		}

		getLcmRelays().add(lcmRelay);
	}

	@Override
	public String toString()
	{
		return "LCM [getLcmRelays()=" + getLcmRelays() + ", getDeviceModel()=" + getDeviceModel()
				+ ", getElectricMeterFlexNetId()=" + getElectricMeterFlexNetId() + ", getConfiguration()="
				+ getConfiguration() + ", getLcmTypeEnum()=" + getLcmTypeEnum() + ", getLcmTypeEnumValue()="
				+ getLcmTypeEnumValue() + ", getMacAddress()=" + getMacAddress() + ", getLifecycleStateEnum()="
				+ getLifecycleStateEnum() + ", getLifecycleStateEnumValue()=" + getLifecycleStateEnumValue()
				+ ", getAlarms()=" + getAlarms() + ", getFirstAlarm()=" + getFirstAlarm() + ", toString()="
				+ super.toString() + "]";
	}

}
