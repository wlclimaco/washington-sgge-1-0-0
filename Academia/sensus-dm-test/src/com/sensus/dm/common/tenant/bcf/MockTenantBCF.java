package com.sensus.dm.common.tenant.bcf;

import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.tenant.model.response.TenantResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockTenantBCF.
 * 
 * @author QAT Global.
 */
public class MockTenantBCF extends AbstractMockBase implements ITenantBCF
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.bcf.ITenantBCF#fetchTenantDescription(com.sensus.dm.common.tenant.model.request.
	 * TenantRequest)
	 */
	@Override
	public TenantResponse fetchTenantDescription(TenantRequest tenantRequest)
	{
		return new TenantResponse();
	}

}
