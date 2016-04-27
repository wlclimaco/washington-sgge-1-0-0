package com.qat.samples.sysmgmt.advocacia.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.advocacia.Field;

public class FieldResponse extends InquiryResponse
{

	/** Attributes */
	private List<Field> FieldList;

	/**
	 * The Constructor.
	 */
	public FieldResponse()
	{

	}

	/**
	 * @return the FieldList
	 */
	public List<Field> getFieldList()
	{
		return FieldList;
	}

	/**
	 * @param FieldList the FieldList to set
	 */
	public void setFieldList(List<Field> FieldList)
	{
		this.FieldList = FieldList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setFieldList((List<Field>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getFieldList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}