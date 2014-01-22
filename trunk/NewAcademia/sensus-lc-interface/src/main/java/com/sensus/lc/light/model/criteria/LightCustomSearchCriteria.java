package com.sensus.lc.light.model.criteria;

/**
 * The Class LightCustomSearchCriteria.
 */
public class LightCustomSearchCriteria
{

	/** The tenant id. */
	private Integer tenantId;

	/** The user id. */
	private Integer userId;

	/** The order by. */
	private String orderBy;

	/** The custom search name. */
	private String customSearchName;

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy()
	{
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	/**
	 * Gets the custom search name.
	 *
	 * @return the custom search name
	 */
	public String getCustomSearchName()
	{
		return customSearchName;
	}

	/**
	 * Sets the custom search name.
	 *
	 * @param customSearchName the new custom search name
	 */
	public void setCustomSearchName(String customSearchName)
	{
		this.customSearchName = customSearchName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightCustomSearchCriteria [getTenantId()=" + getTenantId() + ", getUserId()=" + getUserId()
				+ ", getOrderBy()=" + getOrderBy() + ", getCustomSearchName()=" + getCustomSearchName()
				+ ", toString()=" + super.toString() + "]";
	}
}
