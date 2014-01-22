package com.sensus.lc.foto.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.foto.bcf.IFotoBCF;
import com.sensus.lc.foto.bcl.IFotoBCL;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;
import com.sensus.lc.foto.model.response.FotoResponse;
import com.sensus.lc.foto.model.response.InquiryFotoResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Foto BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class FotoBCFImpl implements IFotoBCF
{

	/** The Constant DEFAULT_FOTO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_FOTO_BCF_EXCEPTION_MSG = "sensus.dm.elec.fotobcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FotoBCFImpl.class);

	/** The foto bcl. */
	private IFotoBCL fotoBCL; // injected by Spring through setter

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
	 * Sets the foto bcl.
	 * 
	 * @param paramFotoBCL the new foto bcl
	 */
	public void setFotoBCL(IFotoBCL paramFotoBCL)
	{
		fotoBCL = paramFotoBCL;
	}

	/**
	 * Gets the foto bcl.
	 * 
	 * @return the foto bcl
	 */
	public IFotoBCL getFotoBCL()
	{
		return fotoBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.bcf.IFotoBCF#fetchAllFotos(com.sensus.dm.common.foto.model.request.InquiryFotoRequest
	 * )
	 */
	@Override
	public InquiryFotoResponse fetchAllFotos(InquiryFotoRequest inquiryFotoRequest)
	{
		InquiryFotoResponse response = new InquiryFotoResponse();

		try
		{
			InternalResultsResponse<Foto> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryFotoRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryFotoRequest.getSortExpressions());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getFotoBCL().fetchAllFotos(inquiryFotoRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FOTO_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.bcf.IFotoBCF#fetchFotoById(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public FotoResponse fetchFotoById(InquiryFotoRequest request)
	{
		FotoResponse response = new FotoResponse();

		try
		{
			InternalResultsResponse<Foto> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Foto.class.getSimpleName(), request.getFirstFoto());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getFotoBCL().fetchAllFotos(request);
				response.setFotos(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FOTO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.bcf.IFotoBCF#fetchFotoByName(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public FotoResponse fetchFotoByName(InquiryFotoRequest inquiryFotoRequest)
	{
		FotoResponse response = new FotoResponse();
		try
		{
			InternalResultsResponse<Foto> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryFotoRequest);
			context.putObjectToBeValidated(Foto.class.getSimpleName(), inquiryFotoRequest.getFirstFoto());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getFotoBCL().fetchAllFotos(inquiryFotoRequest);
				response.setFotos(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FOTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.bcf.IFotoBCF#insertFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public FotoResponse insertFoto(FotoRequest fotoRequest)
	{
		FotoResponse response = new FotoResponse();
		try
		{
			InternalResultsResponse<Foto> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					fotoRequest);
			context.putObjectToBeValidated(Foto.class.getSimpleName(),
					fotoRequest.getFirstFoto());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getFotoBCL().insertFoto(fotoRequest);
				response.setFotos(internalResponse.getResultsList());
				// response.setProcessId(fotoRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FOTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.bcf.IFotoBCF#updateFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public FotoResponse updateFoto(FotoRequest fotoRequest)
	{
		FotoResponse response = new FotoResponse();
		try
		{
			InternalResultsResponse<Foto> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					fotoRequest);
			context.putObjectToBeValidated(Foto.class.getSimpleName(),
					fotoRequest.getFirstFoto());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getFotoBCL().updateFoto(fotoRequest);
				response.setFotos(internalResponse.getResultsList());
				// response.setProcessId(fotoRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FOTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.bcf.IFotoBCF#deleteFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public FotoResponse deleteFoto(FotoRequest fotoRequest)
	{
		FotoResponse response = new FotoResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					fotoRequest);
			context.putObjectToBeValidated(Foto.class.getSimpleName(),
					fotoRequest.getFirstFoto());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getFotoBCL().deleteFoto(fotoRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FOTO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

}
