package com.sensus.mlc.gestao.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
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
import com.sensus.mlc.gestao.bcf.IFilialBCF;
import com.sensus.mlc.gestao.bcl.IFilialBCL;
import com.sensus.mlc.gestao.model.Filial;
import com.sensus.mlc.gestao.model.request.FilialRequest;
import com.sensus.mlc.gestao.model.request.InquiryFilialRequest;
import com.sensus.mlc.gestao.model.response.FilialResponse;
import com.sensus.mlc.gestao.model.response.InquiryFilialResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class FilialBCFImpl.
 *
 * @author - Washington
 */
public class FilialBCFImpl extends AbstractBaseBCF implements IFilialBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_FILIAL_REQUEST_NAME. */
	private static final String INQUIRY_FILIAL_REQUEST_NAME = InquiryFilialRequest.class.getSimpleName();

	/** The Constant FILIAL_REQUEST_NAME. */
	private static final String FILIAL_REQUEST_NAME = FilialRequest.class.getSimpleName();

	/** The Constant FILIAL_NAME. */
	private static final String FILIAL_NAME = Filial.class.getSimpleName();

	/** The Constant DEFAULT_FILIAL_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_FILIAL_BCF_EXCEPTION_MSG = "sensus.mlc.filialbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FilialBCFImpl.class);

	/** The filial bcl. */
	private IFilialBCL filialBCL;

	/** The filial validation controller. */
	private ValidationController filialValidationController;

	/** The filial list validation controller. */
	private ValidationController filialListValidationController;

	/** The filial request validation controller. */
	private ValidationController filialRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the filial bcl.
	 *
	 * @return the filial bcl
	 */
	public IFilialBCL getFilialBCL()
	{
		return this.filialBCL;
	}

	/**
	 * Sets the filial bcl.
	 *
	 * @param filialBCLObject the new filial bcl
	 */
	public void setFilialBCL(IFilialBCL filialBCLObject)
	{
		this.filialBCL = filialBCLObject;
	}

	/**
	 * Gets the filial validation controller.
	 *
	 * @return the filial validation controller
	 */
	public ValidationController getFilialValidationController()
	{
		return this.filialValidationController;
	}

	/**
	 * Sets the filial validation controller.
	 *
	 * @param filialValidationController the new filial validation controller
	 */
	public void setFilialValidationController(ValidationController filialValidationController)
	{
		this.filialValidationController = filialValidationController;
	}

	/**
	 * Gets the filial list validation controller.
	 *
	 * @return the filial list validation controller
	 */
	public ValidationController getFilialListValidationController()
	{
		return this.filialListValidationController;
	}

	/**
	 * Sets the filial list validation controller.
	 *
	 * @param filialListValidationController the new filial list validation controller
	 */
	public void setFilialListValidationController(ValidationController filialListValidationController)
	{
		this.filialListValidationController = filialListValidationController;
	}

	/**
	 * Gets the filial request validation controller.
	 *
	 * @return the filial request validation controller
	 */
	public ValidationController getFilialRequestValidationController()
	{
		return this.filialRequestValidationController;
	}

	/**
	 * Sets the filial request validation controller.
	 *
	 * @param filialRequestValidationController the new filial request validation controller
	 */
	public void setFilialRequestValidationController(ValidationController filialRequestValidationController)
	{
		this.filialRequestValidationController = filialRequestValidationController;
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
	 * @see com.sensus.mlc.filial.bcf.IFilialBCF#insertFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */
	@Override
	public FilialResponse insertFilial(FilialRequest filialRequest)
	{
		FilialResponse response = new FilialResponse();
		InternalResultsResponse<Filial> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(FILIAL_REQUEST_NAME, filialRequest);
			context.putObjectToBeValidated(FILIAL_NAME, filialRequest.getFilial());

			if (getLightingControlRequestValidationController().validate(context)
					&& getFilialValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getFilialBCL().insertFilial(filialRequest);
					response.setFilial(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FILIAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.bcf.IFilialBCF#deleteFilial(com.sensus.mlc.filial.model.request.FilialRequest)
	 */
	@Override
	public FilialResponse deleteFilial(FilialRequest filialRequest)
	{
		FilialResponse response = new FilialResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(FILIAL_REQUEST_NAME, filialRequest);
			context.putObjectToBeValidated(FILIAL_NAME, filialRequest.getFilial());

			if (getLightingControlRequestValidationController().validate(context)
					&& getFilialValidationController().validate(context))
			{
				internalResponse = getFilialBCL().deleteFilial(filialRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FILIAL_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.filial.bcf.IFilialBCF#updateFilial(com.sensus.mlc.filial.model.request.FilialRequest
	 */
	@Override
	public FilialResponse updateFilial(FilialRequest filialRequest)
	{
		FilialResponse response = new FilialResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(FILIAL_REQUEST_NAME, filialRequest);
			context.putObjectToBeValidated(FILIAL_NAME, filialRequest.getFilial());

			if (getLightingControlRequestValidationController().validate(context) &&
					getFilialRequestValidationController().validate(context) &&
					getFilialValidationController().validate(context))
			{
				internalResponse = getFilialBCL().updateFilial(filialRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_FILIAL_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public InquiryFilialResponse fetchAllFilial(
			InquiryFilialRequest inquiryFilialRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilialResponse fetchFilialById(FilialRequest filialRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
