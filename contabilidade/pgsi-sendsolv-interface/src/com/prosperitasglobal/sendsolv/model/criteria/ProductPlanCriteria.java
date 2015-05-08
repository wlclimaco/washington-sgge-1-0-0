package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * Criteria member used for doing a lookup on a product plan in the SendSolv system. This class is abstract, so it must
 * be extended in order to create an instance.
 */
@SuppressWarnings("serial")
public abstract class ProductPlanCriteria implements Serializable
{
	/** The id of the product plan. */
	private Integer id;

	/**
	 * Default constructor.
	 */
	public ProductPlanCriteria()
	{
		super();
	}

	/**
	 * Get the product plan id to use in the inquiry.
	 *
	 * @return The product plan id to use in the inquiry.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the product plan id to use in the inquiry.
	 *
	 * @param id The product plan id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanCriteria [getId()=" + getId() + "]";
	}
}
