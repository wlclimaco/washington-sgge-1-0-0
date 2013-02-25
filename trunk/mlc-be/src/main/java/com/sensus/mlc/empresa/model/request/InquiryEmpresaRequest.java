package com.sensus.mlc.empresa.model.request;

import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.empresa.model.Empresa;



/**
 * The Class InquiryActionRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryEmpresaRequest extends InquiryPaginationRequest
{

	/** The base search. */
	private BaseSearch baseSearch;

	/** The file name. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The actions. */
	private List<Empresa> empresas;

	/**
	 * Gets the base search.
	 * 
	 * @return the base search
	 */
	public BaseSearch getBaseSearch()
	{
		return baseSearch;
	}

	/**
	 * Sets the base search.
	 * 
	 * @param baseSearch the new base search
	 */
	public void setBaseSearch(BaseSearch baseSearch)
	{
		this.baseSearch = baseSearch;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	public List<Empresa> getEmpresas()
	{
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas)
	{
		this.empresas = empresas;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryEmpresaRequest [getBaseSearch()=" + getBaseSearch() + ", getFileName()=" + getFileName()
				+ ", getProcessId()=" + getProcessId() + ", getEmpresas()=" + getEmpresas() + "]";
	}

}
