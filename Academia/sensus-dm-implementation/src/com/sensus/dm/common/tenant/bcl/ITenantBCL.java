package com.sensus.dm.common.tenant.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Interface ITenantBCL.
 * 
 * @author QAT Global.
 */
public interface ITenantBCL
{

	/**
	 * Fetch tenant description.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMTenant> fetchTenantDescription(TenantRequest tenantRequest);

}
