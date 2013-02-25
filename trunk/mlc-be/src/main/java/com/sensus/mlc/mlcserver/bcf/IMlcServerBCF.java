package com.sensus.mlc.mlcserver.bcf;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.mlcserver.model.request.MlcServerRequest;

/**
 * The Interface IMlcServerBCF.
 */
public interface IMlcServerBCF
{
	/**
	 * Process light binding notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processLightBindingNotification(MlcServerRequest request);

	/**
	 * Process light setup notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processLightSetupNotification(MlcServerRequest request);

	/**
	 * Process light status notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processLightStatusNotification(MlcServerRequest request);

	/**
	 * Process alarm warning notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processAlarmWarningNotification(MlcServerRequest request);

	/**
	 * Process read light status notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processReadLightStatusNotification(MlcServerRequest request);

	/**
	 * Process apply light level notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processApplyLightLevelNotification(MlcServerRequest request);

	/**
	 * Process clear schedule notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processClearScheduleNotification(MlcServerRequest request);

	/**
	 * Process apply schedule notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processApplyScheduleNotification(MlcServerRequest request);

	/**
	 * Process clear alarms notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processClearAlarmsNotification(MlcServerRequest request);

	/**
	 * Process apply smartpoint properties notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processApplySmartpointPropertiesNotification(MlcServerRequest request);

	/**
	 * Process channel setup audit notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processChannelSetupAuditNotification(MlcServerRequest request);

	/**
	 * Process channel dimming configuration notification.
	 * 
	 * @param request the request
	 * @return the mlc server response
	 */
	Response processApplyDimmingConfiguration(MlcServerRequest request);

	/**
	 * Process update light status notification.
	 * 
	 * @param request the request
	 * @return the response
	 */
	Response processUpdateLightStatusNotification(MlcServerRequest request);
}
