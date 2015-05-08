package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * Class that breaks a Product Plan into different options for the user. A Plan Category also defines the amount of Call
 * Credits.
 */
@SuppressWarnings("serial")
public class PlanCategory extends QATModelOL
{
	/** The id of the plan category. */
	private Integer id;

	/** Call credit allowance. */
	private BigDecimal callCreditAllowance;

	/** The tiers for the fees. */
	private List<FeeTier> feeTierList;

	/** Name of the plan category. */
	private String name;

	/** The product plan id associated to this Plan Category. */
	private Integer productPlanId;

	/**
	 * Default Constructor.
	 */
	public PlanCategory()
	{
		super();
	}

	/**
	 * Get the call credit allowance.
	 *
	 * @return The call credit allowance.
	 */
	public BigDecimal getCallCreditAllowance()
	{
		return callCreditAllowance;
	}

	/**
	 * Set the call credit allowance.
	 *
	 * @param callCreditAllowance The call credit allowance to set.
	 */
	public void setCallCreditAllowance(BigDecimal callCreditAllowance)
	{
		this.callCreditAllowance = callCreditAllowance;
	}

	/**
	 * Get the tiers for the fees.
	 *
	 * @return The fee tier list
	 */
	public List<FeeTier> getFeeTierList()
	{
		if (ValidationUtil.isNull(feeTierList))
		{
			setFeeTierList(new ArrayList<FeeTier>());
		}

		return feeTierList;
	}

	/**
	 * Set the tiers for the fees.
	 *
	 * @param feeTierList The fee tier list to set.
	 */
	public void setFeeTierList(List<FeeTier> feeTierList)
	{
		this.feeTierList = feeTierList;
	}

	/**
	 * Get the id of the plan category.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the plan category.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the name of the plan category.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the plan category.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the product plan id.
	 *
	 * @return The product plan id.
	 */
	public Integer getProductPlanId()
	{
		return productPlanId;
	}

	/**
	 * Set the product plan id.
	 *
	 * @param productPlanId The product plan id to set.
	 */
	public void setProductPlanId(Integer productPlanId)
	{
		this.productPlanId = productPlanId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PlanCategory [getCallCreditAllowance()=" + getCallCreditAllowance() + ", getFeeTierList()="
				+ getFeeTierList() + ", getId()=" + getId() + ", getName()=" + getName() + ", getProductPlanId()="
				+ getProductPlanId() + ", toString()=" + super.toString() + "]";
	}
}
