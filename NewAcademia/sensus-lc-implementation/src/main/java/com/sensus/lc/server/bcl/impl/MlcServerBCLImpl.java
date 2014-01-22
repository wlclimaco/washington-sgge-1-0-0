package com.sensus.lc.server.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.server.util.MlcServerDataUtil.parseModelNumber;
import static com.sensus.lc.server.util.MlcServerDataUtil.prepareTime;
import static com.sensus.lc.server.util.MlcServerDataUtil.translateAlertSubtype;
import static com.sensus.lc.server.util.MlcServerDataUtil.translateDataType;
import static com.sensus.lc.server.util.MlcServerDataUtil.translateLightIntensity;
import static com.sensus.lc.server.util.MlcServerDataUtil.translateLightState;
import static com.sensus.lc.server.util.MlcServerDataUtil.translateLightTopLevelState;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.TimeZoneInfo;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.bcf.ILightProcessorBCF;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.LightStateEnum;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.OperationalDataTypeEnum;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SerialNumberElement;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.light.model.response.AlarmNotificationResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.ScheduleResponse;
import com.sensus.lc.server.bcl.IMlcServerBCL;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
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
import com.sensus.mlc.mlcserver.type.InstallationInfo;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.MlcGatewayDataNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewayNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;

/**
 * The Class MlcServerBCLImpl.
 */
public class MlcServerBCLImpl implements IMlcServerBCL
{

	private static final String NONE = "NONE";

	private static final Log LOG = LogFactory.getLog(MlcServerBCLImpl.class);

	private static final String LIGHT_BINDING_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.binding.notification.required";
	private static final String LIGHT_SETUP_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.setup.notification.required";
	private static final String LIGHT_STATUS_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.status.notification.required";
	private static final String LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.light.alarm.warning.notification.required";
	private static final String APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.apply.smartpoint.properties.notification.required";
	private static final String LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED =
			"sensus.mlc.gateway.channel.setup.audit.notification.required";
	private static final String PROCESS_REQUIRED = "sensus.mlc.gateway.process.required";
	private static final String PROCESS_ITEMS_FOR_LIGHT_REQUIRED =
			"sensus.mlc.gateway.process.items.for.light.required";
	private static final Integer UNKNOWN_EVENT_SCHEDULE = 2;
	private static final Integer UNKNOWN_OFFSET_SCHEDULE = 1;

	private Float currentConversionFactor;
	private Float voltageConversionFactor;

	private List<SerialNumberElement> serialNumberLayout; // injected via Spring
	private String systemId; // injected via Spring
	private String systemName; // injected via Spring
	private String systemPwd; // injected via Spring

	private IScheduleBCF scheduleBCF; // injected via Spring
	private IProcessBCF processBCF; // injected via Spring
	private ILightProcessorBCF lightProcessorBCF; // injected via Spring
	private ILightBCL lightBCL; // injected via Spring
	private ITenantBCL tenantBCL; // injected via Spring

	/**
	 * @return the lightProcessorBCF
	 */
	public ILightProcessorBCF getLightProcessorBCF()
	{
		return lightProcessorBCF;
	}

	/**
	 * @param lightProcessorBCF the lightProcessorBCF to set
	 */
	public void setLightProcessorBCF(ILightProcessorBCF lightProcessorBCF)
	{
		this.lightProcessorBCF = lightProcessorBCF;
	}

	/**
	 * @return the lightBCL
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * @param lightBCL the lightBCL to set
	 */
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Gets the tenant bcl.
	 * 
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 * 
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
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
		return processBCF;
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
		return scheduleBCF;
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId()
	{
		return systemId;
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
		return systemName;
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
		return systemPwd;
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
		return serialNumberLayout;
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
	 * Gets the Current Conversion factor.
	 * 
	 * @return the Current Conversion factor.
	 */
	public Float getCurrentConversionFactor()
	{
		return currentConversionFactor;
	}

	/**
	 * Sets the Current Conversion factor.
	 * 
	 * @param currentConversionFactorValue the new current conversion factor
	 */
	public void setCurrentConversionFactor(Float currentConversionFactorValue)
	{
		currentConversionFactor = currentConversionFactorValue;
	}

	/**
	 * Gets the Voltage Conversion Factor.
	 * 
	 * @return the Voltage Conversion Factor
	 */
	public Float getVoltageConversionFactor()
	{
		return voltageConversionFactor;
	}

	/**
	 * Sets the Voltage Conversion Factor.
	 * 
	 * @param voltageConversionFactorValue the new voltage conversion factor
	 */
	public void setVoltageConversionFactor(Float voltageConversionFactorValue)
	{
		voltageConversionFactor = voltageConversionFactorValue;
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
		LightStatusNotificationRequest request = prepareRequestToLightStatusNotification(notification);
		if (isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_STATUS_NOTIFICATION_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		Response response = getLightProcessorBCF().processLightStatusNotification(request);
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
	public InternalResponse processAlarmWarning(AlarmWarningNotification notification)
	{
		// Instantiate a response
		InternalResponse internalResponse = new InternalResponse();

		// Prepares the request that goes to LC
		AlarmNotificationRequest request = prepareAlarmWarningNotificationRequest(notification);
		if (isNull(request))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_ALARM_WARNING_NOTIFICATION_REQUIRED, MessageSeverity.Fatal,
					MessageLevel.Object);
			return internalResponse;
		}

		// Call LC BCF
		AlarmNotificationResponse response = getLightProcessorBCF().processAlarmWarningNotification(request);
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
	 * com.sensus.mlc.mlcserver.bcl.IMlcServerBCL#processLightSetup(com.sensus.mlc.mlcserver.type.LightSetupNotification
	 * )
	 */
	@Override
	public InternalResponse processLightSetup(LightSetupNotification notification)
	{
		InternalResponse internalResponse = new InternalResponse();

		// This goes to BCF and is LC specific
		LightSetupNotificationRequest request = new LightSetupNotificationRequest(getUserContext());
		request.setTenant(getTenant(notification.getCustomerID()));

		if (isNull(notification.getSmartpoint()))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_SETUP_NOTIFICATION_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		Light light = fetchLightByRniId(BigInteger.valueOf(notification.getSmartpoint().getRniId()));
		if (isNull(light))
		{
			return internalResponse;
		}

		// Set message date sent by rni
		request.setMessageDate(configureMessageDate(notification, light));
		request.setLight(light);
		Response response = getLightProcessorBCF().processLightSetupNotification(request);
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
		ChannelSetupAuditRequest request = new ChannelSetupAuditRequest(new UserContext(getSystemName()));
		request.setTenant(getTenant(notification.getCustomerID()));

		if (isNull(notification.getSmartpoint())
				|| isNull(notification.getSmartpointDetail()))
		{
			return internalResponse;
		}

		request.setLifeCycleState(
				translateLightTopLevelState(
				notification.getSmartpointDetail().getLightTopLevelState()));

		// Create a light with just RniId OR get the light if it exists already
		Light light = fetchLightByRniId(BigInteger.valueOf(notification.getSmartpoint().getRniId()));
		if (isNull(light))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(LIGHT_CHANNEL_SETUP_AUDIT_NOTIFICATION_REQUIRED, MessageSeverity.Fatal,
					MessageLevel.Object);
			return internalResponse;
		}

		// Set message date sent by rni
		request.setMessageDate(configureMessageDate(notification, light));
		request.setLight(light);

		Response response = getLightProcessorBCF().processChannelSetupAuditNotification(request);
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
		LightStatusNotificationRequest notificationRequest = prepareRequestToLightBindingNotification(notification);
		if (isNull(notificationRequest))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse
					.addMessage(LIGHT_BINDING_NOTIFICATION_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		Response response = getLightProcessorBCF().processLightBindingNotification(notificationRequest);
		if (!response.isOperationSuccess())
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessages(response.getMessageInfoList());
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

		if (!isNull(process))
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

			ScheduleResponse scheduleResponse = getScheduleBCF().deleteLightFromSchedule(scheduleRequest);

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

		if (!isNull(process))
		{
			// Update MLC Data
			ScheduleRequest scheduleRequest = prepareScheduleRequest(process, notification);

			ScheduleResponse scheduleResponse = getScheduleBCF().applyLightToSchedule(scheduleRequest);
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
		if (!isNull(process))
		{
			LightIntensityRequest request =
					prepareLightRequestForApplyLightLevelNotification(notification, response, process);

			// Update if the list of lights is not Null or Empty
			if (!response.isInError() && !isNullOrEmpty(request.getLightList()))
			{
				Response response2 =
						getLightProcessorBCF().processSetLightIntensityNotification(request);
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

		if (isNull(process))
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
	public InternalResponse processReadLightStatus(ReadLightStatusNotification notification)
	{
		InternalResponse response = new InternalResponse();

		// Updating the process.
		Process process = updateProcess(notification);

		if (isNull(process))
		{
			response.setStatus(Status.SystemError);
			response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return response;
		}

		List<ProcessItem> processItems = process.getProcessItems();

		if (isNullOrEmpty(processItems))
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
		for (LightDetail lightDetail : notification.getLightDetail())
		{
			Response statusResponse =
					getLightProcessorBCF().processLightStatusNotification(
							prepareForcedLightStatusNotificationRequest(notification, lightDetail));

			if (!statusResponse.isOperationSuccess())
			{
				response.getMessageInfoList().addAll(statusResponse.getMessageInfoList());
				response.setStatus(Status.SystemError);
				return response;
			}
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
		InternalResponse response = new InternalResponse();

		Process process = updateProcess(updateStatusNotification);

		if (!isNull(process))
		{
			UpdateLightNotificationRequest request = prepareProcessLightRequestForUpdateLightStatusNotification(
					updateStatusNotification, process);

			if (!isNullOrEmpty(request.getLightList()))
			{
				Response responseUpdateLightStatus =
						getLightProcessorBCF().processUpdateLightStatusNotification(request);

				if (!responseUpdateLightStatus.isOperationSuccess())
				{
					response.setStatus(Status.SystemError);
					response.addMessages(responseUpdateLightStatus.getMessageInfoList());
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
		if (!isNull(process))
		{

			ProcessLightsRequest request = new ProcessLightsRequest(getUserContext());
			request.setTenant(getTenant(notification.getCustomerID()));

			// Add lights to the request (lights that succeeded)
			for (ProcessItem processItem : process.getProcessItems())
			{

				if (!isNull(containsRniInList(
						notification.getSuccededSmartpoint(),
						processItem.getLight().getRadio().getFlexNetId().intValue())))
				{
					Light light = fetchLightByRniId(processItem.getLight().getRadio().getFlexNetId());

					// Add properties to light
					addPropertiesToLight(light, process.getLcAction().getActionParameters());
					request.getLights().add(light);

					// Set message date sent by rni
					request.setMessageDate(configureMessageDate(notification, light));
				}
			}

			if (isNull(request))
			{
				response.setStatus(Status.SystemError);
				response.addMessage(APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION_REQUIRED, MessageSeverity.Fatal,
						MessageLevel.Object);
				return response;
			}

			// Call BCF
			Response bcfResponse = getLightProcessorBCF().processApplySmartpointPropertiesNotification(request);
			if (!bcfResponse.isOperationSuccess())
			{
				response.setStatus(Status.SystemError);
				response.addMessages(bcfResponse.getMessageInfoList());
			}
			return response;
		}

		response.setStatus(Status.SystemError);
		response.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
		return response;
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
		if (isNull(process))
		{
			internalResponse.setStatus(Status.SystemError);
			internalResponse.addMessage(PROCESS_REQUIRED, MessageSeverity.Fatal, MessageLevel.Object);
			return internalResponse;
		}

		// Update MLC Data
		AlarmNotificationRequest request = new AlarmNotificationRequest(getUserContext());
		request.setTenant(getTenant(notification.getCustomerID()));

		// The real status will be determined when the message is processed by the smartpoint bcl
		// Process for each light that succeeded
		for (Smartpoint smartpoint : notification.getSuccededSmartpoint())
		{
			// Get light
			Light light = fetchLightByRniId(BigInteger.valueOf(smartpoint.getRniId()));

			// Add subtypes to the message
			request.setAlertSubTypes(getAlertSubtypesBelongsLight(process, light));
			request.setLight(light);

			// Set message date sent by rni
			request.setMessageDate(configureMessageDate(notification, light));

			// Call Process
			AlarmNotificationResponse alarmNotificationResponse =
					getLightProcessorBCF().processClearAlarmNotification(request);
			if (!alarmNotificationResponse.isOperationSuccess())
			{
				internalResponse.setStatus(Status.SystemError);
				internalResponse.addMessages(alarmNotificationResponse.getMessageInfoList());
			}
		}

		return internalResponse;
	}

	/**
	 * Sets the datetime to request.
	 * 
	 * @param notification the notification
	 * @param light the light
	 * @return the date
	 */
	private Date configureMessageDate(MlcGatewayResponse notification, Light light)
	{
		// If rni didn't send date, then get utc system date as default
		if (isNullOrEmpty(notification.getMessage())
				|| isNull(light)
				|| isNull(light.getRadio())
				|| isNull(light.getRadio().getLocation())
				|| isNull(light.getRadio().getLocation().getTimeZoneInfo()))
		{
			return LCDateUtil.getNewDateUTC();
		}

		TimeZoneInfo tzi = light.getRadio().getLocation().getTimeZoneInfo();
		Long offsetHour = tzi.getOffsetInHours() * -1;
		for (MessageInfo message : notification.getMessage())
		{
			if (!isNull(message.getMessageTime()))
			{
				Calendar calendar = message.getMessageTime().toGregorianCalendar();
				calendar.add(Calendar.HOUR, offsetHour.intValue());
				return calendar.getTime();
			}
		}
		return LCDateUtil.getNewDateUTC();
	}

	/**
	 * Gets the alert subtypes belongs light.
	 * 
	 * @param process the process
	 * @param light the light
	 * @return the alert subtypes belongs light
	 */
	private List<AlertSubTypeEnum> getAlertSubtypesBelongsLight(Process process, Light light)
	{
		List<AlertSubTypeEnum> alertSubtypeList = new ArrayList<AlertSubTypeEnum>();
		if (isNullOrEmpty(process.getLcAction().getActionParameters())
				|| isNull(light.getLastNotificationHistory())
				|| isNullOrEmpty(light.getLastNotificationHistory().getAlertClassifications()))
		{
			return alertSubtypeList;
		}

		List<LCActionParameter> actionParameters = process.getLcAction().getActionParameters();
		List<AlertClassification> alerts = light.getLastNotificationHistory().getAlertClassifications();
		for (AlertClassification alertClassification : alerts)
		{
			Integer alertSutypeValue = alertClassification.getAlertSubTypeValue();
			if (!existAlertSubtype(actionParameters, alertSutypeValue))
			{
				alertSubtypeList.add(alertClassification.getAlertSubType());
			}
		}

		return alertSubtypeList;
	}

	/**
	 * Exist alert subtype.
	 * 
	 * @param actionParameters the action parameters
	 * @param alertSubtypeValue the exception value
	 * @return true, if successful
	 */
	private boolean existAlertSubtype(List<LCActionParameter> actionParameters, Integer alertSubtypeValue)
	{
		if (isNullOrEmpty(actionParameters))
		{
			return false;
		}

		for (LCActionParameter prop : actionParameters)
		{
			if (alertSubtypeValue.equals(Integer.parseInt(prop.getActionValue())))
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
	private LightStatusNotificationRequest prepareForcedLightStatusNotificationRequest(
			ReadLightStatusNotification notification,
			LightDetail lightDetail)
	{
		LightStatusNotificationRequest request = new LightStatusNotificationRequest(new UserContext(getSystemName()));
		request.setTenant(getTenant(notification.getCustomerID()));
		request.setForced(true);

		// 1) Get the full light object based on its RNI id.
		Light light = fetchLightByRniId(BigInteger.valueOf(lightDetail.getRniId()));
		request.setLight(light);

		// APP Code 11 - Light state, Alerts
		setAppCode11ToLight(request, lightDetail.getAppCode11Data());

		// APP Code 90 - set Sunrise, Sunset, AC Current/AC Voltage.
		setAppCode90ToLight(request, lightDetail.getAppCode90Data());

		// APP Code 71 - set customer serial number.
		setAppCode71ToLight(request, lightDetail.getAppCode71Data());

		// If additional CONFIG data was requested, process it.
		if (notification.getDataType().contains(DataType.CONFIGURATION_DATA))
		{
			// APP Code 94 - set Model Number, Lower Assembly S/N, Bulb S/N, Ballast S/N.
			setAppCode94ToLight(request, lightDetail.getAppCode94Data());

			// APP Code 85 - set Sunrise Offset, Sunset Offset.
			setAppCode85ToLight(request, lightDetail.getAppCode85Data());
		}

		// Copy DataType to request, translating first to LC types.
		for (DataType dataType : notification.getDataType())
		{
			request.getLightDetailDataType().add(translateDataType(dataType));
		}

		// Set message date sent by rni
		request.setMessageDate(configureMessageDate(notification, light));
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

	private LightIntensityRequest prepareLightRequestForApplyLightLevelNotification(
			ApplyLightLevelNotification notification,
			InternalResponse response, Process process)
	{
		LightIntensityRequest request = new LightIntensityRequest(new UserContext(getSystemName()));
		request.getUserContext().setTenant(getTenant(notification.getCustomerID()));
		Date overridePerDate = null;
		Integer override = OverrideEnum.NONE.getValue();
		String lightBlinkEnum = String.valueOf(BlinkStatusEnum.NONE.getValue());
		String percentage = null;

		// if it is a group light intensity, the light intensity is the second row at ActionParametersList
		LCAction lcAction = process.getLcAction();
		LCActionTypeEnum actionType = lcAction.getActionType();
		if (LCActionTypeEnum.TURN_OFF.equals(actionType)
				|| LCActionTypeEnum.TURN_ON.equals(actionType)
				|| LCActionTypeEnum.DIM.equals(actionType)
				|| LCActionTypeEnum.SET_BLINK_BY_LIGHT.equals(actionType)
				|| LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT.equals(actionType))
		{
			for (LCActionParameter actionParameter : lcAction.getActionParameters())
			{
				if (actionParameter.getProperty().equals(PropertyEnum.LIGHT_INTENSITY))
				{
					percentage = actionParameter.getActionValue();
				}
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
			List<Smartpoint> succededSmartpoint = notification.getSuccededSmartpoint();

			Light light = processItem.getLight();
			Smartpoint smartpoint = containsRniInList(succededSmartpoint, light.getRadio().getFlexNetId().intValue());
			if (isNull(light) || isNull(smartpoint))
			{
				continue;
			}

			addLightToRequest(request,
					light,
					actionType,
					overridePerDate,
					override,
					lightBlinkEnum,
					percentage,
					request.getUserContext(),
					smartpoint);

			// Set message date sent by rni
			request.setMessageDate(configureMessageDate(notification, light));
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

	private void addLightToRequest(
			LightIntensityRequest request,
			Light light,
			LCActionTypeEnum actionType,
			Date overridePerDate,
			Integer override,
			String lightBlinkEnum,
			String percentage,
			UserContext userContext,
			Smartpoint smartpoint)
	{
		light = fetchLightByRniId(light.getRadio().getFlexNetId());
		light.setBlinkStatus(BlinkStatusEnum.enumForValue(Integer.parseInt(lightBlinkEnum)));
		light.setOverrideTypeValue(override);

		if (!isNull(smartpoint.getLightLevel()))
		{
			IntensityEnum intensity = IntensityEnum.valueOf(smartpoint.getLightLevel().name());
			light.setIntensity(intensity);
		}
		if (LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT != actionType)
		{
			light.setOverrideCreateDate(LCDateUtil.getNewDateUTC());
		}
		if (!isNull(overridePerDate))
		{
			light.setOverridePerDate(overridePerDate);
		}

		if (!isNull(smartpoint.getLightState()))
		{
			LightStateEnum state = LightStateEnum.valueOf(smartpoint.getLightState().name());
			light.setDeviceLifeCycleState(state);
			if (LightStateEnum.BLINK.equals(state))
			{
				light.setIntensity(IntensityEnum.LEVEL_6);
			}
		}
		else
		{
			if (actionType == LCActionTypeEnum.TURN_OFF)
			{
				light.setDeviceLifeCycleState(LightStateEnum.OFF);
			}

			if (actionType == LCActionTypeEnum.TURN_ON)
			{
				light.setDeviceLifeCycleState(LightStateEnum.ON);
			}

			if (actionType == LCActionTypeEnum.DIM)
			{
				light.setDeviceLifeCycleState(LightStateEnum.ON);
				if (ValidationUtil.isNullOrZero(Integer.valueOf(percentage)))
				{
					light.setIntensity(IntensityEnum.LEVEL_0);
				}
				else
				{
					LightRequest lightRequest = new LightRequest();
					lightRequest.setUserContext(userContext);
					lightRequest.setActionCriteria(new ActionCriteria());
					lightRequest.getActionCriteria().setPercentage(Integer.parseInt(percentage));
					lightRequest.setLightCriteria(new LightCriteria());
					lightRequest.getLightCriteria().getLightIdList().add(light.getId());
					light.setIntensity(getLightBCL().fetchLightIntensityByLight(lightRequest));
				}
			}

			if (actionType == LCActionTypeEnum.SET_BLINK_BY_LIGHT)
			{
				light.setDeviceLifeCycleState(LightStateEnum.BLINK);
				light.setIntensity(IntensityEnum.LEVEL_6);
			}
		}

		request.getLightList().add(light);
	}

	/**
	 * Prepare alarm warning notification request.
	 * 
	 * @param notification the notification
	 * @return the alarm notification request
	 */
	private AlarmNotificationRequest prepareAlarmWarningNotificationRequest(AlarmWarningNotification notification)
	{
		// This goes to BCF -> MLC specific
		AppCode11Info smartpointDetail = notification.getSmartpointDetail();
		if (isNull(smartpointDetail) || isNull(notification.getSmartpoint()))
		{
			return null;
		}

		Light light = fetchLightByRniId(BigInteger.valueOf(notification.getSmartpoint().getRniId()));
		AlarmNotificationRequest request = new AlarmNotificationRequest(new UserContext(getSystemName()));
		request.setTenant(getTenant(notification.getCustomerID()));
		request.setLight(light);

		// Add alert subtypes.
		AppCode11Info appCode11Data = smartpointDetail;
		setAppCode11ToLight(request, appCode11Data);

		// Add life cycle state
		light.setLifeCycleState(translateLightTopLevelState(smartpointDetail.getLightTopLevelState()));
		request.setLifeCycleState(translateLightTopLevelState(smartpointDetail.getLightTopLevelState()));

		// Current, Voltage
		Date date = LCDateUtil.getNewDateUTC();
		LastOperationalData lastOperationalData = light.getLastOperationalData();
		Float current = getCurrentValueInAmpere(smartpointDetail.getCurrent());
		Float voltage = getVoltageValueInV(smartpointDetail.getVoltage());
		setCurrentAndCheckMinMax(date, lastOperationalData, current);
		setVoltageAndCheckMinMax(date, lastOperationalData, voltage);
		request.addOperationalData(new OperationalData(current, OperationalDataTypeEnum.CURRENT));
		request.addOperationalData(new OperationalData(voltage, OperationalDataTypeEnum.VOLTAGE));
		lastOperationalData.setModelAction(PersistanceActionEnum.UPDATE);
		lastOperationalData.setModifyDate(date);
		lastOperationalData.setModifyUser(getSystemName());

		// Set message date sent by rni
		request.setMessageDate(configureMessageDate(notification, light));
		return request;
	}

	/**
	 * Prepare light status notification request.
	 * 
	 * @param notification the notification
	 * @return the light status notification request
	 */
	private LightStatusNotificationRequest prepareRequestToLightBindingNotification(
			LightBindingNotification notification)
	{
		if (isNull(notification.getSmartpoint())
				|| isNull(notification.getSmartpoint().getRniId()))
		{
			return null;
		}

		// Create light
		Light light = new Light();
		light.getRadio().setFlexNetId(BigInteger.valueOf(notification.getSmartpoint().getRniId()));

		LightStatusNotificationRequest request = new LightStatusNotificationRequest(new UserContext(getSystemName()));
		request.setTenant(getTenant(notification.getCustomerID()));
		request.setTransactionId(notification.getTransactionID());
		request.setMessageDate(LCDateUtil.getNewDateUTC()); // SHOULD CHANGE TO USE NEW GATEWAY ATTRIBUTE.
		request.setLight(light);

		// Lat/Long/PoleId/Firmware version/TZ
		setAppCode86ToLight(request, notification.getSmartpointDetail(), notification.getInstallationDetail());

		// Set message date sent by rni
		request.setMessageDate(configureMessageDate(notification, light));
		return request;
	}

	/**
	 * Prepare request to light status notification.
	 * 
	 * @param notification the notification
	 * @return the light status notification request
	 */
	private LightStatusNotificationRequest prepareRequestToLightStatusNotification(LightStatusNotification notification)
	{
		if (isNull(notification.getSmartpoint())
				|| isNull(notification.getSmartpoint().getRniId()))
		{
			return null;
		}

		Light light = fetchLightByRniId(BigInteger.valueOf(notification.getSmartpoint().getRniId()));
		if (isNull(light))
		{
			return null;
		}

		// AppCode90 carries LightState info (ON/OFF/BLINK/MAINT)
		LightStateEnum state = translateLightState(notification.getSmartpointDetail().getLightState());
		light.setDeviceLifeCycleState(state);
		if (LightStateEnum.ON != state)
		{
			light.setIntensity(IntensityEnum.LEVEL_0);
		}

		if (!ValidationUtil.isNull(notification.getSmartpointDetail()) &&
				!ValidationUtil.isNull(notification.getSmartpointDetail().getLightLevel()))
		{
			light.setIntensity(IntensityEnum.enumForValue(Integer.valueOf(notification.getSmartpointDetail()
					.getLightLevel().value())));
		}

		LightStatusNotificationRequest request = new LightStatusNotificationRequest(new UserContext(getSystemName()));
		request.setLight(light);
		request.setLifeCycleState(light.getLifeCycleState());
		request.setTenant(getTenant(notification.getCustomerID()));
		request.setTransactionId(notification.getTransactionID());
		request.setMessageDate(LCDateUtil.getNewDateUTC()); // SHOULD CHANGE TO USE NEW GATEWAY ATTRIBUTE.

		// APP Code 90 - set Sunrise, Sunset, AC Current/AC Voltage.
		setAppCode90ToLight(request, notification.getSmartpointDetail());

		// Set message date sent by rni
		request.setMessageDate(configureMessageDate(notification, light));
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

		if (LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_EVENT.equals(process.getLcAction().getActionType()))
		{
			if (isNull(schedule))
			{
				schedule = new EventSchedule();
			}
			schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		}
		else if (LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_OFFSET.equals(process.getLcAction().getActionType()))
		{
			if (isNull(schedule))
			{
				schedule = new OffsetSchedule();
			}
			schedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		}
		else
		{
			if (LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_EVENT.equals(process.getLcAction().getActionType()))
			{
				schedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
			}
			else if (LCActionTypeEnum.ADD_LIGHT_TO_SCHEDULE_OFFSET.equals(process.getLcAction().getActionType()))
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
			Light light = fetchLightByRniId(BigInteger.valueOf(smartpoint.getRniId()));
			schedule.getLights().add(light.getId());
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
		ProcessRequest processRequest = new ProcessRequest(new UserContext(getSystemName()));
		Process process = new Process();
		process.setRniCorrelationId(notification.getTransactionID());
		processRequest.setProcess(process);
		processRequest.getUserContext().setTenant(getTenant(notification.getCustomerID()));

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

					if (!isNull(containsRniInList(
							((MlcGatewayNotification)notification).getSuccededSmartpoint(), processItem.getLight()
									.getRadio().getFlexNetId().intValue())))
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

					if (listDetailContains(((MlcGatewayDataNotification)notification).getLightDetail(), processItem
							.getLight().getRadio().getFlexNetId().intValue()))
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
			processRequest.getProcessList().clear();
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
			if (isNull(process))
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

		if (actionType == LCActionTypeEnum.TURN_OFF || actionType == LCActionTypeEnum.TURN_ON
				|| actionType == LCActionTypeEnum.DIM || actionType == LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT)
		{
			// If it is a LightLRP, use the intensity that came back from the notification.
			// It should be better to have a ForcedStatusLRP type to avoid this
			if (isNull(process.getLcAction().getActionParameters().get(0).getActionValue())
					&& notification instanceof ReadLightStatusNotification)
			{
				process.getLcAction()
						.getActionParameters()
						.get(0)
						.setActionValue(
								translateLightIntensity(((ReadLightStatusNotification)notification)
										.getLightDetail().get(0).getAppCode90Data().getLightLevel()).toString());
			}
		}
		else if (actionType == LCActionTypeEnum.SET_INTENSITY_BY_GRP)
		{
			if (isNull(process.getLcAction().getActionParameters().get(1).getActionValue())
					&& notification instanceof ReadLightStatusNotification)
			{
				process.getLcAction()
						.getActionParameters()
						.get(1)
						.setActionValue(
								translateLightIntensity(((ReadLightStatusNotification)notification)
										.getLightDetail().get(0).getAppCode90Data().getLightLevel()).toString());
			}
		}

		return process;
	}

	/**
	 * Adds the properties to light.
	 * 
	 * @param light the light
	 * @param data the data
	 */
	private void addPropertiesToLight(Light light, List<LCActionParameter> data)
	{
		if (isNullOrEmpty(data))
		{
			return;
		}

		// reset values
		light.getRadio().setLocation(new Location());
		light.setPoleId(null);

		// get information from process (lat/long/poleid) and add to light
		for (LCActionParameter actionParm : data)
		{
			if (PropertyEnum.LATITUDE == actionParm.getProperty())
			{
				light.getRadio().getLocation().setLatitude(Double.valueOf(actionParm.getActionValue()));
				continue;
			}
			if (PropertyEnum.LONGITUDE == actionParm.getProperty())
			{
				light.getRadio().getLocation().setLongitude(Double.valueOf(actionParm.getActionValue()));
				continue;
			}
			if (PropertyEnum.POLE_ID == actionParm.getProperty())
			{
				light.setPoleId(actionParm.getActionValue());
			}
		}
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
	 * Contains rni in list.
	 * 
	 * @param list the list
	 * @param rniId the rni id
	 * @return the smartpoint
	 */
	private Smartpoint containsRniInList(List<Smartpoint> list, Integer rniId)
	{
		if (isNullOrEmpty(list) || isNull(rniId))
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
		TenantRequest request = new TenantRequest();
		request.setTenant(new Tenant(rniCode));

		InternalResultsResponse<Tenant> response = getTenantBCL().fetchTenantByRniCode(request);
		if (response.isInError())
		{
			return new Tenant();
		}
		return response.getFirstResult();
	}

	/**
	 * Fetch light by rni id.
	 * 
	 * @param rniId the rni id
	 * @return the light
	 */
	private Light fetchLightByRniId(BigInteger rniId)
	{
		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(null);
		fetchByIdRequest.setRniId(rniId);
		InternalResultsResponse<Light> lightResponse = getLightBCL().fetchById(fetchByIdRequest);
		return lightResponse.getFirstResult();
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

	/**
	 * Prepare process light request for update light status notification.
	 * 
	 * @param notification the notification
	 * @param process the process
	 * @return the light request
	 */

	private UpdateLightNotificationRequest prepareProcessLightRequestForUpdateLightStatusNotification(
			UpdateLightStatusNotification notification, Process process)
	{
		UserContext userContext = getUserContext();
		userContext.setTenant(getTenant(notification.getCustomerID()));

		UpdateLightNotificationRequest request = new UpdateLightNotificationRequest();
		request.setUserContext(userContext);

		for (LCActionParameter actionParameter : process.getLcAction().getActionParameters())
		{
			if (isNull(actionParameter.getActionValue()))
			{
				continue;
			}
			request.setLifeCycleState(LifeCycleStateEnum.valueOf(actionParameter.getProperty().name()));
		}

		if (isNull(request.getLifeCycleState()))
		{
			return request;
		}

		// Add lights to the request (lights that succeeded)
		for (ProcessItem processItem : process.getProcessItems())
		{
			if (!isNull(containsRniInList(
					notification.getSuccededSmartpoint(),
					processItem.getLight().getRadio().getFlexNetId().intValue())))
			{
				Light light = fetchLightByRniId(processItem.getLight().getRadio().getFlexNetId());
				request.getLightList().add(light);

				// Set message date sent by rni
				request.setMessageDate(configureMessageDate(notification, light));
			}
		}
		return request;
	}

	/**
	 * Sets the app code11 to light.
	 * 
	 * @param request the request
	 * @param appCode11Data the app code11 data
	 */
	private void setAppCode11ToLight(AlarmNotificationRequest request, AppCode11Info appCode11Data)
	{
		// Set informations to light
		Light light = request.getLight();
		if (LightStateEnum.ON != light.getDeviceLifeCycleState())
		{
			light.setIntensity(IntensityEnum.LEVEL_0);
		}

		if (isNull(appCode11Data))
		{
			return;
		}

		// light status (ACTIVE/MAINT/IDLE/UNKNOWN) - Top Level State
		light.setDeviceLifeCycleState(translateLightState(appCode11Data.getLightState()));
		light.setLifeCycleState(translateLightTopLevelState(appCode11Data.getLightTopLevelState()));
		request.setLifeCycleState(translateLightTopLevelState(appCode11Data.getLightTopLevelState()));
		if (isNullOrEmpty(appCode11Data.getAlarmWarningMessages()))
		{
			return;
		}

		for (AlarmWarningInfo info : appCode11Data.getAlarmWarningMessages())
		{
			AlertSubTypeEnum subtype = translateAlertSubtype(info.getAlarmWarningSubType());
			if (request.getAlertSubTypes().contains(subtype))
			{
				continue;
			}
			request.getAlertSubTypes().add(subtype);
		}
	}

	/**
	 * Sets the app code90 to light.
	 * 
	 * @param request the request
	 * @param appCode90Data the app code90 info
	 */
	private void setAppCode90ToLight(AlarmNotificationRequest request, AppCode90Info appCode90Data)
	{
		if (isNull(appCode90Data))
		{
			return;
		}

		// Mark to update informations
		Light light = request.getLight();
		Date date = LCDateUtil.getNewDateUTC();
		UserContext userContext = request.getUserContext();
		if (isNull(light.getLightSchedule()))
		{
			light.setLightSchedule(new LightSchedule());
			light.getLightSchedule().setModelAction(PersistanceActionEnum.INSERT);
			light.getLightSchedule().setCreateDate(date);
			light.getLightSchedule().setCreateUser(userContext.getUserId());
		}
		else
		{
			light.getLightSchedule().setModelAction(PersistanceActionEnum.UPDATE);
			light.getLightSchedule().setModifyDate(date);
			light.getLightSchedule().setModifyUser(userContext.getUserId());
		}

		// Sunrise time
		light.getLightSchedule().setSunriseTime(
				prepareTime(
						appCode90Data.getSunriseHour(),
						appCode90Data.getSunriseMinute(),
						request.getTenant()));
		// Sunset time
		light.getLightSchedule().setSunsetTime(
				prepareTime(
						appCode90Data.getSunsetHour(),
						appCode90Data.getSunsetMinute(),
						request.getTenant()));

		prepareOpDataListFromAppCode90Data(light, request.getOperationalData(), appCode90Data, userContext);
	}

	/**
	 * Sets the app code71 to light.
	 * 
	 * @param request the request
	 * @param appCode71Data the app code71 data
	 */
	private void setAppCode71ToLight(AlarmNotificationRequest request, AppCode71Info appCode71Data)
	{
		if (isNull(appCode71Data) || isNull(appCode71Data.getCustomerSerial()))
		{
			return;
		}

		// Mark to update informations
		Light light = request.getLight();
		if (isNull(light.getConfiguration()))
		{
			light.setConfiguration(new Configuration());
			light.getConfiguration().setModelAction(PersistanceActionEnum.INSERT);
		}
		else if (PersistanceActionEnum.NONE == light.getConfiguration().getModelAction())
		{
			light.getConfiguration().setModelAction(PersistanceActionEnum.UPDATE);
		}

		light.getConfiguration().setCustomerSerialNumber(appCode71Data.getCustomerSerial());
	}

	/**
	 * Sets the app code94 to light.
	 * 
	 * @param request the request
	 * @param appCode94Data the app code94 data
	 */
	private void setAppCode94ToLight(AlarmNotificationRequest request,
			AppCode94Info appCode94Data)
	{
		if (isNull(appCode94Data))
		{
			return;
		}

		// Mark to update informations
		Light light = request.getLight();
		if (isNull(light.getConfiguration()))
		{
			light.setConfiguration(new Configuration());
			light.getConfiguration().setModelAction(PersistanceActionEnum.INSERT);
		}
		else if (PersistanceActionEnum.NONE == light.getConfiguration().getModelAction())
		{
			light.getConfiguration().setModelAction(PersistanceActionEnum.UPDATE);
		}

		if (isNull(appCode94Data.getProductNumber()))
		{
			return;
		}

		// APP Code 94 - set Model Number, Lower Assembly S/N, Bulb S/N, Ballast S/N.
		parseModelNumber(appCode94Data.getProductNumber(), getSerialNumberLayout(), light);

		light.getConfiguration().setModelNumber(appCode94Data.getProductNumber());
		light.getConfiguration().setLowerAssemblySerial(NONE);
		light.getConfiguration().setUpperAssemblySerial(NONE);
		light.getConfiguration().setBallastSerialNumber(NONE);
		light.getConfiguration().setBulbSerialNumber(NONE);

		if (!ValidationUtil.isNullOrEmpty(appCode94Data.getLowerCaseSerial()))
		{
			light.getConfiguration().setLowerAssemblySerial(appCode94Data.getLowerCaseSerial());
		}

		if (!ValidationUtil.isNullOrEmpty(appCode94Data.getUpperCaseSerial()))
		{
			light.getConfiguration().setUpperAssemblySerial(appCode94Data.getUpperCaseSerial());
		}

		if (!ValidationUtil.isNullOrEmpty(appCode94Data.getBallastSerial()))
		{
			light.getConfiguration().setBallastSerialNumber(appCode94Data.getBallastSerial());
		}

		if (!ValidationUtil.isNullOrEmpty(appCode94Data.getBulbSerial()))
		{
			light.getConfiguration().setBulbSerialNumber(appCode94Data.getBulbSerial());
		}
	}

	/**
	 * Sets the app code85 to light.
	 * 
	 * @param request the request
	 * @param appCode85Data the app code85 data
	 */
	private void setAppCode85ToLight(AlarmNotificationRequest request, AppCode85Info appCode85Data)
	{
		if (isNull(appCode85Data))
		{
			return;
		}

		// Mark to update informations
		Light light = request.getLight();
		if (isNull(light.getLightSchedule()))
		{
			light.setLightSchedule(new LightSchedule());
			light.getLightSchedule().setModelAction(PersistanceActionEnum.INSERT);
		}
		else if (PersistanceActionEnum.NONE == light.getLightSchedule().getModelAction())
		{
			light.getLightSchedule().setModelAction(PersistanceActionEnum.UPDATE);
		}

		light.getLightSchedule().setSunriseOffset(appCode85Data.getSunriseOffset());
		light.getLightSchedule().setSunsetOffset(appCode85Data.getSunsetOffset());
	}

	/**
	 * Sets the app code86 to light.
	 * 
	 * @param request the request
	 * @param appCode86Data the app code86 data
	 * @param installationInfo
	 */
	private void setAppCode86ToLight(AlarmNotificationRequest request, AppCode86Info appCode86Data,
			InstallationInfo installationInfo)
	{
		if (isNull(appCode86Data))
		{
			return;
		}

		TimeZoneInfo timezone = null;
		if (!isNull(installationInfo) && !isNull(installationInfo.getTimezoneRegion()))
		{
			timezone = new TimeZoneInfo(installationInfo.getTimezoneRegion());
		}

		Light light = request.getLight();
		light.getRadio().getLocation().setTimeZoneInfo(timezone);
		light.getRadio().getLocation().setLatitude(appCode86Data.getLatitude());
		light.getRadio().getLocation().setLongitude(appCode86Data.getLongitude());
		light.setPoleId(appCode86Data.getPoleId());
		light.getConfiguration().setFirmwareVersion(
				prepareFirmwareVersionString(
						appCode86Data.getDeviceVersionMajor(),
						appCode86Data.getDeviceVersionMinor(),
						appCode86Data.getDeviceVersionPatch()));
	}

	/**
	 * Prepare op data list from app code90 data.
	 * 
	 * @param data the data
	 * @return the list
	 */
	private void prepareOpDataListFromAppCode90Data(
			Light light,
			List<OperationalData> operationalDataList,
			AppCode90Info data,
			UserContext userContext)
	{
		Date date = LCDateUtil.getNewDateUTC();
		LastOperationalData lastOperationalData = light.getLastOperationalData();
		if (isNull(lastOperationalData))
		{
			light.setLastOperationalData(new LastOperationalData());
			lastOperationalData.setCreateUser(userContext.getUserId());
			lastOperationalData.setCreateDate(date);
			lastOperationalData.setModifyDate(date);
			lastOperationalData.setModelAction(PersistanceActionEnum.INSERT);
		}
		else
		{
			lastOperationalData.setModifyUser(userContext.getUserId());
			lastOperationalData.setModifyDate(date);
			lastOperationalData.setModelAction(PersistanceActionEnum.UPDATE);
		}

		if (isNull(operationalDataList))
		{
			operationalDataList = new ArrayList<OperationalData>();
		}

		// AppCode90 carries Consumption,Voltage,Current info
		if (!isNull(data.getCurrent()))
		{
			Float current = getCurrentValueInAmpere(data.getCurrent());
			operationalDataList.add(new OperationalData(current, OperationalDataTypeEnum.CURRENT));
			setCurrentAndCheckMinMax(date, lastOperationalData, current);
		}

		if (!isNull(data.getVoltage()))
		{
			// AppCode90 carries Consumption,Voltage,Current info
			Float voltage = getVoltageValueInV(data.getVoltage());
			operationalDataList.add(new OperationalData(voltage, OperationalDataTypeEnum.VOLTAGE));
			setVoltageAndCheckMinMax(date, lastOperationalData, voltage);
		}

		if (!isNull(data.getConsumption()))
		{
			Float consumption = new Float(data.getConsumption());
			operationalDataList.add(new OperationalData(consumption, OperationalDataTypeEnum.CUMULATIVECONSUMPTION));
			setConsumptionAndCheckMinMax(date, lastOperationalData, consumption);
		}
	}

	/**
	 * Sets the current and check min max.
	 * 
	 * @param date the date
	 * @param lastOperationalData the last operational data
	 * @param current the current
	 */
	private void setCurrentAndCheckMinMax(Date date, LastOperationalData lastOperationalData, Float current)
	{
		lastOperationalData.setAcCurrent(current);
		if (isNull(lastOperationalData.getAcCurrentMax()) || current > lastOperationalData.getAcCurrentMax())
		{
			lastOperationalData.setAcCurrentMax(current);
			lastOperationalData.setAcCurrentMaxDate(date);
		}
		if (isNull(lastOperationalData.getAcCurrentMin()) || current < lastOperationalData.getAcCurrentMin())
		{
			lastOperationalData.setAcCurrentMin(current);
			lastOperationalData.setAcCurrentMinDate(date);
		}
	}

	/**
	 * Sets the voltage and check min max.
	 * 
	 * @param date the date
	 * @param lastOperationalData the last operational data
	 * @param voltage the voltage
	 */
	private void setVoltageAndCheckMinMax(Date date, LastOperationalData lastOperationalData, Float voltage)
	{
		lastOperationalData.setAcVoltage(voltage);
		if (isNull(lastOperationalData.getAcVoltageMax()) || voltage > lastOperationalData.getAcVoltageMax())
		{
			lastOperationalData.setAcVoltageMax(voltage);
			lastOperationalData.setAcVoltageMaxDate(date);
		}
		if (isNull(lastOperationalData.getAcVoltageMin()) || voltage < lastOperationalData.getAcVoltageMin())
		{
			lastOperationalData.setAcVoltageMin(voltage);
			lastOperationalData.setAcVoltageMinDate(date);
		}
	}

	/**
	 * Sets the consumption and check min max.
	 * 
	 * @param date the date
	 * @param lastOperationalData the last operational data
	 * @param consumption the consumption
	 */
	private void setConsumptionAndCheckMinMax(Date date, LastOperationalData lastOperationalData, Float consumption)
	{
		lastOperationalData.setConsumption(consumption);
		lastOperationalData.setConsumptionDate(date);
		if (isNull(lastOperationalData.getConsumptionMax()) || consumption > lastOperationalData.getConsumptionMax())
		{
			lastOperationalData.setConsumptionMax(consumption);
		}
		if (isNull(lastOperationalData.getConsumptionMin()) || consumption < lastOperationalData.getConsumptionMin())
		{
			lastOperationalData.setConsumptionMin(consumption);
		}
	}
}
