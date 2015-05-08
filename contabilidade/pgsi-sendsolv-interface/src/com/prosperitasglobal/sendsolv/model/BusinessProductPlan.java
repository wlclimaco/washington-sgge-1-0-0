package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents different pricing strategies for a Business Product Plan.<br>
 * <p>
 * Business Product Plans are associated with {@link Location}'s. It is important to note that a business product plan
 * will always have a {@link ProductPlanTypeEnum#BUSINESS} associated with it.
 */
@SuppressWarnings("serial")
public class BusinessProductPlan extends ProductPlan
{
	/** The location id associated with this business product plan. */
	private Integer locationId;

	/** The business product plan type. This is a ready only attribute. */
	private ProductPlanTypeEnum productPlanType = ProductPlanTypeEnum.BUSINESS;

	/**
	 * Default Constructor. Initializes the <code>productPlanType</code> to be {@link ProductPlanTypeEnum#BUSINESS}.
	 */
	public BusinessProductPlan()
	{
		super();
		productPlanType = ProductPlanTypeEnum.BUSINESS;
	}

	/**
	 * This method will always return {@link ProductPlanTypeEnum#BUSINESS}. This guarantees us that the correct type is
	 * assigned to this class.
	 *
	 * @return The business product plan type.
	 */
	@Override
	public ProductPlanTypeEnum getProductPlanType()
	{
		return productPlanType;
	}

	/**
	 * @param productPlanType the productPlanType to set
	 */
	public void setProductPlanType(ProductPlanTypeEnum productPlanType)
	{
		this.productPlanType = productPlanType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.prosperitasglobal.sendsolv.model.ProductPlan#getProductPlanTypeValue()
	 */
	@Override
	public Integer getProductPlanTypeValue()
	{
		if (productPlanType == null)
		{
			return null;
		}

		return productPlanType.getValue();
	}

	/**
	 * Sets the product plan type value.
	 *
	 * @param productPlanTypeValue the new product plan type value
	 */
	public void setProductPlanTypeValue(Integer productPlanTypeValue)
	{
		productPlanType = ProductPlanTypeEnum.enumForValue(productPlanTypeValue);
	}

	/**
	 * Get the location id.
	 *
	 * @return The location id.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Set the location id.
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
		return "BusinessProductPlan [getProductPlanType()=" + getProductPlanType() + ", getLocationId()="
				+ getLocationId() + ", toString()=" + super.toString() + "]";
	}
}
