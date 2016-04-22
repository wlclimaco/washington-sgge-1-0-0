package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;

public class FormaPgResponse extends InquiryResponse
{

	/** Attributes */
	private List<FormaPg> formaPgList;

	/**
	 * The Constructor.
	 */
	public FormaPgResponse()
	{

	}

	/**
	 * @return the formaPgList
	 */
	public List<FormaPg> getFormaPgList()
	{
		return formaPgList;
	}

	/**
	 * @param formaPgList the formaPgList to set
	 */
	public void setFormaPgList(List<FormaPg> formaPgList)
	{
		this.formaPgList = formaPgList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setFormaPgList((List<FormaPg>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FormaPgResponse [getFormaPgList()=" + getFormaPgList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}