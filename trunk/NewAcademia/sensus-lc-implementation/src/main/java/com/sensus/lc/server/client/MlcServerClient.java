package com.sensus.lc.server.client;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.sensus.mlc.mlcserver.type.AbortTransactionRequest;
import com.sensus.mlc.mlcserver.type.AbortTransactionResponse;
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
import com.sensus.mlc.mlcserver.type.PingURLRequest;
import com.sensus.mlc.mlcserver.type.PingURLResponse;

/**
 * The Class MlcServerClient.
 */
public class MlcServerClient extends SpringBeanAutowiringSupport
{

	/** The ws template. */
	private WebServiceTemplate wsTemplate;

	/**
	 * Sets the light intensity.
	 * 
	 * @param lightIntensity the light intensity
	 * @return the initiate apply light level response
	 */
	public InitiateApplyLightLevelResponse setLightIntensity(InitiateApplyLightLevelRequest lightIntensity)
	{
		return (InitiateApplyLightLevelResponse)getWsTemplate().marshalSendAndReceive(lightIntensity);
	}

	/**
	 * Sets the apply schedule.
	 * 
	 * @param applyScheduleRequest the apply schedule request
	 * @return the initiate apply schedule response
	 */
	public InitiateApplyScheduleResponse setApplySchedule(InitiateApplyScheduleRequest applyScheduleRequest)
	{
		return (InitiateApplyScheduleResponse)getWsTemplate().marshalSendAndReceive(applyScheduleRequest);
	}

	/**
	 * Sets the aborted.
	 * 
	 * @param abortRequest the abort request
	 * @return the abort transaction response
	 */
	public AbortTransactionResponse setAborted(AbortTransactionRequest abortRequest)
	{
		return (AbortTransactionResponse)getWsTemplate().marshalSendAndReceive(abortRequest);
	}

	/**
	 * Gets the light status.
	 * 
	 * @param request the request
	 * @return the light status
	 */
	public InitiateReadLightStatusResponse getLightStatus(InitiateReadLightStatusRequest request)
	{
		return (InitiateReadLightStatusResponse)getWsTemplate().marshalSendAndReceive(request);
	}

	/**
	 * Clear schedule.
	 * 
	 * @param request the request
	 * @return the initiate clear schedule response
	 */
	public InitiateClearScheduleResponse clearSchedule(InitiateClearScheduleRequest request)
	{
		return (InitiateClearScheduleResponse)getWsTemplate().marshalSendAndReceive(request);
	}

	/**
	 * Apply schedule.
	 * 
	 * @param request the request
	 * @return the initiate apply schedule response
	 */
	public InitiateApplyScheduleResponse applySchedule(InitiateApplyScheduleRequest request)
	{
		return (InitiateApplyScheduleResponse)getWsTemplate().marshalSendAndReceive(request);
	}

	/**
	 * Apply Setup Dimming Configuration.
	 * 
	 * @param request the request
	 * @return the initiate dimming configuration response
	 */
	public InitiateApplyDimmingConfigurationResponse applyDimmingConfiguration(
			InitiateApplyDimmingConfigurationRequest request)
	{
		return (InitiateApplyDimmingConfigurationResponse)getWsTemplate().marshalSendAndReceive(request);
	}

	/**
	 * Sets the ws template.
	 * 
	 * @param wsTemplate the new ws template
	 */
	public void setWsTemplate(WebServiceTemplate wsTemplate)
	{
		this.wsTemplate = wsTemplate;
	}

	/**
	 * Gets the ws template.
	 * 
	 * @return the ws template
	 */
	public WebServiceTemplate getWsTemplate()
	{
		return wsTemplate;
	}

	/**
	 * Ping url.
	 * 
	 * @param pingRequest the ping request
	 * @return the ping url response
	 */
	public PingURLResponse pingURL(PingURLRequest pingRequest)
	{
		return (PingURLResponse)getWsTemplate().marshalSendAndReceive(pingRequest);
	}

	/**
	 * Clear alarm.
	 * 
	 * @param clearAlarmRequest the clear alarm request
	 * @return the initiate clear alarms response
	 */
	public InitiateClearAlarmsResponse clearAlarm(InitiateClearAlarmsRequest clearAlarmRequest)
	{
		return (InitiateClearAlarmsResponse)getWsTemplate().marshalSendAndReceive(clearAlarmRequest);
	}

	/**
	 * Apply smartpoint properties.
	 * 
	 * @param applySmartpointPropertiesRequest the apply smartpoint properties request
	 * @return the initiate apply smartpoint properties response
	 */
	public InitiateApplySmartpointPropertiesResponse applySmartpointProperties(
			InitiateApplySmartpointPropertiesRequest applySmartpointPropertiesRequest)
	{
		return (InitiateApplySmartpointPropertiesResponse)getWsTemplate().marshalSendAndReceive(
				applySmartpointPropertiesRequest);
	}

	/**
	 * Read smartpoint properties.
	 * 
	 * @param readSmartpointPropertiesRequest the read smartpoint properties request
	 * @return the initiate read smartpoint properties response
	 */
	public InitiateReadSmartpointPropertiesResponse readSmartpointProperties(
			InitiateReadSmartpointPropertiesRequest readSmartpointPropertiesRequest)
	{
		return (InitiateReadSmartpointPropertiesResponse)getWsTemplate().marshalSendAndReceive(
				readSmartpointPropertiesRequest);
	}

	/**
	 * Update light status.
	 * 
	 * @param lightStatus the light status
	 * @return the initiate update light status response
	 */
	public InitiateUpdateLightStatusResponse updateLightStatus(InitiateUpdateLightStatusRequest lightStatus)
	{
		return (InitiateUpdateLightStatusResponse)getWsTemplate().marshalSendAndReceive(lightStatus);
	}

}
