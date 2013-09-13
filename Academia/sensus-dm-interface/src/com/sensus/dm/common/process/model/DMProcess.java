package com.sensus.dm.common.process.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class DMProcess.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class DMProcess extends Process
{

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The properties. */
	private List<Property> properties;

	/** The property device. */
	private String propertyDevice;

	/** The property id. */
	private String propertyId;

	/** The property valeu. */
	private String propertyValue;

	/** The property file path. */
	private String propertyFilePath;

	/** The property meter id. */
	private String propertyMeterId;

	/** The property_device_type. */
	private String propertyDeviceType;

	/** The property han device type. */
	private String propertyHanDeviceType;

	/** The property link. */
	private String propertyLink;

	/**
	 * Instantiates a new dM process.
	 */
	public DMProcess()
	{
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param id the id
	 */
	public DMProcess(Integer id)
	{
		super(id);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param id the id
	 * @param statusEnum the status enum
	 */
	public DMProcess(Integer id, ProcessStatusEnum statusEnum)
	{
		super(id);
		setProcessStatusEnum(statusEnum);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param statusEnum the status enum
	 * @param propertyList the property list
	 */
	public DMProcess(ProcessStatusEnum statusEnum, List<Property> propertyList)
	{
		setProcessStatusEnum(statusEnum);
		setProperties(propertyList);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param idValue the id value
	 * @param processItemsValue the process items value
	 * @param descriptionValue the description value
	 * @param parentProcessValue the parent process value
	 */
	public DMProcess(Integer idValue, List<ProcessItem> processItemsValue, String descriptionValue,
			Process parentProcessValue)
	{
		setId(idValue);
		setProcessItems(processItemsValue);
		setDescription(descriptionValue);
		setParentProcess(parentProcessValue);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param action the action
	 */
	public DMProcess(DMAction actionParam)
	{
		setAction(actionParam);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param actionParam the action param
	 * @param propertiesParam the properties param
	 * @param rniEventIdParam the rni event id param
	 */
	public DMProcess(DMAction actionParam, List<Property> propertiesParam, Integer rniEventIdParam)
	{
		setAction(actionParam);
		setProperties(propertiesParam);
		setRniEventId(rniEventIdParam);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param processId the process id
	 * @param action the action
	 */
	public DMProcess(Integer processId, DMAction action)
	{
		setId(processId);
		setAction(action);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param processId the process id
	 * @param action the action
	 * @param rniEventId the rni event id
	 */
	public DMProcess(Integer processId, DMAction action, Integer rniEventId)
	{
		this(processId, action);
		setRniEventId(rniEventId);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param processItem the process item
	 */
	public DMProcess(ProcessItem processItem)
	{
		addProcessItem(processItem);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param processId the process id
	 * @param processItem the process item
	 */
	public DMProcess(Integer processId, ProcessItem processItem)
	{
		setId(processId);
		addProcessItem(processItem);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param action the action
	 * @param processItem the process item
	 */
	public DMProcess(DMAction action, ProcessItem processItem)
	{
		setAction(action);
		addProcessItem(processItem);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param action the action
	 * @param processItem the process item
	 * @param status the status
	 */
	public DMProcess(DMAction action, ProcessItem processItem, ProcessStatusEnum status)
	{
		this(action, processItem);
		setProcessStatusEnum(status);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param processItems the process items
	 */
	public DMProcess(List<ProcessItem> processItems)
	{
		setProcessItems(processItems);
	}

	/**
	 * Instantiates a new dM process.
	 * 
	 * @param processId the process id
	 * @param processItems the process items
	 */
	public DMProcess(Integer processId, List<ProcessItem> processItems)
	{
		setId(processId);
		setProcessItems(processItems);
	}

	/**
	 * Instantiates a new process.
	 * 
	 * @param id the id
	 * @param name the name
	 * @param modifyUser the modify user
	 * @param action the action
	 * @param startTime the start time
	 * @param failed the failed
	 * @param total the total
	 */
	public DMProcess(Integer id, String name, String modifyUser, DMAction action, Date startTime, Integer failed,
			Integer total)
	{
		setId(id);
		setDescription(name);
		setCreateUser(modifyUser);
		setAction(action);
		setStartTime(startTime);
		setFailedSmartpoints(failed);
		setTotalSmartpoints(total);
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public List<Property> getProperties()
	{
		return properties;
	}

	/**
	 * Gets the first property.
	 * 
	 * @return the first property
	 */
	public Property getFirstProperty()
	{
		if (getProperties() != null && !getProperties().isEmpty())
		{
			return getProperties().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties)
	{
		this.properties = properties;
	}

	/**
	 * Adds the property.
	 * 
	 * @param property the property
	 */
	public void addProperty(Property property)
	{
		if (getProperties() == null)
		{
			setProperties(new ArrayList<Property>());
		}

		getProperties().add(property);
	}

	/**
	 * Gets the property device.
	 * 
	 * @return the propertyDevice
	 */
	public String getPropertyDevice()
	{
		return propertyDevice;
	}

	/**
	 * Sets the property device.
	 * 
	 * @param propertyDevice the propertyDevice to set
	 */
	public void setPropertyDevice(String propertyDevice)
	{
		this.propertyDevice = propertyDevice;
	}

	/**
	 * Gets the property id.
	 * 
	 * @return the propertyId
	 */
	public String getPropertyId()
	{
		return propertyId;
	}

	/**
	 * Sets the property id.
	 * 
	 * @param propertyId the propertyId to set
	 */
	public void setPropertyId(String propertyId)
	{
		this.propertyId = propertyId;
	}

	/**
	 * Gets the property value.
	 * 
	 * @return the propertyValue
	 */
	public String getPropertyValue()
	{
		return propertyValue;
	}

	/**
	 * Sets the property value.
	 * 
	 * @param propertyValue the new property value
	 */
	public void setPropertyValue(String propertyValue)
	{
		this.propertyValue = propertyValue;
	}

	/**
	 * Gets the property file path.
	 * 
	 * @return the propertyFilePath
	 */
	public String getPropertyFilePath()
	{
		return propertyFilePath;
	}

	/**
	 * Sets the property file path.
	 * 
	 * @param propertyFilePath the propertyFilePath to set
	 */
	public void setPropertyFilePath(String propertyFilePath)
	{
		this.propertyFilePath = propertyFilePath;
	}

	/**
	 * Gets the property meter id.
	 * 
	 * @return the propertyMeterId
	 */
	public String getPropertyMeterId()
	{
		return propertyMeterId;
	}

	/**
	 * Sets the property meter id.
	 * 
	 * @param propertyMeterId the propertyMeterId to set
	 */
	public void setPropertyMeterId(String propertyMeterId)
	{
		this.propertyMeterId = propertyMeterId;
	}

	/**
	 * Gets the property device type.
	 * 
	 * @return the propertyDeviceType
	 */
	public String getPropertyDeviceType()
	{
		return propertyDeviceType;
	}

	/**
	 * Sets the property device type.
	 * 
	 * @param propertyDeviceType the propertyDeviceType to set
	 */
	public void setPropertyDeviceType(String propertyDeviceType)
	{
		this.propertyDeviceType = propertyDeviceType;
	}

	/**
	 * Gets the property han device type.
	 * 
	 * @return the property han device type
	 */
	public String getPropertyHanDeviceType()
	{
		return propertyHanDeviceType;
	}

	/**
	 * Sets the property han device type.
	 * 
	 * @param propertyHanDeviceType the new property han device type
	 */
	public void setPropertyHanDeviceType(String propertyHanDeviceType)
	{
		this.propertyHanDeviceType = propertyHanDeviceType;
	}

	/**
	 * Gets the property link.
	 * 
	 * @return the property link
	 */
	public String getPropertyLink()
	{
		return propertyLink;
	}

	/**
	 * Sets the property link.
	 * 
	 * @param propertyLink the new property link
	 */
	public void setPropertyLink(String propertyLink)
	{
		this.propertyLink = propertyLink;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.model.Process#toString()
	 */
	@Override
	public String toString()
	{
		return "DMProcess [getProperties()=" + getProperties() + ", getFirstProperty()=" + getFirstProperty()
				+ ", getPropertyDevice()=" + getPropertyDevice() + ", getPropertyId()=" + getPropertyId()
				+ ", getPropertyValue()=" + getPropertyValue() + ", getPropertyFilePath()=" + getPropertyFilePath()
				+ ", getPropertyMeterId()=" + getPropertyMeterId() + ", getPropertyDeviceType()="
				+ getPropertyDeviceType() + ", getPropertyHanDeviceType()=" + getPropertyHanDeviceType()
				+ ",getPropertyLink()=" + getPropertyLink() + ", toString()=" + super.toString() + "]";
	}
}
