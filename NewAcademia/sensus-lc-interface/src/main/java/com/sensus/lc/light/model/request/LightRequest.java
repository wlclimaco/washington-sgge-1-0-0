package com.sensus.lc.light.model.request;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.AddressCriteria;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.ConfigurationCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.LightCustomSearchCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.criteria.OperationalDataCriteria;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.criteria.ScheduleCriteria;
import com.sensus.lc.light.model.criteria.TagCriteria;

/**
 * Request object for Light.
 * 
 * Used to return 0 to many <b>fully inflated</b> Light objects.<br>
 * Includes support for paging and sorting using properties from super class.<br>
 * Using this object you can filter the results of the query or in effect "select all".<br>
 * 
 * @author - Ed Lorenz and Thiago - QAT
 */
public class LightRequest extends InquiryRequest
{
	/**
	 * Criteria attributes.
	 */
	private LightCriteria lightCriteria;
	private ActionCriteria actionCriteria;
	private GroupCriteria groupCriteria;
	private AlertCriteria alertCriteria;
	private AddressCriteria addressCriteria;
	private ProcessCriteria processCriteria;
	private ScheduleCriteria scheduleCriteria;
	private ConfigurationCriteria configurationCriteria;
	private OperationalDataCriteria operationalDataCriteria;
	private TagCriteria tagCriteria;
	private NotificationHistoryCriteria notificationHistoryCriteria;
	private LightCustomSearchCriteria lightCustomSearchCriteria;

	/**
	 * Default constructor.
	 */
	public LightRequest()
	{
		setPageSize(NumberUtils.INTEGER_ZERO);
	}

	/**
	 * Instantiates a new light request.
	 * 
	 * @param context the context
	 */
	public LightRequest(UserContext context)
	{
		setUserContext(context);
		setPageSize(NumberUtils.INTEGER_ZERO);
	}

	/**
	 * Gets the action criteria.
	 * 
	 * @return the actionCriteria
	 */
	public ActionCriteria getActionCriteria()
	{
		return actionCriteria;
	}

	/**
	 * Sets the action criteria.
	 * 
	 * @param actionCriteria the actionCriteria to set
	 */
	public void setActionCriteria(ActionCriteria actionCriteria)
	{
		this.actionCriteria = actionCriteria;
	}

	/**
	 * Gets the tag criteria.
	 * 
	 * @return the tagCriteria
	 */
	public TagCriteria getTagCriteria()
	{
		return tagCriteria;
	}

	/**
	 * Sets the tag criteria.
	 * 
	 * @param tagCriteria the tagCriteria to set
	 */
	public void setTagCriteria(TagCriteria tagCriteria)
	{
		this.tagCriteria = tagCriteria;
	}

	/**
	 * Gets the operational data criteria.
	 * 
	 * @return the operationalDataCriteria
	 */
	public OperationalDataCriteria getOperationalDataCriteria()
	{
		return operationalDataCriteria;
	}

	/**
	 * Sets the operational data criteria.
	 * 
	 * @param operationalDataCriteria the operationalDataCriteria to set
	 */
	public void setOperationalDataCriteria(OperationalDataCriteria operationalDataCriteria)
	{
		this.operationalDataCriteria = operationalDataCriteria;
	}

	/**
	 * Gets the configuration criteria.
	 * 
	 * @return the configurationCriteria
	 */
	public ConfigurationCriteria getConfigurationCriteria()
	{
		return configurationCriteria;
	}

	/**
	 * Sets the configuration criteria.
	 * 
	 * @param configurationCriteria the configurationCriteria to set
	 */
	public void setConfigurationCriteria(ConfigurationCriteria configurationCriteria)
	{
		this.configurationCriteria = configurationCriteria;
	}

	/**
	 * Gets the light criteria.
	 * 
	 * @return the lightCriteria
	 */
	public LightCriteria getLightCriteria()
	{
		return lightCriteria;
	}

	/**
	 * Sets the light criteria.
	 * 
	 * @param lightCriteria the lightCriteria to set
	 */
	public void setLightCriteria(LightCriteria lightCriteria)
	{
		this.lightCriteria = lightCriteria;
	}

	/**
	 * Gets the group criteria.
	 * 
	 * @return the groupCriteria
	 */
	public GroupCriteria getGroupCriteria()
	{
		return groupCriteria;
	}

	/**
	 * Sets the group criteria.
	 * 
	 * @param groupCriteria the groupCriteria to set
	 */
	public void setGroupCriteria(GroupCriteria groupCriteria)
	{
		this.groupCriteria = groupCriteria;
	}

	/**
	 * Gets the alert criteria.
	 * 
	 * @return the alert criteria
	 */
	public AlertCriteria getAlertCriteria()
	{
		return alertCriteria;
	}

	/**
	 * Sets the alert criteria.
	 * 
	 * @param alertCriteria the new alert criteria
	 */
	public void setAlertCriteria(AlertCriteria alertCriteria)
	{
		this.alertCriteria = alertCriteria;
	}

	/**
	 * Gets the address criteria.
	 * 
	 * @return the addressCriteria
	 */
	public AddressCriteria getAddressCriteria()
	{
		return addressCriteria;
	}

	/**
	 * Sets the address criteria.
	 * 
	 * @param addressCriteria the addressCriteria to set
	 */
	public void setAddressCriteria(AddressCriteria addressCriteria)
	{
		this.addressCriteria = addressCriteria;
	}

	/**
	 * Gets the process criteria.
	 * 
	 * @return the processCriteria
	 */
	public ProcessCriteria getProcessCriteria()
	{
		return processCriteria;
	}

	/**
	 * Sets the process criteria.
	 * 
	 * @param processCriteria the processCriteria to set
	 */
	public void setProcessCriteria(ProcessCriteria processCriteria)
	{
		this.processCriteria = processCriteria;
	}

	/**
	 * Gets the schedule criteria.
	 * 
	 * @return the scheduleCriteria
	 */
	public ScheduleCriteria getScheduleCriteria()
	{
		return scheduleCriteria;
	}

	/**
	 * Sets the schedule criteria.
	 * 
	 * @param scheduleCriteria the scheduleCriteria to set
	 */
	public void setScheduleCriteria(ScheduleCriteria scheduleCriteria)
	{
		this.scheduleCriteria = scheduleCriteria;
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
	 * Gets the light custom search criteria.
	 * 
	 * @return the light custom search criteria
	 */
	public LightCustomSearchCriteria getLightCustomSearchCriteria()
	{
		return lightCustomSearchCriteria;
	}

	/**
	 * Sets the light custom search criteria.
	 * 
	 * @param lightCustomSearchCriteria the new light custom search criteria
	 */
	public void setLightCustomSearchCriteria(LightCustomSearchCriteria lightCustomSearchCriteria)
	{
		this.lightCustomSearchCriteria = lightCustomSearchCriteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightRequest [lightCriteria=" + lightCriteria + ", actionCriteria=" + actionCriteria
				+ ", groupCriteria=" + groupCriteria + ", alertCriteria=" + alertCriteria + ", addressCriteria="
				+ addressCriteria + ", processCriteria=" + processCriteria + ", scheduleCriteria=" + scheduleCriteria
				+ ", configurationCriteria=" + configurationCriteria + ", operationalDataCriteria="
				+ operationalDataCriteria + ", tagCriteria=" + tagCriteria + ", notificationHistoryCriteria="
				+ notificationHistoryCriteria + ", lightCustomSearchCriteria=" + lightCustomSearchCriteria + "]";
	}

}
