package com.qat.samples.sysmgmt.util.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.util.model.DoisValores;

public class DoisValoresResponse extends InquiryResponse
{

	/** Attributes */
	private List<DoisValores> doisValoresList;

	/**
	 * The Constructor.
	 */
	public DoisValoresResponse()
	{

	}

	/**
	 * @return the doisValoresList
	 */
	public List<DoisValores> getDoisValoresList()
	{
		return doisValoresList;
	}

	/**
	 * @param doisValoresList the doisValoresList to set
	 */
	public void setDoisValoresList(List<DoisValores> doisValoresList)
	{
		this.doisValoresList = doisValoresList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setDoisValoresList((List<DoisValores>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DoisValoresResponse [getDoisValoresList()=" + getDoisValoresList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}