package com.qat.samples.sysmgmt.dp.model.response;

import java.util.Collection;
import java.util.List;

import com.qat.framework.model.response.InquiryResponse;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;

/**
 * The Class LocationResponse.
 * 
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class FuncionarioResponse extends InquiryResponse
{

	/** Attributes */
	private List<Funcionario> funcionarioList;

	/**
	 * The Constructor.
	 */
	public FuncionarioResponse()
	{

	}

	/**
	 * @return the funcionarioList
	 */
	public List<Funcionario> getFuncionarioList()
	{
		return funcionarioList;
	}

	/**
	 * @param funcionarioList the funcionarioList to set
	 */
	public void setFuncionarioList(List<Funcionario> funcionarioList)
	{
		this.funcionarioList = funcionarioList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setFuncionarioList((List<Funcionario>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getFuncionarioList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}