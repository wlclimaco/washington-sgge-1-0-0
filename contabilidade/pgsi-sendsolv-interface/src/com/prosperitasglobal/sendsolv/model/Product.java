package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModelOL;

/**
 * Class represents the product. This could include such things as a Money Transfer or a Calling Card.
 */
@SuppressWarnings("serial")
public class Product extends QATModelOL
{
	/** The description of the product. */
	private String description;

	/** The id of the product. */
	private Integer id;

	/** The name of the product. */
	private String name;

	/** The status of the product. */
	private StatusEnum productStatus;

	/** The end date of the product plan. */
	private Long effectiveEndDate;

	/** The start date of the product plan. */
	private Long effectiveStartDate;

	/**
	 * Default Constructor.
	 */
	public Product()
	{
		super();
	}

	/**
	 * Get the description of the product.
	 *
	 * @return The description.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Set the description of the product.
	 *
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Get the end date of the product plan. If this date has a time portion, it will be removed. This attribute is only
	 * a date.
	 *
	 * @return The effective end date.
	 */
	public Long getEffectiveEndDate()
	{
		return effectiveEndDate;
	}

	/**
	 * Set the end date of the product plan.
	 *
	 * @param effectiveEndDate The effective end date to set.
	 */
	public void setEffectiveEndDate(Long effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * Get the start date of the product plan. If this date has a time portion, it will be removed. This attribute is
	 * only a date.
	 *
	 * @return the effective start date.
	 */
	public Long getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	/**
	 * Set the start date of the product plan.
	 *
	 * @param effectiveStartDate The effective start date to set.
	 */
	public void setEffectiveStartDate(Long effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Get the id of the product.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the product.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the name of the product.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the product.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the status of the product.
	 *
	 * @return The status.
	 */
	public StatusEnum getProductStatus()
	{
		return productStatus;
	}

	/**
	 * Get the status value of the product.
	 *
	 * @return The status value.
	 */
	public Integer getProductStatusValue()
	{
		if (getProductStatus() == null)
		{
			return null;
		}

		return getProductStatus().getValue();
	}

	/**
	 * Set the status of the product.
	 *
	 * @param productStatus The status to set.
	 */
	public void setProductStatus(StatusEnum productStatus)
	{
		this.productStatus = productStatus;
	}

	/**
	 * Set the status for a value of the product.
	 *
	 * @param productStatusValue The value of the status to set.
	 */
	public void setProductStatusValue(Integer productStatusValue)
	{
		productStatus = StatusEnum.enumForValue(productStatusValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Product [getDescription()=" + getDescription() + ", getEffectiveEndDate()=" + getEffectiveEndDate()
				+ ", getEffectiveStartDate()=" + getEffectiveStartDate() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getProductStatus()=" + getProductStatus() + ", getProductStatusValue()="
				+ getProductStatusValue() + ", toString()=" + super.toString() + "]";
	}
}
