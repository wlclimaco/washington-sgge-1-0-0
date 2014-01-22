package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 *  Object model for Notification History Alert.
 *
 *  @author Thiago
 */
@SuppressWarnings("serial")
public class NotificationHistoryAlert extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer notificationHistoryId;

	/** The alert sub type. */
	private AlertSubTypeEnum alertSubType;


	/**
	 * Gets the notification history id.
	 *
	 * @return the notificationHistoryId
	 */
	public Integer getNotificationHistoryId()
	{
		return notificationHistoryId;
	}

	/**
	 * Sets the notification history id.
	 *
	 * @param notificationHistoryId the notificationHistoryId to set
	 */
	public void setNotificationHistoryId(Integer notificationHistoryId)
	{
		this.notificationHistoryId = notificationHistoryId;
	}

	/**
	 * Gets the alert sub type.
	 *
	 * @return the alertSubType
	 */
	public AlertSubTypeEnum getAlertSubType()
	{
		return alertSubType;
	}

	/**
	 * Sets the alert sub type.
	 *
	 * @param alertSubType the alertSubType to set
	 */
	public void setAlertSubType(AlertSubTypeEnum alertSubType)
	{
		this.alertSubType = alertSubType;
	}

	/**
	 * Sets the alert sub type value.
	 *
	 * @param alertSubTypeValue the new alert sub type value
	 */
	public void setAlertSubTypeValue(Integer alertSubTypeValue)
	{
		alertSubType = AlertSubTypeEnum.enumForValue(alertSubTypeValue);
	}

	/**
	 * Gets the alert sub type value.
	 *
	 * @return the alert sub type value
	 */
	public Integer getAlertSubTypeValue()
	{
		if (alertSubType != null)
		{
			return alertSubType.getValue();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "NotificationHistoryAlert [getNotificationHistoryId()=" + getNotificationHistoryId()
				+ ", getAlertSubType()=" + getAlertSubType() + ", getAlertSubTypeValue()=" + getAlertSubTypeValue()
				+ ", toString()=" + super.toString() + "]";
	}

}
