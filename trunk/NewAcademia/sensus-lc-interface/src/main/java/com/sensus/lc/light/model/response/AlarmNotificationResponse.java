package com.sensus.lc.light.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.lc.light.model.NotificationHistory;

/**
 * Response object for AlarmNotification.
 * 
 * @author - Thiago - QAT
 */
public class AlarmNotificationResponse extends Response
{

	/** The notification histories. */
	private List<NotificationHistory> notificationHistories;

	/**
	 * @return the notificationHistories
	 */
	public List<NotificationHistory> getNotificationHistories()
	{
		return notificationHistories;
	}

	/**
	 * @param notificationHistories the notificationHistories to set
	 */
	public void setNotificationHistories(List<NotificationHistory> notificationHistories)
	{
		this.notificationHistories = notificationHistories;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AlarmNotificationResponse [notificationHistories=" + notificationHistories + "]";
	}

}