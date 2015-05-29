package com.prosperitasglobal.sendsolv.tenant.bai;

import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.prosperitasglobal.sendsolv.tenant.model.response.ApiControlResponse;
import com.prosperitasglobal.sendsolv.tenant.model.response.TenantResponse;

/**
 * The Interface ITenantBCF.
 */
public interface ITenantBAI
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
