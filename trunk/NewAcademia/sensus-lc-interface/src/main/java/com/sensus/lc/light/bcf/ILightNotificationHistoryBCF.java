package com.sensus.lc.light.bcf;

import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.response.LightHistoryResponse;
import com.sensus.lc.light.model.response.NotificationHistoryResponse;

/**
 * The Interface ILightNotificationHistoryBCF.
 */
public interface ILightNotificationHistoryBCF
{

	/**
	 * Fetch light notification history.
	 * 
	 * @param request the request
	 * @return the notification history response
	 */
	NotificationHistoryResponse fetchLightNotificationHistoryByRequest(NotificationHistoryRequest request);

	/**
	 * Fetch notification history by id.
	 * 
	 * @param request the request
	 * @return the notification history response
	 */
	NotificationHistoryResponse fetchNotificationHistoryById(NotificationHistoryRequest request);

	/**
	 * Fetch light notification history.
	 * 
	 * @param request the request
	 * @return the notification history response
	 */
	LightHistoryResponse fetchLightNotificationHistory(NotificationHistoryRequest request);

}
