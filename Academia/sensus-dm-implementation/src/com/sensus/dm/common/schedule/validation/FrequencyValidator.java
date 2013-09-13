package com.sensus.dm.common.schedule.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;

/**
 * The Class FrequencyValidator.
 * 
 * @author QAT Global
 */
public class FrequencyValidator implements IValidator
{

	/** The Constant REQUIRED. */
	private static final String REQUIRED = "sensus.epm.frequencyvalidator.required";

	/** The Constant TYPE_REQUIRED. */
	private static final String TYPE_REQUIRED = "sensus.epm.frequencyvalidator.frequencyType.required";

	/** The Constant STARTS_ON_DATE_REQUIRED. */
	private static final String STARTS_ON_DATE_REQUIRED = "sensus.epm.frequencyvalidator.startsOnDate.required";

	/** The Constant REPEATS_EVERY_REQUIRED. */
	private static final String REPEATS_EVERY_REQUIRED = "sensus.epm.frequencyvalidator.repeatsEvery.required";

	/** The Constant REPEATS_EVERY_INVALID. */
	private static final String REPEATS_EVERY_INVALID = "sensus.epm.frequencyvalidator.repeatsEvery.invalid";

	/** The Constant REPEAT_BY_INVALID. */
	private static final String REPEAT_BY_INVALID = "sensus.epm.frequencyvalidator.repeatBy.invalid";

	/** The Constant REPEATS_ON. */
	private static final String REPEATS_ON = "sensus.epm.frequencyvalidator.repeatsOn.invalid";

	/** The Constant ENDS_INVALID. */
	private static final String ENDS_INVALID = "sensus.epm.frequencyvalidator.ends.invalid";

	/** The Constant NEVER_ENDS_INVALID. */
	private static final String NEVER_ENDS_INVALID = "sensus.epm.frequencyvalidator.neverEnds.invalid";

	/** The Constant ENDS_ON_DATE_INVALID. */
	private static final String ENDS_ON_DATE_INVALID = "sensus.epm.frequencyvalidator.endsOnDate.not.inform";

	/** The Constant ENDS_AFTER_OCCURRENCES_INVALID. */
	private static final String ENDS_AFTER_OCCURRENCES_INVALID =
			"sensus.epm.frequencyvalidator.endsAfterOcurrences.not.inform";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{

		DMPersistanceActionEnum action =
				(DMPersistanceActionEnum)validationContext.getValidationArguments().get(
						DMPersistanceActionEnum.getDefaultName());

		Frequency frequency = (Frequency)validationContext.getObjectToBeValidated(Frequency.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(frequency))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		switch (action)
		{
			case INSERT_SCHEDULE:
				ValidationUtil.isNull(frequency.getFrequencyTypeEnum(), TYPE_REQUIRED, validationContext.getMessages());
				validateStartOnDate(validationContext.getMessages(), frequency, validationContext);
				validateRepeatsEvery(validationContext.getMessages(), frequency);
				validateEndsNever(validationContext.getMessages(), frequency);
				validateEndsAfterOccurrences(validationContext.getMessages(), frequency);
				validateEndsOnDate(validationContext.getMessages(), frequency);
				validateRepeatBy(validationContext.getMessages(), frequency);
				validateRepeatsOn(validationContext.getMessages(), frequency);
				break;

			case UPDATE_SCHEDULE:
				ValidationUtil.isNull(frequency.getFrequencyTypeEnum(), TYPE_REQUIRED, validationContext.getMessages());
				validateStartOnDate(validationContext.getMessages(), frequency, validationContext);
				validateRepeatsEvery(validationContext.getMessages(), frequency);
				validateEndsNever(validationContext.getMessages(), frequency);
				validateEndsAfterOccurrences(validationContext.getMessages(), frequency);
				validateEndsOnDate(validationContext.getMessages(), frequency);
				validateRepeatBy(validationContext.getMessages(), frequency);
				validateRepeatsOn(validationContext.getMessages(), frequency);
				break;

			default:
				break;
		}

	}

	/**
	 * Validate repeats on.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateRepeatsOn(List<MessageInfo> list, Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getFrequencyTypeEnum())
				&& frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.WEEKLY))
		{
			ValidationUtil.isNullOrEmpty(frequency.getDaysOfWeeks(), REPEATS_ON, list);
		}
	}

	/**
	 * Validate repeat by.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateRepeatBy(List<MessageInfo> list, Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getFrequencyTypeEnum())
				&& frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.MONTHLY))
		{
			if (ValidationUtil.isNull(frequency.getDayOfMonth()) && ValidationUtil.isNull(frequency.getDayOfWeek()))
			{
				list.add(MessageInfo.createFieldValidationError(REPEAT_BY_INVALID));
			}
			else
			{
				if (!ValidationUtil.isNull(frequency.getDayOfMonth())
						&& !ValidationUtil.isNull(frequency.getDayOfWeek()))
				{
					list.add(MessageInfo.createFieldValidationError(REPEAT_BY_INVALID));
				}
			}

		}

	}

	/**
	 * Validate ends.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateEnds(List<MessageInfo> list, Frequency frequency)
	{
		if ((ValidationUtil.isNull(frequency.getNeverEnds()) || !frequency.getNeverEnds())
				&& ValidationUtil.isNull(frequency.getEndsAfterOccurrences())
				&& ValidationUtil.isNull(frequency.getEndDate()))
		{
			list.add(MessageInfo.createFieldValidationError(ENDS_INVALID));
		}

	}

	/**
	 * Validate ends never.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateEndsNever(List<MessageInfo> list, Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getNeverEnds()) && frequency.getNeverEnds())
		{
			if (!ValidationUtil.isNull(frequency.getEndsAfterOccurrences())
					|| !ValidationUtil.isNull(frequency.getEndDate()))
			{
				list.add(MessageInfo.createFieldValidationError(NEVER_ENDS_INVALID));
			}

		}

	}

	/**
	 * Validate ends after occurrences.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateEndsAfterOccurrences(List<MessageInfo> list, Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getEndsAfterOccurrences()))
		{
			if (!ValidationUtil.isNull(frequency.getEndDate()))
			{
				list.add(MessageInfo.createFieldValidationError(ENDS_ON_DATE_INVALID));
			}

		}
	}

	/**
	 * Validate ends on date.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateEndsOnDate(List<MessageInfo> list, Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getEndDate()))
		{
			if (!ValidationUtil.isNull(frequency.getEndsAfterOccurrences()))
			{
				list.add(MessageInfo.createFieldValidationError(ENDS_AFTER_OCCURRENCES_INVALID));
			}

		}
	}

	/**
	 * Validate repeats every.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateRepeatsEvery(List<MessageInfo> list, Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getFrequencyTypeEnum()))
		{
			if (frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.DAILY) ||
					frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.WEEKLY) ||
					frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.MONTHLY) ||
					frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.YEARLY))
			{
				ValidationUtil.isNullOrZero(frequency.getTimeToRepeat(), REPEATS_EVERY_REQUIRED, list);
			}
			else
			{
				if (!ValidationUtil.isNull(frequency.getTimeToRepeat()))
				{
					list.add(MessageInfo.createFieldValidationError(REPEATS_EVERY_INVALID));
				}
			}

		}

	}

	/**
	 * Validate start on date.
	 * 
	 * @param list the list
	 * @param frequency the frequency
	 */
	private void validateStartOnDate(List<MessageInfo> list, Frequency frequency, ValidationContext validationContext)
	{
		if (!ValidationUtil.isNull(frequency.getFrequencyTypeEnum()) &&
				!frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.NEVER) &&
				!frequency.getFrequencyTypeEnum().equals(FrequencyTypeEnum.CUSTOM))
		{
			ValidationUtil.isNull(frequency.getStartOnDate(), STARTS_ON_DATE_REQUIRED, list);
			validateEnds(validationContext.getMessages(), frequency);
		}
	}

}
