package com.sensus.lc.communication.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.communication.model.AlertCommunication;

/**
 * The Class AlertCommunicationMaintenanceRequest.
 */
public class AlertCommunicationMaintenanceRequest extends MaintenanceRequest
{

	/** The alert communication. */
	private AlertCommunication alertCommunication;

	/**
	 * Instantiates a new alert communication maintenance request.
	 * 
	 * @param alertCommunication the alert communication
	 */
	public AlertCommunicationMaintenanceRequest(AlertCommunication alertCommunication)
	{
		this.alertCommunication = alertCommunication;
	}

	/**
	 * @return the alertCommunication
	 */
	public AlertCommunication getAlertCommunication()
	{
		return alertCommunication;
	}

	/**
	 * @param alertCommunication the alertCommunication to set
	 */
	public void setAlertCommunication(AlertCommunication alertCommunication)
	{
		this.alertCommunication = alertCommunication;
	}

}
