package com.sensus.lc.light.model.criteria;

import java.util.Date;
import java.util.List;

import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.process.model.ProcessFilter;

/**
 * Criteria for Light notification history search.
 * 
 * @author QAT - Global
 */
public class NotificationHistoryCriteria
{

	/**
	 * Attributes.
	 */
	private Integer notificationHistoryId;

	/** The notification type. */
	private NotificationTypeEnum notificationType;

	/** The light id. */
	private Integer lightId;

	/** The tenant id. */
	private Integer tenantId;

	/** The end date. */
	private Date endDate;

	/** The star date. */
	private Date startDate;

	/** The prop name. */
	private String propName;

	/** The action category list. */
	private List<String> actionCategoryList;

	/** The event id. */
	private Integer eventId;

	/** The action id. */
	private Integer actionId;

	/** The search text. */
	private String searchText;

	/** The user ids. */
	private String userIds;

	/** The process filter. */
	private ProcessFilter processFilter;

	/** Flag fetch the latest update date. */
	private Boolean fetchLastest;

	/** Flag to fetch before communication failure. */
	private Boolean fetchNotCommFail = false;

	/** Communication Failure value. */
	private final Integer commFailValue = AlertSubTypeEnum.COMMUNICATION_FAIL.getValue();

	/** The alert sub type. */
	private AlertSubTypeEnum alertSubType;

	/**
	 * Gets the fetch not comm fail.
	 * 
	 * @return the fetchNotCommFail
	 */
	public Boolean getFetchNotCommFail()
	{
		return fetchNotCommFail;
	}

	/**
	 * Sets the fetch not comm fail.
	 * 
	 * @param fetchNotCommFail the fetchNotCommFail to set
	 */
	public void setFetchNotCommFail(Boolean fetchNotCommFail)
	{
		this.fetchNotCommFail = fetchNotCommFail;
	}

	/**
	 * Gets the fetch lastest.
	 * 
	 * @return the fetchLastest
	 */
	public Boolean getFetchLastest()
	{
		return fetchLastest;
	}

	/**
	 * Sets the fetch lastest.
	 * 
	 * @param fetchLastest the fetchLastest to set
	 */
	public void setFetchLastest(Boolean fetchLastest)
	{
		this.fetchLastest = fetchLastest;
	}

	/**
	 * Gets the notification type.
	 * 
	 * @return the notificationType
	 */
	public NotificationTypeEnum getNotificationType()
	{
		return notificationType;
	}

	/**
	 * Gets the notification type value.
	 * 
	 * @return the notification type value
	 */
	public Integer getNotificationTypeValue()
	{
		if (notificationType != null)
		{
			return notificationType.getValue();
		}
		return null;
	}

	/**
	 * Sets the notification type.
	 * 
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(NotificationTypeEnum notificationType)
	{
		this.notificationType = notificationType;
	}

	/**
	 * Sets the notification type value.
	 * 
	 * @param notificationTypeValue the new notification type value
	 */
	public void setNotificationTypeValue(Integer notificationTypeValue)
	{
		notificationType = NotificationTypeEnum.enumForValue(notificationTypeValue);
	}

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
	 * Gets the light id.
	 * 
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the tenant id.
	 * 
	 * @return the tenant id
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 * 
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the star date.
	 * 
	 * @return the star date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the star date.
	 * 
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the prop name.
	 * 
	 * @return the prop name
	 */
	public String getPropName()
	{
		return propName;
	}

	/**
	 * Sets the prop name.
	 * 
	 * @param propName the new prop name
	 */
	public void setPropName(String propName)
	{
		this.propName = propName;
	}

	/**
	 * Gets the action category list.
	 * 
	 * @return the action category list
	 */
	public List<String> getActionCategoryList()
	{
		return actionCategoryList;
	}

	/**
	 * Sets the action category list.
	 * 
	 * @param actionCategoryList the new action category list
	 */
	public void setActionCategoryList(List<String> actionCategoryList)
	{
		this.actionCategoryList = actionCategoryList;
	}

	/**
	 * Gets the event id.
	 * 
	 * @return the event id
	 */
	public Integer getEventId()
	{
		return eventId;
	}

	/**
	 * Sets the event id.
	 * 
	 * @param eventId the new event id
	 */
	public void setEventId(Integer eventId)
	{
		this.eventId = eventId;
	}

	/**
	 * Gets the action id.
	 * 
	 * @return the action id
	 */
	public Integer getActionId()
	{
		return actionId;
	}

	/**
	 * Sets the action id.
	 * 
	 * @param actionId the new action id
	 */
	public void setActionId(Integer actionId)
	{
		this.actionId = actionId;
	}

	/**
	 * Gets the search text.
	 * 
	 * @return the search text
	 */
	public String getSearchText()
	{
		return searchText;
	}

	/**
	 * Sets the search text.
	 * 
	 * @param searchText the new search text
	 */
	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}

	/**
	 * Gets the user ids.
	 * 
	 * @return the user ids
	 */
	public String getUserIds()
	{
		return userIds;
	}

	/**
	 * Sets the user ids.
	 * 
	 * @param userIds the new user ids
	 */
	public void setUserIds(String userIds)
	{
		this.userIds = userIds;
	}

	/**
	 * Gets the process filter.
	 * 
	 * @return the process filter
	 */
	public ProcessFilter getProcessFilter()
	{
		return processFilter;
	}

	/**
	 * Sets the process filter.
	 * 
	 * @param processFilter the new process filter
	 */
	public void setProcessFilter(ProcessFilter processFilter)
	{
		this.processFilter = processFilter;
	}

	/**
	 * Gets the comm fail value.
	 * 
	 * @return the commFailValue
	 */
	public Integer getCommFailValue()
	{
		return commFailValue;
	}

	/**
	 * Gets the alert sub type enum.
	 * 
	 * @return the alert sub type enum
	 */
	public AlertSubTypeEnum getAlertSubType()
	{
		return alertSubType;
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
	 * Sets the alert sub type enum.
	 * 
	 * @param alertSubType the new alert sub type enum
	 */
	public void setAlertSubTypeEnum(AlertSubTypeEnum alertSubTypeParam)
	{
		alertSubType = alertSubTypeParam;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NotificationHistoryCriteria [getFetchNotCommFail()=" + getFetchNotCommFail() + ", getFetchLastest()="
				+ getFetchLastest() + ", getNotificationType()=" + getNotificationType()
				+ ", getNotificationTypeValue()=" + getNotificationTypeValue() + ", getNotificationHistoryId()="
				+ getNotificationHistoryId() + ", getLightId()=" + getLightId() + ", getTenantId()=" + getTenantId()
				+ ", getEndDate()=" + getEndDate() + ", getStartDate()=" + getStartDate() + ", getPropName()="
				+ getPropName() + ", getActionCategoryList()=" + getActionCategoryList() + ", getEventId()="
				+ getEventId() + ", getActionId()=" + getActionId() + ", getSearchText()=" + getSearchText()
				+ ", getUserIds()=" + getUserIds() + ", getProcessFilter()=" + getProcessFilter()
				+ ", getCommFailValue()=" + getCommFailValue() + ", toString()=" + super.toString() + "]";
	}

}
