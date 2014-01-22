package com.sensus.lc.academia.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.academia.model.Academia;

/**
 * The Class AcademiaResponse.
 * 
 * @author - Washington
 */
public class AcademiaResponse extends Response
{

	/** The academias. */
	@XmlElement(nillable = true)
	private List<Academia> academias;

	/** The is academia name unique. */
	private Boolean isAcademiaNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the academias.
	 * 
	 * @return the academias
	 */
	public List<Academia> getAcademias()
	{
		return academias;
	}

	/**
	 * Sets the academias.
	 * 
	 * @param academiaObjects the new academias
	 */
	public void setAcademias(List<Academia> academiaObjects)
	{
		academias = academiaObjects;
	}

	/**
	 * Gets the checks if is academia name unique.
	 * 
	 * @return the checks if is academia name unique
	 */
	public Boolean getIsAcademiaNameUnique()
	{
		return isAcademiaNameUnique;
	}

	/**
	 * Sets the checks if is academia name unique.
	 * 
	 * @param isAcademiaNameUnique the new checks if is academia name unique
	 */
	public void setIsAcademiaNameUnique(Boolean isAcademiaNameUnique)
	{
		this.isAcademiaNameUnique = isAcademiaNameUnique;
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
		return "AcademiaResponse [getAcademias()=" + getAcademias() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsAcademiaNameUnique()="
				+ getIsAcademiaNameUnique() + "]";
	}

}
