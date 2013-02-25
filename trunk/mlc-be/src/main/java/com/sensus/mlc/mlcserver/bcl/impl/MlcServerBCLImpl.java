package com.sensus.mlc.mlcserver.bcl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.mlcserver.bcl.IMlcServerBCL;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AppCode11Info;
import com.sensus.mlc.mlcserver.type.AppCode71Info;
import com.sensus.mlc.mlcserver.type.AppCode85Info;
import com.sensus.mlc.mlcserver.type.AppCode86Info;
import com.sensus.mlc.mlcserver.type.AppCode90Info;
import com.sensus.mlc.mlcserver.type.AppCode94Info;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.DataType;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightLevel;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightState;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightTopLevelState;
import com.sensus.mlc.mlcserver.type.MlcGatewayDataNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewayNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.MlcGatewaySupervisoryNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SerialNumberElement;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.response.AlarmNotificationResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class MlcServerBCLImpl.
 */
public class MlcServerBCLImpl implements IMlcServerBCL
{

	/** The Constant MODEL_NUMBER_SIZE_14. */
	private static final int MODEL_NUMBER_SIZE_14 = 14;

	/** The Constant MODEL_NUMBER_SIZE_15. */
	private static final int MODEL_NUMBER_SIZE_15 = 15;

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(MlcServerBCLImpl.class);

	/** The Constant LIGHT_BINDING_NOTIFICATION_REQUIRED. */
	private static final String LIGHT_BINDING_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.binding.notification.required";

	/** The Constant LIGHT_SETUP_NOTIFICATION_REQUIRED. */
	private static final String LIGHT_SETUP_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.setup.notification.required";

	/** The Constant LIGHT_STATUS_NOTIFICATION_REQUIRED. */
	private static final String LIGHT_STATUS_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.status.notification.required";

	/** The Constant LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED. */
	private static final String LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.alarm.warning.notification.required";

	/** The Constant APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_REQUIRED. */
	private static final String APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.apply.smartpoint.properties.notification.required";

	/** The Constant LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED. */
	private static final String LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.channel.setup.audit.notification.required";

	/** The Constant PROCESS_REQUIRED. */
	private static final String PROCESS_REQUIRED = "sensus.mlc.gateway.process.required";

	/** The Constant PROCESS_ITEMS_FOR_LIGHT_REQUIRED. */
	private static final String PROCESS_ITEMS_FOR_LIGHT_REQUIRED =
			"sensus.mlc.gateway.process.items.for.light.required";

	/** The Constant APPCODE90. */
	private static final int APPCODE90 = 90; // Status

	/** The Constant APPCODE86. */
	private static final int APPCODE86 = 86; // Binding

	/** The Constant UNKNOWN_EVENT_SCHEDULE. */
	private static final Integer UNKNOWN_EVENT_SCHEDULE = 2;

	/** The Constant UNKNOWN_OFFSET_SCHEDULE. */
	private static final Integer UNKNOWN_OFFSET_SCHEDULE = 1;

	/** The Constant currentConversionFactor. */
	private Float currentConversionFactor;

	/** The Constant voltageConversionFactor. */
	private Float voltageConversionFactor;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF; // injected via Spring

	/** The process bcf. */
	private IProcessBCF processBCF; // injected via Spring

	/** The smart point processor bcf. */
	private ISmartPointProcessorBCF smartPointProcessorBCF; // injected via Spring

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected via Spring

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/** The serial number layout. */
	private List<SerialNumberElement> serialNumberLayout; // injected via Spring

	/** The id used for System-initiated actions. */
	private String systemId; // injected via Spring

	/** The system name. */
	private String systemName; // injected via Spring

	/** The pwd used for System-initiated actions. */
	private String systemPwd; // injected via Spring

	/**
	 * Gets the smart point accessor bcl.
	 * 
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return this.smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 * 
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId()
	{
		return this.systemId;
	}

	/**
	 * Sets the system id.
	 * 
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}

	/**
	 * Gets the system name.
	 * 
	 * @return the system name
	 */
	public String getSystemName()
	{
		return this.systemName;
	}

	/**
	 * Sets the system name.
	 * 
	 * @param systemName the new system name
	 */
	public void setSystemName(String systemName)
	{
		this.systemName = systemName;
	}

	/**
	 * Gets the system pwd.
	 * 
	 * @return the system id
	 */
	public String getSystemPwd()
	{
		return this.systemPwd;
	}

	/**
	 * Sets the system pwd.
	 * 
	 * @param systemPwd the new system pwd
	 */
	public void setSystemPwd(String systemPwd)
	{
		this.systemPwd = systemPwd;
	}

	/**
	 * Gets the serial number layout.
	 * 
	 * @return the serial number layout
	 */
	public List<SerialNumberElement> getSerialNumberLayout()
	{
		return this.serialNumberLayout;
	}

	/**
	 * Sets the serial number layout.
	 * 
	 * @param serialNumberLayout the new serial number layout
	 */
	public void setSerialNumberLayout(List<SerialNumberElement> serialNumberLayout)
	{
		this.serialNumberLayout = serialNumberLayout;
	}

	/**
	 * Gets the smart point processor bcf.
	 * 
	 * @return the smart point processor bcf
	 */
	public ISmartPointProcessorBCF getSmartPointProcessorBCF()
	{
		return this.smartPointProcessorBCF;
	}

	/**
	 * Sets the smart point processor bcf.
	 * 
	 * @param smartPointProcessorBCF the new smart point processor bcf
	 */
	public void setSmartPointProcessorBCF(ISmartPointProcessorBCF smartPointProcessorBCF)
	{
		this.smartPointProcessorBCF = smartPointProcessorBCF;
	}

	/**
	 * Gets the smart point updater bcl.
	 * 
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return this.smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 * 
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return this.processBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return this.scheduleBCF;
	}

	/**
	 * Gets the Current Conversion factor.
	 * 
	 * @return the Current Conversion factor.
	 */
	public Float getCurrentConversionFactor()
	{
		return this.currentConversionFactor;
	}

	/**
	 * Sets the Current Conversion factor.
	 * 
	 * @param currentConversionFactorValue the new current conversion factor
	 */
	public void setCurrentConversionFactor(Float currentConversionFactorValue)
	{
		this.currentConversionFactor = currentConversionFactorValue;
	}

	/**
	 * Gets the Voltage Conversion Factor.
	 * 
	 * @return the Voltage Conversion Factor
	 */
	public Float getVoltageConversionFactor()
	{
		return this.voltageConversionFactor;
	}

	/**
	 * Sets the Voltage Conversion Factor.
	 * 
	 * @param voltageConversionFactorValue the new voltage conversion factor
	 */
	public void setVoltageConversionFactor(Float voltageConversionFactorValue)
	{
		this.voltageConversionFactor = voltageConversionFactorValue;
	}

	/**
	 * *******************************
	 * Start of unsolicited messages *
	 * *******************************.
	 * 
	 * @param notification the notification
	 * @return the internal response
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processLightSetup(com.sensus.mlc.mlcserver.type.LightSetupNotification
	 * )
	 */
	@Override
	public InternalResponse processLightSetup(LightSetupNotification notification)
	{
		InternalResponse internalResponse = new InternalResponse();

		LightSetupNotificationRequest request = prepareLightSetupNotificationRequest(notification);

		if (ValidationUtil.isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_SETUP_NOTIFICATION_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		Response response = getSmartPointProcessorBCF().processLightSetupNotification(request);
		if (!response.isOperationSuccess())
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessages(response.getMessageInfoList());
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processChannelSetupAudit(com.sensus.mlc.mlcserver.type.
	 * ChannelSetupAuditNotification)
	 */
	@Override
	public InternalResponse processChannelSetupAudit(ChannelSetupAuditNotification notification)
	{
		// Instantiate a response
		InternalResponse internalResponse = new InternalResponse();

		// Prepares the request that goes to LC
		ChannelSetupAuditRequest request = prepareChannelSetupAuditRequest(notification);

		if (ValidationUtil.isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED, MessageSeverity.Fatal,
					MessageLevel.Object);
			return internalResponse;
		}

		Response response = getSmartPointProcessorBCF().processChannelSetupAuditNotification(request);
		if (!response.isOperationSuccess())
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessages(response.getMessageInfoList());
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processLightBinding(com.sensus.mlc.mlcserver.type.LightBindingNotification
	 * )
	 */
	@Override
	public InternalResponse processLightBinding(LightBindingNotification notification)
	{
		InternalResponse internalResponse = new InternalResponse();
		LightStatusNotificationRequest request = prepareLightStatusNotificationRequest(notification, APPCODE86);
		if (!checkIsValidLightBinding(notification))
		{
			return internalResponse;
		}

		if (ValidationUtil.isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse
					.addMessage(LIGHT_BINDING_NOTIFICATION_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}

		Response response = getSmartPointProcessorBCF().processLightBindingNotification(request);
		if (!response.isOperationSuccess())
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessages(response.getMessageInfoList());
		}
		return internalResponse;
	}

	private Boolean checkIsValidLightBinding(LightBindingNotification notification)
	{

		LightRequest request = prepareLightBindingNotification(notification);
		if (ValidationUtil.isNullOrEmpty(request.getLights()))
		{
			return true;
		}
		return getSmartPointAccessorBCL().checkIfValidBindingMessage(request);

	}

	private LightRequest prepareLightBindingNotification(LightBindingNotification notification)
	{
		LightRequest request = new LightRequest();
		Light light = new Light();
		light.setRniId(notification.getSmartpoint().getRniId());
		request.setTenant(getTenant(notification.getCustomerID()));
		request.getLights().add(light);

		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchLightByRniId(request);
		request.getLights().clear();

		if (!ValidationUtil.isNullOrEmpty(response.getResultsList()))
		{
			request.getLights().add(response.getFirstResult());
		}

		return request;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processLightStatus(com.sensus.mlc.mlcserver.type.LightStatusNotification
	 * )
	 */
	@Override
	public InternalResponse processLightStatus(LightStatusNotification notification)
	{
		InternalResponse internalResponse = new InternalResponse();

		// Prepares the request that goes to LC
		LightStatusNotificationRequest request = prepareLightStatusNotificationRequest(notification, APPCODE90);
		if (ValidationUtil.isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_STATUS_NOTIFICATION_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		Response response = getSmartPointProcessorBCF().processLightStatusNotification(request);
		if (!response.isOperationSuccess())
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessages(response.getMessageInfoList());
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processAlarmWarning(com.sensus.mlc.mlcserver.type.AlarmWarningNotification
	 * )
	 */
	@Override
	public InternalResponse processAlarmWarning(
			AlarmWarningNotification notification)
	{
		// Instantiate a response
		InternalResponse internalResponse = new InternalResponse();

		// Prepares the request that goes to LC
		AlarmNotificationRequest request = prepareAlarmWarningNotificationRequest(notification);
		if (ValidationUtil.isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED, MessageSeverity.Fatal,
					MessageLevel.Object);
			return internalResponse;
		}

		// Call LC BCF
		AlarmNotificationResponse response = getSmartPointProcessorBCF().processAlarmWarningNotification(request);
		if (!response.isOperationSuccess())
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessages(response.getMessageInfoList());
		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processClearAlarms(com.sensus.mlc.mlcserver.type.ClearAlarmsNotification
	 * )
	 */
	@Override
	public InternalResponse processClearAlarms(ClearAlarmsNotification notification)
	{
		// Instantiate a response
		InternalResponse internalResponse = new InternalResponse();

		Process process = updateProcess(notification);

		if (ValidationUtil.isNull(process))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		// Update MLC Data
		AlarmNotificationRequest alarmNotificationRequest = new AlarmNotificationRequest(getUserContext());
		alarmNotificationRequest.setTenant(getTenant(notification.getCustomerID()));

		// The real status will be determined when the message is processed by the smartpoint bcl
		alarmNotificationRequest.setLightStatusEnum(LightStatusEnum.UNKNOWN);

		// Process for each light that succeeded
		for (Smartpoint smartpoint : notification.getSuccededSmartpoint())
		{
			Light light = getLight(smartpoint.getRniId());

			// Add subtypes to the message
			alarmNotificationRequest.setStatusException(getStatusExceptionsBelongsLight(process, light));
			alarmNotificationRequest.setLight(light);

			// Call MLC
			AlarmNotificationResponse alarmNotificationResponse =
					getSmartPointProcessorBCF().processClearAlarmNotification(alarmNotificationRequest);

			if (!alarmNotificationResponse.isOperationSuccess())
			{
				internalResponse.setStatus(Status.SystemError);
				internalResponse.addMessages(alarmNotificationResponse.getMessageInfoList());
			}
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processClearSchedule(com.sensus.mlc.mlcserver.type.
	 * ClearScheduleNotification)
	 */
	@Override
	public InternalResponse processClearSchedule(ClearScheduleNotification notification)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		Process process = updateProcess(notification);

		if (!ValidationUtil.isNull(process))
		{
			// Update LC Data
			ScheduleRequest scheduleRequest = prepareScheduleRequest(process, notification);

			// Change the schedule ID to the Unknown Schedules since it was done before RNI call
			if (ScheduleTypeEnum.EVENT.equals(scheduleRequest.getSchedule().getScheduleTypeEnum()))
			{
				scheduleRequest.getSchedule().setId(UNKNOWN_EVENT_SCHEDULE);
			}
			else if (ScheduleTypeEnum.OFFSET.equals(scheduleRequest.getSchedule().getScheduleTypeEnum()))
			{
				scheduleRequest.getSchedule().setId(UNKNOWN_OFFSET_SCHEDULE);
			}

			ScheduleResponse scheduleResponse = getScheduleBCF().deleteSmartpointFromSchedule(scheduleRequest);

			// The Schedule was deleted just before RNI callback
			if (!scheduleResponse.isOperationSuccess())
			{
				response.setStatus(Status.SystemError);
				response.addMessages(scheduleResponse.getMessageInfoList());
			}
		}
		else
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processApplySchedule(com.sensus.mlc.mlcserver.type.
	 * ApplyScheduleNotification)
	 */
	@Override
	public InternalResponse processApplySchedule(
			ApplyScheduleNotification notification)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		// Update process
		Process process = updateProcess(notification);

		if (!ValidationUtil.isNull(process))
		{
			// Update MLC Data
			ScheduleRequest scheduleRequest = prepareScheduleRequest(process, notification);

			ScheduleResponse scheduleResponse = getScheduleBCF().applySmartPointToSchedule(scheduleRequest);

			if (!scheduleResponse.isOperationSuccess())
			{
				response.setStatus(Status.SystemError);
				response.addMessages(scheduleResponse.getMessageInfoList());
			}
		}
		else
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processReadLightStatus(com.sensus.mlc.mlcserver.type.
	 * ReadLightStatusNotification)
	 */
	@Override
	public InternalResponse processReadLightStatus(final ReadLightStatusNotification notification)
	{
		final InternalResponse response = new InternalResponse();

		// Updating the process.
		final Process process = updateProcess(notification);

		if (ValidationUtil.isNull(process))
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return response;
		}

		List<ProcessItem> processItems = process.getProcessItems();

		if (ValidationUtil.isNullOrEmpty(processItems))
		{
			response.setStatus(Status.NoRowsFoundError);
			response.addMessage(PROCESS_ITEMS_FOR_LIGHT_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return response;
		}

		// TODO Why this verification? Why processItems.size() should be equal 1?
		if (processItems.size() != 1)
		{
			return response;
		}

		// Updating SLC.
		for (final LightDetail lightDetail : notification.getLightDetail())
		{
			Response smpBCFResponse =
					getSmartPointProcessorBCF().processForcedLightStatusNotification(
							prepareForcedLightStatusNotificationRequest(notification, lightDetail));

			if (!smpBCFResponse.isOperationSuccess())
			{
				response.setStatus(Status.SystemError);
				response.addMessages(smpBCFResponse.getMessageInfoList());
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processApplySmartpointPropertiesNotification(com.sensus.mlc.mlcserver
	 * .type.ApplySmartpointPropertiesNotification)
	 */
	@Override
	public InternalResponse processApplySmartpointPropertiesNotification(
			ApplySmartpointPropertiesNotification notification)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		Process process = updateProcess(notification);

		if (!ValidationUtil.isNull(process))
		{
			// This goes to LC's BCF
			ProcessLightsRequest request =
					prepareProcessLightsRequestForApplySmartpointPropertiesNotification(notification, process);

			if (!ValidationUtil.isNull(request))
			{
				// Call BCF
				Response bcfResponse =
						getSmartPointProcessorBCF().processApplySmartpointPropertiesNotification(request);

				if (!bcfResponse.isOperationSuccess())
				{
					response.setStatus(Status.SystemError);
					response.addMessages(bcfResponse.getMessageInfoList());
				}
			}
			else
			{
				response.setStatus(Status.SystemError);
				response.addMessage(APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_REQUIRED, MessageSeverity.Fatal,
						MessageLevel.Object);
			}
		}
		else
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processApplyLightLevel(com.sensus.mlc.mlcserver.type.
	 * ApplyLightLevelNotification)
	 */
	@Override
	public InternalResponse processApplyLightLevel(
			ApplyLightLevelNotification notification)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		Process process = updateProcess(notification);

		if (!ValidationUtil.isNull(process))
		{
			ProcessLightsRequest request =
					prepareProcessLightRequestForApplyLightLevelNotification(notification, response, process);

			// Update if the list of lights is not Null or Empty
			if (!response.isInError() && !ValidationUtil.isNullOrEmpty(request.getLights()))
			{
				Response response2 =
						getSmartPointProcessorBCF().processSetLightIntensityNotification(request);

				if (!response2.isOperationSuccess())
				{
					response.setStatus(Status.SystemError);
					response.addMessages(response2.getMessageInfoList());
				}
			}

		}
		else
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}

		return response;
	}

	/**
	 * Gets the status exceptions belongs light.
	 * 
	 * @param process the process
	 * @param light the light
	 * @return the status exceptions belongs light
	 */
	private List<StatusException> getStatusExceptionsBelongsLight(Process process, Light light)
	{

		if (ValidationUtil.isNullOrEmpty(process.getLcAction().getActionParameters())
				|| ValidationUtil.isNullOrEmpty(light.getCurrentAlarmWarningMessageList()))
		{
			return new ArrayList<StatusException>();
		}

		// Create a list of subtypes (StatusException)
		List<StatusException> statusExceptions = new ArrayList<StatusException>();

		// Cycle through all LCActionParameter for the LCAction
		List<CurrentAlarmWarningMessage> alarmWarningMessages = light.getCurrentAlarmWarningMessageList();
		for (CurrentAlarmWarningMessage alarmWarningMessage : alarmWarningMessages)
		{
			Integer exceptionValue = alarmWarningMessage.getStatusMessageSubtype().getValue();

			if (!existStatusException(process.getLcAction().getActionParameters(), exceptionValue))
			{
				StatusException statusException = new StatusException();
				statusException.setStatusExceptionTypeEnumValue(exceptionValue);
				statusExceptions.add(statusException);
			}
		}

		return statusExceptions;
	}

	/**
	 * Exist status exception.
	 * 
	 * @param actionParameters the action parameters
	 * @param exceptionValue the exception value
	 * @return true, if successful
	 */
	private boolean existStatusException(List<LCActionParameter> actionParameters, Integer exceptionValue)
	{
		if (ValidationUtil.isNullOrEmpty(actionParameters))
		{
			return false;
		}

		for (LCActionParameter prop : actionParameters)
		{
			if (exceptionValue.equals(Integer.parseInt(prop.getActionValue())))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Prepare forced light status notification request.
	 * 
	 * @param notification the notification
	 * @param lightDetail the light detail
	 * @return the forced light status notification request
	 */
	private ForcedLightStatusNotificationRequest prepareForcedLightStatusNotificationRequest(
			ReadLightStatusNotification notification,
			LightDetail lightDetail)
	{
		ForcedLightStatusNotificationRequest request =
				new ForcedLightStatusNotificationRequest(new UserContext(getSystemName()));

		request.setTenant(getTenant(notification.getCustomerID()));

		// Add all exceptions, if any
		AppCode11Info appCode11Data = lightDetail.getAppCode11Data();
		if (!ValidationUtil.isNull(appCode11Data.getAlarmWarningMessages()))
		{
			for (AlarmWarningInfo info : appCode11Data.getAlarmWarningMessages())
			{
				request.getStatusException().add(
						new StatusException(translateStatusException(info.getAlarmWarningSubType())));
			}
		}

		// Prepare Light
		Light light = getLight(lightDetail.getRniId());

		// Decide, based on light state, if light will go on Maintenance
		light.setLightStateEnum(translateLightState(appCode11Data.getLightState()));
		if (!(LightStateEnum.ON).equals(light.getLightStateEnum()))
		{
			light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
		}
		request.setLightStatusEnum(translateLightTopLevelState(appCode11Data.getLightTopLevelState()));

		// Prepare properties
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		List<LightParameter> optionalLightParameters =
				prepareParmListFromAppCode71Data(lightDetail.getAppCode71Data());
		if (!ValidationUtil.isNull(optionalLightParameters))
		{
			lightParameters.addAll(optionalLightParameters);
		}

		// Sunrise/Sunset/AC Current/AC Voltage
		lightParameters.addAll(prepareParmListFromAppCode90Data(lightDetail.getAppCode90Data(),
				lightDetail.getCustomerID()));

		// If additional CONFIG data was requested, process it.
		if (notification.getDataType().contains(DataType.CONFIGURATION_DATA))
		{
			lightParameters.addAll(prepareParmListFromAppCode94Data(lightDetail.getAppCode94Data()));
			lightParameters.addAll(prepareParmListFromAppCode85Data(lightDetail.getAppCode85Data()));
		}

		// Copy DataType to request, translating first to LC types.
		for (DataType dataType : notification.getDataType())
		{
			request.getLightDetailDataType().add(translateDataType(dataType));
		}

		request.setLightParameters(lightParameters);

		// AppCode90 carries Consumption,Voltage,Current info
		request.getOperationalData().addAll(prepareOpDataListFromAppCode90Data(lightDetail.getAppCode90Data()));

		request.setLight(light);

		return request;
	}

	/**
	 * Prepare process lights request.
	 * 
	 * @param notification the notification
	 * @param process the process
	 * @return the process lights request
	 */
	private ProcessLightsRequest prepareProcessLightsRequestForApplySmartpointPropertiesNotification(
			ApplySmartpointPropertiesNotification notification,
			Process process)
	{

		ProcessLightsRequest request = new ProcessLightsRequest(getUserContext());

		request.setTenant(getTenant(notification.getCustomerID()));

		// Add lights to the request (lights that succeeded)
		for (ProcessItem processItem : process.getProcessItems())
		{
			if (!ValidationUtil.isNull(containsRniInList(
					((MlcGatewayNotification)notification).getSuccededSmartpoint(),
					processItem.getLight().getRniId())))
			{
				Light light = getLight(processItem.getLight().getRniId());

				// Add properties to light
				addOtherParameters(light, prepareParmListFromLCActionParameter(process.getLcAction()
						.getActionParameters()));
				request.getLights().add(light);
			}

		}

		return request;
	}

	/**
	 * Prepare process light request for apply light level notification.
	 * 
	 * @param notification the notification
	 * @param response the response
	 * @param process the process
	 * @return the process lights request
	 */
	private ProcessLightsRequest prepareProcessLightRequestForApplyLightLevelNotification(
			ApplyLightLevelNotification notification,
			InternalResponse response, Process process)
	{
		ProcessLightsRequest request = new ProcessLightsRequest(getUserContext());

		request.setTenant(getTenant(notification.getCustomerID()));

		Date overridePerDate = null;
		Integer override = OverrideEnum.NONE.getValue();
		String lightBlinkEnum = String.valueOf(LightBlinkEnum.NONE.getValue());

		// if it is a group light intensity, the light intensity is the second row at ActionParametersList
		LCAction lcAction = process.getLcAction();
		LCActionTypeEnum actionType = lcAction.getActionType();
		if (((LCActionTypeEnum.TURN_OFF.equals(actionType)) || (LCActionTypeEnum.TURN_ON.equals(actionType)) || (LCActionTypeEnum.DIM
				.equals(actionType)))
				|| LCActionTypeEnum.SET_BLINK_BY_LIGHT.equals(actionType)
				|| LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT.equals(actionType))
		{
			for (LCActionParameter actionParameter : lcAction.getActionParameters())
			{
				if (actionParameter.getProperty().equals(PropertyEnum.LIGHT_BLINK))
				{
					lightBlinkEnum = actionParameter.getActionValue();
				}

				if (actionParameter.getProperty().equals(PropertyEnum.OVERRIDE))
				{
					override = Integer.parseInt(actionParameter.getActionValue());
				}

				if (actionParameter.getProperty().equals(PropertyEnum.OVERRIDE_PER_DATE))
				{
					overridePerDate = LCDateUtil.convertStringToDate(actionParameter.getActionValue());
				}
			}
		}

		if (response.isInError())
		{
			return request;
		}

		// Add lights to the request (lights that succeeded)
		for (ProcessItem processItem : process.getProcessItems())
		{
			List<Smartpoint> succededSmartpoint = ((MlcGatewayNotification)notification).getSuccededSmartpoint();
			Light light = processItem.getLight();
			Smartpoint smartpoint = containsRniInList(succededSmartpoint, light.getRniId());
			if (ValidationUtil.isNull(light) || ValidationUtil.isNull(smartpoint))
			{
				continue;
			}

			addLightToRequest(request,
					light,
					actionType,
					overridePerDate,
					override,
					lightBlinkEnum,
					smartpoint);
		}

		return request;
	}

	/**
	 * Adds the light to request.
	 * 
	 * @param request the request
	 * @param light the light
	 * @param actionType the action type
	 * @param overridePerDate the override per date
	 * @param override the override
	 * @param lightBlinkEnum the light blink enum
	 * @param smartpoint the smartpoint
	 */
	private void addLightToRequest(ProcessLightsRequest request, Light light,
			LCActionTypeEnum actionType, Date overridePerDate, Integer override,
			String lightBlinkEnum, Smartpoint smartpoint)
	{
		light = getLight(light.getRniId());

		if (!ValidationUtil.isNull(smartpoint.getLightState()))
		{
			LightStateEnum stateEnum = LightStateEnum.valueOf(smartpoint.getLightState().name());
			light.setLightStateEnum(stateEnum);

			if (LightStateEnum.BLINK.equals(stateEnum))
			{
				light.setLightIntensityEnum(LightIntensityEnum.LEVEL_6);
			}
		}

		light.setLightBlinkEnum(LightBlinkEnum.enumForValue(Integer.parseInt(lightBlinkEnum)));

		if (LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT != actionType)
		{
			light.setOverrideCreateDate(LCDateUtil.getNewDateUTC());
		}

		LightParameter lightParameter = new LightParameter();
		if ((LCActionTypeEnum.SET_BLINK_BY_LIGHT == actionType)
				|| (LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT == actionType))
		{
			lightParameter.setPropertyEnumValue(PropertyEnum.LIGHT_BLINK.getValue());
			lightParameter.setValue(lightBlinkEnum);
		}

		if (!ValidationUtil.isNull(smartpoint.getLightLevel()))
		{
			LightIntensityEnum intensity = LightIntensityEnum.valueOf(smartpoint.getLightLevel().name());
			light.setLightIntensityEnum(intensity);

			if ((LCActionTypeEnum.TURN_OFF == actionType)
					|| (LCActionTypeEnum.TURN_ON == actionType)
					|| (LCActionTypeEnum.DIM == actionType)
					|| (LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT == actionType))
			{
				lightParameter.setPropertyEnumValue(PropertyEnum.LIGHT_INTENSITY.getValue());
				lightParameter.setValue(intensity.getPercentage().toString());
			}
		}

		light.setLightBlinkEnum(LightBlinkEnum.enumForValue(Integer.parseInt(lightBlinkEnum)));

		lightParameter.setPropertyEnumValue(PropertyEnum.OVERRIDE.getValue());
		lightParameter.setValue(override.toString());
		light.setOverrideEnumValue(override);

		if (!ValidationUtil.isNull(overridePerDate))
		{
			lightParameter.setPropertyEnumValue(PropertyEnum.OVERRIDE_PER_DATE.getValue());
			lightParameter.setValue(overridePerDate.toString());
			light.setOverridePerDate(overridePerDate);
		}
		// FIXME - properties
		// light.setParameters(Arrays.asList(lightParameter));
		request.getLights().add(light);

	}

	/**
	 * Prepare alarm warning notification request.
	 * 
	 * @param notification the notification
	 * @return the alarm notification request
	 */
	private AlarmNotificationRequest prepareAlarmWarningNotificationRequest(
			AlarmWarningNotification notification)
	{
		// This goes to BCF -> MLC specific
		AlarmNotificationRequest request = new AlarmNotificationRequest(new UserContext(getSystemName()));

		request.setTenant(getTenant(notification.getCustomerID()));

		if (ValidationUtil.isNull(notification.getSmartpointDetail())
				|| ValidationUtil.isNull(notification.getSmartpoint()))
		{
			request = null;
			return request;
		}

		// Add all exceptions
		AppCode11Info appCode11Data = notification.getSmartpointDetail();
		for (AlarmWarningInfo info : appCode11Data.getAlarmWarningMessages())
		{
			request.getStatusException().add(
					new StatusException(translateStatusException(info.getAlarmWarningSubType())));
		}

		// light status (ACTIVE/MAINT/IDLE/UNKNOWN) - Top Level State
		request.setLightStatusEnum(translateLightTopLevelState(appCode11Data.getLightTopLevelState()));

		// Light light = new Light(notification.getSmartpoint().getRniId());
		Light light = getLight(notification.getSmartpoint().getRniId());

		// light state (ON/OFF/BLINK/MAINT) - State
		light.setLightStateEnum(translateLightState(appCode11Data.getLightState()));
		if (!(LightStateEnum.ON).equals(light.getLightStateEnum()))
		{
			light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
		}
		else
		{
			LightRequest lightRequest = new LightRequest(getUserContext());
			lightRequest.addLight(light);

			InternalResultsResponse<Light> fetchLightById =
					getSmartPointAccessorBCL().fetchLightById(lightRequest);
			if (!ValidationUtil.isNull(fetchLightById.getFirstResult()))
			{
				light.setLightIntensityEnum(fetchLightById.getFirstResult().getLightIntensityEnum());
			}
		}

		request.setLight(light);

		// Current, Voltage
		Float acCurrent = getCurrentValueInAmpere(notification.getSmartpointDetail().getCurrent());
		Float acVoltage = getVoltageValueInV(notification.getSmartpointDetail().getVoltage());

		request.addOperationalData(new OperationalData(acCurrent, OperationalDataTypeEnum.CURRENT));
		request.addOperationalData(new OperationalData(acVoltage, OperationalDataTypeEnum.VOLTAGE));
		request.addLightParameter(new LightParameter(PropertyEnum.AC_CURRENT, String.valueOf(acCurrent.intValue())));
		request.addLightParameter(new LightParameter(PropertyEnum.AC_VOLTAGE, String.valueOf(acVoltage.intValue())));

		return request;
	}

	/**
	 * Prepare light status notification request.
	 * 
	 * @param notification the notification
	 * @param appCode the app code
	 * @return the light status notification request
	 */
	private LightStatusNotificationRequest prepareLightStatusNotificationRequest(
			MlcGatewaySupervisoryNotification notification, Integer appCode)
	{
		// This goes to BCF -> MLC specific
		LightStatusNotificationRequest request =
				new LightStatusNotificationRequest(new UserContext(getSystemName()));

		Tenant tenant = getTenant(notification.getCustomerID());
		request.setTenant(tenant);

		if (!ValidationUtil.isNull(notification.getSmartpoint()))
		{
			List<LightParameter> lightParameters = new ArrayList<LightParameter>();
			List<OperationalData> operationalData = new ArrayList<OperationalData>();
			lightParameters.add(new LightParameter(PropertyEnum.TIME_ZONE, tenant.getLightTimeZone()));
			Light light = getLight(notification.getSmartpoint().getRniId());

			switch (appCode)
			{
			// Binding
				case APPCODE86:

					LightBindingNotification lightBindingNotification = (LightBindingNotification)notification;

					// Lat/Long/PoleId/Firmware version/TZ
					lightParameters.addAll(prepareParmListFromAppCode86Data(lightBindingNotification));

					break;

				// Status
				case APPCODE90:

					LightStatusNotification lightStatusNotification = (LightStatusNotification)notification;

					// AppCode90 carries LightState info (ON/OFF/BLINK/MAINT)
					light.setLightStateEnum(translateLightState(lightStatusNotification
							.getSmartpointDetail().getLightState()));
					if (!(LightStateEnum.ON).equals(light.getLightStateEnum()))
					{
						light.setLightIntensityEnum(LightIntensityEnum.LEVEL_0);
					}

					// AppCode90 carries Consumption,Voltage,Current info
					operationalData.addAll(prepareOpDataListFromAppCode90Data(lightStatusNotification
							.getSmartpointDetail()));

					// Sunrise/Sunset/AC Current/AC Voltage
					lightParameters.addAll(prepareParmListFromAppCode90Data(lightStatusNotification
							.getSmartpointDetail(), lightStatusNotification.getCustomerID()));

					break;

				default:

					return null;

			}

			addOtherParameters(light, lightParameters);
			request.setLightParameters(lightParameters);
			request.setOperationalData(operationalData);
			request.setLight(light);
		}
		else
		{
			return null;
		}

		return request;
	}

	/**
	 * Adds the other parameters.
	 * 
	 * @param light the light
	 * @param lightParameters the light parameters
	 */
	private void addOtherParameters(Light light, List<LightParameter> lightParameters)
	{
		if (ValidationUtil.isNull(light) || ValidationUtil.isNullOrEmpty(lightParameters))
		{
			return;
		}

		// Set different elements of request if the light already exist
		for (LightParameter lightParameter : lightParameters)
		{
			lightParameter.getPropertyEnum();
		}
	}

	/**
	 * Prepare light setup notification request.
	 * 
	 * @param notification the notification
	 * @return the light setup notification request
	 */
	private LightSetupNotificationRequest prepareLightSetupNotificationRequest(LightSetupNotification notification)
	{
		// This goes to BCF and is LC specific
		LightSetupNotificationRequest request = new LightSetupNotificationRequest(getUserContext());
		request.setTenant(getTenant(notification.getCustomerID()));

		if (!ValidationUtil.isNull(notification.getSmartpoint()))
		{
			// Create a light with just RniId OR get the light if it exists already
			request.setLight(
					getLight(notification.getSmartpoint().getRniId()));
		}
		else
		{
			return null;
		}

		return request;
	}

	/**
	 * Prepare channel setup audit request.
	 * 
	 * @param notification the notification
	 * @return the channel setup audit request
	 */
	private ChannelSetupAuditRequest prepareChannelSetupAuditRequest(
			ChannelSetupAuditNotification notification)
	{
		// This goes to BCF and is LC specific
		ChannelSetupAuditRequest request = new ChannelSetupAuditRequest(getUserContext());
		request.setTenant(getTenant(notification.getCustomerID()));

		if (ValidationUtil.isNull(notification.getSmartpoint())
				|| ValidationUtil.isNull(notification.getSmartpointDetail()))
		{
			request = null;
			return request;
		}

		// light status (ACTIVE/MAINT/IDLE/UNKNOWN) - Top Level State
		request.setLightStatusEnum(translateLightTopLevelState(notification.getSmartpointDetail()
				.getLightTopLevelState()));

		// Create a light with just RniId OR get the light if it exists already
		Light light = getLight(notification.getSmartpoint().getRniId());
		request.setLight(light);
		return request;
	}

	/**
	 * Prepare schedule request.
	 * 
	 * @param process the process
	 * @param notification the notification
	 * @return the schedule request
	 */
	private ScheduleRequest prepareScheduleRequest(Process process, MlcGatewayNotification notification)
	{
		ScheduleRequest scheduleRequest = new ScheduleRequest(getUserContext());

		scheduleRequest.setTenant(getTenant(notification.getCustomerID()));
		Schedule schedule = new EventSchedule();

		if (LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_EVENT.equals(process.getLcAction().getActionType()))
		{
			if (ValidationUtil.isNull(schedule))
			{
				schedule = new EventSchedule();
			}
			schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		}
		else if (LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_OFFSET.equals(process.getLcAction().getActionType()))
		{
			if (ValidationUtil.isNull(schedule))
			{
				schedule = new OffsetSchedule();
			}
			schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		}
		else
		{
			if (LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_EVENT.equals(process.getLcAction().getActionType()))
			{
				schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
			}
			else if (LCActionTypeEnum.ADD_SMP_TO_SCHEDULE_OFFSET.equals(process.getLcAction().getActionType()))
			{
				schedule = new OffsetSchedule();
				schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
			}

			for (LCActionParameter actionParameter : process.getLcAction().getActionParameters())
			{
				if (actionParameter.getProperty().equals(PropertyEnum.SCHEDULE_ID))
				{
					schedule.setId(new Integer(actionParameter.getActionValue()));
					break;
				}
			}
		}

		schedule.getLights().clear();

		for (Smartpoint smartpoint : notification.getSuccededSmartpoint())
		{
			Light light = getLight(smartpoint.getRniId());
			schedule.getLights().add(light);
		}

		scheduleRequest.setSchedule(schedule);

		return scheduleRequest;
	}

	/**
	 * Update process.
	 * 
	 * @param notification the notification
	 * @return the process
	 */
	private Process updateProcess(MlcGatewayResponse notification)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("GATEWAY: Starting " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		// Create a request with RniCorrelationId only to fetch the corresponding Process
		ProcessRequest processRequest = new ProcessRequest(new UserContext());
		Process process = new Process();
		process.setRniCorrelationId(notification.getTransactionID());
		processRequest.setProcess(process);

		// Get Process for this RniCorrelationId
		ProcessResponse processResponse = getProcessBCF().fetchProcessByTransactionId(processRequest);

		// Only continue if I got a process back
		if (processResponse.isOperationSuccess() && !processResponse.getProcesses().isEmpty())
		{
			process = setActionValueProcess(notification, processResponse);

			// Need to update several fields in the LRP
			process.setEndTime(LCDateUtil.getNewDateUTC());
			process.setIsProcessComplete(true);

			// Verify if the light is on the Succeeded or Failed list and set Status accordingly
			for (ProcessItem processItem : process.getProcessItems())
			{
				ProcessItemStatusEnum result = null;
				ProcessStatusReasonEnum resultReason = null;
				if (MlcGatewayNotification.class
						.isAssignableFrom(notification.getClass()))
				{
					if (!ValidationUtil.isNull(containsRniInList(
							((MlcGatewayNotification)notification).getSuccededSmartpoint(), processItem
									.getLight().getRniId())))
					{
						result = ProcessItemStatusEnum.SUCCESS;
					}
					else if (processItem.getProcessResult() != ProcessItemStatusEnum.MLCFAILURE)
					{
						result = ProcessItemStatusEnum.RNIASYNCFAILURE;
						resultReason = ProcessStatusReasonEnum.RNI_ASYNC_FAILURE;
					}
					else
					{
						result = processItem.getProcessResult();
						resultReason = processItem.getProcessReason();
					}
				}

				if (MlcGatewayDataNotification.class.isAssignableFrom(notification
						.getClass()))
				{
					if (listDetailContains(((MlcGatewayDataNotification)notification).getLightDetail(),
							processItem
									.getLight().getRniId()))
					{
						result = ProcessItemStatusEnum.SUCCESS;
					}
					else if (processItem.getProcessResult() != ProcessItemStatusEnum.MLCFAILURE)
					{
						result = ProcessItemStatusEnum.RNIASYNCFAILURE;
						resultReason = ProcessStatusReasonEnum.RNI_ASYNC_FAILURE;
					}
					else
					{
						result = processItem.getProcessResult();
						resultReason = processItem.getProcessReason();
					}
				}
				processItem.setProcessItemStatusEnum(result);
				processItem.setProcessStatusReasonEnum(resultReason);
			}

			// Update Process
			processRequest = new ProcessRequest(new UserContext());
			processRequest.setProcess(process);
			processResponse = getProcessBCF().updateProcess(processRequest);

			if (!processResponse.isOperationSuccess())
			{
				process = null;
			}
		}
		else
		{
			process = null;
		}

		if (LOG.isDebugEnabled())
		{
			if (ValidationUtil.isNull(process))
			{
				LOG.debug(" GATEWAY: Ending " + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ " Process: null ");
			}
			else
			{
				LOG.debug("GATEWAY: Ending " + Thread.currentThread().getStackTrace()[1].getMethodName()
						+ " Process:  "
						+ process.toString());
			}
		}

		return process;
	}

	/**
	 * Sets the action value process.
	 * 
	 * @param notification the notification
	 * @param processResponse the process response
	 * @return the process
	 */
	private Process setActionValueProcess(MlcGatewayResponse notification, ProcessResponse processResponse)
	{
		Process process = processResponse.getProcesses().get(0);

		LCActionTypeEnum actionType = process.getLcAction().getActionType();

		if (((actionType == LCActionTypeEnum.TURN_OFF) || (actionType == LCActionTypeEnum.TURN_ON) || (actionType == LCActionTypeEnum.DIM))
				||
				(actionType == LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT))
		{
			// If it is a LightLRP, use the intensity that came back from the notification.
			// It should be better to have a ForcedStatusLRP type to avoid this
			if (ValidationUtil.isNull(process.getLcAction().getActionParameters().get(0).getActionValue())
					&& (notification instanceof ReadLightStatusNotification))
			{
				process.getLcAction().getActionParameters().get(0)
						.setActionValue(translateLightIntensity(((ReadLightStatusNotification)notification)
								.getLightDetail().get(0).getAppCode90Data().getLightLevel()).toString());
			}
		}
		else if (actionType == LCActionTypeEnum.SET_INTENSITY_BY_GRP)
		{
			if (ValidationUtil.isNull(process.getLcAction().getActionParameters().get(1).getActionValue())
					&& (notification instanceof ReadLightStatusNotification))
			{
				process.getLcAction().getActionParameters().get(1)
						.setActionValue(translateLightIntensity(((ReadLightStatusNotification)notification)
								.getLightDetail().get(0).getAppCode90Data().getLightLevel()).toString());
			}
		}

		return process;
	}

	/**
	 * Model Number is a compound code. This routine parses the model number and returns a list of properties and its
	 * values embedded in the Model Number.
	 * 
	 * @param productNumber the product number
	 * @return the list
	 */
	private List<LightParameter> parseModelNumber(String productNumber)
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		// Cycle through all elements that are part of productNumber
		for (SerialNumberElement el : getSerialNumberLayout())
		{
			lightParameters
					.add(new LightParameter(el.getPropertyType(), el.getValue(el.parseElement(productNumber))));
		}

		return lightParameters;
	}

	/**
	 * Prepare parm list from lc action parameter.
	 * 
	 * @param data the data
	 * @return the list
	 */
	private List<LightParameter> prepareParmListFromLCActionParameter(List<LCActionParameter> data)
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		// get information from process (lat/long/poleid) and add as LightParameter
		for (LCActionParameter actionParm : data)
		{
			lightParameters
					.add(new LightParameter(actionParm.getProperty(), actionParm.getActionValue()));
		}

		return lightParameters;
	}

	/**
	 * Prepare op data list from app code90 data.
	 * 
	 * @param data the data
	 * @return the list
	 */
	private List<OperationalData> prepareOpDataListFromAppCode90Data(AppCode90Info data)
	{

		List<OperationalData> operationalDataList = new ArrayList<OperationalData>();

		if (!ValidationUtil.isNull(data.getCurrent()) || !ValidationUtil.isNull(data.getVoltage())
				|| !ValidationUtil.isNull(data.getConsumption()))
		{
			// AppCode90 carries Consumption,Voltage,Current info
			operationalDataList.add(
					new OperationalData(getCurrentValueInAmpere(data
							.getCurrent()),
							OperationalDataTypeEnum.CURRENT));
			operationalDataList.add(
					new OperationalData(getVoltageValueInV(data
							.getVoltage()),
							OperationalDataTypeEnum.VOLTAGE));
			operationalDataList.add(
					new OperationalData(new Float(data.getConsumption()),
							OperationalDataTypeEnum.CUMULATIVECONSUMPTION));
		}
		return operationalDataList;
	}

	/**
	 * Prepare parm list from app code71 data.
	 * 
	 * @param data the data
	 * @return the list
	 */
	private List<LightParameter> prepareParmListFromAppCode71Data(AppCode71Info data)
	{
		List<LightParameter> lightParameters = null;
		// This field is optional, so check if has value
		if (!ValidationUtil.isNull(data) && !ValidationUtil.isNullOrEmpty(data.getCustomerSerial()))
		{
			lightParameters = new ArrayList<LightParameter>();
			lightParameters
					.add(new LightParameter(PropertyEnum.CUSTOMER_SERIAL_NUMBER, data.getCustomerSerial()));

		}

		return lightParameters;
	}

	/**
	 * Prepare app code85 data.
	 * 
	 * @param data the data
	 * @return the list
	 */
	private List<LightParameter> prepareParmListFromAppCode85Data(AppCode85Info data)
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		lightParameters
				.add(new LightParameter(PropertyEnum.SUNRISE_OFFSET, data.getSunriseOffset()
						.toString()));
		lightParameters.add(new LightParameter(PropertyEnum.SUNSET_OFFSET, data.getSunsetOffset()
				.toString()));

		return lightParameters;
	}

	/**
	 * Prepare app code86 data - Binding.
	 * 
	 * @param notification the notification
	 * @return the list
	 */
	private List<LightParameter> prepareParmListFromAppCode86Data(LightBindingNotification notification)
	{
		AppCode86Info data = notification.getSmartpointDetail();
		notification.getInstallationDetail();

		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		lightParameters.add(new LightParameter(PropertyEnum.LATITUDE, data
				.getLatitude().toString()));

		lightParameters.add(new LightParameter(PropertyEnum.LONGITUDE, data
				.getLongitude().toString()));

		lightParameters
				.add(new LightParameter(PropertyEnum.POLE_ID, data.getPoleId()));

		lightParameters.add(new LightParameter(PropertyEnum.FIRMWARE_VERSION, prepareFirmwareVersionString(
				data.getDeviceVersionMajor(), data.getDeviceVersionMinor(), data.getDeviceVersionPatch())));

		return lightParameters;
	}

	/**
	 * Prepare app code90 data. Data related to OperationalData is prepared elsewhere.
	 * 
	 * @param data the data
	 * @param customerID the customer id
	 * @return the list
	 */
	private List<LightParameter> prepareParmListFromAppCode90Data(AppCode90Info data, String customerID)
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		if (!ValidationUtil.isNull(data.getSunriseHour()) || !ValidationUtil.isNull(data.getSunsetHour())
				|| !ValidationUtil.isNull(data.getCurrent()) || !ValidationUtil.isNull(data.getVoltage()))
		{

			lightParameters.add(new LightParameter(PropertyEnum.SUNRISE_TIME, prepareTime(data.getSunriseHour(),
					data
							.getSunriseMinute(), customerID)));
			lightParameters.add(new LightParameter(PropertyEnum.SUNSET_TIME, prepareTime(data.getSunsetHour(),
					data
							.getSunsetMinute(), customerID)));

			lightParameters.add(new LightParameter(PropertyEnum.AC_CURRENT, data
					.getCurrent()
					.toString()));

			lightParameters.add(new LightParameter(PropertyEnum.AC_VOLTAGE, data
					.getVoltage()
					.toString()));
		}
		return lightParameters;
	}

	/**
	 * Prepare parm list from app code94 data. This includes parsing the Model Number to retrieve its parts.
	 * 
	 * @param data the data
	 * @return the list of LightParameter
	 */
	private List<LightParameter> prepareParmListFromAppCode94Data(AppCode94Info data)
	{
		List<LightParameter> lightParameters = new ArrayList<LightParameter>();

		lightParameters
				.add(new LightParameter(PropertyEnum.MODEL_NUMBER, data.getProductNumber()));

		// now parse the Product Number to get more fields

		if ((data.getProductNumber().length() == MODEL_NUMBER_SIZE_14)
				|| (data.getProductNumber().length() == MODEL_NUMBER_SIZE_15))
		{
			lightParameters.addAll(parseModelNumber(data.getProductNumber()));
		}

		lightParameters.add(new LightParameter(PropertyEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, data.getLowerCaseSerial()));
		lightParameters
				.add(new LightParameter(PropertyEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, data.getUpperCaseSerial()));
		lightParameters.add(new LightParameter(PropertyEnum.BULB_SERIAL_NUMBER, data.getBulbSerial()));
		lightParameters
				.add(new LightParameter(PropertyEnum.BALLAST_SERIAL_NUMBER, data.getBallastSerial()));

		return lightParameters;
	}

	/**
	 * Prepare firmware version string.
	 * 
	 * @param major the major
	 * @param minor the minor
	 * @param patch the patch
	 * @return the string
	 */
	private String prepareFirmwareVersionString(Integer major, Integer minor, Integer patch)
	{
		String firmwareVersion = String.format("%d.%d.%d", major, minor, patch);
		return firmwareVersion;
	}

	/**
	 * Prepare time.
	 * 
	 * @param hour the hour
	 * @param min the min
	 * @param rniCode the rni code
	 * @return the string
	 */
	private String prepareTime(Integer hour, Integer min, String rniCode)
	{
		Tenant tenant = getTenant(rniCode);
		return LCDateUtil.convertSunriseSunsetToDefaultUTC(hour, min, tenant.getLightTimeZone());
	}

	/**
	 * Gets the current value in miliampere.
	 * 
	 * @param currentMeasurement the current measurement
	 * @return the current value in miliampere
	 */
	private Float getCurrentValueInAmpere(Integer currentMeasurement)
	{
		return currentMeasurement * getCurrentConversionFactor();
	}

	/**
	 * Gets the voltage value in Volts.
	 * 
	 * @param voltageMeasurement the voltage measurement
	 * @return the voltage value in Volts
	 */
	private Float getVoltageValueInV(Integer voltageMeasurement)
	{
		return voltageMeasurement * getVoltageConversionFactor();
	}

	/**
	 * Translate light top level state.
	 * 
	 * @param lightTopLevelState the light top level state
	 * @return the light status enum
	 */
	private LightStatusEnum translateLightTopLevelState(LightTopLevelState lightTopLevelState)
	{
		// Decide, based on light state, if light will go on Maintenance
		if (LightTopLevelState.MAINTENANCE.equals(lightTopLevelState))
		{
			return LightStatusEnum.MAINTENANCE;
		}
		else if (LightTopLevelState.IDLE.equals(lightTopLevelState))
		{
			return LightStatusEnum.DEACTIVATED;
		}
		else if (LightTopLevelState.ACTIVE.equals(lightTopLevelState))
		{
			return LightStatusEnum.ACTIVE;
		}
		else if (LightTopLevelState.UNKNOWN.equals(lightTopLevelState))
		{
			return LightStatusEnum.UNKNOWN;
		}
		else
		{
			// Alarm/Warning will be decided when exceptions are evaluated (in the DAC).For now, flag it as UNKNOWN
			return LightStatusEnum.UNKNOWN;
		}
	}

	/**
	 * Translate light state.
	 * 
	 * @param lightState the light state
	 * @return the light status enum
	 */
	private LightStateEnum translateLightState(LightState lightState)
	{
		switch (lightState)
		{
			case ON:
				return LightStateEnum.ON;
			case OFF:
				return LightStateEnum.OFF;
			case BLINK:
				return LightStateEnum.BLINK;
			case MAINTENANCE:
				return LightStateEnum.MAINTENANCE;
			case UNKNOWN:
				return LightStateEnum.UNKNOWN;
			default:
				return null;
		}
	}

	/**
	 * Translate status exception.
	 * 
	 * @param alarmWarningSubType the alarm warning sub type
	 * @return the status exception type enum
	 */
	private StatusExceptionTypeEnum translateStatusException(AlarmWarningSubType alarmWarningSubType)
	{
		switch (alarmWarningSubType)
		{
			case BROWN_OUT_DETECTED:
				return StatusExceptionTypeEnum.BROWN_OUT_DETECTED;
			case LAMP_FAILURE:
				return StatusExceptionTypeEnum.LAMP_FAILURE;
			case POWER_FAILURE:
				return StatusExceptionTypeEnum.POWER_FAILURE;
			case POWER_SURGE_DETECTED:
				return StatusExceptionTypeEnum.POWER_SURGE_DETECTED;
			case METROLOGY_ERROR:
				return StatusExceptionTypeEnum.METROLOGY_ERROR;
			case METROLOGY_COM_FAILURE:
				return StatusExceptionTypeEnum.METROLOGY_COM_FAILURE;
			case HIGH_CURRENT:
				return StatusExceptionTypeEnum.HIGH_CURRENT;
			case LOW_CURRENT:
				return StatusExceptionTypeEnum.LOW_CURRENT;
			case REVERSE_ENERGY:
				return StatusExceptionTypeEnum.REVERSE_ENERGY;
			case METROLOGY_RESET:
				return StatusExceptionTypeEnum.METROLOGY_RESET;
			case BOARD_FAILURE:
				return StatusExceptionTypeEnum.BOARD_FAILURE;
			default:
				return null;
		}
	}

	/**
	 * Translate data type.
	 * 
	 * @param dataType the data type
	 * @return the light detail data type enum
	 */
	private LightDetailDataTypeEnum translateDataType(DataType dataType)
	{
		switch (dataType)
		{
			case STATUS_DATA:
				return LightDetailDataTypeEnum.STATUS;
			case CONFIGURATION_DATA:
				return LightDetailDataTypeEnum.CONFIGURATION;
			default:
				return null;
		}
	}

	/**
	 * Translate light intensity.
	 * 
	 * @param lightIntensity the light intensity
	 * @return the light intensity enum
	 */
	private LightIntensityEnum translateLightIntensity(LightLevel lightIntensity)
	{
		switch (lightIntensity)
		{
			case LEVEL_0:
				return LightIntensityEnum.LEVEL_0;
			case LEVEL_1:
				return LightIntensityEnum.LEVEL_1;
			case LEVEL_2:
				return LightIntensityEnum.LEVEL_2;
			case LEVEL_3:
				return LightIntensityEnum.LEVEL_3;
			case LEVEL_4:
				return LightIntensityEnum.LEVEL_4;
			case LEVEL_5:
				return LightIntensityEnum.LEVEL_5;
			case LEVEL_6:
				return LightIntensityEnum.LEVEL_6;
			default:
				return null;
		}
	}

	/**
	 * Contains rni in list.
	 * 
	 * @param list the list
	 * @param rniId the rni id
	 * @return the smartpoint
	 */
	private Smartpoint containsRniInList(List<Smartpoint> list, Integer rniId)
	{
		if (ValidationUtil.isNullOrEmpty(list) || ValidationUtil.isNull(rniId))
		{
			return null;
		}

		for (Smartpoint sp : list)
		{
			if (rniId.equals(sp.getRniId()))
			{
				return sp;
			}
		}

		return null;
	}

	/**
	 * List detail contains.
	 * 
	 * @param list the list
	 * @param rniId the rni id
	 * @return true, if successful
	 */
	private boolean listDetailContains(List<LightDetail> list, Integer rniId)
	{
		for (LightDetail detail : list)
		{
			if (detail.getRniId().intValue() == rniId.intValue())
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param rniCode the rni code
	 * @return the tenant
	 */
	private Tenant getTenant(String rniCode)
	{
		LightingControlRequest request = new LightingControlRequest(new UserContext());
		request.setTenant(new Tenant(rniCode));

		TenantResponse response = getProcessBCF().fetchTenantByRniCode(request);

		if (response.isOperationSuccess())
		{
			return response.getTenant();
		}
		else
		{
			return new Tenant();
		}
	}

	/**
	 * Tries to retrieve the light from LC. If light does not exist, returns a new Light with only rni_Id set.
	 * 
	 * @param rniId the rni id
	 * @return the light
	 */
	private Light getLight(Integer rniId)
	{
		Light light = new Light(rniId);

		LightRequest request = new LightRequest(new UserContext());
		request.addLight(light);

		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchLightByRniId(request);

		if (response.hasResults())
		{
			return response.getFirstResult();
		}
		return light;
	}

	/**
	 * Sets the user context for System generated actions.
	 * 
	 * @return the user context
	 */
	private UserContext getUserContext()
	{
		UserContext result = new UserContext();
		result.setUserId(getSystemId());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processApplyDimmingConfigurationNotification(com.sensus.mlc.mlcserver
	 * .type.ApplyDimmingConfigurationNotification)
	 */
	@Override
	public InternalResponse processApplyDimmingConfigurationNotification(
			ApplyDimmingConfigurationNotification notification)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		// Update process
		Process process = updateProcess(notification);

		if (ValidationUtil.isNull(process))
		{

			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processUpdateLightStatusNotification(com.sensus.mlc.mlcserver.type
	 * .UpdateLightStatusNotification)
	 */
	@Override
	public InternalResponse processUpdateLightStatusNotification(UpdateLightStatusNotification updateStatusNotification)
	{
		// Instantiate a response
		InternalResponse response = new InternalResponse();

		Process process = updateProcess(updateStatusNotification);

		if (!ValidationUtil.isNull(process))
		{
			LightRequest request = prepareProcessLightRequestForUpdateLightStatusNotification(
					updateStatusNotification, process);

			if (!ValidationUtil.isNullOrEmpty(request.getLights()))
			{
				InternalResultsResponse<Light> lightResponse =
						getSmartPointUpdaterBCL().updateLightStatus(request);

				if (lightResponse.isInError())
				{
					response.setStatus(Status.SystemError);
					response.addMessages(lightResponse.getMessageInfoList());
				}
			}
		}
		else
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		}

		return response;
	}

	/**
	 * Prepare process light request for update light status notification.
	 * 
	 * @param notification the notification
	 * @param process the process
	 * @return the light request
	 */
	private LightRequest prepareProcessLightRequestForUpdateLightStatusNotification(
			UpdateLightStatusNotification notification, Process process)
	{
		LightRequest request = new LightRequest(getUserContext());

		request.setTenant(getTenant(notification.getCustomerID()));

		for (LCActionParameter actionParameter : process.getLcAction().getActionParameters())
		{
			if (!ValidationUtil.isNull(actionParameter.getActionValue()))
			{
				if (actionParameter.getProperty().equals(PropertyEnum.DEACTIVATED))
				{
					request.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
				}
				else if (actionParameter.getProperty().equals(PropertyEnum.ACTIVE))
				{
					request.setLightStatusEnum(LightStatusEnum.ACTIVE);
				}
				else if (actionParameter.getProperty().equals(PropertyEnum.MAINTENANCE))
				{
					request.setLightStatusEnum(LightStatusEnum.MAINTENANCE);
				}
			}
		}

		if (!ValidationUtil.isNull(request.getLightStatusEnum()))
		{
			// Add lights to the request (lights that succeeded)
			for (ProcessItem processItem : process.getProcessItems())
			{
				if (!ValidationUtil.isNull(containsRniInList(
						((MlcGatewayNotification)notification).getSuccededSmartpoint(),
						processItem.getLight().getRniId())))
				{
					Light light = getLight(processItem.getLight().getRniId());
					request.getLights().add(light);
				}
			}
		}

		return request;
	}
}
