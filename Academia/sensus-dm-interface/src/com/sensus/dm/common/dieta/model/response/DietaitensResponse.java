package com.sensus.dm.common.dieta.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.dieta.model.Dietaitens;

/**
 * The Class DietaitensResponse.
 * 
 * @author - Washington
 */
public class DietaitensResponse extends Response
{

	/** The dietaitenss. */
	@XmlElement(nillable = true)
	private List<Dietaitens> dietaitenss;

	/** The is dietaitens name unique. */
	private Boolean isDietaitensNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the dietaitenss.
	 * 
	 * @return the dietaitenss
	 */
	public List<Dietaitens> getDietaitenss()
	{
		return dietaitenss;
	}

	/**
	 * Sets the dietaitenss.
	 * 
	 * @param dietaitensObjects the new dietaitenss
	 */
	public void setDietaitenss(List<Dietaitens> dietaitensObjects)
	{
		dietaitenss = dietaitensObjects;
	}

	/**
	 * Gets the checks if is dietaitens name unique.
	 * 
	 * @return the checks if is dietaitens name unique
	 */
	public Boolean getIsDietaitensNameUnique()
	{
		return isDietaitensNameUnique;
	}

	/**
	 * Sets the checks if is dietaitens name unique.
	 * 
	 * @param isDietaitensNameUnique the new checks if is dietaitens name unique
	 */
	public void setIsDietaitensNameUnique(Boolean isDietaitensNameUnique)
	{
		this.isDietaitensNameUnique = isDietaitensNameUnique;
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
		return "DietaitensResponse [getDietaitenss()=" + getDietaitenss() + ", getMessageIterator()="
				+ getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsDietaitensNameUnique()="
				+ getIsDietaitensNameUnique() + "]";
	}

}
