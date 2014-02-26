package com.sensus.lc.curtir.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.curtir.model.Curtir;

/**
 * The Class CurtirResponse.
 * 
 * @author - Washington
 */
public class CurtirResponse extends Response
{

	/** The curtir. */
	@XmlElement(nillable = true)
	private List<Curtir> curtir;

	/** The is foto name unique. */
	private Boolean isCurtirNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the curtir.
	 * 
	 * @return the curtir
	 */
	public List<Curtir> getCurtir()
	{
		return curtir;
	}

	/**
	 * Sets the curtir.
	 * 
	 * @param fotoObjects the new curtir
	 */
	public void setCurtir(List<Curtir> fotoObjects)
	{
		curtir = fotoObjects;
	}

	/**
	 * Gets the checks if is foto name unique.
	 * 
	 * @return the checks if is foto name unique
	 */
	public Boolean getIsCurtirNameUnique()
	{
		return isCurtirNameUnique;
	}

	/**
	 * Sets the checks if is foto name unique.
	 * 
	 * @param isCurtirNameUnique the new checks if is foto name unique
	 */
	public void setIsCurtirNameUnique(Boolean isCurtirNameUnique)
	{
		this.isCurtirNameUnique = isCurtirNameUnique;
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
		return "CurtirResponse [getCurtir()=" + getCurtir() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsCurtirNameUnique()="
				+ getIsCurtirNameUnique() + "]";
	}

}
