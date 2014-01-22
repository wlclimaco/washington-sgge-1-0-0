package com.sensus.lc.tenant.bcf;

import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.ApiControlResponse;
import com.sensus.lc.tenant.model.response.TenantResponse;

public class MockTenantBCF extends AbstractMockBase implements ITenantBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.process.bcf.IProcessBCF#fetchTenantByRniCode(com.sensus.lc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public TenantResponse fetchAllTenants()
	{
		TenantResponse response = new TenantResponse();
		response.setTenant(TestBaseUtil.createTenant());
		return response;
	}

	@Override
	public ApiControlResponse verifyAPIAccess(TenantRequest tenantRequest)
	{
		ApiControlResponse apiControlResponse = new ApiControlResponse();
		apiControlResponse.setAllowAPIAccess(Boolean.TRUE);

		return apiControlResponse;
	}
}
