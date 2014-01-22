package com.sensus.lc.tenant.model.response;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class TenantResponse.
 */
public class TenantResponse extends Response
{
	/** The tenant. */
	private Tenant tenant = new Tenant();

	/** The tenants. */
	private List<Tenant> tenants = new ArrayList<Tenant>();

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
	 * Gets the tenants.
	 * 
	 * @return the tenants
	 */
	public List<Tenant> getTenants()
	{
		return tenants;
	}

	/**
	 * Sets the tenants.
	 * 
	 * @param tenants the new tenants
	 */
	public void setTenants(List<Tenant> tenants)
	{
		this.tenants = tenants;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TenantResponse [getTenant()=" + getTenant() + ", getTenants()=" + getTenants() + ", toString()="
				+ super.toString() + "]";
	}
}
