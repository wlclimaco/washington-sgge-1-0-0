package com.prosperitasglobal.sendsolv.bai.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.bac.ITenantBAC;
import com.prosperitasglobal.sendsolv.tenant.bai.ITenantBAI;
import com.prosperitasglobal.sendsolv.tenant.model.Tenant;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.prosperitasglobal.sendsolv.tenant.model.response.ApiControlResponse;
import com.prosperitasglobal.sendsolv.tenant.model.response.TenantResponse;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class TenantBCFImpl.
 */
public class TenantBAIImpl implements ITenantBAI
{

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant DEFAULT_TENANT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TENANT_BCF_EXCEPTION_MSG = "sensus.mlc.tenantbcfimpl.defaultexception";

	/** The Constant DEFAULT_TENANT_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_TENANT_BCL_EXCEPTION_MSG = "sensus.mlc.tenantbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TenantBAIImpl.class);

	/** The tenant bcl. */
	private ITenantBAC tenantBCL;

	/**
	 * Gets the tenant bcl.
	 *
	 * @return the tenant bcl
	 */
	public ITenantBAC getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 *
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBAC tenantBCL)
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
			handleException(LOG, response, ex, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	private void handleException(Logger log2, TenantResponse response, Exception ex, String defaultTenantBcfExceptionMsg)
	{
		// TODO Auto-generated method stub

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
					|| ValidationUtil.isNullOrZero(tenantRequest.getTenant().getEmprId()))
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
			handleExceptionAPI(LOG, response, ex, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	private void handleOperationStatusAndMessages(TenantResponse response,
			InternalResultsResponse<Tenant> internalResponse, String defaultTenantBclExceptionMsg)
	{
		// TODO Auto-generated method stub

	}

	private void handleExceptionAPI(Logger log2, ApiControlResponse response, Exception ex,
			String defaultTenantBcfExceptionMsg)
	{
		// TODO Auto-generated method stub

	}
}
