package com.sensus.dm.common.tenant.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class TenantResponse.
 * 
 * @author QAT Global.
 */
public class TenantResponse extends Response
{

	/** The tenant. */
	private List<DMTenant> tenant;

	/**
	 * Gets the tenant.
	 * 
	 * @return the tenant
	 */
	public List<DMTenant> getTenant()
	{
		return tenant;
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param tenant the new tenant
	 */
	public void setTenant(List<DMTenant> tenant)
	{
		this.tenant = tenant;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TenantResponse [getTenant()=" + getTenant() + ", toString()=" + super.toString() + "]";
	}
}
