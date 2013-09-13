package com.sensus.dm.controller.importfile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sensus.common.model.Message;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.controller.importfile.model.ScheduleModel;
import com.sensus.dm.controller.model.UserSettings;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.RemoteArmConnectAction;
import com.sensus.dm.elec.action.model.RemoteConnectAction;
import com.sensus.dm.elec.action.model.RemoteDisconnectAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;
import com.sensus.dm.elec.device.model.DeviceClassEnum;
import com.sensus.dm.water.action.model.DemandReadAction;

/**
 * The Class ImportScheduleAPIControler.
 */
@Controller
@RequestMapping("/schedule")
public class ImportScheduleAPIController extends ImportFileAPIController
{
	/** The Constant AFTER. */
	private static final String AFTER = "after";

	/** The Constant DATE_FORMAT_PT. */
	private static final String DATE_FORMAT_PT = "dd/MM/yyyy";

	/** The Constant DATE_FORMAT_EN. */
	private static final String DATE_FORMAT_EN = "MM/dd/yyyy";

	/** The Constant TIME_FORMAT_EN. */
	private static final String TIME_FORMAT_EN = " hh:mma";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ImportScheduleAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TagAPIController";

	/** The Constant FLEXNET_ID. */
	private static final String NETWORD_ADDRESS = "NETWORK_ADDRESS";

	/** The Constant NEVER. */
	private static final String NEVER = "never";

	/** The Constant INSERT. */
	private static final String INSERT = "INSERT";

	/** The Constant COMMA. */
	private static final String COMMA = ",";

	/** The Constant ON. */
	private static final String ON = "on";

	/** The Constant METER_ID. */
	private static final String DEVICE_ID = "DEVICE_ID";

	/** The Constant SEND_LATER. */
	private static final String SEND_LATER = "sendLater";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant UPDATE. */
	private static final String UPDATE = "UPDATE";

	/** The Constant TRUE. */
	private static final String TRUE = "true";

	/** The day weekly. */
	private String dayWeekly;

	/** The action name. */
	private String actionName;

	/**
	 * Gets the day weekly.
	 * 
	 * @return the day weekly
	 */
	public String getDayWeekly()
	{
		return dayWeekly;
	}

	/**
	 * Sets the day weekly.
	 * 
	 * @param dayWeekly the new day weekly
	 */
	public void setDayWeekly(String dayWeekly)
	{
		this.dayWeekly = dayWeekly;
	}

	/**
	 * Gets the action name.
	 * 
	 * @return the action name
	 */
	public String getActionName()
	{
		return actionName;
	}

	/**
	 * Sets the action name.
	 * 
	 * @param actionName the new action name
	 */
	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	/**
	 * Gets the locale date format.
	 * 
	 * @param dateFormat the date format
	 * @return the locale date format
	 */
	private DateFormat getLocaleDateFormat(String dateFormat)
	{
		if (dateFormat.equalsIgnoreCase(DATE_FORMAT_EN))
		{
			return new SimpleDateFormat(DATE_FORMAT_EN + TIME_FORMAT_EN);
		}
		else if (dateFormat.equalsIgnoreCase(DATE_FORMAT_PT))
		{
			return new SimpleDateFormat(DATE_FORMAT_PT + TIME_FORMAT_EN);
		}

		return null;
	}

	/**
	 * Fill frequency.
	 * 
	 * @param model the model
	 * @param ends the ends
	 * @param occurrences the occurrences
	 * @param after the after
	 * @param frequency the frequency
	 * @param df the df
	 * @throws Exception the exception
	 */
	private void fillFrequency(ScheduleModel model, String ends, Integer occurrences, String after,
			Frequency frequency, DateFormat df, String timezone) throws Exception
	{
		if (!ends.equals(NEVER))
		{
			frequency.setNeverEnds(false);
			if (ends.equals(AFTER))
			{
				frequency.setEndsAfterOccurrences(occurrences);
			}
			else if (ends.equals(ON))
			{
				frequency.setEndDate(DMConvertUtil.convertDateToUTC(df
						.parse(after + SPACE + model.getScheduleEventTime()), Integer.valueOf(timezone)));
			}
		}
		else
		{
			frequency.setNeverEnds(true);
		}
	}

	/**
	 * Adds the days of week.
	 * 
	 * @param model the model
	 * @param frequency the frequency
	 * @param dayWeekly the day weekly
	 */
	private void addDaysOfWeek(ScheduleModel model, Frequency frequency, String dayWeekly)
	{

		String[] weekDays = null;

		model.setDayWeekly(dayWeekly);

		weekDays = model.getDayWeekly().split(COMMA);

		frequency.setDaysOfWeeksEnum(new ArrayList<DaysOfWeekEnum>());

		for (String weekday : weekDays)
		{
			frequency.getDaysOfWeeks()
					.add(DaysOfWeekEnum.enumForValue(Integer.valueOf(weekday.trim())));
		}
	}

	/**
	 * Validation insert schedule.
	 * 
	 * @param typeOperation the type operation
	 * @param model the model
	 * @return the schedule
	 * @throws Exception the exception
	 */
	public DMSchedule validationInsertSchedule(String typeOperation, ScheduleModel model) throws Exception
	{
		DMSchedule schedule = new DMSchedule();
		schedule.setName(model.getScheduledEventName());
		schedule.setDescription(model.getScheduledEventDescription());

		if (typeOperation.equals(UPDATE))
		{
			model.setActionName(model.getActionTest());
		}
		else if (!ValidationUtil.isNullOrEmpty(model.getActionTest()))
		{
			model.setActionName(model.getActionTest());
		}

		Frequency frequency = new Frequency();
		frequency.setExecutedOccurrences(0);

		UserSettings userSettings = getUserSettings();

		List<Property> properties = new ArrayList<Property>();

		DMAction action = null;
		DateFormat df = getLocaleDateFormat(userSettings.getDateFormatMask());
		Date dateJavaHan;
		String defineEventWhen;

		if (ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.getActionTypeName().equals(model.getActionName()))
		{
			defineEventWhen = model.getDemandResponseWhen() + SPACE + model.getDemand_response_time();
		}
		else
		{
			defineEventWhen = model.getScheduledEventWhen() + SPACE + model.getScheduleEventTime();
		}

		Date dateJava = df.parse(defineEventWhen);

		// Frequency Date. When action is Demand Response or Send text message the frequency date is equals schedule
		Date frequencyDate =
				DMConvertUtil.convertDateToUTC(dateJava, Integer.valueOf(userSettings.getTimeZoneMinutes()));

		if (model.getActionName().equals(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE.getActionTypeName()))
		{
			SendHanTextMessageAction sendHanTextMessageAction = new SendHanTextMessageAction();
			sendHanTextMessageAction.setDurationHANTextMessage(model.getTextMessageDuration());
			sendHanTextMessageAction.setTextMessage(model.getTextMessageHan());

			if (SEND_LATER.equals(model.getPreProgram().get(0)))
			{

				String defineHanDate = model.getSendMessagesDate() + SPACE + model.getSendMessagesTime();

				if (!ValidationUtil.isNullOrEmpty(defineHanDate))
				{
					dateJavaHan = df.parse(defineHanDate);

					sendHanTextMessageAction.setActionTime(DMConvertUtil
							.convertDateToUTC(dateJava, Integer.valueOf(userSettings.getTimeZoneMinutes())));
					frequency.setNextExecution(DMConvertUtil.convertDateToUTC(dateJavaHan,
							Integer.valueOf(userSettings.getTimeZoneMinutes())));
					schedule.setStartTime(DMConvertUtil.convertDateToUTC(dateJavaHan,
							Integer.valueOf(userSettings.getTimeZoneMinutes())));
				}
			}
			else
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				dateJavaHan = calendar.getTime();
				sendHanTextMessageAction.setActionTime(DMConvertUtil.convertDateToUTC(dateJava,
						Integer.valueOf(userSettings.getTimeZoneMinutes())));
				frequency.setNextExecution(DMConvertUtil.convertDateToDefaultUTC(new Date()));
				schedule.setStartTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
			}

			frequencyDate = schedule.getStartTime();

			action = sendHanTextMessageAction;
		}
		else if (!ValidationUtil.isNullOrEmpty(model.getActionName())
				&& model.getActionName().equals(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.getActionTypeName()))
		{
			DemandResponseEventAction demandResponseEventAction = new DemandResponseEventAction();

			demandResponseEventAction.setDemandResponseDuration(model.getDemandResponseDuration());
			demandResponseEventAction.setEnrollmentCode(model.getEnrollmentCode());
			demandResponseEventAction.setHeating(model.getPctHeating());
			demandResponseEventAction.setCooling(model.getPctCooling());
			demandResponseEventAction.setDutyCycleRate(model.getDutyCycle());
			demandResponseEventAction.setLoadAdjustment(model.getLoadAdjustment());
			demandResponseEventAction.setCriticalityLevel(model.getHanCriticality());

			if (ValidationUtil.isNullOrEmpty(model.getHanStart()))
			{
				demandResponseEventAction.setRandomizeStart(Boolean.FALSE);
			}
			else if (model.getHanStart().equals(ON))
			{
				demandResponseEventAction.setRandomizeStart(Boolean.TRUE);
			}
			else
			{
				demandResponseEventAction.setRandomizeStart(Boolean.FALSE);
			}

			if (ValidationUtil.isNullOrEmpty(model.getHanEnd()))
			{
				demandResponseEventAction.setRandomizeEnd(Boolean.FALSE);
			}
			else if (model.getHanEnd().equals(ON))
			{
				demandResponseEventAction.setRandomizeEnd(Boolean.TRUE);
			}
			else
			{
				demandResponseEventAction.setRandomizeEnd(Boolean.FALSE);
			}

			if (!ValidationUtil.isNullOrEmpty(model.getHancheck()))
			{
				for (String id : model.getHancheck())
				{
					if (id.equals(DeviceClassEnum.HVAC_COMPRESSOR.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.HVAC_COMPRESSOR.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.STRIP_HEATER.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.STRIP_HEATER.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.WATER_HEATER.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.WATER_HEATER.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.POOL_PUMP.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.POOL_PUMP.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.SMART_APPLIANCES.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.SMART_APPLIANCES.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.IRRIGATION_PUMP.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.IRRIGATION_PUMP.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.MANAGED_COMMERCIAL.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.MANAGED_COMMERCIAL.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.SIMPLE_MISC.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.SIMPLE_MISC.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.EXTERIOR_LIGHTING.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.EXTERIOR_LIGHTING.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.INTERIOR_LIGHTING.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.INTERIOR_LIGHTING.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.ELECTRIC_VEHICLE.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.ELECTRIC_VEHICLE.toString(), TRUE));
					}
					else if (id.equals(DeviceClassEnum.GENERATION_SYSTEMS.getValue()))
					{
						properties.add(new Property(DeviceClassEnum.GENERATION_SYSTEMS.toString(), TRUE));
					}
				}
			}
			schedule.setProperties(properties);

			if (!ValidationUtil.isNull(model.getDemandResponseDate())
					&& !ValidationUtil.isNull(model.getDemandResponseTime()))
			{
				String demandResponseDate = model.getDemandResponseDate() + SPACE + model.getDemandResponseTime();

				if (!ValidationUtil.isNullOrEmpty(demandResponseDate))
				{
					dateJavaHan = df.parse(demandResponseDate);
					demandResponseEventAction.setActionTime(dateJava);
					frequency.setNextExecution(DMConvertUtil.convertDateToUTC(dateJavaHan,
							Integer.valueOf(userSettings.getTimeZoneMinutes())));
					schedule.setStartTime(DMConvertUtil.convertDateToUTC(dateJavaHan,
							Integer.valueOf(userSettings.getTimeZoneMinutes())));
				}
			}

			frequencyDate = schedule.getStartTime();

			action = demandResponseEventAction;
		}
		else
		{
			if (!ValidationUtil.isNullOrEmpty(model.getActionName()) && model.getActionName()
					.equals(ActionTypeEnum.REMOTE_ARM_CONNECT.getActionTypeName()))
			{
				action = new RemoteArmConnectAction();
			}
			else if (!ValidationUtil.isNullOrEmpty(model.getActionName()) && model.getActionName()
					.equals(ActionTypeEnum.REMOTE_CONNECT.getActionTypeName()))
			{
				action = new RemoteConnectAction();
			}
			else if (!ValidationUtil.isNullOrEmpty(model.getActionName()) && model.getActionName()
					.equals(ActionTypeEnum.REMOTE_DISCONNECT.getActionTypeName()))
			{
				action = new RemoteDisconnectAction();
			}
			else if (!ValidationUtil.isNullOrEmpty(model.getActionName()) && model.getActionName()
					.equals(ActionTypeEnum.DEMAND_READ.getActionTypeName()))
			{
				action = new DemandReadAction();
			}
			else
			{
				action = new DemandResetEventAction();
			}

			frequency.setNextExecution(DMConvertUtil.convertDateToUTC(dateJava,
					Integer.valueOf(userSettings.getTimeZoneMinutes())));
			schedule.setStartTime(DMConvertUtil.convertDateToUTC(dateJava,
					Integer.valueOf(userSettings.getTimeZoneMinutes())));
			action.setActionTime(DMConvertUtil.convertDateToUTC(dateJava,
					Integer.valueOf(userSettings.getTimeZoneMinutes())));
		}

		// Create the date in the format "00/00/0000 0:00am"
		schedule.setCreateDate(DMConvertUtil.convertDateToUTC(dateJava,
				Integer.valueOf(userSettings.getTimeZoneMinutes())));

		if (ValidationUtil.isNull(model.getRepeatCheckBox()))
		{
			model.setRepeatCheckBox(false);
		}

		if (model.getRepeatCheckBox())
		{
			frequency.setFrequencyTypeEnum(FrequencyTypeEnum.enumForValue(model.getRepeatType()));

			if (!ValidationUtil.isNull(frequency.getFrequencyTypeEnum()))
			{

				frequency.setStartOnDate(frequencyDate);

				switch (frequency.getFrequencyTypeEnum())
				{
					case DAILY:

						frequency.setTimeToRepeat(Integer.parseInt(model.getRepeatsEveryValueDaily()));
						fillFrequency(model, model.getEndsDaily(), model.getOccurrencesDaily(),
								model.getDateAfterDaily(), frequency, df, userSettings.getTimeZoneMinutes());

						break;

					case EVERY_WEEKDAY:

						fillFrequency(model, model.getEndsWeekday(), model.getOccurrencesWeekday(),
								model.getDateAfterWeekday(), frequency, df, userSettings.getTimeZoneMinutes());

						addDaysOfWeek(model, frequency, "2,3,4,5,6");

						break;

					case EVERY_MON_WED_FRI:

						fillFrequency(model, model.getEndsEveryOther(), model.getOccurrencesEveryOther(),
								model.getDateAfterEveryOther(), frequency, df, userSettings.getTimeZoneMinutes());

						addDaysOfWeek(model, frequency, "2,4,6");

						break;

					case EVERY_TUE_THURS:

						fillFrequency(model, model.getEndsEveryT(), model.getOccurrencesEveryT(),
								model.getDateAfterEveryT(), frequency, df, userSettings.getTimeZoneMinutes());

						addDaysOfWeek(model, frequency, "3,5");

						break;

					case WEEKLY:
						frequency.setTimeToRepeat(Integer.parseInt(model.getRepeatsEveryValueWeekly()));

						String[] weekDays = null;

						if (!ValidationUtil.isNullOrEmpty(model.getDayWeekly()))
						{
							weekDays = model.getDayWeekly().split(COMMA);

							frequency.setDaysOfWeeksEnum(new ArrayList<DaysOfWeekEnum>());

							for (String weekday : weekDays)
							{
								frequency.getDaysOfWeeks()
										.add(DaysOfWeekEnum.enumForValue(Integer.valueOf(weekday.trim())));
							}

						}

						fillFrequency(model, model.getEndsWeekly(), model.getOccurrencesWeekly(),
								model.getDateAfterWeekly(), frequency, df, userSettings.getTimeZoneMinutes());

						break;

					case MONTHLY:

						frequency.setTimeToRepeat(Integer.parseInt(model.getRepeatsEveryValueMonthly()));

						if (model.getRepeatByMonthly().equals("day-week"))
						{
							frequency.setDayOfWeek(true);
						}
						else if (model.getRepeatByMonthly().equals("date"))
						{
							frequency.setDayOfWeek(false);
						}

						fillFrequency(model, model.getEndsMonthly(), model.getOccurrencesMonthly(),
								model.getDateAfterMonthly(), frequency, df, userSettings.getTimeZoneMinutes());

						break;

					case YEARLY:
						frequency.setTimeToRepeat(Integer.parseInt(model.getRepeatsEveryValueYearly()));

						fillFrequency(model, model.getEndsYearly(), model.getOccurrencesYearly(),
								model.getDateAfterYearly(), frequency, df, userSettings.getTimeZoneMinutes());
						break;

					case CUSTOM:
						break;

					default:
						break;
				}
			}
			else
			{
				frequency.setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);
			}
		}
		else
		{
			frequency.setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);
		}

		schedule.setFrequency(frequency);

		schedule.setId(model.getScheduledEventId());

		if (!ValidationUtil.isNullOrEmpty(model.getGroupList()))
		{
			action.setGroups(new ArrayList<Group>());
			for (String groupId : model.getGroupList().split(COMMA))
			{
				action.getGroups().add(new Group(new Integer(groupId.trim())));
			}
		}
		else
		{
			action.setGroups(null);
		}

		if (!ValidationUtil.isNullOrEmpty(model.getTagList()))
		{
			action.setTags(new ArrayList<Tag>());
			for (String tagId : model.getTagList().split(COMMA))
			{
				action.addGroup(new Group(new Integer(tagId.trim())));
			}
		}
		else
		{
			action.setTags(null);
		}

		if (typeOperation.equals(INSERT))
		{
			schedule.setModelAction(PersistanceActionEnum.INSERT);
		}
		else
		{
			schedule.setModelAction(PersistanceActionEnum.UPDATE);
			action.setId(model.getActionViewId());
		}

		action.setActionType(new ActionType(ActionTypeEnum.enumForValue(Integer.valueOf(model.getActionId()))));

		schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);

		if (Boolean.TRUE.equals(model.getRecentRequestMonitor()))
		{
			action.setIsMonitored(Boolean.TRUE);
		}
		else
		{
			action.setIsMonitored(Boolean.FALSE);
		}

		action.setOnDemand(Boolean.FALSE);

		schedule.setDmAction(action);
		return schedule;
	}

	/**
	 * Insert.
	 * 
	 * @param scheduleModel the schedule model
	 * @return the response
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ScheduleModel upload(ScheduleModel scheduleModel, HttpServletRequest request)
	{
		ScheduleResponse response = new ScheduleResponse();

		ScheduleRequest scheduleRequest = new ScheduleRequest();

		try
		{

			addUserContextToRequest(scheduleRequest);

			if (!ValidationUtil.isNull(scheduleModel.getImportBy()))
			{
				if (scheduleModel.getImportBy().equals(DEVICE_ID))
				{
					scheduleRequest.setIdFileType(PropertyEnum.DEVICE_ID);
				}
				else if (scheduleModel.getImportBy().equals(NETWORD_ADDRESS))
				{
					scheduleRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
				}
			}

			if (!ValidationUtil.isNull(scheduleModel.getUpload()))
			{
				File f = new File(FILE_NAME);
				scheduleModel.getUpload().transferTo(f);
				scheduleRequest.setIdsFile(f);
			}
			else if (!ValidationUtil.isNullOrEmpty(scheduleModel.getMeterList()))
			{
				scheduleRequest.setUploadIds(scheduleModel.getMeterList());
			}

			if (!ValidationUtil.isNull(scheduleModel.getbMonitored()))
			{
				scheduleRequest.setIsMonitored(scheduleModel.getbMonitored());
			}

			if (ValidationUtil.isNull(scheduleModel.getScheduledEventId()))
			{
				scheduleRequest.setSchedule(validationInsertSchedule(INSERT, scheduleModel));
				response = getScheduleBCF().insertSchedule(scheduleRequest);

				if (response.isOperationSuccess())
				{
					scheduleModel.setMessageSuccess(INSERT);
				}
			}
			else
			{
				scheduleRequest.setSchedule(validationInsertSchedule(UPDATE, scheduleModel));
				response = getScheduleBCF().updateSchedule(scheduleRequest);

				if (response.isOperationSuccess())
				{
					scheduleModel.setMessageSuccess(UPDATE);
				}
			}

			if (response.isOperationSuccess())
			{
				scheduleModel.setUploadFileProcess(scheduleRequest.getFileName());
				scheduleModel.setActionId(response.getSchedules().get(0).getDmAction().getId().toString());
				scheduleModel.setScheduledEventName(scheduleRequest.getSchedule().getName());
				scheduleModel.setProcessId(scheduleRequest.getProcessId());
			}
			else
			{
				for (Message msg : response.getMessageList())
				{
					scheduleModel.setMessageError(msg.getText());
				}
			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return scheduleModel;
	}
}
