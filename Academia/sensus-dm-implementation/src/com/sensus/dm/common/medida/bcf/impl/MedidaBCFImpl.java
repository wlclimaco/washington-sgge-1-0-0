package com.sensus.dm.common.medida.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.medida.bcf.IMedidaBCF;
import com.sensus.dm.common.medida.bcl.IMedidaBCL;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.medida.model.request.InquiryMedidaRequest;
import com.sensus.dm.common.medida.model.request.MedidaRequest;
import com.sensus.dm.common.medida.model.response.InquiryMedidaResponse;
import com.sensus.dm.common.medida.model.response.MedidaResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * Medida BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class MedidaBCFImpl implements IMedidaBCF
{

	/** The Constant DEFAULT_MEDIDA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_MEDIDA_BCF_EXCEPTION_MSG = "sensus.dm.elec.medidabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MedidaBCFImpl.class);

	/** The medida bcl. */
	private IMedidaBCL medidaBCL; // injected by Spring through setter

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
	 * Sets the medida bcl.
	 * 
	 * @param paramMedidaBCL the new medida bcl
	 */
	public void setMedidaBCL(IMedidaBCL paramMedidaBCL)
	{
		medidaBCL = paramMedidaBCL;
	}

	/**
	 * Gets the medida bcl.
	 * 
	 * @return the medida bcl
	 */
	public IMedidaBCL getMedidaBCL()
	{
		return medidaBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcf.IMedidaBCF#fetchAllMedidas(com.sensus.dm.common.medida.model.request.
	 * InquiryMedidaRequest
	 * )
	 */
	@Override
	public InquiryMedidaResponse fetchAllMedidas(InquiryMedidaRequest inquiryMedidaRequest)
	{
		InquiryMedidaResponse response = new InquiryMedidaResponse();

		try
		{
			InternalResultsResponse<Medida> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryMedidaRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryMedidaRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryMedidaRequest);
			context.putObjectToBeValidated(BaseSearch.class.getSimpleName(),
					inquiryMedidaRequest.getDeviceSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getMedidaBCL().fetchAllMedidas(inquiryMedidaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MEDIDA_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcf.IMedidaBCF#fetchMedidaById(com.sensus.dm.common.medida.model.request.MedidaRequest
	 * )
	 */
	@Override
	public MedidaResponse fetchMedidaById(InquiryMedidaRequest request)
	{
		MedidaResponse response = new MedidaResponse();

		try
		{
			InternalResultsResponse<Medida> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Medida.class.getSimpleName(), request.getFirstMedida());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getMedidaBCL().fetchAllMedidas(request);
				response.setMedidas(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MEDIDA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcf.IMedidaBCF#fetchMedidaByName(com.sensus.dm.common.medida.model.request.MedidaRequest
	 * )
	 */
	@Override
	public MedidaResponse fetchMedidaByName(InquiryMedidaRequest inquiryMedidaRequest)
	{
		MedidaResponse response = new MedidaResponse();
		try
		{
			InternalResultsResponse<Medida> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryMedidaRequest);
			context.putObjectToBeValidated(Medida.class.getSimpleName(), inquiryMedidaRequest.getFirstMedida());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getMedidaBCL().fetchAllMedidas(inquiryMedidaRequest);
				response.setMedidas(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MEDIDA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcf.IMedidaBCF#insertMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public MedidaResponse insertMedida(MedidaRequest medidaRequest)
	{
		MedidaResponse response = new MedidaResponse();
		try
		{
			InternalResultsResponse<Medida> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					medidaRequest);
			context.putObjectToBeValidated(Medida.class.getSimpleName(),
					medidaRequest.getFirstMedida());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getMedidaBCL().insertMedida(medidaRequest);
				response.setMedidas(internalResponse.getResultsList());
				response.setProcessId(medidaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MEDIDA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcf.IMedidaBCF#updateMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public MedidaResponse updateMedida(MedidaRequest medidaRequest)
	{
		MedidaResponse response = new MedidaResponse();
		try
		{
			InternalResultsResponse<Medida> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					medidaRequest);
			context.putObjectToBeValidated(Medida.class.getSimpleName(),
					medidaRequest.getFirstMedida());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getMedidaBCL().updateMedida(medidaRequest);
				response.setMedidas(internalResponse.getResultsList());
				response.setProcessId(medidaRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MEDIDA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcf.IMedidaBCF#deleteMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public MedidaResponse deleteMedida(MedidaRequest medidaRequest)
	{
		MedidaResponse response = new MedidaResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					medidaRequest);
			context.putObjectToBeValidated(Medida.class.getSimpleName(),
					medidaRequest.getFirstMedida());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getMedidaBCL().deleteMedida(medidaRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MEDIDA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public DeviceResponse fetchDevicesByMedida(MedidaRequest medidaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
