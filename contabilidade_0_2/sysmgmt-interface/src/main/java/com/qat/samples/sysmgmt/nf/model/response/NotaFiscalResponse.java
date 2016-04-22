package com.qat.samples.sysmgmt.nf.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscal;

public class NotaFiscalResponse extends InquiryResponse
{

	/** Attributes */
	private List<NotaFiscal> notaFiscalList;

	/**
	 * The Constructor.
	 */
	public NotaFiscalResponse()
	{

	}

	/**
	 * @return the notaFiscalList
	 */
	public List<NotaFiscal> getNotaFiscalList()
	{
		return notaFiscalList;
	}

	/**
	 * @param notaFiscalList the notaFiscalList to set
	 */
	public void setNotaFiscalList(List<NotaFiscal> notaFiscalList)
	{
		this.notaFiscalList = notaFiscalList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setNotaFiscalList((List<NotaFiscal>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getNotaFiscalList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}