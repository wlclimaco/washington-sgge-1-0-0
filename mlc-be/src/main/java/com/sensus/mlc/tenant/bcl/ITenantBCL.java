package com.sensus.mlc.tenant.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Interface ITenantBCL.
 */
public interface ITenantBCL
{
	/**
	 * Fetch all tenants.
	 *
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchAllTenants();

}
