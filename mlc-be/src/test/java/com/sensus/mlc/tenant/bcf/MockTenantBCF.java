package com.sensus.mlc.tenant.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.tenant.model.response.TenantResponse;

public class MockTenantBCF extends AbstractMockBase implements ITenantBCF
{

	@Override
	public TenantResponse fetchAllTenants()
	{
		return new TenantResponse();
	}
}
