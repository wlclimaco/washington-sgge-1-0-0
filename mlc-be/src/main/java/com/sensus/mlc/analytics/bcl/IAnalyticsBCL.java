package com.sensus.mlc.analytics.bcl;

import java.util.List;
import java.util.Map;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Interface IAnalyticsBCL.
 *
 * @author Guilherme Vicentine - QAT Brazil.
 *
 */
/**
 * @author QATEmployee
 * 
 */
public interface IAnalyticsBCL
{

	/**
	 * Fetch all analytics groups.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch analytics alarms.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest);

	/**
	 * Fetch analytics alerts by type.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest);

	/**
	 * Fetch dashboard resume.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResume(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch all analytics by date.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsByDate(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch the 6 total values in dashboard header.
	 * 
	 * @param analyticsRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardHeader(AnalyticsRequest analyticsRequest);

	/**
	 * Parses the analytics group alarm.
	 * 
	 * @param list the list
	 * @return the list
	 */
	List<AnalyticsGroup> parseAnalyticsGroupAlarm(List<AnalyticsGroupAlarm> list);

	/**
	 * Parses the analytics group warning.
	 * 
	 * @param list the list
	 * @return the list
	 */
	List<AnalyticsGroup> parseAnalyticsGroupWarning(List<AnalyticsGroupWarning> list);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the InquiryAnalyticsResponse
	 */
	InquiryAnalyticsResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Parses the analytics group type light.
	 * 
	 * @param list the list
	 * @return the list
	 */
	List<AnalyticsGroup> parseAnalyticsGroupTypeLight(List<AnalyticsGroupTypeLight> list);

	/**
	 * Parses the analytics group carbon credits.
	 * 
	 * @param list the list
	 * @return the list
	 */
	List<AnalyticsGroup> parseAnalyticsGroupCarbonCredits(List<AnalyticsGroupCarbonCredits> list);

	/**
	 * Parses the analytics eco mode.
	 * 
	 * @param list the list
	 * @return the list
	 */
	List<AnalyticsGroup> parseAnalyticsEcoMode(List<AnalyticsGroupEcoMode> list);

	/**
	 * Fetch all analytics group.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest);

	/**
	 * Insert csv process.
	 * 
	 * @param lightSelectionRequest the light selection request
	 * @return the process response
	 */
	ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest);

	/**
	 * Delete dashboar resume by tenant.
	 * 
	 * @param tenant the tenant
	 * @return the internal response
	 */
	InternalResponse deleteDashboardResumeByTenant(Tenant tenant);

	/**
	 * Calculate dashboard resume.
	 * 
	 * @param tenant the tenant
	 * @param analyticsGroup the analytics group
	 * @return the internal results response
	 */
	InternalResultsResponse<String> calculateDashboardResume(Tenant tenant, AnalyticsGroup analyticsGroup);

	/**
	 * Fetch all groups by tenant for dashboard.
	 * 
	 * @param tenant the tenant
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(Tenant tenant);

}
