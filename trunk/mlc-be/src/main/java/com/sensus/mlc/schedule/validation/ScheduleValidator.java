package com.sensus.mlc.schedule.validation;

import static com.sensus.common.model.MessageInfo.createFieldValidationError;
import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;

import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ScheduleValidator.
 */
public class ScheduleValidator implements IValidator
{

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = Schedule.class.getSimpleName();

	/** The Constant TENANT_NAME. */
	private static final String TENANT_NAME = Tenant.class.getSimpleName();

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED =
			"sensus.mlc.schedulevalidator.type.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED =
			"sensus.mlc.schedulevalidator.event.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.smartpointvalidator.intensity.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED =
			"sensus.mlc.schedulevalidator.event.time.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED =
			"sensus.mlc.schedulevalidator.event.day.of.week.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED =
			"sensus.mlc.schedulevalidator.offset.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO =
			"sensus.mlc.schedulevalidator.sunriseoffset.greaterthanzero";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO =
			"sensus.mlc.schedulevalidator.sunsetoffset.greaterthanzero";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The scheduleBcl. */
	private IScheduleBCL scheduleBCL; // injected by Spring through setter

	/**
	 * Spring Sets the scheduleBcl.
	 * 
	 * @param scheduleBCLInjected the new schedule bcl
	 */
	public void setScheduleBCL(IScheduleBCL scheduleBCLInjected)
	{
		scheduleBCL = scheduleBCLInjected;
	}

	/**
	 * Gets the scheduleBcl.
	 * 
	 * @return the scheduleBcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext context)
	{

		List<MessageInfo> list = context.getMessages();

		Schedule schedule = (Schedule)context.getObjectToBeValidated(SCHEDULE_NAME);

		if (isNull(schedule))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED,
					SCHEDULE_NAME));
			return;
		}

		Tenant tenant = (Tenant)context.getObjectToBeValidated(TENANT_NAME);

		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)context.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		switch (action)
		{
			case INSERT:

				validateScheduleName(list, schedule, tenant);
				validateScheduleType(list, schedule);
				if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
				{
					validateScheduleEvent(list, (EventSchedule)schedule);
				}
				else if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
				{
					validateScheduleOffset(list, (OffsetSchedule)schedule);
				}
				break;

			case DELETE:

				validateScheduleId(list, schedule);
				break;

			case INITIATE_UPDATE:

				validateScheduleId(list, schedule);
				validateScheduleNameUpdate(list, schedule, tenant);
				validateScheduleType(list, schedule);
				if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
				{
					validateScheduleEvent(list, (EventSchedule)schedule);
				}
				else if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
				{
					validateScheduleOffset(list, (OffsetSchedule)schedule);
				}
				break;

			case UPDATE:
				validateScheduleId(list, schedule);
				validateScheduleNameUpdate(list, schedule, tenant);
				validateScheduleType(list, schedule);
				if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
				{
					validateScheduleEvent(list, (EventSchedule)schedule);
				}
				else if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
				{
					validateScheduleOffset(list, (OffsetSchedule)schedule);
				}
				break;
			case FETCH_BY_ID:
				validateScheduleId(list, schedule);
				break;

			case CLEAR_SMP_SCHEDULE:
				validateScheduleType(list, schedule);
				break;

			case GATEWAY_APPLY_SCHEDULE:
				validateScheduleType(list, schedule);
				break;

			case APPLY_SMP_SCHEDULE:
				validateScheduleType(list, schedule);
				break;

			case FETCH_SMARTPOINT_BY_SCHEDULE_TO_MAP:
				validateScheduleId(list, schedule);
				break;

			default:
				break;
		}

	}

	/**
	 * Validate schedule name.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 * @param tenant the tenant
	 */
	private void validateScheduleName(List<MessageInfo> list, Schedule schedule, Tenant tenant)
	{
		if (isNull(tenant))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED, TENANT_NAME));
			return;
		}

		getScheduleBCL().fetchCanInsert(schedule, list, tenant);
		LCHelp.isNullOrEmptyLC(schedule.getName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list,
				SCHEDULE_NAME);
	}

	/**
	 * Validate schedule type.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 */
	private void validateScheduleType(List<MessageInfo> list, Schedule schedule)
	{
		isNull(schedule.getScheduleTypeEnum(), SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED, list);
	}

	/**
	 * Validate schedule event.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 */
	private void validateScheduleEvent(List<MessageInfo> list, EventSchedule schedule)
	{
		if (isNullOrEmpty(schedule.getEvents()))
		{
			list.add(createFieldValidationError(SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED));
			return;
		}

		for (Event event : schedule.getEvents())
		{
			isNull(event.getIntensity(), SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED, list);
			isNull(event.getTime(), SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED, list);
			isNullOrEmpty(event.getDays(), SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED, list);
		}
	}

	/**
	 * Validate schedule offset.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 */
	private void validateScheduleOffset(List<MessageInfo> list, OffsetSchedule schedule)
	{
		isNull(schedule.getIntensity(), SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED, list);
		if (isNullOrZero(schedule.getSunriseOffsetMinutes()) && isNullOrZero(schedule.getSunsetOffsetMinutes()))
		{
			list.add(createFieldValidationError(SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED));
			return;
		}

		if ((!isNull(schedule.getSunriseOffsetMinutes())) && (schedule.getSunriseOffsetMinutes() < 0))
		{
			list.add(createFieldValidationError(SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO));
		}

		if ((!isNull(schedule.getSunsetOffsetMinutes())) && (schedule.getSunsetOffsetMinutes() < 0))
		{
			list.add(createFieldValidationError(SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO));
		}
	}

	/**
	 * Validate schedule id.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 */
	private void validateScheduleId(List<MessageInfo> list, Schedule schedule)
	{
		LCHelp.isNullOrZeroLC(schedule.getId(), SENSUS_MLC_VALIDATOR_ID_REQUIRED, list, SCHEDULE_NAME);
	}

	/**
	 * Validate schedule name update.
	 * 
	 * @param list the list
	 * @param schedule the schedule
	 * @param tenant the tenant
	 */
	private void validateScheduleNameUpdate(List<MessageInfo> list, Schedule schedule, Tenant tenant)
	{
		if (isNull(tenant))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_VALIDATOR_REQUIRED, TENANT_NAME));
			return;
		}

		getScheduleBCL().fetchCanUpdate(schedule, list, tenant);
		LCHelp.isNullOrEmptyLC(schedule.getName(), SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list,
				SCHEDULE_NAME);
	}

}
