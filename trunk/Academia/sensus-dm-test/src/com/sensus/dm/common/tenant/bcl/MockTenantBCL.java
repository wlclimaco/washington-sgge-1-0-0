package com.sensus.dm.common.tenant.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockTenantBCL.
 * 
 * @author QAT Global.
 */
public class MockTenantBCL extends AbstractMockBase implements ITenantBCL
{

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "ACME";

	/** The Constant CUSTOMER_DESCRIPTION. */
	private static final String CUSTOMER_DESCRIPTION = "Default Customer";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.bcl.ITenantBCL#fetchTenantDescription(com.sensus.dm.common.tenant.model.request.
	 * TenantRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMTenant> fetchTenantDescription(TenantRequest tenantRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<DMTenant>(new DMTenant(CUSTOMER_ID, CUSTOMER_DESCRIPTION));
		}

		return (InternalResultsResponse<DMTenant>)verifyOtherSituations();

	}

}
