package com.qat.samples.sysmgmt.produto.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;

/**
 * The Class PlanoResponse.
 * 
 * @author aporto
 * @version 1.0
 * @created 11-Sep-2014 09:39:00 AM
 */
public class PlanoResponse extends InquiryResponse
{

	/** Attributes. */
	private List<Plano> planoList;

	/**
	 * Gets the plano list.
	 * 
	 * @return the plano list
	 */
	public List<Plano> getPlanoList()
	{
		return planoList;
	}

	/**
	 * Sets the plano list.
	 * 
	 * @param planoList the plano list
	 */
	public void setPlanoList(List<Plano> planoList)
	{
		this.planoList = planoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setPlanoList((List<Plano>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PlanoResponse [getPlanoList()=" + getPlanoList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
