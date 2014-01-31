package com.sensus.lc.grupomuscular.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.grupomuscular.bcf.IGrupomuscularBCF;
import com.sensus.lc.grupomuscular.bcl.IGrupomuscularBCL;
import com.sensus.lc.grupomuscular.model.Grupomuscular;
import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.response.GrupomuscularResponse;
import com.sensus.lc.grupomuscular.model.response.InquiryGrupomuscularResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * Grupomuscular BCF implementation.
 * 
 * @author Washington.
 * 
 */
public class GrupomuscularBCFImpl implements IGrupomuscularBCF
{

	/** The Constant DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG =
			"sensus.dm.elec.grupomuscularbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GrupomuscularBCFImpl.class);

	/** The grupomuscular bcl. */
	private IGrupomuscularBCL grupomuscularBCL; // injected by Spring through setter

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
	 * Sets the grupomuscular bcl.
	 * 
	 * @param paramGrupomuscularBCL the new grupomuscular bcl
	 */
	public void setGrupomuscularBCL(IGrupomuscularBCL paramGrupomuscularBCL)
	{
		grupomuscularBCL = paramGrupomuscularBCL;
	}

	/**
	 * Gets the grupomuscular bcl.
	 * 
	 * @return the grupomuscular bcl
	 */
	public IGrupomuscularBCL getGrupomuscularBCL()
	{
		return grupomuscularBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcf.IGrupomuscularBCF#fetchAllGrupomusculars(com.sensus.dm.common.grupomuscular
	 * .model.
	 * request.
	 * InquiryGrupomuscularRequest
	 * )
	 */
	@Override
	public InquiryGrupomuscularResponse fetchAllGrupomusculars(InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		InquiryGrupomuscularResponse response = new InquiryGrupomuscularResponse();

		try
		{
			InternalResultsResponse<Grupomuscular> internalResponse = null;

			ValidationContext context = new ValidationContext();

			internalResponse = getGrupomuscularBCL().fetchAllGrupomusculars(inquiryGrupomuscularRequest);

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcf.IGrupomuscularBCF#fetchGrupomuscularById(com.sensus.dm.common.grupomuscular
	 * .model.
	 * request.
	 * GrupomuscularRequest)
	 */
	@Override
	public GrupomuscularResponse fetchGrupomuscularById(InquiryGrupomuscularRequest request)
	{
		GrupomuscularResponse response = new GrupomuscularResponse();

		try
		{
			InternalResultsResponse<Grupomuscular> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Grupomuscular.class.getSimpleName(), request.getFirstGrupomuscular());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getGrupomuscularBCL().fetchAllGrupomusculars(request);
				response.setGrupomusculars(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcf.IGrupomuscularBCF#fetchGrupomuscularByName(com.sensus.dm.common.grupomuscular
	 * .model.
	 * request.
	 * GrupomuscularRequest)
	 */
	@Override
	public GrupomuscularResponse fetchGrupomuscularByName(InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		GrupomuscularResponse response = new GrupomuscularResponse();
		try
		{
			InternalResultsResponse<Grupomuscular> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryGrupomuscularRequest);
			context.putObjectToBeValidated(Grupomuscular.class.getSimpleName(),
					inquiryGrupomuscularRequest.getFirstGrupomuscular());

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getGrupomuscularBCL().fetchAllGrupomusculars(inquiryGrupomuscularRequest);
				response.setGrupomusculars(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcf.IGrupomuscularBCF#insertGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public GrupomuscularResponse insertGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		GrupomuscularResponse response = new GrupomuscularResponse();
		try
		{
			InternalResultsResponse<Grupomuscular> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					grupomuscularRequest);
			context.putObjectToBeValidated(Grupomuscular.class.getSimpleName(),
					grupomuscularRequest.getFirstGrupomuscular());

			internalResponse = getGrupomuscularBCL().insertGrupomuscular(grupomuscularRequest);
			response.setGrupomusculars(internalResponse.getResultsList());
			// response.setProcessId(grupomuscularRequest.getProcessId());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcf.IGrupomuscularBCF#updateGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public GrupomuscularResponse updateGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		GrupomuscularResponse response = new GrupomuscularResponse();
		try
		{
			InternalResultsResponse<Grupomuscular> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					grupomuscularRequest);
			context.putObjectToBeValidated(Grupomuscular.class.getSimpleName(),
					grupomuscularRequest.getGrupomusculars());

			internalResponse = getGrupomuscularBCL().updateGrupomuscular(grupomuscularRequest);
			response.setGrupomusculars(internalResponse.getResultsList());
			// response.setProcessId(grupomuscularRequest.getProcessId());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcf.IGrupomuscularBCF#deleteGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public GrupomuscularResponse deleteGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		GrupomuscularResponse response = new GrupomuscularResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					grupomuscularRequest);
			context.putObjectToBeValidated(Grupomuscular.class.getSimpleName(),
					grupomuscularRequest.getFirstGrupomuscular());
			//
			// if (getTenantRequestValidationController().validate(context))
			// {
			internalResponse = getGrupomuscularBCL().deleteGrupomuscular(grupomuscularRequest);
			// }

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public InquiryGrupomuscularResponse fetchAllGrupomuscularByUser(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		InquiryGrupomuscularResponse response = new InquiryGrupomuscularResponse();

		try
		{
			InternalResultsResponse<Grupomuscular> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryGrupomuscularRequest);
			context.putObjectToBeValidated(Grupomuscular.class.getSimpleName(),
					inquiryGrupomuscularRequest.getGrupomusculars());

			// if (getTenantRequestValidationController().validate(context))
			// {
			internalResponse = getGrupomuscularBCL().fetchAllGrupomuscularByUser(inquiryGrupomuscularRequest);
			response.setGrupomusculars(internalResponse.getResultsList());
			// }

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GRUPOMUSCULAR_BCF_EXCEPTION_MSG);
		}

		return response;
	}

}
