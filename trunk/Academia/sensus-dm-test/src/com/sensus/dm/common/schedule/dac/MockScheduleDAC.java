package com.sensus.dm.common.schedule.dac;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockScheduleDAC.
 */
public class MockScheduleDAC extends AbstractMockBase implements IScheduleDAC
{

	/** The Constant TIME_ZONE_VALUE. */
	private static final String TIME_ZONE_VALUE = "-5";

	/** The Constant ID_REQUIRED. */
	private static final String ID_REQUIRED = "sensus.epm.schedulevalidator.id.required";

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = "sch1";

	/** The Constant USER_CONTEXT_ID. */
	private static final String USER_CONTEXT_ID = "rod";

	/** The Constant ACTION_TIME. */
	private static final String ACTION_TIME = "1352209740";

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant NINETY_NINE. */
	private static final Integer NINETY_NINE = 99;

	/** The Constant INT_ONE. */
	private static final int INT_ONE = 1;

	/** The Constant INT_TWO. */
	private static final int INT_TWO = 2;

	/** The Constant INT_THREE. */
	private static final int INT_THREE = 3;

	/** The Constant INT_FOUR. */
	private static final int INT_FOUR = 4;

	/** The Constant INT_FIVE. */
	private static final int INT_FIVE = 5;

	/** The Constant INT_SIX. */
	private static final int INT_SIX = 6;

	/** The Constant INT_SEVEN. */
	private static final int INT_SEVEN = 7;

	/** The Constant INT_EIGHT. */
	private static final int INT_EIGHT = 8;

	/** The Constant INT_NINE. */
	private static final int INT_NINE = 9;

	/** The Constant FORMATTED_DATE_TIME. */
	protected static final String FORMATTED_DATE_TIME = "yyyy-MM-dd h:mma";

	/** The Constant SCHEDULE_TEST. */
	private static final String SCHEDULE_TEST = "Test";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchAllSchedule(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InternalResultsResponse<DMSchedule> internalResultsResponse = new InternalResultsResponse<DMSchedule>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse.setStatus(Status.ExceptionError);
			return internalResultsResponse;
		}

		DMSchedule scheduleView = new DMSchedule();
		scheduleView.setStartTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		DMAction action = new DMAction();
		ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);
		Frequency frequency = new Frequency();

		frequency.setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
		frequency.setNextExecution(scheduleView.getStartTime());

		action.setActionType(actionType);
		action.setTotalDevices(THREE);

		scheduleView.setName(SCHEDULE_NAME);
		scheduleView.setModifyUser(USER_CONTEXT_ID);
		scheduleView.setFrequency(frequency);
		scheduleView.setDmAction(action);

		List<Property> properties = new ArrayList<Property>();

		Calendar calendar = Calendar.getInstance();

		Property property = new Property();

		property.setPropertyName(PropertyEnum.ACTION_DATE.toString());
		property.setPropertyValue(ACTION_TIME);
		properties.add(property);

		property = new Property();
		property.setPropertyName(PropertyEnum.FREQUENCY_TYPE.toString());
		property.setPropertyValue(FrequencyTypeEnum.DAILY.getValue());
		properties.add(property);

		property = new Property();
		property.setPropertyName(PropertyEnum.TIME_TO_REPEAT.toString());
		property.setPropertyValue(TWO.toString());
		properties.add(property);

		property = new Property();
		property.setPropertyName(PropertyEnum.START_ON_DATE.toString());
		property.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar.getTime())));
		properties.add(property);

		property = new Property();
		property.setPropertyName(PropertyEnum.NEVER_ENDS.toString());
		property.setPropertyValue(Boolean.TRUE.toString());
		properties.add(property);

		property = new Property();
		property.setPropertyName(PropertyEnum.EXECUTED_OCCURRENCES.toString());
		property.setPropertyValue(ZERO.toString());
		properties.add(property);

		scheduleView.setProperties(properties);

		inquiryScheduleRequest.setDateFormat(FORMATTED_DATE_TIME);
		inquiryScheduleRequest.setTimeZone(TIME_ZONE_VALUE);

		internalResultsResponse.getResultsList().add(scheduleView);
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#insertSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> insertSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> internalResponse = new InternalResultsResponse<DMSchedule>();
		internalResponse.getResultsList().add(scheduleRequest.getSchedule());

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResponse.setStatus(Status.ExceptionError);
			return internalResponse;
		}

		scheduleRequest.getSchedule().setId(ONE);
		internalResponse.addResult(scheduleRequest.getSchedule());

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#deleteSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResponse.setStatus(Status.ExceptionError);
			return internalResponse;
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResponse internalResultsResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse.setStatus(Status.ExceptionError);
			return internalResultsResponse;
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchScheduleById(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> internalResultsResponse = new InternalResultsResponse<DMSchedule>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse.setStatus(Status.ExceptionError);
			return internalResultsResponse;
		}

		DMAction action =
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));

		if (!ValidationUtil.isNull(scheduleRequest.getSchedule().getDmAction())
				&& !ValidationUtil.isNull(scheduleRequest.getSchedule().getDmAction().getGroups()))
		{
			Group group = new Group(ONE);
			group.addDevice(new Device(new Radio(new BigInteger(ONE.toString()))));

			action.addGroup(group);
		}
		else if (!ValidationUtil.isNull(scheduleRequest.getSchedule().getDmAction())
				&& !ValidationUtil.isNull(scheduleRequest.getSchedule().getDmAction().getTags()))
		{
			Tag tag = new Tag(ONE);
			tag.addDevice(new Device(new Radio(new BigInteger(ONE.toString()))));
			action.addTag(tag);
		}
		else
		{
			action.addDevice(new Device("1010M", new Radio(new BigInteger(ONE.toString())),
					DeviceTypeEnum.ELECTRIC_METER));
		}

		action.setOnDemand(false);
		action.setTotalDevices(TWO);

		DMSchedule scheduleView = new DMSchedule(scheduleRequest.getSchedule().getId());
		scheduleView.setDmAction(action);

		scheduleView.setStartTime(new Date());
		scheduleView.setStatusEnum(ScheduleStatusEnum.ENABLED);

		Property property = new Property(PropertyEnum.ACTION_DATE.getValue(), ACTION_TIME);

		List<Property> properties = new ArrayList<Property>();
		properties.add(property);

		scheduleView.setProperties(properties);

		internalResultsResponse.getResultsList().add(scheduleView);

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#canScheduleBeDeleted(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canScheduleBeDeleted(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (scheduleRequest.getSchedule().getId().equals(NINETY_NINE))
		{
			response.getResultsList().add(Boolean.FALSE);
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ID_REQUIRED);
		}
		else
		{
			response.getResultsList().add(Boolean.TRUE);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#canScheduleBeUpdated(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canScheduleBeUpdated(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Boolean> internalResultsResponse = new InternalResultsResponse<Boolean>();

		if (scheduleRequest.getSchedule().getId().equals(NINETY_NINE))
		{
			internalResultsResponse.getResultsList().add(Boolean.FALSE);
			internalResultsResponse.setStatus(Status.ExceptionError);
			internalResultsResponse.addFieldErrorMessage(ID_REQUIRED);
		}
		else
		{
			internalResultsResponse.getResultsList().add(Boolean.TRUE);
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#canScheduleBeInserted(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canScheduleBeInserted(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Boolean> internalResultsResponse = new InternalResultsResponse<Boolean>();

		if (scheduleRequest.getSchedule().getName() == SCHEDULE_TEST)
		{
			internalResultsResponse.getResultsList().add(Boolean.FALSE);
			internalResultsResponse.setStatus(Status.ExceptionError);
		}
		else
		{
			internalResultsResponse.getResultsList().add(Boolean.TRUE);
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#insertScheduleProperty(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse insertScheduleProperty(ScheduleRequest scheduleRequest)
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResponse.setStatus(Status.ExceptionError);
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#deleteAllScheduleProperty(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteAllScheduleProperty(ScheduleRequest scheduleRequest)
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResponse.setStatus(Status.ExceptionError);
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchSchedulesToExecute(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedulesToExecute(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> internalResultsResponse = new InternalResultsResponse<DMSchedule>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse.setStatus(Status.ExceptionError);
			return internalResultsResponse;
		}

		DMAction action =
				new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));

		action.addDevice(new Device(new Radio(new BigInteger(ONE.toString()))));
		action.addDevice(new Device(new Radio(new BigInteger(TWO.toString()))));

		action.setOnDemand(Boolean.FALSE);
		action.setTotalDevices(TWO);

		for (int i = ONE; i < INT_NINE; i++)
		{
			DMSchedule scheduleView = new DMSchedule(i, ScheduleStatusEnum.ENABLED);

			scheduleView.setDmAction(action);
			scheduleView.setStartTime(new Date());
			scheduleView.setFrequency(new Frequency());
			scheduleView.setProperties(new ArrayList<Property>());

			Property propertyFrequencyType = new Property();
			propertyFrequencyType.setPropertyName(PropertyEnum.FREQUENCY_TYPE.toString());

			Property propertyTimeToRepeat = new Property();
			propertyTimeToRepeat.setPropertyName(PropertyEnum.TIME_TO_REPEAT.toString());

			Property propertyStartOnDate = new Property();
			propertyStartOnDate.setPropertyName(PropertyEnum.START_ON_DATE.toString());

			Property propertyNeverEnds = new Property();
			propertyNeverEnds.setPropertyName(PropertyEnum.NEVER_ENDS.toString());

			Property propertyEndsAfterOccurences = new Property();
			propertyEndsAfterOccurences.setPropertyName(PropertyEnum.ENDS_AFTER_OCURRENCES.toString());

			Property propertyDaysOfWeeks = new Property();
			propertyDaysOfWeeks.setPropertyName(PropertyEnum.DAYS_OF_WEEKS.toString());

			Property propertyExecutedOccurrences = new Property();
			propertyExecutedOccurrences.setPropertyName(PropertyEnum.EXECUTED_OCCURRENCES.toString());

			Property propertyEndDate = new Property();
			propertyEndDate.setPropertyName(PropertyEnum.END_DATE.toString());

			new SimpleDateFormat(FORMATTED_DATE_TIME);

			Calendar calendar = Calendar.getInstance();

			switch (i)
			{
				case INT_ONE:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.NEVER.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyExecutedOccurrences.setPropertyValue(ZERO.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				case INT_TWO:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.DAILY.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyTimeToRepeat.setPropertyValue(TWO.toString());
					scheduleView.getProperties().add(propertyTimeToRepeat);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyNeverEnds.setPropertyValue(Boolean.TRUE.toString());
					scheduleView.getProperties().add(propertyNeverEnds);
					propertyExecutedOccurrences.setPropertyValue(ZERO.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				case INT_THREE:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.EVERY_MON_WED_FRI.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyEndsAfterOccurences.setPropertyValue(TWO.toString());
					scheduleView.getProperties().add(propertyEndsAfterOccurences);
					propertyExecutedOccurrences.setPropertyValue(ONE.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				case INT_FOUR:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.EVERY_TUE_THURS.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyNeverEnds.setPropertyValue(Boolean.TRUE.toString());
					scheduleView.getProperties().add(propertyNeverEnds);
					propertyExecutedOccurrences.setPropertyValue(ZERO.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				case INT_FIVE:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.EVERY_WEEKDAY.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyEndsAfterOccurences.setPropertyValue(ONE.toString());
					scheduleView.getProperties().add(propertyEndsAfterOccurences);
					propertyExecutedOccurrences.setPropertyValue(ZERO.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				case INT_SIX:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.MONTHLY.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyTimeToRepeat.setPropertyValue(TWO.toString());
					scheduleView.getProperties().add(propertyTimeToRepeat);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyNeverEnds.setPropertyValue(Boolean.TRUE.toString());
					scheduleView.getProperties().add(propertyNeverEnds);
					propertyExecutedOccurrences.setPropertyValue(ZERO.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				case INT_SEVEN:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.WEEKLY.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyTimeToRepeat.setPropertyName(PropertyEnum.TIME_TO_REPEAT.toString());
					propertyTimeToRepeat.setPropertyValue(TWO.toString());
					scheduleView.getProperties().add(propertyTimeToRepeat);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyDaysOfWeeks.setPropertyValue("1,5,6");
					scheduleView.getProperties().add(propertyDaysOfWeeks);
					propertyExecutedOccurrences.setPropertyValue(ZERO.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					calendar.add(Calendar.DATE, ONE);
					propertyEndDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyEndDate);
					break;
				case INT_EIGHT:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.YEARLY.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyTimeToRepeat.setPropertyName(PropertyEnum.TIME_TO_REPEAT.toString());
					propertyTimeToRepeat.setPropertyValue(TWO.toString());
					scheduleView.getProperties().add(propertyTimeToRepeat);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					propertyEndsAfterOccurences.setPropertyValue(THREE.toString());
					scheduleView.getProperties().add(propertyEndsAfterOccurences);
					propertyExecutedOccurrences.setPropertyValue(ONE.toString());
					scheduleView.getProperties().add(propertyExecutedOccurrences);
					break;
				default:
					propertyFrequencyType.setPropertyValue(FrequencyTypeEnum.NEVER.getValue());
					scheduleView.getProperties().add(propertyFrequencyType);
					propertyStartOnDate.setPropertyValue(String.valueOf(DMConvertUtil.convertDateToInteger(calendar
							.getTime())));
					scheduleView.getProperties().add(propertyStartOnDate);
					break;
			}

			internalResultsResponse.getResultsList().add(scheduleView);
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();
		response.addResult(new DMSchedule(ONE));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();
		response.addResult(new DMSchedule(ONE));
		return response;
	}
}