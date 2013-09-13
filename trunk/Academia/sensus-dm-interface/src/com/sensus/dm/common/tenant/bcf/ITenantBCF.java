package com.sensus.dm.common.tenant.bcf;

import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.tenant.model.response.TenantResponse;

/**
 * The Interface ITenantBCF.
 * 
 * @author QAT Global.
 */
public interface ITenantBCF
{
	/**
	 * Fetch tenant description.
	 * 
	 * @param tenantRequest the tenant request
	 * @return the tenant response
	 */
	TenantResponse fetchTenantDescription(TenantRequest tenantRequest);
}
