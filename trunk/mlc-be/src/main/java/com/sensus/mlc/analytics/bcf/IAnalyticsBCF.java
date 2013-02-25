package com.sensus.mlc.analytics.bcf;

import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;

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
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the inquiry analytics response
	 */
	InquiryAnalyticsResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest);

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
	 * @param lightSelectionRequest the light selection request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest);
}
