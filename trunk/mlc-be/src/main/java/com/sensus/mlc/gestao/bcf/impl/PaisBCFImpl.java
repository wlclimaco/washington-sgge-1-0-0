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
import com.sensus.mlc.gestao.bcf.IPaisBCF;
import com.sensus.mlc.gestao.bcl.IPaisBCL;
import com.sensus.mlc.gestao.model.Pais;
import com.sensus.mlc.gestao.model.request.InquiryPaisRequest;
import com.sensus.mlc.gestao.model.request.PaisRequest;
import com.sensus.mlc.gestao.model.response.InquiryPaisResponse;
import com.sensus.mlc.gestao.model.response.PaisResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class PaisBCFImpl.
 *
 * @author - Washington
 */
public class PaisBCFImpl extends AbstractBaseBCF implements IPaisBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_PAIS_REQUEST_NAME. */
	private static final String INQUIRY_PAIS_REQUEST_NAME = InquiryPaisRequest.class.getSimpleName();

	/** The Constant PAIS_REQUEST_NAME. */
	private static final String PAIS_REQUEST_NAME = PaisRequest.class.getSimpleName();

	/** The Constant PAIS_NAME. */
	private static final String PAIS_NAME = Pais.class.getSimpleName();

	/** The Constant DEFAULT_PAIS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PAIS_BCF_EXCEPTION_MSG = "sensus.mlc.paisbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PaisBCFImpl.class);

	/** The pais bcl. */
	private IPaisBCL paisBCL;

	/** The pais validation controller. */
	private ValidationController paisValidationController;

	/** The pais list validation controller. */
	private ValidationController paisListValidationController;

	/** The pais request validation controller. */
	private ValidationController paisRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the pais bcl.
	 *
	 * @return the pais bcl
	 */
	public IPaisBCL getPaisBCL()
	{
		return this.paisBCL;
	}

	/**
	 * Sets the pais bcl.
	 *
	 * @param paisBCLObject the new pais bcl
	 */
	public void setPaisBCL(IPaisBCL paisBCLObject)
	{
		this.paisBCL = paisBCLObject;
	}

	/**
	 * Gets the pais validation controller.
	 *
	 * @return the pais validation controller
	 */
	public ValidationController getPaisValidationController()
	{
		return this.paisValidationController;
	}

	/**
	 * Sets the pais validation controller.
	 *
	 * @param paisValidationController the new pais validation controller
	 */
	public void setPaisValidationController(ValidationController paisValidationController)
	{
		this.paisValidationController = paisValidationController;
	}

	/**
	 * Gets the pais list validation controller.
	 *
	 * @return the pais list validation controller
	 */
	public ValidationController getPaisListValidationController()
	{
		return this.paisListValidationController;
	}

	/**
	 * Sets the pais list validation controller.
	 *
	 * @param paisListValidationController the new pais list validation controller
	 */
	public void setPaisListValidationController(ValidationController paisListValidationController)
	{
		this.paisListValidationController = paisListValidationController;
	}

	/**
	 * Gets the pais request validation controller.
	 *
	 * @return the pais request validation controller
	 */
	public ValidationController getPaisRequestValidationController()
	{
		return this.paisRequestValidationController;
	}

	/**
	 * Sets the pais request validation controller.
	 *
	 * @param paisRequestValidationController the new pais request validation controller
	 */
	public void setPaisRequestValidationController(ValidationController paisRequestValidationController)
	{
		this.paisRequestValidationController = paisRequestValidationController;
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
	 * @see com.sensus.mlc.pais.bcf.IPaisBCF#fetchAllPaiss(com.sensus.mlc.pais.model.request.InquiryPaisRequest
	 */
	@Override
	public InquiryPaisResponse fetchAllPais(InquiryPaisRequest inquiryPaisRequest)
	{
		InquiryPaisResponse response = new InquiryPaisResponse();
		InternalResultsResponse<Pais> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_PAIS_REQUEST_NAME, inquiryPaisRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getPaisBCL().fetchAllPais(inquiryPaisRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PAIS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcf.IPaisBCF#fetchPaisByName(com.sensus.mlc.pais.model.request.PaisRequest
	 */
	@Override
	public PaisResponse fetchPaisById(PaisRequest paisRequest)
	{
		PaisResponse response = new PaisResponse();
		InternalResultsResponse<Pais> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(PAIS_REQUEST_NAME, paisRequest);
			context.putObjectToBeValidated(PAIS_NAME, paisRequest.getPais());

			if (getLightingControlRequestValidationController().validate(context)
					&& getPaisValidationController().validate(context))
			{
				internalResponse = getPaisBCL().fetchPaisById(paisRequest);
				response.setPais(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PAIS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcf.IPaisBCF#insertPais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */
	@Override
	public PaisResponse insertPais(PaisRequest paisRequest)
	{
		PaisResponse response = new PaisResponse();
		InternalResultsResponse<Pais> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(PAIS_REQUEST_NAME, paisRequest);
			context.putObjectToBeValidated(PAIS_NAME, paisRequest.getPais());

			if (getLightingControlRequestValidationController().validate(context)
					&& getPaisValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getPaisBCL().insertPais(paisRequest);
					response.setPais(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PAIS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcf.IPaisBCF#deletePais(com.sensus.mlc.pais.model.request.PaisRequest)
	 */
	@Override
	public PaisResponse deletePais(PaisRequest paisRequest)
	{
		PaisResponse response = new PaisResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(PAIS_REQUEST_NAME, paisRequest);
			context.putObjectToBeValidated(PAIS_NAME, paisRequest.getPais());

			if (getLightingControlRequestValidationController().validate(context)
					&& getPaisValidationController().validate(context))
			{
				internalResponse = getPaisBCL().deletePais(paisRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PAIS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.pais.bcf.IPaisBCF#updatePais(com.sensus.mlc.pais.model.request.PaisRequest
	 */
	@Override
	public PaisResponse updatePais(PaisRequest paisRequest)
	{
		PaisResponse response = new PaisResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(PAIS_REQUEST_NAME, paisRequest);
			context.putObjectToBeValidated(PAIS_NAME, paisRequest.getPais());

			if (getLightingControlRequestValidationController().validate(context) &&
					getPaisRequestValidationController().validate(context) &&
					getPaisValidationController().validate(context))
			{
				internalResponse = getPaisBCL().updatePais(paisRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PAIS_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
