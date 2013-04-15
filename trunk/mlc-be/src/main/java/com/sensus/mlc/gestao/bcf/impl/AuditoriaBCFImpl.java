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
import com.sensus.mlc.gestao.bcf.IAuditoriaBCF;
import com.sensus.mlc.gestao.bcl.IAuditoriaBCL;
import com.sensus.mlc.gestao.model.Auditoria;
import com.sensus.mlc.gestao.model.request.AuditoriaRequest;
import com.sensus.mlc.gestao.model.request.InquiryAuditoriaRequest;
import com.sensus.mlc.gestao.model.response.AuditoriaResponse;
import com.sensus.mlc.gestao.model.response.InquiryAuditoriaResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class AuditoriaBCFImpl.
 *
 * @author - Washington
 */
public class AuditoriaBCFImpl extends AbstractBaseBCF implements IAuditoriaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_AUDITORIA_REQUEST_NAME. */
	private static final String INQUIRY_AUDITORIA_REQUEST_NAME = InquiryAuditoriaRequest.class.getSimpleName();

	/** The Constant AUDITORIA_REQUEST_NAME. */
	private static final String AUDITORIA_REQUEST_NAME = AuditoriaRequest.class.getSimpleName();

	/** The Constant AUDITORIA_NAME. */
	private static final String AUDITORIA_NAME = Auditoria.class.getSimpleName();

	/** The Constant DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG = "sensus.mlc.auditoriabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AuditoriaBCFImpl.class);

	/** The auditoria bcl. */
	private IAuditoriaBCL auditoriaBCL;

	/** The auditoria validation controller. */
	private ValidationController auditoriaValidationController;

	/** The auditoria list validation controller. */
	private ValidationController auditoriaListValidationController;

	/** The auditoria request validation controller. */
	private ValidationController auditoriaRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the auditoria bcl.
	 *
	 * @return the auditoria bcl
	 */
	public IAuditoriaBCL getAuditoriaBCL()
	{
		return this.auditoriaBCL;
	}

	/**
	 * Sets the auditoria bcl.
	 *
	 * @param auditoriaBCLObject the new auditoria bcl
	 */
	public void setAuditoriaBCL(IAuditoriaBCL auditoriaBCLObject)
	{
		this.auditoriaBCL = auditoriaBCLObject;
	}

	/**
	 * Gets the auditoria validation controller.
	 *
	 * @return the auditoria validation controller
	 */
	public ValidationController getAuditoriaValidationController()
	{
		return this.auditoriaValidationController;
	}

	/**
	 * Sets the auditoria validation controller.
	 *
	 * @param auditoriaValidationController the new auditoria validation controller
	 */
	public void setAuditoriaValidationController(ValidationController auditoriaValidationController)
	{
		this.auditoriaValidationController = auditoriaValidationController;
	}

	/**
	 * Gets the auditoria list validation controller.
	 *
	 * @return the auditoria list validation controller
	 */
	public ValidationController getAuditoriaListValidationController()
	{
		return this.auditoriaListValidationController;
	}

	/**
	 * Sets the auditoria list validation controller.
	 *
	 * @param auditoriaListValidationController the new auditoria list validation controller
	 */
	public void setAuditoriaListValidationController(ValidationController auditoriaListValidationController)
	{
		this.auditoriaListValidationController = auditoriaListValidationController;
	}

	/**
	 * Gets the auditoria request validation controller.
	 *
	 * @return the auditoria request validation controller
	 */
	public ValidationController getAuditoriaRequestValidationController()
	{
		return this.auditoriaRequestValidationController;
	}

	/**
	 * Sets the auditoria request validation controller.
	 *
	 * @param auditoriaRequestValidationController the new auditoria request validation controller
	 */
	public void setAuditoriaRequestValidationController(ValidationController auditoriaRequestValidationController)
	{
		this.auditoriaRequestValidationController = auditoriaRequestValidationController;
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
	 * @see com.sensus.mlc.auditoria.bcf.IAuditoriaBCF#fetchAllAuditorias(com.sensus.mlc.auditoria.model.request.InquiryAuditoriaRequest
	 */
	@Override
	public InquiryAuditoriaResponse fetchAllAuditoria(InquiryAuditoriaRequest inquiryAuditoriaRequest)
	{
		InquiryAuditoriaResponse response = new InquiryAuditoriaResponse();
		InternalResultsResponse<Auditoria> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_AUDITORIA_REQUEST_NAME, inquiryAuditoriaRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getAuditoriaBCL().fetchAllAuditoria(inquiryAuditoriaRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcf.IAuditoriaBCF#fetchAuditoriaByName(com.sensus.mlc.auditoria.model.request.AuditoriaRequest
	 */
	@Override
	public AuditoriaResponse fetchAuditoriaById(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		InternalResultsResponse<Auditoria> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(AUDITORIA_REQUEST_NAME, auditoriaRequest);
			context.putObjectToBeValidated(AUDITORIA_NAME, auditoriaRequest.getAuditoria());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAuditoriaValidationController().validate(context))
			{
				internalResponse = getAuditoriaBCL().fetchAuditoriaById(auditoriaRequest);
				response.setAuditoria(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcf.IAuditoriaBCF#insertAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public AuditoriaResponse insertAuditoria(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		InternalResultsResponse<Auditoria> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(AUDITORIA_REQUEST_NAME, auditoriaRequest);
			context.putObjectToBeValidated(AUDITORIA_NAME, auditoriaRequest.getAuditoria());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAuditoriaValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getAuditoriaBCL().insertAuditoria(auditoriaRequest);
					response.setAuditoria(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcf.IAuditoriaBCF#deleteAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest)
	 */
	@Override
	public AuditoriaResponse deleteAuditoria(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(AUDITORIA_REQUEST_NAME, auditoriaRequest);
			context.putObjectToBeValidated(AUDITORIA_NAME, auditoriaRequest.getAuditoria());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAuditoriaValidationController().validate(context))
			{
				internalResponse = getAuditoriaBCL().deleteAuditoria(auditoriaRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.auditoria.bcf.IAuditoriaBCF#updateAuditoria(com.sensus.mlc.auditoria.model.request.AuditoriaRequest
	 */
	@Override
	public AuditoriaResponse updateAuditoria(AuditoriaRequest auditoriaRequest)
	{
		AuditoriaResponse response = new AuditoriaResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(AUDITORIA_REQUEST_NAME, auditoriaRequest);
			context.putObjectToBeValidated(AUDITORIA_NAME, auditoriaRequest.getAuditoria());

			if (getLightingControlRequestValidationController().validate(context) &&
					getAuditoriaRequestValidationController().validate(context) &&
					getAuditoriaValidationController().validate(context))
			{
				internalResponse = getAuditoriaBCL().updateAuditoria(auditoriaRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_AUDITORIA_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
