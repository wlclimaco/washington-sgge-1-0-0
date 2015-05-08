package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.qat.framework.model.QATModelOL;

/**
 * Class subdivides a Plan Category into tiers according to transaction amount.
 */
@SuppressWarnings("serial")
public class FeeTier extends QATModelOL
{
	/** The fee value. */
	private BigDecimal feeValue;

	/** The id of the fee tier. */
	private Integer id;

	/** The maximum value for the fee. */
	private BigDecimal maximumValue;

	/** The minimum value for the fee. */
	private BigDecimal minimumValue;

	/** The plan category id associated with this fee tier. */
	private Integer planCategoryId;

	/** Sequence number. */
	private Integer sequenceNumber;

	/**
	 * Default Constructor.
	 */
	public FeeTier()
	{
		super();
	}

	/**
	 * Get the fee value.
	 *
	 * @return The fee value.
	 */
	public BigDecimal getFeeValue()
	{
		return feeValue;
	}

	/**
	 * Set the fee value.
	 *
	 * @param feeValue The fee value to set.
	 */
	public void setFeeValue(BigDecimal feeValue)
	{
		this.feeValue = feeValue;
	}

	/**
	 * Get the id of the tier.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the tier.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the maximum value for the fee.
	 *
	 * @return The maximum value.
	 */
	public BigDecimal getMaximumValue()
	{
		return maximumValue;
	}

	/**
	 * Set the maximum value for the fee.
	 *
	 * @param maximumValue The maximum value to set.
	 */
	public void setMaximumValue(BigDecimal maximumValue)
	{
		this.maximumValue = maximumValue;
	}

	/**
	 * Get the minimum value for the fee.
	 *
	 * @return The minimum value.
	 */
	public BigDecimal getMinimumValue()
	{
		return minimumValue;
	}

	/**
	 * Set the minimum value for the fee.
	 *
	 * @param minimumValue The minimum value to set.
	 */
	public void setMinimumValue(BigDecimal minimumValue)
	{
		this.minimumValue = minimumValue;
	}

	/**
	 * Get the plan category id associated with this fee tier.
	 *
	 * @return The plan category id.
	 */
	public Integer getPlanCategoryId()
	{
		return planCategoryId;
	}

	/**
	 * Set the plan category id associated with this fee tier.
	 *
	 * @param planCategoryId The plan category id to set.
	 */
	public void setPlanCategoryId(Integer planCategoryId)
	{
		this.planCategoryId = planCategoryId;
	}

	/**
	 * Get the sequence number.
	 *
	 * @return The sequenceNumber.
	 */
	public Integer getSequenceNumber()
	{
		return sequenceNumber;
	}

	/**
	 * Set the sequence number.
	 *
	 * @param sequenceNumber The sequence number to set.
	 */
	public void setSequenceNumber(Integer sequenceNumber)
	{
		this.sequenceNumber = sequenceNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FeeTier [getFeeValue()=" + getFeeValue() + ", getId()=" + getId() + ", getMaximumValue()="
				+ getMaximumValue() + ", getMinimumValue()=" + getMinimumValue() + ", getPlanCategoryId()="
				+ getPlanCategoryId() + ", getSequenceNumber()=" + getSequenceNumber() + ", toString()="
				+ super.toString() + "]";
	}
}
