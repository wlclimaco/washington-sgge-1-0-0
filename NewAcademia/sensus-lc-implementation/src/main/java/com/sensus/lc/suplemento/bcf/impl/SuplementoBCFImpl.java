package com.sensus.lc.suplemento.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.suplemento.bcf.ISuplementoBCF;
import com.sensus.lc.suplemento.bcl.ISuplementoBCL;
import com.sensus.lc.suplemento.model.Suplemento;
import com.sensus.lc.suplemento.model.request.InquirySuplementoRequest;
import com.sensus.lc.suplemento.model.request.SuplementoRequest;
import com.sensus.lc.suplemento.model.response.InquirySuplementoResponse;
import com.sensus.lc.suplemento.model.response.SuplementoResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Suplemento BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class SuplementoBCFImpl implements ISuplementoBCF
{

	/** The Constant DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG =
			"sensus.dm.elec.suplementobcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SuplementoBCFImpl.class);

	/** The suplemento bcl. */
	private ISuplementoBCL suplementoBCL; // injected by Spring through setter

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
	 * Sets the suplemento bcl.
	 * 
	 * @param paramSuplementoBCL the new suplemento bcl
	 */
	public void setSuplementoBCL(ISuplementoBCL paramSuplementoBCL)
	{
		suplementoBCL = paramSuplementoBCL;
	}

	/**
	 * Gets the suplemento bcl.
	 * 
	 * @return the suplemento bcl
	 */
	public ISuplementoBCL getSuplementoBCL()
	{
		return suplementoBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcf.ISuplementoBCF#fetchAllSuplementos(com.sensus.dm.common.suplemento.model.request
	 * .InquirySuplementoRequest
	 * )
	 */
	@Override
	public InquirySuplementoResponse fetchAllSuplementos(InquirySuplementoRequest inquirySuplementoRequest)
	{
		InquirySuplementoResponse response = new InquirySuplementoResponse();

		try
		{
			InternalResultsResponse<Suplemento> internalResponse = null;

			ValidationContext context = new ValidationContext();

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquirySuplementoRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquirySuplementoRequest.getSortExpressions());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getSuplementoBCL().fetchAllSuplementos(inquirySuplementoRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcf.ISuplementoBCF#fetchSuplementoById(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public SuplementoResponse fetchSuplementoById(InquirySuplementoRequest request)
	{
		SuplementoResponse response = new SuplementoResponse();

		try
		{
			InternalResultsResponse<Suplemento> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Suplemento.class.getSimpleName(), request.getFirstSuplemento());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getSuplementoBCL().fetchAllSuplementos(request);
				response.setSuplementos(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcf.ISuplementoBCF#fetchSuplementoByName(com.sensus.dm.common.suplemento.model.
	 * request.SuplementoRequest)
	 */
	@Override
	public SuplementoResponse fetchSuplementoByName(InquirySuplementoRequest inquirySuplementoRequest)
	{
		SuplementoResponse response = new SuplementoResponse();
		try
		{
			InternalResultsResponse<Suplemento> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquirySuplementoRequest);
			context.putObjectToBeValidated(Suplemento.class.getSimpleName(),
					inquirySuplementoRequest.getFirstSuplemento());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getSuplementoBCL().fetchAllSuplementos(inquirySuplementoRequest);
				response.setSuplementos(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcf.ISuplementoBCF#insertSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public SuplementoResponse insertSuplemento(SuplementoRequest suplementoRequest)
	{
		SuplementoResponse response = new SuplementoResponse();
		try
		{
			InternalResultsResponse<Suplemento> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					suplementoRequest);
			context.putObjectToBeValidated(Suplemento.class.getSimpleName(),
					suplementoRequest.getFirstSuplemento());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getSuplementoBCL().insertSuplemento(suplementoRequest);
				response.setSuplementos(internalResponse.getResultsList());
				// response.setProcessId(suplementoRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcf.ISuplementoBCF#updateSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public SuplementoResponse updateSuplemento(SuplementoRequest suplementoRequest)
	{
		SuplementoResponse response = new SuplementoResponse();
		try
		{
			InternalResultsResponse<Suplemento> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					suplementoRequest);
			context.putObjectToBeValidated(Suplemento.class.getSimpleName(),
					suplementoRequest.getFirstSuplemento());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getSuplementoBCL().updateSuplemento(suplementoRequest);
				response.setSuplementos(internalResponse.getResultsList());
				// response.setProcessId(suplementoRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcf.ISuplementoBCF#deleteSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public SuplementoResponse deleteSuplemento(SuplementoRequest suplementoRequest)
	{
		SuplementoResponse response = new SuplementoResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					suplementoRequest);
			context.putObjectToBeValidated(Suplemento.class.getSimpleName(),
					suplementoRequest.getFirstSuplemento());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getSuplementoBCL().deleteSuplemento(suplementoRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SUPLEMENTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
