package com.sensus.mlc.tenant.dac;

import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Class MockTenantDAC.
 */
public class MockTenantDAC extends AbstractMockBase implements ITenantDAC
{

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tenant.dac.ITenantDAC#fetchAllTenants()
	 */
	@Override
	public List<Tenant> fetchAllTenants()
	{
		return Arrays.asList(TestBaseUtil.createTenant());
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.tenant.dac.ITenantDAC#fetchTenantByServerName(com.sensus.mlc.tenant.model.request.TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		return testSituationsTenantResultsResponse();
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
	 * Test situations tenant results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tenant> testSituationsTenantResultsResponse()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getTenantResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}
}
