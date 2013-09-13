package com.sensus.dm.common.process.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.process.bcf.IProcessTypeBCF;
import com.sensus.dm.common.process.bcl.IProcessTypeBCL;
import com.sensus.dm.common.process.model.ProcessCategory;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class ProcessTypeBCFImpl.
 * 
 * @author QAT Global
 */
public class ProcessTypeBCFImpl implements IProcessTypeBCF
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessTypeBCFImpl.class);

	// -------------------------------------------------------------------------

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	/** The process type bcl. */
	private IProcessTypeBCL processTypeBCL;

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController;

	/**
	 * Gets the process type bcl.
	 * 
	 * @return the process type bcl
	 */
	public IProcessTypeBCL getProcessTypeBCL()
	{
		return processTypeBCL;
	}

	/**
	 * Sets the process type bcl.
	 * 
	 * @param processTypeBCL the new process type bcl
	 */
	public void setProcessTypeBCL(IProcessTypeBCL processTypeBCL)
	{
		this.processTypeBCL = processTypeBCL;
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
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessTypeBCF#fetchAllProcessCategory(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllProcessCategory(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<ProcessCategory> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_PROCESS_CATEGORY);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResultsResponse = getProcessTypeBCL().fetchAllProcessCategory(processRequest);
				response.setProcessCategories(internalResultsResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

}
