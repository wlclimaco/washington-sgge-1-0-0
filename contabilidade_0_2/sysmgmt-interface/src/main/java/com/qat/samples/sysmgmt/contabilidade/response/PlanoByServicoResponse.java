package com.qat.samples.sysmgmt.contabilidade.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.contabilidade.model.PlanoByServico;

public class PlanoByServicoResponse extends InquiryResponse
{

	/** Attributes */
	private List<PlanoByServico> PlanoByServicoList;

	/**
	 * The Constructor.
	 */
	public PlanoByServicoResponse()
	{

	}

	/**
	 * @return the PlanoByServicoList
	 */
	public List<PlanoByServico> getPlanoByServicoList()
	{
		return PlanoByServicoList;
	}

	/**
	 * @param PlanoByServicoList the PlanoByServicoList to set
	 */
	public void setPlanoByServicoList(List<PlanoByServico> PlanoByServicoList)
	{
		this.PlanoByServicoList = PlanoByServicoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPlanoByServicoList((List<PlanoByServico>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getPlanoByServicoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}