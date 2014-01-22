package com.sensus.lc.light.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.light.model.OperationalData;

public class OperationalDataMaintenanceRequest extends MaintenanceRequest
{
	private OperationalData operationalData;

	/**
	 * Instantiates a new operational data maintenance request.
	 */
	public OperationalDataMaintenanceRequest()
	{
	}

	/**
	 * Instantiates a new operational data value maintenance request.
	 * 
	 * @param userContext the user context
	 * @param operationalDataParam the operational data param
	 */
	public OperationalDataMaintenanceRequest(UserContext userContext, OperationalData operationalDataParam)
	{
		setUserContext(userContext);
		setOperationalData(operationalDataParam);
	}

	/**
	 * Gets the operational data.
	 * 
	 * @return the operational data
	 */
	public OperationalData getOperationalData()
	{
		return operationalData;
	}

	/**
	 * Sets the operational data.
	 * 
	 * @param operationalData the new operational data
	 */
	public void setOperationalData(OperationalData operationalData)
	{
		this.operationalData = operationalData;
	}
}
