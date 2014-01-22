package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.InquiryRequest;
import com.sensus.lc.tenant.model.Tenant;

public class CommunicationFailureRequest extends InquiryRequest
{
	private Tenant tenant;

	private String lightIds; // created this attribute to add a list of lights to pass to function on postgres.

	/**
	 * Instantiates a new communication failure request.
	 */
	public CommunicationFailureRequest()
	{
	}

	/**
	 * Instantiates a new communication failure request.
	 * 
	 * @param tenant the tenant
	 */
	public CommunicationFailureRequest(Tenant tenant)
	{
		super();
		this.tenant = tenant;
	}

	/**
	 * Gets the tenant.
	 * 
	 * @return the tenant
	 */
	public Tenant getTenant()
	{
		return tenant;
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param tenant the new tenant
	 */
	public void setTenant(Tenant tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * Gets the light ids.
	 * 
	 * @return the light ids
	 */
	public String getLightIds()
	{
		return lightIds;
	}

	/**
	 * Sets the light ids.
	 * 
	 * @param lightIds the new light ids
	 */
	public void setLightIds(String lightIds)
	{
		this.lightIds = lightIds;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CommunicationFailureRequest [getTenant()=" + getTenant() + ", getLightIds()=" + getLightIds()
				+ ", toString()=" + super.toString() + "]";
	}
}
