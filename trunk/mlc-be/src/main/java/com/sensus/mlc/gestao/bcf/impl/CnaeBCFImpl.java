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
import com.sensus.mlc.gestao.bcf.ICnaeBCF;
import com.sensus.mlc.gestao.bcl.ICnaeBCL;
import com.sensus.mlc.gestao.model.Cnae;
import com.sensus.mlc.gestao.model.request.CnaeRequest;
import com.sensus.mlc.gestao.model.request.InquiryCnaeRequest;
import com.sensus.mlc.gestao.model.response.CnaeResponse;
import com.sensus.mlc.gestao.model.response.InquiryCnaeResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class CnaeBCFImpl.
 *
 * @author - Washington
 */
public class CnaeBCFImpl extends AbstractBaseBCF implements ICnaeBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_CNAE_REQUEST_NAME. */
	private static final String INQUIRY_CNAE_REQUEST_NAME = InquiryCnaeRequest.class.getSimpleName();

	/** The Constant CNAE_REQUEST_NAME. */
	private static final String CNAE_REQUEST_NAME = CnaeRequest.class.getSimpleName();

	/** The Constant CNAE_NAME. */
	private static final String CNAE_NAME = Cnae.class.getSimpleName();

	/** The Constant DEFAULT_CNAE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_CNAE_BCF_EXCEPTION_MSG = "sensus.mlc.cnaebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CnaeBCFImpl.class);

	/** The cnae bcl. */
	private ICnaeBCL cnaeBCL;

	/** The cnae validation controller. */
	private ValidationController cnaeValidationController;

	/** The cnae list validation controller. */
	private ValidationController cnaeListValidationController;

	/** The cnae request validation controller. */
	private ValidationController cnaeRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the cnae bcl.
	 *
	 * @return the cnae bcl
	 */
	public ICnaeBCL getCnaeBCL()
	{
		return this.cnaeBCL;
	}

	/**
	 * Sets the cnae bcl.
	 *
	 * @param cnaeBCLObject the new cnae bcl
	 */
	public void setCnaeBCL(ICnaeBCL cnaeBCLObject)
	{
		this.cnaeBCL = cnaeBCLObject;
	}

	/**
	 * Gets the cnae validation controller.
	 *
	 * @return the cnae validation controller
	 */
	public ValidationController getCnaeValidationController()
	{
		return this.cnaeValidationController;
	}

	/**
	 * Sets the cnae validation controller.
	 *
	 * @param cnaeValidationController the new cnae validation controller
	 */
	public void setCnaeValidationController(ValidationController cnaeValidationController)
	{
		this.cnaeValidationController = cnaeValidationController;
	}

	/**
	 * Gets the cnae list validation controller.
	 *
	 * @return the cnae list validation controller
	 */
	public ValidationController getCnaeListValidationController()
	{
		return this.cnaeListValidationController;
	}

	/**
	 * Sets the cnae list validation controller.
	 *
	 * @param cnaeListValidationController the new cnae list validation controller
	 */
	public void setCnaeListValidationController(ValidationController cnaeListValidationController)
	{
		this.cnaeListValidationController = cnaeListValidationController;
	}

	/**
	 * Gets the cnae request validation controller.
	 *
	 * @return the cnae request validation controller
	 */
	public ValidationController getCnaeRequestValidationController()
	{
		return this.cnaeRequestValidationController;
	}

	/**
	 * Sets the cnae request validation controller.
	 *
	 * @param cnaeRequestValidationController the new cnae request validation controller
	 */
	public void setCnaeRequestValidationController(ValidationController cnaeRequestValidationController)
	{
		this.cnaeRequestValidationController = cnaeRequestValidationController;
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
	 * @see com.sensus.mlc.cnae.bcf.ICnaeBCF#fetchAllCnaes(com.sensus.mlc.cnae.model.request.InquiryCnaeRequest
	 */
	@Override
	public InquiryCnaeResponse fetchAllCnae(InquiryCnaeRequest inquiryCnaeRequest)
	{
		InquiryCnaeResponse response = new InquiryCnaeResponse();
		InternalResultsResponse<Cnae> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_CNAE_REQUEST_NAME, inquiryCnaeRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getCnaeBCL().fetchAllCnae(inquiryCnaeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CNAE_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcf.ICnaeBCF#fetchCnaeByName(com.sensus.mlc.cnae.model.request.CnaeRequest
	 */
	@Override
	public CnaeResponse fetchCnaeById(CnaeRequest cnaeRequest)
	{
		CnaeResponse response = new CnaeResponse();
		InternalResultsResponse<Cnae> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(CNAE_REQUEST_NAME, cnaeRequest);
			context.putObjectToBeValidated(CNAE_NAME, cnaeRequest.getCnae());

			if (getLightingControlRequestValidationController().validate(context)
					&& getCnaeValidationController().validate(context))
			{
				internalResponse = getCnaeBCL().fetchCnaeById(cnaeRequest);
				response.setCnae(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CNAE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcf.ICnaeBCF#insertCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */
	@Override
	public CnaeResponse insertCnae(CnaeRequest cnaeRequest)
	{
		CnaeResponse response = new CnaeResponse();
		InternalResultsResponse<Cnae> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(CNAE_REQUEST_NAME, cnaeRequest);
			context.putObjectToBeValidated(CNAE_NAME, cnaeRequest.getCnae());

			if (getLightingControlRequestValidationController().validate(context)
					&& getCnaeValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getCnaeBCL().insertCnae(cnaeRequest);
					response.setCnae(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CNAE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcf.ICnaeBCF#deleteCnae(com.sensus.mlc.cnae.model.request.CnaeRequest)
	 */
	@Override
	public CnaeResponse deleteCnae(CnaeRequest cnaeRequest)
	{
		CnaeResponse response = new CnaeResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(CNAE_REQUEST_NAME, cnaeRequest);
			context.putObjectToBeValidated(CNAE_NAME, cnaeRequest.getCnae());

			if (getLightingControlRequestValidationController().validate(context)
					&& getCnaeValidationController().validate(context))
			{
				internalResponse = getCnaeBCL().deleteCnae(cnaeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CNAE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.cnae.bcf.ICnaeBCF#updateCnae(com.sensus.mlc.cnae.model.request.CnaeRequest
	 */
	@Override
	public CnaeResponse updateCnae(CnaeRequest cnaeRequest)
	{
		CnaeResponse response = new CnaeResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(CNAE_REQUEST_NAME, cnaeRequest);
			context.putObjectToBeValidated(CNAE_NAME, cnaeRequest.getCnae());

			if (getLightingControlRequestValidationController().validate(context) &&
					getCnaeRequestValidationController().validate(context) &&
					getCnaeValidationController().validate(context))
			{
				internalResponse = getCnaeBCL().updateCnae(cnaeRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CNAE_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
