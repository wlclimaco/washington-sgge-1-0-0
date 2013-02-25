package com.sensus.mlc.wui.schedule.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;

/**
 * The Class ScheduleSearchAjaxAction.
 */
@SuppressWarnings("serial")
/**
 * Action providing the callback for the schedule search.
 * @author Anke Doerfel-Parker
 */
public class ScheduleSearchAjaxAction extends ActionBase
{
	/**
	 * Key for missing date display value.
	 */
	private static final String MSG_MISSING_ADDED_DATE_KEY = "schedule.page.dateadded.missing";

	/**
	 * The logger for this action.
	 */
	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * The Schedule BCF object.
	 */
	private IScheduleBCF scheduleBCF;

	/**
	 * The Json result returned by the search.
	 */
	private SearchJsonResult scheduleSearchResult;

	/** The response. */
	private Response response;

	/** The inquiry schedule request. */
	private InquiryScheduleRequest inquiryScheduleRequest;

	/**
	 * AJAX callback for searching groups. At this point, the search doesn't
	 * take any parameters. It populates <code>groupSearchResult</code> which is
	 * returned as JSON. Error messages are communicated via the JSON object.
	 * 
	 * @return always "SUCCESS"
	 */

	public String search()
	{

		try
		{

			// ADDS session user context to request
			getInquiryScheduleRequest().setUserContext(getUserContext());

			InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(getInquiryScheduleRequest());

			setResponse(response);

		}
		catch (Exception e)
		{
			logger.error("Error searching for schedules", e);
		}
		return SUCCESS;

	}

	/**
	 * Set the Json result for the search.
	 * 
	 * @return the Json result for the search
	 */
	public SearchJsonResult getScheduleSearchResult()
	{
		return scheduleSearchResult;
	}

	/**
	 * Get the Json result for the search.
	 * 
	 * @param groupSearchResultIn the Json result for the search
	 */
	public void setScheduleSearchResult(SearchJsonResult groupSearchResultIn)
	{
		scheduleSearchResult = groupSearchResultIn;
	}

	/**
	 * Set the Schedule BCF object.
	 * 
	 * @param scheduleBCFIn the Schedule BCF object.
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCFIn)
	{
		scheduleBCF = scheduleBCFIn;
	}

	/**
	 * Get the Schedule BCF object.
	 * 
	 * @return the Schedule BCF object
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
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

	/**
	 * @return the inquiryScheduleRequest
	 */
	public InquiryScheduleRequest getInquiryScheduleRequest()
	{
		return inquiryScheduleRequest;
	}

	/**
	 * @param inquiryScheduleRequest the inquiryScheduleRequest to set
	 */
	public void setInquiryScheduleRequest(InquiryScheduleRequest inquiryScheduleRequest)
	{
		this.inquiryScheduleRequest = inquiryScheduleRequest;
	}

}
