package com.sensus.lc.tenant.dac;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.tenant.model.ApiControl;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.ApiControlMaintenanceRequest;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class MockTenantDAC.
 */
public class MockTenantDAC extends AbstractMockBase implements ITenantDAC
{

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#fetchAllTenant()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		return getResultsResponseTenantBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.tenant.dac.ITenantDAC#fetchTenantByServerName(com.sensus.lc.tenant.model.request.TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		return testSituationsTenantResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#fetchTenantById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantById(TenantRequest request)
	{
		return getResultsResponseTenantBySituation();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.dac.IProcessDAC#fetchTenantByRniCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(TenantRequest request)
	{
		return getResultsResponseTenantBySituation();
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

	/**
	 * Gets the results response tenant by situation.
	 * 
	 * @return the results response tenant by situation
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tenant> getResultsResponseTenantBySituation()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getTenantResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the tenant response.
	 * 
	 * @return the tenant response
	 */
	private InternalResultsResponse<Tenant> getTenantResponse()
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		response.addResult(TestBaseUtil.createTenant());

		// -- to test the alert communication time out
		if (getTestControl() == "TIME_OUT_ALERT_COMMUNICATION")
		{
			response.getFirstResult().setCommunicationCycleTimeout(TEN);
		}

		// test the situation when the tenant doesn't have a number api access per hour
		if (getTestControl() == "TENANT_WITHOUT_CONFIGURATION_API_ACCESS")
		{
			response.getResultsList().get(0).setNumberApiAccessHour(null);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<ApiControl> updateApiControl(ApiControlMaintenanceRequest request)
	{
		InternalResultsResponse<ApiControl> internalResultsResponse = new InternalResultsResponse<ApiControl>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResultsResponse.addResult(TestBaseUtil.createApiControl());
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
