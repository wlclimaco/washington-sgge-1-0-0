package com.sensus.dm.common.academia.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.academia.bcf.IAcademiaBCF;
import com.sensus.dm.common.academia.bcl.IAcademiaBCL;
import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.academia.model.request.AcademiaRequest;
import com.sensus.dm.common.academia.model.request.InquiryAcademiaRequest;
import com.sensus.dm.common.academia.model.response.AcademiaResponse;
import com.sensus.dm.common.academia.model.response.InquiryAcademiaResponse;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * Academia BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class AcademiaBCFImpl implements IAcademiaBCF
{

	/** The Constant DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG = "sensus.dm.elec.academiabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AcademiaBCFImpl.class);

	/** The academia bcl. */
	private IAcademiaBCL academiaBCL; // injected by Spring through setter

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
	 * Sets the academia bcl.
	 * 
	 * @param paramAcademiaBCL the new academia bcl
	 */
	public void setAcademiaBCL(IAcademiaBCL paramAcademiaBCL)
	{
		academiaBCL = paramAcademiaBCL;
	}

	/**
	 * Gets the academia bcl.
	 * 
	 * @return the academia bcl
	 */
	public IAcademiaBCL getAcademiaBCL()
	{
		return academiaBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcf.IAcademiaBCF#fetchAllAcademias(com.sensus.dm.common.academia.model.request.
	 * InquiryAcademiaRequest
	 * )
	 */
	@Override
	public InquiryAcademiaResponse fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		InquiryAcademiaResponse response = new InquiryAcademiaResponse();

		try
		{
			InternalResultsResponse<Academia> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_GROUPS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryAcademiaRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryAcademiaRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryAcademiaRequest);
			context.putObjectToBeValidated(BaseSearch.class.getSimpleName(),
					inquiryAcademiaRequest.getDeviceSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getAcademiaBCL().fetchAllAcademias(inquiryAcademiaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcf.IAcademiaBCF#fetchAcademiaById(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public AcademiaResponse fetchAcademiaById(InquiryAcademiaRequest request)
	{
		AcademiaResponse response = new AcademiaResponse();

		try
		{
			InternalResultsResponse<Academia> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Academia.class.getSimpleName(), request.getFirstAcademia());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getAcademiaBCL().fetchAllAcademias(request);
				response.setAcademias(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcf.IAcademiaBCF#fetchAcademiaByName(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public AcademiaResponse fetchAcademiaByName(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		AcademiaResponse response = new AcademiaResponse();
		try
		{
			InternalResultsResponse<Academia> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryAcademiaRequest);
			context.putObjectToBeValidated(Academia.class.getSimpleName(), inquiryAcademiaRequest.getFirstAcademia());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getAcademiaBCL().fetchAllAcademias(inquiryAcademiaRequest);
				response.setAcademias(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcf.IAcademiaBCF#insertAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public AcademiaResponse insertAcademia(AcademiaRequest academiaRequest)
	{
		AcademiaResponse response = new AcademiaResponse();
		try
		{
			InternalResultsResponse<Academia> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_GROUP);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					academiaRequest);
			context.putObjectToBeValidated(Academia.class.getSimpleName(),
					academiaRequest.getFirstAcademia());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getAcademiaBCL().insertAcademia(academiaRequest);
				response.setAcademias(internalResponse.getResultsList());
				response.setProcessId(academiaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcf.IAcademiaBCF#updateAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public AcademiaResponse updateAcademia(AcademiaRequest academiaRequest)
	{
		AcademiaResponse response = new AcademiaResponse();
		try
		{
			InternalResultsResponse<Academia> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					academiaRequest);
			context.putObjectToBeValidated(Academia.class.getSimpleName(),
					academiaRequest.getFirstAcademia());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getAcademiaBCL().updateAcademia(academiaRequest);
				response.setAcademias(internalResponse.getResultsList());
				response.setProcessId(academiaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcf.IAcademiaBCF#deleteAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public AcademiaResponse deleteAcademia(AcademiaRequest academiaRequest)
	{
		AcademiaResponse response = new AcademiaResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					academiaRequest);
			context.putObjectToBeValidated(Academia.class.getSimpleName(),
					academiaRequest.getFirstAcademia());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getAcademiaBCL().deleteAcademia(academiaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ACADEMIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public DeviceResponse fetchDevicesByAcademia(AcademiaRequest academiaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
