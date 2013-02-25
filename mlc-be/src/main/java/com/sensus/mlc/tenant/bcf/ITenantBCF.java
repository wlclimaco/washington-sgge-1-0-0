package com.sensus.mlc.tenant.bcf;

import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Interface ITenantBCF.
 */
public interface ITenantBCF
{
	/**
	 * Fetch all tenants.
	 * 
	 * @return the tenant response
	 */
	TenantResponse fetchAllTenants();

}
