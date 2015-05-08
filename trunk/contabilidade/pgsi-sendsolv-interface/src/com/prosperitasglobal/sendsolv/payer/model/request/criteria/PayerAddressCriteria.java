package com.prosperitasglobal.sendsolv.payer.model.request.criteria;

import java.io.Serializable;

import com.prosperitasglobal.sendsolv.payer.model.PayerCity;

/**
 * Criteria member used for doing a lookup on a payer address in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class PayerAddressCriteria implements Serializable
{
	private Integer id;

	private PayerCity city;

	private Integer payerId;

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
	 * @return the city
	 */
	public PayerCity getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(PayerCity city)
	{
		this.city = city;
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
		return "PayerAddressCriteria [getId()=" + getId() + ", getCity()="
				+ getCity() + ", getPayerId()=" + getPayerId() + "]";
	}
}
