package com.sensus.mlc.tenant.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.tenant.bcf.ITenantBCF;
import com.sensus.mlc.tenant.bcl.ITenantBCL;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class TenantBCFImpl.
 */
public class TenantBCFImpl implements ITenantBCF
{
	/** The Constant DEFAULT_TENANT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TENANT_BCF_EXCEPTION_MSG = "sensus.mlc.tenantbcfimpl.defaultexception";

	/** The Constant DEFAULT_TENANT_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_TENANT_BCL_EXCEPTION_MSG = "sensus.mlc.tenantbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TenantBCFImpl.class);

	/** The tenant bcl. */
	private ITenantBCL tenantBCL;

	/**
	 * Gets the tenant bcl.
	 *
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 *
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tenant.bcf.ITenantBCF#fetchAllTenants()
	 */
	@Override
	public TenantResponse fetchAllTenants()
	{
		TenantResponse response = new TenantResponse();
		InternalResultsResponse<Tenant> internalResponse = null;

		try
		{
			internalResponse = getTenantBCL().fetchAllTenants();
			response.setTenants(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, DEFAULT_TENANT_BCL_EXCEPTION_MSG);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
