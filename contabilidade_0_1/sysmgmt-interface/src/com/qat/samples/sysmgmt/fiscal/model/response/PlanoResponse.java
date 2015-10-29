package com.qat.samples.sysmgmt.fiscal.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.contabilidade.Plano;

public class PlanoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Plano> planoList;

	/**
	 * The Constructor.
	 */
	public PlanoResponse()
	{

	}

	/**
	 * @return the planoList
	 */
	public List<Plano> getPlanoList()
	{
		return planoList;
	}

	/**
	 * @param planoList the planoList to set
	 */
	public void setPlanoList(List<Plano> planoList)
	{
		this.planoList = planoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
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
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}