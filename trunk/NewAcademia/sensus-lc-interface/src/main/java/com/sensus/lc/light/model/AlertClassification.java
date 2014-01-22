package com.sensus.lc.light.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * The Class AlertClassification.
 * 
 * @author - Gustavo Peres, Thiago - QAT Brazil
 */
@SuppressWarnings("serial")
public class AlertClassification extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer id;

	private Integer notificationHistoryId;

	/** The type of Alert from Notification: NONE, ALARM, WARNING. */
	private AlertTypeEnum alertType;

	/**
	 * The sub-type of Alert from Notification: LAMP_FAILURE, POWER_FAILURE, BOARD_FAILURE, METROLOGY_ERROR,
	 * METROLOGY_COM_FAILURE, POWER_SURGE_DETECTED, BROWNOUT_DETECTED, COMMUNICATION_FAIL, HIGH_CURRENT, LOW_CURRENT,
	 * REVERSE_ENERGY.
	 */
	private AlertSubTypeEnum alertSubType;

	private Date updateDate;

	private Date messageDate;

	/** User for CSV only */
	private Integer flexNetId;
	private String poleId;

	/**
	 * Instantiates a new alert classification.
	 */
	public AlertClassification()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the poleId
	 */
	public String getPoleId()
	{
		return poleId;
	}

	/**
	 * @param poleId the poleId to set
	 */
	public void setPoleId(String poleId)
	{
		this.poleId = poleId;
	}

	/**
	 * @return the flexNetId
	 */
	public Integer getFlexNetId()
	{
		return flexNetId;
	}

	/**
	 * @param flexNetId the flexNetId to set
	 */
	public void setFlexNetId(Integer flexNetId)
	{
		this.flexNetId = flexNetId;
	}

	/**
	 * Gets the alert type enum.
	 * 
	 * @return the alert type enum
	 */
	public AlertTypeEnum getAlertType()
	{
		return alertType;
	}

	/**
	 * Gets the alert type value.
	 * 
	 * @return the alert type value
	 */
	public Integer getAlertTypeValue()
	{
		if (getAlertType() == null)
		{
			return null;
		}

		return getAlertType().getValue();
	}

	/**
	 * Sets the alert type value.
	 * 
	 * @param alertTypeValue the new alert type value
	 */
	public void setAlertTypeValue(Integer alertTypeValue)
	{
		alertType = AlertTypeEnum.enumForValue(alertTypeValue);
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
		this.alertSubType = alertSubTypeParam;
	}

	/**
	 * @return the notificationHistoryId
	 */
	public Integer getNotificationHistoryId()
	{
		return notificationHistoryId;
	}

	/**
	 * @param notificationHistoryId the notificationHistoryId to set
	 */
	public void setNotificationHistoryId(Integer notificationHistoryId)
	{
		this.notificationHistoryId = notificationHistoryId;
	}

	/**
	 * @param alertType the alertType to set
	 */
	public void setAlertType(AlertTypeEnum alertType)
	{
		this.alertType = alertType;
	}

	/**
	 * @param alertSubType the alertSubType to set
	 */
	public void setAlertSubType(AlertSubTypeEnum alertSubType)
	{
		this.alertSubType = alertSubType;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate()
	{
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	/**
	 * @return the messageDate
	 */
	public Date getMessageDate()
	{
		return messageDate;
	}

	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(Date messageDate)
	{
		this.messageDate = messageDate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "AlertClassification [getAlertType()=" + getAlertType() + ", getAlertSubType()=" + getAlertSubType()
				+ ", toString()=" + super.toString() + "]";
	}
}
