package com.sensus.mlc.smartpoint.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE_ALERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_ALARM;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_SMARTPOINT_PROPERTIES;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_BINDING;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_CHANNEL_SETUP_AUDIT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_FORCED_STATUS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_SETUP;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_SET_LIGHT_INTENSITY;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_UNSOLICITED_STATUS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_INTENSITY;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_STATUS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPSERT_LIGHT_PROPERTY;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.response.AlarmNotificationResponse;

/**
 * The Class SmartPointProcessorBCFImpl.
 */
public class SmartPointProcessorBCFImpl extends AbstractBaseBCF implements ISmartPointProcessorBCF
{
	/** The Constant CHANNEL_SETUP_AUDIT_REQUEST_NAME. */
	private static final String CHANNEL_SETUP_AUDIT_REQUEST_NAME = ChannelSetupAuditRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant FORCED_LIGHT_STATUS_NOTIFICATION_REQUEST_NAME. */
	private static final String FORCED_LIGHT_STATUS_NOTIFICATION_REQUEST_NAME =
			ForcedLightStatusNotificationRequest.class.getSimpleName();

	/** The Constant ALARM_NOTIFICATION_REQUEST_NAME. */
	private static final String ALARM_NOTIFICATION_REQUEST_NAME = AlarmNotificationRequest.class.getSimpleName();

	/** The Constant LIGHT_STATUS_NOTIFICATION_REQUEST_NAME. */
	private static final String LIGHT_STATUS_NOTIFICATION_REQUEST_NAME = LightStatusNotificationRequest.class
			.getSimpleName();

	/** The Constant PROCESS_LIGHTS_REQUEST_NAME. */
	private static final String PROCESS_LIGHTS_REQUEST_NAME = ProcessLightsRequest.class.getSimpleName();

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.smartpointbcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SmartPointProcessorBCFImpl.class);

	/** The smart point processor bcl. */
	private ISmartPointProcessorBCL smartPointProcessorBCL; // injected by Spring through setter

	/** The light validation controller. */
	private ValidationController lightValidationController;

	/** The channel setup audit request validation controller. */
	private ValidationController channelSetupAuditRequestValidationController;

	/** The alarm notification request validation controller. */
	private ValidationController alarmNotificationRequestValidationController;

	/** The forced light status notification request validation controller. */
	private ValidationController forcedLightStatusNotificationRequestValidationController;

	/** The light status notification request validation controller. */
	private ValidationController lightStatusNotificationRequestValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/** The properties validation controller. */
	private ValidationController propertiesValidationController;

	/** The light intensity validation controller. */
	private ValidationController lightIntensityValidationController;

	/**
	 * Gets the channel setup audit request validation controller.
	 *
	 * @return the channel setup audit request validation controller
	 */
	public ValidationController getChannelSetupAuditRequestValidationController()
	{
		return channelSetupAuditRequestValidationController;
	}

	/**
	 * Sets the channel setup audit request validation controller.
	 *
	 * @param channelSetupAuditRequestValidationController the new channel setup audit request validation controller
	 */
	public void setChannelSetupAuditRequestValidationController(
			ValidationController channelSetupAuditRequestValidationController)
	{
		this.channelSetupAuditRequestValidationController = channelSetupAuditRequestValidationController;
	}

	/**
	 * Gets the alarm notification request validation controller.
	 *
	 * @return the alarm notification request validation controller
	 */
	public ValidationController getAlarmNotificationRequestValidationController()
	{
		return alarmNotificationRequestValidationController;
	}

	/**
	 * Sets the alarm notification request validation controller.
	 *
	 * @param alarmNotificationRequestValidationController the new alarm notification request validation controller
	 */
	public void setAlarmNotificationRequestValidationController(
			ValidationController alarmNotificationRequestValidationController)
	{
		this.alarmNotificationRequestValidationController = alarmNotificationRequestValidationController;
	}

	/**
	 * Gets the forced light status notification request validation controller.
	 *
	 * @return the forced light status notification request validation controller
	 */
	public ValidationController getForcedLightStatusNotificationRequestValidationController()
	{
		return forcedLightStatusNotificationRequestValidationController;
	}

	/**
	 * Sets the forced light status notification request validation controller.
	 *
	 * @param forcedLightStatusNotificationRequestValidationController the new forced light status notification request
	 *            validation controller
	 */
	public void setForcedLightStatusNotificationRequestValidationController(
			ValidationController forcedLightStatusNotificationRequestValidationController)
	{
		this.forcedLightStatusNotificationRequestValidationController =
				forcedLightStatusNotificationRequestValidationController;
	}

	/**
	 * Gets the light status notification request validation controller.
	 *
	 * @return the light status notification request validation controller
	 */
	public ValidationController getLightStatusNotificationRequestValidationController()
	{
		return lightStatusNotificationRequestValidationController;
	}

	/**
	 * Sets the light status notification request validation controller.
	 *
	 * @param lightStatusNotificationRequestValidationController the new light status notification request validation
	 *            controller
	 */
	public void setLightStatusNotificationRequestValidationController(
			ValidationController lightStatusNotificationRequestValidationController)
	{
		this.lightStatusNotificationRequestValidationController = lightStatusNotificationRequestValidationController;
	}

	/**
	 * Gets the light list validation controller.
	 *
	 * @return the light list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return lightListValidationController;
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

	/**
	 * Gets the light validation controller.
	 *
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return lightValidationController;
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
	 * Gets the smart point processor bcl.
	 *
	 * @return the smart point processor bcl
	 */
	public ISmartPointProcessorBCL getSmartPointProcessorBCL()
	{
		return smartPointProcessorBCL;
	}

	/**
	 * Sets the smart point processor bcl.
	 *
	 * @param smartPointProcessorBCL the new smart point processor bcl
	 */
	public void setSmartPointProcessorBCL(ISmartPointProcessorBCL smartPointProcessorBCL)
	{
		this.smartPointProcessorBCL = smartPointProcessorBCL;
	}

	/**
	 * Gets the properties validation controller.
	 *
	 * @return the properties validation controller
	 */
	public ValidationController getPropertiesValidationController()
	{
		return propertiesValidationController;
	}

	/**
	 * Sets the properties validation controller.
	 *
	 * @param propertiesValidationController the new properties validation controller
	 */
	public void setPropertiesValidationController(ValidationController propertiesValidationController)
	{
		this.propertiesValidationController = propertiesValidationController;
	}

	/**
	 * Gets the light intensity validation controller.
	 *
	 * @return the light intensity validation controller
	 */
	public ValidationController getLightIntensityValidationController()
	{
		return lightIntensityValidationController;
	}

	/**
	 * Sets the light intensity validation controller.
	 *
	 * @param lightIntensityValidationController the new light intensity validation controller
	 */
	public void setLightIntensityValidationController(ValidationController lightIntensityValidationController)
	{
		this.lightIntensityValidationController = lightIntensityValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#initiateUpdateLightIntensity(com.sensus.mlc.smartpoint.
	 * model.request.LightRequest)
	 */
	@Override
	public ProcessResponse initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			checkDefaultsUpdateLightIntensity(lightRequest);

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_INTENSITY);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.OVERRIDE_ENUM.getValue(),
					lightRequest.getOverrideEnum());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.OVERRRIDE_PER_DATE.getValue(),
					lightRequest.getOverridePerDate());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.LIGHT_BLINK_ENUM.getValue(),
					lightRequest.getLightBlinkEnum());
			context.putObjectToBeValidated(
					ObjectToBeValidatedKeyEnum.PERCENTAGE.getValue(),
					lightRequest.getPercentage());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context) &&
					getLightIntensityValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().initiateUpdateLightIntensity(lightRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#initiateGetLightStatus(com.sensus.mlc.smartpoint.model.
	 * request.LightRequest)
	 */
	@Override
	public ProcessResponse initiateGetLightStatus(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_STATUS);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().initiateGetLightStatus(lightRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#initiateDeleteAlert(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public ProcessResponse initiateDeleteAlert(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DELETE_ALERT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().initiateDeleteAlert(lightRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#initiateUpsertLightProperty(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public ProcessResponse initiateUpsertLightProperty(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPSERT_LIGHT_PROPERTY);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.LIGHT_LIST.getValue(), lightRequest.getLights());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context) &&
					getLightListValidationController().validate(context))
			{
				if (getPropertiesValidationController().validate(context))
				{
					internalResponse = getSmartPointProcessorBCL().initiateUpsertLightProperty(lightRequest);
					response.setProcesses(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#initiateUpdateLightStatus(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public ProcessResponse initiateUpdateLightStatus(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_STATUS);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().initiateUpdateLightStatus(lightRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processApplySmartpointPropertiesNotification(com.sensus
	 * .mlc.smartpoint.model.request.ProcessLightsRequest)
	 */
	@Override
	public Response processApplySmartpointPropertiesNotification(ProcessLightsRequest processLightsRequest)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_APPLY_SMARTPOINT_PROPERTIES);
			context.putObjectToBeValidated(PROCESS_LIGHTS_REQUEST_NAME, processLightsRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.LIGHT_LIST.getValue(),
					processLightsRequest.getLights());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightListValidationController().validate(context))
			{
				internalResponse =
						getSmartPointProcessorBCL().processApplySmartpointPropertiesNotification(processLightsRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processChannelSetupAuditNotification(com.sensus.mlc.smartpoint
	 * .model.request.ChannelSetupAuditRequest)
	 */
	@Override
	public Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_CHANNEL_SETUP_AUDIT);
			context.putObjectToBeValidated(CHANNEL_SETUP_AUDIT_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getChannelSetupAuditRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processChannelSetupAuditNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processClearAlarmNotification(com.sensus.mlc.smartpoint
	 * .model.request.AlarmNotificationRequest)
	 */
	@Override
	public AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();
		InternalResultsResponse<StatusMessage> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_ALARM);
			context.putObjectToBeValidated(ALARM_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getAlarmNotificationRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processClearAlarmNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processForcedLightStatusNotification(com.sensus.mlc.smartpoint
	 * .model.request.ForcedLightStatusNotificationRequest)
	 */
	@Override
	public Response processForcedLightStatusNotification(ForcedLightStatusNotificationRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_FORCED_STATUS);
			context.putObjectToBeValidated(FORCED_LIGHT_STATUS_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getForcedLightStatusNotificationRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processForcedLightStatusNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processAlarmWarningNotification(com.sensus.mlc.smartpoint
	 * .model.request.AlarmNotificationRequest)
	 */
	@Override
	public AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();
		InternalResultsResponse<StatusMessage> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_ALARM);
			context.putObjectToBeValidated(ALARM_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getAlarmNotificationRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processAlarmWarningNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processLightStatusNotification(com.sensus.mlc.smartpoint
	 * .model.request.LightStatusNotificationRequest)
	 */
	@Override
	public Response processLightStatusNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_UNSOLICITED_STATUS);
			context.putObjectToBeValidated(LIGHT_STATUS_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getLightStatusNotificationRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processLightStatusNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (final Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processLightBindingNotification(com.sensus.mlc.smartpoint
	 * .model.request.LightStatusNotificationRequest)
	 */
	@Override
	public Response processLightBindingNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_BINDING);
			context.putObjectToBeValidated(LIGHT_STATUS_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getLightStatusNotificationRequestValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processLightBindingNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processLightSetupNotification(com.sensus.mlc.smartpoint
	 * .model.request.LightSetupNotificationRequest)
	 */
	@Override
	public Response processLightSetupNotification(LightSetupNotificationRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_SETUP);
			context.putObjectToBeValidated(LightSetupNotificationRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Light.class.getSimpleName(), request.getLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				// FIXME - properties
				/*
				 * context.putObjectToBeValidated(
				 * PropertyEnum.LATITUDE.name(),
				 * request.getLight().getParameterValue(PropertyEnum.LATITUDE));
				 * context.putObjectToBeValidated(
				 * PropertyEnum.LONGITUDE.name(),
				 * request.getLight().getParameterValue(PropertyEnum.LONGITUDE));
				 */

				if (getGeoCodeValidationController().validate(context))
				{
					internalResponse = getSmartPointProcessorBCL().processLightSetupNotification(request);
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
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
	 * com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF#processSetLightIntensityNotification(com.sensus.mlc.smartpoint
	 * .model.request.ProcessLightsRequest)
	 */
	@Override
	public Response processSetLightIntensityNotification(ProcessLightsRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_SET_LIGHT_INTENSITY);
			context.putObjectToBeValidated(PROCESS_LIGHTS_REQUEST_NAME, request);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.LIGHT_LIST.getValue(), request.getLights());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightListValidationController().validate(context))
			{
				internalResponse = getSmartPointProcessorBCL().processSetLightIntensityNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Check defaults update light intensity.
	 *
	 * @param lightRequest the light request
	 */
	private void checkDefaultsUpdateLightIntensity(LightRequest lightRequest)
	{
		if (ValidationUtil.isNull(lightRequest.getOverrideEnum()))
		{
			lightRequest.setOverrideEnum(OverrideEnum.NONE);
		}

		if (ValidationUtil.isNull(lightRequest.getIsClearOverride()))
		{
			lightRequest.setIsClearOverride(false);
		}
	}
}