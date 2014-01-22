package com.sensus.lc.server.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.APPLY_DIMMING_CONFIGURATION;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_ALARM;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_LIGHT_PROPERTIES;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_SCHEDULE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_BINDING;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_CHANNEL_SETUP_AUDIT;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_CLEAR_ALARMS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_CLEAR_SCHEDULE;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_FORCED_STATUS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_SETUP;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_SET_LIGHT_INTENSITY;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.GATEWAY_UNSOLICITED_STATUS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_STATUS;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.lc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.lc.server.bcf.IMlcServerBCF;
import com.sensus.lc.server.bcl.IMlcServerBCL;
import com.sensus.lc.server.model.request.MlcServerRequest;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;

/**
 * The Class MlcServerBCFImpl.
 * 
 * author Alex Barros - QAT.
 */
public class MlcServerBCFImpl extends AbstractBaseBCF implements IMlcServerBCF
{
	/** The Constant SMARTPOINT. */
	private static final String SMARTPOINT = "smartpoint";

	/** The Constant TRANSACTION_ID. */
	private static final String TRANSACTION_ID = "transactionId";

	/** The Constant CUSTUMER_ID. */
	private static final String CUSTUMER_ID = "custumerId";

	/** The Constant LIGHT_RNI_ID. */
	private static final String LIGHT_RNI_ID = "lightRniId";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.serverbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MlcServerBCFImpl.class);

	/** The Constant CHANNEL_SETUP_AUDIT_NOTIFICATION_NAME. */
	private static final String CHANNEL_SETUP_AUDIT_NOTIFICATION_NAME = ChannelSetupAuditNotification.class
			.getSimpleName();

	/** The Constant LIGHT_STATUS_NOTIFICATION_NAME. */
	private static final String LIGHT_STATUS_NOTIFICATION_NAME = LightStatusNotification.class.getSimpleName();

	/** The Constant LIGHT_BINDING_NOTIFICATION_NAME. */
	private static final String LIGHT_BINDING_NOTIFICATION_NAME = LightBindingNotification.class.getSimpleName();

	/** The Constant LIGHT_SETUP_NOTIFICATION_NAME. */
	private static final String LIGHT_SETUP_NOTIFICATION_NAME = LightSetupNotification.class.getSimpleName();

	/** The Constant ALARM_WARNING_NOTIFICATION. */
	private static final String ALARM_WARNING_NOTIFICATION_NAME = AlarmWarningNotification.class.getSimpleName();

	/** The Constant READ_LIGHT_STATUS_NOTIFICATION_NAME. */
	private static final String READ_LIGHT_STATUS_NOTIFICATION_NAME = ReadLightStatusNotification.class.getSimpleName();

	/** The Constant APPLY_LIGHT_LEVEL_NOTIFICATION_NAME. */
	private static final String APPLY_LIGHT_LEVEL_NOTIFICATION_NAME = ApplyLightLevelNotification.class.getSimpleName();

	/** The Constant CLEAR_ALARM_NOTIFICATION_NAME. */
	private static final String CLEAR_ALARM_NOTIFICATION_NAME = ClearAlarmsNotification.class.getSimpleName();

	/** The Constant CLEAR_SCHEDULE_NOTIFICATION_NAME. */
	private static final String CLEAR_SCHEDULE_NOTIFICATION_NAME = ClearScheduleNotification.class.getSimpleName();

	/** The Constant APPLY_SCHEDULE_NOTIFICATION_NAME. */
	private static final String APPLY_SCHEDULE_NOTIFICATION_NAME = ApplyScheduleNotification.class.getSimpleName();

	/** The Constant APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_NAME. */
	private static final String APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_NAME =
			ApplySmartpointPropertiesNotification.class.getSimpleName();

	/** The Constant APPLY_DIMMING_CONFIGURATION_NOTIFICATION_NAME. */
	private static final String APPLY_DIMMING_CONFIGURATION_NOTIFICATION_NAME =
			ApplyDimmingConfigurationNotification.class.getSimpleName();

	/** The Constant UPDATE_LIGHT_STATUS_NOTIFICATION_NAME. */
	private static final String UPDATE_LIGHT_STATUS_NOTIFICATION_NAME = UpdateLightStatusNotification.class
			.getSimpleName();

	/** The mlc server bcl. */
	private IMlcServerBCL mlcServerBCL; // Injected by Spring through setter.

	/** The validation controller. */
	private ValidationController mlcServerValidationController;

	/** The tenant light association validation controller. */
	private ValidationController tenantLightAssociationValidationController;

	/** The mlc gateway supervisory notification validation controller. */
	private ValidationController mlcGatewaySupervisoryNotificationValidationController;

	/** The mlc server smartpoint list validation controller. */
	private ValidationController mlcServerSmartpointListValidationController;

	/**
	 * Gets the tenant light association validation controller.
	 * 
	 * @return the tenant light association validation controller
	 */
	public ValidationController getTenantLightAssociationValidationController()
	{
		return tenantLightAssociationValidationController;
	}

	/**
	 * Sets the tenant light association validation controller.
	 * 
	 * @param tenantLightAssociationValidationController the new tenant light association validation controller
	 */
	public void setTenantLightAssociationValidationController(
			ValidationController tenantLightAssociationValidationController)
	{
		this.tenantLightAssociationValidationController = tenantLightAssociationValidationController;
	}

	/**
	 * Gets the mlc gateway supervisory notification validation controller.
	 * 
	 * @return the mlc gateway supervisory notification validation controller
	 */
	public ValidationController getMlcGatewaySupervisoryNotificationValidationController()
	{
		return mlcGatewaySupervisoryNotificationValidationController;
	}

	/**
	 * Sets the mlc gateway supervisory notification validation controller.
	 * 
	 * @param mlcGatewaySupervisoryNotificationValidationController the new mlc gateway supervisory notification
	 *            validation controller
	 */
	public void setMlcGatewaySupervisoryNotificationValidationController(
			ValidationController mlcGatewaySupervisoryNotificationValidationController)
	{
		this.mlcGatewaySupervisoryNotificationValidationController =
				mlcGatewaySupervisoryNotificationValidationController;
	}

	/**
	 * Gets the validation controller.
	 * 
	 * @return the validation controller
	 */
	public ValidationController getMlcServerValidationController()
	{
		return mlcServerValidationController;
	}

	/**
	 * Sets the validation controller.
	 * 
	 * @param mlcServerValidationController the new mlc server validation controller
	 */
	public void setMlcServerValidationController(ValidationController mlcServerValidationController)
	{
		this.mlcServerValidationController = mlcServerValidationController;
	}

	/**
	 * Gets the mlc server bcl.
	 * 
	 * @return the mlc server bcl
	 */
	public IMlcServerBCL getMlcServerBCL()
	{
		return mlcServerBCL;
	}

	/**
	 * Sets the mlc server bcl.
	 * 
	 * @param mlcServerBCL the new mlc server bcl
	 */
	public void setMlcServerBCL(IMlcServerBCL mlcServerBCL)
	{
		this.mlcServerBCL = mlcServerBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processChannelSetupAuditNotification(com.sensus.mlc.mlcserver.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processChannelSetupAuditNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ChannelSetupAuditNotification channelSetupAuditNotification =
					request.getChannelSetupAuditNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_CHANNEL_SETUP_AUDIT);
			context.putObjectToBeValidated(CHANNEL_SETUP_AUDIT_NOTIFICATION_NAME, channelSetupAuditNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, channelSetupAuditNotification.getCustomerID());
			context.putObjectToBeValidated(SMARTPOINT, channelSetupAuditNotification.getSmartpoint());

			if (!getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					|| !getMlcServerValidationController().validate(context))
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
				return response;
			}

			context.putObjectToBeValidated(LIGHT_RNI_ID, channelSetupAuditNotification.getSmartpoint().getRniId());
			if (getTenantLightAssociationValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processChannelSetupAudit(channelSetupAuditNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processLightStatusNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			LightStatusNotification lightStatusNotification = request.getLightStatusNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_UNSOLICITED_STATUS);
			context.putObjectToBeValidated(LIGHT_STATUS_NOTIFICATION_NAME, lightStatusNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, lightStatusNotification.getCustomerID());
			context.putObjectToBeValidated(SMARTPOINT, lightStatusNotification.getSmartpoint());

			if (!getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					|| !getMlcServerValidationController().validate(context))
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
				return response;
			}

			context.putObjectToBeValidated(LIGHT_RNI_ID, lightStatusNotification.getSmartpoint().getRniId());
			if (getTenantLightAssociationValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processLightStatus(lightStatusNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processLightBindingNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightBindingNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			LightBindingNotification notification = request.getLightBindingNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_BINDING);
			context.putObjectToBeValidated(LIGHT_BINDING_NOTIFICATION_NAME, notification);
			context.putObjectToBeValidated(CUSTUMER_ID, notification.getCustomerID());
			context.putObjectToBeValidated(SMARTPOINT, notification.getSmartpoint());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				Location location = new Location();
				location.setLatitude(notification.getSmartpointDetail().getLatitude());
				location.setLongitude(notification.getSmartpointDetail().getLongitude());

				Radio radio = new Radio();
				radio.setLocation(location);

				context.putObjectToBeValidated(Radio.class.getSimpleName(), radio);
				if (getGeoCodeValidationController().validate(context))
				{
					internalResponse = getMlcServerBCL().processLightBinding(notification);
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processLightSetupNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightSetupNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			LightSetupNotification lightSetupNotification = request.getLightSetupNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_SETUP);
			context.putObjectToBeValidated(CUSTUMER_ID, lightSetupNotification.getCustomerID());
			context.putObjectToBeValidated(SMARTPOINT, lightSetupNotification.getSmartpoint());
			context.putObjectToBeValidated(LIGHT_SETUP_NOTIFICATION_NAME, lightSetupNotification);

			if (!getMlcGatewaySupervisoryNotificationValidationController().validate(context))
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
				return response;
			}

			internalResponse = getMlcServerBCL().processLightSetup(lightSetupNotification);
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processAlarmWarningNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processAlarmWarningNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			AlarmWarningNotification alarmWarningNotification = request.getAlarmWarningNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_ALARM);
			context.putObjectToBeValidated(ALARM_WARNING_NOTIFICATION_NAME, alarmWarningNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, alarmWarningNotification.getCustomerID());
			context.putObjectToBeValidated(SMARTPOINT, alarmWarningNotification.getSmartpoint());

			if (!getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					|| !getMlcServerValidationController().validate(context))
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
				return response;
			}

			context.putObjectToBeValidated(LIGHT_RNI_ID, alarmWarningNotification.getSmartpoint().getRniId());
			if (getTenantLightAssociationValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processAlarmWarning(alarmWarningNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processReadLightStatusNotification(com.sensus.mlc.mlcserver.model.
	 * request.MlcServerRequest)
	 */
	@Override
	public Response processReadLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ReadLightStatusNotification readLightStatusNotification = request.getReadLightStatusNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_FORCED_STATUS);
			context.putObjectToBeValidated(READ_LIGHT_STATUS_NOTIFICATION_NAME, readLightStatusNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, readLightStatusNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, readLightStatusNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context)
					&& getMlcServerValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processReadLightStatus(readLightStatusNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplyLightLevelNotification(com.sensus.mlc.mlcserver.model.
	 * request.MlcServerRequest)
	 */
	@Override
	public Response processApplyLightLevelNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ApplyLightLevelNotification applyLightLevelNotification = request.getApplyLightLevelNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_SET_LIGHT_INTENSITY);
			context.putObjectToBeValidated(APPLY_LIGHT_LEVEL_NOTIFICATION_NAME, applyLightLevelNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, applyLightLevelNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, applyLightLevelNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processApplyLightLevel(applyLightLevelNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processClearAlarmsNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processClearAlarmsNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ClearAlarmsNotification clearAlarmsNotification = request.getClearAlarmsNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_CLEAR_ALARMS);
			context.putObjectToBeValidated(CLEAR_ALARM_NOTIFICATION_NAME, clearAlarmsNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, clearAlarmsNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, clearAlarmsNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processClearAlarms(clearAlarmsNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processClearScheduleNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processClearScheduleNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ClearScheduleNotification clearScheduleNotification = request.getClearScheduleNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_CLEAR_SCHEDULE);
			context.putObjectToBeValidated(CLEAR_SCHEDULE_NOTIFICATION_NAME, clearScheduleNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, clearScheduleNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, clearScheduleNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processClearSchedule(clearScheduleNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplyScheduleNotification(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processApplyScheduleNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ApplyScheduleNotification applyScheduleNotification = request.getApplyScheduleNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_APPLY_SCHEDULE);
			context.putObjectToBeValidated(APPLY_SCHEDULE_NOTIFICATION_NAME, applyScheduleNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, applyScheduleNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, applyScheduleNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				internalResponse = getMlcServerBCL().processApplySchedule(applyScheduleNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplySmartpointPropertiesNotification(com.sensus.mlc.mlcserver
	 * .model.request.MlcServerRequest)
	 */
	@Override
	public Response processApplySmartpointPropertiesNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ApplySmartpointPropertiesNotification applySmpPropsNotification =
					request.getApplySmartpointPropertiesNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), GATEWAY_APPLY_LIGHT_PROPERTIES);
			context.putObjectToBeValidated(APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_NAME, applySmpPropsNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, applySmpPropsNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, applySmpPropsNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				internalResponse =
						getMlcServerBCL().processApplySmartpointPropertiesNotification(applySmpPropsNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG,
					response,
					ex,
					SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION,
					new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processApplyDimmingConfiguration(com.sensus.mlc.mlcserver.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processApplyDimmingConfiguration(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			ApplyDimmingConfigurationNotification applyDimConfNotification =
					request.getApplyDimmingConfigurationNotification();

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), APPLY_DIMMING_CONFIGURATION);
			context.putObjectToBeValidated(APPLY_DIMMING_CONFIGURATION_NOTIFICATION_NAME, applyDimConfNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, applyDimConfNotification.getCustomerID());
			context.putObjectToBeValidated(TRANSACTION_ID, applyDimConfNotification.getTransactionID());

			if (getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					&& getMlcServerSmartpointListValidationController().validate(context))
			{
				internalResponse =
						getMlcServerBCL().processApplyDimmingConfigurationNotification(applyDimConfNotification);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcf.IMlcServerBCF#processUpdateLightStatusNotification(com.sensus.mlc.mlcserver.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processUpdateLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();
		InternalResponse internalResponse = null;

		try
		{
			UpdateLightStatusNotification updateStatusNotification = request.getUpdateLightStatusNotification();
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_STATUS);
			context.putObjectToBeValidated(UPDATE_LIGHT_STATUS_NOTIFICATION_NAME, updateStatusNotification);
			context.putObjectToBeValidated(CUSTUMER_ID, updateStatusNotification.getCustomerID());

			if (!getMlcGatewaySupervisoryNotificationValidationController().validate(context)
					|| !getMlcServerSmartpointListValidationController().validate(context))
			{
				handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
				return response;
			}

			internalResponse = getMlcServerBCL().processUpdateLightStatusNotification(updateStatusNotification);
			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(
					LOG, response, ex, SENSUS_MLC_MCLSERVERBCLIMPL_DEFAULTEXCEPTION, new Object[] {ex.toString()});
		}

		return response;
	}

	/**
	 * Gets the mlc server smartpoint list validation controller.
	 * 
	 * @return the mlc server smartpoint list validation controller
	 */
	public ValidationController getMlcServerSmartpointListValidationController()
	{
		return mlcServerSmartpointListValidationController;
	}

	/**
	 * Sets the mlc server smartpoint list validation controller.
	 * 
	 * @param mlcServerSmartpointListValidationController the new mlc server smartpoint list validation controller
	 */
	public void setMlcServerSmartpointListValidationController(
			ValidationController mlcServerSmartpointListValidationController)
	{
		this.mlcServerSmartpointListValidationController = mlcServerSmartpointListValidationController;
	}
}
