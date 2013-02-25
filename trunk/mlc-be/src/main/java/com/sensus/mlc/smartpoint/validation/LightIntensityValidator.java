package com.sensus.mlc.smartpoint.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_BLINK_ENUM;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.OVERRIDE_ENUM;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.OVERRRIDE_PER_DATE;
import static com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum.PERCENTAGE;

import java.util.Date;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.OverrideEnum;

/**
 * The Class LightIntensityValidator.
 */
public class LightIntensityValidator implements IValidator
{
	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_EXPIRE_PER_DATE_INVALID. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_EXPIRE_PER_DATE_INVALID =
			"sensus.mlc.smartpointvalidator.expire.per.date.invalid";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.smartpointvalidator.intensity.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_BLINK_LEVEL_REQUIRED =
			"sensus.mlc.smartpointvalidator.blink.level.required";

	/** The Constant MAX_DAY. */
	private static final Integer MAX_DAY = 45;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		List<MessageInfo> list = context.getMessages();
		OverrideEnum overrideEnum = (OverrideEnum)context.getObjectToBeValidated(OVERRIDE_ENUM.getValue());
		Date overridePerDate = (Date)context.getObjectToBeValidated(OVERRRIDE_PER_DATE.getValue());
		LightBlinkEnum lightBlinkEnum = (LightBlinkEnum)context.getObjectToBeValidated(LIGHT_BLINK_ENUM.getValue());
		Integer percentage = (Integer)context.getObjectToBeValidated(PERCENTAGE.getValue());

		switch (action)
		{
			case UPDATE_LIGHT_INTENSITY:
				validateOverrideExpirePerDate(list, overrideEnum, overridePerDate);
				validateLightBlinkEnum(list, lightBlinkEnum);
				validatePercentage(list, percentage);
				break;
			default:
				break;
		}
	}

	/**
	 * Validate override expire per date.
	 * 
	 * @param list the list
	 * @param overrideEnum the override enum
	 * @param overridePerDate the override per date
	 */
	private void validateOverrideExpirePerDate(List<MessageInfo> list, OverrideEnum overrideEnum, Date overridePerDate)
	{
		if (!overrideEnum.equals(OverrideEnum.PER_DATE))
		{
			return;
		}

		if (isNull(overridePerDate))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_EXPIRE_PER_DATE_INVALID));
			return;
		}

		if ((SensusDateUtil.getDaysDiff(LCDateUtil.getNewDateUTC(), overridePerDate) > MAX_DAY)
				|| (SensusDateUtil.getDaysDiff(LCDateUtil.getNewDateUTC(), overridePerDate) < 0))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_SMARTPOINTVALIDATOR_EXPIRE_PER_DATE_INVALID));
		}
	}

	/**
	 * Validate light blink enum.
	 * 
	 * @param list the list
	 * @param lightBlinkEnum the light blink enum
	 */
	private void validateLightBlinkEnum(List<MessageInfo> list, LightBlinkEnum lightBlinkEnum)
	{
		ValidationUtil.isNull(lightBlinkEnum, SENSUS_MLC_SMARTPOINTVALIDATOR_BLINK_LEVEL_REQUIRED,
				list);
	}

	/**
	 * Validate percentage.
	 * 
	 * @param list the list
	 * @param percentage the percentage
	 */
	private void validatePercentage(List<MessageInfo> list, Integer percentage)
	{
		isNull(percentage, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED, list);
	}
}
