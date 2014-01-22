package com.sensus.lc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.sensus.common.model.Message;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.Response;
import com.sensus.lc.server.bcf.IMlcServerBCF;
import com.sensus.lc.server.endpoint.MlcServerSpringWSEndpoint;
import com.sensus.lc.server.model.request.MlcServerRequest;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotificationResult;
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
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotificationResult;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotificationResult;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotificationResult;
import com.sensus.mlc.mlcserver.type.CustomerSerialNumberNotification;
import com.sensus.mlc.mlcserver.type.CustomerSerialNumberNotificationResult;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotificationResult;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightSetupNotificationResult;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.MessageType;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.Status;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotificationResult;

/**
 * The Class MlcGatewayWSServer.
 * 
 * @author QAT
 */
@Endpoint
public class MlcServer implements MlcServerSpringWSEndpoint
{

	/** The Constant GATEWAY_STARTING. */
	private static final String GATEWAY_STARTING = "**** GATEWAY: Starting ";

	/** The Constant GATEWAY_LEAVING. */
	private static final String GATEWAY_LEAVING = "**** GATEWAY: Leaving ";

	/** The Constant RESPONSE_OPERATION_SUCESS. */
	private static final String RESPONSE_OPERATION_SUCESS = "**** response.isOperationSuccess(): ";

	/** The LOG. */
	private static final Log LOG = LogFactory.getLog(MlcServer.class);

	/** The mlc server bcf. */
	private IMlcServerBCF mlcServerBCF;

	/**
	 * Gets the mlc server bcf.
	 * 
	 * @return the mlc server bcf
	 */
	public IMlcServerBCF getMlcServerBCF()
	{
		return mlcServerBCF;
	}

	/**
	 * Sets the mlc server bcf.
	 * 
	 * @param mlcServerBCF the new mlc server bcf
	 */
	public void setMlcServerBCF(IMlcServerBCF mlcServerBCF)
	{
		this.mlcServerBCF = mlcServerBCF;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#readLightStatusNotification(com.sensus.mlc.mlcserver
	 * .type.ReadLightStatusNotification)
	 */
	@Override
	@PayloadRoot(localPart = READ_LIGHT_STATUS_NOTIFICATION, namespace = NAMESPACE)
	public ReadLightStatusNotificationResult readLightStatusNotification(ReadLightStatusNotification notification)
	{
		logNotificationStart(READ_LIGHT_STATUS_NOTIFICATION);

		Response response = getMlcServerBCF().processReadLightStatusNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(READ_LIGHT_STATUS_NOTIFICATION, response);

		return (ReadLightStatusNotificationResult)setResponseStatusAndGetResult(response,
				new ReadLightStatusNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.gateway.ws.server.IMlcGatewayWSServer#setLightIntensity(com.sensus.mlc.mlcserver.type.
	 * SetLightIntensityNotification)
	 */
	@Override
	@PayloadRoot(localPart = APPLY_LIGHT_LEVEL_NOTIFICATION, namespace = NAMESPACE)
	public ApplyLightLevelNotificationResult applyLightLevelNotification(ApplyLightLevelNotification notification)
	{
		logNotificationStart(APPLY_LIGHT_LEVEL_NOTIFICATION);

		Response response = getMlcServerBCF().processApplyLightLevelNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(APPLY_LIGHT_LEVEL_NOTIFICATION, response);

		return (ApplyLightLevelNotificationResult)setResponseStatusAndGetResult(response,
				new ApplyLightLevelNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#clearScheduleNotification(com.sensus.mlc.mlcserver
	 * .type.ClearScheduleNotification)
	 */
	@Override
	@PayloadRoot(localPart = CLEAR_SCHEDULE_NOTIFICATION, namespace = NAMESPACE)
	public ClearScheduleNotificationResult clearScheduleNotification(ClearScheduleNotification notification)
	{
		logNotificationStart(CLEAR_SCHEDULE_NOTIFICATION);

		Response response = getMlcServerBCF().processClearScheduleNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(CLEAR_SCHEDULE_NOTIFICATION, response);

		return (ClearScheduleNotificationResult)setResponseStatusAndGetResult(response,
				new ClearScheduleNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#applyScheduleNotification(com.sensus.mlc.mlcserver
	 * .type.ApplyScheduleNotification)
	 */
	@Override
	@PayloadRoot(localPart = APPLY_SCHEDULE_NOTIFICATION, namespace = NAMESPACE)
	public ApplyScheduleNotificationResult applyScheduleNotification(ApplyScheduleNotification notification)
	{
		logNotificationStart(APPLY_SCHEDULE_NOTIFICATION);

		Response response = getMlcServerBCF().processApplyScheduleNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(APPLY_SCHEDULE_NOTIFICATION, response);

		return (ApplyScheduleNotificationResult)setResponseStatusAndGetResult(response,
				new ApplyScheduleNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#clearAlarmsNotification(com.sensus.mlc.mlcserver.
	 * type.ClearAlarmsNotification)
	 */
	@Override
	@PayloadRoot(localPart = CLEAR_ALARMS_NOTIFICATION, namespace = NAMESPACE)
	public ClearAlarmsNotificationResult clearAlarmsNotification(ClearAlarmsNotification notification)
	{
		logNotificationStart(CLEAR_ALARMS_NOTIFICATION);

		Response response = getMlcServerBCF().processClearAlarmsNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(CLEAR_ALARMS_NOTIFICATION, response);

		return (ClearAlarmsNotificationResult)setResponseStatusAndGetResult(response,
				new ClearAlarmsNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#lightSetupNotification(com.sensus.mlc.mlcserver.type
	 * .LightSetupNotification)
	 */
	@Override
	@PayloadRoot(localPart = LIGHT_SETUP_NOTIFICATION, namespace = NAMESPACE)
	public LightSetupNotificationResult lightSetupNotification(LightSetupNotification notification)
	{
		logNotificationStart(LIGHT_SETUP_NOTIFICATION);

		Response response = getMlcServerBCF().processLightSetupNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(LIGHT_SETUP_NOTIFICATION, response);

		return (LightSetupNotificationResult)setResponseStatusAndGetResult(response,
				new LightSetupNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#lightBindingNotification(com.sensus.mlc.mlcserver
	 * .type.LightBindingNotification)
	 */
	@Override
	@PayloadRoot(localPart = LIGHT_BINDING_NOTIFICATION, namespace = NAMESPACE)
	public LightBindingNotificationResult lightBindingNotification(LightBindingNotification notification)
	{
		logNotificationStart(LIGHT_BINDING_NOTIFICATION);

		Response response = getMlcServerBCF().processLightBindingNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(LIGHT_BINDING_NOTIFICATION, response);

		return (LightBindingNotificationResult)setResponseStatusAndGetResult(response,
				new LightBindingNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#lightStatusNotification(com.sensus.mlc.mlcserver.
	 * type.LightStatusNotification)
	 */
	@Override
	@PayloadRoot(localPart = LIGHT_STATUS_NOTIFICATION, namespace = NAMESPACE)
	public LightStatusNotificationResult lightStatusNotification(LightStatusNotification notification)
	{
		logNotificationStart(LIGHT_STATUS_NOTIFICATION);

		Response response = getMlcServerBCF().processLightStatusNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(LIGHT_STATUS_NOTIFICATION, response);

		return (LightStatusNotificationResult)setResponseStatusAndGetResult(response,
				new LightStatusNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#alarmWarningNotification(com.sensus.mlc.mlcserver
	 * .type.AlarmWarningNotification)
	 */
	@Override
	@PayloadRoot(localPart = ALARM_WARNING_NOTIFICATION, namespace = NAMESPACE)
	public AlarmWarningNotificationResult alarmWarningNotification(AlarmWarningNotification notification)
	{
		logNotificationStart(ALARM_WARNING_NOTIFICATION);

		Response response = getMlcServerBCF().processAlarmWarningNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(ALARM_WARNING_NOTIFICATION, response);

		return (AlarmWarningNotificationResult)setResponseStatusAndGetResult(response,
				new AlarmWarningNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#applySmartpointPropertiesNotification(com.sensus.
	 * mlc.mlcserver.type.ApplySmartpointPropertiesNotification)
	 */
	@Override
	@PayloadRoot(localPart = APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION, namespace = NAMESPACE)
	public ApplySmartpointPropertiesNotificationResult applySmartpointPropertiesNotification(
			ApplySmartpointPropertiesNotification notification)
	{
		logNotificationStart(APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION);

		Response response = getMlcServerBCF().processApplySmartpointPropertiesNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION, response);

		return (ApplySmartpointPropertiesNotificationResult)setResponseStatusAndGetResult(response,
				new ApplySmartpointPropertiesNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#channelSetupAuditNotification(com.sensus.mlc.mlcserver
	 * .type.ChannelSetupAuditNotification)
	 */
	@Override
	@PayloadRoot(localPart = CHANNEL_SETUP_AUDIT_NOTIFICATION, namespace = NAMESPACE)
	public ChannelSetupAuditNotificationResult channelSetupAuditNotification(
			ChannelSetupAuditNotification notification)
	{
		logNotificationStart(CHANNEL_SETUP_AUDIT_NOTIFICATION);

		Response response = getMlcServerBCF().processChannelSetupAuditNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(CHANNEL_SETUP_AUDIT_NOTIFICATION, response);

		return (ChannelSetupAuditNotificationResult)setResponseStatusAndGetResult(response,
				new ChannelSetupAuditNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#applyDimmingConfigurationNotification(com.sensus.
	 * mlc.mlcserver.type.ApplyDimmingConfigurationNotification)
	 */
	@Override
	@PayloadRoot(localPart = APPLY_DIMMING_CONFIGURATION_NOTIFICATION, namespace = NAMESPACE)
	public ApplyDimmingConfigurationNotificationResult applyDimmingConfigurationNotification(
			ApplyDimmingConfigurationNotification notification)
	{
		logNotificationStart(APPLY_DIMMING_CONFIGURATION_NOTIFICATION);

		Response response = getMlcServerBCF().processApplyDimmingConfiguration(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(APPLY_DIMMING_CONFIGURATION_NOTIFICATION, response);

		return (ApplyDimmingConfigurationNotificationResult)setResponseStatusAndGetResult(response,
				new ApplyDimmingConfigurationNotificationResult());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#customerSerialNumberNotification(com.sensus.mlc.mlcserver
	 * .type.CustomerSerialNumberNotification)
	 */
	@Override
	@PayloadRoot(localPart = CUSTOMER_SERIAL_NUMBER_NOTIFICATION, namespace = NAMESPACE)
	public CustomerSerialNumberNotificationResult customerSerialNumberNotification(
			CustomerSerialNumberNotification notification)
	{
		CustomerSerialNumberNotificationResult result = new CustomerSerialNumberNotificationResult();
		result.setStatus(Status.SUCCESS);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#attributeNotification(com.sensus.mlc.mlcserver.type
	 * .AttributeNotification)
	 */
	@Override
	@PayloadRoot(localPart = ATTRIBUTE_NOTIFICATION, namespace = NAMESPACE)
	public AttributeNotificationResult attributeNotification(AttributeNotification notification)
	{
		AttributeNotificationResult result = new AttributeNotificationResult();
		result.setStatus(Status.SUCCESS);
		return result;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:
	// -------------------------------------------------------------------------

	/**
	 * Log notification start.
	 * 
	 * @param methodName the method name
	 */
	private void logNotificationStart(String methodName)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(GATEWAY_STARTING).append(methodName).toString());
		}
	}

	/**
	 * Log notification end.
	 * 
	 * @param methodName the method name
	 * @param response the response
	 */
	private void logNotificationEnd(String methodName, Response response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(GATEWAY_LEAVING).append(methodName).toString());
			LOG.info(new StringBuilder(RESPONSE_OPERATION_SUCESS).append(response.isOperationSuccess()).toString());
		}
	}

	/**
	 * Prepare message info.
	 * This builds error messages to be sent back to the WS client
	 * 
	 * @param list the list
	 * @return the list
	 */
	private List<MessageInfo> prepareMessageInfo(List<Message> list)
	{
		List<MessageInfo> response = new ArrayList<MessageInfo>();

		for (Message msg : list)
		{
			MessageInfo wsMsg = new MessageInfo();
			wsMsg.setMessageText(msg.getText());
			wsMsg.setMessageType(MessageType.OTHER);
			response.add(wsMsg);
		}

		return response;
	}

	/**
	 * Sets the response status and get result.
	 * 
	 * @param response the response
	 * @param mlcGatewayResponse the mlc gateway response
	 * @return the mlc gateway response
	 */
	private MlcGatewayResponse setResponseStatusAndGetResult(
			Response response, MlcGatewayResponse mlcGatewayResponse)
	{
		if (response.isOperationSuccess())
		{
			mlcGatewayResponse.setStatus(Status.SUCCESS);
		}
		else
		{
			mlcGatewayResponse.setStatus(Status.FAIL);
			mlcGatewayResponse.getMessage().addAll(prepareMessageInfo(response.getMessageList()));
		}

		return mlcGatewayResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.mlcserver.endpoint.MlcServerSpringWSEndpoint#updateLightStatusNotification(com.sensus.mlc.mlcserver
	 * .type.UpdateLightStatusNotification)
	 */
	@Override
	@PayloadRoot(localPart = APPLY_UPDATE_LIGHT_STATUS_NOTIFICATION, namespace = NAMESPACE)
	public UpdateLightStatusNotificationResult updateLightStatusNotification(UpdateLightStatusNotification notification)
	{
		logNotificationStart(APPLY_UPDATE_LIGHT_STATUS_NOTIFICATION);

		Response response = getMlcServerBCF().processUpdateLightStatusNotification(
				new MlcServerRequest(new UserContext(), notification));

		logNotificationEnd(APPLY_UPDATE_LIGHT_STATUS_NOTIFICATION, response);

		return (UpdateLightStatusNotificationResult)setResponseStatusAndGetResult(response,
				new UpdateLightStatusNotificationResult());
	}
}
