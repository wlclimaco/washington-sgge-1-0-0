package com.prosperitasglobal.sendsolv.model.response;

import java.util.List;

import com.prosperitasglobal.sendsolv.model.Contas;
import com.qat.framework.model.response.InquiryResponse;

public class ContasResponse extends InquiryResponse
{

	/** Attributes */
	private List<Contas> contasList;

	/**
	 * The Constructor.
	 */
	public ContasResponse()
	{

	}

	/**
	 * @return the contasList
	 */
	public List<Contas> getContasList()
	{
		return contasList;
	}

	/**
	 * @param contasList the contasList to set
	 */
	public void setContasList(List<Contas> contasList)
	{
		this.contasList = contasList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContasResponse [getContasList()=" + getContasList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}