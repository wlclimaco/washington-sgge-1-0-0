package com.qat.samples.sysmgmt.dicionario.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.dicionario.Classes;

public class ClassesResponse extends InquiryResponse
{

	/** Attributes */
	private List<Classes> ClassesList;

	/**
	 * The Constructor.
	 */
	public ClassesResponse()
	{

	}

	/**
	 * @return the ClassesList
	 */
	public List<Classes> getClassesList()
	{
		return ClassesList;
	}

	/**
	 * @param ClassesList the ClassesList to set
	 */
	public void setClassesList(List<Classes> ClassesList)
	{
		this.ClassesList = ClassesList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setClassesList((List<Classes>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getClassesList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}