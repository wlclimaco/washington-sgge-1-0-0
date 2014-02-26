package com.sensus.lc.comentario.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.comentario.model.Comentario;

/**
 * The Class ComentarioResponse.
 * 
 * @author - Washington
 */
public class ComentarioResponse extends Response
{

	/** The comentarios. */
	@XmlElement(nillable = true)
	private List<Comentario> comentarios;

	/** The is comentario name unique. */
	private Boolean isComentarioNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the comentarios.
	 * 
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios()
	{
		return comentarios;
	}

	/**
	 * Sets the comentarios.
	 * 
	 * @param comentarioObjects the new comentarios
	 */
	public void setComentarios(List<Comentario> comentarioObjects)
	{
		comentarios = comentarioObjects;
	}

	/**
	 * Gets the checks if is comentario name unique.
	 * 
	 * @return the checks if is comentario name unique
	 */
	public Boolean getIsComentarioNameUnique()
	{
		return isComentarioNameUnique;
	}

	/**
	 * Sets the checks if is comentario name unique.
	 * 
	 * @param isComentarioNameUnique the new checks if is comentario name unique
	 */
	public void setIsComentarioNameUnique(Boolean isComentarioNameUnique)
	{
		this.isComentarioNameUnique = isComentarioNameUnique;
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
		return "ComentarioResponse [getComentarios()=" + getComentarios() + ", getMessageIterator()="
				+ getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsComentarioNameUnique()="
				+ getIsComentarioNameUnique() + "]";
	}

}
