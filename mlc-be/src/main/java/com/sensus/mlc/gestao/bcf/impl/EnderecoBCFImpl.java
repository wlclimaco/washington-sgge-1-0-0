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
import com.sensus.mlc.gestao.bcf.IEnderecoBCF;
import com.sensus.mlc.gestao.bcl.IEnderecoBCL;
import com.sensus.mlc.gestao.model.Endereco;
import com.sensus.mlc.gestao.model.request.EnderecoRequest;
import com.sensus.mlc.gestao.model.request.InquiryEnderecoRequest;
import com.sensus.mlc.gestao.model.response.EnderecoResponse;
import com.sensus.mlc.gestao.model.response.InquiryEnderecoResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class EnderecoBCFImpl.
 *
 * @author - Washington
 */
public class EnderecoBCFImpl extends AbstractBaseBCF implements IEnderecoBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_ENDERECO_REQUEST_NAME. */
	private static final String INQUIRY_ENDERECO_REQUEST_NAME = InquiryEnderecoRequest.class.getSimpleName();

	/** The Constant ENDERECO_REQUEST_NAME. */
	private static final String ENDERECO_REQUEST_NAME = EnderecoRequest.class.getSimpleName();

	/** The Constant ENDERECO_NAME. */
	private static final String ENDERECO_NAME = Endereco.class.getSimpleName();

	/** The Constant DEFAULT_ENDERECO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ENDERECO_BCF_EXCEPTION_MSG = "sensus.mlc.enderecobcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EnderecoBCFImpl.class);

	/** The endereco bcl. */
	private IEnderecoBCL enderecoBCL;

	/** The endereco validation controller. */
	private ValidationController enderecoValidationController;

	/** The endereco list validation controller. */
	private ValidationController enderecoListValidationController;

	/** The endereco request validation controller. */
	private ValidationController enderecoRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the endereco bcl.
	 *
	 * @return the endereco bcl
	 */
	public IEnderecoBCL getEnderecoBCL()
	{
		return this.enderecoBCL;
	}

	/**
	 * Sets the endereco bcl.
	 *
	 * @param enderecoBCLObject the new endereco bcl
	 */
	public void setEnderecoBCL(IEnderecoBCL enderecoBCLObject)
	{
		this.enderecoBCL = enderecoBCLObject;
	}

	/**
	 * Gets the endereco validation controller.
	 *
	 * @return the endereco validation controller
	 */
	public ValidationController getEnderecoValidationController()
	{
		return this.enderecoValidationController;
	}

	/**
	 * Sets the endereco validation controller.
	 *
	 * @param enderecoValidationController the new endereco validation controller
	 */
	public void setEnderecoValidationController(ValidationController enderecoValidationController)
	{
		this.enderecoValidationController = enderecoValidationController;
	}

	/**
	 * Gets the endereco list validation controller.
	 *
	 * @return the endereco list validation controller
	 */
	public ValidationController getEnderecoListValidationController()
	{
		return this.enderecoListValidationController;
	}

	/**
	 * Sets the endereco list validation controller.
	 *
	 * @param enderecoListValidationController the new endereco list validation controller
	 */
	public void setEnderecoListValidationController(ValidationController enderecoListValidationController)
	{
		this.enderecoListValidationController = enderecoListValidationController;
	}

	/**
	 * Gets the endereco request validation controller.
	 *
	 * @return the endereco request validation controller
	 */
	public ValidationController getEnderecoRequestValidationController()
	{
		return this.enderecoRequestValidationController;
	}

	/**
	 * Sets the endereco request validation controller.
	 *
	 * @param enderecoRequestValidationController the new endereco request validation controller
	 */
	public void setEnderecoRequestValidationController(ValidationController enderecoRequestValidationController)
	{
		this.enderecoRequestValidationController = enderecoRequestValidationController;
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
	 * @see com.sensus.mlc.endereco.bcf.IEnderecoBCF#fetchAllEnderecos(com.sensus.mlc.endereco.model.request.InquiryEnderecoRequest
	 */
	@Override
	public InquiryEnderecoResponse fetchAllEndereco(InquiryEnderecoRequest inquiryEnderecoRequest)
	{
		InquiryEnderecoResponse response = new InquiryEnderecoResponse();
		InternalResultsResponse<Endereco> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_ENDERECO_REQUEST_NAME, inquiryEnderecoRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getEnderecoBCL().fetchAllEndereco(inquiryEnderecoRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ENDERECO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcf.IEnderecoBCF#fetchEnderecoByName(com.sensus.mlc.endereco.model.request.EnderecoRequest
	 */
	@Override
	public EnderecoResponse fetchEnderecoById(EnderecoRequest enderecoRequest)
	{
		EnderecoResponse response = new EnderecoResponse();
		InternalResultsResponse<Endereco> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(ENDERECO_REQUEST_NAME, enderecoRequest);
			context.putObjectToBeValidated(ENDERECO_NAME, enderecoRequest.getEndereco());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEnderecoValidationController().validate(context))
			{
				internalResponse = getEnderecoBCL().fetchEnderecoById(enderecoRequest);
				response.setEndereco(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ENDERECO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcf.IEnderecoBCF#insertEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */
	@Override
	public EnderecoResponse insertEndereco(EnderecoRequest enderecoRequest)
	{
		EnderecoResponse response = new EnderecoResponse();
		InternalResultsResponse<Endereco> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(ENDERECO_REQUEST_NAME, enderecoRequest);
			context.putObjectToBeValidated(ENDERECO_NAME, enderecoRequest.getEndereco());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEnderecoValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getEnderecoBCL().insertEndereco(enderecoRequest);
					response.setEndereco(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ENDERECO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcf.IEnderecoBCF#deleteEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest)
	 */
	@Override
	public EnderecoResponse deleteEndereco(EnderecoRequest enderecoRequest)
	{
		EnderecoResponse response = new EnderecoResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(ENDERECO_REQUEST_NAME, enderecoRequest);
			context.putObjectToBeValidated(ENDERECO_NAME, enderecoRequest.getEndereco());

			if (getLightingControlRequestValidationController().validate(context)
					&& getEnderecoValidationController().validate(context))
			{
				internalResponse = getEnderecoBCL().deleteEndereco(enderecoRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ENDERECO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.endereco.bcf.IEnderecoBCF#updateEndereco(com.sensus.mlc.endereco.model.request.EnderecoRequest
	 */
	@Override
	public EnderecoResponse updateEndereco(EnderecoRequest enderecoRequest)
	{
		EnderecoResponse response = new EnderecoResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(ENDERECO_REQUEST_NAME, enderecoRequest);
			context.putObjectToBeValidated(ENDERECO_NAME, enderecoRequest.getEndereco());

			if (getLightingControlRequestValidationController().validate(context) &&
					getEnderecoRequestValidationController().validate(context) &&
					getEnderecoValidationController().validate(context))
			{
				internalResponse = getEnderecoBCL().updateEndereco(enderecoRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ENDERECO_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
