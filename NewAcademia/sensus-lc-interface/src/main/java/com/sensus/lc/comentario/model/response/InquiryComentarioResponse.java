package com.sensus.lc.comentario.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.comentario.model.Comentario;

/**
 * The Class InquiryComentarioResponse.
 * 
 * @author - Washington
 */
public class InquiryComentarioResponse extends InquiryResponse
{

	/** The comentarios. */
	@XmlElement(nillable = true)
	private List<Comentario> comentarios;

	/** The file name. */
	private String fileName;

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
	 * @param comentarioList the new comentarios
	 */
	public void setComentarios(List<Comentario> comentarioList)
	{
		comentarios = comentarioList;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setComentarios(new ArrayList<Comentario>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryComentarioResponse [comentarios=" + comentarios + ", fileName=" + fileName
				+ ", getComentarios()=" + getComentarios()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
