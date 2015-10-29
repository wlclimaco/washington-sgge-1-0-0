package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Marca;

public class MarcaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Marca> marcaList;

	/**
	 * The Constructor.
	 */
	public MarcaResponse()
	{

	}

	/**
	 * @return the marcaList
	 */
	public List<Marca> getMarcaList()
	{
		return marcaList;
	}

	/**
	 * @param marcaList the marcaList to set
	 */
	public void setMarcaList(List<Marca> marcaList)
	{
		this.marcaList = marcaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setMarcaList((List<Marca>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getMarcaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}