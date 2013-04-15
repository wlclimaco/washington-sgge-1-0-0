package com.sensus.mlc.tabela.bcf.impl;

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
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tabela.bcf.IAtributosBCF;
import com.sensus.mlc.tabela.bcl.IAtributosBCL;
import com.sensus.mlc.tabela.model.Atributos;
import com.sensus.mlc.tabela.model.request.AtributosRequest;
import com.sensus.mlc.tabela.model.request.InquiryAtributosRequest;
import com.sensus.mlc.tabela.model.response.AtributosResponse;
import com.sensus.mlc.tabela.model.response.InquiryAtributosResponse;




/**
 * The Class AtributosBCFImpl.
 *
 * @author - Washington
 */
public class AtributosBCFImpl extends AbstractBaseBCF implements IAtributosBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_ATRIBUTOS_REQUEST_NAME. */
	private static final String INQUIRY_ATRIBUTOS_REQUEST_NAME = InquiryAtributosRequest.class.getSimpleName();

	/** The Constant ATRIBUTOS_REQUEST_NAME. */
	private static final String ATRIBUTOS_REQUEST_NAME = AtributosRequest.class.getSimpleName() class.getSimpleName();

	/** The Constant ATRIBUTOS_NAME. */
	private static final String ATRIBUTOS_NAME = Atributos.class.getSimpleName();

	/** The Constant DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG = "sensus.mlc.atributosbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AtributosBCFImpl.class);

	/** The atributos bcl. */
	private IAtributosBCL atributosBCL;

	/** The atributos validation controller. */
	private ValidationController atributosValidationController;

	/** The atributos list validation controller. */
	private ValidationController atributosListValidationController;

	/** The atributos request validation controller. */
	private ValidationController atributosRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the atributos bcl.
	 *
	 * @return the atributos bcl
	 */
	public IAtributosBCL getAtributosBCL()
	{
		return this.atributosBCL;
	}

	/**
	 * Sets the atributos bcl.
	 *
	 * @param atributosBCLObject the new atributos bcl
	 */
	public void setAtributosBCL(IAtributosBCL atributosBCLObject)
	{
		this.atributosBCL = atributosBCLObject;
	}

	/**
	 * Gets the atributos validation controller.
	 *
	 * @return the atributos validation controller
	 */
	public ValidationController getAtributosValidationController()
	{
		return this.atributosValidationController;
	}

	/**
	 * Sets the atributos validation controller.
	 *
	 * @param atributosValidationController the new atributos validation controller
	 */
	public void setAtributosValidationController(ValidationController atributosValidationController)
	{
		this.atributosValidationController = atributosValidationController;
	}

	/**
	 * Gets the atributos list validation controller.
	 *
	 * @return the atributos list validation controller
	 */
	public ValidationController getAtributosListValidationController()
	{
		return this.atributosListValidationController;
	}

	/**
	 * Sets the atributos list validation controller.
	 *
	 * @param atributosListValidationController the new atributos list validation controller
	 */
	public void setAtributosListValidationController(ValidationController atributosListValidationController)
	{
		this.atributosListValidationController = atributosListValidationController;
	}

	/**
	 * Gets the atributos request validation controller.
	 *
	 * @return the atributos request validation controller
	 */
	public ValidationController getAtributosRequestValidationController()
	{
		return this.atributosRequestValidationController;
	}

	/**
	 * Sets the atributos request validation controller.
	 *
	 * @param atributosRequestValidationController the new atributos request validation controller
	 */
	public void setAtributosRequestValidationController(ValidationController atributosRequestValidationController)
	{
		this.atributosRequestValidationController = atributosRequestValidationController;
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
	 * @see com.sensus.mlc.atributos.bcf.IAtributosBCF#fetchAllAtributoss(com.sensus.mlc.atributos.model.request.InquiryAtributosRequest
	 */
	@Override
	public InquiryAtributosResponse fetchAllAtributos(InquiryAtributosRequest inquiryAtributosRequest)
	{
		InquiryAtributosResponse response = new InquiryAtributosResponse();
		InternalResultsResponse<Atributos> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_ATRIBUTOS_REQUEST_NAME, inquiryAtributosRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getAtributosBCL().fetchAllAtributos(inquiryAtributosRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.bcf.IAtributosBCF#fetchAtributosByName(com.sensus.mlc.atributos.model.request.AtributosRequest
	 */
	@Override
	public AtributosResponse fetchAtributosById(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		InternalResultsResponse<Atributos> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(ATRIBUTOS_REQUEST_NAME, atributosRequest);
			context.putObjectToBeValidated(ATRIBUTOS_NAME, atributosRequest.getAtributos());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAtributosValidationController().validate(context))
			{
				internalResponse = getAtributosBCL().fetchAtributosByName(atributosRequest);
				response.setAtributos(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.bcf.IAtributosBCF#insertAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */
	@Override
	public AtributosResponse insertAtributos(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		InternalResultsResponse<Atributos> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(ATRIBUTOS_REQUEST_NAME, atributosRequest);
			context.putObjectToBeValidated(ATRIBUTOS_NAME, atributosRequest.getAtributos());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAtributosValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getAtributosBCL().insertAtributos(atributosRequest);
					response.setAtributos(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.bcf.IAtributosBCF#deleteAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest)
	 */
	@Override
	public AtributosResponse deleteAtributos(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(ATRIBUTOS_REQUEST_NAME, atributosRequest);
			context.putObjectToBeValidated(ATRIBUTOS_NAME, atributosRequest.getAtributos());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAtributosValidationController().validate(context))
			{
				internalResponse = getAtributosBCL().deleteAtributos(atributosRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.atributos.bcf.IAtributosBCF#updateAtributos(com.sensus.mlc.atributos.model.request.AtributosRequest
	 */
	@Override
	public AtributosResponse updateAtributos(AtributosRequest atributosRequest)
	{
		AtributosResponse response = new AtributosResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(ATRIBUTOS_REQUEST_NAME, atributosRequest);
			context.putObjectToBeValidated(ATRIBUTOS_NAME, atributosRequest.getAtributos());

			if (getLightingControlRequestValidationController().validate(context) &&
					getAtributosRequestValidationController().validate(context) &&
					getAtributosValidationController().validate(context))
			{
				internalResponse = getAtributosBCL().updateAtributos(atributosRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ATRIBUTOS_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
