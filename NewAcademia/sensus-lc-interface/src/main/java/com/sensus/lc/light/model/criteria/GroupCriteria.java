package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains criteria related to a Group.<br>
 * Note when there is no criteria for a Group the property referring to this type should be NULL indicating
 * "all groups".
 */
public class GroupCriteria
{

	/**
	 * Attributes.
	 */
	private List<Integer> groupIdList = new ArrayList<>(); // Just list the group ID in this collection

	/** The not in group id list. */
	private List<Integer> notInGroupIdList = new ArrayList<>();

	/**
	 * Gets the group id list.
	 *
	 * @return the groupIdList
	 */
	public List<Integer> getGroupIdList()
	{
		return groupIdList;
	}

	/**
	 * Sets the group id list.
	 *
	 * @param groupIdList the groupIdList to set
	 */
	public void setGroupIdList(List<Integer> groupIdList)
	{
		this.groupIdList = groupIdList;
	}

	/**
	 * Gets the not in group id list.
	 *
	 * @return the notInGroupIdList
	 */
	public List<Integer> getNotInGroupIdList()
	{
		return notInGroupIdList;
	}

	/**
	 * Sets the not in group id list.
	 *
	 * @param notInGroupIdList the notInGroupIdList to set
	 */
	public void setNotInGroupIdList(List<Integer> notInGroupIdList)
	{
		this.notInGroupIdList = notInGroupIdList;
	}

	/**
	 * Checks for selected group.
	 *
	 * @return true, if successful
	 */
	public boolean hasSelectedGroup()
	{
		return !isNullOrEmpty(getGroupIdList()) ||
				!isNullOrEmpty(getNotInGroupIdList());
	}

	/**
	 * Checks for filter.
	 *
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return hasSelectedGroup();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "GroupCriteria [getGroupIdList()=" + getGroupIdList() + ", getNotInGroupIdList()="
				+ getNotInGroupIdList() + ", hasSelectedGroup()=" + hasSelectedGroup() + ", hasFilter()=" + hasFilter()
				+ ", toString()=" + super.toString() + "]";
	}


}
