package com.sensus.mlc.smartpoint.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;

public interface ISmartPointProcessorBCL
{
	/**
	 * Initiate update light intensity.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpdateLightIntensity(LightRequest lightRequest);

	/**
	 * Initiate upsert light property.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpsertLightProperty(LightRequest lightRequest);

	/**
	 * Initiate setup dimming configuration.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateSetupDimmingConfiguration(
			LightRequest request);

	/**
	 * Initiate update light status.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpdateLightStatus(LightRequest lightRequest);

	/**
	 * Initiate delete alert.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateDeleteAlert(LightRequest lightRequest);

	/**
	 * Process apply smartpoint properties notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processApplySmartpointPropertiesNotification(ProcessLightsRequest request);

	/**
	 * Process channel setup audit notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processChannelSetupAuditNotification(ChannelSetupAuditRequest request);

	/**
	 * Process alarm warning notification.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> processAlarmWarningNotification(AlarmNotificationRequest request);

	/**
	 * Process light status notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processLightStatusNotification(LightStatusNotificationRequest request);

	/**
	 * Process light binding notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processLightBindingNotification(LightStatusNotificationRequest request);

	/**
	 * Update light status.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateGetLightStatus(LightRequest lightRequest);

	/**
	 * Process forced light status notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processForcedLightStatusNotification(
			ForcedLightStatusNotificationRequest request);

	/**
	 * Process light setup notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processLightSetupNotification(LightSetupNotificationRequest request);

	/**
	 * Process set light intensity notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processSetLightIntensityNotification(ProcessLightsRequest request);

	/**
	 * Process clear alarm notification.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<StatusMessage> processClearAlarmNotification(AlarmNotificationRequest request);
}
