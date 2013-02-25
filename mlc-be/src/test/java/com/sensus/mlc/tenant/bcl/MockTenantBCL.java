package com.sensus.mlc.tenant.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class MockTenantBCL.
 */
public class MockTenantBCL extends AbstractMockBase implements ITenantBCL
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tenant.bcl.ITenantBCL#fetchAllTenants()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenants()
	{
		return getTenantResultsResponseBySituations();
	}


	/**
	 * Gets the tenant response default.
	 *
	 * @return the tenant response default
	 */
	private InternalResultsResponse<Tenant> getTenantResponseDefault()
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		response.addResult(TestBaseUtil.createTenant());
		return response;
	}

	/**
	 * Gets the tenant results response by situations.
	 *
	 * @return the tenant results response by situations
	 */
	private InternalResultsResponse<Tenant> getTenantResultsResponseBySituations()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = getTenantResponseDefault();

		if (getAreaEnum() != LCAreaEnum.TENANT)
		{
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException("Test Exception");
		}

		return internalResultsResponse;
	}
}
