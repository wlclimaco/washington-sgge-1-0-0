package com.prosperitasglobal.sendsolv.payer.model.request.criteria;

import java.io.Serializable;

import com.prosperitasglobal.sendsolv.payer.model.PayerAddress;

/**
 * Criteria member used for doing a lookup on a payer branch in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class PayerBranchCriteria implements Serializable
{
	private Integer id;

	private PayerAddress address;

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
	 * @return the address
	 */
	public PayerAddress getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(PayerAddress address)
	{
		this.address = address;
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
		return "PayerBranchCriteria [getId()=" + getId() + ", getAddress()="
				+ getAddress() + ", getPayerId()=" + getPayerId() + "]";
	}
}
