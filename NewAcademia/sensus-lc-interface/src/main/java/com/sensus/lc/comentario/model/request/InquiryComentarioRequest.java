package com.sensus.lc.comentario.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.comentario.model.Comentario;

/**
 * The Class InquiryComentarioRequest.
 * 
 * @author Washington
 */
public class InquiryComentarioRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The comentarios. */
	private List<Comentario> comentarios;

	/**
	 * Instantiates a new inquiry comentario request.
	 */
	public InquiryComentarioRequest()
	{

	}

	/**
	 * Instantiates a new inquiry comentario request.
	 * 
	 * @param comentario the comentario
	 */
	public InquiryComentarioRequest(Comentario comentario)
	{
		addComentario(comentario);
	}

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
	 * @param comentarios the new comentarios
	 */
	public void setComentarios(List<Comentario> comentarios)
	{
		this.comentarios = comentarios;
	}

	/**
	 * Gets the first comentario.
	 * 
	 * @return the first comentario
	 */
	public Comentario getFirstComentario()
	{
		if ((getComentarios() != null) && !getComentarios().isEmpty())
		{
			return getComentarios().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the comentario.
	 * 
	 * @param comentario the comentario
	 */
	public void addComentario(Comentario comentario)
	{
		if (getComentarios() == null)
		{
			setComentarios(new ArrayList<Comentario>());
		}

		getComentarios().add(comentario);
	}

	@Override
	public String toString()
	{
		return "InquiryComentarioRequest [getComentarios()=" + getComentarios() + ", getFirstComentario()="
				+ getFirstComentario() + ", toString()="
				+ super.toString() + "]";
	}

}
