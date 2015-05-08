package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Empresa;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class LocationResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class EmpresaResponse extends InquiryResponse
{

	/** Attributes */
	private List<Empresa> empresaList;

	/**
	 * The Constructor.
	 */
	public EmpresaResponse()
	{

	}

	/**
	 * @return the empresaList
	 */
	public List<Empresa> getEmpresaList()
	{
		return empresaList;
	}

	/**
	 * @param empresaList the empresaList to set
	 */
	public void setEmpresaList(List<Empresa> empresaList)
	{
		this.empresaList = empresaList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setEmpresaList((List<Empresa>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LocationResponse [getLocationList()=" + getEmpresaList() + ", getResultsSetInfo()="
				+ getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}

}