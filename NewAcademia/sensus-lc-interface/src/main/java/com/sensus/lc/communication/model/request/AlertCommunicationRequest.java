package com.sensus.lc.communication.model.request;

import com.sensus.common.model.request.Request;

/**
 * The Class AlertCommunicationRequest.
 */
public class AlertCommunicationRequest extends Request
{

	/** The tenant id. */
	private Integer tenantId;

	/**
	 * Instantiates a new alert communication request.
	 * 
	 * @param tenantId the tenant id
	 */
	public AlertCommunicationRequest(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

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
