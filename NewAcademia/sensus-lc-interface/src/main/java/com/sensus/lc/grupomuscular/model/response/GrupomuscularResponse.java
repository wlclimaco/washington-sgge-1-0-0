package com.sensus.lc.grupomuscular.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.grupomuscular.model.Grupomuscular;

/**
 * The Class GrupomuscularResponse.
 * 
 * @author - Washington
 */
public class GrupomuscularResponse extends Response
{

	/** The grupomusculars. */
	@XmlElement(nillable = true)
	private List<Grupomuscular> grupomusculars;

	/** The is grupomuscular name unique. */
	private Boolean isGrupomuscularNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the grupomusculars.
	 * 
	 * @return the grupomusculars
	 */
	public List<Grupomuscular> getGrupomusculars()
	{
		return grupomusculars;
	}

	/**
	 * Sets the grupomusculars.
	 * 
	 * @param grupomuscularObjects the new grupomusculars
	 */
	public void setGrupomusculars(List<Grupomuscular> grupomuscularObjects)
	{
		grupomusculars = grupomuscularObjects;
	}

	/**
	 * Gets the checks if is grupomuscular name unique.
	 * 
	 * @return the checks if is grupomuscular name unique
	 */
	public Boolean getIsGrupomuscularNameUnique()
	{
		return isGrupomuscularNameUnique;
	}

	/**
	 * Sets the checks if is grupomuscular name unique.
	 * 
	 * @param isGrupomuscularNameUnique the new checks if is grupomuscular name unique
	 */
	public void setIsGrupomuscularNameUnique(Boolean isGrupomuscularNameUnique)
	{
		this.isGrupomuscularNameUnique = isGrupomuscularNameUnique;
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
		return "GrupomuscularResponse [getGrupomusculars()=" + getGrupomusculars() + ", getMessageIterator()="
				+ getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsGrupomuscularNameUnique()="
				+ getIsGrupomuscularNameUnique() + "]";
	}

}
