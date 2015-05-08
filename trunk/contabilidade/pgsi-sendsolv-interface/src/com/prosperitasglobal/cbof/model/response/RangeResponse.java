package com.prosperitasglobal.cbof.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.cbof.model.Range;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class RangeResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:31:26 AM
 */
public class RangeResponse extends InquiryResponse
{

	/** Attributes */
	private List<Range> rangeList;

	/**
	 * The Constructor.
	 */
	public RangeResponse()
	{

	}

	/**
	 * Gets the range list.
	 *
	 * @return the range list
	 */
	public List<Range> getRangeList()
	{
		return rangeList;
	}

	/**
	 * Sets the range list.
	 *
	 * @param rangeList the range list
	 */
	public void setRangeList(List<Range> rangeList)
	{
		this.rangeList = rangeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setRangeList((List<Range>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RangeResponse [getRangeList()=" + getRangeList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}