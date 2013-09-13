package com.sensus.dm.common.base.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.SensusModel;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.process.model.ProcessCategory;

/**
 * The Class BaseSearch.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class BaseSearch extends SensusModel
{

	/** The Constant TIME_OFFSET. */
	private static final Integer TIME_OFFSET = 10;

	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The group types. */
	private List<GroupTypeEnum> groupTypes;

	/** The search text. */
	private String searchText;

	/** The users. */
	private List<UserContext> users;

	/** The contain devices. */
	private Boolean containDevices;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The action type enums. */
	private List<ActionTypeEnum> actionTypeEnums;

	/** The process types. */
	private List<ProcessCategory> processCategories;

	/** The device types. */
	private List<DeviceTypeEnum> deviceTypes;

	/**
	 * Instantiates a new base search.
	 */
	public BaseSearch()
	{
	}

	/**
	 * Instantiates a new base search.
	 * 
	 * @param containDevicesParam the contain devices param
	 */
	public BaseSearch(Boolean containDevicesParam)
	{
		setContainDevices(containDevicesParam);
	}

	/**
	 * Instantiates a new base search.
	 * 
	 * @param searchTextParam the search text param
	 * @param groupTypesParam the group types param
	 */
	public BaseSearch(String searchTextParam, List<GroupTypeEnum> groupTypesParam)
	{
		setSearchText(searchTextParam);
		setGroupTypes(groupTypesParam);
	}

	/**
	 * Instantiates a new base search.
	 * 
	 * @param groupTypesParam the group types param
	 */
	public BaseSearch(List<GroupTypeEnum> groupTypesParam)
	{
		setGroupTypes(groupTypesParam);
	}

	/**
	 * Instantiates a new base search.
	 * 
	 * @param deviceTypeParam the device type param
	 */
	public BaseSearch(DeviceTypeEnum deviceTypeParam)
	{
		addDeviceType(deviceTypeParam);
	}

	/**
	 * Gets the group types.
	 * 
	 * @return the group types
	 */
	public List<GroupTypeEnum> getGroupTypes()
	{
		return groupTypes;
	}

	/**
	 * Sets the group types.
	 * 
	 * @param groupTypes the new group types
	 */
	public void setGroupTypes(List<GroupTypeEnum> groupTypes)
	{
		this.groupTypes = groupTypes;
	}

	/**
	 * Gets the search text.
	 * 
	 * @return the search text
	 */
	public String getSearchText()
	{
		return searchText;
	}

	/**
	 * Sets the search text.
	 * 
	 * @param searchText the new search text
	 */
	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}

	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public List<UserContext> getUsers()
	{
		return users;
	}

	/**
	 * Sets the users.
	 * 
	 * @param users the new users
	 */
	public void setUsers(List<UserContext> users)
	{
		this.users = users;
	}

	/**
	 * Gets the contain devices.
	 * 
	 * @return the contain devices
	 */
	public Boolean getContainDevices()
	{
		return containDevices;
	}

	/**
	 * Sets the contain devices.
	 * 
	 * @param containDevices the new contain devices
	 */
	public void setContainDevices(Boolean containDevices)
	{
		this.containDevices = containDevices;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the start date int.
	 * 
	 * @return the start date int
	 */
	public Integer getStartDateInt()
	{
		if (getStartDate() == null)
		{
			return null;
		}

		return convertDateToInteger(getStartDate());
	}

	/**
	 * Gets the end date int.
	 * 
	 * @return the end date int
	 */
	public Integer getEndDateInt()
	{
		if (getEndDate() == null)
		{
			return null;
		}

		return convertDateToInteger(getEndDate());
	}

	/**
	 * Gets the action type enums.
	 * 
	 * @return the action type enums
	 */
	public List<ActionTypeEnum> getActionTypeEnums()
	{
		return actionTypeEnums;
	}

	/**
	 * Sets the action type enums.
	 * 
	 * @param actionTypeEnums the new action type enums
	 */
	public void setActionTypeEnums(List<ActionTypeEnum> actionTypeEnums)
	{
		this.actionTypeEnums = actionTypeEnums;
	}

	/**
	 * Gets the process categories.
	 * 
	 * @return the process categories
	 */
	public List<ProcessCategory> getProcessCategories()
	{
		return processCategories;
	}

	/**
	 * Sets the process categories.
	 * 
	 * @param processCategories the new process categories
	 */
	public void setProcessCategories(List<ProcessCategory> processCategories)
	{
		this.processCategories = processCategories;
	}

	/**
	 * Gets the device types.
	 * 
	 * @return the device types
	 */
	public List<DeviceTypeEnum> getDeviceTypes()
	{
		return deviceTypes;
	}

	/**
	 * Sets the device types.
	 * 
	 * @param deviceTypes the new device types
	 */
	public void setDeviceTypes(List<DeviceTypeEnum> deviceTypes)
	{
		this.deviceTypes = deviceTypes;
	}

	/**
	 * Adds the device type.
	 * 
	 * @param deviceType the device type
	 */
	public void addDeviceType(DeviceTypeEnum deviceType)
	{
		if (getDeviceTypes() == null)
		{
			setDeviceTypes(new ArrayList<DeviceTypeEnum>());
		}
		getDeviceTypes().add(deviceType);
	}

	/**
	 * Gets the first device type.
	 * 
	 * @return the first device type
	 */
	public DeviceTypeEnum getFirstDeviceType()
	{
		if (getDeviceTypes() != null && !getDeviceTypes().isEmpty())
		{
			return getDeviceTypes().get(FIRST);
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
		return "BaseSearch [getGroupTypes()=" + getGroupTypes() + ", getSearchText()=" + getSearchText()
				+ ", getUsers()=" + getUsers() + ", getContainDevices()=" + getContainDevices() + ", getStartDate()="
				+ getStartDate() + ", getEndDate()=" + getEndDate() + ", getStartDateInt()=" + getStartDateInt()
				+ ", getEndDateInt()=" + getEndDateInt() + ", getActionTypeEnums()=" + getActionTypeEnums()
				+ ", getProcessCategories()=" + getProcessCategories() + ", getDeviceTypes()=" + getDeviceTypes()
				+ ", toString()=" + super.toString() + "]";
	}

	/**
	 * Convert date to integer.
	 * 
	 * @param date the date
	 * @return the integer
	 */
	private Integer convertDateToInteger(Date date)
	{
		String timeStamp =
				String.valueOf(date.getTime()
						+ TimeZone.getTimeZone(Calendar.getInstance().getTimeZone().getID()).getOffset(
								date.getTime()));

		return Integer.parseInt(StringUtils.mid(timeStamp, 0, TIME_OFFSET));
	}
}
