package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.clinica.model.Especialidade;

public class EspecialidadeResponse extends InquiryResponse
{

	/** Attributes */
	private List<Especialidade> expecializacaoList;

	/**
	 * The Constructor.
	 */
	public EspecialidadeResponse()
	{

	}

	/**
	 * @return the expecializacaoList
	 */
	public List<Especialidade> getEspecialidadeList()
	{
		return expecializacaoList;
	}

	/**
	 * @param expecializacaoList the expecializacaoList to set
	 */
	public void setEspecialidadeList(List<Especialidade> expecializacaoList)
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
		setEspecialidadeList((List<Especialidade>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EspecialidadeResponse [getEspecialidadeList()=" + getEspecialidadeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}