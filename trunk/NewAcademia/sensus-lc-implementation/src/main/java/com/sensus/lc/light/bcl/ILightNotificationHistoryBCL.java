package com.sensus.lc.light.bcl;

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
 * Interface for Notification History business component.
 * 
 * @author Thiago - QAT
 */
public interface ILightNotificationHistoryBCL
{

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
	 * Fetch notification history by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> fetchNotificationHistoryByRequest(NotificationHistoryRequest request);

	/**
	 * Fetch notification history alert by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<AlertClassification> fetchNotificationHistoryAlertById(
			NotificationHistoryRequest request);

	/**
	 * Fetch notification history by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> fetchNotificationHistoryById(NotificationHistoryRequest request);

	/**
	 * Insert notification history.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<NotificationHistory> insertNotificationHistory(
			NotificationHistoryMaintenanceRequest request);

	/**
	 * Update notification history.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse updateNotificationHistory(NotificationHistoryMaintenanceRequest request);

	/**
	 * Insert notification history alert classification.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<AlertClassification> insertNotificationHistoryAlertClassification(
			AlertClassificationMaintenanceRequest request);

}
