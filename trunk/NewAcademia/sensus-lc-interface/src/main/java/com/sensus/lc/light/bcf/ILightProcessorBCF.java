package com.sensus.lc.light.bcf;

import com.sensus.common.model.response.Response;
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
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * Contains all the Light Processor related methods.
 * Do not update this interface without checking with Gustavo Peres
 */
public interface ILightProcessorBCF
{

	/**
	 * Process light binding notification.
	 * 
	 * @param request the request
	 * @return the response
	 */
	Response processLightBindingNotification(LightStatusNotificationRequest request);

	/**
	 * Process light state notification.
	 * 
	 * @param request the request
	 * @return the light state notification response
	 */
	Response processLightStatusNotification(LightStatusNotificationRequest request);

	/**
	 * Process alarm warning notification.
	 * 
	 * @param request the request
	 * @return the alarm notification response
	 */
	AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request);

	/**
	 * Process light setup notification.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	Response processLightSetupNotification(LightSetupNotificationRequest request);

	/**
	 * Process clear alarm notification.
	 * 
	 * @param request the request
	 * @return the alarm notification response
	 */
	AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest request);

	/**
	 * Process set light intensity notification.
	 * 
	 * @param request the request
	 * @return the response
	 */
	Response processSetLightIntensityNotification(LightIntensityRequest request);

	/**
	 * Process channel setup audit notification.
	 * 
	 * @param request the request
	 * @return the response
	 */
	Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request);

	/**
	 * Process apply smartpoint properties notification.
	 * 
	 * @param request the process lights request
	 * @return the response
	 */
	Response processApplySmartpointPropertiesNotification(ProcessLightsRequest request);

	/**
	 * Process update light status notification.
	 * 
	 * @param updateStatusNotification the update status notification
	 * @return the response
	 */
	Response processUpdateLightStatusNotification(UpdateLightNotificationRequest request);

	/**
	 * Initiate update light intensity.
	 * 
	 * @param request the light request
	 * @return the process response
	 */
	ProcessResponse initiateUpdateLightIntensity(LightRequest request);

	/**
	 * Initiate get light status.
	 * 
	 * @param request the light request
	 * @return the process response
	 */
	ProcessResponse initiateGetLightStatus(LightRequest request);

	/**
	 * Initiate delete alert.
	 * 
	 * @param request the light request
	 * @return the process response
	 */
	ProcessResponse initiateDeleteAlert(LightRequest request);

	/**
	 * Initiate upsert light pole id.
	 * 
	 * @param request the light request
	 * @return the process response
	 */
	ProcessResponse initiateUpsertLightPoleId(LightMaintenanceRequest request);

	/**
	 * Initiate upsert light lat lng.
	 * 
	 * @param request the light request
	 * @return the process response
	 */
	ProcessResponse initiateUpsertLightLatLng(LightMaintenanceRequest request);

	/**
	 * Initiate update light status.
	 * 
	 * @param request the light request
	 * @return the process response
	 */
	ProcessResponse initiateUpdateLightStatus(LightMaintenanceRequest request);
}
