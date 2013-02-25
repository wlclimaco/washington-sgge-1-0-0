package com.sensus.mlc.tenant.bcl.impl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tenant.bcl.ITenantBCL;
import com.sensus.mlc.tenant.dac.ITenantDAC;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class TenantBCLImpl.
 */
public class TenantBCLImpl implements ITenantBCL
{
	/** The tenant dac. */
	private ITenantDAC tenantDAC;

	/**
	 * Gets the tenant dac.
	 *
	 * @return the tenant dac
	 */
	public ITenantDAC getTenantDAC()
	{
		return tenantDAC;
	}

	/**
	 * Sets the tenant dac.
	 *
	 * @param tenantDAC the new tenant dac
	 */
	public void setTenantDAC(ITenantDAC tenantDAC)
	{
		this.tenantDAC = tenantDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tenant.bcl.ITenantBCL#fetchAllTenants()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenants()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();
		internalResultsResponse.addResults(
				getTenantDAC().fetchAllTenants());
		return internalResultsResponse;
	}
}
