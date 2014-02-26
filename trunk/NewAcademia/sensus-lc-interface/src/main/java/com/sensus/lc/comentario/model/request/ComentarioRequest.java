package com.sensus.lc.comentario.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class ComentarioRequest.
 * 
 * @author Washington
 */
public class ComentarioRequest extends TenantRequest
{
	/** The comentarios. */
	private List<Comentario> comentarios;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new comentario request.
	 */
	public ComentarioRequest()
	{
	}

	/**
	 * Instantiates a new comentario request.
	 * 
	 * @param comentario the comentario
	 */
	public ComentarioRequest(Comentario comentario)
	{
		addComentario(comentario);
	}

	/**
	 * Instantiates a new comentario request.
	 * 
	 * @param userContext the user context
	 */
	public ComentarioRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new comentario request.
	 * 
	 * @param comentario the comentario
	 * @param userContext the user context
	 */
	public ComentarioRequest(Comentario comentario, UserContext userContext)
	{
		addComentario(comentario);
		setUserContext(userContext);
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
	 * Gets the first comentario.
	 * 
	 * @return the first comentario
	 */
	public Comentario getFirstComentario()
	{
		if ((getComentarios() != null) && !getComentarios().isEmpty())
		{
			return getComentarios().get(0);
		}

		return null;
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

	/**
	 * Gets the retrieve history.
	 * 
	 * @return the retrieve history
	 */
	public Boolean getRetrieveHistory()
	{
		return retrieveHistory;
	}

	/**
	 * Sets the retrieve history.
	 * 
	 * @param retrieveHistory the new retrieve history
	 */
	public void setRetrieveHistory(Boolean retrieveHistory)
	{
		this.retrieveHistory = retrieveHistory;
	}

	@Override
	public String toString()
	{
		return "ComentarioRequest [getComentarios()=" + getComentarios() + ", getFirstComentario()="
				+ getFirstComentario()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
