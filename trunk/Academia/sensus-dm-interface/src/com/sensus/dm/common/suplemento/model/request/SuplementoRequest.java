package com.sensus.dm.common.suplemento.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.suplemento.model.Suplemento;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class SuplementoRequest.
 * 
 * @author Washington
 */
public class SuplementoRequest extends TenantRequest
{
	/** The suplementos. */
	private List<Suplemento> suplementos;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new suplemento request.
	 */
	public SuplementoRequest()
	{
	}

	/**
	 * Instantiates a new suplemento request.
	 * 
	 * @param suplemento the suplemento
	 */
	public SuplementoRequest(Suplemento suplemento)
	{
		addSuplemento(suplemento);
	}

	/**
	 * Instantiates a new suplemento request.
	 * 
	 * @param userContext the user context
	 */
	public SuplementoRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new suplemento request.
	 * 
	 * @param suplemento the suplemento
	 * @param userContext the user context
	 */
	public SuplementoRequest(Suplemento suplemento, UserContext userContext)
	{
		addSuplemento(suplemento);
		setUserContext(userContext);
	}

	/**
	 * Gets the suplementos.
	 * 
	 * @return the suplementos
	 */
	public List<Suplemento> getSuplementos()
	{
		return suplementos;
	}

	/**
	 * Gets the first suplemento.
	 * 
	 * @return the first suplemento
	 */  
	public Suplemento getFirstSuplemento()
	{
		if (getSuplementos() != null && !getSuplementos().isEmpty())
		{
			return getSuplementos().get(0);
		}

		return null;
	}

	/**
	 * Sets the suplementos.
	 * 
	 * @param suplementos the new suplementos
	 */
	public void setSuplementos(List<Suplemento> suplementos)
	{
		this.suplementos = suplementos;
	}

	/**
	 * Adds the suplemento.
	 * 
	 * @param suplemento the suplemento
	 */
	public void addSuplemento(Suplemento suplemento)
	{
		if (getSuplementos() == null)
		{
			setSuplementos(new ArrayList<Suplemento>());
		}

		getSuplementos().add(suplemento);
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
		return "SuplementoRequest [getSuplementos()=" + getSuplementos() + ", getFirstSuplemento()=" + getFirstSuplemento()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}


