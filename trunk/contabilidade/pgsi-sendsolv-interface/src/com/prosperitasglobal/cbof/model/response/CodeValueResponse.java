package com.prosperitasglobal.cbof.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.cbof.model.CodeValue;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class CodeValueResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:31:09 AM
 */
public class CodeValueResponse extends InquiryResponse
{

	/** The list of code values. */
	private List<CodeValue> codeValueList;

	/**
	 * The Constructor.
	 */
	public CodeValueResponse()
	{

	}

	/**
	 * Gets the code value list.
	 *
	 * @return the code value list
	 */
	public List<CodeValue> getCodeValueList()
	{
		return codeValueList;
	}

	/**
	 * Sets the code value list.
	 *
	 * @param codeValueList the code value list
	 */
	public void setCodeValueList(List<CodeValue> codeValueList)
	{
		this.codeValueList = codeValueList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCodeValueList((List<CodeValue>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CodeValueResponse [getCodeValueList()=" + getCodeValueList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}