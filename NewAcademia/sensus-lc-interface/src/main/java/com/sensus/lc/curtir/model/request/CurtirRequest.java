package com.sensus.lc.curtir.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class CurtirRequest.
 * 
 * @author Washington
 */
public class CurtirRequest extends TenantRequest
{
	/** The curtir. */
	private List<Curtir> curtir;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new curtir request.
	 */
	public CurtirRequest()
	{
	}

	/**
	 * Instantiates a new curtir request.
	 * 
	 * @param curtir the curtir
	 */
	public CurtirRequest(Curtir curtir)
	{
		addCurtir(curtir);
	}

	/**
	 * Instantiates a new curtir request.
	 * 
	 * @param userContext the user context
	 */
	public CurtirRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new curtir request.
	 * 
	 * @param curtir the curtir
	 * @param userContext the user context
	 */
	public CurtirRequest(Curtir curtir, UserContext userContext)
	{
		addCurtir(curtir);
		setUserContext(userContext);
	}

	/**
	 * Gets the curtir.
	 * 
	 * @return the curtir
	 */
	public List<Curtir> getCurtir()
	{
		return curtir;
	}

	/**
	 * Gets the first curtir.
	 * 
	 * @return the first curtir
	 */
	public Curtir getFirstCurtir()
	{
		if ((getCurtir() != null) && !getCurtir().isEmpty())
		{
			return getCurtir().get(0);
		}

		return null;
	}

	/**
	 * Sets the curtir.
	 * 
	 * @param curtir the new curtir
	 */
	public void setCurtir(List<Curtir> curtir)
	{
		this.curtir = curtir;
	}

	/**
	 * Adds the curtir.
	 * 
	 * @param curtir the curtir
	 */
	public void addCurtir(Curtir curtir)
	{
		if (getCurtir() == null)
		{
			setCurtir(new ArrayList<Curtir>());
		}

		getCurtir().add(curtir);
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
		return "CurtirRequest [getCurtir()=" + getCurtir() + ", getFirstCurtir()=" + getFirstCurtir()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
