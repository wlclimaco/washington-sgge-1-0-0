package com.sensus.mlc.mlcserver.model.request;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class GroupRequest.
 */
public class MlcServerRequest extends LightingControlRequest
{
	// --------------------------------- CONSTANTS

	/** The Constant ALARM_TYPE. */
	private static final String ALARM_TYPE = " Alarm Type: ";

	/** The Constant ALARM_SUBTYPE. */
	private static final String ALARM_SUBTYPE = " Alarm Subtype: ";

	/** The Constant CURRENT. */
	private static final String CURRENT = " Current: ";

	/** The Constant VOLTAGE. */
	private static final String VOLTAGE = " Voltage: ";

	/** The Constant CONSUMPTION. */
	private static final String CONSUMPTION = " Consumption: ";

	// --------------------------------- MESSAGES

	/** The apply light level notification. */
	@XmlElement(nillable = true)
	private ApplyLightLevelNotification applyLightLevelNotification;

	/** The read light status notification. */
	@XmlElement(nillable = true)
	private ReadLightStatusNotification readLightStatusNotification;

	/** The clear schedule notification. */
	@XmlElement(nillable = true)
	private ClearScheduleNotification clearScheduleNotification;

	/** The apply schedule notification. */
	@XmlElement(nillable = true)
	private ApplyScheduleNotification applyScheduleNotification;

	/** The apply schedule notification. */
	@XmlElement(nillable = true)
	private ClearAlarmsNotification clearAlarmsNotification;

	/** The apply smartpoint properties notification. */
	@XmlElement(nillable = true)
	private ApplySmartpointPropertiesNotification applySmartpointPropertiesNotification;

	/** The update light status notification. */
	@XmlElement(nillable = true)
	private UpdateLightStatusNotification updateLightStatusNotification;

	/** The apply light level notification. */
	@XmlElement(nillable = true)
	private ApplyDimmingConfigurationNotification applyDimmingConfigurationNotification;

	// --------------------------------- UNSOLICITED MESSAGES

	/** The alarm warning notification. */
	@XmlElement(nillable = true)
	private AlarmWarningNotification alarmWarningNotification;

	/** The light status notification. */
	@XmlElement(nillable = true)
	private LightStatusNotification lightStatusNotification;

	/** The light setup notification. */
	@XmlElement(nillable = true)
	private LightSetupNotification lightSetupNotification;

	/** The light binding notification. */
	@XmlElement(nillable = true)
	private LightBindingNotification lightBindingNotification;

	/** The channel setup audit notification. */
	@XmlElement(nillable = true)
	private ChannelSetupAuditNotification channelSetupAuditNotification;

	// --------------------------------- CONSTRUCTORS

	/**
	 * Instantiates a new mlc server request.
	 */
	public MlcServerRequest()
	{
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 */
	public MlcServerRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public MlcServerRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param readLightStatusNotificationValue the read light status notification value
	 */
	public MlcServerRequest(UserContext userContext, ReadLightStatusNotification readLightStatusNotificationValue)
	{
		this(userContext);
		setReadLightStatusNotification(readLightStatusNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param applyLightLevelNotificationValue the apply light level notification value
	 */
	public MlcServerRequest(UserContext userContext, ApplyLightLevelNotification applyLightLevelNotificationValue)
	{
		this(userContext);
		setApplyLightLevelNotification(applyLightLevelNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param clearScheduleNotificationValue the clear schedule notification value
	 */
	public MlcServerRequest(UserContext userContext, ClearScheduleNotification clearScheduleNotificationValue)
	{
		this(userContext);
		setClearScheduleNotification(clearScheduleNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param applyScheduleNotificationValue the apply schedule notification value
	 */
	public MlcServerRequest(UserContext userContext, ApplyScheduleNotification applyScheduleNotificationValue)
	{
		this(userContext);
		setApplyScheduleNotification(applyScheduleNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param updateLightStatusNotificationValue the update light status notification value
	 */
	public MlcServerRequest(UserContext userContext, UpdateLightStatusNotification updateLightStatusNotificationValue)
	{
		this(userContext);
		setUpdateLightStatusNotification(updateLightStatusNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param clearAlarmsNotificationValue the clear alarms notification value
	 */
	public MlcServerRequest(UserContext userContext, ClearAlarmsNotification clearAlarmsNotificationValue)
	{
		this(userContext);
		setClearAlarmsNotification(clearAlarmsNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param lightSetupNotificationValue the light setup notification value
	 */
	public MlcServerRequest(UserContext userContext, LightSetupNotification lightSetupNotificationValue)
	{
		this(userContext);
		setLightSetupNotification(lightSetupNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param lightBindingNotificationValue the light binding notification value
	 */
	public MlcServerRequest(UserContext userContext, LightBindingNotification lightBindingNotificationValue)
	{
		this(userContext);
		setLightBindingNotification(lightBindingNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param lightStatusNotificationValue the light status notification value
	 */
	public MlcServerRequest(UserContext userContext, LightStatusNotification lightStatusNotificationValue)
	{
		this(userContext);
		setLightStatusNotification(lightStatusNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param alarmWarningNotificationValue the alarm warning notification value
	 */
	public MlcServerRequest(UserContext userContext, AlarmWarningNotification alarmWarningNotificationValue)
	{
		this(userContext);
		setAlarmWarningNotification(alarmWarningNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param applySmartpointPropertiesNotificationValue the apply smartpoint properties notification value
	 */
	public MlcServerRequest(UserContext userContext,
			ApplySmartpointPropertiesNotification applySmartpointPropertiesNotificationValue)
	{
		this(userContext);
		setApplySmartpointPropertiesNotification(applySmartpointPropertiesNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param channelSetupAuditNotificationValue the channel setup audit notification value
	 */
	public MlcServerRequest(UserContext userContext, ChannelSetupAuditNotification channelSetupAuditNotificationValue)
	{
		this(userContext);
		setChannelSetupAuditNotification(channelSetupAuditNotificationValue);
	}

	/**
	 * Instantiates a new mlc server request.
	 *
	 * @param userContext the user context
	 * @param applyDimmingConfigurationNotificationValue the apply dimming configuration notification value
	 */
	public MlcServerRequest(UserContext userContext,
			ApplyDimmingConfigurationNotification applyDimmingConfigurationNotificationValue)
	{
		this(userContext);
		setApplyDimmingConfigurationNotification(applyDimmingConfigurationNotificationValue);
	}

	// --------------------------------- GETTERS AND SETTERS

	/**
	 * Gets the apply light level notification.
	 *
	 * @return the apply light level notification
	 */
	public ApplyLightLevelNotification getApplyLightLevelNotification()
	{
		return applyLightLevelNotification;
	}

	/**
	 * Sets the apply light level notification.
	 *
	 * @param applyLightLevelNotification the new apply light level notification
	 */
	public void setApplyLightLevelNotification(
			ApplyLightLevelNotification applyLightLevelNotification)
	{
		this.applyLightLevelNotification = applyLightLevelNotification;
	}

	/**
	 * Gets the read light status notification.
	 *
	 * @return the read light status notification
	 */
	public ReadLightStatusNotification getReadLightStatusNotification()
	{
		return readLightStatusNotification;
	}

	/**
	 * Sets the read light status notification.
	 *
	 * @param readLightStatusNotification the new read light status notification
	 */
	public void setReadLightStatusNotification(ReadLightStatusNotification readLightStatusNotification)
	{
		this.readLightStatusNotification = readLightStatusNotification;
	}

	/**
	 * Gets the clear schedule notification.
	 *
	 * @return the clear schedule notification
	 */
	public ClearScheduleNotification getClearScheduleNotification()
	{
		return clearScheduleNotification;
	}

	/**
	 * Sets the clear schedule notification.
	 *
	 * @param clearScheduleNotification the new clear schedule notification
	 */
	public void setClearScheduleNotification(ClearScheduleNotification clearScheduleNotification)
	{
		this.clearScheduleNotification = clearScheduleNotification;
	}

	/**
	 * Sets the apply schedule notification.
	 *
	 * @param applyScheduleNotification the new apply schedule notification
	 */
	public void setApplyScheduleNotification(ApplyScheduleNotification applyScheduleNotification)
	{
		this.applyScheduleNotification = applyScheduleNotification;
	}

	/**
	 * Gets the apply schedule notification.
	 *
	 * @return the apply schedule notification
	 */
	public ApplyScheduleNotification getApplyScheduleNotification()
	{
		return applyScheduleNotification;
	}

	/**
	 * Gets the clear alarms notification.
	 *
	 * @return the clear alarms notification
	 */
	public ClearAlarmsNotification getClearAlarmsNotification()
	{
		return clearAlarmsNotification;
	}

	/**
	 * Sets the clear alarms notification.
	 *
	 * @param clearAlarmsNotification the new clear alarms notification
	 */
	public void setClearAlarmsNotification(ClearAlarmsNotification clearAlarmsNotification)
	{
		this.clearAlarmsNotification = clearAlarmsNotification;
	}

	/**
	 * Gets the apply smartpoint properties notification.
	 *
	 * @return the apply smartpoint properties notification
	 */
	public ApplySmartpointPropertiesNotification getApplySmartpointPropertiesNotification()
	{
		return applySmartpointPropertiesNotification;
	}

	/**
	 * Sets the apply smartpoint properties notification.
	 *
	 * @param applySmartpointPropertiesNotification the new apply smartpoint properties notification
	 */
	public void setApplySmartpointPropertiesNotification(
			ApplySmartpointPropertiesNotification applySmartpointPropertiesNotification)
	{
		this.applySmartpointPropertiesNotification = applySmartpointPropertiesNotification;
	}

	/**
	 * Gets the apply dimming configuration notification.
	 *
	 * @return the apply dimming configuration notification
	 */
	public ApplyDimmingConfigurationNotification getApplyDimmingConfigurationNotification()
	{
		return applyDimmingConfigurationNotification;
	}

	/**
	 * Sets the apply dimming configuration notification.
	 *
	 * @param applyDimmingConfigurationNotification the new apply dimming configuration notification
	 */
	public void setApplyDimmingConfigurationNotification(
			ApplyDimmingConfigurationNotification applyDimmingConfigurationNotification)
	{
		this.applyDimmingConfigurationNotification = applyDimmingConfigurationNotification;
	}

	/**
	 * Gets the alarm warning notification.
	 *
	 * @return the alarm warning notification
	 */
	public AlarmWarningNotification getAlarmWarningNotification()
	{
		return alarmWarningNotification;
	}

	/**
	 * Sets the alarm warning notification.
	 *
	 * @param alarmWarningNotification the new alarm warning notification
	 */
	public void setAlarmWarningNotification(AlarmWarningNotification alarmWarningNotification)
	{
		this.alarmWarningNotification = alarmWarningNotification;
	}

	/**
	 * Gets the light status notification.
	 *
	 * @return the light status notification
	 */
	public LightStatusNotification getLightStatusNotification()
	{
		return lightStatusNotification;
	}

	/**
	 * Sets the light status notification.
	 *
	 * @param lightStatusNotification the new light status notification
	 */
	public void setLightStatusNotification(LightStatusNotification lightStatusNotification)
	{
		this.lightStatusNotification = lightStatusNotification;
	}

	/**
	 * Gets the light setup notification.
	 *
	 * @return the light setup notification
	 */
	public LightSetupNotification getLightSetupNotification()
	{
		return lightSetupNotification;
	}

	/**
	 * Sets the light setup notification.
	 *
	 * @param lightSetupNotification the new light setup notification
	 */
	public void setLightSetupNotification(LightSetupNotification lightSetupNotification)
	{
		this.lightSetupNotification = lightSetupNotification;
	}

	/**
	 * Gets the light binding notification.
	 *
	 * @return the light binding notification
	 */
	public LightBindingNotification getLightBindingNotification()
	{
		return lightBindingNotification;
	}

	/**
	 * Sets the light binding notification.
	 *
	 * @param lightBindingNotification the new light binding notification
	 */
	public void setLightBindingNotification(LightBindingNotification lightBindingNotification)
	{
		this.lightBindingNotification = lightBindingNotification;
	}

	/**
	 * Gets the channel setup audit notification.
	 *
	 * @return the channel setup audit notification
	 */
	public ChannelSetupAuditNotification getChannelSetupAuditNotification()
	{
		return channelSetupAuditNotification;
	}

	/**
	 * Sets the channel setup audit notification.
	 *
	 * @param channelSetupAuditNotification the new channel setup audit notification
	 */
	public void setChannelSetupAuditNotification(ChannelSetupAuditNotification channelSetupAuditNotification)
	{
		this.channelSetupAuditNotification = channelSetupAuditNotification;
	}

	/**
	 * Gets the update light status notification.
	 *
	 * @return the update light status notification
	 */
	public UpdateLightStatusNotification getUpdateLightStatusNotification()
	{
		return updateLightStatusNotification;
	}

	/**
	 * Sets the update light status notification.
	 *
	 * @param updateLightStatusNotification the new update light status notification
	 */
	public void setUpdateLightStatusNotification(UpdateLightStatusNotification updateLightStatusNotification)
	{
		this.updateLightStatusNotification = updateLightStatusNotification;
	}

	// --------------------------------- OVERRIDES

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		if (getAlarmWarningNotification() != null)
		{
			AlarmWarningNotification notification = getAlarmWarningNotification();
			StringBuilder sb = new StringBuilder();
			sb.append("***AlarmWarningNotification*** ");
			for (AlarmWarningInfo info : notification.getSmartpointDetail().getAlarmWarningMessages())
			{
				sb.append(ALARM_TYPE);
				sb.append(info.getAlarmWarningType());
				sb.append(ALARM_SUBTYPE);
				sb.append(info.getAlarmWarningSubType());
			}
			return sb.toString();

		}
		if (getLightStatusNotification() != null)
		{
			LightStatusNotification notification = getLightStatusNotification();
			StringBuilder sb = new StringBuilder();
			sb.append("***LightStatusNotification*** ");
			sb.append(CURRENT);
			sb.append(notification.getSmartpointDetail().getCurrent());
			sb.append(VOLTAGE);
			sb.append(notification.getSmartpointDetail().getVoltage());
			sb.append(CONSUMPTION);
			sb.append(notification.getSmartpointDetail().getConsumption());

			return sb.toString();

		}
		if (getReadLightStatusNotification() != null)
		{
			ReadLightStatusNotification notification = getReadLightStatusNotification();
			StringBuilder sb = new StringBuilder();
			sb.append("***ReadLightStatusNotification*** ");
			for (LightDetail detail : notification.getLightDetail())
			{
				for (AlarmWarningInfo info : detail.getAppCode11Data().getAlarmWarningMessages())
				{
					sb.append(ALARM_TYPE);
					sb.append(info.getAlarmWarningType());
					sb.append(ALARM_SUBTYPE);
					sb.append(info.getAlarmWarningSubType());
				}
				sb.append(CURRENT);
				sb.append(detail.getAppCode90Data().getCurrent());
				sb.append(VOLTAGE);
				sb.append(detail.getAppCode90Data().getVoltage());
				sb.append(CONSUMPTION);
				sb.append(detail.getAppCode90Data().getConsumption());
			}
			return sb.toString();

		}
		else
		{
			return "MlcServerRequest [getAlarmWarningNotification()=" + getAlarmWarningNotification().toString()
					+ ", getLightBindingNotification()=" + getLightBindingNotification()
					+ ", getApplyDimmingConfigurationNotification()="
					+ getApplyDimmingConfigurationNotification()
					+ ", getLightSetupNotification()=" + getLightSetupNotification()
					+ ", getLightStatusNotification()=" + getLightStatusNotification()
					+ ", getProcessGetLightStatusNotification()=" + getReadLightStatusNotification()
					+ ", getProcessSetLightIntensityNotification()=" + getApplyLightLevelNotification()
					+ ", getClearAlarmsNotification()=" + getClearAlarmsNotification()
					+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
		}
	}

}
