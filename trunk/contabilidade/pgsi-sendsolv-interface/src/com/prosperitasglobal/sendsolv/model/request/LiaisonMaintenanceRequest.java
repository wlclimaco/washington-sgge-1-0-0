package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Liaison;

/**
 * The Class LiaisonMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:15:09 PM
 */
public class LiaisonMaintenanceRequest extends Request
{
	/** Attributes. */
	private Liaison liaison;

	/**
	 * The Constructor.
	 */
	public LiaisonMaintenanceRequest()
	{

	}

	/**
	 * Gets the liaison.
	 *
	 * @return the liaison
	 */
	public Liaison getLiaison()
	{
		return liaison;
	}

	/**
	 * Sets the liaison.
	 *
	 * @param liaison the liaison
	 */
	public void setLiaison(Liaison liaison)
	{
		this.liaison = liaison;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LiaisonMaintenanceRequest [getLiaison()=" + getLiaison() + ", getUserContext()=" + getUserContext()
				+ "]";
	}
}