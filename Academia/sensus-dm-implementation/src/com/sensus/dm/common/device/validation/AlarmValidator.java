package com.sensus.dm.common.device.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.cbof.model.Device;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class DeviceValidator.
 * 
 * @author QAT Global
 */
public class AlarmValidator implements IValidator
{

	/** The Constant ALARM_REQUIRED. */
	private static final String ALARM_REQUIRED = "sensus.epm.alarmvalidator.alarm.required";

	/** The Constant REQUIRED. */
	private static final String REQUIRED = "sensus.epm.alarmvalidator.action.required";

	/** The Constant ALARM_NAME_REQUIRED. */
	private static final String ALARM_NAME_REQUIRED = "sensus.epm.alarmvalidator.name.required";

	/** The Constant FLEXNET_ID_REQUIRED. */
	private static final String ALARM_DATE_REQUIRED = "sensus.epm.alarmvalidator.date.required";

	/**
	 * Validate.
	 * 
	 * @param validationContext the validation context
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		Device device = (Device)validationContext.getObjectToBeValidated(Device.class.getSimpleName());

		if (ValidationUtil.isNull(action))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		switch (action)
		{
			case FETCH_ALARM_HISTORY:
				validateAlarm(device, validationContext.getMessages());
				break;
			default:
				break;
		}
	}

	/**
	 * Validate alarm.
	 * 
	 * @param device the device
	 * @param list the list
	 */
	private void validateAlarm(Device device, List<MessageInfo> list)
	{
		List<Alarm> alarms = getAlarm(device);

		if (ValidationUtil.isNullOrEmpty(alarms))
		{
			list.add(MessageInfo.createFieldValidationError(ALARM_REQUIRED));
			return;
		}

		Alarm alarm = alarms.get(0);

		if (ValidationUtil.isNull(alarm.getAlarmEnum())
				|| ValidationUtil.isNullOrEmpty(alarm.getAlarmEnum().getValue()))
		{
			list.add(MessageInfo.createFieldValidationError(ALARM_NAME_REQUIRED));
		}

		ValidationUtil.isNull(alarm.getAlarmTime(), ALARM_DATE_REQUIRED, list);
	}

	/**
	 * Gets the alarm.
	 * 
	 * @param device the device
	 * @return the alarm
	 */
	private List<Alarm> getAlarm(Device device)
	{
		switch (device.getDeviceType())
		{
			case WATER_METER:
				return ((WaterMeter)device).getAlarms();
			case GAS_METER:
				return ((GasMeter)device).getAlarms();
			case LCM:
				return ((LCM)device).getAlarms();
			default:
				break;
		}

		return null;
	}

}
