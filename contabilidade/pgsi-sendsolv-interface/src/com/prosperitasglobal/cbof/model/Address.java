package com.prosperitasglobal.cbof.model;

/**
 * The Class Address.
 */
@SuppressWarnings("serial")
public class Address extends Contact
{

	/** The address line 1. */
	private String addressLine1;

	/** The address line 2. */
	private String addressLine2;

	/** The address line 3. */
	private String addressLine3;

	/** The address line 4. */
	private String addressLine4;

	/** The name of the city. */
	private String cityName;

	/** The state/province/region of the address. */
	private StateProvinceRegion stateProvinceRegion;

	/** The country. */
	private Country country;

	/** The postal code. */
	private String postalCode;

	/** A note about the address. */
	private String note;

	/**
	 * The Constructor.
	 */
	public Address()
	{
	}

	/**
	 * Gets the address line1.
	 *
	 * @return the address line1
	 */
	public String getAddressLine1()
	{
		return addressLine1;
	}

	/**
	 * Sets the address line1.
	 *
	 * @param addressline1 the address line1
	 */
	public void setAddressLine1(String addressline1)
	{
		addressLine1 = addressline1;
	}

	/**
	 * Gets the address line2.
	 *
	 * @return the address line2
	 */
	public String getAddressLine2()
	{
		return addressLine2;
	}

	/**
	 * Sets the address line2.
	 *
	 * @param addressline2 the address line2
	 */
	public void setAddressLine2(String addressline2)
	{
		addressLine2 = addressline2;
	}

	/**
	 * Gets the address line3.
	 *
	 * @return the address line3
	 */
	public String getAddressLine3()
	{
		return addressLine3;
	}

	/**
	 * Sets the address line3.
	 *
	 * @param addressLine3 the address line3
	 */
	public void setAddressLine3(String addressLine3)
	{
		this.addressLine3 = addressLine3;
	}

	/**
	 * Gets the address line4.
	 *
	 * @return the address line4
	 */
	public String getAddressLine4()
	{
		return addressLine4;
	}

	/**
	 * Sets the address line4.
	 *
	 * @param addressLine4 the address line4
	 */
	public void setAddressLine4(String addressLine4)
	{
		this.addressLine4 = addressLine4;
	}

	/**
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public String getCityName()
	{
		return cityName;
	}

	/**
	 * Sets the city name.
	 *
	 * @param cityName the city name
	 */
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	/**
	 * Gets the state province region.
	 *
	 * @return the state province region
	 */
	public StateProvinceRegion getStateProvinceRegion()
	{
		return stateProvinceRegion;
	}

	/**
	 * Sets the state province region.
	 *
	 * @param stateProvinceRegion the state province region
	 */
	public void setStateProvinceRegion(StateProvinceRegion stateProvinceRegion)
	{
		this.stateProvinceRegion = stateProvinceRegion;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public String getPostalCode()
	{
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the postal code
	 */
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the country
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the note
	 */
	public void setNote(String note)
	{
		this.note = note;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Address [getAddressLine1()=" + getAddressLine1() + ", getAddressLine2()=" + getAddressLine2()
				+ ", getAddressLine3()=" + getAddressLine3() + ", getAddressLine4()=" + getAddressLine4()
				+ ", getCityName()=" + getCityName() + ", getStateProvinceRegion()=" + getStateProvinceRegion()
				+ ", getPostalCode()=" + getPostalCode() + ", getCountry()=" + getCountry() + ", getNote()="
				+ getNote() + ", toString()=" + super.toString() + "]";
	}

}
