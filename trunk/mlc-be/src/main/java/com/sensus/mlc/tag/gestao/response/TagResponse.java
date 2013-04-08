package com.sensus.mlc.tag.gestao.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.tag.model.Tag;

/**
 * The Class TagResponse.
 * 
 * @author - Alex Barros - QAT
 */
public class TagResponse extends Response
{

	/** The tags. */
	private List<Tag> tags = new ArrayList<Tag>();

	/**
	 * Sets the tags.
	 * 
	 * @param tagList the new tags
	 */
	public void setTags(List<Tag> tagList)
	{
		this.tags = tagList;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	public List<Tag> getTags()
	{
		return this.tags;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TagResponse [getTags()=" + this.getTags() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}
}
