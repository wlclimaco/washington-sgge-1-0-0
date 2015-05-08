package com.prosperitasglobal.sendsolv.model;

/**
 * Class represents different price strategies for a Template Product Plan.<br>
 * <p>
 * Template Product Plans AREN'T associated with {@link Location}'s, but rather to {@link ProductPlanTemplateGroup}'s.
 * It is important to note that a template product plan will always have a {@link ProductPlanTypeEnum#TEMPLATE}
 * associated with it.
 */
@SuppressWarnings("serial")
public class TemplateProductPlan extends ProductPlan
{
	/** The id of the associated product plan template group. */
	private Integer productPlanTemplateGroupId;

	/** The template product plan type. This is a ready only attribute. */
	private ProductPlanTypeEnum productPlanType = ProductPlanTypeEnum.TEMPLATE;

	/**
	 * Default Constructor. Initializes the <code>productPlanType</code> to be {@link ProductPlanTypeEnum#TEMPLATE}.
	 */
	public TemplateProductPlan()
	{
		super();
		productPlanType = ProductPlanTypeEnum.TEMPLATE;
	}

	/**
	 * Get the id of the product plan template group.
	 *
	 * @return The product plan template group id.
	 */
	public Integer getProductPlanTemplateGroupId()
	{
		return productPlanTemplateGroupId;
	}

	/**
	 * Set the id of the product plan template group.
	 *
	 * @param productPlanTemplateGroupId The product plan template group id to set.
	 */
	public void setProductPlanTemplateGroupId(Integer productPlanTemplateGroupId)
	{
		this.productPlanTemplateGroupId = productPlanTemplateGroupId;
	}

	/**
	 * This method will always return {@link ProductPlanTypeEnum#TEMPLATE}. This guarantees us that the correct type is
	 * assigned to this class.
	 *
	 * @return The template product plan type.
	 */
	@Override
	public ProductPlanTypeEnum getProductPlanType()
	{
		return productPlanType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TemplateProductPlan [getProductPlanTemplateGroupId()=" + getProductPlanTemplateGroupId()
				+ ", getProductPlanType()=" + getProductPlanType() + ", toString()=" + super.toString() + "]";
	}
}
