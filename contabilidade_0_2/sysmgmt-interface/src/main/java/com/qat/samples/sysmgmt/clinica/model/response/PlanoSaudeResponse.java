package com.qat.samples.sysmgmt.clinica.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.clinica.model.PlanoSaude;

public class PlanoSaudeResponse extends InquiryResponse
{

	/** Attributes */
	private List<PlanoSaude> planoSaudeList;

	/**
	 * The Constructor.
	 */
	public PlanoSaudeResponse()
	{

	}

	/**
	 * @return the planoSaudeList
	 */
	public List<PlanoSaude> getPlanoSaudeList()
	{
		return planoSaudeList;
	}

	/**
	 * @param planoSaudeList the planoSaudeList to set
	 */
	public void setPlanoSaudeList(List<PlanoSaude> planoSaudeList)
	{
		this.planoSaudeList = planoSaudeList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setPlanoSaudeList((List<PlanoSaude>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getPlanoSaudeList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}