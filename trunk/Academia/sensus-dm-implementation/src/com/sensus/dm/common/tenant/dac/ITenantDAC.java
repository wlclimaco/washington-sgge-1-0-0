package com.sensus.dm.common.tenant.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Interface ITenantDAC.
 * 
 * @author QAT Global.
 */
public interface ITenantDAC
{
	/**
	 * Fetch tenant description.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMTenant> fetchTenantDescription(TenantRequest tenantRequest);

}
