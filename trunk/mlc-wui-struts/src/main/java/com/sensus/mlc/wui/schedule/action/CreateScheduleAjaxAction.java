package com.sensus.mlc.wui.schedule.action;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * Provides Ajax Callbacks for the Create Schedule Page.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class CreateScheduleAjaxAction extends ActionBase
{

	/** CONSTANTS **/

	/** The Constant CREATE_ERROR_KEY. */
	public static final String CREATE_ERROR_KEY = "schedulecreate.actions.updateschedule";

	/** The Constant UPDATE_ERROR_KEY. */
	public static final String UPDATE_ERROR_KEY = "schedulecreate.actions.createschedule";

	/** The Constant REPEATED_EVENTS. */
	public static final String REPEATED_EVENTS = "schedulecreate.form.repeatedevents";

	/** The Constant MAX_DAYS_ERROR. */
	public static final String MAX_DAYS_ERROR = "schedulecreate.form.maxdays";

	/** The Constant OFFSET_REQUIRED. */
	public static final String OFFSET_REQUIRED = "schedulecreate.form.offset.required";

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(CreateScheduleAjaxAction.class);

	/**
	 * The Schedule BCF object.
	 */
	private IScheduleBCF scheduleBCF;

	/**
	 * The map key for the days value (as returned by the Json request).
	 */
	public static final String KEY_DAYS = "days";

	/**
	 * The map key for the state value (as returned by the Json request).
	 */
	public static final String KEY_STATE = "state";

	/** The Constant KEY_NUMBER_EVENT. */
	public static final String KEY_NUMBER_EVENT = "eventNumber";

	/**
	 * The map key for the time value (as returned by the Json request).
	 */
	public static final String KEY_TIME = "time";

	/** The Constant SPECIAL_CHARACTER. */
	public static final String SPECIAL_CHARACTER = "&#39;";

	/**
	 * Map of the map key values as returned by the Json request and the entries fo the DaysOfWeekEnum.
	 */
	private static final HashMap<String, DaysOfWeekEnum> DAYS_OF_WEEK;
	static
	{
		DAYS_OF_WEEK = new HashMap<String, DaysOfWeekEnum>();
		DAYS_OF_WEEK.put("Mon", DaysOfWeekEnum.MONDAY);
		DAYS_OF_WEEK.put("Tue", DaysOfWeekEnum.TUESDAY);
		DAYS_OF_WEEK.put("Wed", DaysOfWeekEnum.WEDNESDAY);
		DAYS_OF_WEEK.put("Thu", DaysOfWeekEnum.THURSDAY);
		DAYS_OF_WEEK.put("Fri", DaysOfWeekEnum.FRIDAY);
		DAYS_OF_WEEK.put("Sat", DaysOfWeekEnum.SATURDAY);
		DAYS_OF_WEEK.put("Sun", DaysOfWeekEnum.SUNDAY);

	}

	/**
	 * Message key for missing weekday.
	 */
	public static final String MSG_REQUIRED_WEEKDAY_KEY = "schedulecreate.form.weekday.required";

	/**
	 * Message key for invalid weekday.
	 */
	public static final String MSG_INVALID_WEEKDAY_KEY = "schedulecreate.form.weekday.invalid";

	/** The schedule request. */
	private ScheduleRequest scheduleRequest;

	/** The response. */
	private Response response;

	/**
	 * Create a schedule. This is used as an AJAX Callback.
	 * 
	 * @return always SUCCESS.
	 */
	public String createSchedule()
	{

		try
		{
			ScheduleResponse response = getScheduleBCF().insertSchedule(getScheduleRequest());

			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(getText(CREATE_ERROR_KEY), e);
			}
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Update schedule.
	 * 
	 * @return the string
	 */
	public String updateSchedule()
	{

		try
		{

			ScheduleResponse response = getScheduleBCF().initiateUpdateSchedule(getScheduleRequest());

			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(getText(UPDATE_ERROR_KEY), e);
			}
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Set the Schedule BCF object. Injected by Spring.
	 * 
	 * @param scheduleBCFIn the Schedule BCF object
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCFIn)
	{
		scheduleBCF = scheduleBCFIn;
	}

	/**
	 * Get the Schedule BCF object. Injected by Spring.
	 * 
	 * @return the Schedule BCF object
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * @return the scheduleRequest
	 */
	public ScheduleRequest getScheduleRequest()
	{
		scheduleRequest.setUserContext(getUserContext());
		return scheduleRequest;
	}

	/**
	 * @param scheduleRequest the scheduleRequest to set
	 */
	public void setScheduleRequest(ScheduleRequest scheduleRequest)
	{
		this.scheduleRequest = scheduleRequest;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

}