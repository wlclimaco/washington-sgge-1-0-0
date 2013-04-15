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
import com.sensus.mlc.gestao.bcf.IMunicipioBCF;
import com.sensus.mlc.gestao.bcl.IMunicipioBCL;
import com.sensus.mlc.gestao.model.Municipio;
import com.sensus.mlc.gestao.model.request.InquiryMunicipioRequest;
import com.sensus.mlc.gestao.model.request.MunicipioRequest;
import com.sensus.mlc.gestao.model.response.InquiryMunicipioResponse;
import com.sensus.mlc.gestao.model.response.MunicipioResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class MunicipioBCFImpl.
 *
 * @author - Washington
 */
public class MunicipioBCFImpl extends AbstractBaseBCF implements IMunicipioBCF
{
	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant INQUIRY_MUNICIPIO_REQUEST_NAME. */
	private static final String INQUIRY_MUNICIPIO_REQUEST_NAME = InquiryMunicipioRequest.class.getSimpleName();

	/** The Constant MUNICIPIO_REQUEST_NAME. */
	private static final String MUNICIPIO_REQUEST_NAME = MunicipioRequest.class.getSimpleName();

	/** The Constant MUNICIPIO_NAME. */
	private static final String MUNICIPIO_NAME = Municipio.class.getSimpleName();

	/** The Constant DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG = "sensus.mlc.municipiobcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MunicipioBCFImpl.class);

	/** The municipio bcl. */
	private IMunicipioBCL municipioBCL;

	/** The municipio validation controller. */
	private ValidationController municipioValidationController;

	/** The municipio list validation controller. */
	private ValidationController municipioListValidationController;

	/** The municipio request validation controller. */
	private ValidationController municipioRequestValidationController;

	/** The light request validation controller. */
	private ValidationController lightValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/**
	 * Gets the municipio bcl.
	 *
	 * @return the municipio bcl
	 */
	public IMunicipioBCL getMunicipioBCL()
	{
		return this.municipioBCL;
	}

	/**
	 * Sets the municipio bcl.
	 *
	 * @param municipioBCLObject the new municipio bcl
	 */
	public void setMunicipioBCL(IMunicipioBCL municipioBCLObject)
	{
		this.municipioBCL = municipioBCLObject;
	}

	/**
	 * Gets the municipio validation controller.
	 *
	 * @return the municipio validation controller
	 */
	public ValidationController getMunicipioValidationController()
	{
		return this.municipioValidationController;
	}

	/**
	 * Sets the municipio validation controller.
	 *
	 * @param municipioValidationController the new municipio validation controller
	 */
	public void setMunicipioValidationController(ValidationController municipioValidationController)
	{
		this.municipioValidationController = municipioValidationController;
	}

	/**
	 * Gets the municipio list validation controller.
	 *
	 * @return the municipio list validation controller
	 */
	public ValidationController getMunicipioListValidationController()
	{
		return this.municipioListValidationController;
	}

	/**
	 * Sets the municipio list validation controller.
	 *
	 * @param municipioListValidationController the new municipio list validation controller
	 */
	public void setMunicipioListValidationController(ValidationController municipioListValidationController)
	{
		this.municipioListValidationController = municipioListValidationController;
	}

	/**
	 * Gets the municipio request validation controller.
	 *
	 * @return the municipio request validation controller
	 */
	public ValidationController getMunicipioRequestValidationController()
	{
		return this.municipioRequestValidationController;
	}

	/**
	 * Sets the municipio request validation controller.
	 *
	 * @param municipioRequestValidationController the new municipio request validation controller
	 */
	public void setMunicipioRequestValidationController(ValidationController municipioRequestValidationController)
	{
		this.municipioRequestValidationController = municipioRequestValidationController;
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
	 * @see com.sensus.mlc.municipio.bcf.IMunicipioBCF#fetchAllMunicipios(com.sensus.mlc.municipio.model.request.InquiryMunicipioRequest
	 */
	@Override
	public InquiryMunicipioResponse fetchAllMunicipio(InquiryMunicipioRequest inquiryMunicipioRequest)
	{
		InquiryMunicipioResponse response = new InquiryMunicipioResponse();
		InternalResultsResponse<Municipio> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_MUNICIPIO_REQUEST_NAME, inquiryMunicipioRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getMunicipioBCL().fetchAllMunicipio(inquiryMunicipioRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcf.IMunicipioBCF#fetchMunicipioByName(com.sensus.mlc.municipio.model.request.MunicipioRequest
	 */
	@Override
	public MunicipioResponse fetchMunicipioById(MunicipioRequest municipioRequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		InternalResultsResponse<Municipio> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					FETCH_BY_NAME);
			context.putObjectToBeValidated(MUNICIPIO_REQUEST_NAME, municipioRequest);
			context.putObjectToBeValidated(MUNICIPIO_NAME, municipioRequest.getMunicipio());

			if (getLightingControlRequestValidationController().validate(context)
					&& getMunicipioValidationController().validate(context))
			{
				internalResponse = getMunicipioBCL().fetchMunicipioById(municipioRequest);
				response.setMunicipio(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcf.IMunicipioBCF#insertMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */
	@Override
	public MunicipioResponse insertMunicipio(MunicipioRequest municipioRequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		InternalResultsResponse<Municipio> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(MUNICIPIO_REQUEST_NAME, municipioRequest);
			context.putObjectToBeValidated(MUNICIPIO_NAME, municipioRequest.getMunicipio());

			if (getLightingControlRequestValidationController().validate(context)
					&& getMunicipioValidationController().validate(context))
			{



				if (getLightListValidationController().validate(context))
				{
					internalResponse = getMunicipioBCL().insertMunicipio(municipioRequest);
					response.setMunicipio(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcf.IMunicipioBCF#deleteMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest)
	 */
	@Override
	public MunicipioResponse deleteMunicipio(MunicipioRequest municipioRequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					DELETE);
			context.putObjectToBeValidated(MUNICIPIO_REQUEST_NAME, municipioRequest);
			context.putObjectToBeValidated(MUNICIPIO_NAME, municipioRequest.getMunicipio());

			if (getLightingControlRequestValidationController().validate(context)
					&& getMunicipioValidationController().validate(context))
			{
				internalResponse = getMunicipioBCL().deleteMunicipio(municipioRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG);
		}
		return response;
	}

		/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.municipio.bcf.IMunicipioBCF#updateMunicipio(com.sensus.mlc.municipio.model.request.MunicipioRequest
	 */
	@Override
	public MunicipioResponse updateMunicipio(MunicipioRequest municipioRequest)
	{
		MunicipioResponse response = new MunicipioResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(MUNICIPIO_REQUEST_NAME, municipioRequest);
			context.putObjectToBeValidated(MUNICIPIO_NAME, municipioRequest.getMunicipio());

			if (getLightingControlRequestValidationController().validate(context) &&
					getMunicipioRequestValidationController().validate(context) &&
					getMunicipioValidationController().validate(context))
			{
				internalResponse = getMunicipioBCL().updateMunicipio(municipioRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_MUNICIPIO_BCF_EXCEPTION_MSG);
		}

		return response;
	}
}
