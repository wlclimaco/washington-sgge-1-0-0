package com.sensus.dm.common.medida.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.medida.model.Medida;

/**
 * The Class MedidaResponse.
 * 
 * @author - Washington
 */
public class MedidaResponse extends Response
{

	/** The medidas. */
	@XmlElement(nillable = true)
	private List<Medida> medidas;

	/** The is medida name unique. */
	private Boolean isMedidaNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the medidas.
	 * 
	 * @return the medidas
	 */
	public List<Medida> getMedidas()
	{
		return medidas;
	}

	/**
	 * Sets the medidas.
	 * 
	 * @param medidaObjects the new medidas
	 */
	public void setMedidas(List<Medida> medidaObjects)
	{
		medidas = medidaObjects;
	}

	/**
	 * Gets the checks if is medida name unique.
	 * 
	 * @return the checks if is medida name unique
	 */
	public Boolean getIsMedidaNameUnique()
	{
		return isMedidaNameUnique;
	}

	/**
	 * Sets the checks if is medida name unique.
	 * 
	 * @param isMedidaNameUnique the new checks if is medida name unique
	 */
	public void setIsMedidaNameUnique(Boolean isMedidaNameUnique)
	{
		this.isMedidaNameUnique = isMedidaNameUnique;
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
		return "MedidaResponse [getMedidas()=" + getMedidas() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsMedidaNameUnique()="
				+ getIsMedidaNameUnique() + "]";
	}

}

