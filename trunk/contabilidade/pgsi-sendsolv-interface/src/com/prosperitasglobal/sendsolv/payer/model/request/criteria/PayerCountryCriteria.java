package com.prosperitasglobal.sendsolv.payer.model.request.criteria;

import java.io.Serializable;

/**
 * Criteria member used for doing a lookup on a payer country in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class PayerCountryCriteria implements Serializable
{
	/** The country code of the country. */
	private String countryCode;

	/** The name of the country. */
	private String name;

	/** The the payer id associated with the country. */
	private Integer payerId;

	/** The no relationship. */
	private Boolean noRelationship;

	/**
	 * Default constructor.
	 */
	public PayerCountryCriteria()
	{
		super();
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
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

	/**
	 * @return the noRelationship
	 */
	public Boolean getNoRelationship()
	{
		return noRelationship;
	}

	/**
	 * @param noRelationship the noRelationship to set
	 */
	public void setNoRelationship(Boolean noRelationship)
	{
		this.noRelationship = noRelationship;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerCountryCriteria [getCountryCode()=" + getCountryCode() + ", getName()=" + getName()
				+ ", getPayerId()=" + getPayerId() + ", getNoRelationship()=" + getNoRelationship() + "]";
	}

}
