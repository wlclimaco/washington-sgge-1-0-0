package com.sensus.dm.common.foto.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.foto.model.Foto;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class FotoRequest.
 * 
 * @author Washington
 */
public class FotoRequest extends TenantRequest
{
	/** The fotos. */
	private List<Foto> fotos;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new foto request.
	 */
	public FotoRequest()
	{
	}

	/**
	 * Instantiates a new foto request.
	 * 
	 * @param foto the foto
	 */
	public FotoRequest(Foto foto)
	{
		addFoto(foto);
	}

	/**
	 * Instantiates a new foto request.
	 * 
	 * @param userContext the user context
	 */
	public FotoRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new foto request.
	 * 
	 * @param foto the foto
	 * @param userContext the user context
	 */
	public FotoRequest(Foto foto, UserContext userContext)
	{
		addFoto(foto);
		setUserContext(userContext);
	}

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
	 * Gets the first foto.
	 * 
	 * @return the first foto
	 */  
	public Foto getFirstFoto()
	{
		if (getFotos() != null && !getFotos().isEmpty())
		{
			return getFotos().get(0);
		}

		return null;
	}

	/**
	 * Sets the fotos.
	 * 
	 * @param fotos the new fotos
	 */
	public void setFotos(List<Foto> fotos)
	{
		this.fotos = fotos;
	}

	/**
	 * Adds the foto.
	 * 
	 * @param foto the foto
	 */
	public void addFoto(Foto foto)
	{
		if (getFotos() == null)
		{
			setFotos(new ArrayList<Foto>());
		}

		getFotos().add(foto);
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
		return "FotoRequest [getFotos()=" + getFotos() + ", getFirstFoto()=" + getFirstFoto()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}


