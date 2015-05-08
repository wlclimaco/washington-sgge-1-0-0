package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroupLocation;
import com.qat.framework.model.request.Request;

/**
 * This request is used when {@link BusinessProductPlan}'s are to be created from {@link TemplateProductPlan}'s. Specify
 * the {@link ProductPlanTemplateGroup} that contains the {@link TemplateProductPlan} objects to copy, and also specify
 * the location that the new {@link BusinessProductPlan}'s will be associated.
 */
public class ProductPlanTemplateGroupCreateRequest extends Request
{
	/** The product plan template group location. */
	private ProductPlanTemplateGroupLocation productPlanTemplateGroupLocation;

	/**
	 * Get the product plan template group location.
	 *
	 * @return The product plan template group location.
	 */
	public ProductPlanTemplateGroupLocation getProductPlanTemplateGroupLocation()
	{
		return productPlanTemplateGroupLocation;
	}

	/**
	 * Set the product plan template group location.
	 *
	 * @param productPlanTemplateGroupLocation The product plan template group location to set.
	 */
	public void setProductPlanTemplateGroupLocation(ProductPlanTemplateGroupLocation productPlanTemplateGroupLocation)
	{
		this.productPlanTemplateGroupLocation = productPlanTemplateGroupLocation;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroupCreateRequest [getProductPlanTemplateGroupLocation()="
				+ getProductPlanTemplateGroupLocation() + ", getUserContext()=" + getUserContext() + "]";
	}
}
