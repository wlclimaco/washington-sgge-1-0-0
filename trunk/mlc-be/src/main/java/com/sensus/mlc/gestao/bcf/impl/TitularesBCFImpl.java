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
import com.sensus.mlc.gestao.bcf.ITitularesBCF;
import com.sensus.mlc.gestao.bcl.ITitularesBCL;
import com.sensus.mlc.gestao.model.Titulares;
import com.sensus.mlc.gestao.model.request.InquiryTitularesRequest;
import com.sensus.mlc.gestao.model.request.TitularesRequest;
import com.sensus.mlc.gestao.model.response.InquiryTitularesResponse;
import com.sensus.mlc.gestao.model.response.TitularesResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class TitularesBCFImpl.
 *
 * @author - Washington
 */
public class TitularesBCFImpl extends AbstractBaseBCF implements ITitularesBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_TITULARES_REQUEST_NAME. */
	private static final String INQUIRY_TITULARES_REQUEST_NAME = InquiryTitularesRequest.class.getSimpleName();

	/** The Constant TITULARES_REQUEST_NAME. */
	private static final String TITULARES_REQUEST_NAME = TitularesRequest.class.getSimpleName();

	/** The Constant TITULARES_NAME. */
	private static final String TITULARES_NAME = Titulares.class.getSimpleName();

	/** The Constant DEFAULT_TITULARES_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TITULARES_BCF_EXCEPTION_MSG = "sensus.mlc.titularesbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TitularesBCFImpl.class);

	/** The titulares bcl. */
	private ITitularesBCL titularesBCL;

	/** The titulares validation controller. */
	private ValidationController titularesValidationController;

	/** The titulares list validation controller. */
	private ValidationController titularesListValidationController;

	/** The titulares request validation controller. */
	private ValidationController titularesRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the titulares bcl.
	 *
	 * @return the titulares bcl
	 */
	public ITitularesBCL getTitularesBCL()
	{
		return this.titularesBCL;
	}

	/**
	 * Sets the titulares bcl.
	 *
	 * @param titularesBCLObject the new titulares bcl
	 */
	public void setTitularesBCL(ITitularesBCL titularesBCLObject)
	{
		this.titularesBCL = titularesBCLObject;
	}

	/**
	 * Gets the titulares validation controller.
	 *
	 * @return the titulares validation controller
	 */
	public ValidationController getTitularesValidationController()
	{
		return this.titularesValidationController;
	}

	/**
	 * Sets the titulares validation controller.
	 *
	 * @param titularesValidationController the new titulares validation controller
	 */
	public void setTitularesValidationController(ValidationController titularesValidationController)
	{
		this.titularesValidationController = titularesValidationController;
	}

	/**
	 * Gets the titulares list validation controller.
	 *
	 * @return the titulares list validation controller
	 */
	public ValidationController getTitularesListValidationController()
	{
		return this.titularesListValidationController;
	}

	/**
	 * Sets the titulares list validation controller.
	 *
	 * @param titularesListValidationController the new titulares list validation controller
	 */
	public void setTitularesListValidationController(ValidationController titularesListValidationController)
	{
		this.titularesListValidationController = titularesListValidationController;
	}

	/**
	 * Gets the titulares request validation controller.
	 *
	 * @return the titulares request validation controller
	 */
	public ValidationController getTitularesRequestValidationController()
	{
		return this.titularesRequestValidationController;
	}

	/**
	 * Sets the titulares request validation controller.
	 *
	 * @param titularesRequestValidationController the new titulares request validation controller
	 */
	public void setTitularesRequestValidationController(ValidationController titularesRequestValidationController)
	{
		this.titularesRequestValidationController = titularesRequestValidationController;
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
	 * @see com.sensus.mlc.titulares.bcf.ITitularesBCF#fetchAllTitularess(com.sensus.mlc.titulares.model.request.InquiryTitularesRequest
	 */
	@Override
	public InquiryTitularesResponse fetchAllTitulares(InquiryTitularesRequest inquiryTitularesRequest)
	{
		InquiryTitularesResponse response = new InquiryTitularesResponse();
		InternalResultsResponse<Titulares> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_TITULARES_REQUEST_NAME, inquiryTitularesRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getTitularesBCL().fetchAllTitulares(inquiryTitularesRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TITULARES_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcf.ITitularesBCF#fetchTitularesByName(com.sensus.mlc.titulares.model.request.TitularesRequest
	 */
	@Override
	public TitularesResponse fetchTitularesById(TitularesRequest titularesRequest)
	{
		TitularesResponse response = new TitularesResponse();
		InternalResultsResponse<Titulares> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(TITULARES_REQUEST_NAME, titularesRequest);
			context.putObjectToBeValidated(TITULARES_NAME, titularesRequest.getTitulares());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTitularesValidationController().validate(context))
			{
				internalResponse = getTitularesBCL().fetchTitularesById(titularesRequest);
				response.setTitulares(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TITULARES_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcf.ITitularesBCF#insertTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */
	@Override
	public TitularesResponse insertTitulares(TitularesRequest titularesRequest)
	{
		TitularesResponse response = new TitularesResponse();
		InternalResultsResponse<Titulares> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(TITULARES_REQUEST_NAME, titularesRequest);
			context.putObjectToBeValidated(TITULARES_NAME, titularesRequest.getTitulares());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTitularesValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getTitularesBCL().insertTitulares(titularesRequest);
					response.setTitulares(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TITULARES_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcf.ITitularesBCF#deleteTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest)
	 */
	@Override
	public TitularesResponse deleteTitulares(TitularesRequest titularesRequest)
	{
		TitularesResponse response = new TitularesResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(TITULARES_REQUEST_NAME, titularesRequest);
			context.putObjectToBeValidated(TITULARES_NAME, titularesRequest.getTitulares());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTitularesValidationController().validate(context))
			{
				internalResponse = getTitularesBCL().deleteTitulares(titularesRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TITULARES_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.titulares.bcf.ITitularesBCF#updateTitulares(com.sensus.mlc.titulares.model.request.TitularesRequest
	 */
	@Override
	public TitularesResponse updateTitulares(TitularesRequest titularesRequest)
	{
		TitularesResponse response = new TitularesResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(TITULARES_REQUEST_NAME, titularesRequest);
			context.putObjectToBeValidated(TITULARES_NAME, titularesRequest.getTitulares());

			if (getLightingControlRequestValidationController().validate(context) &&
					getTitularesRequestValidationController().validate(context) &&
					getTitularesValidationController().validate(context))
			{
				internalResponse = getTitularesBCL().updateTitulares(titularesRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_TITULARES_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
