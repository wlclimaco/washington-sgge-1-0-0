package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModelOL;
import com.qat.framework.util.QATConvertUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * Class represents different price strategies for a Product.<br>
 * <p>
 * It is an abstract class which must be extended to be used. Each extending class must implement the
 * getProductPlanType() method so that the extending class can be correctly identified when saving to a data store.
 */
@SuppressWarnings("serial")
public abstract class ProductPlan extends QATModelOL
{
	/** Indicates a default product plan. */
	private Boolean defaultProductPlan;

	/** The end date of the product plan. */
	private Long effectiveEndDate;

	/** The start date of the product plan. */
	private Long effectiveStartDate;

	/** The id of the product plan. */
	private Integer id;

	/** The name of the product plan. */
	private String name;

	/** Parent product of the plan. */
	private Product parentProduct;

	/** List of plan categories. */
	private List<PlanCategory> planCategoryList;

	/** Status of the product plan. */
	private StatusEnum planStatus;

	/** List of applicable product plans. */
	private List<ProductPlanApplicability> productPlanApplicabilityList;

	/**
	 * Default Constructor.
	 */
	public ProductPlan()
	{
		super();
	}

	/**
	 * Get the default product plan.
	 *
	 * @return The default product plan.
	 */
	public Boolean isDefaultProductPlan()
	{
		if (defaultProductPlan == null)
		{
			return Boolean.FALSE;
		}

		return defaultProductPlan;
	}

	/**
	 * Get the default product plan value.
	 *
	 * @return The default product plan value.
	 */
	public String getDefaultProductPlanValue()
	{
		return QATConvertUtil.fromBoolean(isDefaultProductPlan());
	}

	/**
	 * Set the default product plan.
	 *
	 * @param defaultProductPlan The default product plan to set.
	 */
	public void setDefaultProductPlan(Boolean defaultProductPlan)
	{
		this.defaultProductPlan = defaultProductPlan;
	}

	/**
	 * Set the default product plan from a {@link String} value.
	 *
	 * @param defaultProductPlanValue The default product plan value to set.
	 */
	public void setDefaultProductPlanValue(String defaultProductPlanValue)
	{
		defaultProductPlan = QATConvertUtil.toBoolean(defaultProductPlanValue);
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
	 * Get the id of the product plan.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the product plan.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the name of the product plan.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the product plan.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the parent product of the plan.
	 *
	 * @return The parent product.
	 */
	public Product getParentProduct()
	{
		return parentProduct;
	}

	/**
	 * Set the parent product of the plan.
	 *
	 * @param parentProduct The parent product to set.
	 */
	public void setParentProduct(Product parentProduct)
	{
		this.parentProduct = parentProduct;
	}

	/**
	 * Get the {@link List} of {@link PlanCategory}'s.
	 *
	 * @return The plan category list.
	 */
	public List<PlanCategory> getPlanCategoryList()
	{
		if (ValidationUtil.isNull(planCategoryList))
		{
			setPlanCategoryList(new ArrayList<PlanCategory>());
		}

		return planCategoryList;
	}

	/**
	 * Set the {@link List} of {@link PlanCategory}'s.
	 *
	 * @param planCategoryList The plan category list to set.
	 */
	public void setPlanCategoryList(List<PlanCategory> planCategoryList)
	{
		this.planCategoryList = planCategoryList;
	}

	/**
	 * Get the status of the plan.
	 *
	 * @return The plan status.
	 */
	public StatusEnum getPlanStatus()
	{
		return planStatus;
	}

	/**
	 * Get the status value of the plan.
	 *
	 * @return The plan status value.
	 */
	public Integer getPlanStatusValue()
	{
		if (getPlanStatus() == null)
		{
			return null;
		}

		return getPlanStatus().getValue();
	}

	/**
	 * Set the status of the plan.
	 *
	 * @param planStatus The plan status to set.
	 */
	public void setPlanStatus(StatusEnum planStatus)
	{
		this.planStatus = planStatus;
	}

	/**
	 * Set the status of the plan based on the enumeration value.
	 *
	 * @param planStatusValue The plan status enumeration value to set.
	 */
	public void setPlanStatusValue(Integer planStatusValue)
	{
		planStatus = StatusEnum.enumForValue(planStatusValue);
	}

	/**
	 * Get the {@link List} of {@link ProductPlanApplicability}'s associated with the product plan.
	 *
	 * @return The list of applicable product plans.
	 */
	public List<ProductPlanApplicability> getProductPlanApplicabilityList()
	{
		if (ValidationUtil.isNull(productPlanApplicabilityList))
		{
			setProductPlanApplicabilityList(new ArrayList<ProductPlanApplicability>());
		}

		return productPlanApplicabilityList;
	}

	/**
	 * Set the {@link List} of {@link ProductPlanApplicability}'s associated with the product plan.
	 *
	 * @param productPlanApplicabilityList The product plan applicability list to set.
	 */
	public void setProductPlanApplicabilityList(List<ProductPlanApplicability> productPlanApplicabilityList)
	{
		this.productPlanApplicabilityList = productPlanApplicabilityList;
	}

	/**
	 * Get the type of product plan enumeration. This method must be implemented by any extending class.
	 *
	 * @return The product plan type enumeration.
	 */
	public abstract ProductPlanTypeEnum getProductPlanType();

	/**
	 * Get the type of product plan enumeration value.
	 *
	 * @return The product plan type enumeration value.
	 */
	public Integer getProductPlanTypeValue()
	{
		if (getProductPlanType() == null)
		{
			return null;
		}

		return getProductPlanType().getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlan [isDefaultProductPlan()=" + isDefaultProductPlan() + ", getDefaultProductPlanValue()="
				+ getDefaultProductPlanValue() + ", getEffectiveEndDate()=" + getEffectiveEndDate()
				+ ", getEffectiveStartDate()=" + getEffectiveStartDate() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getParentProduct()=" + getParentProduct() + ", getPlanCategoryList()="
				+ getPlanCategoryList() + ", getPlanStatus()=" + getPlanStatus() + ", getPlanStatusValue()="
				+ getPlanStatusValue() + ", getProductPlanApplicabilityList()=" + getProductPlanApplicabilityList()
				+ ", getProductPlanType()=" + getProductPlanType() + ", getProductPlanTypeValue()="
				+ getProductPlanTypeValue() + ", toString()=" + super.toString() + "]";
	}
}
