package com.prosperitasglobal.sendsolv.tenant.model.request;

import com.prosperitasglobal.sendsolv.tenant.model.Tenant;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.request.Request;

/**
 * The Class TenantRequest.
 */
public class TenantRequest extends Request
{
	/** Attributes. */
	private Tenant tenant;
	private String serverName;

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

	@Override
	public String toString()
	{
		return "TenantRequest [getTenant()=" + getTenant() + ", getServerName()=" + getServerName() + ", toString()="
				+ super.toString() + "]";
	}

}
