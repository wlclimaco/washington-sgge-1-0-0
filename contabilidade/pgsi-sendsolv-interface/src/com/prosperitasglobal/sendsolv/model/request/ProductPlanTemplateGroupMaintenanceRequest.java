package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * The Class ProductPlanTemplateGroupRequest.
 */
public class ProductPlanTemplateGroupMaintenanceRequest extends MaintenanceRequest
{
	/** The product plan template group. */
	private ProductPlanTemplateGroup productPlanTemplateGroup;

	/**
	 * Get the product plan template group.
	 *
	 * @return The product plan template group.
	 */
	public ProductPlanTemplateGroup getProductPlanTemplateGroup()
	{
		return productPlanTemplateGroup;
	}

	/**
	 * Set the product plan template group.
	 *
	 * @param productPlanTemplateGroup The product plan template group to set.
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
		return "ProductPlanTemplateGroupMaintenanceRequest [getProductPlanTemplateGroup()="
				+ getProductPlanTemplateGroup() + ", getUserContext()=" + getUserContext() + "]";
	}
}
