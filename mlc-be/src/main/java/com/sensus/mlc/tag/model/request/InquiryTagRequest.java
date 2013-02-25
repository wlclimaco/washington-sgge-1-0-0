package com.sensus.mlc.tag.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tag.model.Tag;

/**
 * The Class InquiryTagRequest.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class InquiryTagRequest extends InquiryPaginationRequest
{

	/** The tag. */
	private Tag tag;

	/**
	 * Instantiates a new inquiry tag request.
	 */
	public InquiryTagRequest()
	{
	}

	/**
	 * Instantiates a new inquiry tag request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryTagRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the tag.
	 * 
	 * @param tagObject the new tag
	 */
	public void setTag(Tag tagObject)
	{
		tag = tagObject;
	}

	/**
	 * Gets the tag.
	 * 
	 * @return the tag
	 */
	public Tag getTag()
	{
		return tag;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryTagRequest [getTag()=" + getTag() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}
