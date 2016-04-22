package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.banco.model.Banco;

public class BancoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Banco> bancoList;

	/**
	 * The Constructor.
	 */
	public BancoResponse()
	{

	}

	/**
	 * @return the bancoList
	 */
	public List<Banco> getBancoList()
	{
		return bancoList;
	}

	/**
	 * @param bancoList the bancoList to set
	 */
	public void setBancoList(List<Banco> bancoList)
	{
		this.bancoList = bancoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setBancoList((List<Banco>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getBancoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}