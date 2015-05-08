package com.prosperitasglobal.sendsolv.model.criteria;

/**
 * Criteria member used for doing a lookup on a business product plan in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class BusinessProductPlanCriteria extends ProductPlanCriteria
{
	/** The location id of the business product plan. */
	private Integer locationId;

	/**
	 * Default constructor.
	 */
	public BusinessProductPlanCriteria()
	{
		super();
	}

	/**
	 * Get the location id.
	 *
	 * @return The location id to use in the data store matching.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Set the location id to use in the data store matching.
	 *
	 * @param locationId The location id to set.
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BusinessProductPlanCriteria [getLocationId()=" + getLocationId() + ", toString()=" + super.toString()
				+ "]";
	}
}
