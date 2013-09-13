package com.sensus.dm.common.tenant.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockTenantDAC.
 * 
 * @author QAT Global.
 */
public class MockTenantDAC extends AbstractMockBase implements ITenantDAC
{

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "ACME";

	/** The Constant CUSTOMER_DESCRIPTION. */
	private static final String CUSTOMER_DESCRIPTION = "Default Customer";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.dac.ITenantDAC#fetchTenantDescription(com.sensus.dm.common.tenant.model.request.
	 * TenantRequest)
	 */
	@Override
	public InternalResultsResponse<DMTenant> fetchTenantDescription(TenantRequest tenantRequest)
	{
		return new InternalResultsResponse<DMTenant>(new DMTenant(CUSTOMER_ID, CUSTOMER_DESCRIPTION));
	}

}
