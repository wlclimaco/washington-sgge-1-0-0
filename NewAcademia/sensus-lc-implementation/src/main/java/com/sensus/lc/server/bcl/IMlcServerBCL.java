package com.sensus.lc.server.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;

/**
 * The Interface IGroupBCL.
 */
public interface IMlcServerBCL
{

	/**
	 * Process light binding.
	 *
	 * @param notification the notification
	 * @return the internal response
	 */
	InternalResponse processLightBinding(LightBindingNotification notification);

	/**
	 * Process light setup.
	 *
	 * @param notification the notification
	 * @return the internal response
	 */
	InternalResponse processLightSetup(LightSetupNotification notification);

	/**
	 * Process light status.
	 *
	 * @param notification the notification
	 * @return the internal response
	 */
	InternalResponse processLightStatus(LightStatusNotification notification);

	/**
	 * Process alarm warning.
	 *
	 * @param notification the notification
	 * @return the internal response
	 */
	InternalResponse processAlarmWarning(AlarmWarningNotification notification);

	/**
	 * Process read light status.
	 *
	 * @param notification the notification
	 * @return the internal response
	 */
	InternalResponse processReadLightStatus(ReadLightStatusNotification notification);

	/**
	 * Process apply light level.
	 *
	 * @param setLightIntensityNotification the set light intensity notification
	 * @return the internal response
	 */
	InternalResponse processApplyLightLevel(ApplyLightLevelNotification setLightIntensityNotification);

	/**
	 * Process clear schedule.
	 *
	 * @param clearScheduleNotification the clear schedule notification
	 * @return the internal response
	 */
	InternalResponse processClearSchedule(ClearScheduleNotification clearScheduleNotification);

	/**
	 * Process apply schedule.
	 *
	 * @param applyScheduleNotification the apply schedule notification
	 * @return the internal response
	 */
	InternalResponse processApplySchedule(ApplyScheduleNotification applyScheduleNotification);

	/**
	 * Process clear alarms.
	 *
	 * @param clearAlarmsNotification the clear alarms notification
	 * @return the internal response
	 */
	InternalResponse processClearAlarms(ClearAlarmsNotification clearAlarmsNotification);

	/**
	 * Process apply smartpoint properties notification.
	 *
	 * @param applySmartpointPropertiesNotification the apply smartpoint properties notification
	 * @return the internal response
	 */
	InternalResponse processApplySmartpointPropertiesNotification(ApplySmartpointPropertiesNotification applySmartpointPropertiesNotification);

	/**
	 * Process channel setup audit.
	 *
	 * @param channelSetupAuditNotification the channel setup audit notification
	 * @return the internal response
	 */
	InternalResponse processChannelSetupAudit(ChannelSetupAuditNotification channelSetupAuditNotification);

	/**
	 * Process dimming configuration audit.
	 *
	 * @param channelSetupAuditNotification the channel setup audit notification
	 * @return the internal response
	 */
	InternalResponse processApplyDimmingConfigurationNotification(ApplyDimmingConfigurationNotification applyDimmingConfigurationNotification);

	/**
	 * Process update light status notification.
	 *
	 * @param updateStatusNotification the update status notification
	 * @return the internal response
	 */
	InternalResponse processUpdateLightStatusNotification(UpdateLightStatusNotification updateStatusNotification);

}
