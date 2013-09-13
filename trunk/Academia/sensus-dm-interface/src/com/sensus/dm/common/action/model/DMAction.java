package com.sensus.dm.common.action.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.scheduler.action.Action;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tag.model.Tag;

/**
 * The Class DMAction.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class DMAction extends Action
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The id. */
	private Integer id;

	/** The total devices. */
	private Integer totalDevices;

	/** The on demand. */
	private Boolean onDemand;

	/** The groups. */
	private List<Group> groups;

	/** The tags. */
	private List<Tag> tags;

	/** The devices. */
	private List<Device> devices;

	/** The devices excluded. */
	private List<Device> devicesExcluded;

	/** The is monitored. */
	private Boolean isMonitored;

	/** The action type. */
	private ActionType actionType;

	/** The start time. */
	private Date startTime;

	/** The action time. */
	private Date actionTime;

	/** The process id. */
	private Integer processId;

	/** The rni event id. */
	private Integer rniEventId;

	/**
	 * Instantiates a new dM action.
	 */
	public DMAction()
	{
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param actionTypeParam the action type param
	 */
	public DMAction(ActionType actionTypeParam)
	{
		setActionType(actionTypeParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param idParam the id param
	 */
	public DMAction(Integer idParam)
	{
		setId(idParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param idParam the id param
	 * @param actionTypeParam the action type param
	 */
	public DMAction(Integer idParam, ActionType actionTypeParam)
	{
		setId(idParam);
		setActionType(actionTypeParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param actionTypeParam the action type param
	 * @param devicesParam the devices param
	 */
	public DMAction(ActionType actionTypeParam, List<Device> devicesParam)
	{
		setActionType(actionTypeParam);
		setDevices(devicesParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param deviceParam the device param
	 */
	public DMAction(Device deviceParam)
	{
		addDevice(deviceParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param actionTypeParam the action type param
	 * @param deviceParam the device param
	 */
	public DMAction(ActionType actionTypeParam, Device deviceParam)
	{
		setActionType(actionTypeParam);
		addDevice(deviceParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param idParam the id param
	 * @param actionTypeParam the action type param
	 * @param deviceParam the device param
	 */
	public DMAction(Integer idParam, ActionType actionTypeParam, Device deviceParam)
	{
		setId(idParam);
		setActionType(actionTypeParam);
		addDevice(deviceParam);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param group the group
	 */
	public DMAction(Group group)
	{
		addGroup(group);
	}

	/**
	 * Instantiates a new dM action.
	 * 
	 * @param tag the tag
	 */
	public DMAction(Tag tag)
	{
		addTag(tag);
	}

	/**
	 * Instantiates a new send han text message action.
	 * 
	 * @param actionId the action id
	 * @param createDate the create date
	 * @param createUser the create user
	 * @param devices the devices
	 * @param groups the groups
	 * @param actionTime the action time
	 * @param isMonitored the is monitored
	 * @param processId the process id
	 * @param tags the tags
	 * @param totalDevices the total devices
	 */
	public DMAction(Integer actionId, Date createDate, String createUser, List<Device> devices, List<Group> groups,
			Date actionTime, Boolean isMonitored, Integer processId, List<Tag> tags, Integer totalDevices)
	{
		this();
		setId(actionId);
		setCreateDate(createDate);
		setCreateUser(createUser);
		setDevices(devices);
		setGroups(groups);
		setActionTime(actionTime);
		setIsMonitored(isMonitored);
		setProcessId(processId);
		setTags(tags);
		setTotalDevices(totalDevices);
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
	 * Gets the total devices.
	 * 
	 * @return the total devices
	 */
	public Integer getTotalDevices()
	{
		return totalDevices;
	}

	/**
	 * Sets the total devices.
	 * 
	 * @param totalDevices the new total devices
	 */
	public void setTotalDevices(Integer totalDevices)
	{
		this.totalDevices = totalDevices;
	}

	/**
	 * Checks if is on demand.
	 * 
	 * @return the boolean
	 */
	public Boolean isOnDemand()
	{
		return onDemand;
	}

	/**
	 * Sets the on demand.
	 * 
	 * @param onDemand the new on demand
	 */
	public void setOnDemand(Boolean onDemand)
	{
		this.onDemand = onDemand;
	}

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<Group> getGroups()
	{
		return groups;
	}

	/**
	 * Gets the first group.
	 * 
	 * @return the first group
	 */
	public Group getFirstGroup()
	{
		if (getGroups() != null && !getGroups().isEmpty())
		{
			return getGroups().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups the new groups
	 */
	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}

	/**
	 * Adds the group.
	 * 
	 * @param groupObject the group object
	 */
	public void addGroup(Group groupObject)
	{
		if (getGroups() == null)
		{
			setGroups(new ArrayList<Group>());
		}

		getGroups().add(groupObject);
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return tags;
	}

	/**
	 * Gets the first tag.
	 * 
	 * @return the first tag
	 */
	public Tag getFirstTag()
	{
		if (getTags() != null && !getTags().isEmpty())
		{
			return getTags().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags the new tags
	 */
	public void setTags(List<Tag> tags)
	{
		this.tags = tags;
	}

	/**
	 * Adds the tag.
	 * 
	 * @param tagObject the tag object
	 */
	public void addTag(Tag tagObject)
	{
		if (getTags() == null)
		{
			setTags(new ArrayList<Tag>());
		}

		getTags().add(tagObject);
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
	 * @param devices the new devices
	 */
	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	/**
	 * Adds the device.
	 * 
	 * @param deviceObject the device object
	 */
	public void addDevice(Device deviceObject)
	{
		if (getDevices() == null)
		{
			setDevices(new ArrayList<Device>());
		}

		getDevices().add(deviceObject);
	}

	/**
	 * Gets the devices excluded.
	 * 
	 * @return the devices excluded
	 */
	public List<Device> getDevicesExcluded()
	{
		return devicesExcluded;
	}

	/**
	 * Sets the devices excluded.
	 * 
	 * @param devicesExcluded the new devices excluded
	 */
	public void setDevicesExcluded(List<Device> devicesExcluded)
	{
		this.devicesExcluded = devicesExcluded;
	}

	/**
	 * Gets the first devices excluded.
	 * 
	 * @return the first devices excluded
	 */
	public Device getFirstDevicesExcluded()
	{
		if (getDevicesExcluded() != null && !getDevicesExcluded().isEmpty())
		{
			return getDevicesExcluded().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the devices excluded.
	 * 
	 * @param deviceObject the device object
	 */
	public void addDevicesExcluded(Device deviceObject)
	{
		if (getDevicesExcluded() == null)
		{
			setDevicesExcluded(new ArrayList<Device>());
		}

		getDevicesExcluded().add(deviceObject);
	}

	/**
	 * Gets the checks if is monitored.
	 * 
	 * @return the checks if is monitored
	 */
	public Boolean getIsMonitored()
	{
		return isMonitored;
	}

	/**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the new checks if is monitored
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		this.isMonitored = isMonitored;
	}

	/**
	 * Gets the action type.
	 * 
	 * @return the action type
	 */
	public ActionType getActionType()
	{
		return actionType;
	}

	/**
	 * Sets the action type.
	 * 
	 * @param actionType the new action type
	 */
	public void setActionType(ActionType actionType)
	{
		this.actionType = actionType;
	}

	/**
	 * Gets the start time.
	 * 
	 * @return the start time
	 */
	public Date getStartTime()
	{
		return startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime the new start time
	 */
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * Gets the action time.
	 * 
	 * @return the action time
	 */
	public Date getActionTime()
	{
		return actionTime;
	}

	/**
	 * Sets the action time.
	 * 
	 * @param actionTime the new action time
	 */
	public void setActionTime(Date actionTime)
	{
		this.actionTime = actionTime;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the rni event id.
	 * 
	 * @return the rni event id
	 */
	public Integer getRniEventId()
	{
		return rniEventId;
	}

	/**
	 * Sets the rni event id.
	 * 
	 * @param rniEventId the new rni event id
	 */
	public void setRniEventId(Integer rniEventId)
	{
		this.rniEventId = rniEventId;
	}

	/**
	 * Checks if is process required.
	 * 
	 * @return the boolean
	 */
	public Boolean isProcessRequired()
	{
		return true;
	}

	/**
	 * Send by device.
	 * 
	 * @return the boolean
	 */
	public Boolean sendByDevice()
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "DMAction [getId()=" + getId() + ", getTotalDevices()=" + getTotalDevices() + ", isOnDemand()="
				+ isOnDemand() + ", getGroups()=" + getGroups() + ", getFirstGroup()=" + getFirstGroup()
				+ ", getTags()=" + getTags() + ", getFirstTag()=" + getFirstTag() + ", getDevices()=" + getDevices()
				+ ", getFirstDevice()=" + getFirstDevice() + ", getIsMonitored()=" + getIsMonitored()
				+ ", getActionType()=" + getActionType() + ", getStartTime()=" + getStartTime() + ", getActionTime()="
				+ getActionTime() + ", getProcessId()=" + getProcessId() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate()
				+ ", getDevicesExcluded=" + getDevicesExcluded() + ", getRniEventId=" + getRniEventId() + "]";
	}
}
