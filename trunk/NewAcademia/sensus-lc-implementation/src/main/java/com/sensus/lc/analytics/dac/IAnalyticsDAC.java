package com.sensus.lc.analytics.dac;

import java.util.Map;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.lc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.lc.analytics.model.AnalyticsGroupColumns;
import com.sensus.lc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.lc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.lc.analytics.model.AnalyticsGroupWarning;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Interface IAnalyticsDAC.
 * 
 * @author - QAT Brazil.
 * 
 */
public interface IAnalyticsDAC
{
	/**
	 * Fetch the last 5 lightss alarms (lamp failure or powe failure).
	 * 
	 * @param analyticsRequest the request
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
	 * @param carbonCreditsFactor the factor to calculate carbon credits
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResume(
			AnalyticsRequest analyticsRequest, Double carbonCreditsFactor);

	/**
	 * Fetch all lights alarms by date.
	 * 
	 * @param analyticsRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsAlarmsByDate(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch all lights warnings by date.
	 * 
	 * @param analyticsRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsWarningsByDate(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch all lights installed by date.
	 * 
	 * @param analyticsRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsInstalledByDate(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch all lights consumption by date.
	 * 
	 * @param analyticsRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsConsumptionByDate(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch all analytics energy savings by date.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsEcoModeByDate(
			AnalyticsRequest analyticsRequest);

	/**
	 * Fetch all lights carbon credits by date.
	 * 
	 * @param analyticsRequest the request
	 * @param carbonCreditsFactor the carbon credits factor
	 * @param barrelsOfOilFactor the barrels of oil factor
	 * @param metricOfCOFactor the metric of co factor
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsCarbonCreditsByDate(
			AnalyticsRequest analyticsRequest, Double carbonCreditsFactor, Double barrelsOfOilFactor,
			Double metricOfCOFactor);

	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupAlarm> fetchAllAnalyticsAlarmsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch all analytics warning by group.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupWarning> fetchAllAnalyticsWarningsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch all analytics installed by group.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupTypeLight> fetchAllAnalyticsInstalledByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch all analytics consumption by group.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupTypeLight> fetchAllAnalyticsConsumptionByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch all analytics energy savings by group.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupEcoMode> fetchAllAnalyticsEcoModeGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch all analytics carbon credits by group.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @param carbonCreditsFactor the carbon credits factor
	 * @param barrelsOfOilFactor the barrels of oil factor
	 * @param metricOfCOFactor the metric of co factor
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupCarbonCredits> fetchAllAnalyticsCarbonCreditsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest, Double carbonCreditsFactor, Double barrelsOfOilFactor,
			Double metricOfCOFactor);

	/**
	 * Fetch the 6 total values in dashboard header.
	 * 
	 * @param analyticsRequest the request
	 * @param carbonCreditsFactor the carbon credits factor
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroupColumns> fetchDashboarHeader(AnalyticsRequest analyticsRequest,
			Double carbonCreditsFactor);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @return the string
	 */
	InternalResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest);

	/**
	 * Fetch all analytics group.
	 * 
	 * @param analyticsRequest the analytics request
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest);

	/**
	 * Calculate dashboard resume data.
	 * 
	 * @param carbonCreditsFactor the carbon credits factor
	 * @param tenant the tenant
	 * @param analyticsGroup the analytics group
	 * @return the internal results response
	 */
	InternalResultsResponse<String> calculateDashboardResume(Double carbonCreditsFactor, Tenant tenant,
			AnalyticsGroup analyticsGroup);

	/**
	 * Fetch all groups by tenant for dashboard.
	 * 
	 * @param tenant the tenant
	 * @return the internal results response
	 */
	InternalResultsResponse<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(Tenant tenant);

	/**
	 * Delete dashboar resume by tenant.
	 * 
	 * @param tenant the tenant
	 * @return the internal response
	 */
	InternalResponse deleteDashboarResumeByTenant(Tenant tenant);

	/**
	 * Update analytics alarm warning.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateAnalyticsAlarmWarning(AnalyticsRequest request);

	/**
	 * Insert analytics summarized.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse insertAnalyticsSummarized(AnalyticsRequest request);

}
