package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.util.model.Compromisso;

public class CompromissoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Compromisso> expecializacaoList;

	/**
	 * The Constructor.
	 */
	public CompromissoResponse()
	{

	}

	/**
	 * @return the expecializacaoList
	 */
	public List<Compromisso> getCompromissoList()
	{
		return expecializacaoList;
	}

	/**
	 * @param expecializacaoList the expecializacaoList to set
	 */
	public void setCompromissoList(List<Compromisso> expecializacaoList)
	{
		this.expecializacaoList = expecializacaoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setCompromissoList((List<Compromisso>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CompromissoResponse [getCompromissoList()=" + getCompromissoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}