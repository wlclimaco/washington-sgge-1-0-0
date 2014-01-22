package com.sensus.lc.suplemento.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.suplemento.model.Suplemento;

/**
 * The Class SuplementoResponse.
 * 
 * @author - Washington
 */
public class SuplementoResponse extends Response
{

	/** The suplementos. */
	@XmlElement(nillable = true)
	private List<Suplemento> suplementos;

	/** The is suplemento name unique. */
	private Boolean isSuplementoNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the suplementos.
	 * 
	 * @return the suplementos
	 */
	public List<Suplemento> getSuplementos()
	{
		return suplementos;
	}

	/**
	 * Sets the suplementos.
	 * 
	 * @param suplementoObjects the new suplementos
	 */
	public void setSuplementos(List<Suplemento> suplementoObjects)
	{
		suplementos = suplementoObjects;
	}

	/**
	 * Gets the checks if is suplemento name unique.
	 * 
	 * @return the checks if is suplemento name unique
	 */
	public Boolean getIsSuplementoNameUnique()
	{
		return isSuplementoNameUnique;
	}

	/**
	 * Sets the checks if is suplemento name unique.
	 * 
	 * @param isSuplementoNameUnique the new checks if is suplemento name unique
	 */
	public void setIsSuplementoNameUnique(Boolean isSuplementoNameUnique)
	{
		this.isSuplementoNameUnique = isSuplementoNameUnique;
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

	@Override
	public String toString()
	{
		return "SuplementoResponse [getSuplementos()=" + getSuplementos() + ", getMessageIterator()="
				+ getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsSuplementoNameUnique()="
				+ getIsSuplementoNameUnique() + "]";
	}

}
