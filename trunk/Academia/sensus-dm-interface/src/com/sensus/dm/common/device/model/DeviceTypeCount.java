package com.sensus.dm.common.device.model;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.SensusModel;

/**
 * The Class DeviceTypeCount.
 * 
 * @author - QAT Brazil
 * 
 */
@SuppressWarnings("serial")
public class DeviceTypeCount extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The device. */
	private Device device;

	/** The device count. */
	private Integer deviceCount;

	/**
	 * Instantiates a new device type.
	 */
	public DeviceTypeCount()
	{

	}

	/**
	 * Instantiates a new device type count.
	 * 
	 * @param deviceParam the device param
	 */
	public DeviceTypeCount(Device deviceParam)
	{
		setDevice(deviceParam);
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the device count.
	 * 
	 * @return the device count
	 */
	public Integer getDeviceCount()
	{
		return deviceCount;
	}

	/**
	 * Sets the device count.
	 * 
	 * @param deviceCount the new device count
	 */
	public void setDeviceCount(Integer deviceCount)
	{
		this.deviceCount = deviceCount;
	}

	/**
	 * Gets the device.
	 * 
	 * @return the device
	 */
	public Device getDevice()
	{
		return device;
	}

	/**
	 * Sets the device.
	 * 
	 * @param device the new device
	 */
	public void setDevice(Device device)
	{
		this.device = device;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "DeviceTypeCount [getDeviceCount()=" + getDeviceCount() + ", getDevice()=" + getDevice()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
