package com.qat.samples.sysmgmt.ordemServico.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.ordemServico.model.OrdemServico;

/**
 * The Class OrdemServicoResponse.
 * 
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:39:00 AM
 */
public class OrdemServicoResponse extends InquiryResponse
{

	/** Attributes. */
	private List<OrdemServico> ordemServicoList;

	/**
	 * Gets the ordemServico list.
	 * 
	 * @return the ordemServico list
	 */
	public List<OrdemServico> getOrdemServicoList()
	{
		return ordemServicoList;
	}

	/**
	 * Sets the ordemServico list.
	 * 
	 * @param ordemServicoList the ordemServico list
	 */
	public void setOrdemServicoList(List<OrdemServico> ordemServicoList)
	{
		this.ordemServicoList = ordemServicoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setOrdemServicoList((List<OrdemServico>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrdemServicoResponse [getOrdemServicoList()=" + getOrdemServicoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
