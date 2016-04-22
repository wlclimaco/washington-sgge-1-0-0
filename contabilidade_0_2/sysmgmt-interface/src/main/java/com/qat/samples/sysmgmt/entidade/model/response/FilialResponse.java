package com.qat.samples.sysmgmt.entidade.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.entidade.model.Filial;

public class FilialResponse extends InquiryResponse
{

	/** Attributes */
	private List<Filial> filialList;

	/**
	 * The Constructor.
	 */
	public FilialResponse()
	{

	}

	/**
	 * @return the filialList
	 */
	public List<Filial> getFilialList()
	{
		return filialList;
	}

	/**
	 * @param filialList the filialList to set
	 */
	public void setFilialList(List<Filial> filialList)
	{
		this.filialList = filialList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setFilialList((List<Filial>)coll);
	}

	@Override
	public String toString()
	{
		return "FilialResponse [getFilialList()=" + getFilialList() + ", toString()=" + super.toString() + "]";
	}

}