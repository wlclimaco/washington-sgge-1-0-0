package com.sensus.lc.tenant.bcl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class MockTenantBCL.
 */
public class MockTenantBCL extends AbstractMockBase implements ITenantBCL
{
	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		return getTenantResultsResponseBySituations();
	}

	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(TenantRequest request)
	{
		return getTenantResultsResponseBySituations();
	}

	@Override
	public InternalResultsResponse<Tenant> fetchTenantById(TenantRequest tenantRequest)
	{
		return getTenantResultsResponseBySituations();
	}

	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
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

	@Override
	public InternalResultsResponse<Boolean> verifyAPIAccess(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Boolean> internalResultsResponse = new InternalResultsResponse<Boolean>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResultsResponse.addResult(Boolean.TRUE);
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}
}
