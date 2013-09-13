package com.sensus.dm.common.group.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.SensusModel;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;

/**
 * The Class Group.
 * 
 * @author QAT Global.
 * 
 */
@SuppressWarnings("serial")
public class Group extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The devices count. */
	private Integer devicesCount;

	/** The old name. */
	private String oldName;

	/** The group type enum. */
	private GroupTypeEnum groupTypeEnum;

	/** The parent group. */
	private Group parentGroup;

	/** The devices. */
	private List<Device> devices;

	/** The device type enum. */
	private DeviceTypeEnum deviceType;

	/** The han device type. */
	private HanDeviceTypeEnum hanDeviceType;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new group.
	 */
	public Group()
	{
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param groupId the group id
	 */
	public Group(Integer groupId)
	{
		setId(groupId);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param nameParam the name
	 */
	public Group(String nameParam)
	{
		setName(nameParam);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param groupId the group id
	 * @param valueName the value name
	 */
	public Group(Integer groupId, String valueName)
	{
		setId(groupId);
		setName(valueName);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param groupId the group id
	 * @param valueName the value name
	 * @param valueDescription the value description
	 */
	public Group(Integer groupId, String valueName, String valueDescription)
	{
		setId(groupId);
		setName(valueName);
		setDescription(valueDescription);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param valueId the value id
	 * @param valueName the value name
	 * @param valueDescription the value description
	 * @param count the count
	 * @param groupTypeEnumValue the group type enum value
	 * @param valueParentGroup the value parent group
	 * @param deviceList the devices
	 */
	public Group(Integer valueId, String valueName, String valueDescription, Integer count,
			GroupTypeEnum groupTypeEnumValue, Group valueParentGroup, List<Device> deviceList)
	{
		setId(valueId);
		setName(valueName);
		setDescription(valueDescription);
		setDevicesCount(count);
		setGroupTypeEnum(groupTypeEnumValue);
		setParentGroup(valueParentGroup);
		setDevices(deviceList);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param groupId the group id
	 * @param groupTypeEnumParam the group type enum
	 */
	public Group(Integer groupId, GroupTypeEnum groupTypeEnumParam)
	{
		setId(groupId);
		setGroupTypeEnum(groupTypeEnumParam);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param nameParam the name param
	 * @param descriptionGroupParam the description group param
	 * @param groupTypeEnumParam the group type enum param
	 */
	public Group(String nameParam, String descriptionGroupParam, GroupTypeEnum groupTypeEnumParam)
	{
		setName(nameParam);
		setDescription(descriptionGroupParam);
		setGroupTypeEnum(groupTypeEnumParam);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param device the device
	 */
	public Group(Device device)
	{
		addDevice(device);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param deviceTypeParam the device type param
	 */
	public Group(DeviceTypeEnum deviceTypeParam)
	{
		setDeviceType(deviceTypeParam);
	}

	/**
	 * Instantiates a new group.
	 * 
	 * @param groupId the group id
	 * @param deviceTypeParam the device type param
	 */
	public Group(Integer groupId, DeviceTypeEnum deviceTypeParam)
	{
		setId(groupId);
		setDeviceType(deviceTypeParam);
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
	 * @param groupId the new id
	 */
	public void setId(Integer groupId)
	{
		id = groupId;
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
	 * @param groupName the new name
	 */
	public void setName(String groupName)
	{
		name = groupName;
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
	 * @param descriptionGroup the new description
	 */
	public void setDescription(String descriptionGroup)
	{
		description = descriptionGroup;
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
	 * Gets the old name.
	 * 
	 * @return the old name
	 */
	public String getOldName()
	{
		return oldName;
	}

	/**
	 * Sets the old name.
	 * 
	 * @param oldNameStr the new old name
	 */
	public void setOldName(String oldNameStr)
	{
		oldName = oldNameStr;
	}

	/**
	 * Gets the group type enum.
	 * 
	 * @return the group type enum
	 */
	public GroupTypeEnum getGroupTypeEnum()
	{
		return groupTypeEnum;
	}

	/**
	 * Sets the group type enum.
	 * 
	 * @param groupTypeEnum the new group type enum
	 */
	public void setGroupTypeEnum(GroupTypeEnum groupTypeEnum)
	{
		this.groupTypeEnum = groupTypeEnum;
	}

	/**
	 * Gets the group type enum value.
	 * 
	 * @return the group type enum value
	 */
	public Integer getGroupTypeEnumValue()
	{
		GroupTypeEnum groupType = getGroupTypeEnum();

		if (groupType != null)
		{
			return groupType.getValue();
		}

		return null;
	}

	/**
	 * Sets the group type enum value.
	 * 
	 * @param groupTypeEnumValue the new group type enum value
	 */
	public void setGroupTypeEnumValue(Integer groupTypeEnumValue)
	{
		setGroupTypeEnum(GroupTypeEnum.enumForValue(groupTypeEnumValue));
	}

	/**
	 * Gets the parent group.
	 * 
	 * @return the parent group
	 */
	public Group getParentGroup()
	{
		return parentGroup;
	}

	/**
	 * Sets the parent group.
	 * 
	 * @param parentGroup the new parent group
	 */
	public void setParentGroup(Group parentGroup)
	{
		this.parentGroup = parentGroup;
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
	 * @param deviceType the new device type
	 */
	public void setDeviceType(DeviceTypeEnum deviceType)
	{
		this.deviceType = deviceType;
	}

	/**
	 * Gets the device type value.
	 * 
	 * @return the device type value
	 */
	public Integer getDeviceTypeValue()
	{
		if (getDeviceType() != null)
		{
			return getDeviceType().getValue();
		}

		return null;
	}

	/**
	 * Sets the device type value.
	 * 
	 * @param deviceTypeParam the new device type value
	 */
	public void setDeviceTypeValue(Integer deviceTypeParam)
	{
		setDeviceType(DeviceTypeEnum.enumForValue(deviceTypeParam));
	}

	/**
	 * Gets the han device type.
	 * 
	 * @return the han device type
	 */
	public HanDeviceTypeEnum getHanDeviceType()
	{
		return hanDeviceType;
	}

	/**
	 * Sets the han device type.
	 * 
	 * @param hanDeviceType the new han device type
	 */
	public void setHanDeviceType(HanDeviceTypeEnum hanDeviceType)
	{
		this.hanDeviceType = hanDeviceType;
	}

	/**
	 * Gets the han device type value.
	 * 
	 * @return the han device type value
	 */
	public Integer getHanDeviceTypeValue()
	{
		if (getHanDeviceType() != null)
		{
			return getHanDeviceType().getValue();
		}

		return null;
	}

	/**
	 * Sets the han device type value.
	 * 
	 * @param hanDeviceTypeParam the new han device type value
	 */
	public void setHanDeviceTypeValue(Integer hanDeviceTypeParam)
	{
		setHanDeviceType(HanDeviceTypeEnum.enumForValue(hanDeviceTypeParam));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Group [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ ", getDevicesCount()=" + getDevicesCount() + ", getGroupTypeEnum()=" + getGroupTypeEnum()
				+ ", getGroupTypeEnumValue()=" + getGroupTypeEnumValue() + ", getHanDeviceType()=" + getHanDeviceType()
				+ ", getParentGroup()=" + getParentGroup() + ", getDevices()=" + getDevices()
				+ ", getOldName()=" + getOldName() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate()
				+ ", getDeviceType()=" + getDeviceType() + "]";
	}
}
