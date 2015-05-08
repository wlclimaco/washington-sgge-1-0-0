package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * Represents an association between a payer and a state province region.
 */
@SuppressWarnings("serial")
public class PayerRegion extends QATModel
{
	/** The SendSolv payer id. */
	private Integer payerId;

	/** The state province region id associated with the payer. */
	private String stateProvinceRegionId;

	/**
	 * Get the SendSolv payer id.
	 *
	 * @return The payer id.
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * Set the SendSolv payer id.
	 *
	 * @param payerId The payer id to set.
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * Get the state province region id.
	 *
	 * @return The state province region id.
	 */
	public String getStateProvinceRegionId()
	{
		return stateProvinceRegionId;
	}

	/**
	 * Set the state province region id.
	 *
	 * @param stateProvinceRegionId The state province region id to set.
	 */
	public void setStateProvinceRegionId(String stateProvinceRegionId)
	{
		this.stateProvinceRegionId = stateProvinceRegionId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerRegion [getPayerId()=" + getPayerId() + ", getStateProvinceRegionId()="
				+ getStateProvinceRegionId() + ", toString()=" + super.toString() + "]";
	}
}
