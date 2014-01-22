package com.sensus.lc.dieta.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.dieta.bcf.IDietaitensBCF;
import com.sensus.lc.dieta.bcl.IDietaitensBCL;
import com.sensus.lc.dieta.model.Dietaitens;
import com.sensus.lc.dieta.model.request.DietaitensRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaitensRequest;
import com.sensus.lc.dieta.model.response.DietaitensResponse;
import com.sensus.lc.dieta.model.response.InquiryDietaitensResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Dietaitens BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class DietaitensBCFImpl implements IDietaitensBCF
{

	/** The Constant DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG =
			"sensus.dm.elec.dietaitensbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DietaitensBCFImpl.class);

	/** The dietaitens bcl. */
	private IDietaitensBCL dietaitensBCL; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The order by validation controller. */
	private ValidationController orderByValidationController; // injected by Spring through setter

	/** The page size validation controller. */
	private ValidationController pageSizeValidationController; // injected by Spring through setter

	/** The base search validation controller. */
	private ValidationController baseSearchValidationController; // injected by Spring through setter

	/** The device search validation controller. */
	private ValidationController deviceSearchValidationController; // injected by Spring through setter

	/** The inquiry device request validation controller. */
	private ValidationController inquiryDeviceRequestValidationController; // injected by Spring through setter

	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	public ValidationController getOrderByValidationController()
	{
		return orderByValidationController;
	}

	public void setOrderByValidationController(ValidationController orderByValidationController)
	{
		this.orderByValidationController = orderByValidationController;
	}

	public ValidationController getPageSizeValidationController()
	{
		return pageSizeValidationController;
	}

	public void setPageSizeValidationController(ValidationController pageSizeValidationController)
	{
		this.pageSizeValidationController = pageSizeValidationController;
	}

	public ValidationController getBaseSearchValidationController()
	{
		return baseSearchValidationController;
	}

	public void setBaseSearchValidationController(ValidationController baseSearchValidationController)
	{
		this.baseSearchValidationController = baseSearchValidationController;
	}

	/**
	 * Gets the device search validation controller.
	 * 
	 * @return the device search validation controller
	 */
	public ValidationController getDeviceSearchValidationController()
	{
		return deviceSearchValidationController;
	}

	/**
	 * Sets the device search validation controller.
	 * 
	 * @param deviceSearchValidationController the new device search validation controller
	 */
	public void setDeviceSearchValidationController(ValidationController deviceSearchValidationController)
	{
		this.deviceSearchValidationController = deviceSearchValidationController;
	}

	/**
	 * Gets the inquiry device request validation controller.
	 * 
	 * @return the inquiry device request validation controller
	 */
	public ValidationController getInquiryDeviceRequestValidationController()
	{
		return inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the inquiry device request validation controller.
	 * 
	 * @param inquiryDeviceRequestValidationController the new inquiry device request validation controller
	 */
	public void setInquiryDeviceRequestValidationController(
			ValidationController inquiryDeviceRequestValidationController)
	{
		this.inquiryDeviceRequestValidationController = inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the dietaitens bcl.
	 * 
	 * @param paramDietaitensBCL the new dietaitens bcl
	 */
	public void setDietaitensBCL(IDietaitensBCL paramDietaitensBCL)
	{
		dietaitensBCL = paramDietaitensBCL;
	}

	/**
	 * Gets the dietaitens bcl.
	 * 
	 * @return the dietaitens bcl
	 */
	public IDietaitensBCL getDietaitensBCL()
	{
		return dietaitensBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcf.IDietaitensBCF#fetchDietaitensById(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public DietaitensResponse fetchDietaitensById(InquiryDietaitensRequest request)
	{
		DietaitensResponse response = new DietaitensResponse();

		try
		{
			InternalResultsResponse<Dietaitens> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Dietaitens.class.getSimpleName(), request.getFirstDietaitens());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaitensBCL().fetchAllDietaitenss(request);
				response.setDietaitenss(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcf.IDietaitensBCF#fetchDietaitensByName(com.sensus.dm.common.dietaitens.model.
	 * request.DietaitensRequest)
	 */
	@Override
	public InquiryDietaitensResponse fetchDietaitensByName(InquiryDietaitensRequest inquiryDietaitensRequest)
	{
		InquiryDietaitensResponse response = new InquiryDietaitensResponse();
		try
		{
			InternalResultsResponse<Dietaitens> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDietaitensRequest);
			context.putObjectToBeValidated(Dietaitens.class.getSimpleName(),
					inquiryDietaitensRequest.getFirstDietaitens());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaitensBCL().fetchAllDietaitenss(inquiryDietaitensRequest);
				response.setDietaitenss(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcf.IDietaitensBCF#insertDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public DietaitensResponse insertDietaitens(DietaitensRequest dietaitensRequest)
	{
		DietaitensResponse response = new DietaitensResponse();
		try
		{
			InternalResultsResponse<Dietaitens> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					dietaitensRequest);
			context.putObjectToBeValidated(Dietaitens.class.getSimpleName(),
					dietaitensRequest.getFirstDietaitens());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaitensBCL().insertDietaitens(dietaitensRequest);
				response.setDietaitenss(internalResponse.getResultsList());
				// response.setProcessId(dietaitensRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcf.IDietaitensBCF#updateDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public DietaitensResponse updateDietaitens(DietaitensRequest dietaitensRequest)
	{
		DietaitensResponse response = new DietaitensResponse();
		try
		{
			InternalResultsResponse<Dietaitens> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					dietaitensRequest);
			context.putObjectToBeValidated(Dietaitens.class.getSimpleName(),
					dietaitensRequest.getFirstDietaitens());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaitensBCL().updateDietaitens(dietaitensRequest);
				response.setDietaitenss(internalResponse.getResultsList());
				// response.setProcessId(dietaitensRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcf.IDietaitensBCF#deleteDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public DietaitensResponse deleteDietaitens(DietaitensRequest dietaitensRequest)
	{
		DietaitensResponse response = new DietaitensResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					dietaitensRequest);
			context.putObjectToBeValidated(Dietaitens.class.getSimpleName(),
					dietaitensRequest.getFirstDietaitens());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaitensBCL().deleteDietaitens(dietaitensRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryDietaitensResponse fetchAllDietaitens(InquiryDietaitensRequest inquiryDietaitensRequest)
	{
		InquiryDietaitensResponse response = new InquiryDietaitensResponse();
		try
		{
			InternalResultsResponse<Dietaitens> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDietaitensRequest);
			context.putObjectToBeValidated(Dietaitens.class.getSimpleName(),
					inquiryDietaitensRequest.getFirstDietaitens());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaitensBCL().fetchAllDietaitenss(inquiryDietaitensRequest);
				response.setDietaitenss(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETAITENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
