package com.qat.samples.sysmgmt.fiscal.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.fiscal.model.Regime;

public class RegimeResponse extends InquiryResponse
{

	/** Attributes */
	private List<Regime> regimeList;

	/**
	 * The Constructor.
	 */
	public RegimeResponse()
	{

	}

	/**
	 * @return the regimeList
	 */
	public List<Regime> getRegimeList()
	{
		return regimeList;
	}

	/**
	 * @param regimeList the regimeList to set
	 */
	public void setRegimeList(List<Regime> regimeList)
	{
		this.regimeList = regimeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setRegimeList((List<Regime>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RegimeResponse [getRegimeList()=" + getRegimeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}