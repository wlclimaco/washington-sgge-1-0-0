package com.sensus.lc.dieta.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.dieta.bcf.IDietaBCF;
import com.sensus.lc.dieta.bcl.IDietaBCL;
import com.sensus.lc.dieta.model.Dieta;
import com.sensus.lc.dieta.model.request.DietaRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaRequest;
import com.sensus.lc.dieta.model.response.DietaResponse;
import com.sensus.lc.dieta.model.response.InquiryDietaResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Dieta BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class DietaBCFImpl implements IDietaBCF
{

	/** The Constant DEFAULT_DIETA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DIETA_BCF_EXCEPTION_MSG = "sensus.dm.elec.dietabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DietaBCFImpl.class);

	/** The dieta bcl. */
	private IDietaBCL dietaBCL; // injected by Spring through setter

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
	 * Sets the dieta bcl.
	 * 
	 * @param paramDietaBCL the new dieta bcl
	 */
	public void setDietaBCL(IDietaBCL paramDietaBCL)
	{
		dietaBCL = paramDietaBCL;
	}

	/**
	 * Gets the dieta bcl.
	 * 
	 * @return the dieta bcl
	 */
	public IDietaBCL getDietaBCL()
	{
		return dietaBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dieta.bcf.IDietaBCF#fetchAllDietas(com.sensus.dm.common.dieta.model.request.InquiryDietaRequest
	 * )
	 */
	@Override
	public InquiryDietaResponse fetchAllDietas(InquiryDietaRequest inquiryDietaRequest)
	{
		InquiryDietaResponse response = new InquiryDietaResponse();

		try
		{
			InternalResultsResponse<Dieta> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryDietaRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDietaRequest.getSortExpressions());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getDietaBCL().fetchAllDietas(inquiryDietaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETA_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dieta.bcf.IDietaBCF#fetchDietaById(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public DietaResponse fetchDietaById(InquiryDietaRequest request)
	{
		DietaResponse response = new DietaResponse();

		try
		{
			InternalResultsResponse<Dieta> internalResponse = null;

			ValidationContext context = new ValidationContext();

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Dieta.class.getSimpleName(), request.getFirstDieta());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaBCL().fetchAllDietas(request);
				response.setDietas(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dieta.bcf.IDietaBCF#fetchDietaByName(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public DietaResponse fetchDietaByName(InquiryDietaRequest inquiryDietaRequest)
	{
		DietaResponse response = new DietaResponse();
		try
		{
			InternalResultsResponse<Dieta> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDietaRequest);
			context.putObjectToBeValidated(Dieta.class.getSimpleName(), inquiryDietaRequest.getFirstDieta());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaBCL().fetchAllDietas(inquiryDietaRequest);
				response.setDietas(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.bcf.IDietaBCF#insertDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public DietaResponse insertDieta(DietaRequest dietaRequest)
	{
		DietaResponse response = new DietaResponse();
		try
		{
			InternalResultsResponse<Dieta> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					dietaRequest);
			context.putObjectToBeValidated(Dieta.class.getSimpleName(),
					dietaRequest.getFirstDieta());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaBCL().insertDieta(dietaRequest);
				response.setDietas(internalResponse.getResultsList());
				// response.setProcessId(dietaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.bcf.IDietaBCF#updateDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public DietaResponse updateDieta(DietaRequest dietaRequest)
	{
		DietaResponse response = new DietaResponse();
		try
		{
			InternalResultsResponse<Dieta> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					dietaRequest);
			context.putObjectToBeValidated(Dieta.class.getSimpleName(),
					dietaRequest.getFirstDieta());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaBCL().updateDieta(dietaRequest);
				response.setDietas(internalResponse.getResultsList());
				// response.setProcessId(dietaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.bcf.IDietaBCF#deleteDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public DietaResponse deleteDieta(DietaRequest dietaRequest)
	{
		DietaResponse response = new DietaResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					dietaRequest);
			context.putObjectToBeValidated(Dieta.class.getSimpleName(),
					dietaRequest.getFirstDieta());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDietaBCL().deleteDieta(dietaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DIETA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
