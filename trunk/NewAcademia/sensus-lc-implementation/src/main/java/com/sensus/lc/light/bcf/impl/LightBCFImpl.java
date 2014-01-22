package com.sensus.lc.light.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.MaintenanceResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightOrderByEnum;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.light.model.response.ChangesResponse;
import com.sensus.lc.light.model.response.ConfigurationMaintenanceResponse;
import com.sensus.lc.light.model.response.CountResponse;
import com.sensus.lc.light.model.response.FetchAllResponse;
import com.sensus.lc.light.model.response.GeocodeLightInfoResponse;
import com.sensus.lc.light.model.response.LastOperationalDataMaintenanceResponse;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.light.model.response.LightingConfigurationsResponse;
import com.sensus.lc.light.model.response.ScheduleMaintenanceResponse;

/**
 * The Class LightBCFImpl.
 */
public class LightBCFImpl implements ILightBCF
{
	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The light bcl. */
	private ILightBCL lightBCL;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/** The light2 validation controller. */
	private ValidationController lightValidationController;

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightBCFImpl.class);

	/** The Constant DEFAULT_LIGHT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.lightbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_RNI_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_RNI_REQUIRED = "sensus.mlc.validator.id.rni.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED = "sensus.mlc.validator.parent.id.required";

	/** The Constant LIGHT_MAINTENANCE_REQUEST_NAME. */
	private static final String LIGHT_MAINTENANCE_REQUEST_NAME = LightMaintenanceRequest.class.getSimpleName();

	/** The Constant LIGHT_MASS_UPDATE_REQUEST_NAME. */
	private static final String LIGHT_MASS_UPDATE_REQUEST_NAME = LightMassUpdateRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Gets the light bcl.
	 * 
	 * @return the lightBCL
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * Gets the light2 validation controller.
	 * 
	 * @return the light2 validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return lightValidationController;
	}

	/**
	 * Gets the range date validation controller.
	 * 
	 * @return the range date validation controller
	 */
	public ValidationController getRangeDateValidationController()
	{
		return rangeDateValidationController;
	}

	/**
	 * Gets the request validation controller.
	 * 
	 * @return the request validation controller
	 */
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#countAllByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public CountResponse countAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Integer> response = null;
		CountResponse countResponse = new CountResponse();

		try
		{
			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				response = getLightBCL().countAllByRequest(request);

				if (!ValidationUtil.isNull(response) && !response.isInError())
				{
					countResponse.setCount(response.getFirstResult());
				}
			}

			handleOperationStatusAndMessages(countResponse, response, context.getMessages(), false);

		}
		catch (Exception ex)
		{

			handleException(LOG, countResponse, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

		}

		return countResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcf.ILightBCF#fetchAttributeChanges(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public ChangesResponse fetchAttributeChanges(LightRequest request)
	{
		InternalResultsResponse<BigInteger> lightResponse = null;
		ChangesResponse response = new ChangesResponse();

		try
		{
			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			checkDefaultsFetchAll(request);
			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				lightResponse = getLightBCL().fetchAttributeChanges(request);
			}

			handleOperationStatusAndMessages(response, lightResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#fetchAllByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public FetchAllResponse fetchAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Light> lightResponse = null;
		FetchAllResponse response = new FetchAllResponse();

		try
		{
			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH);

			checkDefaultsFetchAll(request);
			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination
			{
				lightResponse = getLightBCL().fetchAllByRequest(request);
			}

			handleOperationStatusAndMessages(response, lightResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{

			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#fetchAllToMapByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public GeocodeLightInfoResponse fetchAllToMapByRequest(LightRequest request)
	{
		GeocodeLightInfoResponse response = new GeocodeLightInfoResponse();
		InternalResultsResponse<GeocodeLightInfo> internalResponse =
				new InternalResultsResponse<GeocodeLightInfo>();

		try
		{
			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_LIGHT_MAP);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightBCL().fetchAllToMapByRequest(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{

			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#fetchById(com.sensus.mlc.light.model.request.FetchByIdRequest)
	 */
	@Override
	public LightResponse fetchById(FetchByIdRequest request)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResultsResponse<Light> lightResponse = null;

			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_BY_ID);

			if (!getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				handleOperationStatusAndMessages(response, lightResponse, context.getMessages(), false);
				return response;
			}

			if (ValidationUtil.isNull(request.getLightId()) && ValidationUtil.isNull(request.getRniId()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_ID_RNI_REQUIRED));
				return response;
			}

			lightResponse = getLightBCL().fetchById(request);
			response.setLight(lightResponse.getFirstResult());
			handleOperationStatusAndMessages(response, lightResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#fetchMapBoundsByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public GeocodeLightInfoResponse fetchMapBoundsByRequest(LightRequest request)
	{
		GeocodeLightInfoResponse response = new GeocodeLightInfoResponse();
		InternalResultsResponse<GeocodeLightInfo> internalResponse =
				new InternalResultsResponse<GeocodeLightInfo>();

		try
		{
			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_LIGHT_MAP);

			if (getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				internalResponse = getLightBCL().fetchMapBoundsByRequest(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{

			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#fetchPartNumberConfigurationsByModelNumber(java.lang.String)
	 */
	@Override
	public LightingConfigurationsResponse fetchPartNumberConfigurationsByModelNumber(String modelNumber)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#resetMinMaxValue(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public LightResponse resetMinMaxValue(LightRequest request)
	{
		LightResponse response = new LightResponse();
		InternalResultsResponse<Light> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext(
					LightRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.RESET_MIN_MAX);
			checkDefaultsFetchAll(request);
			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getInquiryRequestValidationController().validate(context)) // Validate pagination

			{
				internalResponse = getLightBCL().resetMinMaxValue(request);
				response.setLights(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{

			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/**
	 * Sets the inquiry request validation controller.
	 * 
	 * @param inquiryRequestValidationController the new inquiry request validation controller
	 */
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/**
	 * Sets the light bcl.
	 * 
	 * @param lightBCL the lightBCL to set
	 */
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Sets the light2 validation controller.
	 * 
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Sets the range date validation controller.
	 * 
	 * @param rangeDateValidationController the new range date validation controller
	 */
	public void setRangeDateValidationController(ValidationController rangeDateValidationController)
	{
		this.rangeDateValidationController = rangeDateValidationController;
	}

	/**
	 * Sets the request validation controller.
	 * 
	 * @param requestValidationController the new request validation controller
	 */
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#updateConfiguration(com.sensus.mlc.light.model.request.
	 * ConfigurationMaintenanceRequest)
	 */
	@Override
	public ConfigurationMaintenanceResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		ConfigurationMaintenanceResponse response = new ConfigurationMaintenanceResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext(
					ConfigurationMaintenanceRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_SCHEDULE);

			if (!getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
				return response;
			}

			if (ValidationUtil.isNull(request.getConfiguration())
					|| ValidationUtil.isNull(request.getConfiguration().getParentId()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED));
				return response;
			}

			internalResponse = getLightBCL().updateConfiguration(request);
			handleOperationStatusAndMessages(response, internalResponse, null, true);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcf.ILightBCF#updateLastOperationalData(com.sensus.mlc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public LastOperationalDataMaintenanceResponse updateLastOperationalData(
			LastOperationalDataMaintenanceRequest request)
	{
		LastOperationalDataMaintenanceResponse response = new LastOperationalDataMaintenanceResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext(
					LastOperationalDataMaintenanceRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_LAST_OPERATIONAL_DATA);

			if (!getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
				return response;
			}

			if (ValidationUtil.isNull(request.getLastOperationalData())
					|| ValidationUtil.isNull(request.getLastOperationalData().getParentId()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED));
				return response;
			}

			internalResponse = getLightBCL().updateLastOperationalData(request);
			handleOperationStatusAndMessages(response, internalResponse, null, true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightBCF#updateLightBase(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public MaintenanceResponse updateLightBase(LightMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LIGHT_MAINTENANCE_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getLightValidationController().validate(context)) // Validate light attributes
			{
				internalResponse = getLightBCL().updateLightBase(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightBCF#updateLightMass(com.sensus.mlc.light.model.request.LightMassUpdateRequest)
	 */
	@Override
	public MaintenanceResponse updateLightMass(LightMassUpdateRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LIGHT_MASS_UPDATE_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getRequestValidationController().validate(context) // Validate Tenant and UserContext
					&& getLightValidationController().validate(context)) // Validate light attributes
			{
				internalResponse = getLightBCL().updateLightMass(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightBCF#updateSchedule(com.sensus.mlc.light.model.request.ScheduleMaintenanceRequest)
	 */
	@Override
	public ScheduleMaintenanceResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		ScheduleMaintenanceResponse response = new ScheduleMaintenanceResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext(
					ScheduleMaintenanceRequest.class.getSimpleName(),
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_SCHEDULE);

			if (!getRequestValidationController().validate(context)) // Validate Tenant and UserContext
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
				return response;
			}

			if (ValidationUtil.isNull(request.getSchedule())
					|| ValidationUtil.isNull(request.getSchedule().getParentId()))
			{
				response.addMessage(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED));
				return response;
			}

			internalResponse = getLightBCL().updateSchedule(request);
			handleOperationStatusAndMessages(response, internalResponse, null, true);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Check defaults fetch all.
	 * 
	 * @param inquiryRequest the inquiry request
	 */
	private void checkDefaultsFetchAll(InquiryRequest inquiryRequest)
	{
		if (ValidationUtil.isNull(inquiryRequest.getPageSize())
				|| inquiryRequest.getPageSize().equals(TWENTY))
		{
			inquiryRequest.setPageSize(new Integer(0));
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryRequest.getSortExpressions()))
		{
			return;
		}

		SortExpression sortExpression = new SortExpression();
		sortExpression.setField(LightOrderByEnum.ALERTS.getValue());
		sortExpression.setDirection(Direction.Descending);
		inquiryRequest.addSortExpressions(sortExpression);
	}

}
