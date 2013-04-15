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
import com.sensus.mlc.gestao.bcf.ITipclienteBCF;
import com.sensus.mlc.gestao.bcl.ITipclienteBCL;
import com.sensus.mlc.gestao.model.Tipcliente;
import com.sensus.mlc.gestao.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.gestao.model.request.TipclienteRequest;
import com.sensus.mlc.gestao.model.response.InquiryTipclienteResponse;
import com.sensus.mlc.gestao.model.response.TipclienteResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class TipclienteBCFImpl.
 *
 * @author - Washington
 */
public class TipclienteBCFImpl extends AbstractBaseBCF implements ITipclienteBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_Tipcliente_REQUEST_NAME. */
	private static final String INQUIRY_Tipcliente_REQUEST_NAME = InquiryTipclienteRequest.class.getSimpleName();

	/** The Constant Tipcliente_REQUEST_NAME. */
	private static final String Tipcliente_REQUEST_NAME = TipclienteRequest.class.getSimpleName();

	/** The Constant Tipcliente_NAME. */
	private static final String Tipcliente_NAME = Tipcliente.class.getSimpleName();

	/** The Constant DEFAULT_Tipcliente_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_Tipcliente_BCF_EXCEPTION_MSG = "sensus.mlc.Tipclientebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TipclienteBCFImpl.class);

	/** The Tipcliente bcl. */
	private ITipclienteBCL TipclienteBCL;

	/** The Tipcliente validation controller. */
	private ValidationController TipclienteValidationController;

	/** The Tipcliente list validation controller. */
	private ValidationController TipclienteListValidationController;

	/** The Tipcliente request validation controller. */
	private ValidationController TipclienteRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the Tipcliente bcl.
	 *
	 * @return the Tipcliente bcl
	 */
	public ITipclienteBCL getTipclienteBCL()
	{
		return this.TipclienteBCL;
	}

	/**
	 * Sets the Tipcliente bcl.
	 *
	 * @param TipclienteBCLObject the new Tipcliente bcl
	 */
	public void setTipclienteBCL(ITipclienteBCL TipclienteBCLObject)
	{
		this.TipclienteBCL = TipclienteBCLObject;
	}

	/**
	 * Gets the Tipcliente validation controller.
	 *
	 * @return the Tipcliente validation controller
	 */
	public ValidationController getTipclienteValidationController()
	{
		return this.TipclienteValidationController;
	}

	/**
	 * Sets the Tipcliente validation controller.
	 *
	 * @param TipclienteValidationController the new Tipcliente validation controller
	 */
	public void setTipclienteValidationController(ValidationController TipclienteValidationController)
	{
		this.TipclienteValidationController = TipclienteValidationController;
	}

	/**
	 * Gets the Tipcliente list validation controller.
	 *
	 * @return the Tipcliente list validation controller
	 */
	public ValidationController getTipclienteListValidationController()
	{
		return this.TipclienteListValidationController;
	}

	/**
	 * Sets the Tipcliente list validation controller.
	 *
	 * @param TipclienteListValidationController the new Tipcliente list validation controller
	 */
	public void setTipclienteListValidationController(ValidationController TipclienteListValidationController)
	{
		this.TipclienteListValidationController = TipclienteListValidationController;
	}

	/**
	 * Gets the Tipcliente request validation controller.
	 *
	 * @return the Tipcliente request validation controller
	 */
	public ValidationController getTipclienteRequestValidationController()
	{
		return this.TipclienteRequestValidationController;
	}

	/**
	 * Sets the Tipcliente request validation controller.
	 *
	 * @param TipclienteRequestValidationController the new Tipcliente request validation controller
	 */
	public void setTipclienteRequestValidationController(ValidationController TipclienteRequestValidationController)
	{
		this.TipclienteRequestValidationController = TipclienteRequestValidationController;
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
	 * @see com.sensus.mlc.Tipcliente.bcf.ITipclienteBCF#fetchAllTipclientes(com.sensus.mlc.Tipcliente.model.request.InquiryTipclienteRequest
	 */
	@Override
	public InquiryTipclienteResponse fetchAllTipcliente(InquiryTipclienteRequest inquiryTipclienteRequest)
	{
		InquiryTipclienteResponse response = new InquiryTipclienteResponse();
		InternalResultsResponse<Tipcliente> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_Tipcliente_REQUEST_NAME, inquiryTipclienteRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getTipclienteBCL().fetchAllTipcliente(inquiryTipclienteRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_Tipcliente_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Tipcliente.bcf.ITipclienteBCF#fetchTipclienteByName(com.sensus.mlc.Tipcliente.model.request.TipclienteRequest
	 */
	@Override
	public TipclienteResponse fetchTipclienteById(TipclienteRequest TipclienteRequest)
	{
		TipclienteResponse response = new TipclienteResponse();
		InternalResultsResponse<Tipcliente> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(Tipcliente_REQUEST_NAME, TipclienteRequest);
			context.putObjectToBeValidated(Tipcliente_NAME, TipclienteRequest.getTipcliente());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTipclienteValidationController().validate(context))
			{
				internalResponse = getTipclienteBCL().fetchTipclienteById(TipclienteRequest);
				response.setTipcliente(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_Tipcliente_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Tipcliente.bcf.ITipclienteBCF#insertTipcliente(com.sensus.mlc.Tipcliente.model.request.TipclienteRequest)
	 */
	@Override
	public TipclienteResponse insertTipcliente(TipclienteRequest TipclienteRequest)
	{
		TipclienteResponse response = new TipclienteResponse();
		InternalResultsResponse<Tipcliente> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(Tipcliente_REQUEST_NAME, TipclienteRequest);
			context.putObjectToBeValidated(Tipcliente_NAME, TipclienteRequest.getTipcliente());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTipclienteValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getTipclienteBCL().insertTipcliente(TipclienteRequest);
					response.setTipcliente(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_Tipcliente_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Tipcliente.bcf.ITipclienteBCF#deleteTipcliente(com.sensus.mlc.Tipcliente.model.request.TipclienteRequest)
	 */
	@Override
	public TipclienteResponse deleteTipcliente(TipclienteRequest TipclienteRequest)
	{
		TipclienteResponse response = new TipclienteResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(Tipcliente_REQUEST_NAME, TipclienteRequest);
			context.putObjectToBeValidated(Tipcliente_NAME, TipclienteRequest.getTipcliente());

			if (getLightingControlRequestValidationController().validate(context)
					&& getTipclienteValidationController().validate(context))
			{
				internalResponse = getTipclienteBCL().deleteTipcliente(TipclienteRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_Tipcliente_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.Tipcliente.bcf.ITipclienteBCF#updateTipcliente(com.sensus.mlc.Tipcliente.model.request.TipclienteRequest
	 */
	@Override
	public TipclienteResponse updateTipcliente(TipclienteRequest TipclienteRequest)
	{
		TipclienteResponse response = new TipclienteResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(Tipcliente_REQUEST_NAME, TipclienteRequest);
			context.putObjectToBeValidated(Tipcliente_NAME, TipclienteRequest.getTipcliente());

			if (getLightingControlRequestValidationController().validate(context) &&
					getTipclienteRequestValidationController().validate(context) &&
					getTipclienteValidationController().validate(context))
			{
				internalResponse = getTipclienteBCL().updateTipcliente(TipclienteRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_Tipcliente_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
