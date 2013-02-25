package com.sensus.mlc.wui.schedule.action;

import java.util.List;

import com.sensus.common.model.Response;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.JsonResult;
import com.sensus.mlc.wui.base.model.LightJsonResult;
import com.sensus.mlc.wui.base.util.Constants;

/**
 * Action supporting Ajax Callbacks from the schedule page.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class ScheduleAjaxAction extends ActionBase
{
	/**
	 * The list of smartpoint ids to apply/clear a schedule for.
	 */
	private List<Integer> smartpointIds;

	/** The is all rows. */
	private Boolean isAllRows = false;

	/**
	 * The Json result for the apply/clear schedule actions.
	 */
	private JsonResult result;

	/**
	 * The Schedule BCF object.
	 */
	private IScheduleBCF scheduleBCF;

	/** The is monitored. */
	private Boolean isMonitored = false;

	/** The filter url. */
	private Integer[][] filterUrl;

	/** The string filters url. */
	private String[] stringFiltersURL;

	/** The schedule id. */
	private List<Integer> id;

	/** The max smartpoints. */
	private Integer maxSmartpoints;

	/** The bottom. */
	private Double bottom;

	/** The left. */
	private Double left;

	/** The right. */
	private Double right;

	/** The top. */
	private Double top;

	/** The light result. */
	private LightJsonResult lightResult;

	/** The schedule id. */
	private Integer scheduleId;

	/** The schedule request. */
	private ScheduleRequest scheduleRequest;

	private Response response;

	/**
	 * Apply schedule.
	 * 
	 * @param schedule the schedule
	 * @return the string
	 */
	public String applySchedule()
	{

		try
		{

			ScheduleResponse response = getScheduleBCF().initiateApplySmartPointToSchedule(getScheduleRequest());
			setResponse(response);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error while applying schedule", e);
			}
			getResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Reset the scheduling for smartpoints.
	 * 
	 * @param schedule the schedule
	 * @return SUCCESS result, regardless of outcome
	 */
	public String clearSchedule()
	{
		try
		{
			ScheduleResponse response = getScheduleBCF().initiateDeleteSmartpointFromSchedule(getScheduleRequest());
			setResponse(response);
		}
		catch (Exception e)
		{
			LOG.error("Error while clearing schedule", e);
		}
		return SUCCESS;
	}

	/**
	 * Delete schedule.
	 * 
	 * @return the string
	 */
	public String deleteSchedule()
	{
		setResult(new JsonResult());
		try
		{
			ScheduleResponse response = getScheduleBCF().deleteSchedule(scheduleRequest);

			setResponse(response);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error deleting schedule", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Initiate delete schedule.
	 * 
	 * @return the string
	 */
	public String initiateDeleteSchedule()
	{

		try
		{

			ScheduleResponse response = getScheduleBCF().initiateDeleteSchedule(getScheduleRequest());
			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error initiating delete schedule", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Fetch lights by schedule to map.
	 * 
	 * @return the string
	 */
	public String fetchLightByScheduleToMap()
	{
		try
		{

			InquiryLightResponse response = getScheduleBCF().fetchSmartpointByScheduleToMap(getScheduleRequest());

			setResponse(response);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error getting lights by scheudle to map", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Gets the smartpoint ids.
	 * 
	 * @return the smartpoint ids
	 */
	public List<Integer> getSmartpointIds()
	{
		return smartpointIds;
	}

	/**
	 * Sets the smartpoint ids.
	 * 
	 * @param smartpointIds the new smartpoint ids
	 */
	public void setSmartpointIds(List<Integer> smartpointIds)
	{
		this.smartpointIds = smartpointIds;
	}

	/**
	 * Get the Json result for the apply/clear schedule actions.
	 * 
	 * @return the Json result
	 */
	public JsonResult getResult()
	{
		return result;
	}

	/**
	 * Set the Json result for the apply/clear schedule actions.
	 * 
	 * @param smartpointResultIn the Json result
	 */
	public void setResult(JsonResult smartpointResultIn)
	{
		result = smartpointResultIn;
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
	 * Sets the checks if is all rows.
	 * 
	 * @param isAllRows the new checks if is all rows
	 */
	public void setIsAllRows(Boolean isAllRows)
	{
		this.isAllRows = isAllRows;
	}

	/**
	 * Gets the checks if is all rows.
	 * 
	 * @return the checks if is all rows
	 */
	public Boolean getIsAllRows()
	{
		return isAllRows;
	}

	/**
	 * /**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the new checks if is monitored
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		this.isMonitored = isMonitored;
	}

	/**
	 * Gets the checks if is monitored.
	 * 
	 * @return the checks if is monitored
	 */
	public Boolean getIsMonitored()
	{
		return isMonitored;
	}

	/**
	 * Sets the filter url.
	 * 
	 * @param filterUrl the new filter url
	 */
	public void setFilterUrl(Integer[][] filterUrl)
	{
		this.filterUrl = filterUrl;
	}

	/**
	 * Gets the filter url.
	 * 
	 * @return the filter url
	 */
	public Integer[][] getFilterUrl()
	{
		return filterUrl;
	}

	/**
	 * Gets the string filters url.
	 * 
	 * @return the string filters url
	 */
	public String[] getStringFiltersURL()
	{
		return stringFiltersURL;
	}

	/**
	 * Sets the string filters url.
	 * 
	 * @param stringFiltersURL the new string filters url
	 */
	public void setStringFiltersURL(String[] stringFiltersURL)
	{
		this.stringFiltersURL = stringFiltersURL;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public List<Integer> getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(List<Integer> id)
	{
		this.id = id;
	}

	/**
	 * Gets the max smartpoints.
	 * 
	 * @return the max smartpoints
	 */
	public Integer getMaxSmartpoints()
	{
		return maxSmartpoints;
	}

	/**
	 * Sets the max smartpoints.
	 * 
	 * @param maxSmartpoints the new max smartpoints
	 */
	public void setMaxSmartpoints(Integer maxSmartpoints)
	{
		this.maxSmartpoints = maxSmartpoints;
	}

	/**
	 * Gets the bottom.
	 * 
	 * @return the bottom
	 */
	public Double getBottom()
	{
		return bottom;
	}

	/**
	 * Sets the bottom.
	 * 
	 * @param bottom the new bottom
	 */
	public void setBottom(Double bottom)
	{
		this.bottom = bottom;
	}

	/**
	 * Gets the left.
	 * 
	 * @return the left
	 */
	public Double getLeft()
	{
		return left;
	}

	/**
	 * Sets the left.
	 * 
	 * @param left the new left
	 */
	public void setLeft(Double left)
	{
		this.left = left;
	}

	/**
	 * Gets the right.
	 * 
	 * @return the right
	 */
	public Double getRight()
	{
		return right;
	}

	/**
	 * Sets the right.
	 * 
	 * @param right the new right
	 */
	public void setRight(Double right)
	{
		this.right = right;
	}

	/**
	 * Gets the top.
	 * 
	 * @return the top
	 */
	public Double getTop()
	{
		return top;
	}

	/**
	 * Sets the top.
	 * 
	 * @param top the new top
	 */
	public void setTop(Double top)
	{
		this.top = top;
	}

	/**
	 * Gets the light result.
	 * 
	 * @return the light result
	 */
	public LightJsonResult getLightResult()
	{
		return lightResult;
	}

	/**
	 * Sets the light result.
	 * 
	 * @param lightResult the new light result
	 */
	public void setLightResult(LightJsonResult lightResult)
	{
		this.lightResult = lightResult;
	}

	/**
	 * Gets the schedule id.
	 * 
	 * @return the schedule id
	 */
	public Integer getScheduleId()
	{
		return scheduleId;
	}

	/**
	 * Sets the schedule id.
	 * 
	 * @param scheduleId the new schedule id
	 */
	public void setScheduleId(Integer scheduleId)
	{
		this.scheduleId = scheduleId;
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
