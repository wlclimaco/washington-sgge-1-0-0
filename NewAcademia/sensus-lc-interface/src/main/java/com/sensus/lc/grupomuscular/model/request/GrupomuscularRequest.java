package com.sensus.lc.grupomuscular.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.grupomuscular.model.Grupomuscular;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class GrupomuscularRequest.
 * 
 * @author Washington
 */
public class GrupomuscularRequest extends TenantRequest
{
	/** The grupomusculars. */
	private List<Grupomuscular> grupomusculars;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new grupomuscular request.
	 */
	public GrupomuscularRequest()
	{
	}

	/**
	 * Instantiates a new grupomuscular request.
	 * 
	 * @param grupomuscular the grupomuscular
	 */
	public GrupomuscularRequest(Grupomuscular grupomuscular)
	{
		addGrupomuscular(grupomuscular);
	}

	/**
	 * Instantiates a new grupomuscular request.
	 * 
	 * @param userContext the user context
	 */
	public GrupomuscularRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new grupomuscular request.
	 * 
	 * @param grupomuscular the grupomuscular
	 * @param userContext the user context
	 */
	public GrupomuscularRequest(Grupomuscular grupomuscular, UserContext userContext)
	{
		addGrupomuscular(grupomuscular);
		setUserContext(userContext);
	}

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
	 * Gets the first grupomuscular.
	 * 
	 * @return the first grupomuscular
	 */
	public Grupomuscular getFirstGrupomuscular()
	{
		if ((getGrupomusculars() != null) && !getGrupomusculars().isEmpty())
		{
			return getGrupomusculars().get(0);
		}

		return null;
	}

	/**
	 * Sets the grupomusculars.
	 * 
	 * @param grupomusculars the new grupomusculars
	 */
	public void setGrupomusculars(List<Grupomuscular> grupomusculars)
	{
		this.grupomusculars = grupomusculars;
	}

	/**
	 * Adds the grupomuscular.
	 * 
	 * @param grupomuscular the grupomuscular
	 */
	public void addGrupomuscular(Grupomuscular grupomuscular)
	{
		if (getGrupomusculars() == null)
		{
			setGrupomusculars(new ArrayList<Grupomuscular>());
		}

		getGrupomusculars().add(grupomuscular);
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
		return "GrupomuscularRequest [getGrupomusculars()=" + getGrupomusculars() + ", getFirstGrupomuscular()="
				+ getFirstGrupomuscular()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
