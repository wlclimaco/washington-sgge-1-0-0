package com.sensus.lc.communication.model.request;

import com.sensus.common.model.request.Request;

/**
 * The Class AlertCommunicationConfigRequest.
 */
public class AlertCommunicationConfigRequest extends Request
{

	/** The tenant id. */
	private Integer tenantId;

	/**
	 * @return the tenantId
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

}
