package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LightHistory.
 */
@SuppressWarnings("serial")
public class LightHistory extends SensusModel
{
	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The process id. */
	private Integer processId;

	/** The notification history id. */
	private Integer notificationHistoryId;

	/** The status. */
	private boolean statusComplete;

	/** The light count. */
	private Integer lightCount;

	/** The parameter value. */
	private String parameterValue;

	/** The alert sub type. */
	private AlertSubTypeEnum alertSubType;

	private String Status;

	/**
	 * Instantiates a new light history.
	 */
	public LightHistory()
	{
	}

	/**
	 * Instantiates a new light history.
	 *
	 * @param nameValue the name value
	 * @param descriptionValue the description value
	 * @param processIdValue the process id value
	 */
	public LightHistory(String nameValue, String descriptionValue, Integer processIdValue)
	{
		name = nameValue;
		description = descriptionValue;
		processId = processIdValue;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the process id.
	 *
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 *
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Checks if is status.
	 *
	 * @return true, if is status
	 */
	public boolean isStatusComplete()
	{
		return statusComplete;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatusComplete(boolean status)
	{
		statusComplete = status;
	}

	/**
	 * Gets the notification history id.
	 *
	 * @return the notification history id
	 */
	public Integer getNotificationHistoryId()
	{
		return notificationHistoryId;
	}

	/**
	 * Sets the notification history id.
	 *
	 * @param notificationHistoryId the new notification history id
	 */
	public void setNotificationHistoryId(Integer notificationHistoryId)
	{
		this.notificationHistoryId = notificationHistoryId;
	}

	/**
	 * Gets the light count.
	 *
	 * @return the light count
	 */
	public Integer getLightCount()
	{
		return lightCount;
	}

	/**
	 * Sets the light count.
	 *
	 * @param lightCount the new light count
	 */
	public void setLightCount(Integer lightCount)
	{
		this.lightCount = lightCount;
	}

	/**
	 * Gets the parameter value.
	 *
	 * @return the parameter value
	 */
	public String getParameterValue()
	{
		return parameterValue;
	}

	/**
	 * Sets the parameter value.
	 *
	 * @param parameterValue the new parameter value
	 */
	public void setParameterValue(String parameterValue)
	{
		this.parameterValue = parameterValue;
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

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus()
	{
		return Status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status)
	{
		Status = status;
	}

	/**
	 * Sets the alert sub type.
	 *
	 * @param alertSubType the new alert sub type
	 */
	public void setAlertSubType(AlertSubTypeEnum alertSubType)
	{
		this.alertSubType = alertSubType;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LightHistory [getName()=" + getName() + ", getDescription()=" + getDescription() + ", getProcessId()="
				+ getProcessId() + ", isStatusComplete()=" + isStatusComplete() + ", getNotificationHistoryId()="
				+ getNotificationHistoryId() + ", getLightCount()=" + getLightCount() + ", getParameterValue()="
				+ getParameterValue() + ", getAlertSubType()=" + getAlertSubType() + ", getAlertSubTypeValue()="
				+ getAlertSubTypeValue() + ", getStatus()=" + getStatus() + ", toString()=" + super.toString() + "]";
	}
}
