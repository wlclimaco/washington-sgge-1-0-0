package com.sensus.lc.tenant.bcl.impl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.dac.ITenantDAC;
import com.sensus.lc.tenant.model.ApiControl;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.ApiControlMaintenanceRequest;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class TenantBCLImpl.
 */
public class TenantBCLImpl implements ITenantBCL
{
	/** The tenant dac. */
	private ITenantDAC tenantDAC;

	/**
	 * Gets the tenant dac.
	 * 
	 * @return the tenant dac
	 */
	public ITenantDAC getTenantDAC()
	{
		return tenantDAC;
	}

	/**
	 * Sets the tenant dac.
	 * 
	 * @param tenantDAC the new tenant dac
	 */
	public void setTenantDAC(ITenantDAC tenantDAC)
	{
		this.tenantDAC = tenantDAC;
	}

	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		return getTenantDAC().fetchAllTenant();
	}

	@Override
	public InternalResultsResponse<Tenant> fetchTenantById(TenantRequest tenantRequest)
	{
		return getTenantDAC().fetchTenantById(tenantRequest);
	}

	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(TenantRequest tenantRequest)
	{
		return getTenantDAC().fetchTenantByRniCode(tenantRequest);
	}

	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		return getTenantDAC().fetchTenantByServerName(tenantRequest);
	}

	@Override
	public InternalResultsResponse<Boolean> verifyAPIAccess(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<>();

		// Get the number of access allowed per tenant/hour
		InternalResultsResponse<Tenant> tenantResponse = getTenantDAC().fetchTenantById(tenantRequest);

		if (tenantResponse.isInError())
		{
			response.addResult(Boolean.FALSE);
			return response;
		}

		Integer numberApiAccessHour = tenantResponse.getFirstResult().getNumberApiAccessHour();

		if (ValidationUtil.isNullOrZero(numberApiAccessHour))
		{
			response.addResult(Boolean.FALSE);
			return response;
		}

		// Prepare the request to do the update
		ApiControlMaintenanceRequest apiControlRequest = new ApiControlMaintenanceRequest();
		apiControlRequest.setApiControl(new ApiControl());
		apiControlRequest.getApiControl().setTenantId(tenantRequest.getTenant().getId());

		InternalResultsResponse<ApiControl> responseApiControl = getTenantDAC().updateApiControl(apiControlRequest);

		if (responseApiControl.isInError() || responseApiControl.getFirstResult().getCount() > numberApiAccessHour)
		{
			response.addResult(Boolean.FALSE);
			return response;
		}

		response.addResult(Boolean.TRUE);
		return response;
	}

}
