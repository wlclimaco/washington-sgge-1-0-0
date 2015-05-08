package com.prosperitasglobal.sendsolv.payer.model.request.criteria;

import java.io.Serializable;

import com.prosperitasglobal.sendsolv.payer.model.PayerStateProvinceRegion;

/**
 * Criteria member used for doing a lookup on a payer city in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class PayerCityCriteria implements Serializable
{
	private Integer id;

	private PayerStateProvinceRegion region;

	private Integer payerId;

	/** Default Constructor */
	public PayerCityCriteria()
	{
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the region
	 */
	public PayerStateProvinceRegion getRegion()
	{
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(PayerStateProvinceRegion region)
	{
		this.region = region;
	}

	/**
	 * @return the payerId
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * @param payerId the payerId to set
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerCityCriteria [getId()=" + getId() + ", getRegion()="
				+ getRegion() + ", getPayerId()=" + getPayerId() + "]";
	}
}
