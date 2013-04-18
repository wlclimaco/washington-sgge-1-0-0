package com.sensus.mlc.tag.gestao.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.tag.model.Tag;

/**
 * The Class InquiryTagResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class InquiryTagResponse extends InquiryResponse
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
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setTags(new ArrayList<Tag>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryTagResponse [getTags()=" + getTags() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}