package com.sensus.dm.common.group.model.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class GroupRequest.
 * 
 * @author QAT Brazil
 */
public class GroupRequest extends TenantRequest
{
	/** The groups. */
	private List<Group> groups;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/** The max device count. */
	private Integer maxDeviceCount;

	/** The ids file. */
	private File idsFile;

	/** The group replace. */
	private boolean groupReplace;

	/**
	 * Instantiates a new group request.
	 */
	public GroupRequest()
	{
	}

	/**
	 * Instantiates a new group request.
	 * 
	 * @param group the group
	 */
	public GroupRequest(Group group)
	{
		addGroup(group);
	}

	/**
	 * Instantiates a new group request.
	 * 
	 * @param userContext the user context
	 */
	public GroupRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new group request.
	 * 
	 * @param group the group
	 * @param userContext the user context
	 */
	public GroupRequest(Group group, UserContext userContext)
	{
		addGroup(group);
		setUserContext(userContext);
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
			return getGroups().get(0);
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
	 * @param group the group
	 */
	public void addGroup(Group group)
	{
		if (getGroups() == null)
		{
			setGroups(new ArrayList<Group>());
		}

		getGroups().add(group);
	}

	/**
	 * Gets the retrieve history.
	 * 
	 * @return the retrieve history
	 */
	public Boolean getRetrieveHistory()
	{
		return retrieveHistory;
	}

	/**
	 * Sets the retrieve history.
	 * 
	 * @param retrieveHistory the new retrieve history
	 */
	public void setRetrieveHistory(Boolean retrieveHistory)
	{
		this.retrieveHistory = retrieveHistory;
	}

	/**
	 * Gets the max device count.
	 * 
	 * @return the max device count
	 */
	public Integer getMaxDeviceCount()
	{
		return maxDeviceCount;
	}

	/**
	 * Sets the max device count.
	 * 
	 * @param maxDeviceCount the new max device count
	 */
	public void setMaxDeviceCount(Integer maxDeviceCount)
	{
		this.maxDeviceCount = maxDeviceCount;
	}

	/**
	 * Gets the ids file.
	 * 
	 * @return the ids file
	 */
	public File getIdsFile()
	{
		return idsFile;
	}

	/**
	 * Sets the ids file.
	 * 
	 * @param idsFile the new ids file
	 */
	public void setIdsFile(File idsFile)
	{
		this.idsFile = idsFile;
	}

	/**
	 * Checks if is group replace.
	 * 
	 * @return true, if is group replace
	 */
	public boolean isGroupReplace()
	{
		return groupReplace;
	}

	/**
	 * Sets the group replace.
	 * 
	 * @param groupReplace the new group replace
	 */
	public void setGroupReplace(boolean groupReplace)
	{
		this.groupReplace = groupReplace;
	}

	@Override
	public String toString()
	{
		return "GroupRequest [getGroups()=" + getGroups() + ", getFirstGroup()=" + getFirstGroup()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", getMaxDeviceCount()="
				+ getMaxDeviceCount() + ", getIdsFile()=" + getIdsFile() + ", isGroupReplace()=" + isGroupReplace()
				+ ", toString()=" + super.toString() + "]";
	}

}
