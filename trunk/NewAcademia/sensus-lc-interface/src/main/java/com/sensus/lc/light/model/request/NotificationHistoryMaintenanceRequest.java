package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.NotificationHistory;

/**
 * Used for Inserting, Updating and Deleting NotificationHistory instances.
 *
 * @author Thiago - QAT
 */
public class NotificationHistoryMaintenanceRequest extends MaintenanceRequest
{

	/**
	 * Maintenance object.
	 */
	private NotificationHistory notificationHistory;

	/**
	 * Default Constructor.
	 */
	public NotificationHistoryMaintenanceRequest()
	{

	}

	/**
	 * Instantiates a new notification history maintenance request.
	 *
	 * @param notificationHistoryParam the notification history param
	 */
	public NotificationHistoryMaintenanceRequest(NotificationHistory notificationHistoryParam)
	{
		notificationHistory = notificationHistoryParam;
	}

	/**
	 * @return the notificationHistory
	 */
	public NotificationHistory getNotificationHistory()
	{
		return notificationHistory;
	}

	/**
	 * @param notificationHistory the notificationHistory to set
	 */
	public void setNotificationHistory(NotificationHistory notificationHistory)
	{
		this.notificationHistory = notificationHistory;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotificationHistoryMaintenanceRequest [notificationHistory=" + notificationHistory + "]";
	}

}
