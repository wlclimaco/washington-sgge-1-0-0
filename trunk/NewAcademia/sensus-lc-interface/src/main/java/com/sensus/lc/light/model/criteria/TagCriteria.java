package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * Criteria for Tag.
 *
 * @author Thiago - QAT
 *
 */
public class TagCriteria
{

	/**
	 * Attributes.
	 */
	private List<Integer> tagIdList = new ArrayList<>(); // Just list the group ID in this collection

	/** The not in tag id list. */
	private List<Integer> notInTagIdList = new ArrayList<>();

	/**
	 * Gets the tag id list.
	 *
	 * @return the tagIdList
	 */
	public List<Integer> getTagIdList()
	{
		return tagIdList;
	}

	/**
	 * Sets the tag id list.
	 *
	 * @param tagIdList the tagIdList to set
	 */
	public void setTagIdList(List<Integer> tagIdList)
	{
		this.tagIdList = tagIdList;
	}

	/**
	 * Gets the not in tag id list.
	 *
	 * @return the notInTagIdList
	 */
	public List<Integer> getNotInTagIdList()
	{
		return notInTagIdList;
	}

	/**
	 * Sets the not in tag id list.
	 *
	 * @param notInTagIdList the notInTagIdList to set
	 */
	public void setNotInTagIdList(List<Integer> notInTagIdList)
	{
		this.notInTagIdList = notInTagIdList;
	}

	/**
	 * Checks for selected tag.
	 *
	 * @return true, if successful
	 */
	public boolean hasSelectedTag()
	{
		return !isNullOrEmpty(getTagIdList()) ||
				!isNullOrEmpty(getNotInTagIdList());
	}

	/**
	 * Checks for filter.
	 *
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return hasSelectedTag();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TagCriteria [getTagIdList()=" + getTagIdList() + ", getNotInTagIdList()=" + getNotInTagIdList()
				+ ", hasSelectedTag()=" + hasSelectedTag() + ", hasFilter()=" + hasFilter() + ", toString()="
				+ super.toString() + "]";
	}


}
