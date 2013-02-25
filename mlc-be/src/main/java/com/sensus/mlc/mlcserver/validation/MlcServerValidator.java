package com.sensus.mlc.mlcserver.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCValidationUtil;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AlarmWarningType;
import com.sensus.mlc.mlcserver.type.AppCode11Info;
import com.sensus.mlc.mlcserver.type.AppCode75Info;
import com.sensus.mlc.mlcserver.type.AppCode85Info;
import com.sensus.mlc.mlcserver.type.AppCode86Info;
import com.sensus.mlc.mlcserver.type.AppCode90Info;
import com.sensus.mlc.mlcserver.type.AppCode94Info;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.DataType;
import com.sensus.mlc.mlcserver.type.InstallationInfo;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightDetail;
import com.sensus.mlc.mlcserver.type.LightState;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.LightTopLevelState;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;

/**
 * The Class MlcServerValidator.
 */
public class MlcServerValidator implements IValidator
{
	/** The Constant CHANNEL_SETUP_AUDIT_NOTIFICATION_NAME. */
	private static final String CHANNEL_SETUP_AUDIT_NOTIFICATION_NAME = ChannelSetupAuditNotification.class
			.getSimpleName();

	/** The Constant LIGHT_STATUS_NOTIFICATION_NAME. */
	private static final String LIGHT_STATUS_NOTIFICATION_NAME = LightStatusNotification.class.getSimpleName();

	/** The Constant LIGHT_BINDING_NOTIFICATION_NAME. */
	private static final String LIGHT_BINDING_NOTIFICATION_NAME = LightBindingNotification.class.getSimpleName();

	/** The Constant ALARM_WARNING_NOTIFICATION. */
	private static final String ALARM_WARNING_NOTIFICATION_NAME = AlarmWarningNotification.class.getSimpleName();

	/** The Constant READ_LIGHT_STATUS_NOTIFICATION_NAME. */
	private static final String READ_LIGHT_STATUS_NOTIFICATION_NAME = ReadLightStatusNotification.class.getSimpleName();

	/** The Constant SUNSET_MINUTE. */
	private static final String SUNSET_MINUTE = "Sunset Minute";

	/** The Constant SUNSET_HOUR. */
	private static final String SUNSET_HOUR = "Sunset Hour";

	/** The Constant SUNRISE_MINUTE. */
	private static final String SUNRISE_MINUTE = "Sunrise Minute";

	/** The Constant SUNRISE_HOUR. */
	private static final String SUNRISE_HOUR = "Sunrise Hour";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION =
			"sensus.mlc.gateway.alarm.warning.invalid.combination";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_TYPE_REQUIRED =
			"sensus.mlc.gateway.alarm.warning.type.required";

	/** The Constant SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED. */
	private static final String SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED =
			"sensus.mlc.gateway.alarm.warning.subtype.required";

	/** The Constant SENSUS_MLC_VALIDATOR_POLEID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_POLEID_REQUIRED =
			"sensus.mlc.validator.poleid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED =
			"sensus.mlc.smartpointvalidator.firmware.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.properties.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED =
			"sensus.mlc.smartpointvalidator.schedule.properties.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.top.level.state.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.state.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunrise.offset.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED =
			"sensus.mlc.smartpointvalidator.sunset.offset.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATUS_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATUS_REQUIRED =
			"sensus.mlc.smartpointvalidator.light.status.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INSTALLATION_INFO_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INSTALLATION_INFO_REQUIRED =
			"sensus.mlc.smartpointvalidator.installation.info.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED =
			"sensus.mlc.smartpointvalidator.lower.case.serial.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED =
			"sensus.mlc.smartpointvalidator.upper.case.serial.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_BULB_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_BULB_SERIAL_REQUIRED =
			"sensus.mlc.smartpointvalidator.bulb.serial.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_BALLAST_SERIAL_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_BALLAST_SERIAL_REQUIRED =
			"sensus.mlc.smartpointvalidator.ballast.serial.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_SERIAL_NUMBERS_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_SERIAL_NUMBERS_REQUIRED =
			"sensus.mlc.smartpointvalidator.serial.numbers.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_PRODUCT_NUMBER_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_PRODUCT_NUMBER_REQUIRED =
			"sensus.mlc.smartpointvalidator.product.number.required";

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/**
	 * Gets the smart point accessor bcl.
	 * 
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return this.smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 * 
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		List<MessageInfo> list = validationContext.getMessages();

		switch (action)
		{
			case GATEWAY_BINDING:

				LightBindingNotification request =
						(LightBindingNotification)validationContext
								.getObjectToBeValidated(LIGHT_BINDING_NOTIFICATION_NAME);

				validateAppCode86Data(list, request.getSmartpointDetail());
				validateInstallationDetail(request.getInstallationDetail(), list);
				return;

			case GATEWAY_CHANNEL_SETUP_AUDIT:

				ChannelSetupAuditNotification auditNotificationRequest =
						(ChannelSetupAuditNotification)validationContext
								.getObjectToBeValidated(CHANNEL_SETUP_AUDIT_NOTIFICATION_NAME);
				validateAppCode75Data(auditNotificationRequest.getSmartpointDetail(), list);
				return;

			case GATEWAY_UNSOLICITED_STATUS:

				LightStatusNotification statusNotificationRequest =
						(LightStatusNotification)validationContext
								.getObjectToBeValidated(LIGHT_STATUS_NOTIFICATION_NAME);
				validateAppCode90Data(list, statusNotificationRequest.getSmartpointDetail());
				return;

			case GATEWAY_ALARM:

				AlarmWarningNotification alarmWaringNotificationRequest =
						(AlarmWarningNotification)validationContext
								.getObjectToBeValidated(ALARM_WARNING_NOTIFICATION_NAME);
				validateAppCode11Data(list, alarmWaringNotificationRequest.getSmartpointDetail());
				return;

			case GATEWAY_FORCED_STATUS:

				ReadLightStatusNotification readLightStatusNotificationRequest =
						(ReadLightStatusNotification)validationContext
								.getObjectToBeValidated(READ_LIGHT_STATUS_NOTIFICATION_NAME);

				if ((!isNullOrEmpty(readLightStatusNotificationRequest.getFailedSmartpoint())
						&& !isNullOrEmpty(readLightStatusNotificationRequest.getLightDetail()))
						|| (isNullOrEmpty(readLightStatusNotificationRequest.getFailedSmartpoint())
						&& (readLightStatusNotificationRequest.getLightDetail().size() > 1))
						|| (isNullOrEmpty(readLightStatusNotificationRequest.getLightDetail())
						&& (readLightStatusNotificationRequest.getFailedSmartpoint().size() > 1)))
				{
					return;
				}

				// Only validate if there are values. Note that this list can be empty if all failed.
				if (isNullOrEmpty(readLightStatusNotificationRequest.getLightDetail()))
				{
					return;
				}

				for (LightDetail lightDetail : readLightStatusNotificationRequest.getLightDetail())
				{
					// This will always come
					validateAppCode11Data(list, lightDetail.getAppCode11Data());
					validateAppCode90DataReadLight(list, lightDetail.getAppCode90Data());
					// This only if DataType contains CONFIG
					if (readLightStatusNotificationRequest.getDataType().contains(DataType.CONFIGURATION_DATA))
					{
						validateAppCode85Data(list, lightDetail.getAppCode85Data());
						validateAppCode94Data(list, lightDetail.getAppCode94Data());
					}
					// AppCode71 is optional (customer serial number)
				}
				return;

			default:
				break;
		}
	}

	/**
	 * Validate alarm warning info.
	 * 
	 * @param list the list
	 * @param alarmWarningMessages the alarm warning messages
	 */
	private void validateAlarmWarningInfo(List<MessageInfo> list, List<AlarmWarningInfo> alarmWarningMessages)
	{
		// The list of Alarms can be empty, for instance in the case the light comes back from failure
		for (AlarmWarningInfo alarmWarningInfo : alarmWarningMessages)
		{
			validateAlarmWarningType(alarmWarningInfo, list);
		}
	}

	/**
	 * Validate alarm warning type.
	 * 
	 * @param alarmWarningInfo the alarm warning info
	 * @param list the list
	 */
	private void validateAlarmWarningType(AlarmWarningInfo alarmWarningInfo, List<MessageInfo> list)
	{
		if (isNull(alarmWarningInfo.getAlarmWarningType()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_GATEWAY_ALARM_WARNING_TYPE_REQUIRED));
			return;
		}

		if (isNull(alarmWarningInfo.getAlarmWarningSubType()))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_GATEWAY_ALARM_WARNING_SUBTYPE_REQUIRED));
			return;
		}

		if ((alarmWarningInfo.getAlarmWarningType() == AlarmWarningType.ALARM) &&
				((alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.POWER_SURGE_DETECTED)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.BROWN_OUT_DETECTED)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.HIGH_CURRENT)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.LOW_CURRENT)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.REVERSE_ENERGY)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.METROLOGY_RESET)))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION));
			return;
		}

		if ((alarmWarningInfo.getAlarmWarningType() == AlarmWarningType.WARNING)
				&& ((alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.POWER_FAILURE)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.LAMP_FAILURE)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.METROLOGY_ERROR)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.METROLOGY_COM_FAILURE)
						|| (alarmWarningInfo.getAlarmWarningSubType() == AlarmWarningSubType.BOARD_FAILURE)))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_GATEWAY_ALARM_WARNING_INVALID_COMBINATION));
		}
	}

	/**
	 * Validate binding properties: LAT, LONG, POLEID, FIRMWARE.
	 * 
	 * @param list the list
	 * @param lightBindingDetail the light binding detail
	 */
	private void validateAppCode86Data(List<MessageInfo> list, AppCode86Info lightBindingDetail)
	{
		// Do not proceed if it failed already.
		if (isNull(lightBindingDetail))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		isNullOrEmpty(lightBindingDetail.getPoleId(), SENSUS_MLC_VALIDATOR_POLEID_REQUIRED, list);
		isNull(lightBindingDetail.getDeviceVersionMajor(), SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED, list);
		isNull(lightBindingDetail.getDeviceVersionMinor(), SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED, list);
		isNull(lightBindingDetail.getDeviceVersionPatch(), SENSUS_MLC_SMARTPOINTVALIDATOR_FIRMWARE_REQUIRED, list);
	}

	/**
	 * Validate app code11 data.
	 * 
	 * @param list the list
	 * @param appCode11Data the app code11 data
	 */
	private void validateAppCode11Data(List<MessageInfo> list, AppCode11Info appCode11Data)
	{
		// Do not proceed if it failed already.
		if (isNull(appCode11Data))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		LCValidationUtil.validateCurrent(appCode11Data.getCurrent(), list);
		LCValidationUtil.validateVoltage(appCode11Data.getVoltage(), list);
		LCValidationUtil.validateTemperature(appCode11Data.getTemperature(), list);
		validateLightState(appCode11Data.getLightState(), list);
		validateLightTopLevelState(appCode11Data.getLightTopLevelState(), list);

		if (list.size() == 0)
		{
			validateAlarmWarningInfo(list, appCode11Data.getAlarmWarningMessages());
		}
	}

	/**
	 * Validate app code75 data. (CHANNEL SETUP AUDIT)
	 * 
	 * @param appCode75Data the app code75 data
	 * @param list the list
	 */
	private void validateAppCode75Data(AppCode75Info appCode75Data, List<MessageInfo> list)
	{
		// Do not proceed if it failed already.
		if (isNull(appCode75Data))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}
		validateLightTopLevelState(appCode75Data.getLightTopLevelState(), list);
	}

	/**
	 * Validate light top level state.
	 * 
	 * @param lightTopLevelState the light top level state
	 * @param list the list
	 */
	private void validateLightTopLevelState(LightTopLevelState lightTopLevelState, List<MessageInfo> list)
	{
		isNull(lightTopLevelState, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_TOP_LEVEL_STATE_REQUIRED, list);
	}

	/**
	 * Validate light state.
	 * 
	 * @param lightState the light state
	 * @param list the list
	 */
	private void validateLightState(LightState lightState, List<MessageInfo> list)
	{
		isNull(lightState, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATE_REQUIRED, list);
	}

	/**
	 * Validate offset properties.
	 * 
	 * @param list the list
	 * @param appCode85Info the app code85 info
	 */
	private void validateAppCode85Data(List<MessageInfo> list, AppCode85Info appCode85Info)
	{
		if (isNull(appCode85Info))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_PROPERTIES_REQUIRED));
			return;
		}

		isNull(appCode85Info.getSunriseOffset(), SENSUS_MLC_SMARTPOINTVALIDATOR_SUNRISEOFFSET_REQUIRED, list);
		isNull(appCode85Info.getSunsetOffset(), SENSUS_MLC_SMARTPOINTVALIDATOR_SUNSETOFFSET_REQUIRED, list);
	}

	/**
	 * Validate status properties.
	 * 
	 * @param list the list
	 * @param appCode90Info the app code90 info
	 */
	private void validateAppCode90Data(List<MessageInfo> list, AppCode90Info appCode90Info)
	{
		if (isNull(appCode90Info))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED));
			return;
		}

		isNull(appCode90Info.getLightState(), SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATUS_REQUIRED, list);

		// Sunrise
		if (!isNull(appCode90Info.getSunriseHour()))
		{
			LCValidationUtil.validateHour(appCode90Info.getSunriseHour(), SUNRISE_HOUR, list);
		}
		if (!isNull(appCode90Info.getSunriseMinute()))
		{
			LCValidationUtil.validateMinute(appCode90Info.getSunriseMinute(), SUNRISE_MINUTE, list);
		}

		// Sunset
		if (!isNull(appCode90Info.getSunsetHour()))
		{
			LCValidationUtil.validateHour(appCode90Info.getSunsetHour(), SUNSET_HOUR, list);
		}
		if (!isNull(appCode90Info.getSunsetMinute()))
		{
			LCValidationUtil.validateMinute(appCode90Info.getSunsetMinute(), SUNSET_MINUTE, list);
		}

	}

	/**
	 * Validate app code90 data read light.
	 * 
	 * @param list the list
	 * @param appCode90Info the app code90 info
	 */
	private void validateAppCode90DataReadLight(List<MessageInfo> list, AppCode90Info appCode90Info)
	{
		if (isNull(appCode90Info))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_SCHEDULE_PROPERTIES_REQUIRED));
			return;
		}

		isNull(appCode90Info.getLightState(), SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_STATUS_REQUIRED, list);

		// Sunrise
		LCValidationUtil.validateHour(appCode90Info.getSunriseHour(), SUNRISE_HOUR, list);
		LCValidationUtil.validateMinute(appCode90Info.getSunriseMinute(), SUNRISE_MINUTE, list);

		// Sunset
		LCValidationUtil.validateHour(appCode90Info.getSunsetHour(), SUNSET_HOUR, list);
		LCValidationUtil.validateMinute(appCode90Info.getSunsetMinute(), SUNSET_MINUTE, list);

		// Operational Data
		LCValidationUtil.validateCurrent(appCode90Info.getCurrent(), list);
		LCValidationUtil.validateVoltage(appCode90Info.getVoltage(), list);
		LCValidationUtil.validateConsumption(appCode90Info.getConsumption(), list);
	}

	/**
	 * Validate installation detail.
	 * 
	 * @param installationDetail the installation detail
	 * @param list the list
	 */
	private void validateInstallationDetail(InstallationInfo installationDetail, List<MessageInfo> list)
	{
		if (isNull(installationDetail))
		{
			list.add(MessageInfo
					.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_INSTALLATION_INFO_REQUIRED,
							installationDetail));
			return;
		}

		LCValidationUtil.validateTimeZoneRegion(installationDetail.getTimezoneRegion(), list);
	}

	/**
	 * Validate app code94 data.
	 * 
	 * @param list the list
	 * @param appCode94Data the app code94 data
	 */
	private void validateAppCode94Data(List<MessageInfo> list, AppCode94Info appCode94Data)
	{
		if (isNull(appCode94Data))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_SERIAL_NUMBERS_REQUIRED));
			return;
		}

		isNullOrEmpty(appCode94Data.getLowerCaseSerial(),
				SENSUS_MLC_SMARTPOINTVALIDATOR_LOWER_CASE_SERIAL_REQUIRED,
				list);

		isNullOrEmpty(appCode94Data.getUpperCaseSerial(),
				SENSUS_MLC_SMARTPOINTVALIDATOR_UPPER_CASE_SERIAL_REQUIRED,
				list);

		isNullOrEmpty(appCode94Data.getBulbSerial(),
				SENSUS_MLC_SMARTPOINTVALIDATOR_BULB_SERIAL_REQUIRED,
				list);

		isNullOrEmpty(appCode94Data.getBallastSerial(),
				SENSUS_MLC_SMARTPOINTVALIDATOR_BALLAST_SERIAL_REQUIRED,
				list);

		isNullOrEmpty(appCode94Data.getProductNumber(),
				SENSUS_MLC_SMARTPOINTVALIDATOR_PRODUCT_NUMBER_REQUIRED,
				list);
	}
}
