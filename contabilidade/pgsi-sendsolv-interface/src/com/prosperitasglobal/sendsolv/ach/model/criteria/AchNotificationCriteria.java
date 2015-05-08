package com.prosperitasglobal.sendsolv.ach.model.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.ach.model.AchStatus;
import com.qat.framework.validation.ValidationUtil;

/**
 * The criteria used for notifications at the automated clearing house.
 */
@SuppressWarnings("serial")
public class AchNotificationCriteria implements Serializable
{
	/** The number of notifications to get. */
	private Integer numberOfNotifications;

	/** The list of status. */
	private List<AchStatus> achStatusList;

	/**
	 * Get the number of notifications to get.
	 *
	 * @return The number.
	 */
	public Integer getNumberOfNotifications()
	{
		return numberOfNotifications;
	}

	/**
	 * Set the number of notifications to get.
	 *
	 * @param numberOfNotifications The number to set.
	 */
	public void setNumberOfNotifications(Integer numberOfNotifications)
	{
		this.numberOfNotifications = numberOfNotifications;
	}

	/**
	 * Get the list of status.
	 *
	 * @return The list.
	 */
	public List<AchStatus> getAchStatusList()
	{
		return achStatusList;
	}

	/**
	 * Set the list of status.
	 *
	 * @param achStatusList The list to set.
	 */
	public void setAchStatusList(List<AchStatus> achStatusList)
	{
		this.achStatusList = achStatusList;
	}

	/**
	 * Add an automated clearing house status to the list.
	 *
	 * @param achStatus The status to add.
	 */
	public void addAchStatus(AchStatus achStatus)
	{
		if (ValidationUtil.isNull(getAchStatusList()))
		{
			setAchStatusList(new ArrayList<AchStatus>());
		}

		getAchStatusList().add(achStatus);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AchNotificationCriteria [getNumberOfNotifications()=" + getNumberOfNotifications()
				+ ", getAchStatusList()=" + getAchStatusList() + "]";
	}

}
