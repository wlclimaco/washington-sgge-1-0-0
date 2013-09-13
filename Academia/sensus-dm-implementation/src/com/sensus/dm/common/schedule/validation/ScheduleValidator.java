package com.sensus.dm.common.schedule.validation;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.scheduler.model.Schedule;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;

/**
 * The Class ScheduleValidator.
 * 
 * @author QAT Brazil
 */
public class ScheduleValidator implements IValidator
{

	/** The Constant REQUIRED. */
	private static final String REQUIRED = "sensus.epm.schedulevalidator.required";

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.epm.schedulevalidator.id.required";

	/** The Constant NAME_REQUIRED. */
	private static final String NAME_REQUIRED = "sensus.epm.schedulevalidator.name.required";

	/** The Constant NAME_INVALID. */
	private static final String NAME_INVALID = "sensus.epm.schedulevalidator.name.invalid";

	/** The Constant DESCRIPTION_INVALID. */
	private static final String DESCRIPTION_INVALID = "sensus.epm.schedulevalidator.description.invalid";

	/** The Constant STATUS_REQUIRED. */
	private static final String STATUS_REQUIRED = "sensus.epm.schedulevalidator.status.required";

	/** The name length. */
	private int nameLength;

	/**
	 * Gets the name length.
	 * 
	 * @return the name length
	 */
	public int getNameLength()
	{
		return nameLength;
	}

	/**
	 * Sets the name length.
	 * 
	 * @param nameLength the new name length
	 */
	public void setNameLength(int nameLength)
	{
		this.nameLength = nameLength;
	}

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

		Schedule schedule = (Schedule)validationContext.getObjectToBeValidated(Schedule.class.getSimpleName());

		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(schedule))
		{
			validationContext.getMessages().add(MessageInfo.createFieldValidationError(REQUIRED));
			return;
		}

		switch (action)
		{
			case DELETE:
			case GENERATE_FILE_CSV:
			case FETCH_BY_ID:
				ValidationUtil.isNullOrZero(schedule.getId(), ID_REQUIRED, validationContext.getMessages());
				break;
			case FETCH_BY_NAME:
				ValidationUtil.isNullOrEmpty(schedule.getName(), NAME_REQUIRED, validationContext.getMessages());
				break;
			case INSERT_SCHEDULE:
				ValidationUtil.isNullOrEmpty(schedule.getName(), NAME_REQUIRED, validationContext.getMessages());
				validateNameLength(validationContext.getMessages(), schedule);
				validateDescriptionLength(validationContext.getMessages(), schedule);
				ValidationUtil.isNull(schedule.getStatusEnum(), STATUS_REQUIRED, validationContext.getMessages());
				break;
			case UPDATE_SCHEDULE:
				ValidationUtil.isNullOrZero(schedule.getId(), ID_REQUIRED, validationContext.getMessages());
				ValidationUtil.isNullOrEmpty(schedule.getName(), NAME_REQUIRED, validationContext.getMessages());
				validateNameLength(validationContext.getMessages(), schedule);
				validateDescriptionLength(validationContext.getMessages(), schedule);
				ValidationUtil.isNull(schedule.getStatusEnum(), STATUS_REQUIRED, validationContext.getMessages());
				break;
			case UPDATE_STATUS:
				ValidationUtil.isNullOrZero(schedule.getId(), ID_REQUIRED, validationContext.getMessages());
				ValidationUtil.isNull(schedule.getStatusEnum(), STATUS_REQUIRED, validationContext.getMessages());
				break;
			default:
				break;
		}

	}

	/**
	 * Validate name length.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 */
	private void validateNameLength(List<MessageInfo> list, Schedule schedule)
	{
		if (!ValidationUtil.isNull(schedule.getName()) && (schedule.getName().length() > getNameLength()))
		{
			list.add(MessageInfo.createFieldValidationError(NAME_INVALID, getNameLength()));
		}
	}

	/**
	 * Validate description length.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 */
	private void validateDescriptionLength(List<MessageInfo> list, Schedule schedule)
	{
		if (!ValidationUtil.isNull(schedule.getDescription()) && (schedule.getDescription().length() > getNameLength()))
		{
			list.add(MessageInfo.createFieldValidationError(DESCRIPTION_INVALID, getNameLength()));
		}
	}

}
