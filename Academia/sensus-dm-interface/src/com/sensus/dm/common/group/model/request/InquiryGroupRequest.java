/**
 *
 */
package com.sensus.dm.common.group.model.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryGroupRequest.
 * 
 * @author QAT Global
 */
public class InquiryGroupRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The group type enum. */
	private GroupTypeEnum groupTypeEnum;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The groups. */
	private List<Group> groups;

	/** The ids file. */
	private File idsFile;

	/**
	 * Instantiates a new inquiry group request.
	 */
	public InquiryGroupRequest()
	{

	}

	/**
	 * Instantiates a new inquiry group request.
	 * 
	 * @param group the group
	 */
	public InquiryGroupRequest(Group group)
	{
		addGroup(group);
	}

	/**
	 * Instantiates a new inquiry group request.
	 * 
	 * @param group the group
	 * @param tenant the tenant
	 */
	public InquiryGroupRequest(Group group, DMTenant tenant)
	{
		addGroup(group);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry group request.
	 * 
	 * @param group the group
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryGroupRequest(Group group, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addGroup(group);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry group request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryGroupRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry group request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryGroupRequest(DeviceSearch deviceSearchParam)
	{
		setDeviceSearch(deviceSearchParam);
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
	 * Gets the device search.
	 * 
	 * @return the device search
	 */
	public DeviceSearch getDeviceSearch()
	{
		return deviceSearch;
	}

	/**
	 * Sets the device search.
	 * 
	 * @param deviceSearch the new device search
	 */
	public void setDeviceSearch(DeviceSearch deviceSearch)
	{
		this.deviceSearch = deviceSearch;
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
	 * Sets the groups.
	 * 
	 * @param groups the new groups
	 */
	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
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

	@Override
	public String toString()
	{
		return "InquiryGroupRequest [getGroupTypeEnum()=" + getGroupTypeEnum() + ", getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getGroups()=" + getGroups() + ", getFirstGroup()=" + getFirstGroup() + ", getIdsFile()="
				+ getIdsFile() + ", toString()=" + super.toString() + "]";
	}

}
