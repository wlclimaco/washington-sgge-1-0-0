package com.sensus.lc.light.model.request;

/**
 * Request object for Light.
 *
 * Used to return 0 to many <b>fully inflated</b> Light objects.<br>
 * Includes support for paging and sorting using properties from super class.<br>
 * Using this object you can filter the results of the query or in effect "select all".<br>
 *
 * @author - Ed Lorenz and Thiago - QAT
 */
/**
 * @author QATEmployee
 * 
 */
public class LightHistoryCSVRequest extends CSVRequest
{

	/** The notification history request. */
	private NotificationHistoryRequest notificationHistoryRequest;

	/**
	 * Instantiates a new light history csv request.
	 */
	public LightHistoryCSVRequest()
	{
	}

	/**
	 * Instantiates a new light history csv request.
	 * 
	 * @param notificationHistoryRequest the notification history request
	 */
	public LightHistoryCSVRequest(NotificationHistoryRequest notificationHistoryRequest)
	{
		setNotificationHistoryRequest(notificationHistoryRequest);
	}

	/**
	 * Gets the notification history request.
	 * 
	 * @return the notificationHistoryRequest
	 */
	public NotificationHistoryRequest getNotificationHistoryRequest()
	{
		return notificationHistoryRequest;
	}

	/**
	 * Sets the notification history request.
	 * 
	 * @param notificationHistoryRequest the notificationHistoryRequest to set
	 */
	public void setNotificationHistoryRequest(NotificationHistoryRequest notificationHistoryRequest)
	{
		this.notificationHistoryRequest = notificationHistoryRequest;
	}

}