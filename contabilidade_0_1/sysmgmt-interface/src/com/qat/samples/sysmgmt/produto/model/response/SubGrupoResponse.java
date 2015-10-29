package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;

public class SubGrupoResponse extends InquiryResponse
{

	/** Attributes */
	private List<SubGrupo> arquivoList;

	/**
	 * The Constructor.
	 */
	public SubGrupoResponse()
	{

	}

	/**
	 * @return the arquivoList
	 */
	public List<SubGrupo> getSubGrupoList()
	{
		return arquivoList;
	}

	/**
	 * @param arquivoList the arquivoList to set
	 */
	public void setSubGrupoList(List<SubGrupo> arquivoList)
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
		setSubGrupoList((List<SubGrupo>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getSubGrupoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}