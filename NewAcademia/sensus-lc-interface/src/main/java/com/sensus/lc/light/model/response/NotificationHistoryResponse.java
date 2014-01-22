package com.sensus.lc.light.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.light.model.NotificationHistory;

/**
 * Response object for NotificationHistory.
 * 
 * @author Thiago - QAT
 */
public class NotificationHistoryResponse extends InquiryResponse
{

	/**
	 * Attributes.
	 */
	private List<NotificationHistory> notificationHistories = new ArrayList<NotificationHistory>();

	/**
	 * Adds the results.
	 * 
	 * @param coll the coll
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addResults(@SuppressWarnings("rawtypes") Collection coll)
	{
		getNotificationHistories().addAll(coll);
	}

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
		return "NotificationHistoryResponse [notificationHistories=" + notificationHistories + "]";
	}

}
