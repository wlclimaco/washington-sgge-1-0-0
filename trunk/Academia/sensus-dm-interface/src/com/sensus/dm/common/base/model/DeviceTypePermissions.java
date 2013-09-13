package com.sensus.dm.common.base.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.dm.common.device.model.AlarmEnum;

/**
 * The Class DeviceTypePermissions.
 * 
 * @author QAT Global.
 */
public class DeviceTypePermissions
{
	/** The device type. */
	private DeviceTypeEnum deviceType;

	/** The device type models. */
	private List<DeviceTypeModelPermissions> deviceTypeModels;

	/** The default actions. */
	private DefaultActionsPermissions defaultActions;

	/** The alarms. */
	private List<AlarmEnum> alarms;

	/**
	 * Instantiates a new device type permissions.
	 */
	public DeviceTypePermissions()
	{

	}

	/**
	 * Instantiates a new device type permissions.
	 * 
	 * @param deviceTypeParam the device type enum
	 * @param deviceTypeModelsParam the device type models
	 * @param defaultActionsParam the default actions
	 * @param alarmsParam the alarms param
	 */
	public DeviceTypePermissions(DeviceTypeEnum deviceTypeParam,
			List<DeviceTypeModelPermissions> deviceTypeModelsParam,
			DefaultActionsPermissions defaultActionsParam,
			List<AlarmEnum> alarmsParam)
	{
		setDeviceType(deviceTypeParam);
		setDeviceTypeModels(deviceTypeModelsParam);
		setDefaultActions(defaultActionsParam);
		setAlarms(alarmsParam);
	}

	/**
	 * Gets the device type models.
	 * 
	 * @return the device type models
	 */
	public List<DeviceTypeModelPermissions> getDeviceTypeModels()
	{
		return deviceTypeModels;
	}

	/**
	 * Sets the device type models.
	 * 
	 * @param param the new device type models
	 */
	public void setDeviceTypeModels(List<DeviceTypeModelPermissions> param)
	{
		deviceTypeModels = param;
	}

	/**
	 * Gets the default actions.
	 * 
	 * @return the default actions
	 */
	public DefaultActionsPermissions getDefaultActions()
	{
		return defaultActions;
	}

	/**
	 * Sets the default actions.
	 * 
	 * @param param the new default actions
	 */
	public void setDefaultActions(DefaultActionsPermissions param)
	{
		defaultActions = param;
	}

	/**
	 * Gets the device type.
	 * 
	 * @return the device type
	 */
	public DeviceTypeEnum getDeviceType()
	{
		return deviceType;
	}

	/**
	 * Sets the device type.
	 * 
	 * @param param the new device type
	 */
	public void setDeviceType(DeviceTypeEnum param)
	{
		deviceType = param;
	}

	/**
	 * Adds the device type permissions.
	 * 
	 * @param deviceTypeModel the device type model
	 */
	public void addDeviceTypePermissions(DeviceTypeModelPermissions deviceTypeModel)
	{
		if (getDeviceTypeModels() == null)
		{
			setDeviceTypeModels(new ArrayList<DeviceTypeModelPermissions>());
		}

		getDeviceTypeModels().add(deviceTypeModel);
	}

	/**
	 * Gets the alarms.
	 * 
	 * @return the alarms
	 */
	public List<AlarmEnum> getAlarms()
	{
		return alarms;
	}

	/**
	 * Sets the alarms.
	 * 
	 * @param alarms the new alarms
	 */
	public void setAlarms(List<AlarmEnum> alarms)
	{
		this.alarms = alarms;
	}

	/**
	 * Adds the alarms.
	 * 
	 * @param alarmEnum the alarm enum
	 */
	public void addAlarms(AlarmEnum alarmEnum)
	{
		if (getAlarms() == null)
		{
			setAlarms(new ArrayList<AlarmEnum>());
		}

		getAlarms().add(alarmEnum);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceTypePermissions [getDeviceTypeModels()=" + getDeviceTypeModels() + ", getDefaultActions()="
				+ getDefaultActions() + ", getDeviceType()=" + getDeviceType() + ", getAlarms()=" + getAlarms()
				+ "]";
	}
}
