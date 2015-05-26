package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Cliente;
import com.qat.framework.model.response.InquiryResponse;

public class ClienteResponse extends InquiryResponse
{

	/** Attributes */
	private List<Cliente> clienteList;

	/**
	 * The Constructor.
	 */
	public ClienteResponse()
	{

	}

	/**
	 * @return the clienteList
	 */
	public List<Cliente> getClienteList()
	{
		return clienteList;
	}

	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(List<Cliente> clienteList)
	{
		this.clienteList = clienteList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setClienteList((List<Cliente>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ClienteResponse [getClienteList()=" + getClienteList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}