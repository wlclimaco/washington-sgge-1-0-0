package com.sensus.lc.video.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.foto.model.Foto;

/**
 * The Class FotoResponse.
 * 
 * @author - Washington
 */
public class VideoResponse extends Response
{

	/** The fotos. */
	@XmlElement(nillable = true)
	private List<Foto> fotos;

	/** The is foto name unique. */
	private Boolean isFotoNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the fotos.
	 * 
	 * @return the fotos
	 */
	public List<Foto> getFotos()
	{
		return fotos;
	}

	/**
	 * Sets the fotos.
	 * 
	 * @param fotoObjects the new fotos
	 */
	public void setFotos(List<Foto> fotoObjects)
	{
		fotos = fotoObjects;
	}

	/**
	 * Gets the checks if is foto name unique.
	 * 
	 * @return the checks if is foto name unique
	 */
	public Boolean getIsFotoNameUnique()
	{
		return isFotoNameUnique;
	}

	/**
	 * Sets the checks if is foto name unique.
	 * 
	 * @param isFotoNameUnique the new checks if is foto name unique
	 */
	public void setIsFotoNameUnique(Boolean isFotoNameUnique)
	{
		this.isFotoNameUnique = isFotoNameUnique;
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
		return "FotoResponse [getFotos()=" + getFotos() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsFotoNameUnique()="
				+ getIsFotoNameUnique() + "]";
	}

}
