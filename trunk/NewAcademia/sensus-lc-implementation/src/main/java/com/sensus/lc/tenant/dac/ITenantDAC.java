package com.sensus.lc.tenant.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.tenant.model.ApiControl;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.ApiControlMaintenanceRequest;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Interface ITenantDAC.
 */
public interface ITenantDAC
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
	 * Update api control.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<ApiControl> updateApiControl(ApiControlMaintenanceRequest request);

}
