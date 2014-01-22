/*
 *
 */
package com.sensus.lc.light.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;

/**
 * The Interface INotificationHistoryDAC.
 *
 * @author Thiago
 */
public interface INotificationHistoryDAC
{

	/**
	 * Fetch notification history by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> fetchNotificationHistoryByRequest(NotificationHistoryRequest request);

	/**
	 * Fetch notification history by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> fetchNotificationHistoryById(NotificationHistoryRequest request);

	/**
	 * Fetch light notification history.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<LightHistory> fetchLightNotificationHistory(NotificationHistoryRequest request);

	/**
	 * Fetch light history header.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(NotificationHistoryRequest request);

	/**
	 * Insert notification history.
	 *
	 * @param notificationHistoryMaintenanceRequest the notification history maintenance request
	 * @return the internal response
	 */
	InternalResultsResponse<NotificationHistory> insertNotificationHistory(
			NotificationHistoryMaintenanceRequest notificationHistoryMaintenanceRequest);

	/**
	 * Insert alert classification.
	 *
	 * @param alertClassificationMaintenance the alert classification maintenance
	 * @return the internal results response
	 */
	InternalResultsResponse<AlertClassification> insertAlertClassification(
			AlertClassificationMaintenanceRequest alertClassificationMaintenance);

	/**
	 * Update notification history.
	 *
	 * @param notificationHistoryMaintenanceRequest the notification history maintenance request
	 * @return the internal response
	 */
	InternalResponse updateNotificationHistory(
			NotificationHistoryMaintenanceRequest notificationHistoryMaintenanceRequest);

	/**
	 * Update alert classification.
	 *
	 * @param alertClassificationMaintenanceRequest the alert classification maintenance request
	 * @return the internal response
	 */
	InternalResponse updateAlertClassification(
			AlertClassificationMaintenanceRequest alertClassificationMaintenanceRequest);

	/**
	 * Fetch notification history alert by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AlertClassification> fetchNotificationHistoryAlertById(NotificationHistoryRequest request);
}
