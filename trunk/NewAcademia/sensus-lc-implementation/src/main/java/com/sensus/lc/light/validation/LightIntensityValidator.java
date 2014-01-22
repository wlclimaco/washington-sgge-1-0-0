package com.sensus.lc.light.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.LIGHT_BLINK_ENUM;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.OVERRIDE_ENUM;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.OVERRRIDE_PER_DATE;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.PERCENTAGE;

import java.util.Date;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.OverrideEnum;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class LightIntensityValidator.
 */
public class LightIntensityValidator implements IValidator
{
	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_EXPIRE_PER_DATE_INVALID. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_EXPIRE_PER_DATE_INVALID =
			"sensus.mlc.lightvalidator.expire.per.date.invalid";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_BLINK_LEVEL_REQUIRED =
			"sensus.mlc.lightvalidator.blink.level.required";

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

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
		LightRequest lightRequest = (LightRequest)context.getObjectToBeValidated(LIGHT_REQUEST_NAME);
		OverrideEnum overrideEnum = (OverrideEnum)context.getObjectToBeValidated(OVERRIDE_ENUM.getValue());
		Date overridePerDate = (Date)context.getObjectToBeValidated(OVERRRIDE_PER_DATE.getValue());
		BlinkStatusEnum lightBlinkEnum = (BlinkStatusEnum)context.getObjectToBeValidated(LIGHT_BLINK_ENUM.getValue());
		Integer percentage = (Integer)context.getObjectToBeValidated(PERCENTAGE.getValue());

		switch (action)
		{
			case UPDATE_LIGHT_INTENSITY:
				validateOverrideExpirePerDate(list, overrideEnum, overridePerDate);
				validateLightBlinkEnum(list, lightBlinkEnum);
				ActionCriteria actionCriteria = lightRequest.getActionCriteria();
				if (!isNull(actionCriteria) && !actionCriteria.isClearOverride())
				{
					validatePercentage(list, percentage);
				}
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
		if (!OverrideEnum.PER_DATE.equals(overrideEnum))
		{
			return;
		}

		if (isNull(overridePerDate))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_EXPIRE_PER_DATE_INVALID));
			return;
		}

		if ((SensusDateUtil.getDaysDiff(LCDateUtil.getNewDateUTC(), overridePerDate) > MAX_DAY)
				|| (SensusDateUtil.getDaysDiff(LCDateUtil.getNewDateUTC(), overridePerDate) < 0))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_LIGHTVALIDATOR_EXPIRE_PER_DATE_INVALID));
		}
	}

	/**
	 * Validate light blink enum.
	 *
	 * @param list the list
	 * @param lightBlinkEnum the light blink enum
	 */
	private void validateLightBlinkEnum(List<MessageInfo> list, BlinkStatusEnum lightBlinkEnum)
	{
		ValidationUtil.isNull(lightBlinkEnum, SENSUS_MLC_LIGHTVALIDATOR_BLINK_LEVEL_REQUIRED,
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
		isNull(percentage, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED, list);
	}
}
