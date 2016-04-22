package com.qat.samples.sysmgmt.cotacao.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;

public class CotacaoResponse extends InquiryResponse
{

	/** Attributes */
	private List<Advogado> AdvogadoList;

	/**
	 * The Constructor.
	 */
	public CotacaoResponse()
	{

	}

	/**
	 * @return the AdvogadoList
	 */
	public List<Advogado> getAdvogadoList()
	{
		return AdvogadoList;
	}

	/**
	 * @param AdvogadoList the AdvogadoList to set
	 */
	public void setAdvogadoList(List<Advogado> AdvogadoList)
	{
		this.AdvogadoList = AdvogadoList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setAdvogadoList((List<Advogado>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getAdvogadoList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}