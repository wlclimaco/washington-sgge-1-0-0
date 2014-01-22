package com.sensus.lc.light.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class NotificationHistory.
 * 
 * @author - Gustavo Peres - QAT Brazil
 */
@SuppressWarnings("serial")
public class NotificationHistory extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The light id. */
	private Integer lightId;

	/** The alert classifications. */
	private List<AlertClassification> alertClassifications = new ArrayList<AlertClassification>();

	/** The last message date. */
	private Date lastMessageDate;

	/** The message date. */
	private Date messageDate;

	/** The update date. */
	private Date updateDate;

	/** The trasaction id. */
	private String trasactionId;

	/** The life cycle state. */
	private LifeCycleStateEnum lifeCycleState;

	/** The notification type. */
	private NotificationTypeEnum notificationType;

	private Boolean simpleNotification;

	/** The precedence enum. */
	private PrecedenceEnum precedence;

	/** The operational data values. */
	private List<OperationalDataValue> operationalDataValues = new ArrayList<OperationalDataValue>();

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the simpleNotification
	 */
	public Boolean getSimpleNotification()
	{
		return simpleNotification;
	}

	/**
	 * @param simpleNotification the simpleNotification to set
	 */
	public void setSimpleNotification(Boolean simpleNotification)
	{
		this.simpleNotification = simpleNotification;
	}

	/**
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the update date.
	 * 
	 * @return the updateDate
	 */
	public Date getUpdateDate()
	{
		return updateDate;
	}

	/**
	 * Sets the update date.
	 * 
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	/**
	 * Gets the last message date.
	 * 
	 * @return the last message date
	 */
	public Date getLastMessageDate()
	{
		return lastMessageDate;
	}

	/**
	 * Sets the last message date.
	 * 
	 * @param lastMessageDate the new last message date
	 */
	public void setLastMessageDate(Date lastMessageDate)
	{
		this.lastMessageDate = lastMessageDate;
	}

	/**
	 * Gets the message date.
	 * 
	 * @return the message date
	 */
	public Date getMessageDate()
	{
		return messageDate;
	}

	/**
	 * Sets the message date.
	 * 
	 * @param messageDate the new message date
	 */
	public void setMessageDate(Date messageDate)
	{
		this.messageDate = messageDate;
	}

	/**
	 * Gets the trasaction id.
	 * 
	 * @return the trasaction id
	 */
	public String getTrasactionId()
	{
		return trasactionId;
	}

	/**
	 * Sets the trasaction id.
	 * 
	 * @param trasactionId the new trasaction id
	 */
	public void setTrasactionId(String trasactionId)
	{
		this.trasactionId = trasactionId;
	}

	/**
	 * Gets the alert classifications.
	 * 
	 * @return the alert classifications
	 */
	public List<AlertClassification> getAlertClassifications()
	{
		return alertClassifications;
	}

	/**
	 * @param alertClassifications the alertClassifications to set
	 */
	public void setAlertClassifications(List<AlertClassification> alertClassifications)
	{
		this.alertClassifications = alertClassifications;
	}

	/**
	 * Gets the life cycle state.
	 * 
	 * @return the life cycle state
	 */
	public LifeCycleStateEnum getLifeCycleState()
	{
		return lifeCycleState;
	}

	/**
	 * Gets the life cycle state value.
	 * 
	 * @return the life cycle state value
	 */
	public Integer getLifeCycleStateValue()
	{
		if (lifeCycleState != null)
		{
			return lifeCycleState.getValue();
		}
		return null;
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
	 * Sets the life cycle state value.
	 * 
	 * @param lifeCycleStateValue the new life cycle state value
	 */
	public void setLifeCycleStateValue(Integer lifeCycleStateValue)
	{
		lifeCycleState = LifeCycleStateEnum.enumForValue(lifeCycleStateValue);
	}

	/**
	 * Sets the life cycle state.
	 * 
	 * @param lifeCycleState the new life cycle state
	 */
	public void setLifeCycleState(LifeCycleStateEnum lifeCycleState)
	{
		this.lifeCycleState = lifeCycleState;
	}

	/**
	 * @return the operationalDataValues
	 */
	public List<OperationalDataValue> getOperationalDataValues()
	{
		return operationalDataValues;
	}

	/**
	 * @param operationalDataValues the operationalDataValues to set
	 */
	public void setOperationalDataValues(List<OperationalDataValue> operationalDataValues)
	{
		this.operationalDataValues = operationalDataValues;
	}

	/**
	 * @return the precedence
	 */
	public PrecedenceEnum getPrecedence()
	{
		return precedence;
	}

	/**
	 * @param precedence the precedence to set
	 */
	public void setPrecedence(PrecedenceEnum precedence)
	{
		this.precedence = precedence;
	}

	/**
	 * @return the precedence
	 */
	public Integer getPrecedenceValue()
	{
		if (precedence != null)
		{
			return precedence.getValue();
		}
		return null;
	}

	/**
	 * @param precedence the precedence to set
	 */
	public void setPrecedenceValue(Integer value)
	{
		this.precedence = PrecedenceEnum.enumForValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "NotificationHistory [getId()=" + getId() + ", getCreateDate()=" + getCreateDate()
				+ ", getLastMessageDate()=" + getLastMessageDate() + ", getMessageDate()=" + getMessageDate()
				+ ", getTrasactionId()=" + getTrasactionId() + ", getAlertClassifications()="
				+ getAlertClassifications() + "]";
	}

}
