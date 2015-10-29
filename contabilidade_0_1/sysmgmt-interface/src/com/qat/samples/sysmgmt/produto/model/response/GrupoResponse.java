package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.Grupo;

public class GrupoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Grupo> arquivoList;

	/**
	 * The Constructor.
	 */
	public GrupoResponse()
	{

	}

	/**
	 * @return the arquivoList
	 */
	public List<Grupo> getGrupoList()
	{
		return arquivoList;
	}

	/**
	 * @param arquivoList the arquivoList to set
	 */
	public void setGrupoList(List<Grupo> arquivoList)
	{
		this.arquivoList = arquivoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setGrupoList((List<Grupo>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getGrupoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}