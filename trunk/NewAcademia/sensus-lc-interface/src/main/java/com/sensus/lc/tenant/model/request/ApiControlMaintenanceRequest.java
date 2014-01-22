package com.sensus.lc.tenant.model.request;

import com.sensus.common.model.request.MaintenanceRequest;
import com.sensus.lc.tenant.model.ApiControl;

/**
 * The Class ApiControlMaintenanceRequest.
 */
public class ApiControlMaintenanceRequest extends MaintenanceRequest
{

	/** The api control. */
	private ApiControl apiControl;

	/**
	 * Gets the api control.
	 * 
	 * @return the apiControl
	 */
	public ApiControl getApiControl()
	{
		return apiControl;
	}

	/**
	 * Sets the api control.
	 * 
	 * @param apiControl the apiControl to set
	 */
	public void setApiControl(ApiControl apiControl)
	{
		this.apiControl = apiControl;
	}

}
