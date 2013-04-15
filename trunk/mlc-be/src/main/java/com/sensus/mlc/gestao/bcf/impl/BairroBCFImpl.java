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
import com.sensus.mlc.gestao.bcf.IBairroBCF;
import com.sensus.mlc.gestao.bcl.IBairroBCL;
import com.sensus.mlc.gestao.model.Bairro;
import com.sensus.mlc.gestao.model.request.BairroRequest;
import com.sensus.mlc.gestao.model.request.InquiryBairroRequest;
import com.sensus.mlc.gestao.model.response.BairroResponse;
import com.sensus.mlc.gestao.model.response.InquiryBairroResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class BairroBCFImpl.
 *
 * @author - Washington
 */
public class BairroBCFImpl extends AbstractBaseBCF implements IBairroBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_BAIRRO_REQUEST_NAME. */
	private static final String INQUIRY_BAIRRO_REQUEST_NAME = InquiryBairroRequest.class.getSimpleName();

	/** The Constant BAIRRO_REQUEST_NAME. */
	private static final String BAIRRO_REQUEST_NAME = BairroRequest.class.getSimpleName();

	/** The Constant BAIRRO_NAME. */
	private static final String BAIRRO_NAME = Bairro.class.getSimpleName();

	/** The Constant DEFAULT_BAIRRO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_BAIRRO_BCF_EXCEPTION_MSG = "sensus.mlc.bairrobcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(BairroBCFImpl.class);

	/** The bairro bcl. */
	private IBairroBCL bairroBCL;

	/** The bairro validation controller. */
	private ValidationController bairroValidationController;

	/** The bairro list validation controller. */
	private ValidationController bairroListValidationController;

	/** The bairro request validation controller. */
	private ValidationController bairroRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the bairro bcl.
	 *
	 * @return the bairro bcl
	 */
	public IBairroBCL getBairroBCL()
	{
		return this.bairroBCL;
	}

	/**
	 * Sets the bairro bcl.
	 *
	 * @param bairroBCLObject the new bairro bcl
	 */
	public void setBairroBCL(IBairroBCL bairroBCLObject)
	{
		this.bairroBCL = bairroBCLObject;
	}

	/**
	 * Gets the bairro validation controller.
	 *
	 * @return the bairro validation controller
	 */
	public ValidationController getBairroValidationController()
	{
		return this.bairroValidationController;
	}

	/**
	 * Sets the bairro validation controller.
	 *
	 * @param bairroValidationController the new bairro validation controller
	 */
	public void setBairroValidationController(ValidationController bairroValidationController)
	{
		this.bairroValidationController = bairroValidationController;
	}

	/**
	 * Gets the bairro list validation controller.
	 *
	 * @return the bairro list validation controller
	 */
	public ValidationController getBairroListValidationController()
	{
		return this.bairroListValidationController;
	}

	/**
	 * Sets the bairro list validation controller.
	 *
	 * @param bairroListValidationController the new bairro list validation controller
	 */
	public void setBairroListValidationController(ValidationController bairroListValidationController)
	{
		this.bairroListValidationController = bairroListValidationController;
	}

	/**
	 * Gets the bairro request validation controller.
	 *
	 * @return the bairro request validation controller
	 */
	public ValidationController getBairroRequestValidationController()
	{
		return this.bairroRequestValidationController;
	}

	/**
	 * Sets the bairro request validation controller.
	 *
	 * @param bairroRequestValidationController the new bairro request validation controller
	 */
	public void setBairroRequestValidationController(ValidationController bairroRequestValidationController)
	{
		this.bairroRequestValidationController = bairroRequestValidationController;
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
	 * @see com.sensus.mlc.bairro.bcf.IBairroBCF#fetchAllBairros(com.sensus.mlc.bairro.model.request.InquiryBairroRequest
	 */
	@Override
	public InquiryBairroResponse fetchAllBairro(InquiryBairroRequest inquiryBairroRequest)
	{
		InquiryBairroResponse response = new InquiryBairroResponse();
		InternalResultsResponse<Bairro> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_BAIRRO_REQUEST_NAME, inquiryBairroRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getBairroBCL().fetchAllBairro(inquiryBairroRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_BAIRRO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcf.IBairroBCF#fetchBairroByName(com.sensus.mlc.bairro.model.request.BairroRequest
	 */
	@Override
	public BairroResponse fetchBairroById(BairroRequest bairroRequest)
	{
		BairroResponse response = new BairroResponse();
		InternalResultsResponse<Bairro> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(BAIRRO_REQUEST_NAME, bairroRequest);
			context.putObjectToBeValidated(BAIRRO_NAME, bairroRequest.getBairro());

			if (getLightingControlRequestValidationController().validate(context)
					&& getBairroValidationController().validate(context))
			{
				internalResponse = getBairroBCL().fetchBairroById(bairroRequest);
				response.setBairro(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_BAIRRO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcf.IBairroBCF#insertBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public BairroResponse insertBairro(BairroRequest bairroRequest)
	{
		BairroResponse response = new BairroResponse();
		InternalResultsResponse<Bairro> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(BAIRRO_REQUEST_NAME, bairroRequest);
			context.putObjectToBeValidated(BAIRRO_NAME, bairroRequest.getBairro());

			if (getLightingControlRequestValidationController().validate(context)
					&& getBairroValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getBairroBCL().insertBairro(bairroRequest);
					response.setBairro(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_BAIRRO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcf.IBairroBCF#deleteBairro(com.sensus.mlc.bairro.model.request.BairroRequest)
	 */
	@Override
	public BairroResponse deleteBairro(BairroRequest bairroRequest)
	{
		BairroResponse response = new BairroResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(BAIRRO_REQUEST_NAME, bairroRequest);
			context.putObjectToBeValidated(BAIRRO_NAME, bairroRequest.getBairro());

			if (getLightingControlRequestValidationController().validate(context)
					&& getBairroValidationController().validate(context))
			{
				internalResponse = getBairroBCL().deleteBairro(bairroRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_BAIRRO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.bairro.bcf.IBairroBCF#updateBairro(com.sensus.mlc.bairro.model.request.BairroRequest
	 */
	@Override
	public BairroResponse updateBairro(BairroRequest bairroRequest)
	{
		BairroResponse response = new BairroResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(BAIRRO_REQUEST_NAME, bairroRequest);
			context.putObjectToBeValidated(BAIRRO_NAME, bairroRequest.getBairro());

			if (getLightingControlRequestValidationController().validate(context) &&
					getBairroRequestValidationController().validate(context) &&
					getBairroValidationController().validate(context))
			{
				internalResponse = getBairroBCL().updateBairro(bairroRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_BAIRRO_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
