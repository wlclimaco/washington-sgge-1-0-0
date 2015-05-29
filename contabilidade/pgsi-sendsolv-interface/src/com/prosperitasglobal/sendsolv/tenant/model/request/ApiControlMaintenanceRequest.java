package com.prosperitasglobal.sendsolv.tenant.model.request;

import com.prosperitasglobal.sendsolv.tenant.model.ApiControl;
import com.qat.framework.model.request.MaintenanceRequest;

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
