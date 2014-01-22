package com.sensus.lc.tenant.bcf;

import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.ApiControlResponse;
import com.sensus.lc.tenant.model.response.TenantResponse;

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

	/**
	 * Verify api access.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the api control response
	 */
	ApiControlResponse verifyAPIAccess(TenantRequest tenantRequest);
}
