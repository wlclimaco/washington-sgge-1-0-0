package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.AlertClassification;

/**
 * Used for Inserting, Updating and Deleting AlertClassification instances.
 *
 * @author Thiago - QAT
 */
public class AlertClassificationMaintenanceRequest extends MaintenanceRequest
{
	/**
	 * Attributes.
	 */
	private AlertClassification alertClassification;

	/**
	 * Default Constructor.
	 */
	public AlertClassificationMaintenanceRequest()
	{
	}

	/**
	 * Instantiates a new alert classification maintenance request.
	 *
	 * @param alertClassificationParam the alert classification param
	 */
	public AlertClassificationMaintenanceRequest(AlertClassification alertClassificationParam)
	{
		alertClassification = alertClassificationParam;
	}

	/**
	 * @return the alertClassification
	 */
	public AlertClassification getAlertClassification()
	{
		return alertClassification;
	}

	/**
	 * @param alertClassification the alertClassification to set
	 */
	public void setAlertClassification(AlertClassification alertClassification)
	{
		this.alertClassification = alertClassification;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AlertClassificationMaintenance [alertClassification=" + alertClassification + "]";
	}

}
