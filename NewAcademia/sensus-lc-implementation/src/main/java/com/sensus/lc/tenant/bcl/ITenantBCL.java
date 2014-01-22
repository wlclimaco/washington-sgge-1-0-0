package com.sensus.lc.tenant.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Interface ITenantBCL.
 */
public interface ITenantBCL
{
	/**
	 * Fetch all tenant.
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchAllTenant();

	/**
	 * Fetch tenant by id.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the tenant
	 */
	InternalResultsResponse<Tenant> fetchTenantById(TenantRequest tenantRequest);

	/**
	 * Fetch tenant by rni code.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchTenantByRniCode(TenantRequest tenantRequest);

	/**
	 * Fetch tenant by server name.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest);

	/**
	 * Verify api access.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<Boolean> verifyAPIAccess(TenantRequest tenantRequest);
}
