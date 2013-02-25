package com.sensus.mlc.wui.smartpoint.action;

import com.sensus.common.model.Response;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.SearchJsonResult;

/**
 * The Class SmartpointHistorySearchAjaxAction.
 */
@SuppressWarnings("serial")
/**
 * Action for searching smartpoint history.
 * 
 * @author Alex Tiveron
 */
public class SmartpointHistorySearchAjaxAction extends ActionBase
{

	/** CONSTANTS **/

	/** The Constant HOUR_FORMAT. */
	public static final String HOUR_FORMAT = "HH:mm:ss";

	/** The smartpoint search result. */
	private SearchJsonResult smartpointSearchResult;

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The Constant HOURS. */
	public static final String HOURS = "process.page.hours";

	/** The Constant MINUTES. */
	public static final String MINUTES = "process.page.minutes";

	/** The Constant SECONDS. */
	public static final String SECONDS = "process.page.seconds";

	/** The Constant DAYS. */
	public static final String DAYS = "process.page.days";

	/** The Constant EAST. */
	public static final String EAST = "process.page.east";

	/** The Constant TYPE. */
	public static final String ACTION_TYPE = "action_type";

	/** The Constant DAYS_END. */
	public static final String START_DATE = "total_days";

	/** The Constant START_DATE. */
	public static final String DAYS_END = "view_from";

	/** The Constant FILTER_USER. */
	public static final String FILTER_USER = "users";

	/** The Constant TOKEN. */
	public static final String PIPE = "[|]";

	/** The Constant TEXT_QUERY. */
	public static final String TEXT_QUERY = "query";

	/** The Constant MARKER. */
	public static final String MARKER = "a";

	/** The Constant SMTPROTECTED_KEY. */
	public static final String SMTPROTECTED_KEY = "smpProtected";

	/** The Constant SYNC_KEY. */
	public static final String SYNC_KEY = "sync";

	/** The Constant ASYNC_KEY. */
	public static final String ASYNC_KEY = "async";

	/** The Constant ABORTED_KEY. */
	public static final String ABORTED_KEY = "aborted";

	/** The Constant DEACTIVATED_KEY. */
	public static final String DEACTIVATED_KEY = "deactivated";

	/** The Constant MAINTENANCE_KEY. */
	public static final String MAINTENANCE_KEY = "maintenance";

	/** The Constant MONTH_FORMAT. */
	public static final String MONTH_FORMAT = "MMMMM";

	/** The Constant DAY_FORMAT. */
	public static final String DAY_FORMAT = "dd";

	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant TODAY. */
	public static final String TODAY = "process.table.table.today";

	/** The Constant ERROR_COLD_NOT_SEARCH_LIGHT_HISTORY. */
	public static final String ERROR_COLD_NOT_SEARCH_LIGHT_HISTORY = "Could not search Light History";

	/** The Constant EMPTY_STRING. */
	public static final String EMPTY_STRING = "";

	/** The inquiry light request. */
	private InquiryLightRequest inquiryLightRequest;

	/** The response. */
	private Response response;

	/**
	 * Search.
	 * 
	 * @return the string
	 */
	public String search()
	{
		try
		{
			// Call BCF Layer
			InquiryLightResponse inquiryLightResponse =
					getSmartPointAccessorBCF().fetchLightHistory(getInquiryLightRequest());
			setResponse(inquiryLightResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(ERROR_COLD_NOT_SEARCH_LIGHT_HISTORY, ex);
			}
		}
		return SUCCESS;

	}

	/**
	 * Gets the smartpoint search result.
	 * 
	 * @return the smartpointSearchResult
	 */
	public SearchJsonResult getSmartpointSearchResult()
	{
		return smartpointSearchResult;
	}

	/**
	 * Sets the smartpoint search result.
	 * 
	 * @param smartpointSearchResult the smartpointSearchResult to set
	 */
	public void setSmartpointSearchResult(SearchJsonResult smartpointSearchResult)
	{
		this.smartpointSearchResult = smartpointSearchResult;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
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
	 * @return the inquiryLightRequest
	 */
	public InquiryLightRequest getInquiryLightRequest()
	{
		return inquiryLightRequest;
	}

	/**
	 * @param inquiryLightRequest the inquiryLightRequest to set
	 */
	public void setInquiryLightRequest(InquiryLightRequest inquiryLightRequest)
	{
		this.inquiryLightRequest = inquiryLightRequest;
	}
}
