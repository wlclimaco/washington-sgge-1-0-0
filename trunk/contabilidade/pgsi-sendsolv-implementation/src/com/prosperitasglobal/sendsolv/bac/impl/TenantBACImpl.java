package com.prosperitasglobal.sendsolv.bac.impl;

import com.prosperitasglobal.sendsolv.bac.ITenantBAC;
import com.prosperitasglobal.sendsolv.dac.ITenantDAC;
import com.prosperitasglobal.sendsolv.tenant.model.ApiControl;
import com.prosperitasglobal.sendsolv.tenant.model.Tenant;
import com.prosperitasglobal.sendsolv.tenant.model.request.ApiControlMaintenanceRequest;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class TenantBCLImpl.
 */
public class TenantBACImpl implements ITenantBAC
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
		apiControlRequest.getApiControl().setTenantId(tenantRequest.getTenant().getEmprId());

		InternalResultsResponse<ApiControl> responseApiControl = getTenantDAC().updateApiControl(apiControlRequest);

		if (responseApiControl.isInError() || (responseApiControl.getFirstResult().getCount() > numberApiAccessHour))
		{
			response.addResult(Boolean.FALSE);
			return response;
		}

		response.addResult(Boolean.TRUE);
		return response;
	}

}
