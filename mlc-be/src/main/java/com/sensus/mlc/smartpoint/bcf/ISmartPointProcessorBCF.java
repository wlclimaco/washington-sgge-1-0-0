package com.sensus.mlc.smartpoint.bcf;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.response.AlarmNotificationResponse;

public interface ISmartPointProcessorBCF
{
	/**
	 * Initiate update light intensity.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	ProcessResponse initiateUpdateLightIntensity(LightRequest lightRequest);

	/**
	 * Update light status.
	 * 
	 * @param lightRequest the light request
	 * @return the process response
	 */
	ProcessResponse initiateGetLightStatus(LightRequest lightRequest);

	/**
	 * Initiate delete alert.
	 * 
	 * @param lightRequest the light request
	 * @return the light response
	 */
	ProcessResponse initiateDeleteAlert(LightRequest lightRequest);

	/**
	 * Initiate upsert light property.
	 * 
	 * @param lightRequest the light request
	 * @return the process response
	 */
	ProcessResponse initiateUpsertLightProperty(LightRequest lightRequest);

	/**
	 * Initiate update light status.
	 * 
	 * @param lightRequest the light request
	 * @return the process response
	 */
	ProcessResponse initiateUpdateLightStatus(LightRequest lightRequest);

	/**
	 * Process apply smartpoint properties notification.
	 * 
	 * @param request the request
	 * @return the process lights response
	 */
	Response processApplySmartpointPropertiesNotification(ProcessLightsRequest request);

	/**
	 * Process channel setup audit notification.
	 * 
	 * @param request the request
	 * @return the channel setup audit response
	 */
	Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request);

	/**
	 * Clear Alarm status messages by light.
	 * 
	 * @param alarmNotificationRequest the light request
	 * @return the alarmNotification response
	 */
	AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest alarmNotificationRequest);

	/**
	 * Process forced light status notification.
	 * 
	 * @param request the request
	 * @return the forced light status notification response
	 */
	Response processForcedLightStatusNotification(
			ForcedLightStatusNotificationRequest request);

	/**
	 * Process alarm warning notification.
	 * 
	 * @param request the request
	 * @return the alarm notification response
	 */
	AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request);

	/**
	 * Process light status notification.
	 * 
	 * @param request the request
	 * @return the light status notification response
	 */
	Response processLightStatusNotification(LightStatusNotificationRequest request);

	/**
	 * Process light binding notification.
	 * 
	 * @param request the request
	 * @return the light status notification response
	 */
	Response processLightBindingNotification(LightStatusNotificationRequest request);

	/**
	 * Process light setup notification.
	 * 
	 * @param request the request
	 * @return the light setup notification response
	 */
	Response processLightSetupNotification(LightSetupNotificationRequest request);

	/**
	 * Process set light intensity notification.
	 * 
	 * @param request the request
	 * @return the process set light intensity response
	 */
	Response processSetLightIntensityNotification(ProcessLightsRequest request);
}
