package com.sensus.dm.common.tag.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.tag.model.Tag;

/**
 * The Class TagResponse.
 * 
 * @author - Alex Barros - QAT
 */
public class TagResponse extends Response
{

	/** The tags. */
	private List<Tag> tags;

	/**
	 * Sets the tags.
	 * 
	 * @param tagList the new tags
	 */
	public void setTags(List<Tag> tagList)
	{
		tags = tagList;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TagResponse [getTags()=" + getTags() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
