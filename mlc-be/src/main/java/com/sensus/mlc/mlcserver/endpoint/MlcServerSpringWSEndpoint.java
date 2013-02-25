package com.sensus.mlc.mlcserver.endpoint;

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
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotificationResult;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotificationResult;

/**
 * The Interface IMlcGatewayWSServer.
 */
// @WebService
public interface MlcServerSpringWSEndpoint
{
	// This MUST match the namespace of the object that represents the request
	/** The Constant NAMESPACE. */
	String NAMESPACE = "http://www.sensus.com/gateway/mlc";

	// This MUST match the name of the object that represents the request
	/** The Constant APPLY_LIGHT_LEVEL_NOTIFICATION. */
	String APPLY_LIGHT_LEVEL_NOTIFICATION = "ApplyLightLevelNotification";

	/** The Constant READ_LIGHT_STATUS_NOTIFICATION. */
	String READ_LIGHT_STATUS_NOTIFICATION = "ReadLightStatusNotification";

	/** The Constant CLEAR_SCHEDULE_NOTIFICATION. */
	String CLEAR_SCHEDULE_NOTIFICATION = "ClearScheduleNotification";

	/** The Constant APPLY_SCHEDULE_NOTIFICATION. */
	String APPLY_SCHEDULE_NOTIFICATION = "ApplyScheduleNotification";

	/** The Constant APPLY_UPDATE_LIGHT_STATUS_NOTIFICATION. */
	String APPLY_UPDATE_LIGHT_STATUS_NOTIFICATION = "UpdateLightStatusNotification";

	/** The Constant ALARM_WARNING_NOTIFICATION. */
	String ALARM_WARNING_NOTIFICATION = "AlarmWarningNotification";

	/** The Constant LIGHT_STATUS_NOTIFICATION. */
	String LIGHT_STATUS_NOTIFICATION = "LightStatusNotification";

	/** The Constant LIGHT_SETUP_NOTIFICATION. */
	String LIGHT_SETUP_NOTIFICATION = "LightSetupNotification";

	/** The Constant LIGHT_BINDING_NOTIFICATION. */
	String LIGHT_BINDING_NOTIFICATION = "LightBindingNotification";

	/** The Constant CLEAR_ALARMS_NOTIFICATION. */
	String CLEAR_ALARMS_NOTIFICATION = "ClearAlarmsNotification";

	/** The Constant CHANNEL_SETUP_AUDIT_NOTIFICATION. */
	String CHANNEL_SETUP_AUDIT_NOTIFICATION = "ChannelSetupAuditNotification";

	/** The Constant APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION. */
	String APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION = "ApplySmartpointPropertiesNotification";

	/** The Constant CUSTOMER_SERIAL_NUMBER_NOTIFICATION. */
	String CUSTOMER_SERIAL_NUMBER_NOTIFICATION = "CustomerSerialNumberNotification";

	/** The Constant ATTRIBUTE_NOTIFICATION. */
	String ATTRIBUTE_NOTIFICATION = "AttributeNotification";

	/** The Constant APPLY_DIMMING_CONFIGURATION_NOTIFICATION. */
	String APPLY_DIMMING_CONFIGURATION_NOTIFICATION = "ApplyDimmingConfigurationNotification";

	/**
	 * Clear alarms notification.
	 *
	 * @param notification the notification
	 * @return the clear alarms notification result
	 */
	ClearAlarmsNotificationResult clearAlarmsNotification(
			ClearAlarmsNotification notification);

	/**
	 * Apply light level notification.
	 *
	 * @param notification the notification
	 * @return the apply light level notification result
	 */
	ApplyLightLevelNotificationResult applyLightLevelNotification(
			ApplyLightLevelNotification notification);

	/**
	 * Read light status notification.
	 *
	 * @param notification the notification
	 * @return the read light status notification result
	 */
	ReadLightStatusNotificationResult readLightStatusNotification(
			ReadLightStatusNotification notification);

	/**
	 * Clear schedule notification.
	 *
	 * @param notification the notification
	 * @return the clear schedule notification result
	 */
	ClearScheduleNotificationResult clearScheduleNotification(
			ClearScheduleNotification notification);

	/**
	 * Apply schedule notification.
	 *
	 * @param notification the notification
	 * @return the apply schedule notification result
	 */
	ApplyScheduleNotificationResult applyScheduleNotification(ApplyScheduleNotification notification);

	/**
	 * Update light status notification.
	 *
	 * @param notification the notification
	 * @return the update light status notification result
	 */
	UpdateLightStatusNotificationResult updateLightStatusNotification(UpdateLightStatusNotification notification);

	/**
	 * **********************
	 * UNSOLICITED MESSAGES *
	 * **********************
	 */

	/**
	 * Alarm warning notification.
	 *
	 * @param notification the notification
	 * @return the alarm warning notification result
	 */
	AlarmWarningNotificationResult alarmWarningNotification(AlarmWarningNotification notification);

	/**
	 * Light status notification.
	 *
	 * @param notification the notification
	 * @return the light status notification result
	 */
	LightStatusNotificationResult lightStatusNotification(LightStatusNotification notification);

	/**
	 * Light setup notification.
	 *
	 * @param notification the notification
	 * @return the light setup notification result
	 */
	LightSetupNotificationResult lightSetupNotification(LightSetupNotification notification);

	/**
	 * Light binding notification.
	 *
	 * @param notification the notification
	 * @return the light binding notification result
	 */
	LightBindingNotificationResult lightBindingNotification(LightBindingNotification notification);

	/**
	 * Channel setup audit notification. This message is not being processed by LC and always returns SUCCESS
	 *
	 * @param notification the notification
	 * @return the channel setup audit notification result
	 */
	ChannelSetupAuditNotificationResult channelSetupAuditNotification(
			ChannelSetupAuditNotification notification);

	/**
	 * Apply smartpoint properties notification.
	 *
	 * @param notification the notification
	 * @return the apply smartpoint properties notification result
	 */
	ApplySmartpointPropertiesNotificationResult applySmartpointPropertiesNotification(
			ApplySmartpointPropertiesNotification notification);

	// App Code 71 (0x47) Serial Number Message
	/**
	 * Customer serial number notification.
	 *
	 * @param notification the notification
	 * @return the customer serial number notification result
	 */
	CustomerSerialNumberNotificationResult customerSerialNumberNotification(
			CustomerSerialNumberNotification notification);

	// App Code 94 (0x5E) NA2W Attribute String
	/**
	 * Attribute notification.
	 *
	 * @param notification the notification
	 * @return the attribute notification result
	 */
	AttributeNotificationResult attributeNotification(AttributeNotification notification);

	/**
	 * Apply dimming configuration notification.
	 *
	 * @param notification the notification
	 * @return the dimming configuration notification result
	 */
	ApplyDimmingConfigurationNotificationResult applyDimmingConfigurationNotification(
			ApplyDimmingConfigurationNotification notification);
}
