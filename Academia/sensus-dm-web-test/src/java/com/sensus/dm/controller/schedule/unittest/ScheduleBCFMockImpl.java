package com.sensus.dm.controller.schedule.unittest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.ResultsSetInfo;
import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;
import com.sensus.dm.elec.device.model.ElectricMeter;

/**
 * The Class ScheduleBCFMockImpl.
 */
public class ScheduleBCFMockImpl extends BaseMockImpl implements IScheduleBCF
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ZERO. */
	private static final int TWENTY = 20;

	/** The page size. */
	private Integer pageSize = 100;

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchAllSchedule(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InquiryScheduleResponse fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InquiryScheduleResponse inquiryScheduleResponse = new InquiryScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (inquiryScheduleRequest.getPageSize() > ZERO)
			{
				setPageSize(inquiryScheduleRequest.getPageSize());
			}
			else
			{
				setPageSize(TWENTY);
			}

			inquiryScheduleResponse.setSchedules(new ArrayList<DMSchedule>());

			DMSchedule schedule;

			Calendar calendar;

			for (int i = inquiryScheduleRequest.getStartRow(); i < (inquiryScheduleRequest.getStartRow() + pageSize); i++)
			{
				schedule = new DMSchedule();

				DMAction epmAction = new DMAction(i, new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));

				schedule.setId(i + 1);
				schedule.setName("BillingGroup " + (i + 1) + " Billing Read");
				schedule.setCreateUser("Tom Tuner");
				schedule.setModifyUser("Tom Tuner");

				calendar = new GregorianCalendar();
				calendar.add(Calendar.DAY_OF_MONTH, i + 1);
				schedule.setCreateDate(calendar.getTime());

				schedule.setFrequency(new Frequency());
				schedule.getFrequency().setNextExecution(new Date());

				if (i == 0)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
					schedule.getFrequency().setNeverEnds(null);
					schedule.getFrequency().setEndsAfterOccurrences(10);

					epmAction.setId(1);
					epmAction.setActionType(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
				}
				else if (i == 1)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
					schedule.getFrequency().setNeverEnds(null);
					schedule.getFrequency().setEndsAfterOccurrences(10);

					epmAction.setId(1);
					epmAction.setActionType(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
				}
				else if (i == 2)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);

					epmAction.setId(1);
					epmAction.setActionType(new ActionType(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE));
				}
				else if (i == 3)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_MON_WED_FRI);
				}
				else if (i == 4)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_TUE_THURS);
				}
				else if (i == 5)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_WEEKDAY);
				}
				else if (i == 6)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.MONTHLY);
				}
				else if (i == 7)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);
				}
				else if (i == 8)
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.WEEKLY);
				}
				else
				{
					schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.YEARLY);
				}

				List<Device> devices = new ArrayList<Device>();
				for (Integer count = 0; count < 10; count++)
				{
					Device device = new Device(new Radio(BigInteger.valueOf(count + 1000)));
					devices.add(device);
				}

				epmAction.setDevices(devices);
				epmAction.setProcessId(i);
				epmAction.setActionTime(new Date());

				schedule.setDmAction(epmAction);
				inquiryScheduleResponse.getSchedules().add(schedule);
			}

			ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
			resultsSetInfo.setTotalRowsAvailable(getPageSize());

			inquiryScheduleResponse.setResultsSetInfo(resultsSetInfo);
			inquiryScheduleResponse.setOperationSuccess(true);

			return inquiryScheduleResponse;
		}
		return (InquiryScheduleResponse)testOtherDefaultModes(inquiryScheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleById(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNullOrEmpty(scheduleRequest.getSchedule().getId().toString()))
			{
				DMSchedule schedule = new DMSchedule();

				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 10);
				calendar.set(Calendar.MONTH, 10);
				calendar.set(Calendar.YEAR, 2011);

				// /////////////////// DAILY ///////////////////////////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 1)
				{
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());
					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT);

					DMAction epmAction = new DMAction(actionType);

					epmAction.setTotalDevices(devices.size());
					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}

					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}

					epmAction.setTags(tags);

					List<Property> properties = new ArrayList<Property>();
					for (Integer i = 0; i <= 20; i++)
					{
						Property property = new Property();

						if (i == 0)
						{
							property.setPropertyName("DEMAND_RESPONSE_DURATION");
							property.setPropertyValue("10");
						}
						else if (i == 1)
						{
							property.setPropertyName("DEMAND_RESPONSE_ENROLLMENT_CODE");
							property.setPropertyValue("0");
						}
						else if (i == 2)
						{
							property.setPropertyName("DEMAND_RESPONSE_HEATING");
							property.setPropertyValue("5");
						}
						else if (i == 3)
						{
							property.setPropertyName("DEMAND_RESPONSE_DUTY_CYCLE_RATE");
							property.setPropertyValue("5");
						}
						else if (i == 4)
						{
							property.setPropertyName("DEMAND_RESPONSE_LOAD_ADJUSTMENT");
							property.setPropertyValue("10");
						}
						else if (i == 5)
						{
							property.setPropertyName("DEMAND_RESPONSE_CRITICALITY_LEVEL");
							property.setPropertyValue("1");
						}
						else if (i == 6)
						{
							property.setPropertyName("DEMAND_RESPONSE_RANDOMIZE_START");
							property.setPropertyValue("True");
						}
						else if (i == 7)
						{
							property.setPropertyName("DEMAND_RESPONSE_RANDOMIZE_START");
							property.setPropertyValue("True");
						}
						else if (i == 8)
						{
							property.setPropertyName("DEMAND_RESPONSE_RANDOMIZE_END");
							property.setPropertyValue("True");
						}
						else if (i == 9)
						{
							property.setPropertyName("HVAC_COMPRESSOR");
							property.setPropertyValue("True");
						}
						else if (i == 10)
						{
							property.setPropertyName("STRIP_HEATER");
							property.setPropertyValue("True");
						}
						else if (i == 11)
						{
							property.setPropertyName("WATER_HEATER");
							property.setPropertyValue("True");
						}
						else if (i == 12)
						{
							property.setPropertyName("POOL_PUMP");
							property.setPropertyValue("True");
						}
						else if (i == 13)
						{
							property.setPropertyName("SMART_APPLIANCES");
							property.setPropertyValue("True");
						}
						else if (i == 14)
						{
							property.setPropertyName("IRRIGATION_PUMP");
							property.setPropertyValue("True");
						}
						else if (i == 15)
						{
							property.setPropertyName("MANAGED_COMMERCIAL");
							property.setPropertyValue("True");
						}
						else if (i == 16)
						{
							property.setPropertyName("SIMPLE_MISC");
							property.setPropertyValue("True");
						}
						else if (i == 17)
						{
							property.setPropertyName("EXTERIOR_LIGHTING");
							property.setPropertyValue("True");
						}
						else if (i == 18)
						{
							property.setPropertyName("INTERIOR_LIGHTING");
							property.setPropertyValue("True");
						}
						else if (i == 19)
						{
							property.setPropertyName("ELECTRIC_VEHICLE");
							property.setPropertyValue("True");
						}
						else if (i == 20)
						{
							property.setPropertyName("GENERATION_SYSTEMS");
							property.setPropertyValue("True");
						}
						properties.add(property);
					}

					schedule.setStartTime(new Date());
					schedule.setProperties(properties);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}

				// /////////////// EVERY_WEEKDAY ////////////////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 2)
				{
					schedule = new DMSchedule();
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_WEEKDAY);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setNeverEnds(true);
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());

					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE);

					DMAction epmAction = new DMAction(actionType);

					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}
					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}
					epmAction.setTags(tags);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}

				// ///////////////////// EVERY_MON_WED_FRI //////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 3)
				{
					schedule = new DMSchedule();
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());

					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_MON_WED_FRI);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());
					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);

					DMAction epmAction = new DMAction(actionType);
					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}
					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}
					epmAction.setTags(tags);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}

				// ////////////////////// EVERY_TUE_THURS ///////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 4)
				{
					schedule = new DMSchedule();
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());

					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_TUE_THURS);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setNeverEnds(true);
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());
					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);

					DMAction epmAction = new DMAction(actionType);
					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}
					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}
					epmAction.setTags(tags);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}

				// /////////////////// WEEKLY //////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 5)
				{
					schedule = new DMSchedule();
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());

					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.WEEKLY);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());
					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);

					DMAction epmAction = new DMAction(actionType);
					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}
					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}
					epmAction.setTags(tags);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}

				// /////////////////// MONTHLY ////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 6)
				{
					schedule = new DMSchedule();
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());

					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.MONTHLY);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setNeverEnds(true);
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());
					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);

					DMAction epmAction = new DMAction(actionType);
					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}
					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}
					epmAction.setTags(tags);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}

				// /////////////// YEARLY /////////////////////////////////////
				if (scheduleRequest.getSchedule().getId() == 7)
				{
					schedule = new DMSchedule();
					schedule.setId(scheduleRequest.getSchedule().getId());
					schedule.setName("schedule 0" + scheduleRequest.getSchedule().getId().toString());
					schedule.setDescription("schedule description 0" + scheduleRequest.getSchedule().getId().toString());

					schedule.setCreateDate(new Date());

					List<DaysOfWeekEnum> dayOfWeeksList = new ArrayList<DaysOfWeekEnum>();
					dayOfWeeksList.add(DaysOfWeekEnum.FRIDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.MONDAY);
					dayOfWeeksList.add(DaysOfWeekEnum.SATURDAY);

					Frequency frequency = new Frequency();
					frequency.setId(scheduleRequest.getSchedule().getId());
					frequency.setFrequencyTypeEnum(FrequencyTypeEnum.YEARLY);
					frequency.setTimeToRepeat(11);
					frequency.setStartOnDate(new Date());
					frequency.setNeverEnds(true);
					frequency.setDayOfWeek(true);
					frequency.setDayOfMonth(false);
					frequency.setEndsAfterOccurrences(10);
					frequency.setEndDate(new Date());
					frequency.setDaysOfWeeksEnum(dayOfWeeksList);

					List<Date> repeatsDate = new ArrayList<Date>();
					repeatsDate.add(new Date());
					repeatsDate.add(calendar.getTime());
					frequency.setRepeatsDate(repeatsDate);

					schedule.setFrequency(frequency);

					List<Device> devices = new ArrayList<Device>();
					for (Integer i = 0; i < 10; i++)
					{
						ElectricMeter eletricMeter = new ElectricMeter();
						eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
						devices.add(eletricMeter);
					}

					ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT);

					DMAction epmAction = new DMAction(actionType);
					epmAction.setDevices(devices);

					List<Group> groups = new ArrayList<Group>();
					for (Integer i = 0; i < 10; i++)
					{
						Group group = new Group();
						group.setId(i + 100);
						group.setName("Group" + (i + 100));
						group.setDevicesCount(i * 10);
						groups.add(group);
					}
					epmAction.setGroups(groups);

					List<Tag> tags = new ArrayList<Tag>();
					for (Integer i = 0; i < 10; i++)
					{
						Tag tag = new Tag();
						tag.setId(i + 100);
						tag.setName("Tag" + (i + 100));
						tag.setDevicesCount(i * 10);
						tags.add(tag);
					}
					epmAction.setTags(tags);

					schedule.setAction(epmAction);
					List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
					scheduleList.add(schedule);

					scheduleResponse.setSchedules(scheduleList);
				}
			}
			scheduleResponse.setOperationSuccess(true);
			return scheduleResponse;
		}
		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByName(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByName(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();

			DMSchedule schedule = new DMSchedule();

			// In this case the schedule name exists already.
			if (scheduleRequest.getSchedule().getName() == "Schedule Name 02")
			{
				schedule.setName(null);
				scheduleList.add(schedule);
				scheduleResponse.setSchedules(scheduleList);
				scheduleResponse.setOperationSuccess(false);
			}
			else
			{
				schedule.setName("Schedule Name 01");
				scheduleList.add(schedule);
				scheduleResponse.setSchedules(scheduleList);
				scheduleResponse.setOperationSuccess(true);
			}
			return scheduleResponse;
		}
		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMSchedule> schedules = new ArrayList<DMSchedule>();

			for (int i = 0; i < 4; i++)
			{
				DMSchedule schedule = new DMSchedule();
				schedule.setId(i);
				schedule.setStartTime(new Date());
				schedule.setName("Event Schedule " + i);

				Frequency frequency = new Frequency();
				frequency.setFrequencyTypeEnum(FrequencyTypeEnum.MONTHLY);
				schedule.setFrequency(frequency);

				ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT);

				DMAction epmAction = new DMAction(actionType);

				schedule.setAction(epmAction);

				schedules.add(schedule);
			}

			scheduleResponse.setSchedules(schedules);
			scheduleResponse.setOperationSuccess(true);

			return scheduleResponse;
		}
		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<DMSchedule> schedules = new ArrayList<DMSchedule>();

			for (int i = 0; i < 4; i++)
			{
				DMSchedule schedule = new DMSchedule();
				schedule.setId(i);
				schedule.setStartTime(new Date());
				schedule.setName("Event Schedule " + i);

				Frequency frequency = new Frequency();
				frequency.setFrequencyTypeEnum(FrequencyTypeEnum.MONTHLY);
				schedule.setFrequency(frequency);

				ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT);

				DMAction epmAction = new DMAction(actionType);

				schedule.setAction(epmAction);

				schedules.add(schedule);
			}

			scheduleResponse.setSchedules(schedules);
			scheduleResponse.setOperationSuccess(true);

			return scheduleResponse;
		}

		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcf.IScheduleBCF#insertSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setSchedules(new ArrayList<DMSchedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			response.getSchedules().get(0).setId(1);
			response.getSchedules().get(0).getDmAction().setId(1);
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcf.IScheduleBCF#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setSchedules(new ArrayList<DMSchedule>());
			response.getSchedules().add(scheduleRequest.getSchedule());
			response.getSchedules().get(0).setId(1);
			response.getSchedules().get(0).getDmAction().setId(1);
			response.setOperationSuccess(Boolean.TRUE);

			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#deleteSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(scheduleRequest.getSchedule().getId()))
			{
				scheduleResponse.setOperationSuccess(true);
				return scheduleResponse;
			}
		}
		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	@Override
	public InquiryScheduleResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InquiryScheduleResponse inquiryScheduleResponse = new InquiryScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return inquiryScheduleResponse;
		}

		return (InquiryScheduleResponse)testOtherDefaultModes(inquiryScheduleResponse);
	}

	@Override
	public ScheduleResponse generateFileCSVScheduleDevice(ScheduleRequest inquiryScheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return scheduleResponse;
		}

		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#updateScheduleStatus(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse updateScheduleStatus(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(scheduleRequest.getSchedule())
					|| !ValidationUtil.isNull(scheduleRequest.getSchedule().getId())
					|| !ValidationUtil.isNull(scheduleRequest.getSchedule().getStatusEnum()))
			{
				scheduleResponse.setOperationSuccess(true);
				return scheduleResponse;
			}
		}

		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByAction(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByAction(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			DMSchedule schedule = new DMSchedule();

			if (!ValidationUtil.isNullOrEmpty(scheduleRequest.getSchedule().getDmAction().getId().toString()))
			{
				schedule.setName("Test");
				schedule.setDescription("Description");

				List<Device> devices = new ArrayList<Device>();

				for (Integer i = 0; i < 10; i++)
				{
					ElectricMeter eletricMeter = new ElectricMeter();
					eletricMeter.setRadio(new Radio(BigInteger.valueOf(i + 1000)));
					devices.add(eletricMeter);
				}

				ActionType actionType = new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT);

				DMAction epmAction = new DMAction(actionType);

				epmAction.setTotalDevices(devices.size());
				epmAction.setDevices(devices);

				schedule.setAction(epmAction);
				schedule.setCreateDate(new Date());

				List<DMSchedule> scheduleList = new ArrayList<DMSchedule>();
				scheduleList.add(schedule);

				scheduleResponse.setSchedules(scheduleList);
			}

			scheduleResponse.setOperationSuccess(true);
			return scheduleResponse;
		}
		return (ScheduleResponse)testOtherDefaultModes(scheduleResponse);
	}

	@Override
	public ScheduleResponse insertScheduleOnDemand(InquiryActionRequest inquiryActionRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			if (!ValidationUtil.isNullOrEmpty(inquiryActionRequest.getActions()))
			{
				if (!ValidationUtil.isNull(inquiryActionRequest.getActions().get(0).getFirstDevice()))
				{
					response.setOperationSuccess(Boolean.TRUE);
				}
				else
				{
					response.setOperationSuccess(Boolean.FALSE);
				}
			}
			else
			{
				response.setOperationSuccess(Boolean.FALSE);
			}

			return response;
		}

		return (ScheduleResponse)testOtherDefaultModes(response);
	}

}