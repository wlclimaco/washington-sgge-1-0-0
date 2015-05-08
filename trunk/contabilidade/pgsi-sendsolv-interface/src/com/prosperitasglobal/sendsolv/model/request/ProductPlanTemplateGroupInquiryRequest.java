package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.ProductPlanTemplateGroupCriteria;

/**
 * The product plan template group inquiry request is used to do inquiry lookups on the data store, using the criteria
 * specified in the <code>criteria</code> property.
 */
public class ProductPlanTemplateGroupInquiryRequest extends PagedInquiryRequest
{
	/** The criteria to use in the inquiry request. */
	private ProductPlanTemplateGroupCriteria criteria;

	/**
	 * Get the criteria for the lookup.
	 *
	 * @return The criteria.
	 */
	public ProductPlanTemplateGroupCriteria getCriteria()
	{
		if (null == criteria)
		{
			criteria = new ProductPlanTemplateGroupCriteria();
		}

		return criteria;
	}

	/**
	 * Set the criteria for the lookup.
	 *
	 * @param criteria The criteria to set.
	 */
	public void setCriteria(ProductPlanTemplateGroupCriteria criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroupInquiryRequest [getCriteria()=" + getCriteria() + ", toString()="
				+ super.toString() + "]";
	}
}
