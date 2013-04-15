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
import com.sensus.mlc.gestao.bcf.IEmbalagensBCF;
import com.sensus.mlc.gestao.bcl.IEmbalagensBCL;
import com.sensus.mlc.gestao.model.Embalagens;
import com.sensus.mlc.gestao.model.request.EmbalagensRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmbalagensRequest;
import com.sensus.mlc.gestao.model.response.EmbalagensResponse;
import com.sensus.mlc.gestao.model.response.InquiryEmbalagensResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class EmbalagensBCFImpl.
 *
 * @author - Washington
 */
public class EmbalagensBCFImpl extends AbstractBaseBCF implements IEmbalagensBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_EMBALAGENS_REQUEST_NAME. */
	private static final String INQUIRY_EMBALAGENS_REQUEST_NAME = InquiryEmbalagensRequest.class.getSimpleName();

	/** The Constant EMBALAGENS_REQUEST_NAME. */
	private static final String EMBALAGENS_REQUEST_NAME = EmbalagensRequest.class.getSimpleName();

	/** The Constant EMBALAGENS_NAME. */
	private static final String EMBALAGENS_NAME = Embalagens.class.getSimpleName();

	/** The Constant DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG = "sensus.mlc.embalagensbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmbalagensBCFImpl.class);

	/** The embalagens bcl. */
	private IEmbalagensBCL embalagensBCL;

	/** The embalagens validation controller. */
	private ValidationController embalagensValidationController;

	/** The embalagens list validation controller. */
	private ValidationController embalagensListValidationController;

	/** The embalagens request validation controller. */
	private ValidationController embalagensRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the embalagens bcl.
	 *
	 * @return the embalagens bcl
	 */
	public IEmbalagensBCL getEmbalagensBCL()
	{
		return this.embalagensBCL;
	}

	/**
	 * Sets the embalagens bcl.
	 *
	 * @param embalagensBCLObject the new embalagens bcl
	 */
	public void setEmbalagensBCL(IEmbalagensBCL embalagensBCLObject)
	{
		this.embalagensBCL = embalagensBCLObject;
	}

	/**
	 * Gets the embalagens validation controller.
	 *
	 * @return the embalagens validation controller
	 */
	public ValidationController getEmbalagensValidationController()
	{
		return this.embalagensValidationController;
	}

	/**
	 * Sets the embalagens validation controller.
	 *
	 * @param embalagensValidationController the new embalagens validation controller
	 */
	public void setEmbalagensValidationController(ValidationController embalagensValidationController)
	{
		this.embalagensValidationController = embalagensValidationController;
	}

	/**
	 * Gets the embalagens list validation controller.
	 *
	 * @return the embalagens list validation controller
	 */
	public ValidationController getEmbalagensListValidationController()
	{
		return this.embalagensListValidationController;
	}

	/**
	 * Sets the embalagens list validation controller.
	 *
	 * @param embalagensListValidationController the new embalagens list validation controller
	 */
	public void setEmbalagensListValidationController(ValidationController embalagensListValidationController)
	{
		this.embalagensListValidationController = embalagensListValidationController;
	}

	/**
	 * Gets the embalagens request validation controller.
	 *
	 * @return the embalagens request validation controller
	 */
	public ValidationController getEmbalagensRequestValidationController()
	{
		return this.embalagensRequestValidationController;
	}

	/**
	 * Sets the embalagens request validation controller.
	 *
	 * @param embalagensRequestValidationController the new embalagens request validation controller
	 */
	public void setEmbalagensRequestValidationController(ValidationController embalagensRequestValidationController)
	{
		this.embalagensRequestValidationController = embalagensRequestValidationController;
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
	 * @see com.sensus.mlc.embalagens.bcf.IEmbalagensBCF#fetchAllEmbalagenss(com.sensus.mlc.embalagens.model.request.InquiryEmbalagensRequest
	 */
	@Override
	public InquiryEmbalagensResponse fetchAllEmbalagens(InquiryEmbalagensRequest inquiryEmbalagensRequest)
	{
		InquiryEmbalagensResponse response = new InquiryEmbalagensResponse();
		InternalResultsResponse<Embalagens> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_EMBALAGENS_REQUEST_NAME, inquiryEmbalagensRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getEmbalagensBCL().fetchAllEmbalagens(inquiryEmbalagensRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcf.IEmbalagensBCF#fetchEmbalagensByName(com.sensus.mlc.embalagens.model.request.EmbalagensRequest
	 */
	@Override
	public EmbalagensResponse fetchEmbalagensById(EmbalagensRequest embalagensRequest)
	{
		EmbalagensResponse response = new EmbalagensResponse();
		InternalResultsResponse<Embalagens> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(EMBALAGENS_REQUEST_NAME, embalagensRequest);
			context.putObjectToBeValidated(EMBALAGENS_NAME, embalagensRequest.getEmbalagens());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEmbalagensValidationController().validate(context))
			{
				internalResponse = getEmbalagensBCL().fetchEmbalagensById(embalagensRequest);
				response.setEmbalagens(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcf.IEmbalagensBCF#insertEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public EmbalagensResponse insertEmbalagens(EmbalagensRequest embalagensRequest)
	{
		EmbalagensResponse response = new EmbalagensResponse();
		InternalResultsResponse<Embalagens> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(EMBALAGENS_REQUEST_NAME, embalagensRequest);
			context.putObjectToBeValidated(EMBALAGENS_NAME, embalagensRequest.getEmbalagens());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEmbalagensValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getEmbalagensBCL().insertEmbalagens(embalagensRequest);
					response.setEmbalagens(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcf.IEmbalagensBCF#deleteEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest)
	 */
	@Override
	public EmbalagensResponse deleteEmbalagens(EmbalagensRequest embalagensRequest)
	{
		EmbalagensResponse response = new EmbalagensResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(EMBALAGENS_REQUEST_NAME, embalagensRequest);
			context.putObjectToBeValidated(EMBALAGENS_NAME, embalagensRequest.getEmbalagens());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEmbalagensValidationController().validate(context))
			{
				internalResponse = getEmbalagensBCL().deleteEmbalagens(embalagensRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.embalagens.bcf.IEmbalagensBCF#updateEmbalagens(com.sensus.mlc.embalagens.model.request.EmbalagensRequest
	 */
	@Override
	public EmbalagensResponse updateEmbalagens(EmbalagensRequest embalagensRequest)
	{
		EmbalagensResponse response = new EmbalagensResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(EMBALAGENS_REQUEST_NAME, embalagensRequest);
			context.putObjectToBeValidated(EMBALAGENS_NAME, embalagensRequest.getEmbalagens());

			if (getLightingControlRequestValidationController().validate(context) &&
					getEmbalagensRequestValidationController().validate(context) &&
					getEmbalagensValidationController().validate(context))
			{
				internalResponse = getEmbalagensBCL().updateEmbalagens(embalagensRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMBALAGENS_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
