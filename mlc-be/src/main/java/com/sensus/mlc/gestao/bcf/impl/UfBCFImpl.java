package com.sensus.mlc.gestao.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.gestao.bcf.IUfBCF;
import com.sensus.mlc.gestao.bcl.IUfBCL;
import com.sensus.mlc.gestao.model.Uf;
import com.sensus.mlc.gestao.model.request.InquiryUfRequest;
import com.sensus.mlc.gestao.model.request.UfRequest;
import com.sensus.mlc.gestao.model.response.InquiryUfResponse;
import com.sensus.mlc.gestao.model.response.UfResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class UfBCFImpl.
 *
 * @author - Washington
 */
public class UfBCFImpl extends AbstractBaseBCF implements IUfBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_UF_REQUEST_NAME. */
	private static final String INQUIRY_UF_REQUEST_NAME = InquiryUfRequest.class.getSimpleName();

	/** The Constant UF_REQUEST_NAME. */
	private static final String UF_REQUEST_NAME = UfRequest.class.getSimpleName();

	/** The Constant UF_NAME. */
	private static final String UF_NAME = Uf.class.getSimpleName();

	/** The Constant DEFAULT_UF_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_UF_BCF_EXCEPTION_MSG = "sensus.mlc.ufbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UfBCFImpl.class);

	/** The uf bcl. */
	private IUfBCL ufBCL;

	/** The uf validation controller. */
	private ValidationController ufValidationController;

	/** The uf list validation controller. */
	private ValidationController ufListValidationController;

	/** The uf request validation controller. */
	private ValidationController ufRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the uf bcl.
	 *
	 * @return the uf bcl
	 */
	public IUfBCL getUfBCL()
	{
		return this.ufBCL;
	}

	/**
	 * Sets the uf bcl.
	 *
	 * @param ufBCLObject the new uf bcl
	 */
	public void setUfBCL(IUfBCL ufBCLObject)
	{
		this.ufBCL = ufBCLObject;
	}

	/**
	 * Gets the uf validation controller.
	 *
	 * @return the uf validation controller
	 */
	public ValidationController getUfValidationController()
	{
		return this.ufValidationController;
	}

	/**
	 * Sets the uf validation controller.
	 *
	 * @param ufValidationController the new uf validation controller
	 */
	public void setUfValidationController(ValidationController ufValidationController)
	{
		this.ufValidationController = ufValidationController;
	}

	/**
	 * Gets the uf list validation controller.
	 *
	 * @return the uf list validation controller
	 */
	public ValidationController getUfListValidationController()
	{
		return this.ufListValidationController;
	}

	/**
	 * Sets the uf list validation controller.
	 *
	 * @param ufListValidationController the new uf list validation controller
	 */
	public void setUfListValidationController(ValidationController ufListValidationController)
	{
		this.ufListValidationController = ufListValidationController;
	}

	/**
	 * Gets the uf request validation controller.
	 *
	 * @return the uf request validation controller
	 */
	public ValidationController getUfRequestValidationController()
	{
		return this.ufRequestValidationController;
	}

	/**
	 * Sets the uf request validation controller.
	 *
	 * @param ufRequestValidationController the new uf request validation controller
	 */
	public void setUfRequestValidationController(ValidationController ufRequestValidationController)
	{
		this.ufRequestValidationController = ufRequestValidationController;
	}

	/**
	 * Gets the light validation controller.
	 *
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return this.lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 *
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Gets the light list validation controller.
	 *
	 * @return the light list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return this.lightListValidationController;
	}

	/**
	 * Sets the light list validation controller.
	 *
	 * @param lightListValidationController the new light list validation controller
	 */
	public void setLightListValidationController(ValidationController lightListValidationController)
	{
		this.lightListValidationController = lightListValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcf.IUfBCF#fetchAllUfs(com.sensus.mlc.uf.model.request.InquiryUfRequest
	 */
	@Override
	public InquiryUfResponse fetchAllUf(InquiryUfRequest inquiryUfRequest)
	{
		InquiryUfResponse response = new InquiryUfResponse();
		InternalResultsResponse<Uf> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_UF_REQUEST_NAME, inquiryUfRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getUfBCL().fetchAllUf(inquiryUfRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UF_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcf.IUfBCF#fetchUfByName(com.sensus.mlc.uf.model.request.UfRequest
	 */
	@Override
	public UfResponse fetchUfById(UfRequest ufRequest)
	{
		UfResponse response = new UfResponse();
		InternalResultsResponse<Uf> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(UF_REQUEST_NAME, ufRequest);
			context.putObjectToBeValidated(UF_NAME, ufRequest.getUf());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUfValidationController().validate(context))
			{
				internalResponse = getUfBCL().fetchUfById(ufRequest);
				response.setUf(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UF_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcf.IUfBCF#insertUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public UfResponse insertUf(UfRequest ufRequest)
	{
		UfResponse response = new UfResponse();
		InternalResultsResponse<Uf> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(UF_REQUEST_NAME, ufRequest);
			context.putObjectToBeValidated(UF_NAME, ufRequest.getUf());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUfValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getUfBCL().insertUf(ufRequest);
					response.setUf(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UF_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcf.IUfBCF#deleteUf(com.sensus.mlc.uf.model.request.UfRequest)
	 */
	@Override
	public UfResponse deleteUf(UfRequest ufRequest)
	{
		UfResponse response = new UfResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(UF_REQUEST_NAME, ufRequest);
			context.putObjectToBeValidated(UF_NAME, ufRequest.getUf());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUfValidationController().validate(context))
			{
				internalResponse = getUfBCL().deleteUf(ufRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UF_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.uf.bcf.IUfBCF#updateUf(com.sensus.mlc.uf.model.request.UfRequest
	 */
	@Override
	public UfResponse updateUf(UfRequest ufRequest)
	{
		UfResponse response = new UfResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(UF_REQUEST_NAME, ufRequest);
			context.putObjectToBeValidated(UF_NAME, ufRequest.getUf());

			if (getLightingControlRequestValidationController().validate(context) &&
					getUfRequestValidationController().validate(context) &&
					getUfValidationController().validate(context))
			{
				internalResponse = getUfBCL().updateUf(ufRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UF_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
