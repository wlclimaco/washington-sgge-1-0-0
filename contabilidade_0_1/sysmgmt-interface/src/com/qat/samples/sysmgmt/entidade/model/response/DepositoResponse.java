package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.Deposito;

public class DepositoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Deposito> depositoList;

	/**
	 * The Constructor.
	 */
	public DepositoResponse()
	{

	}

	/**
	 * @return the depositoList
	 */
	public List<Deposito> getDepositoList()
	{
		return depositoList;
	}

	/**
	 * @param depositoList the depositoList to set
	 */
	public void setDepositoList(List<Deposito> depositoList)
	{
		this.depositoList = depositoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setDepositoList((List<Deposito>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getDepositoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}