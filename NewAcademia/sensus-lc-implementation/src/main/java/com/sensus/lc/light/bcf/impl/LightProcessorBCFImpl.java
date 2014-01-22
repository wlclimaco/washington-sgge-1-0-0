/*
 *
 */
package com.sensus.lc.light.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.DELETE_ALERT;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_ALARM;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_LIGHT_PROPERTIES;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_BINDING;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_CHANNEL_SETUP_AUDIT;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_CLEAR_ALARMS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_FORCED_STATUS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_SETUP;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_SET_LIGHT_INTENSITY;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_UNSOLICITED_STATUS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_INTENSITY;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_LAT_LNG;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_POLE_ID;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_STATUS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_BLINK_ENUM;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_LIST;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.OVERRIDE_ENUM;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.OVERRRIDE_PER_DATE;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.PERCENTAGE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.light.bcf.ILightProcessorBCF;
import com.sensus.lc.light.bcl.ILightProcessorBCL;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.light.model.response.AlarmNotificationResponse;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * ILightProcessorBCF implementation.
 * 
 * @author Thiago - QAT
 */
public class LightProcessorBCFImpl implements ILightProcessorBCF
{
	private static final Logger LOG = LoggerFactory.getLogger(LightProcessorBCFImpl.class);
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.lightbcfimpl.defaultexception";

	/**
	 * Validations.
	 */
	private ValidationController requestValidationController;
	private ValidationController lightValidationController;
	private ValidationController lightStatusNotificationRequestValidationController;
	private ValidationController alarmNotificationRequestValidationController;
	private ValidationController channelSetupAuditRequestValidationController;
	private ValidationController lightListValidationController;
	private ValidationController lightIntensityValidationController;
	private ValidationController geoCodeValidationController;

	/**
	 * Objects name.
	 */
	private static final String LIGHT_NAME = Light.class.getSimpleName();
	private static final String ALARM_NOTIFICATION_REQUEST_NAME = AlarmNotificationRequest.class.getSimpleName();
	private static final String PROCESS_LIGHTS_REQUEST_NAME = ProcessLightsRequest.class.getSimpleName();
	private static final String CHANNEL_SETUP_AUDIT_REQUEST_NAME = ChannelSetupAuditRequest.class.getSimpleName();
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();
	private static final String UPDATE_LIGHT_NOTIFICATION_REQUEST_NAME = UpdateLightNotificationRequest.class
			.getSimpleName();
	private static final String LIGHT_CRITERIA_NAME = LightCriteria.class.getSimpleName();
	private static final String RADIO_NAME = Radio.class.getSimpleName();
	private static final String LIGHT_STATUS_NOTIFICATION_REQUEST_NAME = LightStatusNotificationRequest.class
			.getSimpleName();

	/**
	 * BCL's.
	 */
	private ILightProcessorBCL lightProcessorBCL;

	/**
	 * Gets the request validation controller.
	 * 
	 * @return the request validation controller
	 */
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
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
	 * Gets the light2 status notification request validation controller.
	 * 
	 * @return the light2 status notification request validation controller
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
	 * Gets the light2 list validation controller.
	 * 
	 * @return the light2 list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return lightListValidationController;
	}

	/**
	 * Sets the light2 list validation controller.
	 * 
	 * @param lightListValidationController the new light list validation controller
	 */
	public void setLightListValidationController(ValidationController lightListValidationController)
	{
		this.lightListValidationController = lightListValidationController;
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

	/**
	 * Gets the geo code validation controller.
	 * 
	 * @return the geo code validation controller
	 */
	public ValidationController getGeoCodeValidationController()
	{
		return geoCodeValidationController;
	}

	/**
	 * Sets the geo code validation controller.
	 * 
	 * @param geoCodeValidationController the new geo code validation controller
	 */
	public void setGeoCodeValidationController(ValidationController geoCodeValidationController)
	{
		this.geoCodeValidationController = geoCodeValidationController;
	}

	/**
	 * Gets the light processor bcl.
	 * 
	 * @return the light processor bcl
	 */
	public ILightProcessorBCL getLightProcessorBCL()
	{
		return lightProcessorBCL;
	}

	/**
	 * Sets the light processor bcl.
	 * 
	 * @param lightProcessorBCL the new light processor bcl
	 */
	public void setLightProcessorBCL(ILightProcessorBCL lightProcessorBCL)
	{
		this.lightProcessorBCL = lightProcessorBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processLightBindingNotification(com.sensus.mlc.light.model.request
	 * .LightStatusNotificationRequest)
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

			if (getRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getLightStatusNotificationRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processLightBindingNotification(request);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processLightStateNotification(com.sensus.mlc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	@Override
	public Response processLightStatusNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LIGHT_STATUS_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_UNSOLICITED_STATUS);
			if (!isNull(request.isForced()) && request.isForced())
			{
				context.getValidationArguments().put(getSlcActionName(), GATEWAY_FORCED_STATUS);
			}

			if (getRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getLightStatusNotificationRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processLightStatusNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processAlarmWarningNotification(com.sensus.mlc.light.model.request
	 * .AlarmNotificationRequest)
	 */
	@Override
	public AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();
		InternalResultsResponse<NotificationHistory> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_ALARM);
			context.putObjectToBeValidated(ALARM_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getAlarmNotificationRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processAlarmWarningNotification(request);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processLightSetupNotification(com.sensus.mlc.light.model.request.
	 * LightSetupNotificationRequest)
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

			if (getRequestValidationController().validate(context) &&
					getLightValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processLightSetupNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processClearAlarmNotification(com.sensus.mlc.light.model.request.
	 * AlarmNotificationRequest)
	 */
	@Override
	public AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();
		InternalResultsResponse<NotificationHistory> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_CLEAR_ALARMS);
			context.putObjectToBeValidated(ALARM_NOTIFICATION_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getRequestValidationController().validate(context) &&
					getLightValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processClearAlarmNotification(request);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processSetLightIntensityNotification(com.sensus.mlc.light.model.request
	 * .LightIntensityRequest)
	 */
	@Override
	public Response processSetLightIntensityNotification(LightIntensityRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_SET_LIGHT_INTENSITY);
			context.putObjectToBeValidated(PROCESS_LIGHTS_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_LIST.getValue(), request.getLightList());

			if (getRequestValidationController().validate(context) &&
					getLightListValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processSetLightIntensityNotification(request);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processChannelSetupAuditNotification(com.sensus.mlc.light.model.request
	 * .ChannelSetupAuditRequest)
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

			if (getRequestValidationController().validate(context) &&
					getLightValidationController().validate(context) &&
					getChannelSetupAuditRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processChannelSetupAuditNotification(request);
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
	 * com.sensus.mlc.light.bcf.ILightProcessorBCF#processApplySmartpointPropertiesNotification(com.sensus.mlc.light
	 * .model.request.ProcessLightsRequest)
	 */
	@Override
	public Response processApplySmartpointPropertiesNotification(ProcessLightsRequest processLightsRequest)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_APPLY_LIGHT_PROPERTIES);
			context.putObjectToBeValidated(PROCESS_LIGHTS_REQUEST_NAME, processLightsRequest);
			context.putObjectToBeValidated(LIGHT_LIST.getValue(), processLightsRequest.getLights());

			if (getRequestValidationController().validate(context) &&
					getLightListValidationController().validate(context))
			{
				internalResponse =
						getLightProcessorBCL().processApplySmartpointPropertiesNotification(processLightsRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public Response processUpdateLightStatusNotification(UpdateLightNotificationRequest request)
	{
		LightResponse response = new LightResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext(
					UPDATE_LIGHT_NOTIFICATION_REQUEST_NAME,
					request,
					MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.UPDATE_LIGHT_STATUS);

			if (getRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().processUpdateLightStatusNotification(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

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

			ActionCriteria actionCriteria = lightRequest.getActionCriteria();
			context.putObjectToBeValidated(LIGHT_CRITERIA_NAME, actionCriteria);
			context.putObjectToBeValidated(PERCENTAGE.getValue(), actionCriteria.getPercentage());
			context.putObjectToBeValidated(OVERRIDE_ENUM.getValue(), actionCriteria.getOverride());
			context.putObjectToBeValidated(OVERRRIDE_PER_DATE.getValue(), actionCriteria.getOverridePerDate());
			context.putObjectToBeValidated(LIGHT_BLINK_ENUM.getValue(), actionCriteria.getBlinkStatus());

			if (getRequestValidationController().validate(context)
					&& getLightIntensityValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().initiateUpdateLightIntensity(lightRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}

		return response;
	}

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

			if (getRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().initiateGetLightStatus(lightRequest);
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

			if (getRequestValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().initiateDeleteAlert(lightRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public ProcessResponse initiateUpsertLightPoleId(LightMaintenanceRequest request)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_POLE_ID);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().initiateUpsertLightPoleId(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public ProcessResponse initiateUpsertLightLatLng(LightMaintenanceRequest request)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_LAT_LNG);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (!getRequestValidationController().validate(context)
					|| !getLightValidationController().validate(context))
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
				return response;
			}

			context.putObjectToBeValidated(RADIO_NAME, request.getLight().getRadio());
			if (getGeoCodeValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().initiateUpsertLightLatLng(request);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	@Override
	public ProcessResponse initiateUpdateLightStatus(LightMaintenanceRequest request)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_STATUS);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, request);
			context.putObjectToBeValidated(LIGHT_NAME, request.getLight());

			if (getRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{
				internalResponse = getLightProcessorBCL().initiateUpdateLightStatus(request);
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
		ActionCriteria actionCriteria = lightRequest.getActionCriteria();
		if (isNull(actionCriteria))
		{
			actionCriteria = new ActionCriteria();
		}

		if (isNull(actionCriteria.getOverride()))
		{
			actionCriteria.setOverride(OverrideEnum.NONE);
		}

		if (isNull(actionCriteria.getBlinkStatus()))
		{
			actionCriteria.setBlinkStatus(BlinkStatusEnum.NONE);
		}

		if (isNull(actionCriteria.isClearOverride()))
		{
			actionCriteria.setIsClearOverride(false);
		}
	}
}
