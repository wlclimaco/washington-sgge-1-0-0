package com.sensus.dm.common.tenant.bcl.impl;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.bcl.ITenantBCL;
import com.sensus.dm.common.tenant.dac.ITenantDAC;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class TenantBCLImpl.
 * 
 * @author QAT Global.
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.bcl.ITenantBCL#fetchTenantDescription(com.sensus.dm.common.tenant.model.request.
	 * TenantRequest)
	 */
	@Override
	public InternalResultsResponse<DMTenant> fetchTenantDescription(TenantRequest tenantRequest)
	{
		return getTenantDAC().fetchTenantDescription(tenantRequest);
	}

}
