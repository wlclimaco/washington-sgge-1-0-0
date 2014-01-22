package com.sensus.lc.light.bcl;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.SetupDimmingConfigurationRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.process.model.Process;

/**
 * Interface for Light Processor business component.
 * 
 * @author Thiago - QAT
 */
public interface ILightProcessorBCL
{

	/**
	 * Process light status notification.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> processLightStatusNotification(
			LightStatusNotificationRequest request);

	/**
	 * Process alarm warning notification.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> processAlarmWarningNotification(AlarmNotificationRequest request);

	/**
	 * Process clear alarm notification.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> processClearAlarmNotification(AlarmNotificationRequest request);

	/**
	 * Process light setup notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processLightSetupNotification(LightSetupNotificationRequest request);

	/**
	 * Process channel setup audit.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processChannelSetupAuditNotification(ChannelSetupAuditRequest request);

	/**
	 * Process light binding notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processLightBindingNotification(LightStatusNotificationRequest request);

	/**
	 * Process light binding notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processSetLightIntensityNotification(LightIntensityRequest request);

	/**
	 * Process update light status.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processUpdateLightStatusNotification(UpdateLightNotificationRequest request);

	/**
	 * Process apply smartpoint properties notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse processApplySmartpointPropertiesNotification(ProcessLightsRequest request);

	/**
	 * Initiate setup dimming configuration.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateSetupDimmingConfiguration(SetupDimmingConfigurationRequest request);

	/**
	 * Initiate get light status.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateGetLightStatus(LightRequest lightRequest);

	/**
	 * Initiate update light intensity.
	 * 
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpdateLightIntensity(LightRequest lightRequest);

	/**
	 * Initiate delete alert.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateDeleteAlert(LightRequest request);

	/**
	 * Initiate upsert light pole id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpsertLightPoleId(LightMaintenanceRequest request);

	/**
	 * Initiate upsert light lat lng.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpsertLightLatLng(LightMaintenanceRequest request);

	/**
	 * Initiate update light status.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Process> initiateUpdateLightStatus(LightMaintenanceRequest request);

	/**
	 * Verify communication failure.
	 * 
	 * @param userContext the user context
	 */
	void verifyCommunicationFailure(UserContext userContext);

	/**
	 * Insert consumption to light in communication failure.
	 * 
	 * @param userContext the user context
	 */
	void insertConsumptionToLightInCommunicationFailure(UserContext userContext);
}
