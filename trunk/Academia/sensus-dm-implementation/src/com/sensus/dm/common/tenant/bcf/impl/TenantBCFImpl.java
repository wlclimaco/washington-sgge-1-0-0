package com.sensus.dm.common.tenant.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.tenant.bcf.ITenantBCF;
import com.sensus.dm.common.tenant.bcl.ITenantBCL;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.tenant.model.response.TenantResponse;

/**
 * The Class TenantBCFImpl.
 * 
 * @author QAT Global.
 */
public class TenantBCFImpl implements ITenantBCF
{

	/** The Constant DEFAULT_TENANT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TENANT_BCF_EXCEPTION_MSG = "sensus.dm.common.tenantbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TenantBCFImpl.class);

	/** The tenant bcl. */
	private ITenantBCL tenantBCL;

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

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

	/**
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the tenant request validation controller.
	 * 
	 * @param tenantRequestValidationController the new tenant request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.bcf.ITenantBCF#fetchTenantDescription(com.sensus.dm.common.tenant.model.request.
	 * TenantRequest)
	 */
	@Override
	public TenantResponse fetchTenantDescription(TenantRequest tenantRequest)
	{
		TenantResponse response = new TenantResponse();

		try
		{
			InternalResultsResponse<DMTenant> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_TENANT_BY_DESCRIPTION);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), tenantRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getTenantBCL().fetchTenantDescription(tenantRequest);
				response.setTenant(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
