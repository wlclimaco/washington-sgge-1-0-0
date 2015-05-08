package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.qat.framework.model.QATModel;

/**
 * This class is a representation of an address for a participant in a Money Transfer Transaction.
 */
@SuppressWarnings("serial")
public class MoneyTransferParticipantAddress extends QATModel
{
	/** The address lines. */
	private String address;

	/** The city. */
	private String city;

	/** The state/province/region. */
	private StateProvinceRegion stateProvinceRegion;

	/** The country. */
	private Country country;

	/** The postal code. */
	private String postalCode;

	/**
	 * Get the address lines.
	 *
	 * @return The address lines.
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Set the address lines.
	 *
	 * @param address The address lines to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Get the city.
	 *
	 * @return The city.
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Set the city.
	 *
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Get the state/province/region.
	 *
	 * @return The state province region.
	 */
	public StateProvinceRegion getStateProvinceRegion()
	{
		return stateProvinceRegion;
	}

	/**
	 * Set the state/province/region.
	 *
	 * @param stateProvinceRegion The state province region to set.
	 */
	public void setStateProvinceRegion(StateProvinceRegion stateProvinceRegion)
	{
		this.stateProvinceRegion = stateProvinceRegion;
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
	 * Set the country.
	 *
	 * @param country The country to set.
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Get the postal code.
	 *
	 * @return The postalCode.
	 */
	public String getPostalCode()
	{
		return postalCode;
	}

	/**
	 * Set the postal code.
	 *
	 * @param postalCode The postal code to set.
	 */
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferParticipantAddress [getAddress()=" + getAddress() + ", getCity()=" + getCity()
				+ ", getStateProvinceRegion()=" + getStateProvinceRegion() + ", getCountry()=" + getCountry()
				+ ", getPostalCode()=" + getPostalCode() + ", toString()=" + super.toString() + "]";
	}
}
