package com.sensus.lc.tenant.model.response;

import com.sensus.common.model.response.Response;

/**
 * The Class ApiControlResponse.
 */
public class ApiControlResponse extends Response
{

	/** The allow api acess. */
	private Boolean allowAPIAccess;

	/**
	 * Gets the allow api acess.
	 * 
	 * @return the allowAPIAcess
	 */
	public Boolean getAllowAPIAccess()
	{
		return allowAPIAccess;
	}

	/**
	 * Sets the allow api acess.
	 * 
	 * @param allowAPIAcess the allowAPIAcess to set
	 */
	public void setAllowAPIAccess(Boolean allowAPIAcess)
	{
		this.allowAPIAccess = allowAPIAcess;
	}

}
