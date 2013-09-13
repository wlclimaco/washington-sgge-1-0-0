package com.sensus.dm.commons.serie.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.commons.serie.model.Serieitens;

/**
 * The Class SerieitensRequest.
 * 
 * @author Washington
 */
public class SerieitensRequest extends TenantRequest
{
	/** The serieitenss. */
	private List<Serieitens> serieitenss;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new serieitens request.
	 */
	public SerieitensRequest()
	{
	}

	/**
	 * Instantiates a new serieitens request.
	 * 
	 * @param serieitens the serieitens
	 */
	public SerieitensRequest(Serieitens serieitens)
	{
		addSerieitens(serieitens);
	}

	/**
	 * Instantiates a new serieitens request.
	 * 
	 * @param userContext the user context
	 */
	public SerieitensRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new serieitens request.
	 * 
	 * @param serieitens the serieitens
	 * @param userContext the user context
	 */
	public SerieitensRequest(Serieitens serieitens, UserContext userContext)
	{
		addSerieitens(serieitens);
		setUserContext(userContext);
	}

	/**
	 * Gets the serieitenss.
	 * 
	 * @return the serieitenss
	 */
	public List<Serieitens> getSerieitenss()
	{
		return serieitenss;
	}

	/**
	 * Gets the first serieitens.
	 * 
	 * @return the first serieitens
	 */
	public Serieitens getFirstSerieitens()
	{
		if ((getSerieitenss() != null) && !getSerieitenss().isEmpty())
		{
			return getSerieitenss().get(0);
		}

		return null;
	}

	/**
	 * Sets the serieitenss.
	 * 
	 * @param serieitenss the new serieitenss
	 */
	public void setSerieitenss(List<Serieitens> serieitenss)
	{
		this.serieitenss = serieitenss;
	}

	/**
	 * Adds the serieitens.
	 * 
	 * @param serieitens the serieitens
	 */
	public void addSerieitens(Serieitens serieitens)
	{
		if (getSerieitenss() == null)
		{
			setSerieitenss(new ArrayList<Serieitens>());
		}

		getSerieitenss().add(serieitens);
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
		return "SerieitensRequest [getSerieitenss()=" + getSerieitenss() + ", getFirstSerieitens()="
				+ getFirstSerieitens()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
