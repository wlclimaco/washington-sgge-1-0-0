package com.sensus.dm.elec.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;

/**
 * The Class SmartPoint.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class SmartPoint extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The id. */
	private Integer id;

	/** The related smart point. */
	private List<SmartPoint> relatedSmartPoint;

	/** The radios. */
	private List<Radio> radios;

	/** The devices. */
	private List<Device> devices;

	/**
	 * Instantiates a new smart point.
	 */
	public SmartPoint()
	{
	}

	/**
	 * Instantiates a new smart point.
	 * 
	 * @param endPointId the end point id
	 * @param paramRelatedSmartPoint the param related smart point
	 * @param paramRadios the param radios
	 */
	public SmartPoint(Integer endPointId, List<SmartPoint> paramRelatedSmartPoint, List<Radio> paramRadios)
	{
		setId(endPointId);
		setRelatedSmartPoint(paramRelatedSmartPoint);
		setRadios(paramRadios);
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
	 * Gets the related smart point.
	 * 
	 * @return the related smart point
	 */
	public List<SmartPoint> getRelatedSmartPoint()
	{
		return relatedSmartPoint;
	}

	/**
	 * Sets the related smart point.
	 * 
	 * @param relatedSmartPoint the new related smart point
	 */
	public void setRelatedSmartPoint(List<SmartPoint> relatedSmartPoint)
	{
		this.relatedSmartPoint = relatedSmartPoint;
	}

	/**
	 * Gets the radios.
	 * 
	 * @return the radios
	 */
	public List<Radio> getRadios()
	{
		return radios;
	}

	/**
	 * Sets the radios.
	 * 
	 * @param radios the new radios
	 */
	public void setRadios(List<Radio> radios)
	{
		this.radios = radios;
	}

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
	 * Sets the devices.
	 * 
	 * @param devices the new devices
	 */
	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	/**
	 * Gets the process.
	 * 
	 * @return the process
	 */
	public Device getFirstDevice()
	{
		if ((getDevices() != null) && !getDevices().isEmpty())
		{
			return getDevices().get(FIRST);
		}

		return null;
	}

	public void addDevice(Device device)
	{
		if (getDevices() == null)
		{
			setDevices(new ArrayList<Device>());
		}

		getDevices().add(device);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "SmartPoint [getId()=" + getId() + ", getRelatedSmartPoint()=" + getRelatedSmartPoint()
				+ ", getRadios()=" + getRadios() + ", getDevices()=" + getDevices() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
