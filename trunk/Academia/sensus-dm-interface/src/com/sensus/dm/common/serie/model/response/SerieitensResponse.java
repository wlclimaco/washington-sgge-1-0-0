package com.sensus.dm.common.serie.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.serie.model.Serieitens;

/**
 * The Class SerieitensResponse.
 * 
 * @author - Washington
 */
public class SerieitensResponse extends Response
{

	/** The serieitenss. */
	@XmlElement(nillable = true)
	private List<Serieitens> serieitenss;

	/** The is serieitens name unique. */
	private Boolean isSerieitensNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the serieitenss.
	 * 
	 * @return the serieitenss
	 */
	public List<Serieitens> getSerieitenss()
	{
		return serieitenss;
	}

	/**
	 * Sets the serieitenss.
	 * 
	 * @param serieitensObjects the new serieitenss
	 */
	public void setSerieitenss(List<Serieitens> serieitensObjects)
	{
		serieitenss = serieitensObjects;
	}

	/**
	 * Gets the checks if is serieitens name unique.
	 * 
	 * @return the checks if is serieitens name unique
	 */
	public Boolean getIsSerieitensNameUnique()
	{
		return isSerieitensNameUnique;
	}

	/**
	 * Sets the checks if is serieitens name unique.
	 * 
	 * @param isSerieitensNameUnique the new checks if is serieitens name unique
	 */
	public void setIsSerieitensNameUnique(Boolean isSerieitensNameUnique)
	{
		this.isSerieitensNameUnique = isSerieitensNameUnique;
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
		return "SerieitensResponse [getSerieitenss()=" + getSerieitenss() + ", getMessageIterator()="
				+ getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsSerieitensNameUnique()="
				+ getIsSerieitensNameUnique() + "]";
	}

}
