package com.sensus.mlc.mlcserver.client;

import com.sensus.mlc.mlcserver.type.AbortTransactionRequest;
import com.sensus.mlc.mlcserver.type.AbortTransactionResponse;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotificationResult;
import com.sensus.mlc.mlcserver.type.AppCode11Info;
import com.sensus.mlc.mlcserver.type.AppCode71Info;
import com.sensus.mlc.mlcserver.type.AppCode75Info;
import com.sensus.mlc.mlcserver.type.AppCode85Info;
import com.sensus.mlc.mlcserver.type.AppCode86Info;
import com.sensus.mlc.mlcserver.type.AppCode90Info;
import com.sensus.mlc.mlcserver.type.AppCode94Info;
import com.sensus.mlc.mlcserver.type.AppCode94InfoIndividual;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotificationResult;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotificationResult;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotificationResult;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotificationResult;
import com.sensus.mlc.mlcserver.type.AttributeNotification;
import com.sensus.mlc.mlcserver.type.AttributeNotificationResult;
import com.sensus.mlc.mlcserver.type.ChannelSetupAudit2Notification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAudit2NotificationResult;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotificationResult;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotificationResult;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotificationResult;
import com.sensus.mlc.mlcserver.type.CustomerSerialNumberNotification;
import com.sensus.mlc.mlcserver.type.CustomerSerialNumberNotificationResult;
import com.sensus.mlc.mlcserver.type.DimmedSmartpoint;
import com.sensus.mlc.mlcserver.type.EndpointProperties;
import com.sensus.mlc.mlcserver.type.InitiateApplyDimmingConfigurationRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyDimmingConfigurationResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplyLightLevelRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyLightLevelResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplySmartpointPropertiesRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplySmartpointPropertiesResponse;
import com.sensus.mlc.mlcserver.type.InitiateClearAlarmsRequest;
import com.sensus.mlc.mlcserver.type.InitiateClearAlarmsResponse;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleResponse;
import com.sensus.mlc.mlcserver.type.InitiateReadLightStatusRequest;
import com.sensus.mlc.mlcserver.type.InitiateReadLightStatusResponse;
import com.sensus.mlc.mlcserver.type.InitiateReadSmartpointPropertiesRequest;
import com.sensus.mlc.mlcserver.type.InitiateReadSmartpointPropertiesResponse;
import com.sensus.mlc.mlcserver.type.InitiateUpdateLightStatusRequest;
import com.sensus.mlc.mlcserver.type.InitiateUpdateLightStatusResponse;
import com.sensus.mlc.mlcserver.type.InstallationInfo;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotificationResult;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightDimmingConfiguration;
import com.sensus.mlc.mlcserver.type.LightSetupDetail;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightSetupNotificationResult;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.MlcGatewayDataNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewayNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewayNotificationResult;
import com.sensus.mlc.mlcserver.type.MlcGatewayRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.MlcGatewaySupervisoryNotification;
import com.sensus.mlc.mlcserver.type.MlcGatewaySupervisoryNotificationResult;
import com.sensus.mlc.mlcserver.type.ObjectFactory;
import com.sensus.mlc.mlcserver.type.PingURLRequest;
import com.sensus.mlc.mlcserver.type.PingURLResponse;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.ReadSmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ReadSmartpointPropertiesNotificationResult;
import com.sensus.mlc.mlcserver.type.Schedule;
import com.sensus.mlc.mlcserver.type.ScheduleEvent;
import com.sensus.mlc.mlcserver.type.ScheduleEventData;
import com.sensus.mlc.mlcserver.type.ScheduleOffsetData;
import com.sensus.mlc.mlcserver.type.ScheduledSmartpoint;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.mlcserver.type.SmartpointStateLevel;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotificationResult;

public class ObjectFactoryMock extends ObjectFactory
{

	@Override
	public AbortTransactionRequest createAbortTransactionRequest()
	{
		// TODO Auto-generated method stub
		return super.createAbortTransactionRequest();
	}

	@Override
	public AbortTransactionResponse createAbortTransactionResponse()
	{
		// TODO Auto-generated method stub
		return super.createAbortTransactionResponse();
	}

	@Override
	public AlarmWarningInfo createAlarmWarningInfo()
	{
		// TODO Auto-generated method stub
		return super.createAlarmWarningInfo();
	}

	@Override
	public AlarmWarningNotification createAlarmWarningNotification()
	{
		// TODO Auto-generated method stub
		return super.createAlarmWarningNotification();
	}

	@Override
	public AlarmWarningNotificationResult createAlarmWarningNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createAlarmWarningNotificationResult();
	}

	@Override
	public AppCode11Info createAppCode11Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode11Info();
	}

	@Override
	public AppCode71Info createAppCode71Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode71Info();
	}

	@Override
	public AppCode75Info createAppCode75Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode75Info();
	}

	@Override
	public AppCode85Info createAppCode85Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode85Info();
	}

	@Override
	public AppCode86Info createAppCode86Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode86Info();
	}

	@Override
	public AppCode90Info createAppCode90Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode90Info();
	}

	@Override
	public AppCode94Info createAppCode94Info()
	{
		// TODO Auto-generated method stub
		return super.createAppCode94Info();
	}

	@Override
	public AppCode94InfoIndividual createAppCode94InfoIndividual()
	{
		// TODO Auto-generated method stub
		return super.createAppCode94InfoIndividual();
	}

	@Override
	public ApplyDimmingConfigurationNotification createApplyDimmingConfigurationNotification()
	{
		// TODO Auto-generated method stub
		return super.createApplyDimmingConfigurationNotification();
	}

	@Override
	public ApplyDimmingConfigurationNotificationResult createApplyDimmingConfigurationNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createApplyDimmingConfigurationNotificationResult();
	}

	@Override
	public ApplyLightLevelNotification createApplyLightLevelNotification()
	{
		// TODO Auto-generated method stub
		return super.createApplyLightLevelNotification();
	}

	@Override
	public ApplyLightLevelNotificationResult createApplyLightLevelNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createApplyLightLevelNotificationResult();
	}

	@Override
	public ApplyScheduleNotification createApplyScheduleNotification()
	{
		// TODO Auto-generated method stub
		return super.createApplyScheduleNotification();
	}

	@Override
	public ApplyScheduleNotificationResult createApplyScheduleNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createApplyScheduleNotificationResult();
	}

	@Override
	public ApplySmartpointPropertiesNotification createApplySmartpointPropertiesNotification()
	{
		// TODO Auto-generated method stub
		return super.createApplySmartpointPropertiesNotification();
	}

	@Override
	public ApplySmartpointPropertiesNotificationResult createApplySmartpointPropertiesNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createApplySmartpointPropertiesNotificationResult();
	}

	@Override
	public AttributeNotification createAttributeNotification()
	{
		// TODO Auto-generated method stub
		return super.createAttributeNotification();
	}

	@Override
	public AttributeNotificationResult createAttributeNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createAttributeNotificationResult();
	}

	@Override
	public ChannelSetupAudit2Notification createChannelSetupAudit2Notification()
	{
		// TODO Auto-generated method stub
		return super.createChannelSetupAudit2Notification();
	}

	@Override
	public ChannelSetupAudit2NotificationResult createChannelSetupAudit2NotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createChannelSetupAudit2NotificationResult();
	}

	@Override
	public ChannelSetupAuditNotification createChannelSetupAuditNotification()
	{
		// TODO Auto-generated method stub
		return super.createChannelSetupAuditNotification();
	}

	@Override
	public ChannelSetupAuditNotificationResult createChannelSetupAuditNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createChannelSetupAuditNotificationResult();
	}

	@Override
	public ClearAlarmsNotification createClearAlarmsNotification()
	{
		// TODO Auto-generated method stub
		return super.createClearAlarmsNotification();
	}

	@Override
	public ClearAlarmsNotificationResult createClearAlarmsNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createClearAlarmsNotificationResult();
	}

	@Override
	public ClearScheduleNotification createClearScheduleNotification()
	{
		// TODO Auto-generated method stub
		return super.createClearScheduleNotification();
	}

	@Override
	public ClearScheduleNotificationResult createClearScheduleNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createClearScheduleNotificationResult();
	}

	@Override
	public CustomerSerialNumberNotification createCustomerSerialNumberNotification()
	{
		// TODO Auto-generated method stub
		return super.createCustomerSerialNumberNotification();
	}

	@Override
	public CustomerSerialNumberNotificationResult createCustomerSerialNumberNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createCustomerSerialNumberNotificationResult();
	}

	@Override
	public DimmedSmartpoint createDimmedSmartpoint()
	{
		// TODO Auto-generated method stub
		return super.createDimmedSmartpoint();
	}

	@Override
	public EndpointProperties createEndpointProperties()
	{
		// TODO Auto-generated method stub
		return super.createEndpointProperties();
	}

	@Override
	public InitiateApplyDimmingConfigurationRequest createInitiateApplyDimmingConfigurationRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplyDimmingConfigurationRequest();
	}

	@Override
	public InitiateApplyDimmingConfigurationResponse createInitiateApplyDimmingConfigurationResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplyDimmingConfigurationResponse();
	}

	@Override
	public InitiateApplyLightLevelRequest createInitiateApplyLightLevelRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplyLightLevelRequest();
	}

	@Override
	public InitiateApplyLightLevelResponse createInitiateApplyLightLevelResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplyLightLevelResponse();
	}

	@Override
	public InitiateApplyScheduleRequest createInitiateApplyScheduleRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplyScheduleRequest();
	}

	@Override
	public InitiateApplyScheduleResponse createInitiateApplyScheduleResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplyScheduleResponse();
	}

	@Override
	public InitiateApplySmartpointPropertiesRequest createInitiateApplySmartpointPropertiesRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplySmartpointPropertiesRequest();
	}

	@Override
	public InitiateApplySmartpointPropertiesResponse createInitiateApplySmartpointPropertiesResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateApplySmartpointPropertiesResponse();
	}

	@Override
	public InitiateClearAlarmsRequest createInitiateClearAlarmsRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateClearAlarmsRequest();
	}

	@Override
	public InitiateClearAlarmsResponse createInitiateClearAlarmsResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateClearAlarmsResponse();
	}

	@Override
	public InitiateClearScheduleRequest createInitiateClearScheduleRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateClearScheduleRequest();
	}

	@Override
	public InitiateClearScheduleResponse createInitiateClearScheduleResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateClearScheduleResponse();
	}

	@Override
	public InitiateReadLightStatusRequest createInitiateReadLightStatusRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateReadLightStatusRequest();
	}

	@Override
	public InitiateReadLightStatusResponse createInitiateReadLightStatusResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateReadLightStatusResponse();
	}

	@Override
	public InitiateReadSmartpointPropertiesRequest createInitiateReadSmartpointPropertiesRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateReadSmartpointPropertiesRequest();
	}

	@Override
	public InitiateReadSmartpointPropertiesResponse createInitiateReadSmartpointPropertiesResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateReadSmartpointPropertiesResponse();
	}

	@Override
	public InitiateUpdateLightStatusRequest createInitiateUpdateLightStatusRequest()
	{
		// TODO Auto-generated method stub
		return super.createInitiateUpdateLightStatusRequest();
	}

	@Override
	public InitiateUpdateLightStatusResponse createInitiateUpdateLightStatusResponse()
	{
		// TODO Auto-generated method stub
		return super.createInitiateUpdateLightStatusResponse();
	}

	@Override
	public InstallationInfo createInstallationInfo()
	{
		// TODO Auto-generated method stub
		return super.createInstallationInfo();
	}

	@Override
	public LightBindingNotification createLightBindingNotification()
	{
		// TODO Auto-generated method stub
		return super.createLightBindingNotification();
	}

	@Override
	public LightBindingNotificationResult createLightBindingNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createLightBindingNotificationResult();
	}

	@Override
	public LightDetail createLightDetail()
	{
		// TODO Auto-generated method stub
		return super.createLightDetail();
	}

	@Override
	public LightDimmingConfiguration createLightDimmingConfiguration()
	{
		// TODO Auto-generated method stub
		return super.createLightDimmingConfiguration();
	}

	@Override
	public LightSetupDetail createLightSetupDetail()
	{
		// TODO Auto-generated method stub
		return super.createLightSetupDetail();
	}

	@Override
	public LightSetupNotification createLightSetupNotification()
	{
		// TODO Auto-generated method stub
		return super.createLightSetupNotification();
	}

	@Override
	public LightSetupNotificationResult createLightSetupNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createLightSetupNotificationResult();
	}

	@Override
	public LightStatusNotification createLightStatusNotification()
	{
		// TODO Auto-generated method stub
		return super.createLightStatusNotification();
	}

	@Override
	public LightStatusNotificationResult createLightStatusNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createLightStatusNotificationResult();
	}

	@Override
	public MessageInfo createMessageInfo()
	{
		// TODO Auto-generated method stub
		return super.createMessageInfo();
	}

	@Override
	public MlcGatewayDataNotification createMlcGatewayDataNotification()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewayDataNotification();
	}

	@Override
	public MlcGatewayNotification createMlcGatewayNotification()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewayNotification();
	}

	@Override
	public MlcGatewayNotificationResult createMlcGatewayNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewayNotificationResult();
	}

	@Override
	public MlcGatewayRequest createMlcGatewayRequest()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewayRequest();
	}

	@Override
	public MlcGatewayResponse createMlcGatewayResponse()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewayResponse();
	}

	@Override
	public MlcGatewaySupervisoryNotification createMlcGatewaySupervisoryNotification()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewaySupervisoryNotification();
	}

	@Override
	public MlcGatewaySupervisoryNotificationResult createMlcGatewaySupervisoryNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createMlcGatewaySupervisoryNotificationResult();
	}

	@Override
	public PingURLRequest createPingURLRequest()
	{
		// TODO Auto-generated method stub
		return super.createPingURLRequest();
	}

	@Override
	public PingURLResponse createPingURLResponse()
	{
		// TODO Auto-generated method stub
		return super.createPingURLResponse();
	}

	@Override
	public ReadLightStatusNotification createReadLightStatusNotification()
	{
		// TODO Auto-generated method stub
		return super.createReadLightStatusNotification();
	}

	@Override
	public ReadLightStatusNotificationResult createReadLightStatusNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createReadLightStatusNotificationResult();
	}

	@Override
	public ReadSmartpointPropertiesNotification createReadSmartpointPropertiesNotification()
	{
		// TODO Auto-generated method stub
		return super.createReadSmartpointPropertiesNotification();
	}

	@Override
	public ReadSmartpointPropertiesNotificationResult createReadSmartpointPropertiesNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createReadSmartpointPropertiesNotificationResult();
	}

	@Override
	public Schedule createSchedule()
	{
		// TODO Auto-generated method stub
		return super.createSchedule();
	}

	@Override
	public ScheduleEvent createScheduleEvent()
	{
		// TODO Auto-generated method stub
		return super.createScheduleEvent();
	}

	@Override
	public ScheduleEventData createScheduleEventData()
	{
		// TODO Auto-generated method stub
		return super.createScheduleEventData();
	}

	@Override
	public ScheduleOffsetData createScheduleOffsetData()
	{
		// TODO Auto-generated method stub
		return super.createScheduleOffsetData();
	}

	@Override
	public ScheduledSmartpoint createScheduledSmartpoint()
	{
		// TODO Auto-generated method stub
		return super.createScheduledSmartpoint();
	}

	@Override
	public Smartpoint createSmartpoint()
	{
		// TODO Auto-generated method stub
		return super.createSmartpoint();
	}

	@Override
	public SmartpointStateLevel createSmartpointStateLevel()
	{
		// TODO Auto-generated method stub
		return super.createSmartpointStateLevel();
	}

	@Override
	public UpdateLightStatusNotification createUpdateLightStatusNotification()
	{
		// TODO Auto-generated method stub
		return super.createUpdateLightStatusNotification();
	}

	@Override
	public UpdateLightStatusNotificationResult createUpdateLightStatusNotificationResult()
	{
		// TODO Auto-generated method stub
		return super.createUpdateLightStatusNotificationResult();
	}

}
