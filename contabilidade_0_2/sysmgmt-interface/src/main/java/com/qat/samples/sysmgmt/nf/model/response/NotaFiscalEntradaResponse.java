package com.qat.samples.sysmgmt.nf.model.response;

import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;

public class NotaFiscalEntradaResponse extends InquiryResponse
{

	/** Attributes */
	private List<NotaFiscalEntrada> notaFiscalList;

	/**
	 * The Constructor.
	 */
	public NotaFiscalEntradaResponse()
	{

	}

	/**
	 * @return the notaFiscalList
	 */
	public List<NotaFiscalEntrada> getNotaFiscalList()
	{
		return notaFiscalList;
	}

	/**
	 * @param notaFiscalList the notaFiscalList to set
	 */
	public void setNotaFiscalList(List<NotaFiscalEntrada> notaFiscalList)
	{
		this.notaFiscalList = notaFiscalList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotaFiscalEntradaResponse [getNotaFiscalList()=" + getNotaFiscalList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}