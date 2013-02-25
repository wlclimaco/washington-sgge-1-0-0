package com.sensus.mlc.wui.dashboard.action;

import com.sensus.common.model.Response;
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * The Class DashBoardSearchAjaxAction is called when table is rendering.
 * 
 * @author Alexandre Tiveron
 */

@SuppressWarnings("serial")
public class DashboardSearchAjaxAction extends ActionBase
{

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/** The type failure. */
	private Integer typeFailure;

	/** The response. */
	private Response response;

	/** The analytics request. */
	private AnalyticsRequest analyticsRequest;

	/**
	 * Search Lights. This method will take into account the request parameters and populate the search result
	 * accordingly.
	 * 
	 * @return always "SUCCESS"
	 */
	public String search()
	{
		try
		{
			AnalyticsResponse analytcsResp = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(getAnalyticsRequest());
			setResponse(analytcsResp);

		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not search dashboard", ex);
			}
		}

		return SUCCESS;
	}

	/**
	 * Load header data.
	 * 
	 * @return the string
	 */
	public String loadHeaderData()
	{
		try
		{
			AnalyticsResponse analytcsResponse = getAnalyticsBCF().fetchDashboardHeader(getAnalyticsRequest());

			setResponse(analytcsResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not load Header Data", ex);
			}
		}

		return SUCCESS;

	}

	/**
	 * Load light control table.
	 * 
	 * @return the string
	 */
	public String loadLightControlTable()
	{
		try
		{
			AnalyticsResponse analytcsResponse = getAnalyticsBCF().fetchDashboardResume(getAnalyticsRequest());
			setResponse(analytcsResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not load Header Data", ex);
			}
		}

		return SUCCESS;
	}

	/**
	 * Load highcharts data.
	 * 
	 * @return the string
	 */
	public String loadHighchartsData()
	{
		try
		{
			AnalyticsResponse analytcsResponse = getAnalyticsBCF().fetchAnalyticsAlertsByType(getAnalyticsRequest());
			setResponse(analytcsResponse);
		}
		catch (Exception ex)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Could not load Header Data", ex);
			}
		}

		return SUCCESS;
	}

	/**
	 * Sets the analytics bcf.
	 * 
	 * @param analyticsBCF the analyticsBCF to set
	 */
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
	}

	/**
	 * Gets the analytics bcf.
	 * 
	 * @return the analyticsBCF
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return analyticsBCF;
	}

	/**
	 * Sets the type failure.
	 * 
	 * @param typeFailure the typeFailure to set
	 */
	public void setTypeFailure(Integer typeFailure)
	{
		this.typeFailure = typeFailure;
	}

	/**
	 * Gets the type failure.
	 * 
	 * @return the typeFailure
	 */
	public Integer getTypeFailure()
	{
		return typeFailure;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * @return the analyticsRequest
	 */
	public AnalyticsRequest getAnalyticsRequest()
	{
		return analyticsRequest;
	}

	/**
	 * @param analyticsRequest the analyticsRequest to set
	 */
	public void setAnalyticsRequest(AnalyticsRequest analyticsRequest)
	{
		this.analyticsRequest = analyticsRequest;
	}

}
