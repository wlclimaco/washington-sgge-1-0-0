package com.qat.samples.sysmgmt.historico.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.historico.model.Historico;

public class HistoricoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Historico> historicoList;

	/**
	 * The Constructor.
	 */
	public HistoricoResponse()
	{

	}

	/**
	 * @return the historicoList
	 */
	public List<Historico> getHistoricoList()
	{
		return historicoList;
	}

	/**
	 * @param historicoList the historicoList to set
	 */
	public void setHistoricoList(List<Historico> historicoList)
	{
		this.historicoList = historicoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setHistoricoList((List<Historico>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HistoricoResponse [getHistoricoList()=" + getHistoricoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}