package com.prosperitasglobal.sendsolv.tenant.model;

import java.util.Date;

import com.prosperitasglobal.sendsolv.model.ModelCosmeDamiao;

/**
 * The Class ApiControl.
 */
@SuppressWarnings("serial")
public class ApiControl extends ModelCosmeDamiao
{

	/** The tenant id. */
	private Integer tenantId;

	/** The count. */
	private Integer count;

	/** The first date. */
	private Date firstDate;

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenantId
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Integer getCount()
	{
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the count to set
	 */
	public void setCount(Integer count)
	{
		this.count = count;
	}

	/**
	 * Gets the first date.
	 *
	 * @return the firstDate
	 */
	public Date getFirstDate()
	{
		return firstDate;
	}

	/**
	 * Sets the first date.
	 *
	 * @param firstDate the firstDate to set
	 */
	public void setFirstDate(Date firstDate)
	{
		this.firstDate = firstDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ApiControl [getTenantId()=" + getTenantId() + ", getCount()=" + getCount() + ", getFirstDate()="
				+ getFirstDate() + "]";
	}

}
