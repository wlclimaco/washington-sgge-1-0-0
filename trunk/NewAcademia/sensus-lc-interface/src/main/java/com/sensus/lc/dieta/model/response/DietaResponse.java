package com.sensus.lc.dieta.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.dieta.model.Dieta;

/**
 * The Class DietaResponse.
 * 
 * @author - Washington
 */
public class DietaResponse extends Response
{

	/** The dietas. */
	@XmlElement(nillable = true)
	private List<Dieta> dietas;

	/** The is dieta name unique. */
	private Boolean isDietaNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the dietas.
	 * 
	 * @return the dietas
	 */
	public List<Dieta> getDietas()
	{
		return dietas;
	}

	/**
	 * Sets the dietas.
	 * 
	 * @param dietaObjects the new dietas
	 */
	public void setDietas(List<Dieta> dietaObjects)
	{
		dietas = dietaObjects;
	}

	/**
	 * Gets the checks if is dieta name unique.
	 * 
	 * @return the checks if is dieta name unique
	 */
	public Boolean getIsDietaNameUnique()
	{
		return isDietaNameUnique;
	}

	/**
	 * Sets the checks if is dieta name unique.
	 * 
	 * @param isDietaNameUnique the new checks if is dieta name unique
	 */
	public void setIsDietaNameUnique(Boolean isDietaNameUnique)
	{
		this.isDietaNameUnique = isDietaNameUnique;
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
		return "DietaResponse [getDietas()=" + getDietas() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsDietaNameUnique()="
				+ getIsDietaNameUnique() + "]";
	}

}
