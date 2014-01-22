package com.sensus.lc.dieta.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.dieta.model.Dietaitens;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class DietaitensRequest.
 * 
 * @author Washington
 */
public class DietaitensRequest extends TenantRequest
{
	/** The dietaitenss. */
	private List<Dietaitens> dietaitenss;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new dietaitens request.
	 */
	public DietaitensRequest()
	{
	}

	/**
	 * Instantiates a new dietaitens request.
	 * 
	 * @param dietaitens the dietaitens
	 */
	public DietaitensRequest(Dietaitens dietaitens)
	{
		addDietaitens(dietaitens);
	}

	/**
	 * Instantiates a new dietaitens request.
	 * 
	 * @param userContext the user context
	 */
	public DietaitensRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new dietaitens request.
	 * 
	 * @param dietaitens the dietaitens
	 * @param userContext the user context
	 */
	public DietaitensRequest(Dietaitens dietaitens, UserContext userContext)
	{
		addDietaitens(dietaitens);
		setUserContext(userContext);
	}

	/**
	 * Gets the dietaitenss.
	 * 
	 * @return the dietaitenss
	 */
	public List<Dietaitens> getDietaitenss()
	{
		return dietaitenss;
	}

	/**
	 * Gets the first dietaitens.
	 * 
	 * @return the first dietaitens
	 */
	public Dietaitens getFirstDietaitens()
	{
		if ((getDietaitenss() != null) && !getDietaitenss().isEmpty())
		{
			return getDietaitenss().get(0);
		}

		return null;
	}

	/**
	 * Sets the dietaitenss.
	 * 
	 * @param dietaitenss the new dietaitenss
	 */
	public void setDietaitenss(List<Dietaitens> dietaitenss)
	{
		this.dietaitenss = dietaitenss;
	}

	/**
	 * Adds the dietaitens.
	 * 
	 * @param dietaitens the dietaitens
	 */
	public void addDietaitens(Dietaitens dietaitens)
	{
		if (getDietaitenss() == null)
		{
			setDietaitenss(new ArrayList<Dietaitens>());
		}

		getDietaitenss().add(dietaitens);
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
		return "DietaitensRequest [getDietaitenss()=" + getDietaitenss() + ", getFirstDietaitens()="
				+ getFirstDietaitens()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
