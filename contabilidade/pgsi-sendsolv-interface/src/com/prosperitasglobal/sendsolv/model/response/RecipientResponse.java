package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Recipient;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class RecipientResponse extends InquiryResponse
{

	/** Attributes */
	private List<Recipient> recipientList;

	/**
	 * The Constructor.
	 */
	public RecipientResponse()
	{

	}

	/**
	 * Gets the location list.
	 *
	 * @return the location list
	 */
	public List<Recipient> getRecipientList()
	{
		return recipientList;
	}

	/**
	 * Sets the location list.
	 *
	 * @param recipientList the location list
	 */
	public void setRecipientList(List<Recipient> recipientList)
	{
		this.recipientList = recipientList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setRecipientList((List<Recipient>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RecipientResponse [getRecipientList()=" + getRecipientList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}