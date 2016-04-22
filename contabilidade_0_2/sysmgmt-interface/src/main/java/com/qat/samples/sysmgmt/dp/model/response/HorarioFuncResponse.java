package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.dp.model.HorarioFunc;

public class HorarioFuncResponse extends InquiryResponse
{

	/** Attributes */
	private List<HorarioFunc> horarioFuncList;

	/**
	 * The Constructor.
	 */
	public HorarioFuncResponse()
	{

	}

	/**
	 * @return the horarioFuncList
	 */
	public List<HorarioFunc> getHorarioFuncList()
	{
		return horarioFuncList;
	}

	/**
	 * @param horarioFuncList the horarioFuncList to set
	 */
	public void setHorarioFuncList(List<HorarioFunc> horarioFuncList)
	{
		this.horarioFuncList = horarioFuncList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setHorarioFuncList((List<HorarioFunc>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getHorarioFuncList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}