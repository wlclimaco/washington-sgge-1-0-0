package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.AlertTypeEnum;

public class AlertCriteria
{
	/**
	 * Attributes.
	 */
	private List<AlertTypeEnum> alertTypeList = new ArrayList<AlertTypeEnum>();
	private List<AlertSubTypeEnum> alertSubtypeList = new ArrayList<AlertSubTypeEnum>();
	private Boolean notAlert;

	/**
	 * Gets the alert type list.
	 * 
	 * @return the alert type list
	 */
	public List<AlertTypeEnum> getAlertTypeList()
	{
		return alertTypeList;
	}

	/**
	 * Sets the alert type list.
	 * 
	 * @param alertTypeList the new alert type list
	 */
	public void setAlertTypeList(List<AlertTypeEnum> alertTypeList)
	{
		this.alertTypeList = alertTypeList;
	}

	/**
	 * Gets the alert subtype list.
	 * 
	 * @return the alert subtype list
	 */
	public List<AlertSubTypeEnum> getAlertSubtypeList()
	{
		return alertSubtypeList;
	}

	public void setAlertSubtypeList(List<AlertSubTypeEnum> alertSubtypeList)
	{
		this.alertSubtypeList = alertSubtypeList;
	}

	/**
	 * Checks if is not alert.
	 * 
	 * @return the boolean
	 */
	public Boolean getNotAlert()
	{
		return notAlert;
	}

	/**
	 * Sets the not alert.
	 * 
	 * @param notAlert the new not alert
	 */
	public void setNotAlert(Boolean notAlert)
	{
		this.notAlert = notAlert;
	}

	/**
	 * Checks for notification history filter.
	 * 
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return !isNullOrEmpty(getAlertTypeList()) ||
				!isNullOrEmpty(getAlertSubtypeList()) ||
				(!isNull(getNotAlert()) && getNotAlert());
	}

}
