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
import com.sensus.mlc.gestao.bcf.IClassclienteBCF;
import com.sensus.mlc.gestao.bcl.IClassclienteBCL;
import com.sensus.mlc.gestao.model.Classcliente;
import com.sensus.mlc.gestao.model.request.ClassclienteRequest;
import com.sensus.mlc.gestao.model.request.InquiryClassclienteRequest;
import com.sensus.mlc.gestao.model.response.ClassclienteResponse;
import com.sensus.mlc.gestao.model.response.InquiryClassclienteResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class ClasscliBCFImpl.
 *
 * @author - Washington
 */
public class ClasscliBCFImpl extends AbstractBaseBCF implements IClassclienteBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_CLASSCLI_REQUEST_NAME. */
	private static final String INQUIRY_CLASSCLI_REQUEST_NAME = InquiryClassclienteRequest.class.getSimpleName();

	/** The Constant CLASSCLI_REQUEST_NAME. */
	private static final String CLASSCLI_REQUEST_NAME = ClassclienteRequest.class.getSimpleName();

	/** The Constant CLASSCLI_NAME. */
	private static final String CLASSCLI_NAME = Classcliente.class.getSimpleName();

	/** The Constant DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG = "sensus.mlc.classclibcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ClasscliBCFImpl.class);

	/** The classcli bcl. */
	private IClassclienteBCL classcliBCL;

	/** The classcli validation controller. */
	private ValidationController classcliValidationController;

	/** The classcli list validation controller. */
	private ValidationController classcliListValidationController;

	/** The classcli request validation controller. */
	private ValidationController classcliRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the classcli bcl.
	 *
	 * @return the classcli bcl
	 */
	public IClassclienteBCL getClasscliBCL()
	{
		return this.classcliBCL;
	}

	/**
	 * Sets the classcli bcl.
	 *
	 * @param classcliBCLObject the new classcli bcl
	 */
	public void setClasscliBCL(IClassclienteBCL classcliBCLObject)
	{
		this.classcliBCL = classcliBCLObject;
	}

	/**
	 * Gets the classcli validation controller.
	 *
	 * @return the classcli validation controller
	 */
	public ValidationController getClasscliValidationController()
	{
		return this.classcliValidationController;
	}

	/**
	 * Sets the classcli validation controller.
	 *
	 * @param classcliValidationController the new classcli validation controller
	 */
	public void setClasscliValidationController(ValidationController classcliValidationController)
	{
		this.classcliValidationController = classcliValidationController;
	}

	/**
	 * Gets the classcli list validation controller.
	 *
	 * @return the classcli list validation controller
	 */
	public ValidationController getClasscliListValidationController()
	{
		return this.classcliListValidationController;
	}

	/**
	 * Sets the classcli list validation controller.
	 *
	 * @param classcliListValidationController the new classcli list validation controller
	 */
	public void setClasscliListValidationController(ValidationController classcliListValidationController)
	{
		this.classcliListValidationController = classcliListValidationController;
	}

	/**
	 * Gets the classcli request validation controller.
	 *
	 * @return the classcli request validation controller
	 */
	public ValidationController getClasscliRequestValidationController()
	{
		return this.classcliRequestValidationController;
	}

	/**
	 * Sets the classcli request validation controller.
	 *
	 * @param classcliRequestValidationController the new classcli request validation controller
	 */
	public void setClasscliRequestValidationController(ValidationController classcliRequestValidationController)
	{
		this.classcliRequestValidationController = classcliRequestValidationController;
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
	 * @see com.sensus.mlc.classcli.bcf.IClasscliBCF#fetchAllClassclis(com.sensus.mlc.classcli.model.request.InquiryClasscliRequest
	 */
	@Override
	public InquiryClassclienteResponse fetchAllClasscliente(InquiryClassclienteRequest  inquiryClasscliRequest)
	{
		InquiryClassclienteResponse response = new InquiryClassclienteResponse();
		InternalResultsResponse<Classcliente> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_CLASSCLI_REQUEST_NAME, inquiryClasscliRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getClasscliBCL().fetchAllClasscliente(inquiryClasscliRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.bcf.IClasscliBCF#fetchClasscliByName(com.sensus.mlc.classcli.model.request.ClasscliRequest
	 */
	@Override
	public ClassclienteResponse fetchClassclienteById(ClassclienteRequest classcliRequest)
	{
		ClassclienteResponse response = new ClassclienteResponse();
		InternalResultsResponse<Classcliente> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(CLASSCLI_REQUEST_NAME, classcliRequest);
			context.putObjectToBeValidated(CLASSCLI_NAME, classcliRequest.getClasscliente());

			if (getLightingControlRequestValidationController().validate(context)
					&& getClasscliValidationController().validate(context))
			{
				internalResponse = getClasscliBCL().fetchClassclienteById(classcliRequest);
				response.setClasscliente(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.bcf.IClasscliBCF#insertClasscli(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */
	@Override
	public ClassclienteResponse insertClasscliente(ClassclienteRequest classcliRequest)
	{
		ClassclienteResponse response = new ClassclienteResponse();
		InternalResultsResponse<Classcliente> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(CLASSCLI_REQUEST_NAME, classcliRequest);
			context.putObjectToBeValidated(CLASSCLI_NAME, classcliRequest.getClasscliente());

			if (getLightingControlRequestValidationController().validate(context)
					&& getClasscliValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getClasscliBCL().insertClasscliente(classcliRequest);
					response.setClasscliente(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.bcf.IClasscliBCF#deleteClasscli(com.sensus.mlc.classcli.model.request.ClasscliRequest)
	 */
	@Override
	public ClassclienteResponse deleteClasscliente(ClassclienteRequest classcliRequest)
	{
		ClassclienteResponse response = new ClassclienteResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(CLASSCLI_REQUEST_NAME, classcliRequest);
			context.putObjectToBeValidated(CLASSCLI_NAME, classcliRequest.getClasscliente());

			if (getLightingControlRequestValidationController().validate(context)
					&& getClasscliValidationController().validate(context))
			{
				internalResponse = getClasscliBCL().deleteClasscliente(classcliRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.classcli.bcf.IClasscliBCF#updateClasscli(com.sensus.mlc.classcli.model.request.ClasscliRequest
	 */
	@Override
	public ClassclienteResponse updateClasscliente(ClassclienteRequest classcliRequest)
	{
		ClassclienteResponse response = new ClassclienteResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(CLASSCLI_REQUEST_NAME, classcliRequest);
			context.putObjectToBeValidated(CLASSCLI_NAME, classcliRequest.getClasscliente());

			if (getLightingControlRequestValidationController().validate(context) &&
					getClasscliRequestValidationController().validate(context) &&
					getClasscliValidationController().validate(context))
			{
				internalResponse = getClasscliBCL().updateClasscliente(classcliRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_CLASSCLI_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
