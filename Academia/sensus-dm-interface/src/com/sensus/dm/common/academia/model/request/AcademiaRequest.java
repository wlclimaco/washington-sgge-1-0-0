package com.sensus.dm.common.academia.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class AcademiaRequest.
 * 
 * @author Washington
 */
public class AcademiaRequest extends TenantRequest
{
	/** The academias. */
	private List<Academia> academias;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new academia request.
	 */
	public AcademiaRequest()
	{
	}

	/**
	 * Instantiates a new academia request.
	 * 
	 * @param academia the academia
	 */
	public AcademiaRequest(Academia academia)
	{
		addAcademia(academia);
	}

	/**
	 * Instantiates a new academia request.
	 * 
	 * @param userContext the user context
	 */
	public AcademiaRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new academia request.
	 * 
	 * @param academia the academia
	 * @param userContext the user context
	 */
	public AcademiaRequest(Academia academia, UserContext userContext)
	{
		addAcademia(academia);
		setUserContext(userContext);
	}

	/**
	 * Gets the academias.
	 * 
	 * @return the academias
	 */
	public List<Academia> getAcademias()
	{
		return academias;
	}

	/**
	 * Gets the first academia.
	 * 
	 * @return the first academia
	 */  
	public Academia getFirstAcademia()
	{
		if (getAcademias() != null && !getAcademias().isEmpty())
		{
			return getAcademias().get(0);
		}

		return null;
	}

	/**
	 * Sets the academias.
	 * 
	 * @param academias the new academias
	 */
	public void setAcademias(List<Academia> academias)
	{
		this.academias = academias;
	}

	/**
	 * Adds the academia.
	 * 
	 * @param academia the academia
	 */
	public void addAcademia(Academia academia)
	{
		if (getAcademias() == null)
		{
			setAcademias(new ArrayList<Academia>());
		}

		getAcademias().add(academia);
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
		return "AcademiaRequest [getAcademias()=" + getAcademias() + ", getFirstAcademia()=" + getFirstAcademia()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}


