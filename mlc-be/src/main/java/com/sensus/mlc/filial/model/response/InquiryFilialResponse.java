package com.sensus.mlc.filial.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.filial.model.Filial;


/**
 * The Class InquiryActionResponse.
 *
 * @author - QAT Brazil.
 */
public class InquiryFilialResponse extends InquiryResponse
{

	/** The actions. */
	private List<Filial> filial;

	/** The process id. */
	private Integer processId;

	/**
	 * @return the filial
	 */
	public List<Filial> getFilial()
	{
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(List<Filial> filial)
	{
		this.filial = filial;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryFilialResponse [filial=" + filial + ", processId=" + processId + "]";
	}

}
