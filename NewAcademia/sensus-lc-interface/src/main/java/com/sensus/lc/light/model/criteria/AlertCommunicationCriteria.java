package com.sensus.lc.light.model.criteria;

import java.util.Date;

/**
 * The Class AlertCommunicationCriteria.
 */
public class AlertCommunicationCriteria
{

	/** The tenant id. */
	private Integer tenantId;

	/** The star date. */
	private Date startDate;

	/**
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
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
