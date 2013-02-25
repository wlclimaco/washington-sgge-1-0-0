package com.sensus.mlc.filial.model.request;

import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.filial.model.Filial;



/**
 * The Class InquiryActionRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryFilialRequest extends InquiryPaginationRequest
{

	/** The base search. */
	private BaseSearch baseSearch;

	/** The file name. */
	private String fileName;

	/** The process id. */
	private Integer processId;

	/** The actions. */
	private List<Filial> Filial;

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

	/**
	 * @return the filial
	 */
	public List<Filial> getFilial()
	{
		return Filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(List<Filial> filial)
	{
		Filial = filial;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryFilialRequest [baseSearch=" + baseSearch + ", fileName=" + fileName + ", processId=" + processId
				+ ", Filial=" + Filial + "]";
	}

}
