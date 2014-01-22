package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.InquiryRequest;
import com.sensus.lc.light.model.criteria.AlertCommunicationCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;

/**
 * Request object for NotificationHistory.
 * 
 * @author - Thiago
 */
public class NotificationHistoryRequest extends InquiryRequest
{

	/**
	 * Attributes.
	 */
	private NotificationHistoryCriteria notificationHistoryCriteria;

	private AlertCommunicationCriteria alertCommunicationCriteria;

	private GroupCriteria groupCriteria;

	/**
	 * @return the alertCommunicationCriteria
	 */
	public AlertCommunicationCriteria getAlertCommunicationCriteria()
	{
		return alertCommunicationCriteria;
	}

	/**
	 * @param alertCommunicationCriteria the alertCommunicationCriteria to set
	 */
	public void setAlertCommunicationCriteria(AlertCommunicationCriteria alertCommunicationCriteria)
	{
		this.alertCommunicationCriteria = alertCommunicationCriteria;
	}

	/**
	 * Gets the notification history criteria.
	 * 
	 * @return the notificationHistoryCriteria
	 */
	public NotificationHistoryCriteria getNotificationHistoryCriteria()
	{
		return notificationHistoryCriteria;
	}

	/**
	 * Sets the notification history criteria.
	 * 
	 * @param notificationHistoryCriteria the notificationHistoryCriteria to set
	 */
	public void setNotificationHistoryCriteria(NotificationHistoryCriteria notificationHistoryCriteria)
	{
		this.notificationHistoryCriteria = notificationHistoryCriteria;
	}

	/**
	 * Gets the group criteria.
	 * 
	 * @return the group criteria
	 */
	public GroupCriteria getGroupCriteria()
	{
		return groupCriteria;
	}

	/**
	 * Sets the group criteria.
	 * 
	 * @param groupCriteria the new group criteria
	 */
	public void setGroupCriteria(GroupCriteria groupCriteria)
	{
		this.groupCriteria = groupCriteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotificationHistoryRequest [getNotificationHistoryCriteria()=" + getNotificationHistoryCriteria()
				+ ", getGroupCriteria()=" + getGroupCriteria() + ", getSortExpression()=" + getSortExpression()
				+ ", toString()=" + super.toString() + "]";
	}
}
