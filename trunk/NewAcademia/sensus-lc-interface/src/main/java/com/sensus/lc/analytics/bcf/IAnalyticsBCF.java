package com.sensus.lc.analytics.bcf;

import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Interface IAnalyticsBCF.
 * 
 * @author Guilherme Vicentine - QAT Brazil.
 * 
 */
public interface IAnalyticsBCF
{

	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the inquiry analytics response
	 */
	InquiryAnalyticsResponse fetchAllAnalyticsByGroup(InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch the analytics data in a period.
	 * 
	 * @param analyticsRequest the request
	 * @return the analytics response
	 */
	AnalyticsResponse fetchAllAnalyticsByDate(AnalyticsRequest analyticsRequest);

	/**
	 * Fetch the last 5 light alarms.
	 * 
	 * @param analyticsRequest the request
	 * @return the analytics response
	 */
	AnalyticsResponse fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest);

	/**
	 * Fetch the last 30 day light's alerts.
	 * 
	 * @param analyticsRequest the request
	 * @return the analytics response
	 */
	AnalyticsResponse fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest);

	/**
	 * Fetch dashboard resume.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the analytics response
	 */
	AnalyticsResponse fetchDashboardResume(AnalyticsRequest analyticsRequest);

	/**
	 * Fetch the analytics data in a period.
	 * 
	 * @param analyticsRequest the request
	 * @return the analytics response
	 */
	AnalyticsResponse fetchDashboardHeader(AnalyticsRequest analyticsRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param analyticsCSVRequest the analytics csv request
	 * @return the cSV response
	 */
	CSVResponse generateFileCSV(AnalyticsCSVRequest analyticsCSVRequest);

	/**
	 * Fetch all analytics group.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the analytics response
	 */
	AnalyticsResponse fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param request the request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(InquiryPaginationRequest request);
}
