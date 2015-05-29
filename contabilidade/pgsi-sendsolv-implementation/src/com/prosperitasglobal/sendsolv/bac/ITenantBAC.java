package com.prosperitasglobal.sendsolv.bac;

import com.prosperitasglobal.sendsolv.tenant.model.Tenant;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ITenantBCL.
 */
public interface ITenantBAC
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
