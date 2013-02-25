package com.sensus.mlc.wui.schedule.action;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.Response;
import com.sensus.common.util.SensusStringUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.util.ResultUtil;

/**
 * Struts Action rendering the Create Schedule page as well as the Schedule Event Include used by the Create Schedule
 * Page. The Event Template gets passed in the count with the request since each event on the page needs a unique id.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class CreateSchedulePageAction extends LayoutBase
{
	/** CONSTANTS **/

	/** The Constant ERROR_KEY. */
	public static final String ERROR_KEY = "scheduleupdate.actions.openupdatepage";

	/** The Constant HOUR_FORMAT. */
	private static final String HOUR_FORMAT = "h:mm a";

	/** The Constant DAYS_KEY. */
	private static final String DAYS_KEY = "days";

	/** The Constant DATE_KEY. */
	private static final String DATE_KEY = "date";

	/** The Constant STATE_KEY. */
	private static final String STATE_KEY = "state";

	/** The error message. */
	private List<String> errorMessage = new ArrayList<String>();

	/**
	 * The Schedule BCF object.
	 */
	private IScheduleBCF scheduleBCF;

	/**
	 * The number used to generate unique ids for an event when rendering the code.
	 */
	private Integer count;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The turn light on. */
	private Integer turnLightOn;

	/** The turn light off. */
	private Integer turnLightOff;

	/** The sunrise before. */
	private Boolean sunriseBefore;

	/** The sunset before. */
	private Boolean sunsetBefore;

	/** The events list. */
	private List<HashMap> eventsList;

	/** The smartpoint count. */
	private Integer smartpointCount;

	/** The offset intensity. */
	private Integer offsetIntensity;

	/** The schedule request. */
	private ScheduleRequest scheduleRequest;

	/** The response. */
	private Response response;

	/**
	 * Open update off set schedule.
	 * 
	 * @return the string
	 */
	public String openUpdateOffSetSchedule()
	{

		try
		{
			ScheduleRequest request = new ScheduleRequest(getUserContext());

			OffsetSchedule schedule = new OffsetSchedule();
			schedule.setId(getId());

			request.setSchedule(schedule);

			ScheduleResponse response = getScheduleBCF().fetchScheduleById(request);

			List<Schedule> listSchedule = response.getSchedules();

			if (!ValidationUtil.isNullOrEmpty(listSchedule))
			{
				setId(listSchedule.get(0).getId());
				setName(listSchedule.get(0).getName());
				setDescription(listSchedule.get(0).getDescription());
				setSmartpointCount(listSchedule.get(0).getSmartPointCount());
				setTurnLightOff(((OffsetSchedule)listSchedule.get(0)).getSunriseOffsetMinutes());
				setTurnLightOn(((OffsetSchedule)listSchedule.get(0)).getSunsetOffsetMinutes());
				setSunriseBefore(((OffsetSchedule)listSchedule.get(0)).getSunriseBefore());
				setSunsetBefore(((OffsetSchedule)listSchedule.get(0)).getSunsetBefore());
				setOffsetIntensity(((OffsetSchedule)listSchedule.get(0)).getIntensity());
			}
			if (!response.isOperationSuccess())
			{
				ResultUtil.setMessages(getErrorMessage(), response);
				return ERROR;
			}
		}
		catch (Exception e)
		{
			List<String> exceptionMessage = new ArrayList<String>();
			exceptionMessage.add(getText(ERROR_KEY));
			setErrorMessage(exceptionMessage);

			if (LOG.isErrorEnabled())
			{
				LOG.error(getText(ERROR_KEY), e);
			}
			return ERROR;
		}
		return SUCCESS;
	}

	public String fillEventSchedule()
	{

		ScheduleResponse response = getScheduleBCF().fetchScheduleById(getScheduleRequest());

		setResponse(response);

		return SUCCESS;
	}

	public String fillOffsetSchedule()
	{

		ScheduleResponse response = getScheduleBCF().fetchScheduleById(getScheduleRequest());

		setResponse(response);

		return SUCCESS;
	}

	/**
	 * Open update event schedule.
	 * 
	 * @return the string
	 */
	public String openUpdateEventSchedule()
	{
		try
		{

			ScheduleRequest request = new ScheduleRequest(getUserContext());

			EventSchedule eventSchedule = new EventSchedule();
			eventSchedule.setId(getId());

			request.setSchedule(eventSchedule);

			ScheduleResponse response = getScheduleBCF().fetchScheduleById(request);

			List<Schedule> listSchedules = response.getSchedules();

			if (!ValidationUtil.isNullOrEmpty(listSchedules))
			{
				setId(listSchedules.get(0).getId());
				setName(listSchedules.get(0).getName());
				setDescription(listSchedules.get(0).getDescription());
				setSmartpointCount(listSchedules.get(0).getSmartPointCount());
				List<Event> events = ((EventSchedule)listSchedules.get(0)).getEvents();
				List<HashMap> listMap = new ArrayList<HashMap>();
				for (Event event : events)
				{
					Format formatter = new SimpleDateFormat(HOUR_FORMAT);
					String date = formatter.format(event.getTime());

					HashMap eventsData = new HashMap<String, String>();
					List<String> days = new ArrayList<String>();
					for (DaysOfWeekEnum day : event.getDays())
					{
						days.add(day.toString());
					}

					Integer state = event.getIntensity();
					eventsData.put(DAYS_KEY, days);
					eventsData.put(DATE_KEY, date);
					eventsData.put(STATE_KEY, SensusStringUtil.createToString(state));

					listMap.add(eventsData);

				}

				setEventsList(listMap);
			}
			if (!response.isOperationSuccess())
			{
				ResultUtil.setMessages(getErrorMessage(), response);
				return ERROR;
			}
		}
		catch (Exception e)
		{
			List<String> exceptionMessage = new ArrayList<String>();
			exceptionMessage.add(getText(ERROR_KEY));
			setErrorMessage(exceptionMessage);

			if (LOG.isErrorEnabled())
			{
				LOG.error(getText(getText(ERROR_KEY)), e);
			}
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Set the unique number for the event id.
	 * 
	 * @param countIn the unique number to set
	 */
	public void setCount(Integer countIn)
	{
		count = countIn;
	}

	/**
	 * Get the unique number for the event id.
	 * 
	 * @return the unique number to set.
	 */
	public Integer getCount()
	{
		return count;
	}

	/**
	 * Gets the turn light on.
	 * 
	 * @return the turn light on
	 */
	public Integer getTurnLightOn()
	{
		return turnLightOn;
	}

	/**
	 * Sets the turn light on.
	 * 
	 * @param turnLightOn the new turn light on
	 */
	public void setTurnLightOn(Integer turnLightOn)
	{
		this.turnLightOn = turnLightOn;
	}

	/**
	 * Gets the turn light off.
	 * 
	 * @return the turn light off
	 */
	public Integer getTurnLightOff()
	{
		return turnLightOff;
	}

	/**
	 * Sets the turn light off.
	 * 
	 * @param turnLightOff the new turn light off
	 */
	public void setTurnLightOff(Integer turnLightOff)
	{
		this.turnLightOff = turnLightOff;
	}

	/**
	 * Gets the sunrise before.
	 * 
	 * @return the sunrise before
	 */
	public Boolean getSunriseBefore()
	{
		return sunriseBefore;
	}

	/**
	 * Sets the sunrise before.
	 * 
	 * @param sunriseBefore the new sunrise before
	 */
	public void setSunriseBefore(Boolean sunriseBefore)
	{
		this.sunriseBefore = sunriseBefore;
	}

	/**
	 * Gets the sunset before.
	 * 
	 * @return the sunset before
	 */
	public Boolean getSunsetBefore()
	{
		return sunsetBefore;
	}

	/**
	 * Sets the sunset before.
	 * 
	 * @param sunsetBefore the new sunset before
	 */
	public void setSunsetBefore(Boolean sunsetBefore)
	{
		this.sunsetBefore = sunsetBefore;
	}

	/**
	 * Gets the events list.
	 * 
	 * @return the events list
	 */
	public List<HashMap> getEventsList()
	{
		return eventsList;
	}

	/**
	 * Sets the events list.
	 * 
	 * @param eventsList the new events list
	 */
	public void setEventsList(List<HashMap> eventsList)
	{
		this.eventsList = eventsList;
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(List<String> errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the error message
	 */
	public List<String> getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * Sets the smartpoint count.
	 * 
	 * @param smartpointCount the new smartpoint count
	 */
	public void setSmartpointCount(Integer smartpointCount)
	{
		this.smartpointCount = smartpointCount;
	}

	/**
	 * Gets the smartpoint count.
	 * 
	 * @return the smartpoint count
	 */
	public Integer getSmartpointCount()
	{
		return smartpointCount;
	}

	/**
	 * Sets the offset intensity.
	 * 
	 * @param offsetIntensity the new offset intensity
	 */
	public void setOffsetIntensity(Integer offsetIntensity)
	{
		this.offsetIntensity = offsetIntensity;
	}

	/**
	 * Gets the offset intensity.
	 * 
	 * @return the offset intensity
	 */
	public Integer getOffsetIntensity()
	{
		return offsetIntensity;
	}

	/**
	 * @return the scheduleRequest
	 */
	public ScheduleRequest getScheduleRequest()
	{
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
