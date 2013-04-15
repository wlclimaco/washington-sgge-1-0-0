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
import com.sensus.mlc.gestao.bcf.IUnimedBCF;
import com.sensus.mlc.gestao.bcl.IUnimedBCL;
import com.sensus.mlc.gestao.model.Unimed;
import com.sensus.mlc.gestao.model.request.InquiryUnimedRequest;
import com.sensus.mlc.gestao.model.request.UnimedRequest;
import com.sensus.mlc.gestao.model.response.InquiryUnimedResponse;
import com.sensus.mlc.gestao.model.response.UnimedResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class UnimedBCFImpl.
 *
 * @author - Washington
 */
public class UnimedBCFImpl extends AbstractBaseBCF implements IUnimedBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_UNIMED_REQUEST_NAME. */
	private static final String INQUIRY_UNIMED_REQUEST_NAME = InquiryUnimedRequest.class.getSimpleName();

	/** The Constant UNIMED_REQUEST_NAME. */
	private static final String UNIMED_REQUEST_NAME = UnimedRequest.class.getSimpleName();

	/** The Constant UNIMED_NAME. */
	private static final String UNIMED_NAME = Unimed.class.getSimpleName();

	/** The Constant DEFAULT_UNIMED_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_UNIMED_BCF_EXCEPTION_MSG = "sensus.mlc.unimedbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UnimedBCFImpl.class);

	/** The unimed bcl. */
	private IUnimedBCL unimedBCL;

	/** The unimed validation controller. */
	private ValidationController unimedValidationController;

	/** The unimed list validation controller. */
	private ValidationController unimedListValidationController;

	/** The unimed request validation controller. */
	private ValidationController unimedRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the unimed bcl.
	 *
	 * @return the unimed bcl
	 */
	public IUnimedBCL getUnimedBCL()
	{
		return this.unimedBCL;
	}

	/**
	 * Sets the unimed bcl.
	 *
	 * @param unimedBCLObject the new unimed bcl
	 */
	public void setUnimedBCL(IUnimedBCL unimedBCLObject)
	{
		this.unimedBCL = unimedBCLObject;
	}

	/**
	 * Gets the unimed validation controller.
	 *
	 * @return the unimed validation controller
	 */
	public ValidationController getUnimedValidationController()
	{
		return this.unimedValidationController;
	}

	/**
	 * Sets the unimed validation controller.
	 *
	 * @param unimedValidationController the new unimed validation controller
	 */
	public void setUnimedValidationController(ValidationController unimedValidationController)
	{
		this.unimedValidationController = unimedValidationController;
	}

	/**
	 * Gets the unimed list validation controller.
	 *
	 * @return the unimed list validation controller
	 */
	public ValidationController getUnimedListValidationController()
	{
		return this.unimedListValidationController;
	}

	/**
	 * Sets the unimed list validation controller.
	 *
	 * @param unimedListValidationController the new unimed list validation controller
	 */
	public void setUnimedListValidationController(ValidationController unimedListValidationController)
	{
		this.unimedListValidationController = unimedListValidationController;
	}

	/**
	 * Gets the unimed request validation controller.
	 *
	 * @return the unimed request validation controller
	 */
	public ValidationController getUnimedRequestValidationController()
	{
		return this.unimedRequestValidationController;
	}

	/**
	 * Sets the unimed request validation controller.
	 *
	 * @param unimedRequestValidationController the new unimed request validation controller
	 */
	public void setUnimedRequestValidationController(ValidationController unimedRequestValidationController)
	{
		this.unimedRequestValidationController = unimedRequestValidationController;
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
	 * @see com.sensus.mlc.unimed.bcf.IUnimedBCF#fetchAllUnimeds(com.sensus.mlc.unimed.model.request.InquiryUnimedRequest
	 */
	@Override
	public InquiryUnimedResponse fetchAllUnimed(InquiryUnimedRequest inquiryUnimedRequest)
	{
		InquiryUnimedResponse response = new InquiryUnimedResponse();
		InternalResultsResponse<Unimed> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_UNIMED_REQUEST_NAME, inquiryUnimedRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getUnimedBCL().fetchAllUnimed(inquiryUnimedRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UNIMED_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcf.IUnimedBCF#fetchUnimedByName(com.sensus.mlc.unimed.model.request.UnimedRequest
	 */
	@Override
	public UnimedResponse fetchUnimedById(UnimedRequest unimedRequest)
	{
		UnimedResponse response = new UnimedResponse();
		InternalResultsResponse<Unimed> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(UNIMED_REQUEST_NAME, unimedRequest);
			context.putObjectToBeValidated(UNIMED_NAME, unimedRequest.getUnimed());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUnimedValidationController().validate(context))
			{
				internalResponse = getUnimedBCL().fetchUnimedById(unimedRequest);
				response.setUnimed(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UNIMED_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcf.IUnimedBCF#insertUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */
	@Override
	public UnimedResponse insertUnimed(UnimedRequest unimedRequest)
	{
		UnimedResponse response = new UnimedResponse();
		InternalResultsResponse<Unimed> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(UNIMED_REQUEST_NAME, unimedRequest);
			context.putObjectToBeValidated(UNIMED_NAME, unimedRequest.getUnimed());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUnimedValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getUnimedBCL().insertUnimed(unimedRequest);
					response.setUnimed(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UNIMED_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcf.IUnimedBCF#deleteUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest)
	 */
	@Override
	public UnimedResponse deleteUnimed(UnimedRequest unimedRequest)
	{
		UnimedResponse response = new UnimedResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(UNIMED_REQUEST_NAME, unimedRequest);
			context.putObjectToBeValidated(UNIMED_NAME, unimedRequest.getUnimed());

			if (getLightingControlRequestValidationController().validate(context)
					&& getUnimedValidationController().validate(context))
			{
				internalResponse = getUnimedBCL().deleteUnimed(unimedRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UNIMED_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.unimed.bcf.IUnimedBCF#updateUnimed(com.sensus.mlc.unimed.model.request.UnimedRequest
	 */
	@Override
	public UnimedResponse updateUnimed(UnimedRequest unimedRequest)
	{
		UnimedResponse response = new UnimedResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(UNIMED_REQUEST_NAME, unimedRequest);
			context.putObjectToBeValidated(UNIMED_NAME, unimedRequest.getUnimed());

			if (getLightingControlRequestValidationController().validate(context) &&
					getUnimedRequestValidationController().validate(context) &&
					getUnimedValidationController().validate(context))
			{
				internalResponse = getUnimedBCL().updateUnimed(unimedRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_UNIMED_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
