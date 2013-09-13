package com.sensus.dm.common.dieta.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.dieta.model.Dieta;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class DietaRequest.
 * 
 * @author Washington
 */
public class DietaRequest extends TenantRequest
{
	/** The dietas. */
	private List<Dieta> dietas;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new dieta request.
	 */
	public DietaRequest()
	{
	}

	/**
	 * Instantiates a new dieta request.
	 * 
	 * @param dieta the dieta
	 */
	public DietaRequest(Dieta dieta)
	{
		addDieta(dieta);
	}

	/**
	 * Instantiates a new dieta request.
	 * 
	 * @param userContext the user context
	 */
	public DietaRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new dieta request.
	 * 
	 * @param dieta the dieta
	 * @param userContext the user context
	 */
	public DietaRequest(Dieta dieta, UserContext userContext)
	{
		addDieta(dieta);
		setUserContext(userContext);
	}

	/**
	 * Gets the dietas.
	 * 
	 * @return the dietas
	 */
	public List<Dieta> getDietas()
	{
		return dietas;
	}

	/**
	 * Gets the first dieta.
	 * 
	 * @return the first dieta
	 */  
	public Dieta getFirstDieta()
	{
		if (getDietas() != null && !getDietas().isEmpty())
		{
			return getDietas().get(0);
		}

		return null;
	}

	/**
	 * Sets the dietas.
	 * 
	 * @param dietas the new dietas
	 */
	public void setDietas(List<Dieta> dietas)
	{
		this.dietas = dietas;
	}

	/**
	 * Adds the dieta.
	 * 
	 * @param dieta the dieta
	 */
	public void addDieta(Dieta dieta)
	{
		if (getDietas() == null)
		{
			setDietas(new ArrayList<Dieta>());
		}

		getDietas().add(dieta);
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
		return "DietaRequest [getDietas()=" + getDietas() + ", getFirstDieta()=" + getFirstDieta()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}


