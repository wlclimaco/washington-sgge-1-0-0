package com.prosperitasglobal.sendsolv.model.criteria;

/**
 * Criteria member used for doing a lookup on a template product plan in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class TemplateProductPlanCriteria extends ProductPlanCriteria
{
	/** The product plan template group id associated to the template product plan. */
	private Integer productPlanTemplateGroupId;

	/**
	 * Default constructor.
	 */
	public TemplateProductPlanCriteria()
	{
		super();
	}

	/**
	 * Get the product plan template group id.
	 *
	 * @return The product plan template group id to use in the data store matching.
	 */
	public Integer getProductPlanTemplateGroupId()
	{
		return productPlanTemplateGroupId;
	}

	/**
	 * Set the product plan template group id to use in the data store matching.
	 *
	 * @param productPlanTemplateGroupId The product plan template group id to set.
	 */
	public void setProductPlanTemplateGroupId(Integer productPlanTemplateGroupId)
	{
		this.productPlanTemplateGroupId = productPlanTemplateGroupId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TemplateProductPlanCriteria [getProductPlanTemplateGroupId()=" + getProductPlanTemplateGroupId()
				+ ", toString()=" + super.toString() + "]";
	}
}
