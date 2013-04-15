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
import com.sensus.mlc.gestao.bcf.IEmpresaBCF;
import com.sensus.mlc.gestao.bcl.IEmpresaBCL;
import com.sensus.mlc.gestao.model.Empresa;
import com.sensus.mlc.gestao.model.request.EmpresaRequest;
import com.sensus.mlc.gestao.model.request.InquiryEmpresaRequest;
import com.sensus.mlc.gestao.model.response.EmpresaResponse;
import com.sensus.mlc.gestao.model.response.InquiryEmpresaResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class EmpresaBCFImpl.
 *
 * @author - Washington
 */
public class EmpresaBCFImpl extends AbstractBaseBCF implements IEmpresaBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_EMPRESA_REQUEST_NAME. */
	private static final String INQUIRY_EMPRESA_REQUEST_NAME = InquiryEmpresaRequest.class.getSimpleName();

	/** The Constant EMPRESA_REQUEST_NAME. */
	private static final String EMPRESA_REQUEST_NAME = EmpresaRequest.class.getSimpleName();

	/** The Constant EMPRESA_NAME. */
	private static final String EMPRESA_NAME = Empresa.class.getSimpleName();

	/** The Constant DEFAULT_EMPRESA_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_EMPRESA_BCF_EXCEPTION_MSG = "sensus.mlc.empresabcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmpresaBCFImpl.class);

	/** The empresa bcl. */
	private IEmpresaBCL empresaBCL;

	/** The empresa validation controller. */
	private ValidationController empresaValidationController;

	/** The empresa list validation controller. */
	private ValidationController empresaListValidationController;

	/** The empresa request validation controller. */
	private ValidationController empresaRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the empresa bcl.
	 *
	 * @return the empresa bcl
	 */
	public IEmpresaBCL getEmpresaBCL()
	{
		return this.empresaBCL;
	}

	/**
	 * Sets the empresa bcl.
	 *
	 * @param empresaBCLObject the new empresa bcl
	 */
	public void setEmpresaBCL(IEmpresaBCL empresaBCLObject)
	{
		this.empresaBCL = empresaBCLObject;
	}

	/**
	 * Gets the empresa validation controller.
	 *
	 * @return the empresa validation controller
	 */
	public ValidationController getEmpresaValidationController()
	{
		return this.empresaValidationController;
	}

	/**
	 * Sets the empresa validation controller.
	 *
	 * @param empresaValidationController the new empresa validation controller
	 */
	public void setEmpresaValidationController(ValidationController empresaValidationController)
	{
		this.empresaValidationController = empresaValidationController;
	}

	/**
	 * Gets the empresa list validation controller.
	 *
	 * @return the empresa list validation controller
	 */
	public ValidationController getEmpresaListValidationController()
	{
		return this.empresaListValidationController;
	}

	/**
	 * Sets the empresa list validation controller.
	 *
	 * @param empresaListValidationController the new empresa list validation controller
	 */
	public void setEmpresaListValidationController(ValidationController empresaListValidationController)
	{
		this.empresaListValidationController = empresaListValidationController;
	}

	/**
	 * Gets the empresa request validation controller.
	 *
	 * @return the empresa request validation controller
	 */
	public ValidationController getEmpresaRequestValidationController()
	{
		return this.empresaRequestValidationController;
	}

	/**
	 * Sets the empresa request validation controller.
	 *
	 * @param empresaRequestValidationController the new empresa request validation controller
	 */
	public void setEmpresaRequestValidationController(ValidationController empresaRequestValidationController)
	{
		this.empresaRequestValidationController = empresaRequestValidationController;
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
	 * @see com.sensus.mlc.empresa.bcf.IEmpresaBCF#fetchAllEmpresas(com.sensus.mlc.empresa.model.request.InquiryEmpresaRequest
	 */
	@Override
	public InquiryEmpresaResponse fetchAllEmpresa(InquiryEmpresaRequest inquiryEmpresaRequest)
	{
		InquiryEmpresaResponse response = new InquiryEmpresaResponse();
		InternalResultsResponse<Empresa> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_EMPRESA_REQUEST_NAME, inquiryEmpresaRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getEmpresaBCL().fetchAllEmpresa(inquiryEmpresaRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.bcf.IEmpresaBCF#insertEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public EmpresaResponse insertEmpresa(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		InternalResultsResponse<Empresa> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(EMPRESA_REQUEST_NAME, empresaRequest);
			if (getLightingControlRequestValidationController().validate(context)
					&& getEmpresaValidationController().validate(context))
			{
				if (getLightListValidationController().validate(context))
				{
					internalResponse = getEmpresaBCL().insertEmpresa(empresaRequest);
					response.setEmpresa(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.bcf.IEmpresaBCF#deleteEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest)
	 */
	@Override
	public EmpresaResponse deleteEmpresa(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(EMPRESA_REQUEST_NAME, empresaRequest);

			if (getLightingControlRequestValidationController().validate(context)
					&& getEmpresaValidationController().validate(context))
			{
				internalResponse = getEmpresaBCL().deleteEmpresa(empresaRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.empresa.bcf.IEmpresaBCF#updateEmpresa(com.sensus.mlc.empresa.model.request.EmpresaRequest
	 */
	@Override
	public EmpresaResponse updateEmpresa(EmpresaRequest empresaRequest)
	{
		EmpresaResponse response = new EmpresaResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(EMPRESA_REQUEST_NAME, empresaRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getEmpresaRequestValidationController().validate(context) &&
					getEmpresaValidationController().validate(context))
			{
				internalResponse = getEmpresaBCL().updateEmpresa(empresaRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EMPRESA_BCF_EXCEPTION_MSG);
		}

		return response;
	}

		@Override
		public EmpresaResponse fetchEmpresaById(EmpresaRequest empresaRequest) {
			// TODO Auto-generated method stub
			return null;
		}
}
