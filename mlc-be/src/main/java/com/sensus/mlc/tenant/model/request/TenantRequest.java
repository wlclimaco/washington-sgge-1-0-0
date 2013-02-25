package com.sensus.mlc.tenant.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class TenantRequest.
 */
public class TenantRequest extends Request
{
	/** The tenant. */
	private Tenant tenant;

	/** The server name. */
	private String serverName;

	/** The communication failure. */
	private Boolean communicationFailure;

	/**
	 * Instantiates a new tenant request.
	 */
	public TenantRequest()
	{
		setUserContext(new UserContext());
	}

	/**
	 * Instantiates a new tenant request.
	 *
	 * @param userContext the user context
	 */
	public TenantRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new tenant request.
	 *
	 * @param tenant the tenant
	 */
	public TenantRequest(Tenant tenantValue)
	{
		setTenant(tenantValue);
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
	 * Gets the server name.
	 *
	 * @return the server name
	 */
	public String getServerName()
	{
		return serverName;
	}

	/**
	 * Sets the server name.
	 *
	 * @param serverName the new server name
	 */
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	/**
	 * Gets the communication failure.
	 *
	 * @return the communication failure
	 */
	public Boolean getCommunicationFailure()
	{
		return communicationFailure;
	}

	/**
	 * Sets the communication failure.
	 *
	 * @param communicationFailure the new communication failure
	 */
	public void setCommunicationFailure(Boolean communicationFailure)
	{
		this.communicationFailure = communicationFailure;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TenantRequest [getTenant()=" + getTenant() + ", getServerName()=" + getServerName()
				+ ", getCommunicationFailure()=" + getCommunicationFailure() + "]";
	}

}
