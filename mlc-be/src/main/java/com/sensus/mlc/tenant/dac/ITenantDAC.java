package com.sensus.mlc.tenant.dac;

import java.util.List;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Interface ITenantDAC.
 */
public interface ITenantDAC
{
	/**
	 * Fetch all tenants.
	 * 
	 * @return the list
	 */
	List<Tenant> fetchAllTenants();

	/**
	 * Fetch tenant by server name.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest);
}
