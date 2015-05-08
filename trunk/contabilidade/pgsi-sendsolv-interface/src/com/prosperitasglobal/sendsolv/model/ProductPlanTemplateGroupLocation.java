package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class exists to associate a location to {@link TemplateProductPlan}'s. Normally, a location isn't associated
 * with a {@link TemplateProductPlan}. However, certain processes in the SendSolv system need to make this association
 * from time to time. An example of this is the process of creating {@link BusinessProductPlan}'s from a
 * {@link TemplateProductPlan}. When this is done, a location is needed for the {@link BusinessProductPlan}. This
 * specific class is not maintained in any data store, and is used mainly for processing.
 */
@SuppressWarnings("serial")
public class ProductPlanTemplateGroupLocation extends QATModel
{
	/** The location id associated with business product plan's. */
	private Integer locationId;

	/** The product plan template group containing a list of template product plans. */
	private ProductPlanTemplateGroup productPlanTemplateGroup;

	/**
	 * Default Constructor.
	 */
	public ProductPlanTemplateGroupLocation()
	{
		super();
	}

	/**
	 * Get the location id associated to business product plan's.
	 *
	 * @return The location id.
	 */
	public Integer getLocationId()
	{
		return locationId;
	}

	/**
	 * Set the location id associated to business product plan's.
	 *
	 * @param locationId The location id associated to business product plan's to set.
	 */
	public void setLocationId(Integer locationId)
	{
		this.locationId = locationId;
	}

	/**
	 * Get the product plan template group containing template product plans.
	 *
	 * @return The product plan template group containing template product plans.
	 */
	public ProductPlanTemplateGroup getProductPlanTemplateGroup()
	{
		return productPlanTemplateGroup;
	}

	/**
	 * Set the product plan template group containing template product plans.
	 *
	 * @param productPlanTemplateGroup The product plan template group containing template product plans to set.
	 */
	public void setProductPlanTemplateGroup(ProductPlanTemplateGroup productPlanTemplateGroup)
	{
		this.productPlanTemplateGroup = productPlanTemplateGroup;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroupLocation [getLocationId()=" + getLocationId()
				+ ", getProductPlanTemplateGroup()=" + getProductPlanTemplateGroup() + ", toString()="
				+ super.toString() + "]";
	}
}
