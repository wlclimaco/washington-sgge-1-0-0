package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.Country;
import com.qat.framework.model.QATModelOL;

/**
 * Class represents an association between a {@link Country} and a usage of that Country, {@link CountryUsage}.
 */
@SuppressWarnings("serial")
public class CountryUsage extends QATModelOL
{
	/** The id of the country usage. */
	private Integer id;

	/** The id of the person associated with this country usage. */
	private Integer personId;

	/** The country. */
	private Country country;

	/** The usage of that country. */
	private CountryUsageEnum usage;

	/**
	 * Default Constructor.
	 */
	public CountryUsage()
	{
		super();
	}

	/**
	 * Get the country.
	 *
	 * @return The country.
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Get the usage of that country.
	 *
	 * @return The country usage.
	 */
	public CountryUsageEnum getUsage()
	{
		return usage;
	}

	/**
	 * Get the usage value of that country.
	 *
	 * @return The country usage value.
	 */
	public Integer getUsageValue()
	{
		if (getUsage() == null)
		{
			return null;
		}

		return getUsage().getValue();
	}

	/**
	 * Get the id.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Get the person id associated to country usage.
	 *
	 * @return The person id associated to country usage.
	 */
	public Integer getPersonId()
	{
		return personId;
	}

	/**
	 * Set the country.
	 *
	 * @param country The country to set.
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Set the usage of that country.
	 *
	 * @param usage The country usage to set.
	 */
	public void setUsage(CountryUsageEnum usage)
	{
		this.usage = usage;
	}

	/**
	 * Set the usage value of that country.
	 *
	 * @param usageValue The enum value for the country usage to set.
	 */
	public void setUsageValue(Integer usageValue)
	{
		usage = CountryUsageEnum.enumForValue(usageValue);
	}

	/**
	 * Set the id.
	 *
	 * @param id The id.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Set the person id associated to this country usage.
	 *
	 * @param personId The personId.
	 */
	public void setPersonId(Integer personId)
	{
		this.personId = personId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CountryUsage [getCountry()=" + getCountry() + ", getUsage()=" + getUsage() + ", getUsageValue()="
				+ getUsageValue() + ", getId()=" + getId() + ", getPersonId()=" + getPersonId() + ", toString()="
				+ super.toString() + "]";
	}
}