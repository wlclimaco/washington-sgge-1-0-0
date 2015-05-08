package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.sendsolv.model.AutomatedClearingHouse;

/**
 * Criteria member used for doing a lookup on a payer in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class PayerCriteria implements Serializable
{
	/** The automated clearing house associated with payers. */
	private AutomatedClearingHouse automatedClearingHouse;

	/** The country of the payer. */
	private Country country;

	/** The id of the payer. */
	private Integer id;

	/** The name of the payer. */
	private String name;

	/** The ach payee code. */
	private String achPayeeCode;

	/**
	 * Default constructor.
	 */
	public PayerCriteria()
	{
		super();
	}

	/**
	 * Get the automated clearing house.
	 *
	 * @return The automated clearing house to use in the data store matching.
	 */
	public AutomatedClearingHouse getAutomatedClearingHouse()
	{
		return automatedClearingHouse;
	}

	/**
	 * Set the automated clearing house in the data store matching.
	 *
	 * @param automatedClearingHouse The automated clearing house to set.
	 */
	public void setAutomatedClearingHouse(AutomatedClearingHouse automatedClearingHouse)
	{
		this.automatedClearingHouse = automatedClearingHouse;
	}

	/**
	 * Get the country.
	 *
	 * @return The country to use in the data store matching.
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Set the country to use in the data store matching.
	 *
	 * @param country The country to set.
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Get the id.
	 *
	 * @return The id to use in the data store matching.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id to use in the data store matching.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the name.
	 *
	 * @return The name to use in the data store matching.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name to use in the data store matching.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the achPayeeCode
	 */
	public String getAchPayeeCode()
	{
		return achPayeeCode;
	}

	/**
	 * @param achPayeeCode the achPayeeCode to set
	 */
	public void setAchPayeeCode(String achPayeeCode)
	{
		this.achPayeeCode = achPayeeCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerCriteria [getAutomatedClearingHouse()=" + getAutomatedClearingHouse() + ", getCountry()="
				+ getCountry() + ", getId()=" + getId() + ", getName()=" + getName() + ", getAchPayeeCode()="
				+ getAchPayeeCode() + "]";
	}

}
