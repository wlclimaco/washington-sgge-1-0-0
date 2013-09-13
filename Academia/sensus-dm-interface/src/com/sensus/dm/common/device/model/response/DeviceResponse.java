package com.sensus.dm.common.device.model.response;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.Response;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;

/**
 * The Class DeviceResponse.
 * 
 * @author QAT Global.
 */
public class DeviceResponse extends Response
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The devices. */
	private List<Device> devices;

	/** The services type permissions. */
	private ServicesPermissions servicesPermissions;

	/** The file name. */
	private String fileName;

	/** The device type count list. */
	private List<DeviceTypeCount> deviceTypeCountList;

	/** The Device models. */
	private List<DeviceModel> deviceModels;

	/** The lifecycle states. */
	private List<LifecycleState> lifecycleStates;

	/** The Connection states. */
	private List<ConnectionState> connectionStates;

	/** The alarms. */
	private List<Alarm> alarms;

	/** The lcm relays. */
	private List<LCMRelay> lcmRelays;

	/** The water gas meter status. */
	private List<WaterGasMeterStatusCount> waterGasMeterStatusCount;

	/** The quarantine amount. */
	private Integer quarantineAmount;

	/** The alarms types count. */
	private List<AlarmsTypesCount> alarmsTypesCount;

	/**
	 * Gets the devices.
	 * 
	 * @return the devices
	 */
	public List<Device> getDevices()
	{
		return devices;
	}

	/**
	 * Gets the first device.
	 * 
	 * @return the first device
	 */
	public Device getFirstDevice()
	{
		if (getDevices() != null && !getDevices().isEmpty())
		{
			return getDevices().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the devices.
	 * 
	 * @param deviceList the new devices
	 */
	public void setDevices(List<Device> deviceList)
	{
		devices = deviceList;
	}

	/**
	 * Gets the services permissions.
	 * 
	 * @return the services permissions
	 */
	public ServicesPermissions getServicesPermissions()
	{
		return servicesPermissions;
	}

	/**
	 * Sets the services permissions.
	 * 
	 * @param servicesPermissionsParam the new services permissions
	 */
	public void setServicesPermissions(ServicesPermissions servicesPermissionsParam)
	{
		servicesPermissions = servicesPermissionsParam;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the device type count list.
	 * 
	 * @return the device type count list
	 */
	public List<DeviceTypeCount> getDeviceTypeCountList()
	{
		return deviceTypeCountList;
	}

	/**
	 * Gets the first device type count list.
	 * 
	 * @return the first device type count list
	 */
	public DeviceTypeCount getFirstDeviceTypeCountList()
	{
		if (getDeviceTypeCountList() != null && !getDeviceTypeCountList().isEmpty())
		{
			return getDeviceTypeCountList().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the device type count list.
	 * 
	 * @param deviceTypeCountList the new device type count list
	 */
	public void setDeviceTypeCountList(List<DeviceTypeCount> deviceTypeCountList)
	{
		this.deviceTypeCountList = deviceTypeCountList;
	}

	/**
	 * Gets the lifecycle states.
	 * 
	 * @return the lifecycle states
	 */
	public List<LifecycleState> getLifecycleStates()
	{
		return lifecycleStates;
	}

	/**
	 * Gets the first lifecycle state.
	 * 
	 * @return the first lifecycle state
	 */
	public LifecycleState getFirstLifecycleState()
	{
		if (getLifecycleStates() != null && !getLifecycleStates().isEmpty())
		{
			return getLifecycleStates().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the lifecycle states.
	 * 
	 * @param lifecycleStates the new lifecycle states
	 */
	public void setLifecycleStates(List<LifecycleState> lifecycleStates)
	{
		this.lifecycleStates = lifecycleStates;
	}

	/**
	 * Gets the connection states.
	 * 
	 * @return the connection states
	 */
	public List<ConnectionState> getConnectionStates()
	{
		return connectionStates;
	}

	/**
	 * Sets the connection states.
	 * 
	 * @param connectionStates the new connection states
	 */
	public void setConnectionStates(List<ConnectionState> connectionStates)
	{
		this.connectionStates = connectionStates;
	}

	/**
	 * Gets the device models.
	 * 
	 * @return the device models
	 */
	public List<DeviceModel> getDeviceModels()
	{
		return deviceModels;
	}

	/**
	 * Sets the device models.
	 * 
	 * @param deviceModels the new device models
	 */
	public void setDeviceModels(List<DeviceModel> deviceModels)
	{
		this.deviceModels = deviceModels;
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
	 * Gets the water gas meter status.
	 * 
	 * @return the water gas meter status
	 */
	public List<WaterGasMeterStatusCount> getWaterGasMeterStatusCount()
	{
		return waterGasMeterStatusCount;
	}

	/**
	 * Sets the water gas meter status.
	 * 
	 * @param waterGasMeterStatusCount the new water gas meter status count
	 */
	public void setWaterGasMeterStatusCount(List<WaterGasMeterStatusCount> waterGasMeterStatusCount)
	{
		this.waterGasMeterStatusCount = waterGasMeterStatusCount;
	}

	/**
	 * Gets the quarantine amount.
	 * 
	 * @return the quarantine amount
	 */
	public Integer getQuarantineAmount()
	{
		return quarantineAmount;
	}

	/**
	 * Sets the quarantine amount.
	 * 
	 * @param quarantineAmount the new quarantine amount
	 */
	public void setQuarantineAmount(Integer quarantineAmount)
	{
		this.quarantineAmount = quarantineAmount;
	}

	/**
	 * Gets the alarms types count.
	 * 
	 * @return the alarms types count
	 */
	public List<AlarmsTypesCount> getAlarmsTypesCount()
	{
		return alarmsTypesCount;
	}

	/**
	 * Sets the alarms types count.
	 * 
	 * @param alarmsTypesCount the new alarms types count
	 */
	public void setAlarmsTypesCount(List<AlarmsTypesCount> alarmsTypesCount)
	{
		this.alarmsTypesCount = alarmsTypesCount;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceResponse [getDevices()=" + getDevices() + ", getFirstDevice()=" + getFirstDevice()
				+ ", getServicesPermissions()=" + getServicesPermissions() + ", getFileName()=" + getFileName()
				+ ", getDeviceTypeCountList()=" + getDeviceTypeCountList() + ", getFirstDeviceTypeCountList()="
				+ getFirstDeviceTypeCountList() + ", getLifecycleStates()=" + getLifecycleStates()
				+ ", getFirstLifecycleState()=" + getFirstLifecycleState() + ", getConnectionStates()="
				+ getConnectionStates()
				+ ", getDeviceModels()=" + getDeviceModels() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getAlarms()=" + getAlarms()
				+ ", getLcmRelays()=" + getLcmRelays() + "]";
	}
}
