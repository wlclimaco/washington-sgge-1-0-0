package com.sensus.dm.common.schedule.bcl.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.DaysOfWeekEnum;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.Schedule;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.scheduler.processor.ScheduleProcessor;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.schedule.bcl.IScheduleBCL;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class DMScheduleProcessor.
 * 
 * @author QAT Brazil
 */
public class DMScheduleProcessor extends ScheduleProcessor
{

	/** The Constant NINE_HUNDRED_NINETY_NINE. */
	private static final int NINE_HUNDRED_NINETY_NINE = 998;

	/** The Constant FIFTY_NINE. */
	private static final int FIFTY_NINE = 59;

	/** The Constant SEVEN. */
	private static final int SEVEN = 7;

	/** The log. */
	private static transient Log LOG = LogFactory.getLog(DMScheduleProcessor.class);

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = ". Error Message: ";

	/** The Constant SCHEDULE. */
	private static final String SCHEDULE = "### Schedule:";

	/** The Constant UTC_OFFSET. */
	private static final Integer UTC_OFFSET = 3600000;

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL;

	/** The action processor. */
	private IActionBCL actionBCL;

	/**
	 * Instantiates a new ePM schedule processor.
	 */
	public DMScheduleProcessor()
	{
	}

	/**
	 * Gets the schedule bcl.
	 * 
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/**
	 * Sets the schedule bcl.
	 * 
	 * @param scheduleBCL the new schedule bcl
	 */
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/**
	 * Gets the action bcl.
	 * 
	 * @return the action bcl
	 */
	public IActionBCL getActionBCL()
	{
		return actionBCL;
	}

	/**
	 * Sets the action bcl.
	 * 
	 * @param actionBCL the new action bcl
	 */
	public void setActionBCL(IActionBCL actionBCL)
	{
		this.actionBCL = actionBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.scheduler.processor.ScheduleProcessor#execute()
	 */
	@Override
	public void execute()
	{
		printLogDebug(SCHEDULE + " Initializing ### ");

		try
		{

			// creates the request adding start and end date to use on the fetch
			ScheduleRequest request = createRequestToExecute();

			// retrieves schedules to be executed
			InternalResultsResponse<DMSchedule> fetchResponse = getScheduleBCL().fetchSchedulesToExecute(request);

			if (ValidationUtil.isNullOrEmpty(fetchResponse.getResultsList()))
			{
				printLogDebug(SCHEDULE + " There is nothing to execute. Finished ### ");
				return;
			}

			printLogDebug(SCHEDULE + " There are " + fetchResponse.getResultsList().size() + " schedule(s) to execute.");

			if (executeSchedules(fetchResponse.getResultsList()))
			{
				printLogDebug(SCHEDULE + " All schedules successfully executed.");
			}

		}
		catch (Exception e)
		{
			printLogError(e.getMessage());

		}

		printLogDebug(SCHEDULE + " Finished ###");
	}

	/**
	 * Execute schedules.
	 * 
	 * @param schedules the schedules
	 * @return the boolean
	 */
	private Boolean executeSchedules(List<DMSchedule> schedules)
	{

		Boolean allExecuted = true;

		// Execute Schedules
		for (DMSchedule schedule : schedules)
		{
			printLogDebug(SCHEDULE + " Executing schedule : " + schedule.getId() + " - " + schedule.getName());

			// if there is no devices associated with the schedule (devices removed from group for example): failed it !
			if (ValidationUtil.isNullOrEmpty(schedule.getDmAction().getDevices())
					&& ValidationUtil.isNullOrEmpty(schedule.getDmAction().getDevicesExcluded()))
			{
				printLogDebug(SCHEDULE
						+ " There is no devices associated to this schedule. Changing schedule ID "
						+ schedule.getId() + " to FAILED state.");

				schedule.setStatusEnum(ScheduleStatusEnum.FAILED);

				InternalResponse response = getScheduleBCL().updateScheduleStatus(
						new ScheduleRequest(schedule, new UserContext(schedule.getModifyUser())));
				if (response.isInError())
				{
					printLogDebug(SCHEDULE
							+ " Could not update schedule ID " + schedule.getId() + " to FAILED state. Error: "
							+ response.getMessageInfoList());
				}
				continue;

			}

			// prepare schedule to call RNI
			schedule.getDmAction().setStartTime(schedule.getStartTime());
			schedule.getDmAction().setCreateUser(schedule.getModifyUser());

			// call RNI
			InternalResponse internalResponse =
					getActionBCL().applyDeviceToAction(
							new InquiryActionRequest(schedule.getDmAction(), new UserContext(schedule.getModifyUser()),
									schedule.getServiceTypeEnum(), schedule.getName(),
									new DMTenant(schedule.getCustomerId())));

			printLogDebug(SCHEDULE + " Device To Action Applied. Status: " + internalResponse.getStatus()
					+ ERROR_MESSAGE
					+ internalResponse.getMessageInfoList());

			// if the action execution is in error, go to the next schedule to execute
			if (internalResponse.isInError())
			{
				allExecuted = false;
				continue;
			}

			// Update next execution date for the schedule.
			internalResponse = finalizeSchedule(schedule);

			printLogDebug(SCHEDULE + " Finalizing schedule called. Schedule Id: " + schedule.getId() + ". Status: "
					+ internalResponse.getStatus() + ERROR_MESSAGE + internalResponse.getMessageInfoList());

			if (internalResponse.isInError())
			{
				allExecuted = false;
				continue;
			}

			// removes the devices from opt out list
			if (!ValidationUtil.isNullOrEmpty(schedule.getDmAction().getDevicesExcluded()))
			{
				internalResponse =
						getActionBCL().deleteDevicesOptOutList(
								new ActionRequest(new DMAction(schedule.getDmAction().getId()), new UserContext(
										schedule.getModifyUser())));

				printLogDebug(SCHEDULE + " Removing devices from opt-out list. Schedule/ActionId: " + schedule.getId()
						+ "/" + schedule.getDmAction().getId() + ". Status:" + internalResponse.getStatus()
						+ ERROR_MESSAGE + internalResponse.getMessageInfoList());

				if (internalResponse.isInError())
				{
					allExecuted = false;
				}
			}
		}

		return allExecuted;
	}

	/**
	 * Finalize schedule.
	 * 
	 * @param schedule the schedule
	 * @return the internal response
	 */
	private InternalResponse finalizeSchedule(DMSchedule schedule)
	{
		// calculates next execution time according frequency type, occurrences, etc
		calculateNextExecution(schedule);

		// verify if schedule should be executed again
		if (!executeScheduleAgain(schedule.getFrequency()))
		{
			disableSchedule(schedule);
		}

		return getScheduleBCL().updateScheduleStatus(
				new ScheduleRequest(schedule, new UserContext(schedule.getModifyUser())));
	}

	/**
	 * Disable schedule.
	 * 
	 * @param schedule the schedule
	 */
	private void disableSchedule(DMSchedule schedule)
	{
		Calendar now = Calendar.getInstance();

		int diff = TimeZone.getDefault().getOffset(now.getTimeInMillis()) / UTC_OFFSET;

		now.add(Calendar.HOUR_OF_DAY, -1 * diff);

		schedule.setEndDate(now.getTime());
		schedule.setStatusEnum(ScheduleStatusEnum.DISABLED);

	}

	/**
	 * Calculate next execution.
	 * 
	 * @param schedule the schedule
	 */
	private void calculateNextExecution(DMSchedule schedule)
	{
		if (ValidationUtil.isNull(schedule.getFrequency()))
		{
			return;
		}

		// Every schedule must have a frequency (frequency = NEVER when it is onDemand)
		if (FrequencyTypeEnum.NEVER.equals(schedule.getFrequency().getFrequencyTypeEnum()))
		{
			return;
		}

		// Get Start Time
		Calendar now = Calendar.getInstance();
		now.setTime(schedule.getStartTime());

		// New Next Execution = Start Time (Hour and Minute)
		Calendar cNextExecution = Calendar.getInstance();
		cNextExecution.setTime(schedule.getFrequency().getNextExecution());
		cNextExecution.set(Calendar.HOUR, now.get(Calendar.HOUR));
		cNextExecution.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
		cNextExecution.set(Calendar.SECOND, 0);
		cNextExecution.set(Calendar.MILLISECOND, 0);

		// Start Time Today = Next Execution (Date, Month and Year)
		Calendar startTimeToday = Calendar.getInstance();
		startTimeToday.setTime(schedule.getStartTime());
		startTimeToday.set(Calendar.DATE, cNextExecution.get(Calendar.DATE));
		startTimeToday.set(Calendar.MONTH, cNextExecution.get(Calendar.MONTH));
		startTimeToday.set(Calendar.YEAR, cNextExecution.get(Calendar.YEAR));
		cNextExecution.set(Calendar.SECOND, 0);
		cNextExecution.set(Calendar.MILLISECOND, 0);

		// New Action Date = Start Time Today + (Old Action Date - Old Next Execution)
		Calendar cActionTime = Calendar.getInstance();
		cActionTime.setTime(DMConvertUtil.convertDateToDefaultUTC(
				DMConvertUtil.convertIntegerToDate(
						DMConvertUtil.convertDateToInteger(startTimeToday.getTime())
								+ DMConvertUtil.convertDateToInteger(schedule.getDmAction().getActionTime())
								- DMConvertUtil.convertDateToInteger(schedule.getFrequency().getNextExecution())
						)));
		cActionTime.set(Calendar.SECOND, 0);
		cActionTime.set(Calendar.MILLISECOND, 0);

		// Get current date and time
		now = Calendar.getInstance();
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);

		int diff = TimeZone.getDefault().getOffset(now.getTimeInMillis()) / UTC_OFFSET;

		now.add(Calendar.HOUR_OF_DAY, -1 * diff);

		calculateNextExecutionByFrequency(now, schedule, cNextExecution, cActionTime);
	}

	/**
	 * Calculate next execution by frequency.
	 * 
	 * @param now the now
	 * @param schedule the schedule
	 * @param cNextExecution the c next execution
	 * @param cActionTime the c action time
	 */
	private void calculateNextExecutionByFrequency(Calendar now, DMSchedule schedule, Calendar cNextExecution,
			Calendar cActionTime)
	{

		// if it is a demand response action, calculate actionTime and nextExecution according it
		if (!ValidationUtil.isNull(schedule.getDmAction())
				&& schedule.getDmAction().getActionType().getActionTypeEnum()
						.equals(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT))
		{
			applyDemandResponseTime(schedule, cNextExecution, cActionTime);
		}

		switch (schedule.getFrequency().getFrequencyTypeEnum())
		{
			case DAILY:
				cNextExecution.add(Calendar.DATE, schedule.getFrequency().getTimeToRepeat());
				cActionTime.add(Calendar.DATE, schedule.getFrequency().getTimeToRepeat());
				break;

			case MONTHLY:
				cNextExecution.add(Calendar.MONTH, schedule.getFrequency().getTimeToRepeat());
				cActionTime.add(Calendar.MONTH, schedule.getFrequency().getTimeToRepeat());
				break;

			case YEARLY:
				cNextExecution.add(Calendar.YEAR, schedule.getFrequency().getTimeToRepeat());
				cActionTime.add(Calendar.YEAR, schedule.getFrequency().getTimeToRepeat());
				break;

			default:
				calculateWeeklyDaysToAdd(schedule, cNextExecution, cActionTime);
				break;

		}

		schedule.getFrequency().setNextExecution(cNextExecution.getTime());
		schedule.getDmAction().setActionTime(cActionTime.getTime());

	}

	/**
	 * Apply demand response time.
	 * 
	 * @param schedule the schedule
	 * @param cNextExecution the c next execution
	 * @param cActionTime the c action time
	 */
	private void applyDemandResponseTime(DMSchedule schedule, Calendar cNextExecution, Calendar cActionTime)
	{

		Calendar nextExecution = new GregorianCalendar();
		nextExecution.setTime(cNextExecution.getTime());
		nextExecution.set(Calendar.HOUR_OF_DAY, cActionTime.get(Calendar.HOUR_OF_DAY));
		nextExecution.set(Calendar.MINUTE, cActionTime.get(Calendar.MINUTE));
		nextExecution.set(Calendar.SECOND, cActionTime.get(Calendar.SECOND));
		nextExecution.set(Calendar.MILLISECOND, cActionTime.get(Calendar.MILLISECOND));

		Calendar actionTime = new GregorianCalendar();
		actionTime.setTime(cActionTime.getTime());

		// verifies that the date of the action is less than the start date.
		if (actionTime.before(nextExecution))
		{
			// If the date is less then the action
			// adds one day because it is the maximum that can be the difference.
			cActionTime.add(Calendar.DATE, 1);
		}

		// if the dates are the same, but the action time is before the start time, then
		// set the action time as start time.
		if (cActionTime.before(cNextExecution))
		{
			cNextExecution.setTime(cActionTime.getTime());
		}

	}

	/**
	 * Execute schedule again.
	 * 
	 * @param frequency the frequency
	 * @return the boolean
	 */
	private Boolean executeScheduleAgain(Frequency frequency)
	{

		if (FrequencyTypeEnum.NEVER.equals(frequency.getFrequencyTypeEnum()))
		{
			return false;
		}

		if (ValidationUtil.isNull(frequency.getNextExecution()))
		{
			return false;
		}

		if (isEndDate(frequency))
		{
			return false;
		}

		if (isAllOccurrencesExecuted(frequency))
		{
			return false;
		}

		return true;
	}

	/**
	 * Checks if is end date.
	 * 
	 * @param frequency the frequency
	 * @return the boolean
	 */
	private Boolean isEndDate(Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getEndDate())
				&& !ValidationUtil.isNull(frequency.getStartOnDate())
				&& !ValidationUtil.isNull(frequency.getNextExecution()))
		{
			Calendar startOnDate = Calendar.getInstance();
			startOnDate.setTime(frequency.getStartOnDate());
			startOnDate.set(Calendar.HOUR_OF_DAY, 0);
			startOnDate.set(Calendar.MINUTE, 0);
			startOnDate.set(Calendar.SECOND, 0);
			startOnDate.set(Calendar.MILLISECOND, 0);

			Calendar endDate = Calendar.getInstance();
			endDate.setTime(frequency.getEndDate());
			endDate.set(Calendar.HOUR_OF_DAY, 0);
			endDate.set(Calendar.MINUTE, 0);
			endDate.set(Calendar.SECOND, 0);
			endDate.set(Calendar.MILLISECOND, 0);

			Calendar nextExecution = Calendar.getInstance();
			nextExecution.setTime(frequency.getNextExecution());
			nextExecution.set(Calendar.HOUR_OF_DAY, 0);
			nextExecution.set(Calendar.MINUTE, 0);
			nextExecution.set(Calendar.SECOND, 0);
			nextExecution.set(Calendar.MILLISECOND, 0);

			if (startOnDate.equals(endDate) || nextExecution.after(endDate))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if is all occurrences executed.
	 * 
	 * @param frequency the frequency
	 * @return the boolean
	 */
	private Boolean isAllOccurrencesExecuted(Frequency frequency)
	{
		if (!ValidationUtil.isNull(frequency.getEndsAfterOccurrences())
				&& !ValidationUtil.isNull(frequency.getExecutedOccurrences()))
		{
			frequency.setExecutedOccurrences(frequency.getExecutedOccurrences() + 1);
			if (frequency.getEndsAfterOccurrences().equals(frequency.getExecutedOccurrences()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Calculate weekly days to add.
	 * 
	 * @param schedule the schedule
	 * @param cNextExecution the c scheduled next execution
	 * @param cActionTime the c scheduled action time
	 */
	private void calculateWeeklyDaysToAdd(Schedule schedule, Calendar cNextExecution,
			Calendar cActionTime)
	{
		if (!ValidationUtil.isNullOrEmpty(schedule.getFrequency().getDaysOfWeeks())
				&& schedule.getFrequency().getDaysOfWeeks().size() > 0)
		{
			if (schedule.getFrequency().getFrequencyTypeEnum().getValue().equals(FrequencyTypeEnum.WEEKLY.getValue())
					&& schedule.getFrequency().getDaysOfWeeks()
							.get(schedule.getFrequency().getDaysOfWeeks().size() - 1)
							.getValue().equals(cNextExecution.get(Calendar.DAY_OF_WEEK)))
			{
				while (true)
				{
					cNextExecution.add(Calendar.DATE, -1);
					cActionTime.add(Calendar.DATE, -1);
					if (schedule.getFrequency().getDaysOfWeeks().get(0).getValue()
							.equals(cNextExecution.get(Calendar.DAY_OF_WEEK)))
					{
						cNextExecution.add(Calendar.DATE, SEVEN * schedule.getFrequency().getTimeToRepeat());
						cActionTime.add(Calendar.DATE, SEVEN * schedule.getFrequency().getTimeToRepeat());
						break;
					}
				}
			}
			else
			{
				while (true)
				{
					cNextExecution.add(Calendar.DATE, 1);
					cActionTime.add(Calendar.DATE, 1);
					if (schedule.getFrequency().getDaysOfWeeks()
							.contains(DaysOfWeekEnum.enumForValue(cNextExecution.get(Calendar.DAY_OF_WEEK))))
					{
						break;
					}
				}
			}
		}
		else
		{
			cNextExecution.add(Calendar.DATE, SEVEN * schedule.getFrequency().getTimeToRepeat());
			cActionTime.add(Calendar.DATE, SEVEN * schedule.getFrequency().getTimeToRepeat());
		}
	}

	/**
	 * Creates the request to execute.
	 * 
	 * @return the schedule request
	 */
	private ScheduleRequest createRequestToExecute()
	{

		// get end date and time (applied time zone hours difference)
		Calendar cEndDate = Calendar.getInstance();
		cEndDate.set(Calendar.SECOND, FIFTY_NINE);
		cEndDate.set(Calendar.MILLISECOND, NINE_HUNDRED_NINETY_NINE);

		int diff = TimeZone.getDefault().getOffset(cEndDate.getTimeInMillis()) / UTC_OFFSET;

		cEndDate.add(Calendar.HOUR_OF_DAY, -1 * diff);

		// Get start date and time (applied time zone hours difference)
		Calendar cStartDate = Calendar.getInstance();
		cStartDate.setTime(cEndDate.getTime());

		// Removing one hour
		cStartDate.add(Calendar.HOUR_OF_DAY, -1);
		cStartDate.set(Calendar.SECOND, 0);
		cStartDate.set(Calendar.MILLISECOND, 0);

		return new ScheduleRequest(cStartDate.getTime(), cEndDate.getTime());
	}

	/**
	 * Prints the log debug.
	 * 
	 * @param log the log
	 */
	private void printLogDebug(String log)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(log);
		}
	}

	/**
	 * Prints the log error.
	 * 
	 * @param log the log
	 */
	private void printLogError(String log)
	{
		if (LOG.isErrorEnabled())
		{
			LOG.error(log);
		}
	}
}
