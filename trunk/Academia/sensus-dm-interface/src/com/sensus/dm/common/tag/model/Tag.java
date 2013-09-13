package com.sensus.dm.common.tag.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.cbof.model.Device;

/**
 * The Class Tag.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class Tag extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The devices count. */
	private Integer devicesCount;

	/** The address related. */
	private Boolean addressRelated;

	/** The devices. */
	private List<Device> devices;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new tag.
	 */
	public Tag()
	{
	}

	/**
	 * Instantiates a new tag.
	 * 
	 * @param value the value
	 */
	public Tag(Integer value)
	{
		setId(value);
	}

	/**
	 * Instantiates a new tag.
	 * 
	 * @param valueName the value name
	 */
	public Tag(String valueName)
	{
		setName(valueName);
	}

	/**
	 * Instantiates a new tag.
	 * 
	 * @param idParam the id
	 * @param nameParam the name
	 */
	public Tag(Integer idParam, String nameParam)
	{
		setId(idParam);
		setName(nameParam);
	}

	/**
	 * Instantiates a new tag.
	 * 
	 * @param idParam the id param
	 * @param nameParam the name param
	 * @param descriptionParam the description param
	 */
	public Tag(Integer idParam, String nameParam, String descriptionParam)
	{
		setId(idParam);
		setName(nameParam);
		setDescription(descriptionParam);
	}

	/**
	 * Instantiates a new tag.
	 * 
	 * @param valueId the value id
	 * @param valueName the value name
	 * @param count the count
	 * @param valueAddressRelated the value address related
	 */
	public Tag(Integer valueId, String valueName, Integer count, Boolean valueAddressRelated)
	{
		setId(valueId);
		setName(valueName);
		setDevicesCount(count);
		setAddressRelated(valueAddressRelated);
	}

	/**
	 * Instantiates a new tag.
	 * 
	 * @param device the device
	 */
	public Tag(Device device)
	{
		addDevice(device);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

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
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the devices count.
	 * 
	 * @return the devices count
	 */
	public Integer getDevicesCount()
	{
		return devicesCount;
	}

	/**
	 * Sets the devices count.
	 * 
	 * @param devicesCount the new devices count
	 */
	public void setDevicesCount(Integer devicesCount)
	{
		this.devicesCount = devicesCount;
	}

	/**
	 * Gets the address related.
	 * 
	 * @return the address related
	 */
	public Boolean getAddressRelated()
	{
		return addressRelated;
	}

	/**
	 * Sets the address related.
	 * 
	 * @param addressRelated the new address related
	 */
	public void setAddressRelated(Boolean addressRelated)
	{
		this.addressRelated = addressRelated;
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
	 * Adds the device.
	 * 
	 * @param device the device
	 */
	public void addDevice(Device device)
	{
		if (getDevices() == null)
		{
			setDevices(new ArrayList<Device>());
		}

		getDevices().add(device);
	}

	/**
	 * Gets the first device.
	 * 
	 * @return the first device
	 */
	public Device getFirstDevice()
	{
		if ((getDevices() != null) && !getDevices().isEmpty())
		{
			return getDevices().get(FIRST);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Tag [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getDevicesCount()=" + getDevicesCount() + ", getAddressRelated()=" + getAddressRelated()
				+ ", getDevices()=" + getDevices() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
