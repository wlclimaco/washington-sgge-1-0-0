package com.sensus.lc.tenant.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.tenant.bcf.ITenantBCF;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.ApiControlResponse;
import com.sensus.lc.tenant.model.response.TenantResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class TenantBCFImpl.
 */
public class TenantBCFImpl implements ITenantBCF
{

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

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
			internalResponse = getTenantBCL().fetchAllTenant();
			response.setTenants(internalResponse.getResultsList());

			handleOperationStatusAndMessages(response, internalResponse, DEFAULT_TENANT_BCL_EXCEPTION_MSG);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.tenant.bcf.ITenantBCF#verifyAPIAccess(com.sensus.lc.tenant.model.request.TenantRequest)
	 */
	@Override
	public ApiControlResponse verifyAPIAccess(TenantRequest tenantRequest)
	{
		ApiControlResponse response = new ApiControlResponse();

		try
		{
			if (ValidationUtil.isNull(tenantRequest.getTenant())
					|| ValidationUtil.isNullOrZero(tenantRequest.getTenant().getId()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_ID_REQUIRED));
				response.setOperationSuccess(Boolean.FALSE);
				return response;
			}

			InternalResultsResponse<Boolean> internalResponse = getTenantBCL().verifyAPIAccess(tenantRequest);

			if (!ValidationUtil.isNull(internalResponse))
			{
				response.setAllowAPIAccess(internalResponse.getFirstResult());
			}

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
		}

		return response;

	}
}
